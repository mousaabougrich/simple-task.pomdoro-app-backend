package stage_test.taskmmanagerapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import stage_test.taskmmanagerapp.services.PomodoroService;

@RestController
public class PomodoroController {

    @Autowired
    private PomodoroService pomodoroService;

    @PostMapping("/start")
    public String startPomodoro() {
        pomodoroService.startPomodoro();
        return "Pomodoro session started.";
    }

    @GetMapping("/status")
    public String getPomodoroStatus() {
        return pomodoroService.getStatus();
    }

    @PostMapping("/reset")
    public String resetPomodoro() {
        pomodoroService.resetPomodoro();
        return "Pomodoro session reset.";
    }
}
