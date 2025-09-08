package com.lab.demo

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.MDC
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class CorrelationIdFilter: OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val correlationId = request.getHeader("X-Correlation-ID") ?: java.util.UUID.randomUUID().toString()
        val traceId = java.util.UUID.randomUUID().toString()
        val spanId = java.util.UUID.randomUUID().toString()
        MDC.put("traceId", traceId)
        MDC.put("spanId", spanId)
        MDC.put("correlationId", correlationId)
        try {
            filterChain.doFilter(request, response)
        } finally {
            MDC.clear()
        }
    }
}