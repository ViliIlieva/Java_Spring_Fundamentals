package com.example.shoppinglist.service;

import com.example.shoppinglist.model.dtos.AddProductDTO;
import com.example.shoppinglist.model.dtos.ProductDTO;
import com.example.shoppinglist.model.entity.Category;
import com.example.shoppinglist.model.entity.CategoryEnum;
import com.example.shoppinglist.model.entity.Product;
import com.example.shoppinglist.repository.CategoryRepository;
import com.example.shoppinglist.repository.ProductRepository;
import com.example.shoppinglist.repository.UserRepository;
import com.example.shoppinglist.session.LoggedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;
    private final LoggedUser loggedUser;
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(CategoryRepository categoryRepository,
                          UserRepository userRepository,
                          LoggedUser loggedUser,
                          ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
        this.loggedUser = loggedUser;
        this.productRepository = productRepository;
    }

    public boolean addProduct(AddProductDTO addProductDTO) {
        CategoryEnum type = switch (addProductDTO.getCategory ().toString ().toUpperCase ()) {
            case "FOOD" -> CategoryEnum.FOOD;
            case "DRINK" -> CategoryEnum.DRINK;
            case "HOUSEHOLD" -> CategoryEnum.HOUSEHOLD;
            case "OTHER" -> CategoryEnum.OTHER;
            default -> CategoryEnum.FOOD;
        };

        Category category = this.categoryRepository.findByName (type);

        Product product = new Product ();
        product.setName (addProductDTO.getName ());
        product.setDescription (addProductDTO.getDescription ());
        product.setNeededBefore (addProductDTO.getNeededBefore ());
        product.setPrice (addProductDTO.getPrice ());
        product.setCategory (category);

        this.productRepository.save (product);
        return true;
    }

    public Product findSongById(Long productID) {
        return this.productRepository.findById (productID).orElseThrow ();
    }

    private ProductDTO mapSongDTO(Product product) {
        ProductDTO productDTO = new ProductDTO ();
        productDTO.setId (product.getId ());
        productDTO.setName (product.getName ());
        productDTO.setPrice (product.getPrice ());
        return productDTO;
    }
}
