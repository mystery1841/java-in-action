package learning.advanced.annotation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Method;

public class AnnotationTest {


    @Test
    public void testInheritedAnnotation() {
        Class<Foo> cl = Foo.class;
        ReadOnly readOnly = cl.getAnnotation(ReadOnly.class);
        Assertions.assertNotNull(readOnly);
        Class<Bar> clr = Bar.class;
        Assertions.assertTrue(clr.isAnnotationPresent(ReadOnly.class));
        ReadOnly readOnly2 = clr.getAnnotation(ReadOnly.class);
        Assertions.assertNotNull(readOnly2);
    }

    @Test
    public void testAnnotationTypeUse() throws NoSuchMethodException {
        Class<Foo> cl = Foo.class;
        Method method = cl.getMethod("foo", String.class);
        Annotation[][] annotations = method.getParameterAnnotations();
        AnnotatedType[] types = method.getAnnotatedParameterTypes();
        for (AnnotatedType annotatedType : types) {
            System.out.println("TYPE_USE");
            System.out.println(annotatedType.isAnnotationPresent(ReadOnly.class));
            System.out.println(annotatedType.getType().getTypeName());
        }
        for (Annotation[] as:annotations){
            System.out.println("PARAMETER");
            for (Annotation a:as){
                System.out.println(a.annotationType().getCanonicalName());
            }
        }
    }
}
