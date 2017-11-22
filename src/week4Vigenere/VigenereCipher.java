package week4Vigenere;

import java.util.Arrays;

import edu.duke.FileResource;

public class VigenereCipher {

	CaesarCipher[] ciphers;
	
	public VigenereCipher(int[] key){
		ciphers = new CaesarCipher[key.length];
		for(int i=0; i< key.length; i++){
			ciphers[i] = new CaesarCipher(key[i]);
		}
	}
	
	public String encrypt(String input) {
		StringBuilder answer = new StringBuilder();
		int i = 0;
		for(char c: input.toCharArray()) {
			int cipherIndex = i % ciphers.length;
			CaesarCipher thisCipher = ciphers[cipherIndex];
			answer.append(thisCipher.encryptLetter(c));
			i++;
		}
		return answer.toString();
	}
	
	public String decrypt(String input) {
		StringBuilder answer = new StringBuilder();
		int i = 0;
		for(char c: input.toCharArray()) {
			int cipherIndex = i % ciphers.length;
			//System.out.println(cipherIndex);
			CaesarCipher thisCipher = ciphers[cipherIndex];
			answer.append(thisCipher.decryptLetter(c));
			i++;
		}
		return answer.toString();
	}
	
	public String toString() {
		return Arrays.toString(ciphers);
	}
	
	public void testVigenereCipher(){
		
		FileResource resource = new FileResource("week4Data/titus-small.txt");
		String encrypted = resource.asString();
		System.out.println(encrypt(encrypted));
	}
	public static void main(String[] args) {
		int[] key = {17, 14, 12, 4};
		VigenereCipher vc = new VigenereCipher(key);
		vc.testVigenereCipher();

	}

}
