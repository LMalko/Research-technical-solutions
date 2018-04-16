package Interface;

import Model.Item;

public interface Spaceship {

        boolean launch();
        boolean land();
        boolean canCarry(Item item);
        void carry(Item item);
}
