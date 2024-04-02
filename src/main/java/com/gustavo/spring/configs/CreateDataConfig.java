package com.gustavo.spring.configs;

import com.gustavo.spring.domain.Category;
import com.gustavo.spring.domain.Order;
import com.gustavo.spring.domain.Product;
import com.gustavo.spring.domain.User;
import com.gustavo.spring.domain.enums.OrderStatus;
import com.gustavo.spring.domain.relationship.OrderItemRelationship;
import com.gustavo.spring.domain.relationship.pks.OrderItemPK;
import com.gustavo.spring.repositories.*;
import jakarta.persistence.EntityManager;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

@Configuration
public class CreateDataConfig implements CommandLineRunner {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRelationshipRepository orderItemRelationshipRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;


    private final Faker faker = new Faker();

    private final Random random = new Random();


    @Override
    public void run(String... args) {
        AtomicInteger counter = new AtomicInteger();

        int usersQuantity = 3;
        var users = createUsers(usersQuantity);

        int ordersQuantity = usersQuantity * 2;
        var orders = createOrders(ordersQuantity);

        int ordersPerUser = ordersQuantity / usersQuantity;
        counter.set(0);
        users.forEach(user -> {
            for (int i = 0; i < ordersPerUser; i++) {
                user.getOrders().add(orders.get(counter.get()));
                orders.get(counter.get()).setClient(user);

                counter.getAndIncrement();
            }
        });

        userRepository.saveAll(users);
        orderRepository.saveAll(orders);


        int categoriesQuantity = 4;
        var categories = createCategories(categoriesQuantity);
        int productsQuantity = 10;
        var products = createProducts(productsQuantity);

        products.forEach(product -> {
            int randomIndex = random.nextInt(categoriesQuantity);
            var category = categories.get(randomIndex);

            product.getCategories().add(category);
            category.getProducts().add(product);
        });

        categoryRepository.saveAll(categories);
        productRepository.saveAll(products);

        int orderItemQuantity = 4;
        var orderItemRelationships = createOrderItemRelationship(orderItemQuantity);

        orderItemRelationships.forEach(orderItemRelationship -> {
            Order order = orders.get(random.nextInt(orders.size() - 1));
            Product product = products.get(random.nextInt(products.size() - 1));
            OrderItemPK orderItemPK = OrderItemPK.builder()
                    .order(order)
                    .product(product)
                    .build();

            orderItemRelationship.setId(orderItemPK);
            orderItemRelationship.setPrice(orderItemPK.getProduct().getPrice());
        });

        orderItemRelationshipRepository.saveAll(orderItemRelationships);
    }

    private List<User> createUsers(int quantity) {
        List<User> users = new ArrayList<>();

        for (int i = 0; i < quantity; i++) {
            users.add(User.builder()
                    .phone(faker.phoneNumber().phoneNumber())
                    .name(faker.name().fullName())
                    .email(faker.internet().emailAddress())
                    .password(faker.internet().password())
                    .build()
            );
        }

        return users;
    }

    private List<Order> createOrders(int quantity) {
        List<Order> orders = new ArrayList<>();

        for (int i = 0; i < quantity; i++) {
            orders.add(Order.builder()
                    .moment(Instant.now())
                    .orderStatus(OrderStatus.PAID)
                    .build()
            );
        }

        return orders;
    }

    private List<Category> createCategories(int quantity) {
        List<Category> categories = new ArrayList<>();

        for (int i = 0; i < quantity; i++) {
            categories.add(Category.builder()
                    .name(faker.commerce().material())
                    .build()
            );
        }

        return categories;
    }

    private List<Product> createProducts(int quantity) {
        List<Product> products = new ArrayList<>();

        for (int i = 0; i < quantity; i++) {
            products.add(Product.builder()
                    .name(faker.commerce().productName())
                    .description(faker.restaurant().description().substring(0, 50))
                    .price(Double.parseDouble(faker.commerce().price()))
                    .imgUrl(faker.file().fileName())
                    .build()
            );
        }

        return products;
    }

    private List<OrderItemRelationship> createOrderItemRelationship(int quantity) {
        List<OrderItemRelationship> orderItemRelationships = new ArrayList<>();

        for (int i = 0; i < quantity; i++) {
            orderItemRelationships.add(OrderItemRelationship.builder()
                    .quantity(random.nextInt(5) + 1)
                    .build()
            );
        }

        return orderItemRelationships;
    }

}
