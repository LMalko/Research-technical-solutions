package Model;

public class U2 extends Rocket{

        public U2(int costInMlnDollars, int weighInTonnes, int maxWeightWithCargoInTonnes) {
                super(costInMlnDollars, weighInTonnes, maxWeightWithCargoInTonnes);
        }

        @Override
        public boolean launch() {
                double temp= Math.random();
                return temp > calcChanceOfLaunchExplosion();
        }

        @Override
        public boolean land() {
                double temp= Math.random();
                return temp > calcChanceOfCrashLanding();
        }

        private double calcChanceOfLaunchExplosion(){
                float launchExplosionCoefficient = 0.04f;
                double chanceOfExplosion = launchExplosionCoefficient * (this.getCargoCarried() / this.getMaxWeightWithCargoInTonnes());
                return Math.round(chanceOfExplosion * 100.0) / 100.0;
        }

        private double calcChanceOfCrashLanding(){
                float crashLandingCoefficient = 0.08f;
                double chanceOfCrash = crashLandingCoefficient * (this.getCargoCarried() / this.getMaxWeightWithCargoInTonnes());
                return Math.round(chanceOfCrash * 100.0) / 100.0;
        }
}
