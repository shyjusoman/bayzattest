package com.mycom.platform.configuration;
/*
 * package com.bayzat.platform.configuration;
 * 
 * import org.springframework.context.annotation.Bean; import
 * org.springframework.context.annotation.Configuration; import
 * org.springframework.context.annotation.Primary; import
 * org.springframework.hateoas.client.LinkDiscoverer; import
 * org.springframework.hateoas.client.LinkDiscoverers; import
 * org.springframework.hateoas.mediatype.collectionjson.
 * CollectionJsonLinkDiscoverer; import
 * org.springframework.plugin.core.SimplePluginRegistry;
 * 
 * import io.swagger.models.Contact; import
 * springfox.documentation.builders.RequestHandlerSelectors; import
 * springfox.documentation.service.ApiInfo; import
 * springfox.documentation.spi.DocumentationType; import
 * springfox.documentation.spring.web.plugins.Docket; import
 * springfox.documentation.swagger2.annotations.EnableSwagger2; import static
 * springfox.documentation.builders.PathSelectors.regex;
 * 
 * import java.util.ArrayList; import java.util.List;
 * 
 * @Configuration
 * 
 * @EnableSwagger2 public class SwaggerConfig {
 * 
 * @Bean public Docket productApi() { return new
 * Docket(DocumentationType.SWAGGER_2)
 * .select().apis(RequestHandlerSelectors.basePackage(
 * "com.bayzat.platform.hr.controllers")) .paths(regex("/companies.*"))
 * .build().apiInfo(customApiInfo()); }
 * 
 * private ApiInfo customApiInfo() { ApiInfo apiInfo = new
 * ApiInfo("Bayzat Rest API","Bayzat HR Platform Rest API"
 * ,"1.0","Terms of service","Shaiju Soman","License Version 1.0"
 * ,"http://localhost:8080/license.html"); return apiInfo; }
 * 
 * @Primary
 * 
 * @Bean public LinkDiscoverers discoverers() { List<LinkDiscoverer> plugins =
 * new ArrayList<>(); plugins.add(new CollectionJsonLinkDiscoverer()); return
 * new LinkDiscoverers(SimplePluginRegistry.create(plugins)); } }
 */