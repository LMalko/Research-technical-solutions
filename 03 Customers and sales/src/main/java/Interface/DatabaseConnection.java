package Interface;

import Controller.DBStatementProcessor;

public interface DatabaseConnection {

        void connectToDatabase();
        void closeDatabase();
        DBStatementProcessor getDBStatementProcessor();
}
