package de.struktuhr.oauthauthserver.boundary;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GenralController {

    @RequestMapping("/")
    public String home() {
        return "Hello from home";
    }
}
