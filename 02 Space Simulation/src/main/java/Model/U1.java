package Model;

public class U1 extends Rocket{

        public U1(int costInMlnDollars, int weighInTonnes, int maxWeightWithCargoInTonnes) {
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

        public double calcChanceOfLaunchExplosion(){
                float launchExplosionCoefficient = 0.05f;
                double chanceOfExplosion = launchExplosionCoefficient * ((float)20 / 21);
                return Math.round(chanceOfExplosion * 100.0) / 100.0;
        }

        private double calcChanceOfCrashLanding(){
                float crashLandingCoefficient = 0.01f;
                double chanceOfCrash = crashLandingCoefficient * ((float)20 / 21);
                return Math.round(chanceOfCrash * 100.0) / 100.0;
        }
}
