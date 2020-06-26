package com.ashikhman.elastic.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;

/**
 * Elasticsearch configuration properties.
 */
@ConfigurationProperties(prefix = "app.elasticsearch")
@Data
@Component
public class ElasticsearchProperties {

    /**
     * Service url.
     */
    @NotBlank
    private String url;
}
