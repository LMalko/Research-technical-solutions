package Model;

public class U4Prototype extends Rocket{

        public U4Prototype() {
                super(150, 25, 40);
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

        public double calcChanceOfLaunchExplosion(){
                float launchExplosionCoefficient = 0.25f;
                double chanceOfExplosion = launchExplosionCoefficient * (this.getCargoCarried() / this.getMaxWeightWithCargoInTonnes());
                return Math.round(chanceOfExplosion * 100.0) / 100.0;
        }

        private double calcChanceOfCrashLanding(){
                float crashLandingCoefficient = 0.15f;
                double chanceOfCrash = crashLandingCoefficient * (this.getCargoCarried() / this.getMaxWeightWithCargoInTonnes());
                return Math.round(chanceOfCrash * 100.0) / 100.0;
        }
}