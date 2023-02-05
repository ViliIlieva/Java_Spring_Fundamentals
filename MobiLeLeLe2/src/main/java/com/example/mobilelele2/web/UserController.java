package com.example.mobilelele2.web;

import com.example.mobilelele2.domain.dtoS.banding.UserLoginFormDto;
import com.example.mobilelele2.domain.dtoS.banding.UserRegisterFormDto;
import com.example.mobilelele2.domain.dtoS.model.UserModel;
import com.example.mobilelele2.domain.dtoS.veiw.UserRoleViewDto;
import com.example.mobilelele2.services.role.UserRoleService;
import com.example.mobilelele2.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/users") // url after localhost:8080 -> /users
public class UserController extends BaseController {
    private final UserRoleService roleService;
    private final UserService userService;

    @Autowired
    public UserController(UserRoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @GetMapping("/register") // post method localhost:8080/users/register
    public ModelAndView getRegister(ModelAndView modelAndView) {
        List<UserRoleViewDto> roleServiceAll = this.roleService.getAll();

        modelAndView.addObject("roles", roleServiceAll);

        return super.view("auth-register", modelAndView);
    }

    @PostMapping("/register")
    public ModelAndView postRegister(@ModelAttribute UserRegisterFormDto userRegisterInfo) {
        this.userService.registerUser(userRegisterInfo);

        return super.redirect("login");
    }

    @GetMapping("/login")
    public ModelAndView getLogin(){
        return super.view("auth-login");
    }

    @PostMapping("/login")
    public ModelAndView postLogin(UserLoginFormDto userLoginFormDto){
        UserModel userModel = this.userService.loginUser(userLoginFormDto);

        return userModel.isValid()
                ?
                super.redirect("/")
                :
                super.redirect("login");
    }

    @PostMapping("/logout")
    public ModelAndView postLogout(){
      this.userService.logout ();

        return super.redirect("/");
    }

}
