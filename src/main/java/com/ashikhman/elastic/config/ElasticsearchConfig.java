package com.ashikhman.elastic.config;

import lombok.RequiredArgsConstructor;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;

/**
 * Elasticsearch bean configuration.
 */
@Configuration
@RequiredArgsConstructor
public class ElasticsearchConfig {

    /**
     * Configuration properties.
     */
    private final ElasticsearchProperties properties;

    /**
     * Elasticsearch client bean.
     *
     * @return the client
     */
    @Bean
    @Primary
    public RestHighLevelClient restHighLevelClient() {
        ClientConfiguration clientConfiguration
                = ClientConfiguration.builder()
                .connectedTo(properties.getUrl())
                .build();

        return RestClients.create(clientConfiguration).rest();
    }

    /**
     * Elasticsearch template bean for document operations.
     *
     * @param client elasticsearch client
     * @return template bean
     */
    @Bean
    public ElasticsearchOperations elasticsearchTemplate(RestHighLevelClient client) {
        return new ElasticsearchRestTemplate(client);
    }
}
