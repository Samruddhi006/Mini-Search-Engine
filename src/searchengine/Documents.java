package searchengine;

import java.util.*;

class Documents{
    int docId;
    String title;
    String content;
    int totalWords;
    Map<String, Integer> freq;
    
    static final Set<String> stopWords=new HashSet<>(Arrays.asList(
        "is","the","a","an","are","was","in","where"
        ,"on","of","at","to","and","or","for","with","this", "that", "it",
        "as", "by", "be", "from", "has", "have", "had", "used", "using"
    ));

    public Documents(int id, String title, String content){
        this.docId=id;
        this.title=title;
        this.content=content;
        this.freq=new HashMap<>();
        this.totalWords=this.processDoc();
    }


    //tokenize, remove stop words, get freq of each word and total count of words.
    //to get term freq, we can just get freq of that word/totalCount and this can be done when needed.
    int processDoc(){
        int cnt=0;
        String[] words=content.toLowerCase().split("[^a-zA-Z0-9]+");
        for(String word:words){
            if(word.length()==0){
                continue;
            }
            if(stopWords.contains(word)){
                continue;
            }else{
                freq.put(word,freq.getOrDefault(word, 0)+1);
                cnt++;
            }
        }
        //free up memory consumed by content as the file can be too large.
        //also freq map is created just at starting.
        //later it is used for each operation even for inverted index creation.
        content=null;
        return cnt;
    }
}

