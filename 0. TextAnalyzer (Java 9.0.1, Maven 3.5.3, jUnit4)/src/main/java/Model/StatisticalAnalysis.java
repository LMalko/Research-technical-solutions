package Model;

import java.util.*;

public class StatisticalAnalysis {

        private Iterator<String> iterator;

        public StatisticalAnalysis(Iterator<String> iterator) {
                this.iterator = iterator;
        }

        public int getCountOf(String element){
                int result = 0;
                while(iterator.hasNext()){
                        if(iterator.next() == element){
                                result ++;
                        }
                }
                return result;
        }

        public int dictionarySize(){
                Set<String> result = new HashSet<>();
                while(iterator.hasNext()){
                        result.add(iterator.next());
                }
                return result.size();
        }

        public int size(){
                int result = 0;
                while(iterator.hasNext()){
                        result ++;
                }
                return result;
        }

        public Set<String> occurMoreThan(Integer number){
                Map<String, Integer> dictionary = getMapStatistics();
                HashSet result = new HashSet<>();
                for(String key: dictionary.keySet()){
                        if(dictionary.get(key) > number){
                                result.add(dictionary.get(key));
                        }
                }
                return result;
        }

        public Map<String, Integer> getMapStatistics(){
                Map<String, Integer> dictionary = new HashMap<>();
                while(iterator.hasNext()){
                        String tempString = iterator.next();
                        if(dictionary.get(tempString) == null){
                                dictionary.put(tempString, 1);
                        }else{
                                int tempInt = dictionary.get(tempString);
                                dictionary.put(tempString, tempInt);
                        }
                }
                return dictionary;
        }
}
