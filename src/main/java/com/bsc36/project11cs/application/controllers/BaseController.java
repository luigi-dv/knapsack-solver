package com.bsc36.project11cs.application.controllers;

import javafx.stage.Stage;
import com.bsc36.project11cs.MainApplication;

/**
 * Base controller class for managing JavaFX application views.
 * Provides methods to set the main stage and reference to the MainApplication instance.
 */
public class BaseController {
    /**
     * The main stage for the JavaFX application.
     */
    public Stage stage;

    /**
     * The MainApplication instance associated with this controller.
     */
    public MainApplication main;

    /**
     * Sets the main stage for the controller.
     *
     * @param stage The JavaFX Stage object to set.
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * Sets the MainApplication instance for the controller.
     *
     * @param mainApp The MainApplication object to set.
     */
    public void setMain(MainApplication mainApp) {
        this.main = mainApp;
    }
}
