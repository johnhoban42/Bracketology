package bracket;

import java.util.*;

public class Bracketology {
	
	/*
	 * For each game, set a winning boundary for the two teams such that the favorite
	 * wins if a random number between 0 and 1 is less than the boundary and the lower
	 * team wins if it is less than the boundary.
	 * A higher chalk factor makes it harder for an underdog to pull off an upset.
	 */
	private static Team smartSim(Team t1, Team t2, double chalkFactor) {
		Team favorite, underdog;
		if(t1.seed < t2.seed) {
			favorite = t1;
			underdog = t2;
		}else {
			favorite = t2;
			underdog = t1;
		}
		double favoriteWinBoundary;
		if(favorite.seed == underdog.seed) {
			favoriteWinBoundary = 0.5;
		}else {
			favoriteWinBoundary = favorite.P() / (favorite.P() + Math.pow(underdog.P(), chalkFactor));
		}
		return Math.random() < favoriteWinBoundary ? favorite : underdog;
	}
	
	/*
	 * Picks a winner for each game regardless of seed.
	 */
	private static Team lazySim(Team t1, Team t2) {
		return Math.random() < 0.5 ? t1 : t2;
	}
	
	public static void main(String[] args) {
		// Initialize the arrays for each round of the bracket
		// 0 - First Round
		// 1 - Second Round
		// 2 - Sweet 16
		// 3 - Elite 8
		// 4 - Final 4
		// 5 - National Championship
		// 6 - National Champion
		Team[][] bracket = new Team[][] {new Team[64], new Team[32], new Team[16], new Team[8],
				new Team[4], new Team[2], new Team[1]};
				
		// Fill the First Round
		int n = 0;
		for(Region r: Region.values()) {
			bracket[0][n++] = new Team(r, 1);
			bracket[0][n++] = new Team(r, 16);
			bracket[0][n++] = new Team(r, 8);
			bracket[0][n++] = new Team(r, 9);
			bracket[0][n++] = new Team(r, 5);
			bracket[0][n++] = new Team(r, 12);
			bracket[0][n++] = new Team(r, 4);
			bracket[0][n++] = new Team(r, 13);
			bracket[0][n++] = new Team(r, 6);
			bracket[0][n++] = new Team(r, 11);
			bracket[0][n++] = new Team(r, 3);
			bracket[0][n++] = new Team(r, 14);
			bracket[0][n++] = new Team(r, 7);
			bracket[0][n++] = new Team(r, 10);
			bracket[0][n++] = new Team(r, 2);
			bracket[0][n++] = new Team(r, 15);
		}
		
		// The user selects either smartSim or lazySim.
		Scanner sc = new Scanner(System.in);
		char choice;
		boolean smart = true;
		boolean validInput = false;
		System.out.print("Choose (S)martSim or (L)azySim: ");
		while(!validInput) {
			choice = sc.nextLine().toUpperCase().charAt(0);
			switch(choice) {
				case 'S':
					validInput = true;
					break;
				case 'L':
					smart = false;
					validInput = true;
					break;
				default:
					System.out.print("Invalid input, please enter 'S' or 'L': ");
					break;
			}			
		}
		sc.close();
		
		// Simulate each round of the tournament, game by game.
		// Note that with smartSim it gets progressively more difficult for an underdog to pull off an upset.
		for(int i = 0; i < bracket.length-1; i++) {
			System.out.println("\nROUND " + (i+1) + ":\n");
			for(Team t: bracket[i]) {System.out.println(t);}
			for(int j = 0; j < bracket[i].length; j += 2) {
				Team winner;
				if(smart) {
					winner = smartSim(bracket[i][j], bracket[i][j+1], 0.9+i);
				}else {
					winner = lazySim(bracket[i][j], bracket[i][j+1]);
				}
				bracket[i+1][j/2] = winner;
			}
			System.out.println("\n************\n");
		}
		System.out.println("NATIONAL CHAMP: " + bracket[6][0]);
		
	}
	
}
