package Queries;

import java.util.Collection;
import java.util.function.Predicate;

public interface DatabaseQuery<T> {
    Collection<T> getAll();
    T getById(int id);
    int insert(T o);
    int update(T o);
    int delete(int i);
    Collection<T> search(Predicate<T> filter);
}