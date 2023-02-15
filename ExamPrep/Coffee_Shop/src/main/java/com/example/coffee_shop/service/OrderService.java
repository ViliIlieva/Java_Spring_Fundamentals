package com.example.coffee_shop.service;

import com.example.coffee_shop.models.dtos.bindingModels.AddOrderDTO;
import com.example.coffee_shop.models.entity.Order;
import com.example.coffee_shop.repository.CategoryRepository;
import com.example.coffee_shop.repository.OrderRepository;
import com.example.coffee_shop.repository.UserRepository;
import com.example.coffee_shop.session.LoggedUser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;
    private final LoggedUser loggedUser;
    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public OrderService(CategoryRepository categoryRepository,
                        UserRepository userRepository,
                        LoggedUser loggedUser,
                        OrderRepository orderRepository,
                        ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
        this.loggedUser = loggedUser;
        this.orderRepository = orderRepository;
        this.modelMapper = modelMapper;
    }

    public boolean addOrder(AddOrderDTO addOrderDTO) {
        Order order = modelMapper.map(addOrderDTO, Order.class);

        order.setEmployee (userRepository.findByUsername (loggedUser.getUsername ()).get ());
        order.setCategory (categoryRepository.findByName (addOrderDTO.getCategory ()));
        this.orderRepository.save (order);
        return true;
    }

    public void removeOrderById(Long id) {
        this.orderRepository.deleteById (id);
    }
}
