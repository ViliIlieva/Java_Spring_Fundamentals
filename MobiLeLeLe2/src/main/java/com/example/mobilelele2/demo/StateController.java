package com.example.mobilelele2.demo;

import com.example.mobilelele2.domain.dtoS.banding.UserRegisterFormDto;
import com.example.mobilelele2.web.BaseController;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.net.http.HttpResponse;

@Controller
@RequestMapping("/demo")
public class StateController extends BaseController {

    private static final String STATE_USERNAME_KEY = "username";

    //1.това е опорната точка като извикаме вюто
    @GetMapping("/register")
    public ModelAndView getRegister() {
        return super.view("demo/register");
    }

    //2.за да се получи в респонса кукито трябва да има сет куки
    //за това ни трябва сервелет респонс, след това му казваме че искаме един рикуестнат параметър т.е. юзър нейм
    //той се намапи чрез нейм валю в инпут
    //после си създаваме едно куки за име то има името на юзъра, и валюто му е полето което сме взели от формата
    //след това добавихме в респонса кукито след което направихме редирект към логин
    //програмата знае че ще получи респонс и при редиректа добавя новите неща които е получило
    //тези две пост и гет заявки са за кукитата
    @PostMapping("/registerTestMe")
    public ModelAndView postRegister(HttpServletResponse response,
                                     @RequestParam(name = STATE_USERNAME_KEY) String username){
        final Cookie cookie = new Cookie(STATE_USERNAME_KEY, username);

        //това е колко да живее едно куки
        cookie.setMaxAge(Integer.MAX_VALUE);
        response.addCookie(cookie);

        return super.redirect("/demo/login");
    }


    @GetMapping("/loginTestMe")
    public ModelAndView getLogin(ModelAndView modelAndView,
                                 @CookieValue(name = STATE_USERNAME_KEY,
                                         defaultValue = "") String username){
//вземаме един обект и му казваме че искаме да добавим информациято която фечнахме от кукито
        modelAndView.addObject(STATE_USERNAME_KEY, username);

        return super.view("/demo/login", modelAndView);
    }


//Тези две заявки пост и гет са за Сесия
    @PostMapping("/register")
    public ModelAndView postRegister(UserRegisterFormDto userRegisterFormDto,//тук по принцип трябва да поставим ДТО със валидации
                                     HttpSession httpSession) {

        httpSession.setAttribute(STATE_USERNAME_KEY, userRegisterFormDto.getUsername());

        return super.redirect("/demo/login");
    }

    @GetMapping("/login")
    public ModelAndView getLogin2(ModelAndView modelAndView,
                                 HttpSession httpSession){

        String fetchedUsername = httpSession.getAttribute(STATE_USERNAME_KEY).toString();

        modelAndView.addObject(STATE_USERNAME_KEY, fetchedUsername);

        return super.view("/demo/login", modelAndView);
    }
}
