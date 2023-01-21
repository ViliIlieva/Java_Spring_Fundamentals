package bg.softuni.MobiLeLeLe.web;

import org.springframework.web.servlet.ModelAndView;

public abstract class BaseController {

    //използваме го когато ни трябва страница и информация от базата
    public ModelAndView view(String viewName, ModelAndView modelAndView){
        modelAndView.setViewName (viewName);
        return modelAndView;
    }

    //използваме когато ни трябва само страница
    public ModelAndView view(String viewName){
        return this.view (viewName, new ModelAndView ());
    }

    //насочва ни към друга страница
    public ModelAndView redirect(String url){
        return this.view ("redirect:" + url);
    }
}
