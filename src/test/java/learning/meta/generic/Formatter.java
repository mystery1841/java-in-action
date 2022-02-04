package learning.meta.generic;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class Formatter {
    private Map<TypeLiteral<?>, Function<?, String>> rules = new HashMap<>();

    public <T> void forType(TypeLiteral<T> type, Function<T, String> formatterForType) {
        rules.put(type, formatterForType);
    }

    public String formatFields(Object obj)
            throws IllegalArgumentException, IllegalAccessException {
        var result = new StringBuilder();
        for (Field f : obj.getClass().getDeclaredFields()) {
            result.append(f.getName());
            result.append("=");
            f.setAccessible(true);
            Function<?, String> formatterForType = rules.get(TypeLiteral.of(f.getGenericType()));
            if (formatterForType != null) {
                @SuppressWarnings("unchecked")
                Function<Object, String> objectFormatter = (Function<Object, String>) formatterForType;
                result.append(objectFormatter.apply(f.get(obj)));
            } else
                result.append(f.get(obj).toString());
            result.append("\n");
        }
        return result.toString();
    }
}
