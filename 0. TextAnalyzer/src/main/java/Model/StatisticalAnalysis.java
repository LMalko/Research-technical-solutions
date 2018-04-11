package Model;

import java.util.*;


public class StatisticalAnalysis {

        private Iterator<String> iterator;
        private int allElementsCount;
        private int alphaNumElementsCount;
        private int charNoSpacesCount;
        private int sentencesCount = 0;
        private final int secondsInMinute = 60;
        private final int minutesInHour = 60;
        private final int hoursInDay = 24;
        private String previous = "";
        private String beforePrevious = "";
        private String regexAlphaNumeric = "[A-Za-z0-9]+";
        private List<List> occurMoreThanOne;
        private List<List> wordsMoreThanFour;
        private Set<String> authorsDict = new HashSet<>();
        private LinkedHashMap<String, Integer> elementsDictionary = new LinkedHashMap<>();
        private LinkedHashMap<String, Integer> elements2xDictionary = new LinkedHashMap<>();
        private LinkedHashMap<String, Integer> elements3xDictionary = new LinkedHashMap<>();

        public StatisticalAnalysis(Iterator<String> iterator) {
                this.iterator = iterator;
                runAnalysis();
                setSentencesCount();
                elementsDictionary = sortMapByValues(elementsDictionary);
                elements2xDictionary = sortMapByValues(elements2xDictionary);
                elements3xDictionary = sortMapByValues(elements3xDictionary);
                occurMoreThanOne();
                wordsLenMoreThanFour();
        }

        private void runAnalysis(){
                int flag = 0;
                while(iterator.hasNext()){
                        String temp = iterator.next();
                        analyzeElement(temp, flag);
                        if(flag < 2){
                                flag++;
                        }
                }
        }

        private void analyzeElement(String temp, int flag){
                authorsDict.add(temp.toLowerCase());
                addElementToMap(temp, elementsDictionary);
                allElementsCount++;
                String regexNotSpaces = "[^\\s]+";
                if(temp.matches(regexNotSpaces)) charNoSpacesCount++;
                if(temp.matches(regexAlphaNumeric)) alphaNumElementsCount++;

                if (flag == 0) { previous = temp; }
                else if (flag == 1) {
                        addElementToMap(previous + temp, elements2xDictionary);
                        beforePrevious = previous;
                        previous = temp;
                } else {
                        addElementToMap(String.format("%s %s", previous, temp), elements2xDictionary);
                        addElementToMap(String.format("%s %s %s", beforePrevious, previous, temp), elements3xDictionary);
                        beforePrevious = previous;
                        previous = temp;
                }
        }

        private void addElementToMap(String nextElement, LinkedHashMap<String, Integer> map){
                String tempString;

                if(nextElement.length() == 1){
                        tempString = nextElement.toUpperCase();
                }else{
                        tempString = nextElement.toLowerCase();
                }
                if(map.get(tempString) == null){
                        map.put(tempString, 1);
                }else{
                        int tempInt = map.get(tempString) + 1;
                        map.put(tempString, tempInt);
                }
        }

        private LinkedHashMap<String, Integer> sortMapByValues(Map<String, Integer> passedMap) {
                List<String> mapKeys = new ArrayList<>(passedMap.keySet());
                List<Integer> mapValues = new ArrayList<>(passedMap.values());
                mapValues.sort(Collections.reverseOrder());
                Collections.sort(mapKeys);
                LinkedHashMap<String, Integer> sortedMap = new LinkedHashMap<>();
                for (Integer val : mapValues) {
                        Iterator<String> keyIt = mapKeys.iterator();
                        while (keyIt.hasNext()) {
                                String key = keyIt.next();
                                Integer comp1 = passedMap.get(key);
                                if (comp1.equals(val)) {
                                        keyIt.remove();
                                        sortedMap.put(key, val);
                                        break;
                                }
                        }
                }
                return sortedMap;
        }

