package ua.nure.kn.susidskyi.db;

import java.sql.SQLException;

public class DatabaseException extends Exception {

	public DatabaseException(SQLException e) {
		super(e);
	}

}
