package week4Vigenere;

import java.util.HashMap;
import java.util.HashSet;

import edu.duke.FileResource;

public class VigenereBreaker {
	/* a String message, representing the encrypted message, an integer whichSlice, 
	 * indicating the index the slice should start from, and an integer totalSlices, 
	 * indicating the length of the key. This method
	 *  returns a String consisting of every totalSlices-th character from message, 
	 *  starting at the whichSlice-th character.*/
	public String sliceString(String message, int whichSlice, int totalSlice){
		
		StringBuilder slicedMessage = new StringBuilder();
		for(int i = whichSlice; i < message.length(); i = i+totalSlice){
			slicedMessage.append(message.substring(i, i+1));
		}
		
		return slicedMessage.toString();
	}
	/*  three parameters—a String encrypted that represents the encrypted message,
	 *  an integer klength that represents the key length, 
	 *  and a character mostCommon that indicates the most common character in the language of the message. 
	 *  This method should make use of the CaesarCracker class, as well as the sliceString method, 
	 *  to find the shift for each index in the key. 
	 *  You should fill in the key (which is an array of integers) and return it.*/
	
	public int[] tryKeyLength(String encrypted, int klength, char mostCommon){
		int[] key= new int[klength];
		String[] slicedMessages = new String[klength];
		
		for(int i=0; i < klength; i++){
			slicedMessages[i] = sliceString(encrypted, i, klength);
		}
		
		CaesarCracker cc = new CaesarCracker();
		for(int i = 0; i< klength; i++){
			key[i] = cc.getKey(slicedMessages[i]);
		}
		
		return key;
	}
	
	/*This void method should put everything together, so that you can create a new VigenereBreaker  */
	
	public String breakVigenere() {
		FileResource resource = new FileResource("week4Data/athens_keyflute.txt");
		String encrypted = resource.asString();
		int[] keys = tryKeyLength(encrypted, 5, 'e');

		VigenereCipher vc = new VigenereCipher(keys);
		
		String decrypted = vc.decrypt(encrypted);
		return decrypted;
	}
	
	/*which has one parameter—a FileResource fr. 
	 * This method should first make a new HashSet of Strings, 
	 * then read each line in fr, convert that line to lowercase, 
	 * and put that line into the HashSet that you created. The method should 
	 * then return the HashSet representing the words in a dictionary. 
	 * All the dictionary files, including the English dictionary file, */
	
	public HashSet<String> readDictionary(FileResource fr){
		
		HashSet<String> dictionary = new HashSet<String>();
		for(String line: fr.lines()){
		   line = line.toLowerCase();
		   dictionary.add(line);
		}
		return dictionary;
	}
/*countWords, which has two parameters—a String message, and a HashSet of Strings dictionary.
 * This method should split the message into words (use .split(“\\W+”), which returns a String array), 
 * iterate over those words, and see how many of them are “real words”—that is, 
 * how many appear in the dictionary.  */
	
	public int countWords(String message, HashSet<String> dictionary){
		int result = 0;
		int totalCount = 0;
		for(String word: message.split("\\W+")){
			word = word.toLowerCase();
			totalCount = totalCount +1;
			
			if(dictionary.contains(word)){
				result = result+1;
			}
		}
		return result;
	}
/* which has two parameters—a String encrypted, and a HashSet of Strings dictionary. 
 * This method should try all key lengths from 1 to 100 (use your tryKeyLength method to 
 * try one particular key length) to obtain the best decryption for each key length in that range. 
 * For each key length, your method should decrypt the message (using VigenereCipher’s decrypt method 
 * as before), and count how many of the “words” in it are real words in English, 
 * based on the dictionary passed in (use the countWords method you just wrote). 
 * This method should figure out which decryption gives the largest count of real words,
 *  and return that String decryption. Note that there is nothing special about 100;
 *   we will just give you messages with key lengths in the range 1–100*/
	
	public String breakForLanguage(String encrypted, HashSet<String> dictionary){
		int[] key_list = new int[100];
		int[] totalWords = new int[100];
		String[] decrypted = new String[100];
		
		
		for(int i =0; i < key_list.length ; i++){
			key_list[i] = i+1;
		}
		
		for(int i=0; i < key_list.length; i++){
			   
		       int[] keys = tryKeyLength(encrypted, key_list[i], 'e');
		       VigenereCipher vc = new VigenereCipher(keys);
		       String decrypt = vc.decrypt(encrypted);
		       totalWords[i] = countWords(decrypt, dictionary);
		       decrypted[i] = decrypt;
		}
		
		int maxIdx = maxIdx(totalWords);
		
		System.out.println( " Maximum words are " + totalWords[maxIdx] + 
				            " at index " + maxIdx + 
				            " key length is " + key_list[maxIdx]);
		
		int trueKey = key_list[maxIdx];
		int[] keys = tryKeyLength(encrypted, trueKey, 'e');
		System.out.println("The keys length  is " + "\t" + keys.length);
        for (int k = 0; k < keys.length; k++) {
            System.out.println("Keys are " + keys[k]);
        }
        
        String trueDecrypted = decrypted[maxIdx];
        return trueDecrypted;
        
	}
	
	public int maxIdx(int[] totalWords){
		int maxWords = 0;
		int maxIdx =0;
		for(int i =0; i< totalWords.length; i++){
			if(maxWords < totalWords[i]){
				maxWords = totalWords[i];
				maxIdx = i;
			}
		}
		return maxIdx;
	}
	
	/*This method should find out which character, of the letters in the English alphabet, 
	 * appears most often in the words in dictionary. It should return this most 
	 * commonly occurring character.Remember that you can iterate over a 
	 * HashSet of Strings with a for-each style for loop */
	
