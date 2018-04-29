package Interface;

import DAO.DBStatementProcessor;

public interface Connectable {

        void connectToDatabase();
        void closeDatabase();
        DBStatementProcessor getDBStatementProcessor();
}
