package Interface;

import Controller.DBStatementProcessor;

public interface Connectable {

        void connectToDatabase();
        void closeDatabase();
        DBStatementProcessor getDBStatementProcessor();
}
