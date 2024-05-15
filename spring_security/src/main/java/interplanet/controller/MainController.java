package interplanet.controller;


import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/secured")
public class MainController {



    @GetMapping("/user")
    @ResponseBody
    public String userAccess(Principal principal) {
        if (principal == null) {
            return null;
        }

        System.out.println(SecurityContextHolder.getContext());
        System.out.println( "Main contr " + principal.getName());
        return principal.getName();
    }



}
