package com.aej.ojekkuapi.authentication

import com.aej.ojekkuapi.exception.OjekuException
import com.aej.ojekkuapi.utils.BaseResponse
import com.aej.ojekkuapi.utils.Constants
import com.aej.ojekkuapi.utils.Empty
import com.fasterxml.jackson.databind.ObjectMapper
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.UnsupportedJwtException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import java.util.stream.Collectors
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class AuthenticationFilter : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        try {
            if (JwtConfig.isPermit(request)) {
                filterChain.doFilter(request, response)
            } else {
                val claims = validate(request)
                if (claims[Constants.CLAIMS] != null) {
                    // setup
                    setupAuthentication(claims) {
                        filterChain.doFilter(request, response)
                    }
                } else {
                    SecurityContextHolder.clearContext()
                    throw OjekuException("Token required")
                }
            }
        } catch (e: Exception) {
            val errorResponse = BaseResponse<Empty>()
            response.status = HttpServletResponse.SC_UNAUTHORIZED
            response.contentType = "application/json"
            when (e) {
                is UnsupportedJwtException -> {
                    errorResponse.message = "error unsupported!"
                    val responseString = ObjectMapper()
                        .writerWithDefaultPrettyPrinter()
                        .writeValueAsString(errorResponse)

                    response.writer.println(responseString)
                }

                else -> {
                    errorResponse.message = e.message ?: "token invalid!"
                    val responseString = ObjectMapper()
                        .writerWithDefaultPrettyPrinter()
                        .writeValueAsString(errorResponse)

                    response.writer.println(responseString)
                }
            }
        }
    }

    private fun validate(request: HttpServletRequest): Claims {
        val jwtToken = request.getHeader("Authorization")
        return Jwts.parserBuilder()
            .setSigningKey(Constants.SECRET.toByteArray())
            .build()
            .parseClaimsJws(jwtToken)
            .body
    }

    private fun setupAuthentication(claims: Claims, doOnNext: () -> Unit) {
        val authorities = claims[Constants.CLAIMS] as List<String>
        val authStream = authorities.stream().map { SimpleGrantedAuthority(it) }
            .collect(Collectors.toList())

        val auth = UsernamePasswordAuthenticationToken(claims.subject, null, authStream)
        SecurityContextHolder.getContext().authentication = auth
        doOnNext.invoke()
    }
}