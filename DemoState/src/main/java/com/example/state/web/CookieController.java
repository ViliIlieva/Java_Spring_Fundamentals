package com.example.state.web;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class CookieController {

  private static final String LANG_COOKIE_NAME = "lang";//име на кукито

  @GetMapping("/cookies")
  public String cookies(Model model,@CookieValue( //опитваме се да прочетем куки с име ланг от това което се връща към нас от web
                            name = LANG_COOKIE_NAME,
                            defaultValue = "en"//дефолтната стойност
                        ) String lang) {

    model.addAttribute("lang", lang);//стойността идва от кукито
    return "cookies";
  }

  @PostMapping("/cookies")//когато се извика
  public String cookies(
          HttpServletResponse response, //спринг инжектира репрезентация на отговора който връщаме на клиента
          @RequestParam("language") String language) {

    Cookie cookie = new Cookie(LANG_COOKIE_NAME, language);//сетваме куки
    response.addCookie(cookie);//в респонса добавяме кукито

    return "redirect:/cookies";//презареждаме същата страница но вече с ново избрания език
    //и се появява обновения надпис за езика
  }

}
