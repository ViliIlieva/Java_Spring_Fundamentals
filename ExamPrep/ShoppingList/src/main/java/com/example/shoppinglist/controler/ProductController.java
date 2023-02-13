package com.example.shoppinglist.controler;

import com.example.shoppinglist.model.dtos.AddProductDTO;
import com.example.shoppinglist.model.dtos.ProductDTO;
import com.example.shoppinglist.service.AuthService;
import com.example.shoppinglist.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ProductController {
    private final AuthService authService;
    private final ProductService productService;

    @Autowired
    public ProductController(AuthService authService, ProductService productService) {
        this.authService = authService;
        this.productService = productService;
    }

    @ModelAttribute("addProductDTO")
    public AddProductDTO initAddProductDTO() {
        return new AddProductDTO();
    }

    @ModelAttribute("productDTO")
    public ProductDTO initProductDTO() {
        return new ProductDTO();
    }

    @GetMapping("/products/add")
    public String products() {
        if (!this.authService.isLoggedIn()) {
            return "redirect:/";
        }

        return "product-add";
    }

    @PostMapping("/products/add")
    public String products(@Valid AddProductDTO addProductDTO,
                        BindingResult bindingResult,
                        RedirectAttributes redirectAttributes) {
        if (!this.authService.isLoggedIn()) {
            return "redirect:/";
        }

        if (bindingResult.hasErrors() || !this.productService.addProduct(addProductDTO)) {
            redirectAttributes.addFlashAttribute("addProductDTO", addProductDTO);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.addProductDTO", bindingResult);

            return "redirect:/products/add";
        }
        return "redirect:/home";
    }
}
