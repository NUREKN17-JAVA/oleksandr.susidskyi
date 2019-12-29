package ua.nure.kn.susidskyi.db;

import java.util.Collection;

import ua.nure.kn.susidskyi.usermanagment.domain.User;

public interface Dao<T>{
	User create(User user) throws DataBaseException;
	
	void update(User user) throws DataBaseException;
	
	void delete(User user) throws DataBaseException;
	
	User find(Long id) throws DataBaseException;
	
	Collection<User> findAll() throws DataBaseException;
	
	void setConnectionFactory(ConnectionFactory connectionFactory);
}