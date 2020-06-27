package com.ashikhman.elastic.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;

@ConfigurationProperties(prefix = "app")
@Component
@Data
public class AppProperties {

    @NotBlank
    private String loansFile;

    @NotBlank
    private String personsFile;
}
