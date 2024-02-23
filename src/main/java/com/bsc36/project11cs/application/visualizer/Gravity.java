package com.bsc36.project11cs.application.visualizer;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

/**
 * Gravity class
 */
public class Gravity {

    private final Timeline rotationTimeline;
    private boolean running;

    /**
     * Gravity constructor
     *
     * @param group SmartGroup Group
     */
    public Gravity(SmartGroup group) {
        Rotate rotate = new Rotate(0, Rotate.Y_AXIS);
        group.getTransforms().add(rotate);

        KeyValue kvRotate = new KeyValue(rotate.angleProperty(), 360);
        KeyFrame kfRotate = new KeyFrame(Duration.millis(20000), kvRotate);
        rotationTimeline = new Timeline(kfRotate);
        rotationTimeline.setCycleCount(Timeline.INDEFINITE);

        group.setOnMousePressed(event -> {
            if (isRunning()) {
                Platform.runLater(rotationTimeline::pause);
            }
        });

        group.setOnMouseReleased(event -> {
            if (isRunning()) {
                Platform.runLater(rotationTimeline::play);
            }
        });
    }

    /**
     * Start the gravity
     */
    public void start() {
        this.running = true;
        Platform.runLater(rotationTimeline::play);
    }

    /**
     * Stop the gravity
     */
    public void stop() {
        this.running = false;
        Platform.runLater(rotationTimeline::stop);
    }

    /**
     * Check if the gravity is running
     *
     * @return boolean Is running
     */
    public boolean isRunning() {
        return this.running;
    }
}

