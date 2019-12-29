package ua.nure.kn.susidskyi.db;
import java.sql.Connection;

public interface ConnectionFactory {
    Connection createConnection() throws DataBaseException;
}
