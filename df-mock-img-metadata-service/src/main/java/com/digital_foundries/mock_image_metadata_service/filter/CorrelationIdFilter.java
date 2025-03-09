package com.digital_foundries.mock_image_metadata_service.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.UUID;

@Component
public class CorrelationIdFilter implements Filter {

    private static final String CORRELATION_ID_HEADER = "X-Correlation-Id";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String correlationId = httpRequest.getHeader(CORRELATION_ID_HEADER);

        if (correlationId == null || correlationId.isEmpty()) {
            correlationId = UUID.randomUUID().toString(); // Generate new correlation ID if missing
        }

        MDC.put("correlationId", correlationId); // Store in MDC for logging
        try {
            chain.doFilter(request, response); // Continue request processing
        } finally {
            MDC.remove("correlationId"); // Remove after request completes
        }
    }
}
