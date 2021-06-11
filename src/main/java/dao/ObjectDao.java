package dao;

import java.util.List;

public interface ObjectDao<T>{

	 List<T> getAll();

	 boolean create(T obj);

	 boolean update(T obj);

	 boolean delete(long id);

	 T findById(long id);
}
