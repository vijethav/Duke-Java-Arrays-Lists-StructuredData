package week1;

import edu.duke.FileResource;

public class CaesarCipher {

	public String encrypt(String input, int key){
		StringBuilder encrypted = new StringBuilder(input);

		String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		//String lowerAlphabet = "abcdefghijklmnopqrstuvwxyz";
		String shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0, key);
		//String shiftedLowerAlphabet = lowerAlphabet.substring(key) + lowerAlphabet.substring(0, key);
		


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

	public String encryptTwoKeys(String input, int key1, int key2){
		StringBuilder encrypted = new StringBuilder(input);
		String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String shiftedAlphabetEven = alphabet.substring(key1) + alphabet.substring(0, key1);
		String shiftedAlphabetOdd = alphabet.substring(key2) + alphabet.substring(0, key2);

		for(int i =0; i< encrypted.length(); i++){
			char currChar = encrypted.charAt(i);
			int idx = alphabet.indexOf(Character.toUpperCase(currChar));
			if(idx != -1){
				if(i%2 == 0){
					if( currChar == Character.toUpperCase(currChar)){
						char newChar = shiftedAlphabetEven.charAt(idx);
						encrypted.setCharAt(i, newChar);
					}
					else {
						char newChar = Character.toLowerCase(shiftedAlphabetEven.charAt(idx));
						encrypted.setCharAt(i, newChar);
					}
				}
				else {
					if( currChar == Character.toUpperCase(currChar)){
						char newChar = shiftedAlphabetOdd.charAt(idx);
						encrypted.setCharAt(i, newChar);
					}
					else {
						char newChar = Character.toLowerCase(shiftedAlphabetOdd.charAt(idx));
						encrypted.setCharAt(i, newChar);
					}
				}

			}
		}
		return encrypted.toString();
	}
	
	public void eyeballDecrypt(String encrypted) {
		CaesarCipher cipher = new CaesarCipher();
		for( int i =0; i < 26; i++){
			String s = cipher.encrypt(encrypted, i);
			System.out.println(i + "\t" + s);
		}
	}

	public static void main(String[] args) {
		int key = 15;
		CaesarCipher c = new CaesarCipher();
		FileResource fr = new FileResource("data/message3.txt");
		String message = fr.asString();
		
		//c.eyeballDecrypt("Lujyfwapvu huk zljbypaf hyl mbukhtluahs whyaz vm avkhf'z Pualyula");
		
		
		String encrypted = c.encrypt("First Legion", key);
		System.out.println("key is " + key + "\n" + encrypted);
		//String decrypted = c.encrypt(encrypted, 26-key);
		//System.out.println(decrypted);

		System.out.println(c.encryptTwoKeys(message, 17 , 4));

		

	}

}
