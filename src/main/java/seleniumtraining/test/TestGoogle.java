package seleniumtraining.test;


import static seleniumtraining.core.DriverFactory.getDriver;
import static seleniumtraining.core.DriverFactory.killDriver;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import seleniumtraining.core.DriverFactory;

public class TestGoogle {
	

	@Before
	public void inicializa(){
		getDriver();
	}
	
	@After
	public void finaliza(){
		killDriver();
	}
	
	@Test
	public void testVerifyTitle() {
		DriverFactory.getDriver().get("http://www.google.com");
		Assert.assertEquals("Google", DriverFactory.getDriver().getTitle());
	}

}
