package week2;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import edu.duke.DirectoryResource;
import edu.duke.FileResource;

public class WordsInFiles {
	
	private HashMap<String, ArrayList<String>> myMap;  // maps a word to an ArrayList of filenames.

	public WordsInFiles(){
		myMap = new HashMap<String, ArrayList<String>>();
	}
   /*This method should add all the words from f into the map.
	If a word is not in the map, then you must create a new ArrayList of 
	type String with this word, and have the word map to this ArrayList. 
	If a word is already in the map, then add the current filename to its ArrayList, */ 
	public void addWordsFromFile(FileResource fr, String fName){
		ArrayList<String> fileName = new ArrayList<String>();
	
		for(String word: fr.words()){
			//word = word.toLowerCase();
            if(!myMap.containsKey(word)){
            	myMap.put(word, new ArrayList<String>());
            	myMap.get(word).add(fName);
            }
            else{
            	if(!myMap.get(word).contains(fName)){
            		myMap.get(word).add(fName);
            	}
            }
		}
	}
	private void buildWordFileMap() {
        myMap.clear();
        DirectoryResource dr = new DirectoryResource();
        for (File f: dr.selectedFiles()) {
        	//addWordsFromFile(f, f.getName());
        }
        
    }
 /* returns the maximum number of files any word appears in, */
	public int maxNumber(){
		int maxFiles =0;
		for(String word : myMap.keySet()){
			int current = myMap.get(word).size();
			if( maxFiles < current){
				maxFiles = current;
			}
		}
		return maxFiles;
	}
	/*This method returns an ArrayList of words 
	that appear in exactly number files*/
	public ArrayList<String> wordsInNumFiles(int number){
		ArrayList<String> wordsInNumberFiles = new ArrayList<String>();
		for(String word : myMap.keySet()){
			int current = myMap.get(word).size();
			if(current == number){
				wordsInNumberFiles.add(word);
			}
		}
		return wordsInNumberFiles;		
	}
/*	prints the names of the files this word appears in, one filename per line. */	
	public ArrayList<String> printFilesIn(String word){
		ArrayList<String> filesName = new ArrayList<String>();
		if(myMap.containsKey(word)){
			filesName.addAll(myMap.get(word));		
		}
		return filesName;
		
	}
	
	
	public static void main(String[] args) {
		WordsInFiles wf = new WordsInFiles();
		String[] plays = {"caesar.txt", "confucius.txt", "errors.txt", "hamlet.txt", "likeit.txt", 
		          "macbeth.txt", "romeo.txt"};
         // String[] plays = {"brief1.txt", "brief2.txt", "brief3.txt", "brief4.txt"};
          
		for(int i =0; i < plays.length; i++){
			FileResource rs = new FileResource("week2data/" + plays[i]);
			wf.addWordsFromFile(rs, plays[i]);
			System.out.println("done with " + plays[i]);			
	    }
		System.out.println("Total Words  " + wf.myMap.size());

		for(String word: wf.myMap.keySet()){
			System.out.println("Words : " + word + " : " + wf.myMap.get(word) );
		}
		
//		int j = 1;
//		for(String word: wf.myMap.keySet()){
//			
//			ArrayList<String> filenames = wf.myMap.get(word);
//			//if(filenames.size() == 4){
//				
//			    System.out.println( j + " Words " + word + " \t Number of Files " + filenames.size());
//			    j++;
//			//}
//			for(int i=0; i < filenames.size();i++){
//				System.out.println("Words " + word + " \t filesnames " + filenames.get(i));
//			}
//		}
		int numberMax = 4;
		ArrayList<String> wordInNumberFiles = wf.wordsInNumFiles(numberMax);
		System.out.println(wordInNumberFiles.size() + " words in Number files " + numberMax);
		System.out.println(numberMax + " wordInNumberFiles a word apperened in " + wordInNumberFiles);
		
		String[] wordinFile = {"tree"};
		for(int i=0; i< wordinFile.length;i++){
		ArrayList<String> fileIn = wf.printFilesIn(wordinFile[i]);
		System.out.println( wordinFile[i] + " appears in the files: " + fileIn);
		}
		
	}

}
