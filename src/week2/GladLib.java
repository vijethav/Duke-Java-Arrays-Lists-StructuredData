package week2;

import java.util.ArrayList;
import java.util.Random;

import edu.duke.FileResource;
import edu.duke.URLResource;

public class GladLib {
	
	private ArrayList<String> adjectiveList;
	private ArrayList<String> nounList;
	private ArrayList<String> colorList;
	private ArrayList<String> countryList;
	private ArrayList<String> nameList;
	private ArrayList<String> animalList;
	private ArrayList<String> timeList;
	private ArrayList<String> verbList;
	private ArrayList<String> fruitList;
	private Random myRandom;
	private ArrayList<String> labelName;
	private ArrayList<String> words;
	
	private String dataSourceUrl = "http://dukelearntoprogram.com/course3/data";
	private String dataSourceDirectory = "dataStory";
	
	public GladLib(){
		initializeFromSource(dataSourceDirectory);
		myRandom = new Random();
		words = new ArrayList<>();
	}
	
	public GladLib(String source){
		initializeFromSource(source);
		myRandom = new Random();
	}
	public void initializeFromSource(String source) {
		adjectiveList= readIt(source+"/adjective.txt");	
		nounList = readIt(source+"/noun.txt");
		colorList = readIt(source+"/color.txt");
		countryList = readIt(source+"/country.txt");
		nameList = readIt(source+"/name.txt");		
		animalList = readIt(source+"/animal.txt");
		timeList = readIt(source+"/timeframe.txt");
		verbList = readIt(source+"/verb.txt");
		fruitList = readIt(source+"/fruit.txt");
		
	}
	
	public String randomForm(ArrayList<String> source){
		int index = myRandom.nextInt(source.size());
		return source.get(index);
	}
	
	public String getSubstitute(String label){
		if(label.equals("country")) {
			return randomForm(countryList);	
		}
		if (label.equals("color")){
			return randomForm(colorList);
		}
		if (label.equals("noun")) {
			return randomForm(nounList);
		}
		if (label.equals("name")) {
			return randomForm(nameList);
		}
		if (label.equals("adjective")) {
			return randomForm(adjectiveList);
		}
		if (label.equals("animal")) {
			return randomForm(animalList);
		}
		if (label.equals("verb")) {
			return randomForm(verbList);
		}
		if (label.equals("fruit")) {
			return randomForm(fruitList);
		}
		if (label.equals("timeframe")) {
			return randomForm(timeList);
		}
		if (label.equals("number")) {
			return "" + myRandom.nextInt(50) +5;
		}
		return "**UNKNOWN**";
		}
	
	public String processWord(String w){
		int first = w.indexOf("<");
		int last = w.indexOf(">" , first);
		if(first == -1 || last == -1){
			return w;
		}
		String prefix = w.substring(0, first);
		String suffix = w.substring(last+1);
		
		String sub = getSubstitute(w.substring(first+1, last));
		while(words.contains(sub)){
			sub = getSubstitute(w.substring(first+1, last));
		}
		if(!words.contains(sub)){
			words.add(sub);
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
		GladLib gl = new GladLib();
		System.out.println("\n");
		String story = gl.fromTemplate("dataStory/madtemplate2.txt");
		System.out.println(" Total words used " + gl.words.size());
		System.out.println("\n");

        gl.printOut(story, 60);
	}

}
