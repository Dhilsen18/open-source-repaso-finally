package com.equinix.platform.u202319440.shared.interfaces.rest;

import jakarta.servlet.http.HttpServletRequest;

import java.util.Locale;

/**
 * Resolves request locale from the Accept-Language header.
 */
public final class RequestLocaleResolver {

    private RequestLocaleResolver() {
    }

    /**
     * Resolves the locale for the current HTTP request.
     *
     * @param request HTTP servlet request
     * @return resolved locale (English or Spanish variants)
     */
    public static Locale resolve(HttpServletRequest request) {
        if (request == null) {
            return Locale.ENGLISH;
        }
        String acceptLanguage = request.getHeader("Accept-Language");
        if (acceptLanguage != null) {
            String normalized = acceptLanguage.toLowerCase(Locale.ROOT);
            if (normalized.startsWith("es")) {
                return Locale.forLanguageTag("es");
            }
            if (normalized.startsWith("en")) {
                return Locale.ENGLISH;
            }
        }
        return Locale.ENGLISH;
    }
}
