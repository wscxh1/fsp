package com.foodshare.controller.home;

import com.foodshare.controller.AbstractSessionController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController extends AbstractSessionController {

    @GetMapping("/home")
    public String home() {
        return "/home/admin/home.html";
    }
}
