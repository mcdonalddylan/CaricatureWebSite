package com.caricature.util;

import java.util.List;

public interface DAOInterface <T,I> {

	public List<T> getAll();
	
	public T get(I idVal);
	
	public boolean create(T t);
	
	public boolean delete(I idVal);
}
