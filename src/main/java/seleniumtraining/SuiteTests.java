package seleniumtraining;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ TestRegistration.class, TestRegisterRules.class, TestOnFieldTraining.class
})
public class SuiteTests {

}
