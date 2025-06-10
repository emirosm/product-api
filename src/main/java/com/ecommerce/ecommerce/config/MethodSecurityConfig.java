package com.ecommerce.ecommerce.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@Configuration
@EnableMethodSecurity // Enables @PreAuthorize, @Secured, etc.
public class MethodSecurityConfig {}
