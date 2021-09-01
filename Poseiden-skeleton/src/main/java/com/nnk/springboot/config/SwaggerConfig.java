package com.nnk.springboot.config;


import static springfox.documentation.builders.PathSelectors.any;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 
 * @author Teffery Makonde
 *
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	/**
	 * 
	 * @return
	 */
	@Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors
                        .basePackage("com.nnk.poseidon.controllers"))
                .paths(any()).build().apiInfo(apiDocumentationData());
    }
	
	/**
	 * 
	 * @return
	 */
	private ApiInfo apiDocumentationData() {
        Collection<VendorExtension> vendorExtensions = new ArrayList<>();
        return new ApiInfo("Spring Boot Rest Api",
                "Spring Boot Rest Api for the Poseidon Application",
                "1.0.0", "https://swagger.io/specification/",
                new Contact("Teffery MAKONDE",
                        "https://kingteff99.github.io/3-page-web/",
                        "teffery@makonde.fake-email.com"),
                "Apache Licence Version 2.0",
                "https://www.apache.org/licenses/LICENSE-2.0",
                vendorExtensions);
    }

}
