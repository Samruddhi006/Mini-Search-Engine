package searchengine;


import java.util.*;

//Main class
public class Main {

    public static void main(String[] args) {

        SearchEngine se=new SearchEngine();  //handles all the operations

        Scanner sc=new Scanner(System.in);

        int inp=-1;

        while(inp!=4){

            System.out.println("\n\n1.Search for a word \n2.Autosuggest word with given prefix");
            System.out.print("3.Search 2 or more words \n4.Exit \nYour choice: ");
            inp=sc.nextInt();
            System.out.println();

            if(inp==1){
                //search a word in documents 
                //e.g. java-> documents (with freq, termfreq)
                //if word not found, give suggesions of words 
                //starting with that prefix if present in trie
                //e.g. ja->[java, javascript]
                System.out.println("enter word: ");
                sc.nextLine();
                String s=sc.nextLine().toLowerCase();

                se.search(s);

            }

            else if(inp==2){
                //give words starting with given prefix 
                //e.g. java->[java, javascript]
                System.out.println("Enter prefix: ");
                sc.nextLine();
                String pre=sc.nextLine().toLowerCase();

                se.searchWithPre(pre);
            }

            else if(inp==3){
                //intersection of docs
                System.out.println("Enter words: ");
                sc.nextLine();
                String words=sc.nextLine();
                se.searchAllWords(words);
            }
            
            else if(inp==4){
                System.out.println("exitting..");
                break;
            }
            
            else{
                System.out.println("Invalid input");
            }
        }

        sc.close();

        return;
    }
}
