package learning.generic.limitation;

public class GenericArray <E>{
    private Object[] elements;
    @SuppressWarnings("unchecked")
    public E get(int n) { return (E) elements[n]; }
    public void set(int n, E e) { elements[n] = e; }
}
