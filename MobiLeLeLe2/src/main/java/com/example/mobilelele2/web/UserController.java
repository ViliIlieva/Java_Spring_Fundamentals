package com.example.mobilelele2.web;

import com.example.mobilelele2.domain.dtoS.banding.UserLoginFormDto;
import com.example.mobilelele2.domain.dtoS.banding.UserRegisterFormDto;
import com.example.mobilelele2.domain.dtoS.model.UserModel;
import com.example.mobilelele2.domain.dtoS.veiw.UserRoleViewDto;
import com.example.mobilelele2.services.role.UserRoleService;
import com.example.mobilelele2.services.user.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/users") // url after localhost:8080 -> /users
public class UserController extends BaseController {
    public static final String BINDING_RESULT_PATH = "org.springframework.validation.BindingResult.";

    private final UserRoleService roleService;
    private final UserService userService;

    @Autowired
    public UserController(UserRoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    //http mappings

    //при стартиране на нашето приложение таймлийф и спринг ще иницират долните два Модел Атрибута
    @GetMapping("/register") // post method localhost:8080/users/register
    public String getRegister(Model model) {
        return "auth-register";
    }

    //името на модел атрибута е същото като в html th:object за да може да фечне новата инстанция на този модел атрибут
    //
    @PostMapping("/register")
    public String postRegister(@Valid @ModelAttribute(name = "userRegisterForm")//модел атрибута ще направи връзката с html-a и ДТО-то
                                     UserRegisterFormDto userRegisterInfo,
                                     BindingResult bindingResult,//показва резултата от валидациите в ДТО-то
                                     RedirectAttributes redirectAttributes) {//атрибути които се пазят следващите две сесии
        if (bindingResult.hasErrors ()) { //искаме да проверим дали байндинг резълта има грешки
            redirectAttributes.addFlashAttribute ("userRegisterForm", userRegisterInfo) //ако има грешки да направим редирект, като заредим валидните данни
                    .addFlashAttribute (BINDING_RESULT_PATH + "userRegisterForm", bindingResult);//а на полетата с грешки, байдинг резълта да покаже какви са валидните стойности

        return "redirect:register";
        }
        //ако успешно се регистрира, да се пренасочи към логин
        this.userService.registerUser (userRegisterInfo);

        return "redirect:login";
    }

    @GetMapping("/login")
    public ModelAndView getLogin() {
        return super.view ("auth-login");
    }

    @PostMapping("/login")
    public ModelAndView postLogin(@ModelAttribute UserLoginFormDto userLoginFormDto) {
        UserModel userModel = this.userService.loginUser (userLoginFormDto);

        return userModel.isValid ()
                ? super.redirect ("/")
                : super.redirect ("login");
    }

    @PostMapping("/logout")
    public ModelAndView postLogout() {
        this.userService.logout ();

        return super.redirect ("/");
    }

    //Model attributes

    @ModelAttribute(name = "userRegisterForm")
    public UserRegisterFormDto initUserRegisterFormDto() {
        return new UserRegisterFormDto ();
    }

    @ModelAttribute(name = "roles")
    public List<UserRoleViewDto> getAllRoles() {
        return this.roleService.getAll ();
    }
}
