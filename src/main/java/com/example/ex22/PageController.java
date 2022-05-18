package com.example.ex22;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PageController {
   @RequestMapping(value = "/home", method = RequestMethod.GET)
   public String index() {
      return "index.html";
   }
   @RequestMapping(value = "/OtherPage", method = RequestMethod.GET)
   public String redirect() {
      return "redirect:/other.html";
   }
}
