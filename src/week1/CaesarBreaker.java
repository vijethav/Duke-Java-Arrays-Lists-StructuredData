package week1;

import edu.duke.FileResource;

public class CaesarBreaker {
	String alpha = "abcdefghijklmnopqrstuvwxyz";
	
	public int[] countLetters(String message) {
		
		int counts[] = new int[26];
		for(int i=0; i< message.length(); i++){
			char ch = Character.toLowerCase(message.charAt(i));
			int idx = alpha.indexOf(ch);
			if(idx != -1){
				counts[idx] += 1;
			}
		}
		
		return counts;		
	}
	
	
	public int getKey(String s){
		
		int[] letterFreqs = countLetters(s);
		
		/*for(int i =0; i< letterFreqs.length; i++){
			if(letterFreqs[i] !=0){
			   System.out.println(alpha.charAt(i) + "\t" + letterFreqs[i]);
			}
		}*/
		
		int maxIdx =  maxIndex(letterFreqs);
		
		System.out.println("MAX Freqs = " + maxIdx);
		int dkey = maxIdx - 4;
		if(maxIdx < 4){
			dkey = 26- (4-maxIdx);
		}
		System.out.println("print dkey = " + dkey);
		return dkey;
	}
	
	public String decrypt(String encrypted, int key){
		String message = encrypt(encrypted, 26 - key);
		return message;
	}
	
	public int maxIndex(int[] values) {
		int maxIdx = 0;
		for(int i = 0; i< values.length; i++){
			if(values[i] > values[maxIdx]){
				maxIdx = i;
			}
		}
		return maxIdx;
	}
	public String halfOfString(String message, int start){
		String answer = "";
		for(int i = start; i < message.length(); i +=2){
			answer = answer+message.charAt(i);
		}
		return answer;
	}
	
	public String decryptTwoKeys(String encrypted){
		String firstHalf = halfOfString(encrypted,0);
		String secondHalf = halfOfString(encrypted,1);
		int key1 = getKey(firstHalf);
		int key2 = getKey(secondHalf);
		System.out.println("Two keys found: key1= " + key1 + ", key2= " + key2);
		CaesarCipher cc = new CaesarCipher();
		String decryptedMessage = cc.encryptTwoKeys(encrypted, 26-key1, 26-key2);
		return decryptedMessage;
	}
	
	
	public String encrypt(String input, int key){
		StringBuilder encrypted = new StringBuilder(input);

		String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0, key);
	
		for(int i =0; i< encrypted.length(); i++){
			char currChar = encrypted.charAt(i);
			int idx = alphabet.indexOf(Character.toUpperCase(currChar));

			if(idx != -1){
				if( currChar == Character.toUpperCase(currChar)){
					char newChar = shiftedAlphabet.charAt(idx);
					encrypted.setCharAt(i, newChar);
				}
				else {
					char newChar = Character.toLowerCase(shiftedAlphabet.charAt(idx));
					encrypted.setCharAt(i, newChar);
				}
			}

		}
		return encrypted.toString();	
	}
	
	
	
	public static void main(String[] args) {
		String encrypted1 = "Ghdu Rzhq, Qr pdwwhu zkdw brx pdb kdyh khdug, wkhuh lv qr fdnh lq wkh frqihuhqfh urrp. Wkh fdnh lv d olh. Sohdvh nhhs zrunlqj rq Frxuvhud ylghrv. Wkdqnv, Guhz";
       
		CaesarBreaker cl = new CaesarBreaker();
		String decoded = cl.decrypt(encrypted1, cl.getKey(encrypted1));
		System.out.println(decoded);
		FileResource fr = new FileResource("data/mysteryTwoKeysPractice.txt");
        String message = fr.asString();
		System.out.println(cl.decryptTwoKeys(message));
	}

}
