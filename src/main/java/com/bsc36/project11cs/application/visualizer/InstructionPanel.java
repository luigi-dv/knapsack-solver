package com.bsc36.project11cs.application.visualizer;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class InstructionPanel extends VBox {

    public InstructionPanel(int score, int usedParcel1, int usedParcel2, int usedParcel3) {
        // Set padding and spacing
        this.setPadding(new Insets(10));
        this.setSpacing(10);

        // Set background color and round corners
        this.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, new CornerRadii(5.0), Insets.EMPTY)));

        // Add instructions

        // Creates a new score Label
        Label scoreLabel = new Label("Score: " + score);
        scoreLabel.setStyle("-fx-font-size: 20px;");
        scoreLabel.setPadding(new Insets(10));
        scoreLabel.setMinWidth(Region.USE_PREF_SIZE);
        scoreLabel.setStyle("-fx-font-weight: bold;");
        this.getChildren().add(scoreLabel);

        // Creates a parcel1 Label
        Label usedParcel1Label = new Label("Used Parcel 1: " + usedParcel1);
        usedParcel1Label.setStyle("-fx-font-size: 14px;");
        usedParcel1Label.setPadding(new Insets(10));
        usedParcel1Label.setMinWidth(Region.USE_PREF_SIZE);
        this.getChildren().add(usedParcel1Label);

        // Creates a parcel2 Label
        Label usedParcel2Label = new Label("Used Parcel 2: " + usedParcel2);
        usedParcel2Label.setStyle("-fx-font-size: 14px;");
        usedParcel2Label.setPadding(new Insets(10));
        usedParcel2Label.setMinWidth(Region.USE_PREF_SIZE);
        this.getChildren().add(usedParcel2Label);

        // Creates a parcel3 Label
        Label usedParcel3Label = new Label("Used Parcel 3: " + usedParcel3);
        usedParcel3Label.setStyle("-fx-font-size: 14px;");
        usedParcel3Label.setPadding(new Insets(10));
        usedParcel3Label.setMinWidth(Region.USE_PREF_SIZE);
        this.getChildren().add(usedParcel3Label);

        // Creates Instruction Labels
        this.getChildren().add(new Label("Instructions:"));
        this.getChildren().add(new Label("W/S: Zoom in/out"));
        this.getChildren().add(new Label("Q/E: Rotate X-axis"));
        this.getChildren().add(new Label("A/D: Rotate Y-axis"));

        // Limit the size of the VBox to its content
        this.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
    }
}

