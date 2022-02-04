package learning.meta.generic;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class TypeLiteral<T> {
    private Type type;

    public TypeLiteral() {
        Type parentType = getClass().getGenericSuperclass();
        if (parentType instanceof ParameterizedType) {
            type = ((ParameterizedType) parentType).getActualTypeArguments()
                    [0];
        } else
            throw new UnsupportedOperationException(
                    "Construct as new TypeLiteral<. . .>(){}");
    }

    private TypeLiteral(Type type) {
        this.type = type;
    }

    public static TypeLiteral<?> of(Type type) {
        return new TypeLiteral<Object>(type);

    }

    public String toString() {
        if (type instanceof Class) return ((Class<?>) type).getName();
        else return type.toString();
    }

    public boolean equals(Object otherObject) {
        return otherObject instanceof TypeLiteral
                && type.equals(((TypeLiteral<?>) otherObject).type);

    }

    public int hashCode() {
        return type.hashCode();
    }


}
