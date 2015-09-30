import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import static java.util.concurrent.TimeUnit.*;
import javax.swing.*;

public class Engine {
    private static final float BASERPM = 13.33f;  // ~800rpm
    private static final float MAXRPM = 116.6f; // ~7000rpm
    private static final byte REACTIONTIME = 100; // milliseconds
    private static final byte REFRESHTIME = 10; //  milliseconds
    private static final float MAXRANGE = MAXRPM - BASERPM;

    private boolean isStarted;

    private float actualRPM;
    public float getActualRPM() {
        return actualRPM;
    }

    private double previousGasPedalPushPercent;
    private double actualGasPedalPushPercent;

    private ScheduledExecutorService refreshExecutorService;
    private Timer startTimer;
    private Timer throttleTimer;

    public Engine() {
        isStarted = false;
        actualRPM = 0;
        previousGasPedalPushPercent = 0;
        refreshExecutorService = Executors.newScheduledThreadPool(1);

        final ActionListener actionListenerStartEngine = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                isStarted = true;
            }
        };
        final ActionListener actionListenerAccelerate = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                Accelerate();
            }
        };
        final ActionListener actionListenerDecelerate = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                Decelerate();
            }
        };

        refreshExecutorService.schedule(new Runnable() {
            @Override
            public void run() {
                if (!isStarted /*&& DriverInput.getEngineToggleButtonState()*/) {
                    startTimer = new Timer(REACTIONTIME, actionListenerStartEngine);
                    startTimer.start();
                } else if (isStarted /*&& !DriverInput.getEngineToggleButtonState()*/) {
                    isStarted = false;
                } else {
                    /*actualGasPedalPushPercent = DriverInput.getGasPedalPushPercent();*/

                    if (previousGasPedalPushPercent < actualGasPedalPushPercent) {
                        throttleTimer = new Timer(REACTIONTIME, actionListenerAccelerate);
                        throttleTimer.start();
                    } else if (previousGasPedalPushPercent > actualGasPedalPushPercent) {
                        throttleTimer = new Timer(REACTIONTIME, actionListenerDecelerate);
                        throttleTimer.start();
                    }
                }
            }
        }, REFRESHTIME, MILLISECONDS);
    }

    private void Accelerate() {

        if (getActualRPM() + 150 > MAXRPM)
        {
            previousGasPedalPushPercent = actualGasPedalPushPercent;
            actualRPM = MAXRPM;
        }
        else if (getActualRPM() + 150 < MAXRPM) {
            previousGasPedalPushPercent = actualGasPedalPushPercent;
            actualRPM = actualRPM + 150;
        }
        else {
            Decelerate();
        }
    }

    private void Decelerate() {
        if (getActualRPM() - 150 < BASERPM)
        {
            previousGasPedalPushPercent = actualGasPedalPushPercent;
            actualRPM = BASERPM;
        }
        else if (getActualRPM() - 150 > BASERPM) {
            previousGasPedalPushPercent = actualGasPedalPushPercent;
            actualRPM = actualRPM - 150;
        }
    }
}