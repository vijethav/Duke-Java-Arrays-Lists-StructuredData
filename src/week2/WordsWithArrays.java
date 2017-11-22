package week2;

import edu.duke.FileResource;
import edu.duke.StorageResource;

public class WordsWithArrays {
	
	StorageResource myWords;
	
	public WordsWithArrays() {
		myWords = new StorageResource();
	}
		
    public void readWords(FileResource resource) {
    	//myWords.clear();
    	//FileResource resource = new FileResource("data/small.txt");
    	for(String word: resource.words()){
    		myWords.add(word.toLowerCase());   // All words are added 
    		
    	}
    }
    
    public boolean contains(String[] list, String word, int number){
    	for(int i =0; i < number; i++){
    		if(list[i].equals(word)){
    			return true;
    		}
    	}
    	return false;
    }
    
    public int numberOfUniqueWords() {
    	int numStored = 0;
    	String[] words = new String[myWords.size()];         //array size is length of myWords.
    	for(String word : myWords.data()){                      // my words data " "the " "and" "add" "all" "word" "
    		if(!contains(words, word, numStored)){    //  array of words[no words] , the,  0
    			words[numStored] = word;             //words[0] = the
    			numStored++;                         // 1
    		}
    	}
//    	for(int i=0; i<words.length; i++){
//			System.out.println(words[i] +"\t" + i);
//		}
    	
    	return numStored;
    }
	public static void main(String[] args) {
		WordsWithArrays wa = new WordsWithArrays();
		String[] plays = {"caesar.txt", "hamlet.txt", "likeit.txt", 
		           "macbeth.txt", "romeo.txt"};
		for(int i =0; i < plays.length; i++){
			FileResource rs = new FileResource("week2data/" + plays[i]);
			wa.readWords(rs);
			//wf.addWordsFromFile(rs, plays[i]);
			System.out.println("done with " + plays[i]);
	      }
		System.out.println("Number of words Read: "+ wa.myWords.size());
		int unique = wa.numberOfUniqueWords();
		System.out.println("NUmber of Unique Words"+ unique);
		
		

	}

}
