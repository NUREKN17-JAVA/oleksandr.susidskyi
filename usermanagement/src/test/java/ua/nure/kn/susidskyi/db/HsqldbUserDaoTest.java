package ua.nure.kn.susidskyi.db;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.dbunit.DatabaseTestCase;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;

import ua.nure.kn.susidskyi.usermanagement.User;

public class HsqldbUserDaoTest extends DatabaseTestCase {
	private HsqldbUserDao dao;
	protected void setUp() throws Exception {
		
		super.setUp();
	}
	
	
	public void testCreate() throws DatabaseException, ParseException{
		User user = new User(1L, "Александр", "Соседский", new SimpleDateFormat("d-MM-yyyy").parse("11-09-2000"));
		assertNull(user.getId());
		User userToCheck = dao.create(user);
		assertNotNull(userToCheck);
		assertNotNull(userToCheck.getId());
		assertEquals(user.getFirstName(), userToCheck.getFirstName());
		assertEquals(user.getLastName(), userToCheck.getLastName());
		assertEquals(user.getDateOfBirth(), userToCheck.getDateOfBirth());
	}
	@Override
	protected IDatabaseConnection getConnection() throws Exception {
		ConnectionFactoryImpl connectionFactory = new ConnectionFactoryImpl();
		return new DatabaseConnection(connectionFactory.createConnection());
	}

	@Override
	protected IDataSet getDataSet() throws Exception {
		// TOD
		return null;
	}

}
