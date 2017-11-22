package week4Vigenere;

import javax.crypto.EncryptedPrivateKeyInfo;

import edu.duke.FileResource;

/*This class provides an implementation of the Caesar cipher algorithm 
 * that you learned about earlier with public encrypt and decrypt methods */
public class CaesarCipher {
	private String alphabet;
	private String shiftedAlphabet;
	private int theKey;

	public CaesarCipher(int key){
		theKey = key;
		alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		shiftedAlphabet = alphabet.substring(key) + 
				          alphabet.substring(0, key);
		
		alphabet = alphabet + alphabet.toLowerCase();
		shiftedAlphabet = shiftedAlphabet + shiftedAlphabet.toLowerCase();
		
	}

    private char transformLetter(char c, String from, String to) {
		int idx = from.indexOf(c);
		if(idx != -1){
			return to.charAt(idx);
		}
		return c;

	}
    
    public char encryptLetter(char c){
    	return transformLetter(c, alphabet, shiftedAlphabet);
    }
    
    public char decryptLetter(char c){
    	return transformLetter(c, shiftedAlphabet, alphabet);
    }
    
    public String transform(String input, String from, String to){
    	StringBuilder sb = new StringBuilder(input);
    	for(int i=0; i < sb.length(); i++){
    		char c = sb.charAt(i);
    		c = transformLetter(c, from, to);
    		sb.setCharAt(i, c);
    	}
    	return sb.toString();
    	
    }
    
    public String encrypt(String input){
    	return transform(input, alphabet, shiftedAlphabet);
    }
    
    public String decrypt(String input){
    	return transform(input,shiftedAlphabet, alphabet);
    }
    
    public String toString() {
    	return "" + theKey;
    }
    
    public void testCaesarCipher(){
    	FileResource resource = new FileResource("week4Data/titus-small_key5.txt");
    	String rs = resource.asString();
    	String decrypt = decrypt(rs);
    	System.out.println(decrypt);
    }
    
    public static void main(String[] args) {
    	CaesarCipher cc = new CaesarCipher(5);
    	//cc.testCaesarCipher();
    	
    }
	
}
