package searchengine;

import java.util.*;


class InvertedIndex{
    Map<String, List<Integer>> invertedIndex;
    Map<Integer, Documents> docMap;

    public InvertedIndex(List<Documents> li, Trie root){
        docMap=new HashMap<>();
        buildDocMap(li);
        invertedIndex=new HashMap<>();
        addWords(li);
        sort();
        buildPrefixTree(root);
    }



    void buildDocMap(List<Documents> li){
        for(Documents d: li){
            docMap.put(d.docId, d);
        }
    }


    void addWords(List<Documents> li){
        for(int i=0;i<li.size();i++){
            for(String key:li.get(i).freq.keySet()){
                List<Integer> curr=invertedIndex.get(key);
                if(curr==null){
                    curr=new ArrayList<>();
                }
                curr.add(li.get(i).docId);
                invertedIndex.put(key, curr);
            }
        }
    }

    void sort() {
        for (String word : invertedIndex.keySet()) {

            List<Integer> curr = invertedIndex.get(word);

            curr.sort((a, b) -> {

                Documents d1 = docMap.get(a);
                Documents d2 = docMap.get(b);

                double tf1 = (double)d1.freq.get(word) / d1.totalWords;
                double tf2 = (double)d2.freq.get(word) / d2.totalWords;

                return Double.compare(tf2, tf1);
            });
        }
    }

    
    void buildPrefixTree(Trie root){
        for(String key : invertedIndex.keySet()){
            root.insertWord(key);
        }
    }

}
