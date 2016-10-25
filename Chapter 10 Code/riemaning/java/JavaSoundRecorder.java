import javax.sound.sampled.*;
import java.io.*;
 
public class JavaSoundRecorder {
    int RECORD_TIME;
    File wavFile; 
    AudioFileFormat.Type fileType = AudioFileFormat.Type.WAVE;
    TargetDataLine line;
    AudioFormat format = setAudioFormat();
    String METHOD;
    boolean running = true;
 
    public JavaSoundRecorder(String _fileName, int _recordTime, String method) {
        this.wavFile = new File(_fileName);
        RECORD_TIME = _recordTime * 1000;
        METHOD = method;
    }
    
    AudioFormat setAudioFormat() {
        return setAudioFormat(16000, 8, 2, true, true);
    }
    AudioFormat setAudioFormat(float sampleRate, int sampleSizeInBits, int channels, boolean signed, boolean bigEndian) {
        AudioFormat format = 
        new AudioFormat(sampleRate, sampleSizeInBits,channels, signed, bigEndian);
        return format;
    }

    void start() {
        try {
            
            DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);
 
            if (!AudioSystem.isLineSupported(info)) {
                throw new RuntimeException("Line not supported");
            }

            line = (TargetDataLine) AudioSystem.getLine(info);
            line.open(format);
            line.start();   // start capturing
            AudioInputStream ais = new AudioInputStream(line);
 
            if(METHOD=="1") {
                System.out.println("Start capturing...");
            
            System.out.println("Start recording...");
            AudioSystem.write(ais, fileType, wavFile);
            } else {

            ByteArrayOutputStream out  = new ByteArrayOutputStream();
            int numBytesRead;
            byte[] data = new byte[line.getBufferSize() / 5];

                while (running) {
                    try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                    numBytesRead =  line.read(data, 0, data.length);
                    out.write(data, 0, numBytesRead);
            //         System.out.println(numBytesRead);
                    System.out.println(">"+line.getLevel());
                }     
                

            }
 
        } catch (Exception ioe) {
            ioe.printStackTrace();
        }
    }

    void finish() {
        running=false;
        line.stop();
        line.close();
        System.out.println("Finished");
    }

    void record() {
        JavaSoundRecorder recorder = this;

        Thread stopper = new Thread(new Runnable() {
            public void run() {
                try {
                    Thread.sleep(RECORD_TIME);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                recorder.finish();
            }
        });
 
        stopper.start();
        this.start();
    }

    public static void main(String[] args) {
        final JavaSoundRecorder recorder = 
            new JavaSoundRecorder(args[0], Integer.parseInt(args[1]), args[2]);
        recorder.setAudioFormat(16000, 8, 2, true, true);
        recorder.record();
    }
}