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
                MenuItem drinks  = new MenuItem("Drink", "Cola, Fanta, Srite", 3.2);
                MenuItem coffee = new MenuItem("Coffee", "coffee with milk", 4.0);

                menuItemRepository.save(str);
                menuItemRepository.save(drinks);
                menuItemRepository.save(coffee);


            }
        };
    }
}