	public char mostCommonCharIn(HashSet<String> dictionary){
		HashMap<String, Integer> countChar = new HashMap<String, Integer>();
		String alph = "abcdefghijklmnopqrstuvwxyz";
		//assinging alphabet to key in HashMap
		for(int i =0; i < alph.length() ; i++){
			countChar.put(alph.substring(i, i+1), 0);
		}
		
		// counting each alphabet
		
		for(String word: dictionary){
			word = word.toLowerCase();
			String[] letters = word.split("");
			for(String letter: countChar.keySet()){
				for(String le: letters){
					if(le.equals(letter)){
						countChar.put(letter, countChar.get(letter)+1);
					}
				}
			}
		}
		// Max value for the alphabet in hashmap
		int maxCharCount = 0;
		String maxChar = null;
		for (String letter: countChar.keySet()){
			if(maxCharCount < countChar.get(letter)){
				maxCharCount = countChar.get(letter);
				maxChar = letter;
			}
		}
		return maxChar.charAt(0);
		
	}
	
	
	public void breakForAllLangs(String encrypted, HashMap<String, HashSet<String>> languages){
		HashMap<String, String> decrypted = new HashMap<String, String>();
		for(String language: languages.keySet()){
		     String result = breakForAllLanguage(encrypted, languages.get(language), language);
		     decrypted.put(language, result);
		}
		
		for(String la: decrypted.keySet()){
			System.out.println(" Language Found " + la);
			System.out.println(decrypted.get(la));
		}
	}
	
	public String breakForAllLanguage(String encrypted, HashSet<String> dictionary, String lan){
		int[] key_list = new int[100];
		int[] totalWords = new int[100];
		String[] decrypted = new String[100];
		System.out.println("Language is " + lan) ;
		
		for(int i =0; i < key_list.length ; i++){
			key_list[i] = i+1;
		}
		
		char common = mostCommonCharIn(dictionary);
		System.out.println("Common Char in " + lan + " is " + common);
		for(int i=0; i < key_list.length; i++){
			   
		       int[] keys = tryKeyLength(encrypted, key_list[i], common);
		       VigenereCipher vc = new VigenereCipher(keys);
		       String decrypt = vc.decrypt(encrypted);
		       totalWords[i] = countWords(decrypt, dictionary);
		       decrypted[i] = decrypt;
		}
		
		int maxIdx = maxIdx(totalWords);
		
		System.out.println( " Maximum words are " + totalWords[maxIdx] + 
				            " at index " + maxIdx + 
				            " key length is " + key_list[maxIdx]);
		
		int trueKey = key_list[maxIdx];
		int[] keys = tryKeyLength(encrypted, trueKey, 'e');
		System.out.println("The keys length  is " + "\t" + keys.length);
        for (int k = 0; k < keys.length; k++) {
           // System.out.println("Keys are " + keys[k]);
        }
        
        String trueDecrypted = decrypted[maxIdx];
        return trueDecrypted;
        
	}
	
	
	 public void breakVigenere2() {
	        FileResource fr = new FileResource();
	        String str = fr.asString();
	        FileResource dic = new FileResource();
	        HashSet<String> dictionary = readDictionary(dic);
	       // String result = breakForLanguage(str, dictionary);
	      //  System.out.println(result);
	        
	    }
	
	 
	 
	 //Text Slice String 
	public void testSliceString(){
		System.out.println(sliceString("abcdefghijklm", 4, 5));
		
	}
	public void testTryKeyLength(){
		FileResource resource = new FileResource("week4Data/secretmessage3.txt");
		String rs = resource.asString();
		String key = "flute";
		int[] keys = tryKeyLength(rs, key.length(), 'e');
		
		for(int i= 0; i< keys.length; i++){
			System.out.print(keys[i] + " ");
		}
	}
	
	public void testReadDictionary(){
		FileResource dicfr = new FileResource("week4Dictionaries/English");
		FileResource resource = new FileResource("week4messages/secretmessage3.txt");
		String ms = resource.asString();
		HashSet<String> dictionary = readDictionary(dicfr);	
	}
	 
	
	public static void main(String[] args) {
		VigenereBreaker vb = new VigenereBreaker();
		HashMap<String, HashSet<String>> languages = new HashMap<String, HashSet<String>>();
		String[] dictionaries = { "Danish", "Dutch", "French", "German", 
		          "Italian", "Portuguese", "Spanish", "English"};
		for(String dictionary: dictionaries){
		        FileResource dic = new FileResource("week4Dictionaries/" + dictionary);
		        HashSet<String> dictionaryWords = vb.readDictionary(dic);
		        languages.put(dictionary, dictionaryWords);
		}
		
		
		
		//FileResource dicfr = new FileResource("week4Dictionaries/English");
		FileResource resource = new FileResource("week4messages/secretmessage4.txt");
		String encrypted = resource.asString();
		//tester readDictionary
		//HashSet<String> dictionary = vb.readDictionary(dicfr);
		//char common = vb.mostCommonCharIn(dictionary);
		//System.out.println("Common Character: " + common);
 
		//vb.testSliceString();
		//vb.testTryKeyLength();
		//System.out.println(vb.breakVigenere());
		
		//String klength = "flute";
		//int key[] = vb.tryKeyLength(encrypted, klength.length(), common);
		//VigenereCipher vc = new VigenereCipher(key);
		//String decrypt = vc.decrypt(encrypted);
		
		//String decrypted = vb.breakForLanguage(encrypted, dictionary);
		//System.out.println(decrypted);
		
	  
		
		//char comm = vb.mostCommonCharIn(dictionary);
		//System.out.println(comm);
		
		
		vb.breakForAllLangs(encrypted, languages);
	}
	
	

}
