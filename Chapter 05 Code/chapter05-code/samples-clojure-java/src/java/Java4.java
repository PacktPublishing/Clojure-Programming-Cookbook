
import bar.ProgressBar;

public class Java4 {

    public static void main(String[] args) throws InterruptedException {
        ProgressBar bar = new ProgressBar(50);
        for (int i = 0; i <= 10; i++) {
            Thread.sleep(600);
            bar.printProcessBar(i * 10);
        }
        System.out.println();
    }
    
}