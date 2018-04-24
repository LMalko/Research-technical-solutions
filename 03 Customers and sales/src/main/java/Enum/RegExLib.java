package Enum;

public enum RegExLib {

        NEW_LINE("\n");

        private String regex;

        RegExLib(String regex) {
                this.regex = regex;
        }

        public String getRegex() {
                return regex;
        }
}
