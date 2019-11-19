package bracket;

public class Team {

	// Array of winning percentages (denoted P) of each seed in the tournament.
	// Data retrieved from https://www.betfirm.com/seeds-national-championship-odds/
	private static double[] P =
		{
				-1,
				455.0/570,
				321.0/452,
				249.0/381,
				210.0/345,
				152.0/290,
				148.0/282,
				126.0/261,
				98.0/233,
				80.0/216,
				84.0/220,
				84.0/221,
				68.0/204,
				34.0/170,
				23.0/159,
				9.0/145,
				1.0/137
		};
	
	public Region region;
	public int seed;
	
	public Team(Region r, int seed) {
		this.region = r;
		this.seed = seed;
	}
	
	public String toString() {
		return region + " " + seed;
	}
	
	// Gets the winning percentage of the team, given its seed
	public double P() {
		return P[seed];
	}
	
}
