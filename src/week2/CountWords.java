package week2;

import java.util.Random;

import edu.duke.FileResource;
import edu.duke.StorageResource;
import edu.duke.URLResource;

public class CountWords {

	StorageResource myWords;
	
	public CountWords() {
		myWords = new StorageResource();
	}
	
	public int getCount(){
		return myWords.size();
	}
	
	public String getRandomWord(){
		Random rand = new Random();
		int choice = rand.nextInt(myWords.size());
		for(String s : myWords.data()){
			if(choice == 0) {
				return s;
			}
			choice = choice - 1;
		}
		return "";
	}
	public void readWords(String source){
		myWords.clear();
		if (source.startsWith("http")){
			URLResource resource = new URLResource(source);
			for(String word : resource.words()){
				myWords.add(word.toLowerCase());
			}
		}
		else{
			FileResource resource = new FileResource(source);
			for(String word : resource.words()){
				word = word.toLowerCase();
    			if(! myWords.contains(word)){
    			    myWords.add(word);
    			}
    		
			}
		}
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
