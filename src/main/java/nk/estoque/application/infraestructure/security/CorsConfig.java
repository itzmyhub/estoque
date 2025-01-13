package nk.estoque.application.infraestructure.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class CorsConfig implements WebMvcConfigurer {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:3039") // Allows requests from this origin
                        .allowedMethods("GET", "POST", "PUT", "DELETE") // Allows specified HTTP methods
                        .allowedHeaders("*") // Allows all headers
                        .allowCredentials(true); // Allows credentials (e.g., cookies)
            }
        };
    }
}
