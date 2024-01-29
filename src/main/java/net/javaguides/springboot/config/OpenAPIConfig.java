package net.javaguides.springboot.config;

import java.util.List;

import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;


public class OpenAPIConfig {

    @Bean
    public OpenAPI myOpenAPI() {
        Server devServer = new Server();
        devServer.setUrl("http://localhost:8080");
        devServer.setDescription("Server URL in Development environment");

        Contact contact = new Contact();
        contact.setEmail("kristopherfana@gmail.com");
        contact.setName("Kristopher Fana");
        contact.setUrl("https://www.bezkoder.com");

        License mitLicense = new License().name("MIT License").url("https://choosealicense.com/licenses/mit/");

        Info info = new Info()
                .title("Barber Booking API")
                .version("1.0")
                .contact(contact)
                .description("This API exposes endpoints and their " +
                        "description" +
                        ".")
                .license(mitLicense);

        return new OpenAPI().info(info).servers((List<Server>) devServer);
    }
}