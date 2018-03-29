package Model;

import java.util.*;

public class StatisticalAnalysis {

        private Iterator<String> iterator;


        public StatisticalAnalysis(Iterator<String> iterator) {
                this.iterator = iterator;
        }

        public int getCount(){
                int result = 0;
                while(iterator.hasNext()){
                        if(iterator.next() != null) {
                                result++;
                        }
                }
                return result;
        }

        public int getCountOf(String element){
                int result = 0;
                while(iterator.hasNext()){
                        if(iterator.next().equalsIgnoreCase(element)){
                                System.out.println(element);
                                result ++;
                        }
                }
                return result;
        }

        public int dictionarySize(){
                Set<String> result = new HashSet<>();
                while(iterator.hasNext()){
                        result.add(iterator.next().toLowerCase());
                }
                return result.size();
        }

        public Set<String> occurMoreThan(Integer number){
                if(number <= 1){
                        number = 1;
                }
                Map<String, Integer> dictionary = getMapStatistics();
                Set result = new LinkedHashSet();
                for(String key: dictionary.keySet()){
                        if(dictionary.get(key) > number){
                                result.add(String.format("%s - %d times", key, dictionary.get(key)));
                        }
                }
                return result;
        }

        private Map<String, Integer> getMapStatistics(){
                Map<String, Integer> dictionary = new HashMap<>();
                while(iterator.hasNext()){
                        String tempString = iterator.next();
                        if(dictionary.get(tempString) == null){
                                dictionary.put(tempString, 1);
                        }else{
                                int tempInt = dictionary.get(tempString) + 1;
                                dictionary.put(tempString, tempInt);
                        }
                }
                return sortHashMapByValues(dictionary);
        }

        private LinkedHashMap<String, Integer> sortHashMapByValues(Map<String, Integer> passedMap) {
                List<String> mapKeys = new ArrayList<>(passedMap.keySet());
                List<Integer> mapValues = new ArrayList<>(passedMap.values());
                Collections.sort(mapValues);
                Collections.sort(mapKeys);

                LinkedHashMap<String, Integer> sortedMap =
                        new LinkedHashMap<>();

                Iterator<Integer> valueIt = mapValues.iterator();
                while (valueIt.hasNext()) {
                        Integer val = valueIt.next();
                        Iterator<String> keyIt = mapKeys.iterator();

                        while (keyIt.hasNext()) {
                                String key = keyIt.next();
                                Integer comp1 = passedMap.get(key);
                                Integer comp2 = val;

                                if (comp1.equals(comp2)) {
                                        keyIt.remove();
                                        sortedMap.put(key, val);
                                        break;
                                }
                        }
                }
                return sortedMap;
        }
}
