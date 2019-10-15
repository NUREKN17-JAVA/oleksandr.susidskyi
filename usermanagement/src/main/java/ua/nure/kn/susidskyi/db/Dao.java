package ua.nure.kn.susidskyi.db;

import java.util.Collection;

import ua.nure.kn.susidskyi.usermanagement.User;

public interface Dao<T> {
	User create(User entity) throws DatabaseException;
	
	void update(User entity) throws DatabaseException;
	
	void delete(User entity) throws DatabaseException;
	
	User find(Long id) throws DatabaseException;
	
	Collection<User> findAll() throws DatabaseException;
	
	

}
