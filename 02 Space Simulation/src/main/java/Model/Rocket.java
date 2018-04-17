package Model;

import Interface.Spaceship;

public class Rocket implements Spaceship{

        private int costInMlnDollars;
        private int weighInTonnes;
        private int maxWeightWithCargoInTonnes;
        private int cargoCarried;

        public Rocket(int costInMlnDollars, int weighInTonnes, int maxWeightWithCargoInTonnes) {
                this.cargoCarried = 0;
                this.costInMlnDollars = costInMlnDollars;
                this.weighInTonnes = weighInTonnes;
                this.maxWeightWithCargoInTonnes = maxWeightWithCargoInTonnes;
        }

        public int getMaxWeightWithCargoInTonnes() {
                return maxWeightWithCargoInTonnes;
        }

        public int getCargoCarried() {
                return cargoCarried;
        }

        @Override
        public boolean launch() {
                return true;
        }

        @Override
        public boolean land() {
                return true;
        }

        @Override
        public boolean canCarry(Item item) {
                return this.maxWeightWithCargoInTonnes - (this.cargoCarried + item.getWeight()) >= 0;
        }

        @Override
        public void carry(Item item) {
                this.cargoCarried += item.getWeight();
        }

}
