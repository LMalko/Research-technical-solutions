package Controller;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class DBStatementProcessor{

        private Connection connection;
        private Statement statement = null;
        private ResultSet result;

        public DBStatementProcessor(Connection connection) {
                this.connection = connection;
        }

        public void executeQueryAgainstDatabase(String query){
                try{
                        statement = connection.createStatement();
                        ResultSet result = statement.executeQuery(query);
                        printQueryResult(result);

                }catch(Exception exception){
                        System.err.println(exception.getClass().getName() + ": " + exception.getMessage() );
                        System.out.println("\n\n\nQuery was NOT performed successfully");

                }
        }

        private void printQueryResult(ResultSet result){
                try {
                        // Group all column names from query result
                        ResultSetMetaData metaData = result.getMetaData();
                        int columnCount = metaData.getColumnCount();
                        List<String> columnNames = new ArrayList<String>();


                        for (int row = 1; row <= columnCount; row++) {
                                String columnName = metaData.getColumnName(row);
                                columnNames.add(columnName);
                        }

                        while (result.next()) {
                                StringBuilder row = new StringBuilder();

                                for (String columnName : columnNames) {
                                        row.append(columnName).append(": ").append(result.getString(columnName));
                                        row.append("  ");
                                }
                                System.out.println(row);
                        }
                }catch(Exception exception){
                        System.err.println(exception.getClass().getName() + ": " + exception.getMessage());
                        System.out.println("\n\n\nQuery was NOT performed successfully");
                }
        }


        public void executeUpdateAgainstDatabase(String update){
                try{
                        statement = connection.createStatement();
                        statement.executeUpdate(update);
                }catch (Exception exception){
                        System.err.println(exception.getClass().getName() + ": " + exception.getMessage() );
                        System.out.println("\n\n\nUpdate was NOT performed successfully");
                }

        }

        public String getStringDataFromQuery(String query, String columnName){
                String receivedData = "";
                try{
                        result = statement.executeQuery(query);
                        while (result.next()) {
                                receivedData = result.getString(columnName);
                        }

                } catch (SQLException exception) {
                        System.err.println(exception.getClass().getName() + ": " + exception.getMessage() );
                        System.out.println("\n\n\nOperation was NOT performed successfully");
                }
                return receivedData;
        }

        public int getIntegerDataFromQuery(String query, String columnName){
                int receivedData = 0;
                try{
                        result = statement.executeQuery(query);
                        while (result.next()) {
                                receivedData = result.getInt(columnName);
                        }

                } catch (SQLException exception) {
                        System.err.println(exception.getClass().getName() + ": " + exception.getMessage() );
                        System.out.println("\n\n\nOperation was NOT performed successfully");
                }
                return receivedData;
        }

        public ArrayList getArrayListFromQuery(String query){

                ArrayList<ArrayList> arrayResult = new ArrayList<ArrayList>();

                try{

                        statement = connection.createStatement();
                        ResultSet result = statement.executeQuery(query);

                        // Group all column names from query result
                        ResultSetMetaData metaData = result.getMetaData();
                        int columnCount = metaData.getColumnCount();
                        List<String> columnNames = new ArrayList<String>();


                        for (int row = 1; row <= columnCount; row++){
                                String columnName = metaData.getColumnName(row);
                                columnNames.add(columnName);
                        }

                        while(result.next()){

                                ArrayList<String> rowResult = new ArrayList<String>();

                                for (int i = 0; i < columnNames.size(); i++){

                                        rowResult.add(result.getString(columnNames.get(i)));
                                }
                                arrayResult.add(rowResult);
                        }
                }catch(Exception exception){

                        System.err.println(exception.getClass().getName() + ": " + exception.getMessage() );
                        System.out.println("\n\n\nOperation was NOT performed successfully");
                }
                return arrayResult;
        }
}