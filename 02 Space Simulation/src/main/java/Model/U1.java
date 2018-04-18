package Model;

public class U1 extends Rocket{

        public U1() {
                super(100, 18);
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
                float launchExplosionCoefficient = 0.05f;
                double chanceOfExplosion = launchExplosionCoefficient * (this.getCargoCarried() / this.getMaxWeightWithCargoInTonnes());
                return Math.round(chanceOfExplosion * 100.0) / 100.0;
        }

        private double calcChanceOfCrashLanding(){
                float crashLandingCoefficient = 0.01f;
                double chanceOfCrash = crashLandingCoefficient * (this.getCargoCarried() / this.getMaxWeightWithCargoInTonnes());
                return Math.round(chanceOfCrash * 100.0) / 100.0;
        }
}