        private void occurMoreThanOne(){

                List<List> result = new ArrayList<>();
                for(String key: elementsDictionary.keySet()){
                        if(elementsDictionary.get(key) > 1 && key.matches(regexAlphaNumeric)){
                                List<String> temp = new ArrayList<>();
                                temp.add(key);
                                temp.add(elementsDictionary.get(key).toString());
                                result.add(temp);
                        }
                }
                occurMoreThanOne = result;
        }

        private void wordsLenMoreThanFour(){

                List<List> result = new ArrayList<>();
                for(String key: elementsDictionary.keySet()){
                        if(key.length() > 4 && elementsDictionary.get(key) > 1){
                                List<String> temp = new ArrayList<>();
                                temp.add(key);
                                temp.add(elementsDictionary.get(key).toString());
                                result.add(temp);
                        }
                }
                wordsMoreThanFour = result;
        }

        private void setSentencesCount(){
                String regexSentenceEnd = "[.?!] [^?!.]";
                for(String key: elements2xDictionary.keySet()) {
                        if (key.matches(regexSentenceEnd)) {
                                sentencesCount += elements2xDictionary.get(key);
                        }
                }
        }

        public int getCharNoSpacesCount() {
                return charNoSpacesCount;
        }

        public int getAlphaNumElementsCount() {
                return alphaNumElementsCount;
        }

        public int getAllElementsCount() {
                return allElementsCount;
        }

        public int getSentencesCount() {
                return sentencesCount;
        }

        public int getDictionarySize() {
                return authorsDict.size();
        }

        public List<List> getOccureMoreThanOne() {
                return occurMoreThanOne;
        }

        public List<List> getWordsMoreThanFour() {
                return wordsMoreThanFour;
        }

        public LinkedHashMap<String, Integer> getElements2xDictionary() {
                return elements2xDictionary;
        }

        public LinkedHashMap<String, Integer> getElements3xDictionary() {
                return elements3xDictionary;
        }

        public String getReadingTime(){
                final int wordsPerMinute = 275;
                final int wordsPerHour = 16_500;
                final int wordsPerDay = 396_000;
                int days = this.getAllElementsCount() / wordsPerDay;
                int hours = this.getAllElementsCount() / wordsPerHour;
                if(days > 0){ return String.format("%d day(s) %d hour(s)", days, hours % hoursInDay); }
                int minutes = this.getAllElementsCount() / wordsPerMinute;
                if(hours > 0){ return String.format("%d hour(s) %d minute(s)", hours, minutes % minutesInHour); }
                int seconds = (int)((float)this.getAllElementsCount() % wordsPerMinute / wordsPerMinute * secondsInMinute);
                return String.format("%d minute(s) %d second(s)", minutes, seconds);

        }

        public String getSpeakingTime() {
                final int wordsPerMinute = 180;
                final int wordsPerHour = 10_800;
                final int wordsPerDay = 259_200;
                int days = this.getAllElementsCount() / wordsPerDay;
                int hours = this.getAllElementsCount() / wordsPerHour;
                if (days > 0) { return String.format("%d day(s) %d hour(s)", days, hours % hoursInDay); }
                int minutes = this.getAllElementsCount() / wordsPerMinute;
                if (hours > 0) { return String.format("%d hour(s) %d minute(s)", hours, minutes % minutesInHour); }
                int seconds = (int) ((float) this.getAllElementsCount() % wordsPerMinute / wordsPerMinute * secondsInMinute);
                return String.format("%d minute(s) %d second(s)", minutes, seconds);
        }

        public String getWritingTime(){
                final int wordsPerMinute = 68;
                final int wordsPerHour = 4_080;
                final int wordsPerDay = 97_920;
                int days = this.getAllElementsCount() / wordsPerDay;
                int hours = this.getAllElementsCount() / wordsPerHour;
                if(days > 0){ return String.format("%d day(s) %d hour(s)", days, hours % hoursInDay); }
                int minutes = this.getAllElementsCount() / wordsPerMinute;
                if(hours > 0){ return String.format("%d hour(s) %d minute(s)", hours, minutes % minutesInHour); }
                int seconds = (int)((float)this.getAllElementsCount() % wordsPerMinute / wordsPerMinute * secondsInMinute);
                return String.format("%d minute(s) %d second(s)", minutes, seconds);
        }
}
