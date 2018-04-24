package Controller;

import DAO.GuestsDAO;
import Model.Admin;
import Model.User;
import View.LoginView;

import java.util.ArrayList;

public class LoginController{

        private LoginView view = new LoginView();
        private GuestsDAO dao = new GuestsDAO();
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
                        view.displayText("Given user does not exists!");
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
                        view.displayText("Wrong password!");
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

        private User getUserAccount(String userLogin, String userPassword){
                for (User user : usersCollection) {
                        if(userLogin.equals(user.getLogin()) && userPassword.equals(user.getPassword())){
                                return user;
                        }
                }
                return null;
        }

        private void runProperUserPanel(String userLogin, String userPassword, String userStatus){
                if(userStatus.equals("admin")){
                        User user = new Admin(userLogin, userPassword, userStatus);
                        AdminController controller = new AdminController();
                        controller.startAdminPanel();
                }
                else if(userStatus.equals("guest")){

                }
        }
}