package com.yellowbytestudios.ui;

import com.badlogic.gdx.math.Vector2;
import com.yellowbytestudios.media.Sounds;

public class ToggleButton extends TextButton {

    private boolean isActive;
    private String activeName;
    private String inactiveName;

    public ToggleButton(String activeName, String inactiveName, Vector2 pos, OnTouchListener onTouchListener, boolean isActive) {
        super(activeName, pos, onTouchListener);
        setActiveName(activeName);
        setInactiveName(inactiveName);
        setActive(isActive);
        setName(isActive ? getActiveName() : getInactiveName());
    }

    public void checkTouch(Vector2 touch) {
        if (getBounds().contains(touch)) {
            onTouchListener.onTouch(touch);
            isActive = !isActive;
            setName(isActive ? getActiveName() : getInactiveName());
            Sounds.play("sound/click.wav");
        }
    }

    public String getInactiveName() {
        return inactiveName;
    }

    public void setInactiveName(String inactiveName) {
        this.inactiveName = inactiveName;
    }

    public String getActiveName() {
        return activeName;
    }

    public void setActiveName(String activeName) {
        this.activeName = activeName;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
