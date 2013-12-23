package dan;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
*/
@Component
public class Dependency {

    public static final String FOREIGN_DEPENDENCY = "foreign-dependency";

    @Resource(name = FOREIGN_DEPENDENCY)
    private ForeignDependency dependency;

    public String getValue() {
        return dependency.getValue();
    }

}
