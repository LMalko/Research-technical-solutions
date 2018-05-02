package Controller;

import Interface.UserController;

public class CashierController  implements UserController {

        @Override
        public void startPanel() {
                System.out.println("cashier");
        }

        @Override
        public void handlePanelOptions() {

        }
}
