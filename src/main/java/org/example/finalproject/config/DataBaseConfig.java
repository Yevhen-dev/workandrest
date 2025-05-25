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
//                used for add some info to DB

            }
        };
    }
}
