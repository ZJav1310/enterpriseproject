package Queries;

import java.sql.SQLException;
import java.util.Collection;
import java.util.function.Predicate;

public interface DatabaseQuery<T> {
    Collection<T> getAll();
    T getById(int id) throws SQLException;
    int insert(T o);
    int update(T o);
    int delete(int i);
    Collection<T> search(Predicate<T> filter);
}