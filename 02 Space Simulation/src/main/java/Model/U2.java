package Model;

public class U2 extends Rocket{

        public U2(int costInMlnDollars, int weighInTonnes, int maxWeightWithCargoInTonnes,
                  int launchExplosionCoefficient, int landingCrashCoefficient) {
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

}
