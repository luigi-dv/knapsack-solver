package com.bsc36.project11cs.application.visualizer;

import javafx.scene.Group;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Transform;

/**
 * SmartGroup class
 */
public class SmartGroup extends Group {
    Rotate r = new Rotate();

    Transform t = new Rotate();

    /**
     * Rotate by X
     *
     * @param ang int
     */
    public void rotateByX(int ang) {
        t = new Rotate(ang, Rotate.X_AXIS);
        this.getTransforms().add(t);
    }

    /**
     * Rotate by Y
     *
     * @param ang int
     */
    public void rotateByY(int ang) {
        t = new Rotate(ang, Rotate.Y_AXIS);
        this.getTransforms().add(t);
    }
}
