package Model;

import Interface.IterableElement;

import java.util.*;

public class StatisticalAnalysis {

        private IterableElement<String> iterator;
        private int allCount;
        private int alphaNumCount;
        private int charNoSpacesCount;
        private int dictionarySize;
        private int sentencesCount = 0;
        private List<List> occurMoreThanOne;
        private List<List> wordsMoreThanFour;

        public StatisticalAnalysis(IterableElement<String> iterator) {
                this.iterator = iterator;
                runAnalysis();
        }

        private void runAnalysis(){
                runCount();
                iterator.restartIterator();
                dictionarySize();
                iterator.restartIterator();
                occurMoreThanOne();
                iterator.restartIterator();
                wordsLenMoreThanFour();
        }

        private void runCount(){
                while(iterator.hasNext()){
                        String temp = iterator.next();
                        allCount++;
                        if(temp.matches("[^\\s]+")) {
                                charNoSpacesCount++;
                        }
                        if(temp.matches("[A-Za-z0-9]+")) {
                                alphaNumCount++;
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
                return allCount;
        }

        public int getSentencesCount() {
                return sentencesCount;
        }

        private void dictionarySize(){
                Set<String> result = new HashSet<>();
                        while(iterator.hasNext()){
                                result.add(iterator.next().toLowerCase());
                        }
                        
                dictionarySize = result.size();
        }

        public int getDictionarySize() {
                return dictionarySize;
        }

        private void occurMoreThanOne(){
                Map<String, Integer> dictionary = getMapStatistics();
                List<List> result = new ArrayList<>();
                for(String key: dictionary.keySet()){
                        if(dictionary.get(key) > 1 && key.matches("[A-Za-z0-9]+")){
                                List<String> temp = new ArrayList<>();
                                temp.add(key);
                                temp.add(dictionary.get(key).toString());
                                result.add(temp);
                        }
                }
                occurMoreThanOne = result;
        }

        private void wordsLenMoreThanFour(){
                Map<String, Integer> dictionary = getMapStatistics();
                List<List> result = new ArrayList<>();
                for(String key: dictionary.keySet()){
                        if(key.length() > 4 && dictionary.get(key) > 1){
                                List<String> temp = new ArrayList<>();
                                temp.add(key);
                                temp.add(dictionary.get(key).toString());
                                result.add(temp);
                        }
                }
                wordsMoreThanFour = result;
        }

        public List<List> getOccureMoreThanOne() {
                return occurMoreThanOne;
        }

        public List<List> getWordsMoreThanFour() {
                return wordsMoreThanFour;
        }

        private Map<String, Integer> getMapStatistics(){
                String tempString;
                Map<String, Integer> dictionary = new HashMap<>();
                while(iterator.hasNext()){
                        String temp = iterator.next();
                        if(temp.length() == 1){
                                tempString = temp.toUpperCase();
                        }else{
                                tempString = temp.toLowerCase();
                        }
                        if(dictionary.get(tempString) == null){
                                dictionary.put(tempString, 1);
                        }else{
                                int tempInt = dictionary.get(tempString) + 1;
                                dictionary.put(tempString, tempInt);
                        }
                }
                return sortMapByValues(dictionary);
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
}
