package week2;

import java.util.ArrayList;
import java.util.HashMap;

import edu.duke.FileResource;

public class WordFrequencies {
	private ArrayList<String> myWords;    // store unique words 
	private ArrayList<Integer> myFreqs;   //  number of times the kth word in myWords occurs in the file
	HashMap<String, Integer> myWordFreqs = new HashMap<String, Integer>();
	
	

	public WordFrequencies() {
		myWords = new ArrayList<String>();
		myFreqs = new ArrayList<Integer>();
	}
	
	/*selects a file and then iterates over every word in the file, 
	 * putting the unique words found into myWords. */
	public void findUnique() {
		myWords.clear();
		myFreqs.clear();
		
		FileResource resource = new FileResource("data/errors.txt");
		
		for(String word: resource.words()) {
			word = word.toLowerCase();
			int index = myWords.indexOf(word);
			if(index == -1){
				myWords.add(word);
				myFreqs.add(1);
			}
			else{
				int value = myFreqs.get(index);
				myFreqs.set(index, value+1);
			}
		}
	}
	
	public void findUniqueMap() {
		myWordFreqs.clear();
		FileResource resource = new FileResource("data/testwordfreqs.txt");
		
		for(String word: resource.words()) {
			word = word.toLowerCase();
			if(!myWordFreqs.containsKey(word)){
				myWordFreqs.put(word, 1);
			}
			else{
				myWordFreqs.put(word, myWordFreqs.get(word)+1);
			}
		}
		
		for(String s: myWordFreqs.keySet()){
     	   System.out.println(myWordFreqs.get(s) +"\t" + s);
        }
	}
	
	public int findIndexOfMax(){
		int MaxIndex = 0;
		for(int i =0; i < myFreqs.size(); i++){
			if(myFreqs.get(i) > myFreqs.get(MaxIndex)){
				MaxIndex = i;
			}
		}
		
		return MaxIndex;
		
	}

	public void findUniqueTester(){
		findUnique();
		System.out.println("Number of unique words: " + myWords.size());
		for(int i =0; i < myWords.size(); i++){
      	  System.out.println(myFreqs.get(i)+ "\t" + myWords.get(i));
        }
		
		int maxIndex = findIndexOfMax();
		System.out.println("The word that occurs most often and its count are: " + myWords.get(maxIndex) + " number " + myFreqs.get(maxIndex));
	}


	public static void main(String[] args) {
          WordFrequencies wf = new WordFrequencies();
//          //wf.findUniqueMap();
//           for(String s: wf.myWordFreqs.keySet()){
//        	   System.out.println(wf.myWordFreqs.get(s) +"\t" + s);
//           }
          wf.findUniqueTester();
          
	}

}
