package week2;

import java.util.ArrayList;

import edu.duke.FileResource;

public class CharactersInPlay {
	private ArrayList<String> charNames;   //names of the characters you find
	private ArrayList<Integer> nameFreqs;  //number of times characters counts

	public CharactersInPlay() {
		charNames = new ArrayList<String>();
		nameFreqs = new ArrayList<Integer>();
	}

	/*For each line, if there is a period on the line, extract the possible name of 
	 * the speaking part, and call update to count it as an occurrence for this person.  */
	public void findAllCharacters(){
		charNames.clear(); 
		nameFreqs.clear();
		FileResource resource = new FileResource("data/errors.txt");
		for(String line: resource.lines() ){
			int index = line.indexOf(".");
			if(index > -1){
				int startIdx = 0;
								while(index > startIdx){
									if(!line.substring(startIdx, startIdx+1).equals(" "))
										break;
									else
										startIdx = startIdx+1;
								}
				String person = line.substring(startIdx, index);
				update(person);

			}
		}
	}
	/*  update the two ArrayLists, adding the character’s 
	 * name if it is not already there, 
	 * and counting this line as one speaking part for this person. */
	public void update(String person){
		int index = charNames.indexOf(person);
		if(index == -1) {
			charNames.add(person);
			nameFreqs.add(1);
		}
		else{
			int value = nameFreqs.get(index);
			nameFreqs.set(index, value+1);

		}

	}
	
	public int charactersWithMaxParts(){
		int maxIndex = 0;
		int maxParts = nameFreqs.get(maxIndex);
		for(int i =0; i < nameFreqs.size(); i++){
			if(nameFreqs.get(i) > maxParts){
				maxParts = nameFreqs.get(i);
				maxIndex = i;
			}
		}
	    return maxIndex;
	}

   public void charactersWithNumParts( int num1 , int num2){
	   for(int i =  0; i < nameFreqs.size();i++){
		   if(nameFreqs.get(i) >= num1 && nameFreqs.get(i) <= num2){
			   System.out.println("Character name : " + charNames.get(i) +  " --Number : " + nameFreqs.get(i));
			   
		   }
	   }
   }

   public void findAllCharactersTester() {
	   findAllCharacters();
	   for(int i =0; i < charNames.size() ; i++){
			//System.out.println("Character name : " + charNames.get(i) + " -- Number : " + nameFreqs.get(i));
		} 
	   charactersWithNumParts(10, 15);
   }
	public static void main(String[] args) {
		CharactersInPlay cp = new CharactersInPlay();
		cp.findAllCharactersTester();
		
		int maxIndex = cp.charactersWithMaxParts();
		System.out.println(maxIndex + " Character with max Parts : " + cp.nameFreqs.get(maxIndex) + "Max Number : " + cp.charNames.get(maxIndex));
       // cp.charactersWithNumParts(10, 15);

	}

}
