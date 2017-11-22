package week2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import edu.duke.FileResource;
import edu.duke.URLResource;

public class GladLibMap {

	private HashMap<String, ArrayList<String>> myMap ;
	private ArrayList<String> words;
	private Random myRandom;
	private ArrayList<String> labelName;
	
	private String dataSourceUrl = "http://dukelearntoprogram.com/course3/data";
	private String dataSourceDirectory = "dataStory";
	
	public GladLibMap(){
		myMap = new HashMap<String, ArrayList<String>>();
		initializeFromSource(dataSourceDirectory);
		words = new ArrayList<String>();
		labelName = new ArrayList<String>();
		myRandom = new Random();
	}
	
	public GladLibMap(String source){
		initializeFromSource(source);
		myRandom = new Random();
	}
	public void initializeFromSource(String source) {
		
		String[] labels = {"adjective", "noun", "color", "country", "name", "animal", "timeframe", "verb", "fruit"};
		for(String s: labels){
			ArrayList<String> list = readIt("dataStory"+"/"+s+".txt");
			myMap.put(s, list);
			
			
		}		
	}
	
	public int totalWordsInMap(){
		int i =0;
		for(String s: myMap.keySet()){
			i += myMap.get(s).size();
		}
		return i;
	}
	
	public int totalWordsConsidered(){
		int totalWords = 0;
		for(int i =0; i < labelName.size(); i++ ){
			String name = labelName.get(i);
			if(myMap.containsKey(name)){
				totalWords += myMap.get(name).size();
				
			}
		}
        
		return totalWords;
    }
	
	public String randomForm(ArrayList<String> source){
		int index = myRandom.nextInt(source.size());
		return source.get(index);
	}
	
	public String getSubstitute(String label){
		
		if (label.equals("number")) {
			return "" + myRandom.nextInt(50) +5;
		}
		return randomForm(myMap.get(label));	
		
		}
	
	public String processWord(String w){
		int first = w.indexOf("<");
		int last = w.indexOf(">" , first);
		if(first == -1 || last == -1){
			return w;
		}
		String prefix = w.substring(0, first);
		String suffix = w.substring(last+1);
		String label = w.substring(first+1, last);
		String sub = getSubstitute(w.substring(first+1, last));
		System.out.println(label);
		if(!labelName.contains(label)){
			labelName.add(label);
		}
		return prefix+sub+suffix;
	}
	
	public void printOut(String s, int lineWidth){
		int charsWritten =0;
		for(String w : s.split("\\s+")){
			if(charsWritten + w.length() > lineWidth){
				System.out.println();
				charsWritten = 0;
			}
			System.out.print(w+ " ");
			charsWritten += w.length() +1;
		}
	}
	
	public String fromTemplate(String source){
		String story = "";
		if(source.startsWith("http")){
			URLResource resource = new URLResource(source);
			for(String word : resource.words()){
				story = story + processWord(word) + " ";
				
			}
		}
		else {
			FileResource resource = new FileResource(source);
			words.clear();
			for(String word : resource.words()){
				story = story + processWord(word) + " ";
			}
		}
		return story;
	}

	public ArrayList<String> readIt(String source){
		ArrayList<String> list = new ArrayList<String>();
		if(source.startsWith("http")){
			URLResource resource = new URLResource(source);
			for(String line: resource.lines()){
				list.add(line);
			}
		}
		else {
			FileResource resource = new FileResource(source);
			for(String line: resource.lines()){
				list.add(line);
			}
		}
		return list;
	}
	public static void main(String[] args) {
		GladLibMap gl = new GladLibMap();
		 //System.out.println("\n");
		String story = gl.fromTemplate("datalong/madtemplate3.txt");
        gl.printOut(story, 60);
        
        int totalWordsUsed = gl.totalWordsInMap();
        System.out.println("Total words used to build the Story " + totalWordsUsed);
        
        int totalWordsConsidered = gl.totalWordsConsidered();
        System.out.println("Total words  " + totalWordsConsidered);
	}

}
