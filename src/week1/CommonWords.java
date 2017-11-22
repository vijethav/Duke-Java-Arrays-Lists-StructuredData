package week1;

import edu.duke.FileResource;

public class CommonWords {
	
	private String[] getCommon() {
		FileResource rs = new FileResource("data/common.txt");
		String common[] = new String[20];
		int index = 0;
		for(String s: rs.words()){
			common[index] = s;
			index += 1;
		}
		return common;
	}
	
	void countShakespeare(){
		String[] plays = {"caesar.txt", "errors.txt", "hamlet.txt", 
			           "likeit.txt", "macbeth.txt", "romeo.txt"};
		//String[] plays = {"romeo.txt"};
		String[] common = getCommon();
		int[] counts = new int[common.length];
		for(int i =0; i < plays.length; i++){
			FileResource rs = new FileResource("data/" + plays[i]);
			countWords(rs, common, counts );
			System.out.println("done with " + plays[i]);
			
		}
		for(int i=0; i<common.length; i++){
			System.out.println(common[i] +"\t" + counts[i]);
		}
		
    }

	private void countWords(FileResource rs, String[] common, int[] counts) {
		for(String word: rs.words()){
			word = word.toLowerCase();
			int index = indexOf(common, word);
			if(index != -1) {
				counts[index] += 1;
			}
		}
		
	}

	

	private int indexOf(String[] common, String word) {
		for(int i =0; i < common.length;i++){
			if(common[i].equals(word)){
				return i;
			}
		}
		return -1;
	}

	public static void main(String[] args) {
		CommonWords cw = new CommonWords();
		cw.countShakespeare();
		

	}

}
