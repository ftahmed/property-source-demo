package com.example.propertysourcedemo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = CustomProperties.class)
public class CustomPropertiesTests {

	@Autowired
	Environment env;

	@Autowired
	CustomProperties customProperties;

	@Test
	public void contextLoads() {
		assertNull(env.getProperty("level1.prop1"));
		assertNull(env.getProperty("level1.level2.prop2"));
		assertNull(env.getProperty("level1.level2.level3.prop3"));
	}

	@Test
	public void customPropertiesEnv() {
		assertEquals("cval1", env.getProperty("custom.level1.prop1"));
		assertEquals("cval2", env.getProperty("custom.level1.level2.prop2"));
	}

	@Test
	public void customPropertiesBean() {
		assertEquals("cval1", customProperties.getCl1p1());
	}

}
