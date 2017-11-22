package week2;

import java.util.HashMap;
import java.util.Map;

import edu.duke.FileResource;

public class CodonCount {

	private HashMap<String, Integer> myMap;  //map DNA codons to their count.



	public CodonCount() {
		myMap = new HashMap<String, Integer>();
	}
  /*  new map of codons mapped to their counts from the string dna with the 
   * reading frame with the position start (a value of 0, 1, or 2)*/
	public void buildCodonMap(int start, String dna){
		myMap.clear();
		dna = dna.toUpperCase();
		dna = dna.trim();
		for(int i=start; i < dna.length(); i++){
			if(start+2 < dna.length()){
				String codon = dna.substring(start, start+3);
				if(codon.length() == 3){
					if(myMap.keySet().contains(codon)){
						myMap.put(codon, myMap.get(codon)+1);
					}
					else{
						myMap.put(codon, 1);
					}
				}
				start = start+3;
			}
		}
	}
	/* the codon in a reading frame that has the largest count */
	public String getMostCommonCodon(){
		String maxCodon = "";
		int maxCodonCount = 0;
		for(Map.Entry<String, Integer> entry: myMap.entrySet()){
			if(entry.getValue() > maxCodonCount){
				maxCodon = entry.getKey();
				maxCodonCount = entry.getValue();
			}
		}
		//System.out.println(" Max Codon " + maxCodon + " number " + maxCodonCount);
		return maxCodon;
	}
	
	public void printCodonCounts(int start, int end){
		System.out.println("Counts of codons between " + start + " and " + end + " inclusive are:");
		for(String codon : myMap.keySet()){
			int codonCount = myMap.get(codon);
			if ( codonCount >= start ||  codonCount <= end){
				System.out.println(codon + "\t" + codonCount);
			}
		}
		
	}


	public static void main(String[] args) {
		CodonCount cc = new CodonCount();
		
		FileResource fr = new FileResource("week2data/dnaMystery2");
		String st = fr.asString();
		
		for(int i =0; i < 3; i++){
			cc.buildCodonMap(i, st);
			
			String maxCodon = cc.getMostCommonCodon();
			System.out.println("Reading Frame starting with " + i +" results in " + cc.myMap.size() + " Unique Codons");
			System.out.println("and most common Codon is " + maxCodon + " with Count " + cc.myMap.get(maxCodon));
			cc.printCodonCounts(1, 5);
			System.out.println("\n");
		}


	}

}
