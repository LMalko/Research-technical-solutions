package Model;

import java.util.*;
import java.util.regex.Pattern;

public class StatisticalAnalysis {

        private Iterator<String> iterator;
        private int allElementsCount;
        private int alphaNumCount;
        private int charNoSpacesCount;
        private int sentencesCount = 0;
        private List<List> occurMoreThanOne;
        private List<List> wordsMoreThanFour;
        private LinkedHashMap<String, Integer> elementsDictionary = new LinkedHashMap<>();
        private LinkedHashMap<String, Integer> elements2xDictionary = new LinkedHashMap<>();
        private LinkedHashMap<String, Integer> elements3xDictionary = new LinkedHashMap<>();
        private Set<String> authorsDict = new HashSet<>();
        private String previous = "";
        private String beforePrevious = "";

        public StatisticalAnalysis(Iterator<String> iterator) {
                this.iterator = iterator;
                runAnalysis();
                occurMoreThanOne();
                wordsLenMoreThanFour();
                setSentencesCount();
                elementsDictionary = sortMapByValues(elementsDictionary);
                elements2xDictionary = sortMapByValues(elements2xDictionary);
                elements3xDictionary = sortMapByValues(elements3xDictionary);
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
}
