package dan;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
*/
@Component
public class Dependency {

    @Resource(name = "foreign-dependency")
    private ForeignDependency dependency;

    public String getValue() {
        return dependency.getValue();
    }

}
