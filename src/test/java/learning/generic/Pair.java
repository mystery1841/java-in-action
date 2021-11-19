package learning.generic;

public class Pair<T> {
    private T first;
    private T second;

    public Pair() {
        first = null;
        second = null;
    }

    public Pair(T first, T second) {
        this.first = first;
        this.second = second;
    }

    public T getFirst() {
        return first;
    }

    public T getSecond() {
        return second;
    }

    public void setFirst(T newValue) {
        first = newValue;
    }

    public void setSecond(T newValue) {
        second = newValue;
    }

    public static <T> Pair<T> makePair(Class<T> cl) {
        try {
            return new Pair<>(cl.getConstructor().newInstance(),
                    cl.getConstructor().newInstance());
        } catch (Exception e) {
            return null;
        }
    }

}
