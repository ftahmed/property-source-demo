package com.example.propertysourcedemo;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PropertySourceDemoApplicationTests {

	@Autowired
	Environment env;

	@Autowired
	CustomProperties customProperties;

	@Test
	public void contextLoads() {
		assertEquals("val1", env.getProperty("level1.prop1"));
		assertEquals("val2", env.getProperty("level1.level2.prop2"));
		assertEquals("val3", env.getProperty("level1.level2.level3.prop3"));
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
