package ua.nure.kn.susidskyi.db;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.LinkedList;

import ua.nure.kn.susidskyi.usermanagement.User;

public class HsqldbUserDao implements Dao<User> {
	private static final String SELECT_FROM_USERS = "SELECT * from users";
	//4 QUERIES!!!
	private static final String INSERT_QUERY = "INSERT INTO users (firstname, lastname, dateofbirth) VALUES (?, ?, ?)";
	private ConnectionFactory connectionFactory;
	
	public void HsqlUserDao(ConnectionFactory connectionFactory) {
		this.connectionFactory = connectionFactory;
	}
	
	@Override
	public User create(User entity) throws DatabaseException {
		try {
			Connection connection = connectionFactory.createConnection();
			PreparedStatement statement = connection
					.prepareStatement(INSERT_QUERY);
			statement.setString(1, entity.getFirstName());
			statement.setString(2, entity.getLastName());
			statement.setDate(3, new Date(entity.getDateOfBirth().getTime()));
			int numberOfRows = statement.executeUpdate();
			if (numberOfRows != 1) {
				throw new DatabaseException("Number of inserted rows:" + numberOfRows);
			}
			CallableStatement callableStatement = connection
					.prepareCall("call IDENTITY()");
			ResultSet keys = callableStatement.executeQuery();
			if (keys.next()) {
				entity.setId(keys.getLong(1)); //Mutation
			}
			keys.close();
			callableStatement.close();
			statement.close();
			connection.close();
			return entity;
		} catch(DatabaseException e){
			throw e; 
		}
		catch(SQLException e) {
			throw new DatabaseException(e);
		}
	}

	@Override
	public void update(User entity) throws DatabaseException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(User entity) throws DatabaseException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public User find(Long id) throws DatabaseException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<User> findAll() throws DatabaseException {
		Collection<User> result = new LinkedList<User>();
		try {
			Connection connection = connectionFactory.createConnection();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(SELECT_FROM_USERS);
			while (resultSet.next()) {
				User user = new User(1L, "Александр", "Соседский", new SimpleDateFormat("d-MM-yyyy").parse("11-09-2000"));
				user.setId(resultSet.getLong(1));
				user.setFirstName(resultSet.getString(2));
				user.setLastName(resultSet.getString(3));
				user.setDateOfBirth(resultSet.getDate(4));
				result.add(user);
			}
			resultSet.close();
			statement.close();
			connection.close();
		}
		catch(DatabaseException e) {
			throw e;
		}
		catch(SQLException e) {
			throw new DatabaseException(e);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return result;
	}

}
