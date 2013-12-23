package dan;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanNotOfRequiredTypeException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;

import java.util.List;

/**
 */
public class CustomBeanFactory implements BeanFactory {

//    private final BeanFactory origin;
    private final List<BeanProvider> providers;

    public CustomBeanFactory(BeanFactory beanFactory,
                             List<BeanProvider> providers)
    {
//        origin = beanFactory;
        this.providers = providers;
    }

    private Object findInBeanProviders(String name) {
        for (BeanProvider provider : providers) {
            Object result = provider.lookup(name);
            if (result != null) {
                return result;
            }
        }
        return null;
    }

    @Override
    public Object getBean(String name) throws BeansException {
        Object result = findInBeanProviders(name);
        if (result == null) {
            throw new NoSuchBeanDefinitionException(name);
//            result = origin.getBean(name);
        }
        return result;
    }

    @Override
    public <T> T getBean(String name, Class<T> requiredType)
            throws BeansException
    {
        Object result = getBean(name);
        if (requiredType.isAssignableFrom(result.getClass())) {
            return (T) result;
        }
        throw new BeanNotOfRequiredTypeException(name, requiredType,
                result.getClass());
    }

    @Override
    public <T> T getBean(Class<T> requiredType)
            throws BeansException
    {
        throw new NoSuchBeanDefinitionException(requiredType);
//        return origin.getBean(requiredType);
    }

    @Override
    public Object getBean(String name, Object... args)
            throws BeansException
    {
        return getBean(name);
    }

    @Override
    public boolean containsBean(String name) {
        return null != findInBeanProviders(name);
    }

    @Override
    public boolean isSingleton(String name)
            throws NoSuchBeanDefinitionException
    {
        return false;
    }

    @Override
    public boolean isPrototype(String name)
            throws NoSuchBeanDefinitionException
    {
        return false;
    }

    @Override
    public boolean isTypeMatch(String name, Class<?> targetType)
            throws NoSuchBeanDefinitionException
    {
        Object result = findInBeanProviders(name);
        if (result != null) {
            return targetType.isAssignableFrom(result.getClass());
        }
        throw new NoSuchBeanDefinitionException(name);
    }

    @Override
    public Class<?> getType(String name)
            throws NoSuchBeanDefinitionException
    {
        Object result = findInBeanProviders(name);
        if (result != null) {
            return result.getClass();
        }
        throw new NoSuchBeanDefinitionException(name);
    }

    @Override
    public String[] getAliases(String name) {
        return new String[0];
    }
}
