package dan;

import org.springframework.beans.BeansException;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 */
public class CustomApplicationContext extends ClassPathXmlApplicationContext {
    public CustomApplicationContext(String configLocation) throws BeansException {
        super(configLocation);
    }
}
