package DAO;

import Controller.DBStatementProcessor;
import Controller.DatabaseDAOConnection;
import Model.Admin;
import Model.Analyst;
import Model.Cashier;
import Model.User;

import java.util.ArrayList;

public class UsersDAO {

        private static ArrayList<User> usersCollection = new ArrayList<User>();
        private DBStatementProcessor dbStatementProcessor;

        private static final int idIndex = 0;
        private static final int nameIndex = 1;
        private static final int surnameIndex = 2;
        private static final int passwordIndex = 4;
        private static final int statusIndex = 5;

        public UsersDAO(DatabaseDAOConnection databaseDAOConnection) {
                this.dbStatementProcessor = databaseDAOConnection.getDBStatementProcessor();
        }

        private void importUsersData() {
                usersCollection.clear();
                ArrayList<ArrayList<String>> users = dbStatementProcessor.getArrayListFromQuery("SELECT * FROM users");
                for(int i =0; i < users.size(); i++) {
                        ArrayList<String> personData = users.get(i);
                        User person = createUserObject(personData);
                        usersCollection.add(person);
                }
        }

        public ArrayList<User> getUsersCollection() {
                importUsersData();
                return usersCollection;
        }

        private User createUserObject(ArrayList<String> personData) {
                int id = Integer.parseInt(personData.get(idIndex));
                String name = personData.get(nameIndex);
                String surname = personData.get(surnameIndex);
                String password = personData.get(passwordIndex);
                String status = personData.get(statusIndex);

                User person = null;
                if(status.equals("admin")){
                        person = new Admin(id, name, surname, password);
                }
                else if(status.equals("analyst")){
                        person = new Analyst(id, name, surname, password);
                }
                else if(status.equals("cashier")){
                        person = new Cashier(id, name, surname, password);
                }
                return person;
        }

        public void addUserToDatabase(User user){
                String name = user.getName();
                String surname = user.getSurname();
                String login = user.getLogin();
                String password = user.getPassword();
                String status = user.getStatus();

                String query = "INSERT INTO users (name, surname, login, password," +
                        "status, group_id, experience) VALUES ( " + "'" +
                        name + "', '" +
                        surname + "', '" +
                        login + "', '" +
                        password + "', '" +
                        status +
                        "');";

                usersCollection.add(user);
                dbStatementProcessor.executeUpdateAgainstDatabase(query);

        }

        public void updateUserDataInDatabase(User user) {
                String name = user.getName();
                String surname = user.getSurname();
                String login = user.getLogin();
                String password = user.getPassword();
                String status = user.getStatus();
                String query = "UPDATE users SET name = '" + name + "' ," +
                        "surname = '" + surname + "' ," +
                        "login = '" + login + "' ," +
                        "password = '" + password + "' ," +
                        "status = '" + status + "' ," +
                        "WHERE id = " + user.getId() + ";";
                dbStatementProcessor.executeUpdateAgainstDatabase(query);
        }

        public ArrayList<User> getAllUsersByStatus(String userStatus){
                importUsersData();
                ArrayList<User> usersWithGivenStatus = new ArrayList<User>();
                for (User user : usersCollection){
                        if (user.getStatus().equals(userStatus)){
                                usersWithGivenStatus.add(user);
                        }
                }
                return usersWithGivenStatus;
        }
}
