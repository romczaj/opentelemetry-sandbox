package pl.romczaj.opentelemetry.order.app;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.support.RestTemplateAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;
import org.springframework.web.util.DefaultUriBuilderFactory;

@Configuration
class RestClientConfiguration {

    private static final String PRODUCT_SERVICE_URL = "http://localhost:8081";

    @Bean
    RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    ProductServiceClient productServiceClient(RestTemplate restTemplate) {
        restTemplate.setUriTemplateHandler(new DefaultUriBuilderFactory(PRODUCT_SERVICE_URL));
        RestTemplateAdapter adapter = RestTemplateAdapter.create(restTemplate);
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter).build();
        return factory.createClient(ProductServiceClient.class);
    }
}
