package com.example.propertysourcedemo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.env.YamlPropertySourceLoader;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = MultitenantYamlTestsContext.class, initializers = {YamlLoaderApplicationContextInitializer.class})
public class MultitenantYamlTests {

    @Autowired
    MultitenantYamlTestsContext context;

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

@EnableConfigurationProperties(MultitenantProperties.class)
class MultitenantYamlTestsContext {
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

/**
 * https://stackoverflow.com/questions/21483466/how-to-load-application-yaml-config-in-spring-boot-configuration-for-selenium-te
 * https://docs.spring.io/spring-boot/docs/current-SNAPSHOT/reference/html/boot-features-external-config.html
 */
class YamlLoaderApplicationContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
    private static final Logger logger = LoggerFactory.getLogger(YamlLoaderApplicationContextInitializer.class);

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        ConfigurableEnvironment env = applicationContext.getEnvironment();

        YamlPropertySourceLoader loader = new YamlPropertySourceLoader();
        org.springframework.core.env.PropertySource<?> yamlPropertySource = null;
        try {
            yamlPropertySource = loader.load("multitenant.yml", new ClassPathResource("multitenant.yml"), null);
        } catch (IOException e) {
            logger.warn("Unable to load Yaml property sources", e);
        }
        env.getPropertySources().addFirst(yamlPropertySource);
    }
}
