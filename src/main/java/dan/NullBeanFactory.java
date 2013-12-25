package dan;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;

/**
 */
public class NullBeanFactory implements BeanFactory {
    @Override
    public Object getBean(String name) throws BeansException {
        throw new NoSuchBeanDefinitionException(name);
    }

    @Override
    public <T> T getBean(String name, Class<T> requiredType) throws BeansException {
        throw new NoSuchBeanDefinitionException(name);
    }

    @Override
    public <T> T getBean(Class<T> requiredType) throws BeansException {
        throw new NoSuchBeanDefinitionException(requiredType);
    }

    @Override
    public Object getBean(String name, Object... args) throws BeansException {
        throw new NoSuchBeanDefinitionException(name);
    }

    @Override
    public boolean containsBean(String name) {
        return false;
    }

    @Override
    public boolean isSingleton(String name) throws NoSuchBeanDefinitionException {
        return false;
    }

    @Override
    public boolean isPrototype(String name) throws NoSuchBeanDefinitionException {
        return false;
    }

    @Override
    public boolean isTypeMatch(String name, Class<?> targetType)
            throws NoSuchBeanDefinitionException
    {
        throw new NoSuchBeanDefinitionException(name);
    }

    @Override
    public Class<?> getType(String name) throws NoSuchBeanDefinitionException {
        throw new NoSuchBeanDefinitionException(name);
    }

    @Override
    public String[] getAliases(String name) {
        return new String[0];
    }
}
