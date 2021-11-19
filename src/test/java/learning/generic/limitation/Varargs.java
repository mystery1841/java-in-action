package learning.generic.limitation;

import java.util.Collection;

public class Varargs {

    @SafeVarargs
    public static <T> void addAll(Collection<T> coll, T... ts) {
        for (T t : ts) coll.add(t);
    }

}
