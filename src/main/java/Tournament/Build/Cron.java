package Tournament.Build;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.swing.*;

public class Cron extends JFrame {
    private final ScheduledExecutorService SCHEDULER = Executors.newScheduledThreadPool(1);
    private final int TIMER;
    private final JFrame windowCloser; // Only like this will we be able to close other classes windows from Cron

    public Cron(int TIMER, JFrame windowCloser) {
        this.TIMER = TIMER;
        this.windowCloser = windowCloser;
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
                    windowCloser.dispose();
                }
            }
        };
        SCHEDULER.scheduleAtFixedRate(RUNNABLE, 0, 1, TimeUnit.SECONDS);
    }

    public void shutDownTimer() {
        SCHEDULER.shutdown();
        System.out.println("Stopping the Count Down");
    }
}
