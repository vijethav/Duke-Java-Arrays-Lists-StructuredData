package week2;

import java.util.HashMap;

import edu.duke.FileResource;

public class WordFrequenciesMap {
	HashMap<String, Integer> map = new HashMap<String, Integer>();

	public void countWords(){
		FileResource fr = new FileResource("data/confucius.txt");
		int total = 0;
		for(String word: fr.words()){
			word = word.toLowerCase();
			if (map.keySet().contains(word)) {
				map.put(word, map.get(word)+1);
			}
			else{
				map.put(word, 1);
			}
			total +=1;
		}
		System.out.println("total = " + total);
	}
	public static void main(String[] args) {
		WordFrequenciesMap wf = new WordFrequenciesMap();
		wf.countWords();
		for(String word : wf.map.keySet()){
			int frequencies = wf.map.get(word);
			if (frequencies > 500){
				System.out.println(frequencies + "\t" + word);
			}
		}
		

	}

}
