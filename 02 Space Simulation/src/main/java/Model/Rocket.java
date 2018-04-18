package Model;

import Interface.Spaceship;

public class Rocket implements Spaceship, Cloneable{

        private int costInMlnDollars;
        private int maxWeightWithCargoInTonnes;
        private int cargoCarried;

        public Rocket(int costInMlnDollars, int maxWeightWithCargoInTonnes) {
                this.cargoCarried = maxWeightWithCargoInTonnes;
                this.costInMlnDollars = costInMlnDollars;
                this.maxWeightWithCargoInTonnes = maxWeightWithCargoInTonnes;
        }

        public int getCostInMlnDollars() {
                return costInMlnDollars;
        }

        public int getMaxWeightWithCargoInTonnes() {
                return maxWeightWithCargoInTonnes;
        }

        public int getCargoCarried() {
                return cargoCarried;
        }

        public void setCargoCarried(int load){
                this.cargoCarried = load;
        }

        @Override
        public boolean launch() {
                return true;
        }

        @Override
        public Rocket clone() throws CloneNotSupportedException {
                return (Rocket)super.clone();
        }

        @Override
        public boolean land() {
                return true;
        }


}
