package seleniumtraining.core;

import static seleniumtraining.core.DriverFactory.killDriver;

import org.junit.After;

public class BaseTest {
	
	@After
	public void terminate(){
		if(Properties.CLOSE_BROWSER) killDriver();
	}
}
