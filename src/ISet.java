import java.util.Iterator;

/*
    Custom Set Interface with provided methods.
 */

public interface ISet<T> {
    boolean add(T e);
    boolean addAll(ISet<T> s);
    boolean contains(T e);
    boolean equals(ISet s);
    Iterator<T> iterator();
    boolean remove(T e);
    int size();
    T[] toArray();
}
