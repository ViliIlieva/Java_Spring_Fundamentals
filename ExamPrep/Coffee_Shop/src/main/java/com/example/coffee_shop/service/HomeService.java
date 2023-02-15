package com.example.coffee_shop.service;

import com.example.coffee_shop.models.dtos.viewModels.OrderDTO;
import com.example.coffee_shop.models.dtos.viewModels.UserViewDTO;
import com.example.coffee_shop.models.entity.Order;
import com.example.coffee_shop.repository.OrderRepository;
import com.example.coffee_shop.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class HomeService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public HomeService(OrderRepository orderRepository, UserRepository userRepository, ModelMapper modelMapper) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    public List<OrderDTO> getAllOrders() {
        return this.orderRepository.findAllByOrderByPriceDesc ()
                .stream ()
                .map (order -> modelMapper.map (order, OrderDTO.class))
                .toList ();
    }

    public BigDecimal getOrdersTotalTime(){
        return this.orderRepository.findAll ()
                .stream ()
                .map (Order::getPrice)
                .reduce (BigDecimal::add)
                .orElse (BigDecimal.ZERO);
    }

    public List<UserViewDTO> findCountOfOrdersByEmployee() {
        return userRepository.findCountOfOrdersByEmployee ()//открий всички юзъри с техните заявки в базата
                .stream ()
                .map (user -> { //мапни всеки един от тях
                    UserViewDTO userViewDTO = modelMapper.map (user, UserViewDTO.class);//към юзър вюто
                    userViewDTO.setCountOfOrders (user.getOrders ().size ()); //сетни им броя на заявките като вземеш на юзъра заявките, техния размер
                    return userViewDTO;//върни готовото вю
                })
                .toList ();//съхрани всичко в лист
    }
}
