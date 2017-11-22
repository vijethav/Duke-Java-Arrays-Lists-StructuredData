package week1;

public class WordPlay {
	
	public static boolean isVowel(char ch) {
		String vowels = "aeiouAEIOU";
		int idx = vowels.indexOf(ch);
		if( idx != -1)
			return true;
		else
			return false;
	}
	
	public static String replaceVowels(String phrase,char ch){
		StringBuilder message = new StringBuilder(phrase);
		for(int i =0; i< message.length(); i++){
			char currChar = message.charAt(i);
			if(isVowel(currChar)) {
				message.setCharAt(i, ch);
			}
		}
		return message.toString();
	}
	
	public static String emphasize(String phrase, char ch){
		StringBuilder message = new StringBuilder(phrase);
		for(int i = 0; i< message.length(); i++) {
			char currChar = message.charAt(i);
			if(ch == Character.toLowerCase(currChar))
			   if(i % 2 == 0 ){
				    message.setCharAt(i, '*');
				}
			   else {
				   message.setCharAt(i, '+');
			   }
		}
		
		return message.toString();
	}

	public static void main(String[] args) {

		System.out.println(isVowel('G'));
		System.out.println(replaceVowels("Hello World", '*'));
		System.out.println(emphasize("Mary Bella Abracadabra", 'a'));

	}

}
