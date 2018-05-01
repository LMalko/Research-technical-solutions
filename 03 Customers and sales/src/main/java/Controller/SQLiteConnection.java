package Controller;

import Interface.DatabaseConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class SQLiteConnection implements DatabaseConnection{

        private String filename;
        private Connection connection = null;
        private Statement statement = null;
        private ResultSet result;

        SQLiteConnection(String filename) {
                this.filename = filename;
                connectToDatabase();
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