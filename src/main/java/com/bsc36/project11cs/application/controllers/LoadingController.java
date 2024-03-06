package com.bsc36.project11cs.application.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ProgressIndicator;

import java.util.concurrent.CompletableFuture;

public class LoadingController extends BaseController {

    // Use @FXML to inject the ProgressIndicator defined in the FXML
    @FXML
    private ProgressIndicator progressIndicator;

    /**
     * Initialize the controller
     */
    public void initialize() {
        // Start ParcelSolver asynchronously
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            // Simulating some task
            for (int[] progress = {0}; progress[0] <= 100; progress[0]++) {
                // Update the ProgressIndicator on the JavaFX Application Thread
                javafx.application.Platform.runLater(() -> progressIndicator.setProgress(progress[0] / 100.0));

                try {
                    // Simulate a time-consuming task
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
