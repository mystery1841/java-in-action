package learning.meta.proxy;

import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.Random;

public class ProxyTest {
    public static void main(String[] args) {
        var elements = new Object[1000];
        for (int i = 0; i < elements.length; i++) {
            Integer value = i + 1;
            var handler = new TraceHandler(value);
            Object proxy = Proxy.newProxyInstance(
                    ClassLoader.getSystemClassLoader(),
                    new Class[]{Comparable.class}, handler);
            elements[i] = proxy;
        }
        Integer key = new Random().nextInt(elements.length) + 1;
        int result = Arrays.binarySearch(elements, key);
        if (result >= 0) System.out.println(elements[result]);
    }
}
