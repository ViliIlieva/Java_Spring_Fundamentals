package bg.softuni.MobiLeLeLe.web;

import bg.softuni.MobiLeLeLe.model.dtoS.banding.UserRegisterFormDto;
import bg.softuni.MobiLeLeLe.model.dtoS.veiw.UserRoleViewDto;
import bg.softuni.MobiLeLeLe.services.role.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/users")//къде очакваме да е заявката
public class UserController extends BaseController{
    private final UserRoleService roleService;

    @Autowired
    public UserController(UserRoleService roleService) {
        this.roleService = roleService;
    }

    //добавяме някаква дата за да попълним дупките в тайм лийф
    //и тя да се покаже на нашия екран
    @GetMapping("/register")
    public ModelAndView getRegister(ModelAndView modelAndView){
        List<UserRoleViewDto> roleServiceAll = this.roleService.getAll ();//добавяме я към модел енд вю,
        //защото искаме да я визуализираме на нашия екран

        modelAndView.addObject ("roles", roleServiceAll);//той ще стои в roles.html

        return super.view ("auth-register", modelAndView);
    }

    //ако потребителя се е регистрирал отваряме страница за логване
    @PostMapping("/register")
    public ModelAndView postRegister(UserRegisterFormDto userRegister){//искаме да получим това ДТО
        return super.redirect ("auth-login");
    }
}
