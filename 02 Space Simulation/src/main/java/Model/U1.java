package Model;

public class U1 extends Rocket{

        public U1(int costInMlnDollars, int weighInTonnes, int maxWeightWithCargoInTonnes) {
                super(costInMlnDollars, weighInTonnes, maxWeightWithCargoInTonnes);
        }

        @Override
        public boolean launch() {
                return true;
        }

        @Override
        public boolean land() {
                return true;
        }

        private double calculateChanceOfExplosion(){
                float launchExplosionCoefficient = 0.05f;
                double chanceOfExplosion = launchExplosionCoefficient * (this.getCargoCarried() / this.getMaxWeightWithCargoInTonnes());
                return Math.round(chanceOfExplosion * 100.0) / 100.0;
        }

        private double calculateChanceOfCrashLanding(){
                float crashLandingCoefficient = 0.01f;
                double chanceOfCrash = crashLandingCoefficient * (this.getCargoCarried() / this.getMaxWeightWithCargoInTonnes());
                return Math.round(chanceOfCrash * 100.0) / 100.0;
        }
}
