package learning.advanced.annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE,ElementType.TYPE_USE,ElementType.PARAMETER})
@Inherited
public @interface ReadOnly {
}
