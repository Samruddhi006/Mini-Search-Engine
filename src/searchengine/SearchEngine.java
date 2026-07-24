package searchengine;

import java.util.*;
import java.io.IOException;
import java.nio.file.*;


class SearchEngine{
    List<Documents> docs;
    InvertedIndex index;
    Trie root;

    public SearchEngine(){
        docs=new ArrayList<>();
        loadDocuments();
        root=new Trie();
        index=new InvertedIndex(docs, root);
    }

    //load documents and store them in list
    void loadDocuments(){
        int id=1;
        for(int i=1;i<=4;i++){
            try{
                String content=Files.readString(Paths.get("Documents/doc"+i+".txt"));
                docs.add(new Documents(id++, "doc"+i, content));
            }catch(IOException e){
                System.out.println("file cannot be read\n"+e.getMessage());
            }
        }
    }

    void search(String s){
        
        if(index.invertedIndex.containsKey(s)){
            
            //display documents (by term freq in sorted order (doc with highest term freq first))
            //this is done in inverted function itself.
            //hence just print whole list
            for(int i: index.invertedIndex.get(s)){
                Documents d=index.docMap.get(i);
                System.out.println("document: "+d.title + "\t Frequency: " +d.freq.get(s) + "\t TermFreq: "+ (double)d.freq.get(s)/d.totalWords);
            }
            
        }
        else{
            System.out.println("No exact match found...");
            List<String> withPre=root.searchWordWithPrefix(s);
            if(withPre.size()>0){
                System.out.println("Did you mean: ");
                System.out.println(withPre);
            }
        }
    }

    void searchWithPre(String s){
        List<String> withPre=root.searchWordWithPrefix(s);
        if(withPre.size()>0){
            System.out.println("words starting with '"+s+"' : ");
            System.out.println(withPre);
        }else{
            System.out.println("No words with given prefix found");
        }
    }

    void searchAllWords(String query){
        String[] words = query.toLowerCase().split("[^a-zA-Z0-9]+");
        List<Integer> first = index.invertedIndex.get(words[0]);

        if(first == null){
            System.out.println("No document contains all these words.");
            return;
        }

        List<Integer> result = new ArrayList<>(first);
        
        for (int i = 1; i < words.length; i++) {
            if(result.isEmpty()){
                break;
            }
            
            List<Integer> curr = index.invertedIndex.get(words[i]);

            if (curr == null) {
                result.clear();
                break;
            }

            Set<Integer> set = new HashSet<>(result);
            List<Integer> intersection = new ArrayList<>();

            for (int id : curr) {
                if (set.contains(id)) {
                    intersection.add(id);
                }
            }

            result = intersection;
        }

        if(result.size()==0){
            System.out.println("No document contains all these words");
        }
        for(int i=0;i<result.size();i++){
            System.out.println(index.docMap.get(result.get(i)).title);
        }

    }
}
