import java.util.Timer;
import java.util.TimerTask;

/**
 * Author: jas chen
 * Date: 2019-05-16
 * Time: 10:11
 * Blog: http://www.rukia.cc
 */
public class TimerTest {

    public static void main(String[] args){
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                System.out.println("fuck java");
            }
        };
        Timer timer = new Timer(true);
        timer.schedule(timerTask,1000,1000);
        while (true){

        }
    }
}
