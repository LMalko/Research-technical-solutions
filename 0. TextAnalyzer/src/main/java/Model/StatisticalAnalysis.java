package Model;

import java.util.*;

public class StatisticalAnalysis {

        private Iterator<String> iterator;
        private int allElementsCount;
        private int alphaNumCount;
        private int charNoSpacesCount;
        private int sentencesCount = 0;
        private List<List> occurMoreThanOne;
        private List<List> wordsMoreThanFour;
        private LinkedHashMap<String, Integer> elementsDictionary;
        private LinkedHashMap<String, Integer> elements2xDictionary;
        private LinkedHashMap<String, Integer> elements3xDictionary;
        private Set<String> authorsDict = new HashSet<>();
        private String previous = "";
        private String beforePrevious = "";

        public StatisticalAnalysis(Iterator<String> iterator) {
                this.iterator = iterator;
                elementsDictionary = new LinkedHashMap<>();
                elements2xDictionary = new LinkedHashMap<>();
                elements3xDictionary = new LinkedHashMap<>();
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
                        authorsDict.add(temp.toLowerCase());
                        addElementToMap(temp, elementsDictionary);
                        allElementsCount++;
                        if(temp.matches("[^\\s]+")) charNoSpacesCount++;
                        if(temp.matches("[A-Za-z0-9]+")) alphaNumCount++;

                        if(flag == 0){
                                flag++;
                                previous = temp;
                        }else if(flag == 1){
                                flag++;
                                addElementToMap(previous + temp, elements2xDictionary);
                                beforePrevious = previous;
                                previous = temp;
                        }else{
                                addElementToMap(String.format("%s %s", previous, temp), elements2xDictionary);
                                addElementToMap(String.format("%s %s %s", beforePrevious, previous, temp), elements3xDictionary);
                                beforePrevious = previous;
                                previous = temp;
                        }
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
                        if(elementsDictionary.get(key) > 1 && key.matches("[A-Za-z0-9]+")){
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
                for(String key: elements2xDictionary.keySet()) {
                        if (key.matches("[.?!] [^?!.]")) {
                                sentencesCount += elements2xDictionary.get(key);
                        }
                }
        }

        public int getCharNoSpacesCount() {
                return charNoSpacesCount;
        }

        public int getAlphaNumCount() {
                return alphaNumCount;
        }

        public int getAllCount() {
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
                int days = this.getAllCount() / 396_000;
                int hours = this.getAllCount() / 16_500;
                if(days > 0){ return String.format("%d day(s) %d hour(s)", days, hours % 24); }
                int minutes = this.getAllCount() / 275;
                if(hours > 0){ return String.format("%d hour(s) %d minute(s)", hours, minutes % 60); }
                int seconds = (int)((float)this.getAllCount() % 275 / 275 * 60);
                return String.format("%d minute(s) %d second(s)", minutes, seconds);

        }

        public String getSpeakingTime() {
                int days = this.getAllCount() / 259_200;
                int hours = this.getAllCount() / 10_800;
                if (days > 0) { return String.format("%d day(s) %d hour(s)", days, hours % 24); }
                int minutes = this.getAllCount() / 180;
                if (hours > 0) { return String.format("%d hour(s) %d minute(s)", hours, minutes % 60); }
                int seconds = (int) ((float) this.getAllCount() % 180 / 180 * 60);
                return String.format("%d minute(s) %d second(s)", minutes, seconds);
        }

        public String getWritingTime(){
                int days = this.getAllCount() / 97_920;
                int hours = this.getAllCount() / 4_080;
                if(days > 0){ return String.format("%d day(s) %d hour(s)", days, hours % 24); }
                int minutes = this.getAllCount() / 68;
                if(hours > 0){ return String.format("%d hour(s) %d minute(s)", hours, minutes % 60); }
                int seconds = (int)((float)this.getAllCount() % 68 / 68 * 60);
                return String.format("%d minute(s) %d second(s)", minutes, seconds);
        }
}
