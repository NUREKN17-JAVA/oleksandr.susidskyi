package ua.nure.kn.susidskyi.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactoryImpl implements ConnectionFactory {
	
	private String driver = "org.hsqldb.jbdcDriver";
	private String url = "jdbc:hsqldb:file:db/usermanagement";
	private String user = "sa";
	private String password = "";
	@Override
	public Connection createConnection() throws DatabaseException{
		try {
			Class.forName("org.hsql.jdbcDriver");
		}
		catch(ClassNotFoundException e){
			throw new RuntimeException(e);
		}
		try {
			return DriverManager.getConnection(url, user, password);
		}
		catch (SQLException e) {
			throw new DatabaseException(e);
		}
		
	}

}
