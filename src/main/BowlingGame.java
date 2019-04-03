package main;

public interface BowlingGame {
	
	/**
     * Keeps track of pins knocked over
     * @param noOfPins knocked over per frame
     */
	public void roll(int noOfPins);
	
	/**
    * @return score of current frame only
    */
	public int score();

}
