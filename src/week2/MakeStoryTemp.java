package week2;

import java.util.ArrayList;
import java.util.Random;

import edu.duke.FileResource;

public class MakeStoryTemp {
	
	private ArrayList<String> adjectiveList;
	private ArrayList<String> nounList;
	private ArrayList<String> animalList;
	private ArrayList<String> countryList;
	private ArrayList<String> nameList;
	private ArrayList<String> colorList;
	private ArrayList<String> timeList;
	private Random myRandom;
	
	private String dataSourceDirectory = "dataStory";
	public MakeStoryTemp(){
		initializeFromSource(dataSourceDirectory);
		myRandom = new Random();
	}
	private String processWord(String w) {
		int first = w.indexOf("<");
		int last = w.indexOf(">", first);
		if(first == -1 || last == -1){
			return w;
		}
		else {
			w = w.substring(first+1, last);
		}
		return w;
	}
	
	private void initializeFromSource(String source){
		adjectiveList = readIt(source+"/adjective.txt");
		nounList = readIt(source+"/noun.txt");
		animalList = readIt(source+"/animal.txt");
		countryList = readIt(source+"/country.txt");
		nameList = readIt(source+"/name.txt");
		colorList = readIt(source+"/color.txt");
		timeList = readIt(source+"/timeframe.txt");
	}
	private ArrayList<String> readIt(String string) {
		ArrayList<String> list = new ArrayList<String>();
		FileResource resource = new FileResource();
		for(String line: resource.lines()){
			list.add(line);
		}
		return list;
	}

	private String getSubstitute(String label) {
		if (label.equals("country")){
			return randomFrom(countryList);
		}
		if (label.equals("color")){
			return randomFrom(colorList);
		}
		if (label.equals("noun")){
			return randomFrom(nounList);
		}
		if (label.equals("name")){
			return randomFrom(nameList);
		}
		if (label.equals("animal")){
			return randomFrom(animalList);
		}
		if (label.equals("time")){
			return randomFrom(adjectiveList);
		}
		return "**UNKNOWN**";
	}
	
	private String randomFrom(ArrayList<String> source){
		int index = myRandom.nextInt(source.size());
		return source.get(index);
	}
	private void printOut(String s, int lineWidth){
		int charsWritten =0;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
