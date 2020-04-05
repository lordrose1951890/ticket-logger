package com.ducdh.ticket.config;

import com.google.common.net.HttpHeaders;
import io.swagger.models.auth.In;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.google.common.net.HttpHeaders.*;
import static io.swagger.models.auth.In.HEADER;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    public static final Contact DEFAULT_CONTACT = new Contact("LordRose",
            "https://www.facebook.com/profile.php?id=100021367732299",
            "ducdhse130442@fpt.edu.vn");

    @Bean
    public Docket api() {
        final List<ResponseMessage> getMessages = new ArrayList<>();
        getMessages.add(new ResponseMessageBuilder().code(500).message("500 message")
                .responseModel(new ModelRef("Error")).build());
        getMessages.add(new ResponseMessageBuilder().code(403).message("Forbidden").build());
        getMessages.add(new ResponseMessageBuilder().code(401).message("Unauthorized").build());

        Set<String> produces = new HashSet<>();
        produces.add("application/json");

        Set<String> consumes = new HashSet<>();
        consumes.add("application/json");

        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage("com.ducdh.ticket.controller")).build()
                .securitySchemes(Collections.singletonList(new ApiKey("JWT", AUTHORIZATION, HEADER.name())))
                .securityContexts(Collections.singletonList(
                        SecurityContext.builder()
                                .securityReferences(
                                        Collections.singletonList(
                                                SecurityReference.builder()
                                                        .reference("JWT")
                                                        .scopes(new AuthorizationScope[0])
                                                        .build()
                                        )
                                )
                                .build())
                ).produces(produces).consumes(consumes).globalResponseMessage(RequestMethod.GET, getMessages)
                .globalResponseMessage(RequestMethod.GET, getMessages);
    }

    private ApiInfo apiInfo() {
        return new ApiInfo("Ticket Logger REST API",
                "API for ticket logging. All path requires bearer token except /authenticate",
                "1.0", "urn:tos", DEFAULT_CONTACT, "", "",
                new ArrayList<VendorExtension>());
    }

    private static ArrayList<? extends SecurityScheme> securitySchemes() {
        return (ArrayList<? extends SecurityScheme>) Stream.of(
                new ApiKey("Bearer", "Authorization", "header")
        ).collect(Collectors.toList());
    }
}
