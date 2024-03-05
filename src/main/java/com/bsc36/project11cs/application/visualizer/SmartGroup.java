package com.bsc36.project11cs.application.visualizer;

import javafx.scene.Group;
import javafx.scene.transform.Rotate;

/**
 * The SmartGroup class extends JavaFX Group to provide additional rotation capabilities.
 * It includes methods for rotating the group around the X and Y axes.
 */
public class SmartGroup extends Group {

    /**
     * Rotates the group around the X-axis.
     *
     * @param angle The angle in degrees by which to rotate the group.
     */
    public void rotateByX(int angle) {
        Rotate rotationX = new Rotate(angle, Rotate.X_AXIS);
        this.getTransforms().add(rotationX);
    }

    /**
     * Rotates the group around the Y-axis.
     *
     * @param angle The angle in degrees by which to rotate the group.
     */
    public void rotateByY(int angle) {
        Rotate rotationY = new Rotate(angle, Rotate.Y_AXIS);
        this.getTransforms().add(rotationY);
    }
}
