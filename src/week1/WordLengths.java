package week1;

import edu.duke.FileResource;

public class WordLengths {
	
	public int[] countWordLengths(FileResource resource,  int counts[]){
	
		for(String word: resource.words()){
			StringBuilder wd = new StringBuilder(word);
			int wordLength = 0;
			for( int i = 0; i < wd.length(); i++){
				if(i == 0 && !Character.isLetter(wd.charAt(i))){
					wd.deleteCharAt(i);
				}
				else if(i == wd.length()-1  && !Character.isLetter(wd.charAt(i))){
					wd.deleteCharAt(i);
				}
				else{
					wordLength++;
				}
			}
			
			if(wordLength < counts.length && wordLength != -1)
			    counts[wordLength] ++;
			else if(wordLength >= counts.length) {
				counts[counts.length]++;
			}
	
		}
		
		return counts;
	}
	
	public int indexOfMax(int[] counts) {
		int maxIdx =0;
		for(int i = 0; i< counts.length; i++){
			if(counts[i] > counts[maxIdx]){
				maxIdx = i;
			}
		}
		System.out.println(maxIdx);
		return maxIdx;
	}

	public static void main(String[] args) {
		WordLengths wl = new WordLengths();
		FileResource resource = new FileResource("data/romeo.txt");
		int counts[] = new int[31];
		
		int result[] = wl.countWordLengths(resource, counts); 
		
		for(int i = 0; i < counts.length; i++){
			if(counts[i] !=0){
			   System.out.println(counts[i] + " words of length" + i);
			}
		}
		wl.indexOfMax(counts);
		
		
		

	}

}
