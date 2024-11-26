package org.firstinspires.ftc.teamcode.helpers;


import java.util.ArrayList;
import java.util.List;

public class EdgeDetector {

    private final List<Runnable> onPress = new ArrayList<>();
    private final List<Runnable> onRelease = new ArrayList<>();

    private boolean state;

    public EdgeDetector(boolean initialState) {
        this.state = initialState;
    }

    public void update(boolean newState) {

        // Update the state variable early on
        // To make calls to getter return the correct variable
        // When calling from a lambda function
        boolean oldState = state;
        state = newState;

        // Notify event handlers
        if (newState && !oldState) {
            onPress.forEach(Runnable::run);
        }
        if (!newState && oldState) {
            onRelease.forEach(Runnable::run);
        }

    }

    void onPress(Runnable action) {
        onPress.add(action);
    }

    void onRelease(Runnable action) {
        onRelease.add(action);
    }

    public boolean state() {
        return state;
    }

}
