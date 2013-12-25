package dan;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanNotOfRequiredTypeException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;

import java.util.List;

/**
 */
public class CustomBeanFactory implements BeanFactory {

    private final CustomApplicationContext context;
    private final List<BeanProvider> providers;
    private final BeanFactory nullFactory = new NullBeanFactory();

    public CustomBeanFactory(CustomApplicationContext context,
                             List<BeanProvider> providers)
    {
        this.context = context;
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

    private BeanFactory getOrigin() {
        BeanFactory result = context.getOriginParentFactory();
        if (result == null) {
            return nullFactory;
        }
        return result;
    }

    @Override
    public Object getBean(String name) throws BeansException {
        Object result = findInBeanProviders(name);
        if (result == null) {
            result = getOrigin().getBean(name);
        }
        return result;
    }

    @Override
    public <T> T getBean(String name, Class<T> requiredType)
            throws BeansException
    {
        Object result = findInBeanProviders(name);
        if (result == null) {
            return getOrigin().getBean(name, requiredType);
        } else {
            if (requiredType.isAssignableFrom(result.getClass())) {
                return (T) result;
            }
            throw new BeanNotOfRequiredTypeException(name, requiredType,
                    result.getClass());
        }
    }

    @Override
    public <T> T getBean(Class<T> requiredType)
            throws BeansException
    {
        return getOrigin().getBean(requiredType);
    }

    @Override
    public Object getBean(String name, Object... args)
            throws BeansException
    {
        return getOrigin().getBean(name, args);
    }

    @Override
    public boolean containsBean(String name) {
        return null != findInBeanProviders(name)
                || getOrigin().containsBean(name);
    }

    @Override
    public boolean isSingleton(String name)
            throws NoSuchBeanDefinitionException
    {
        if (findInBeanProviders(name) == null) {
            return getOrigin().isSingleton(name);
        } else {
            return false;
        }
    }

    @Override
    public boolean isPrototype(String name)
            throws NoSuchBeanDefinitionException
    {
        if (findInBeanProviders(name) == null) {
            return getOrigin().isPrototype(name);
        } else {
            return false;
        }
    }

    @Override
    public boolean isTypeMatch(String name, Class<?> targetType)
            throws NoSuchBeanDefinitionException
    {
        Object result = findInBeanProviders(name);
        if (result == null) {
            return getOrigin().isTypeMatch(name, targetType);
        } else {
            return targetType.isAssignableFrom(result.getClass());
        }
    }

    @Override
    public Class<?> getType(String name)
            throws NoSuchBeanDefinitionException
    {
        Object result = findInBeanProviders(name);
        if (result == null) {
            return getOrigin().getType(name);
        } else {
            return result.getClass();
        }
    }

    @Override
    public String[] getAliases(String name) {
        if (findInBeanProviders(name) == null) {
            return getOrigin().getAliases(name);
        } else {
            return new String[0];
        }
    }
}
