package com.example.propertysourcedemo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = MultitenantPropertiesTestsContext.class)
public class MultitenantPropertiesTests {

    @Autowired
    MultitenantPropertiesTestsContext context;

    @Autowired
    MultitenantProperties multitenantProperties;

    @Test
    public void valueLoaded() {
        assertEquals("jdbc:postgresql://localhost:5432/default_db", context.getUrl());
    }

    @Test
    public void multitenantPropertiesLoaded() {
        assertTrue(multitenantProperties.getTenants().containsKey("default"));
    }

}

@PropertySource("classpath:multitenant.properties")
@EnableConfigurationProperties(MultitenantProperties.class)
class MultitenantPropertiesTestsContext {
    @Value("${tenants.default.datasource.url}")
    private String url;

    @Bean
    @ConfigurationProperties("tenants")
    public MultitenantProperties multitenantProperties() {
        return new MultitenantProperties();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

