package searchengine;


import java.util.*;

class Trie{

    Trie[] children;
    boolean eow;

    public Trie(){
        children=new Trie[26];
        eow=false;
    }

    void insertWord(String s){
        Trie curr=this;
        for(int i=0;i<s.length();i++){
            //if it is numeric value->ignore
            if(s.charAt(i)-'a'>25 || s.charAt(i)-'a'<0){
                continue;
            }
            if(curr.children[s.charAt(i)-'a']==null){
                curr.children[s.charAt(i)-'a']=new Trie();
            }
            curr=curr.children[s.charAt(i)-'a'];
        }
        curr.eow=true;
    }

    List<String> searchWordWithPrefix(String word){
        List<String> wrds=new ArrayList<>();

        Trie curr=this;
        for(int i=0;i<word.length();i++){
            if(curr.children[word.charAt(i)-'a']==null){
                return wrds;
            }
            curr=curr.children[word.charAt(i)-'a'];
        }

        StringBuilder sb=new StringBuilder(word);

        dfs(curr, wrds, sb);

        return wrds;
    }

    void dfs(Trie root, List<String> li, StringBuilder sb){
        if(root==null){
            return;
        }
        if(root.eow){
            li.add(sb.toString());
        }
        for(int i=0;i<26;i++){
            if(root.children[i]!=null){
                sb.append((char)(i+'a'));
                dfs(root.children[i], li, sb);
                sb.deleteCharAt(sb.length()-1);
            }
        }
    }

}

