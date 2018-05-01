package Controller;

import DAO.UsersDAO;
import Model.Admin;
import Model.Analyst;
import Model.Cashier;
import Model.User;
import View.LoginView;

import java.util.ArrayList;

public class LoginController{

        private SQLiteConnection databaseDAOConnection = new SQLiteConnection("jdbc:sqlite:resources/shop.db");

        private LoginView view = new LoginView();
        private UsersDAO dao = new UsersDAO(databaseDAOConnection);
        private ArrayList<User> usersCollection = dao.getUsersCollection();

        public void login(){

                boolean isLoginSession = true;

                while (isLoginSession){
                        view.clearScreen();
                        String userLogin = view.getLogin();
                        String userPassword = view.getPassword();
                        String userStatus = getUserStatus(userLogin, userPassword);
                        if (checkIfUserExists(userLogin) && checkUserPassword(userLogin, userPassword)){
                                runProperUserPanel(userLogin, userPassword, userStatus);
                        }
                }
        }

        private boolean checkIfUserExists(String login){
                try{
                        for (User user : usersCollection){
                                if (login.equals(user.getLogin())){
                                        return true;
                                }
                        }
                        view.print("Given user does not exists!");
                        Thread.sleep(1000);
                } catch (InterruptedException e){
                        Thread.currentThread().interrupt();
                }
                return false;
        }

        private boolean checkUserPassword(String login, String password){
                try{
                        for (User user : usersCollection){
                                if (login.equals(user.getLogin()) && password.equals(user.getPassword())){
                                        return true;
                                }
                        }
                        view.print("Wrong password!");
                        Thread.sleep(1000);
                } catch (InterruptedException e){
                        Thread.currentThread().interrupt();
                }
                return false;
        }

        private String getUserStatus(String login, String password){
                for (User user : usersCollection){
                        if (login.equals(user.getLogin()) && password.equals(user.getPassword())){
                                return user.getStatus();
                        }
                }
                return null;
        }

        private void runProperUserPanel(String userLogin, String userPassword, String userStatus){
                if(userStatus.equals("admin")){
                        User user = new Admin(userLogin, userPassword, userStatus);
                        AdminController controller = new AdminController();
                        controller.startPanel();
                }
                else if(userStatus.equals("cashier")){
                        User user = new Cashier(userLogin, userPassword, userStatus);
                        CashierController controller = new CashierController();
                        controller.startPanel();
                }
                else if(userStatus.equals("analyst")){
                        User user = new Analyst(userLogin, userPassword, userStatus);
                        AnalystController controller = new AnalystController();
                        controller.startPanel();
                }
        }
}