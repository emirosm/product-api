package com.ecommerce.ecommerce.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class AdminTestController {

    @GetMapping("/only-admin")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String adminOnly() {
        return "✅ You are an ADMIN!";
    }

    @GetMapping("/shared")
    @PreAuthorize("hasAuthority('ROLE_USER') or hasAuthority('ROLE_ADMIN')")
    public String userOrAdmin() {
        return "✅ You are either a USER or ADMIN!";
    }
}
