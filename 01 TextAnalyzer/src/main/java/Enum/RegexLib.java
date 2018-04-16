package Enum;

public enum RegexLib {

        SPACES("\\s+"),
        NEW_LINE("\n"),
        NO_SPACES("[^\\s]+"),
        END_OF_SENTENCE("[.?!] [^?!.]"),
        ALPHA_NUMERIC("[A-Za-z0-9]+"),
        NOT_ALPHA_NUMERIC_OR_SPACE("[^A-Za-z0-9\\s]");

        private String regex;

        RegexLib(String regex) {
                this.regex = regex;
        }

        public String getRegex() {
                return regex;
        }
}
