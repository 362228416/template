package com.ydpp.web;

import com.ydpp.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * @author: john
 * @version: 1.0 2015-02-17 下午10:42
 */
@Controller
public class AppController {

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public String login(@RequestParam("name") String name, HttpSession session) {
        User user = new User();
        user.setName(name);
        session.setAttribute("loginUser", user);
        return "Hi " + name + "~";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public void login() {
    }

    @RequestMapping("/logout")
    @ResponseBody
    public String logout(@RequestParam("name") String name, HttpSession session) {
        session.removeAttribute("loginUser");
        return "Bye " + name + "~";
    }

    @RequestMapping("/")
    public String index() {
        return "index";
    }

}
