package model.repositories;

public interface BasicCrud {
	
	Object create(Object object);
	
	Object findById(Integer id);
	
	Object updateById(Object object);
	
	void delete(Integer id);
	
}
