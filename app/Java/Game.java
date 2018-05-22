import ai.kitt.snowboy.*;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.TargetDataLine;

import breakout.Breakout;

public class Game {
  static {
    System.loadLibrary("snowboy-detect-java");
  }
  static Breakout breakout;

  public static void main(String[] args) {
    char direction = 'r';
    int prev_result = -2;
    int result = -2;

    // Sets up audio.
    AudioFormat format = new AudioFormat(16000, 16, 1, true, false);
    DataLine.Info targetInfo = new DataLine.Info(TargetDataLine.class, format);

    // Sets up Snowboy.
    SnowboyDetect detector = new SnowboyDetect("resources/common.res",
                                               "resources/models/snowboy.umdl");
    detector.SetSensitivity("0.5");
    detector.SetAudioGain(1);
    detector.ApplyFrontend(false);

    breakout = new Breakout();
    
    try {
      TargetDataLine targetLine =
        (TargetDataLine) AudioSystem.getLine(targetInfo);
      targetLine.open(format);
      targetLine.start();
      
      // Reads 0.1 second of audio in each call.
      byte[] targetData = new byte[3200];
      short[] snowboyData = new short[1600];
      int numBytesRead;

      while (true) {
        // Reads the audio data in the blocking mode. If you are on a very slow
        // machine such that the hotword detector could not process the audio
        // data in real time, this will cause problem...
        numBytesRead = targetLine.read(targetData, 0, targetData.length);

        if (numBytesRead == -1) {
          System.out.print("Fails to read audio data.");
          break;
        }

        // Converts bytes into int16 that Snowboy will read.
        ByteBuffer.wrap(targetData).order(ByteOrder.LITTLE_ENDIAN).asShortBuffer().get(snowboyData);

        // Detection.
        prev_result = result;
        result = detector.RunDetection(snowboyData, snowboyData.length);
        
        if (result == 0) {
          if (breakout.isJustFinishRestarting) {
            direction = 'l';
            breakout.isJustFinishRestarting = false;

          } else {
            if (result != prev_result) {
              if (direction == 'l') direction = 'r';
              else if (direction == 'r') direction = 'l';
            }
          }
          breakout.panel.paddle.direction = direction;

        } else if (result == -2) {
          breakout.panel.paddle.direction = 's';
        }

      }
    } catch (Exception e) {
      System.err.println(e);
    }
  }
}