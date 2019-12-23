package pers.georgedon.susutimer.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import pers.georgedon.susutimer.utils.DateUtil;

public class TimerController {

    @FXML
    private Button btnStart;

    @FXML
    private Label labelTime;

    private Long startTime;

    private Long curDuration = 0L;

    private volatile boolean on = true;

    public void onStart() {
        if (null == startTime) {
            startTime = System.currentTimeMillis();
            Thread thd = new Thread(() -> {
                while (true) {
                    if (on) {
                        Platform.runLater(() -> {
                            labelTime.setText(DateUtil.formatDuration(System.currentTimeMillis() - startTime + curDuration));
                        });
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            thd.start();
        }
    }

    public void onStop() {
        if (on){
            on = false;
            curDuration = System.currentTimeMillis() - startTime + curDuration;
        }
    }

    public void onContinue() {
        if (!on){
            on = true;
            startTime = System.currentTimeMillis();
        }
    }
}
