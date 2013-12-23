package dan;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;

import java.util.Arrays;

/**
 */
public class CustomBeanFactoryTest {
    @Test
    public void test() {
        ApplicationContext ac = new CustomApplicationContext(
                "applicationContext.xml",
                Arrays.<BeanProvider>asList(
                        new BeanProvider() {
                            public Object lookup(String name) {
                                if (name.equals(Dependency.FOREIGN_DEPENDENCY)) {
                                    return new ForeignDependency();
                                }
                                return null;
                            }
                        }
                ));
        Dependency dependency = ac.getBean(Dependency.class);
        Assert.assertEquals("hello world", dependency.getValue());
    }
}
