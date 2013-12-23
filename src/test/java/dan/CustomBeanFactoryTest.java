package dan;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;

/**
 */
public class CustomBeanFactoryTest {
    @Test
    public void test() {
        ApplicationContext ac = new CustomApplicationContext("applicationContext.xml");
        Dependency dependency = ac.getBean(Dependency.class);
        Assert.assertEquals("hello world", dependency.getValue());
    }
}
