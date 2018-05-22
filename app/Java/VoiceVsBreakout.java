import breakout.Breakout;
import audioengine.AudioEngine;

public class VoiceVsBreakout {
    static Breakout breakout;
    static AudioEngine audio;
    
    public static void main(String[] args) {        
        breakout = new Breakout();
        audio = new AudioEngine();
        
        try {
            //letsRock(15); // Piano
            letsRock(30); // Voice
        }
        catch(Exception ex) {}
    }

    public static void letsRock(int frequency_gap) throws Exception {
        final int time_gap = 100;
        
        double prev_frequency = 0, curr_frequency;
        
        char direction_before_pause = 'r';
        long prev_time = -1, curr_time;
        
        char direction = 's';

        while(true) {
            curr_frequency = audio.getCurrentFrequency();
            curr_time = System.currentTimeMillis();
            
            if(curr_frequency == 0) {
                if(prev_time == -1) {
                    prev_time = curr_time;
                }
                else if(direction != 's' && curr_time - prev_time >= time_gap) {
                    direction_before_pause = direction;
                    direction = 's';
                }
            }
            else {
                if(direction == 's') {
                    direction = direction_before_pause;
                }
                prev_time = -1;
            }
            
            if(direction != 's' && Math.abs(curr_frequency - prev_frequency) >= frequency_gap && curr_frequency != 0) {
                if(direction == 'r') {
                    direction = 'l';
                }
                else {
                    direction = 'r';
                }

                prev_frequency = curr_frequency;
            }
            
            // If you want to play with keyboard, comment this (and uncomment in Panel.java)
            // breakout.panel.paddle.direction = direction;
        }
    }
}
