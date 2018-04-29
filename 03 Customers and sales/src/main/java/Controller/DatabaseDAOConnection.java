package Controller;

import DAO.DBStatementProcessor;
import Interface.Connectable;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DatabaseDAOConnection implements Connectable{

        private static String filename;
        private Connection connection = null;
        private Statement statement = null;
        private ResultSet result;

        private static DatabaseDAOConnection ourInstance;


        private DatabaseDAOConnection(String filename) {
                ourInstance = new DatabaseDAOConnection(filename);
                connectToDatabase();
        }

        public static DatabaseDAOConnection getInstance() {
                return ourInstance;
        }

        @Override
        public DBStatementProcessor getDBStatementProcessor(){
                return new DBStatementProcessor(connection);
        }

        @Override
        public void connectToDatabase() {
                try {
                        // Register JDBC driver.
                        Class.forName("org.sqlite.JDBC");
                        // Open a connection to database.
                        connection = DriverManager.getConnection(filename);
                }catch ( Exception exception ) {
                        System.err.println( exception.getClass().getName() + ": " + exception.getMessage() );
                }
        }

        @Override
        public void closeDatabase(){
                try{
                        if (result != null){
                                result.close();
                        }
                        if (statement != null){
                                statement.close();
                        }
                        if (connection != null){
                                connection.close();
                        }
                }catch(Exception exception){
                        System.err.println(exception.getClass().getName() + ": " + exception.getMessage() );
                        System.exit(0);
                }
        }
}