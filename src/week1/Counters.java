package week1;

public class Counters {

	public void textFingerPrint(String s){
		String alpha = "abcdefghijklmnopqrstuvwxyz";
		int[] counters = new int[26];
		for(int i =0; i < s.length(); i++){
			char ch = s.charAt(i);
			int index = alpha.indexOf(Character.toLowerCase(ch));
			if(index != -1){
				counters[index] += 1;
			}
		}
		for(int i =0; i < counters.length; i++){
			System.out.println(alpha.charAt(i) + "\t" + counters[i]);
		}
		
	}
	
	public void dnaFingerprint(String s){
		int cc = 0, cg = 0, ca =0, ct =0;
		for( int i =0; i< s.length(); i++){
			char ch = s.charAt(i);
			if(ch == 'c'){
				cc +=1;
			}
			else if(ch =='g'){
				cg +=1;
			}
			else if(ch == 'a'){
				ca +=1;
			}
			else if(ch == 't'){
				ct +=1;
			}
			
		}
		System.out.println(cc +"\t" + cg +"\t" + ca +"\t" + ct );
	}
	
	
	public static void main(String[] args) {
		Counters c = new Counters();
		c.textFingerPrint("Vijethaaaa");
		c.dnaFingerprint("ctaagttctracgt");

	}

}
