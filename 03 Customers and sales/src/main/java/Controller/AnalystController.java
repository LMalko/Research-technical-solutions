package Controller;

import Interface.UserController;

public class AnalystController  implements UserController {

        @Override
        public void startPanel() {
                System.out.println("cashier");
        }

        @Override
        public void handlePanelOptions() {

        }
}
