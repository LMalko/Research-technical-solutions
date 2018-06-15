package Enum;

public enum WordsToTime {

        READ_PER_MIN(275),
        READ_PER_HOUR(16_500),
        READ_PER_DAY(396_000),

        SPEAK_PER_MIN(180),
        SPEAK_PER_HOUR(10_800),
        SPEAK_PER_DAY(259_200),

        WRITE_PER_MIN(68),
        WRITE_PER_HOUR(4_080),
        WRITE_PER_DAY(97_920);

        private int convert;

        WordsToTime(int convert) {
                this.convert = convert;
        }

        public int getConvert() {
                return convert;
        }
}
