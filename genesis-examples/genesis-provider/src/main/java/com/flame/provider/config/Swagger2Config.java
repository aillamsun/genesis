package com.flame.provider.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static com.google.common.collect.Lists.newArrayList;

/**
 * http://localhost:8080/swagger-ui.html
 */
@Configuration
@EnableSwagger2
public class Swagger2Config extends WebMvcConfigurerAdapter {

    @Bean
    public Docket buildDocket() {
        Docket docket = new Docket(DocumentationType.SWAGGER_2);
        //增加前面的 url
//        docket.pathMapping("/v1/api");
        docket.useDefaultResponseMessages(false);
        docket.globalResponseMessage(RequestMethod.GET,
                newArrayList(
                        new ResponseMessageBuilder()
                                .code(500)
                                .message("500 message")
                                .responseModel(new ModelRef("Error"))
                                .build(),
                        new ResponseMessageBuilder()
                                .code(403)
                                .message("Forbidden!").build()));
        docket.enable(true)
                .apiInfo(buildApiInf())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.flame.provider.controller"))//要扫描的API(Controller)基础包
                .paths(PathSelectors.any())
                .build();

        return docket;
//        return new Docket(DocumentationType.SWAGGER_2)
//                .enable(true)
//                .apiInfo(buildApiInf())
//                .select()
//
//                .apis(RequestHandlerSelectors.basePackage("com.flame.demo.controller"))//要扫描的API(Controller)基础包
////                .paths(regex("/api/.*"))
//
//                .paths(PathSelectors.any())
//                .build();
    }

//    @Bean
//    public UiConfiguration uiConfig() {
//        return UiConfiguration.DEFAULT;
//    }

    private ApiInfo buildApiInf() {
        return new ApiInfoBuilder()
                .title("Spring Cloud Test API文档")
                .contact(new Contact("Ailliam'S", "", "sungang_1120@sina.com"))
                .termsOfServiceUrl("")
                .version("1.0")
                .build();
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addRedirectViewController("/documentation/v2/api-docs", "/v2/api-docs");
//        registry.addRedirectViewController("/documentation/configuration/ui", "/configuration/ui");
//        registry.addRedirectViewController("/documentation/configuration/security", "/configuration/security");
//        registry.addRedirectViewController("/documentation/swagger-resources", "/swagger-resources");
//        registry.addRedirectViewController("/documentation", "/documentation/swagger-ui.html");
//        registry.addRedirectViewController("/v1/api/doc", "/swagger-ui.html");

    }


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/documentation/**").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

}
