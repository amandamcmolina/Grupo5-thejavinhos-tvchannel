package com.thejavinhos.tvchannel.swagger;

import com.thejavinhos.tvchannel.entity.User;
import io.swagger.models.Swagger;
import java.util.Arrays;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration
public class SwaggerConfig {



  @Bean
  public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2)
        .select()
        .apis(RequestHandlerSelectors.basePackage("com.thejavinhos.tvchannel"))
        .paths(PathSelectors.ant("/**"))
        .build()
        .apiInfo(metaData())
        .globalOperationParameters(Arrays.asList(
            new ParameterBuilder()
            .name("Authorization")
            .description("Header para token JWT")
            .modelRef(new ModelRef("string"))
            .parameterType("header")
            .required(false)
            .build()
        ))
        .ignoredParameterTypes(User.class);


  }

  private ApiInfo metaData() {
    return new ApiInfoBuilder()
        .title("Tc Channel - The Javinhos")
        .description("API tvChannel - create users and reserve actors")
        .version("1.0")
        .license("Belongs to The Javinhos")
        .licenseUrl("tvChannel.com")
        .build();
  }


}
