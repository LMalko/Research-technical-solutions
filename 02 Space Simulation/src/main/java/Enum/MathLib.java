package Enum;

public enum MathLib {

        MILION(1_000_000);

        private int mathExp;

        MathLib(int mathExp) {
                this.mathExp = mathExp;
        }

        public int getExp() {
                return mathExp;
        }
}
