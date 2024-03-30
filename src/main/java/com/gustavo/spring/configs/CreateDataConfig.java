package com.gustavo.spring.configs;

import com.gustavo.spring.domain.Order;
import com.gustavo.spring.domain.User;
import com.gustavo.spring.domain.enums.OrderStatus;
import com.gustavo.spring.repositories.OrderRepository;
import com.gustavo.spring.repositories.UserRepository;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class CreateDataConfig implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    private final Faker randomData = new Faker();

    @Override
    public void run(String... args) {
        var users = createUsers(3);
        var orders = createOrders(users, 2);

        userRepository.saveAll(users);
        orderRepository.saveAll(orders);
    }

    private List<User> createUsers(int quantity) {
        List<User> users = new ArrayList<>();

        for (int i = 0; i < quantity; i++) {
            users.add(User.builder()
                    .phone(randomData.phoneNumber().phoneNumber())
                    .name(randomData.name().fullName())
                    .email(randomData.internet().emailAddress())
                    .password(randomData.internet().password())
                    .build()
            );
        }

        return users;
    }

    private List<Order> createOrders(List<User> users, int quantityPerUser) {
        List<Order> orders = new ArrayList<>();

        users.forEach(user -> {
            for (int i = 0; i < quantityPerUser; i++) {
                var order = Order.builder()
                        .client(user)
                        .moment(Instant.now())
                        .orderStatus(OrderStatus.PAID)
                        .build();

                orders.add(order);
            }
        });

        return orders;
    }

}
