package Model;

import Interface.Spaceship;

public class Rocket implements Spaceship, Cloneable{

        private int costInMlnDollars;
        private int weighInTonnes;
        private int maxWeightWithCargoInTonnes;
        private int cargoCarried;

        public Rocket(int costInMlnDollars, int weighInTonnes, int maxWeightWithCargoInTonnes) {
                this.cargoCarried = maxWeightWithCargoInTonnes;
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

        @Override
        public boolean canCarry(Item item) {
                return this.maxWeightWithCargoInTonnes - (this.cargoCarried + item.getWeight()) >= 0;
        }

        @Override
        public void carry(Item item) {
                this.cargoCarried += item.getWeight();
        }

}
