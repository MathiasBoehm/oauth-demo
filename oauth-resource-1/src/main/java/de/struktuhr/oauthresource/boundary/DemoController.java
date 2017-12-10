package de.struktuhr.oauthresource.boundary;

import de.struktuhr.oauthresource.control.Resource2Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class DemoController {

    @Autowired
    private Resource2Client resource2Client;

    @PreAuthorize("#oauth2.hasScope('read')")
    @RequestMapping("/demo")
    public String demo() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println("principal = " + principal);
        return "Hello from resource 1\n";
    }


    @PreAuthorize("#oauth2.hasScope('read')")
    @RequestMapping("/demo2")
    public String demo2() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println("principal = " + principal);
        String s = resource2Client.demo();
        return "Hello from resource 1 " + s + "\n";
    }

}
