package week1;

	

public class CharaterDemo {
	
	public static void digitTest() {
		String test = "ABCabc0123456789';#!";
		for(int i =0; i< test.length(); i++){
			char ch = test.charAt(i);

			if(Character.isDigit(ch)){
				System.out.println(ch + " is a digit");	
			}

			else if (Character.isAlphabetic(ch)){
				System.out.println(ch + " is a Alphabet");
			}

			else {
				System.out.println(ch +" is a special Character");
			}
		}
	}

	public static void main(String[] args) {

           digitTest();
	}

}
