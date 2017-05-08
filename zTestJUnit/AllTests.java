package zTestJUnit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ AllDestsTest.class, ConvTest.class, TestNetwork.class, UserTest.class })
public class AllTests {

}
