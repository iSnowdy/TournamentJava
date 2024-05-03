package Tournament.Build;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.swing.*;

public class Cron extends JFrame {
    private final ScheduledExecutorService SCHEDULER = Executors.newScheduledThreadPool(1);
    private final int TIMER;

    public Cron(int TIMER) {
        this.TIMER = TIMER;
    }

    public void countDownTimer() {
        System.out.println("Starting Count Down...\n");
        final Runnable RUNNABLE = new Runnable() {
            int countDownStart = TIMER;
            @Override
            public void run() {
                System.out.println(countDownStart);
                countDownStart --;

                if (countDownStart <= 0) {
                    System.out.println("Time is up!");
                    SCHEDULER.shutdown();
                    dispose(); // This is not working :D
                }
            }
        };
        SCHEDULER.scheduleAtFixedRate(RUNNABLE, 0, 1, TimeUnit.SECONDS);
    }
}
