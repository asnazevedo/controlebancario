package seleniumtraining.suite;

import static seleniumtraining.core.DriverFactory.killDriver;

import org.junit.AfterClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import seleniumtraining.core.DriverFactory;
import seleniumtraining.test.*;

@RunWith(Suite.class)
@SuiteClasses({
	
	TestRegistration.class,
	TestRegisterRules.class
})
public class SuiteTests {
	@AfterClass
	public static void terminateAll() {
		killDriver();
	}
}
