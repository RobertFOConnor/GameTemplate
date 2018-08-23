package com.yellowbytestudios.game.util;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class TimerHelper {

    private ArrayList<Timer> timers;

    public void initializeTimers() {
        timers = new ArrayList<Timer>();
    }

    public void addTimer(int increment, TimerTask task) {
        Timer t = new Timer();
        t.scheduleAtFixedRate(task, 0, increment);
        timers.add(t);
    }

    public void stopTimers() {
        for (int i = 0; i < timers.size(); i++) {
            timers.get(i).cancel();
        }
    }
}
