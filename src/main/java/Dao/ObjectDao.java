package Dao;

import Entity.Teacher;

import java.util.List;

public interface ObjectDao<T>{

	 List<T> getAll();

	 boolean create(T obj);

	 boolean update(T obj);

	 boolean delete(T obj);

	 T findById(long id);
}
