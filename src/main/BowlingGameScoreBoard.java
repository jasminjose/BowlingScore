package main;
import java.util.ArrayList;
import java.util.List;


public class BowlingGameScoreBoard implements BowlingGame {
	
    private List<Frame> frames;
    private static int MAX_FRAMES = 10;
    private static int MAX_PINS = 10;
//    private static int MAX_ATTEMPTS_PER_FRAME = 2;
    private int frameCounter = 0;
    private int strikeCounter = 0;
    private static final int ALL_STRIKE_SCORE = 300;
    
    public BowlingGameScoreBoard(){
    	frames = new ArrayList<Frame>(MAX_FRAMES);
    	for (int i = 0; i < MAX_FRAMES; i++){
    		frames.add(new Frame());
    	}
    }

	public void roll(int noOfPins) {
		// TODO Auto-generated method stub
        if (noOfPins > MAX_PINS) {
            throw new BowlingException("illegal argument " + noOfPins);
        }

        Frame frame = getFrame();

        if (frame == null) {
            throw new BowlingException("all attempts exhausted - start new game");
        }

        frame.setScore(noOfPins);

        if (isBonusFrame()) {
            Frame prev = getPreviousFrame();
            // restrict to one attempt, when last frame was spare
            if (prev.isSpare()) {
                frame.limitToOneAttempt();
            }
        }
	}

	@Override
	public int score() {
		// TODO Auto-generated method stub
	       int score;

	        // first frame
	        if (frameCounter == 0) {

	            Frame curr = getCurrentFrame();
	            return curr.score();

	        } else {

	            // score 300, strikes for all frames
	            if (isLastFrame() && isAllStrikes()) {
	                return ALL_STRIKE_SCORE;
	            }

	            Frame curr = getCurrentFrame();
	            Frame prev = getPreviousFrame();

	            // only add previous last frame to current score
	            if (isBonusFrame()) {
	                return prev.score() + curr.score();
	            }

	            score = curr.score();

	            if(prev.isSpare()) {
	                score += (prev.score() + curr.getFirstScore());
	            }

	            if(prev.isStrike()) {
	                score += (prev.score() + curr.getFirstScore() +  curr.getSecondScore());
	            }

	        }

	        return score;	}

    private Frame getFrame(){

        Frame frame = getCurrentFrame();

        if (frame.isDone()) {

            // new bonus frame
            if(isLastFrame() && (frame.isSpare() || frame.isStrike())) {
                Frame bonus = new Frame();
                frames.add(bonus);
                frameCounter++;
                return bonus;
            }

            frameCounter++;
            if (frameCounter == MAX_FRAMES || isBonusFrame()) {
                return null;
            }

            frame = getCurrentFrame();
        }

        return frame;
    }
    
    private Frame getPreviousFrame() {
        return frames.get(frameCounter-1);
    }

    private Frame getCurrentFrame() {
        return frames.get(frameCounter);
    }

    private boolean isAllStrikes() {
    return strikeCounter == MAX_FRAMES ;
}

    private boolean isBonusFrame() {
        return frames.size() > MAX_FRAMES;
    }

    private boolean isLastFrame() {
        return frameCounter == MAX_FRAMES - 1;
    }
    
	public List<Frame> getFrames() {
		return frames;
	}

	public void setFrames(List<Frame> frames) {
		this.frames = frames;
	}
	
	

}
