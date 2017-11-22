package week1;

import java.util.Random;

public class RandomDices {

	public void simpleSimulate( int rolls) {
		Random rand = new Random();
		int twos =0;
		int twelves = 0;
		for(int i=0; i< rolls; i++) {
			int d1 = rand.nextInt(6) +1;
			int d2 = rand.nextInt(6) +1;
			if(d1+d2 == 2){
				twos +=1;
			}
			else if(d1 + d2 == 12){
				twelves +=1;
			}
		}
		System.out.println("2's=\t" + twos + "\t" + 100.0 * twos/rolls);
		System.out.println("12's=\t" + twelves + "\t" + 100.0 * twelves/rolls);
	}
	
	public void simulate(int rolls) {
		Random rand = new Random();
		int counts[] = new int[13];
		
		for(int i=0; i < rolls; i++) {
			int d1 = rand.nextInt(6) +1;
			int d2 = rand.nextInt(6) +1;
			counts[d1+d2] +=1;
		}
		
		for(int i=2; i<= 12; i++) {
			System.out.println( i + "'s=\t" + counts[i] + "\t" + 100.0 * counts[i]/rolls);
		}
	}
	public static void main(String[] args) {
		RandomDices rd = new RandomDices();
		rd.simpleSimulate(10000);
		rd.simulate(10000);
	}

}
