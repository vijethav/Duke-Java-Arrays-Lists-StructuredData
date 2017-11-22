package week4Vigenere;

import java.io.File;

import edu.duke.FileResource;

public class CaesarCracker {
	char mostCommon;
	
	public  CaesarCracker() {
		mostCommon = 'e';
	}
	public CaesarCracker(char c){
		mostCommon = c;
	}
	
	public int getKey(String encrypted){
		
		int[] freqs = countLetters(encrypted);
		int maxDex = maxIndex(freqs);
		int mostCommonPos = mostCommon - 'a';
		//System.out.println(mostCommonPos);
		int dkey = maxDex - mostCommonPos;
		if(maxDex < mostCommonPos){
			dkey = 26 - (mostCommonPos-maxDex);
		}
		return dkey;
		
	}
	

	private int maxIndex(int[] values) {
		int maxDex = 0;
		for(int i=0; i< values.length; i++){
			
			if (values[i] > values[maxDex]){
				maxDex = i;
			}
				
		}
		return maxDex;
	}
	private int[] countLetters(String message) {
		String alphabet = "abcdefghijklmnopqrstuvwxyz";
		int[] counts = new int[26];
		for(int i =0; i < message.length(); i++){
			char current = message.charAt(i);
			int dex = alphabet.indexOf(Character.toLowerCase(current));
			if(dex != -1){
				counts[dex] += 1;
			}
		}
		return counts;
	}
	
	public String decrypt(String encrypted){
		int key = getKey(encrypted);
		CaesarCipher cc = new CaesarCipher(key);
		return cc.decrypt(encrypted);
	}
	public void testCaesarCracker(){
		FileResource resource = new FileResource("week4Data/oslusiadas_key17.txt");
		String encrypted = resource.asString();
		System.out.println(decrypt(encrypted));
	}
	
	public static void main(String[] args) {
		CaesarCracker cc = new CaesarCracker('a');
        //cc.testCaesarCracker();

	}

}
