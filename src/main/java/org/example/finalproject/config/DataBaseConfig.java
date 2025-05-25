package org.example.finalproject.config;


import org.example.finalproject.domains.MenuItem;
import org.example.finalproject.repositories.MenuItemRepository;
import org.example.finalproject.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class DataBaseConfig implements WebMvcConfigurer {


    @Bean
    public CommandLineRunner commandLineRunner(final UserService userService, final MenuItemRepository menuItemRepository) {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
//                userService.registerUser("admin", "1234", "admin@gmail.com", "ROLE_ADMIN");
//                userService.registerUser("trial", "1234", "trial@gmail.com", "ROLE_USER");
//                userService.registerUser("Me", "1234", "springtrialmail@gmail.com", "ROLE_USER");
//
                MenuItem str = new MenuItem("Strawberry danish", "pastry with strawberry jam", 5.3);
                MenuItem drinks  = new MenuItem("Drink", "Cola, Fanta, Sprite", 3.2);
                MenuItem coffee = new MenuItem("Coffee", "coffee with milk", 4.0);

                MenuItem pizza = new MenuItem("Margherita Pizza", "classic pizza with tomatoes and mozzarella", 8.5);
                MenuItem burger = new MenuItem("Cheeseburger", "beef burger with cheese and lettuce", 7.0);
                MenuItem salad = new MenuItem("Caesar Salad", "salad with chicken, lettuce and Caesar dressing", 6.5);
                MenuItem pasta = new MenuItem("Spaghetti Carbonara", "pasta with eggs, bacon and cheese", 9.0);
                MenuItem soup = new MenuItem("Tomato Soup", "fresh tomato soup with basil", 4.5);
                MenuItem sandwich = new MenuItem("Club Sandwich", "triple layered sandwich with turkey and bacon", 6.8);
                MenuItem iceCream = new MenuItem("Vanilla Ice Cream", "creamy vanilla ice cream scoop", 3.7);
                MenuItem tea = new MenuItem("Green Tea", "fresh brewed green tea", 2.9);

                menuItemRepository.save(str);
                menuItemRepository.save(drinks);
                menuItemRepository.save(coffee);
                menuItemRepository.save(pizza);
                menuItemRepository.save(burger);
                menuItemRepository.save(salad);
                menuItemRepository.save(pasta);
                menuItemRepository.save(soup);
                menuItemRepository.save(sandwich);
                menuItemRepository.save(iceCream);
                menuItemRepository.save(tea);



            }
        };
    }
}
