package Enum;

public enum RegexLib {

        DOT("\\."),
        EQUALS_SIGN("="),
        Y_SIGN("y");

        private String regex;

        RegexLib(String regex) {
                this.regex = regex;
        }

        public String getRegex() {
                return regex;
        }
}