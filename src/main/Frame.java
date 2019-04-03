package main;

public class Frame {
    private static final int MAX_ATTEMPTS_PER_FRAME = 0;
	private static final int MAX_PINS = 0;
	private int[] scores = new int[MAX_ATTEMPTS_PER_FRAME];
    private int noOfPins = 10;
    private int noAttempts = 0;
    private boolean isStrike = false;
    
    
   int strikeCounter = 0;

    public boolean isSpare() {
        return noOfPins == 0 && noAttempts == MAX_ATTEMPTS_PER_FRAME && !isStrike;
    }

    public boolean isStrike() {
        return noOfPins == 0 && noAttempts == MAX_ATTEMPTS_PER_FRAME && isStrike;
    }

    public boolean isDone () {
        return noAttempts == MAX_ATTEMPTS_PER_FRAME;
    }

    public void setScore(int score) {

        scores[noAttempts++] = score;
        noOfPins -= score; // keep track of remaining pins/frame

        if (score == MAX_PINS) {
            isStrike = true;
 			strikeCounter++;
        }
    }

    public void limitToOneAttempt(){
        scores[1] = 0;
        noAttempts++;
    }

    public int score() { return scores[0] + scores[1];}

    public int getFirstScore() {
        return scores[0];
    }

    public int getSecondScore() {
        return scores[1];
    }
    

}
