package dan;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;

/**
 */
public class CustomApplicationContext extends ClassPathXmlApplicationContext {

    private final List<BeanProvider> providers;

    public CustomApplicationContext(String configLocation,
                                    List<BeanProvider> providers)
            throws BeansException
    {
        super(new String[] { configLocation }, false);
        this.providers = providers;
        refresh();
    }

    @Override
    protected BeanFactory getInternalParentBeanFactory() {
        return new CustomBeanFactory(
                super.getInternalParentBeanFactory(),
                providers);
    }
}
