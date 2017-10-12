package com.example.propertysourcedemo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource({"classpath:custom.properties"})
public class CustomProperties {

    @Value("${custom.level1.prop1}")
    private String cl1p1;

    public String getCl1p1() {
        return cl1p1;
    }

    public void setCl1p1(String cl1p1) {
        this.cl1p1 = cl1p1;
    }
}
