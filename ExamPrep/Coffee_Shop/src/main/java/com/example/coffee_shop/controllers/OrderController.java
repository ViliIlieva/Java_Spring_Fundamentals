package com.example.coffee_shop.controllers;

import com.example.coffee_shop.models.dtos.bindingModels.AddOrderDTO;
import com.example.coffee_shop.models.dtos.viewModels.OrderDTO;
import com.example.coffee_shop.service.AuthService;
import com.example.coffee_shop.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class OrderController {
    private final AuthService authService;
    private final OrderService orderService;

    @Autowired
    public OrderController(AuthService authService, OrderService orderService) {
        this.authService = authService;
        this.orderService = orderService;
    }
    @ModelAttribute("addOrderDTO")
    public AddOrderDTO initAddOrderDTO() {
        return new AddOrderDTO();
    }

    @ModelAttribute("OrderDTO")
    public OrderDTO initOrderDTO() {
        return new OrderDTO();
    }

    @GetMapping("/orders/add")
    public String orders() {
        if (!this.authService.isLoggedIn()) {
            return "redirect:/";
        }
        return "order-add";
    }

    @PostMapping("/orders/add")
    public String orders(@Valid AddOrderDTO addOrderDTO,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {
        if (!this.authService.isLoggedIn()) {
            return "redirect:/";
        }
        if (bindingResult.hasErrors() || !this.orderService.addOrder(addOrderDTO)) {
            redirectAttributes.addFlashAttribute("addOrderDTO", addOrderDTO);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.addOrderDTO", bindingResult);

            return "redirect:/orders/add";
        }
        return "redirect:/home";
    }

}
