package dao;

import java.util.List;
import java.util.Optional;


//todo genericamente si usa una classe generica di base per le operazioni CRUD
public interface Dao<T> {

    Optional<T> get(int id);

    List<T> getAll();

    void save(T t);

    void update(T t, String[] params);

    void delete(T t);
}