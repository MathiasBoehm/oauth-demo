package de.struktuhr.oauthresource.boundary;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class DemoController {

    @PreAuthorize("#oauth2.hasScope('read')")
    @RequestMapping("/demo")
    public String demo() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println("principal = " + principal);
        return "Hello from resource 2\n";
    }
}
