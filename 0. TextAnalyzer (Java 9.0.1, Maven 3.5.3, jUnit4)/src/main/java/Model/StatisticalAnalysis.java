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
        private LinkedHashMap<String, Integer> elementsDictionary = new LinkedHashMap<>();
        private Set<String> authorsDict = new HashSet<>();

        public StatisticalAnalysis(Iterator<String> iterator) {
                this.iterator = iterator;
                runAnalysis();
        }

        private void runAnalysis(){
                while(iterator.hasNext()){
                        String temp = iterator.next();
                        authorsDict.add(temp.toLowerCase());
                        addElementToMap(temp);
                        allElementsCount++;
                        if(temp.matches("[^\\s]+")) {
                                charNoSpacesCount++;
                        }
                        if(temp.matches("[A-Za-z0-9]+")) {
                                alphaNumCount++;
                        }
                }
                sortMapByValues(elementsDictionary);
                occurMoreThanOne();
                wordsLenMoreThanFour();
        }

        private void addElementToMap(String nextElement){
                String tempString;

                if(nextElement.length() == 1){
                        tempString = nextElement.toUpperCase();
                }else{
                        tempString = nextElement.toLowerCase();
                }
                if(elementsDictionary.get(tempString) == null){
                        elementsDictionary.put(tempString, 1);
                }else{
                        int tempInt = elementsDictionary.get(tempString) + 1;
                        elementsDictionary.put(tempString, tempInt);
                }
        }

        private void sortMapByValues(Map<String, Integer> passedMap) {
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
                elementsDictionary = sortedMap;
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
}
