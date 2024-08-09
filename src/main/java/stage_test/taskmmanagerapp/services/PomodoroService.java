package stage_test.taskmmanagerapp.services;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class PomodoroService {

    private static final long POMODORO_DURATION = 25 * 60 * 1000; // 25 minutes in milliseconds
    private static final long SHORT_BREAK_DURATION = 5 * 60 * 1000; // 5 minutes in milliseconds
    private static final long LONG_BREAK_DURATION = 15 * 60 * 1000; // 15 minutes in milliseconds

    private int pomodoroCount = 0;
    private String status = "Idle";

    @Async
    public void startPomodoro() {
        pomodoroCount++;
        status = "Pomodoro session started. This is session number: " + pomodoroCount;

        long remainingTime = POMODORO_DURATION;

        while (remainingTime > 0) {
            status = "Pomodoro session in progress. Time left: " + formatTime(remainingTime);
            try {
                Thread.sleep(1000); // Sleep for 1 second
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            remainingTime -= 1000;
        }

        if (pomodoroCount % 4 == 0) {
            startLongBreak();
        } else {
            startShortBreak();
        }
    }

    public String getStatus() {
        return status;
    }

    public void resetPomodoro() {
        pomodoroCount = 0;
        status = "Pomodoro session reset.";
    }

    @Async
    protected void startShortBreak() {
        status = "Short break started.";
        long remainingTime = SHORT_BREAK_DURATION;

        while (remainingTime > 0) {
            status = "Short break in progress. Time left: " + formatTime(remainingTime);
            try {
                Thread.sleep(1000); // Sleep for 1 second
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            remainingTime -= 1000;
        }
        status = "Short break ended. Ready for the next Pomodoro.";
    }

    @Async
    protected void startLongBreak() {
        status = "Long break started.";
        long remainingTime = LONG_BREAK_DURATION;

        while (remainingTime > 0) {
            status = "Long break in progress. Time left: " + formatTime(remainingTime);
            try {
                Thread.sleep(1000); // Sleep for 1 second
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            remainingTime -= 1000;
        }
        status = "Long break ended. Ready for the next Pomodoro.";
    }

    private String formatTime(long millis) {
        long minutes = (millis / 1000) / 60;
        long seconds = (millis / 1000) % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }
}
