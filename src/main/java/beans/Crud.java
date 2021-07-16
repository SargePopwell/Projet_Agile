package beans;

import java.util.List;

public interface Crud<T> {

	public void insert();
	public List<?> selectAll();
	public T select();
	public void update();
	public void delete();
}