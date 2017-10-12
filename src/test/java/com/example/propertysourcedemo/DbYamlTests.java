package com.example.propertysourcedemo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.ConfigFileApplicationContextInitializer;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@ContextConfiguration(initializers = ConfigFileApplicationContextInitializer.class, classes = DbYaml.class)
public class DbYamlTests {

	@Autowired
	Environment env;

	@Autowired
	DbYaml dbYaml;

	@Test
	public void contextLoads() {
        assertEquals("val1", env.getProperty("level1.prop1"));
        assertEquals("val2", env.getProperty("level1.level2.prop2"));
		assertEquals("val3", env.getProperty("level1.level2.level3.prop3"));
	}

	@Test
	public void dbPropertiesEnv() {
		// TODO Yaml gets 'flattened' when used in @PropertySource with @ConfigurationProperties.
		//      Also @ConfigurationProperties can't take a prefix. Need to investigate further to understand.
		//      https://github.com/spring-projects/spring-boot/issues/9104
		assertEquals("dburl", env.getProperty("url"));
		assertEquals("dburl", env.getProperty("db.url"));
	}

	@Test
	public void dbPropertiesBean() {
		assertEquals("dburl", dbYaml.getUrl());
		assertEquals("dbuser", dbYaml.getUsername());
		assertEquals("dbpass", dbYaml.getPassword());
	}
}
