package Enum;

public enum TimeConvert {

        MILISEC_IN_SEC(1_000),
        SECS_IN_MIN(60),
        MINS_IN_HOUR(60),
        HOURS_IN_DAY(24);


        private int timeConvert;

        TimeConvert(int timeConvert) {
                this.timeConvert = timeConvert;
        }

        public int getConvert() {
                return timeConvert;
        }
}
