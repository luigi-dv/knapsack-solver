package com.bsc36.project11cs.application.visualizer;

import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.geometry.Insets;
import javafx.scene.paint.Color;
import javafx.scene.control.Label;
import javafx.scene.text.FontWeight;

/**
 * The InstructionPanel class represents a visual panel that provides user instructions and displays key information.
 * It is designed to enhance the user experience and improve the visual appeal of the application.
 */
public class InstructionPanel extends VBox {

    /**
     * Constructs an InstructionPanel with relevant information and styling.
     *
     * @param score      The current score displayed in the instruction panel.
     * @param usedParcel1 The count of used Parcel 1 displayed in the instruction panel.
     * @param usedParcel2 The count of used Parcel 2 displayed in the instruction panel.
     * @param usedParcel3 The count of used Parcel 3 displayed in the instruction panel.
     */
    public InstructionPanel(int score, int usedParcel1, int usedParcel2, int usedParcel3) {
        // Set padding and spacing
        this.setPadding(new Insets(20));
        this.setSpacing(15);

        // Set background color and round corners
        this.setBackground(new Background(new BackgroundFill(Color.rgb(230, 230, 255), new CornerRadii(10.0), Insets.EMPTY)));

        // Add instructions

        // Create a new score Label
        Label scoreLabel = createInstructionLabel("Score: " + score, 20, FontWeight.BOLD, Color.DARKBLUE);
        this.getChildren().add(scoreLabel);

        // Create parcel Labels
        Label usedParcel1Label = createInstructionLabel("Used Parcel 1: " + usedParcel1, 16, FontWeight.NORMAL, Color.DARKGREEN);
        Label usedParcel2Label = createInstructionLabel("Used Parcel 2: " + usedParcel2, 16, FontWeight.NORMAL, Color.DARKGREEN);
        Label usedParcel3Label = createInstructionLabel("Used Parcel 3: " + usedParcel3, 16, FontWeight.NORMAL, Color.DARKGREEN);
        this.getChildren().addAll(usedParcel1Label, usedParcel2Label, usedParcel3Label);

        // Create Instruction Labels
        this.getChildren().add(createSectionLabel("Instructions", 18, FontWeight.BOLD, Color.DARKSLATEGRAY));
        this.getChildren().add(createInstructionLabel("W/S: Zoom in/out", 14, FontWeight.NORMAL, Color.DARKSLATEGRAY));
        this.getChildren().add(createInstructionLabel("Q/E: Rotate X-axis", 14, FontWeight.NORMAL, Color.DARKSLATEGRAY));
        this.getChildren().add(createInstructionLabel("A/D: Rotate Y-axis", 14, FontWeight.NORMAL, Color.DARKSLATEGRAY));

        // Limit the size of the VBox to its content
        this.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
    }

    /**
     * Creates a labeled instruction with specified text, font size, font weight, and text color.
     *
     * @param text       The text content of the instruction.
     * @param fontSize   The font size of the instruction text.
     * @param fontWeight The font weight of the instruction text.
     * @param textColor  The color of the instruction text.
     * @return A Label representing the labeled instruction.
     */
    private Label createInstructionLabel(String text, int fontSize, FontWeight fontWeight, Color textColor) {
        Label label = new Label(text);
        label.setFont(Font.font("Arial", fontWeight, fontSize));
        label.setTextFill(textColor);
        label.setPadding(new Insets(5));
        return label;
    }

    /**
     * Creates a labeled section with a specified text, font size, font weight, and text color.
     * Additionally, adds a border at the bottom of the section for visual separation.
     *
     * @param text       The text content of the section label.
     * @param fontSize   The font size of the section label text.
     * @param fontWeight The font weight of the section label text.
     * @param textColor  The color of the section label text.
     * @return A Label representing the labeled section.
     */
    private Label createSectionLabel(String text, int fontSize, FontWeight fontWeight, Color textColor) {
        Label label = createInstructionLabel(text, fontSize, fontWeight, textColor);
        label.setStyle("-fx-border-color: " + toHex(textColor) + "; -fx-border-width: 0 0 2 0;");
        return label;
    }

    /**
     * Converts a JavaFX Color object to its corresponding hexadecimal representation.
     *
     * @param color The JavaFX Color object to be converted.
     * @return A String representing the hexadecimal color code.
     */
    private String toHex(Color color) {
        return String.format("#%02X%02X%02X",
                (int) (color.getRed() * 255),
                (int) (color.getGreen() * 255),
                (int) (color.getBlue() * 255));
    }
}
