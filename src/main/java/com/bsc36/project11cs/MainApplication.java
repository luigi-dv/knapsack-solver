package com.bsc36.project11cs;

import com.bsc36.project11cs.domain.entities.knapsack.KnapsackBase;
import com.bsc36.project11cs.domain.entities.knapsack.KnapsackValue;
import com.bsc36.project11cs.domain.entities.parcel.Parcel;
import com.bsc36.project11cs.domain.entities.parcel.ValueParcel;
import javafx.scene.*;
import java.util.Objects;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.geometry.Pos;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.paint.Color;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.transform.Rotate;
import javafx.application.Application;
import javafx.scene.input.ScrollEvent;
import javafx.beans.property.DoubleProperty;
import java.util.concurrent.CompletableFuture;
import javafx.beans.property.SimpleDoubleProperty;
import com.bsc36.project11cs.domain.services.BackTrack;
import com.bsc36.project11cs.domain.services.GeneticAlgo;
import com.bsc36.project11cs.infrastructure.configuration.BasicConfig;
import com.bsc36.project11cs.application.controllers.BaseController;
import com.bsc36.project11cs.domain.entities.*;
import com.bsc36.project11cs.application.visualizer.ArrayVisualization;
import com.bsc36.project11cs.application.visualizer.Gravity;
import com.bsc36.project11cs.application.visualizer.InstructionPanel;
import com.bsc36.project11cs.application.visualizer.SmartGroup;

/**
 * MainApplication class
 */
public class MainApplication extends Application {
    private Stage stage;
    private double anchorX, anchorY;
    private double anchorAngleX = 0;
    private double anchorAngleY = 0;
    private final DoubleProperty angleX = new SimpleDoubleProperty(0);
    private final DoubleProperty angleY = new SimpleDoubleProperty(0);

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Override the start method of the Application class.
     *
     * @param primaryStage the primary stage for this application, onto which
     *                     the application scene can be set.
     *                     Applications may create other stages, if needed, but they will not be
     *                     primary stages.
     */
    @Override
    public void start(Stage primaryStage) throws IOException {
        this.stage = primaryStage;
        // Set the title of the stage
        stage.setTitle("Knapsack Solver | Group 36");
        // Set the stage properties
        stage.setFullScreen(true);
        // Show the menu
        showMenu();
    }

    /**
     * Show a view in the stage
     *
     * @param fxml FXML View File
     * @param css  CSS File
     */
    private void showView(String fxml, String css) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource(fxml));
            Parent root = fxmlLoader.load();

            if (css != null) {
                String cssFile = Objects.requireNonNull(MainApplication.class.getResource(css)).toExternalForm();
                root.getStylesheets().add(cssFile);
            }

            Scene scene = stage.getScene();
            if (scene == null) {
                scene = new Scene(root);
                stage.setFullScreen(true);
                stage.setScene(scene);
            } else {
                stage.setFullScreen(true);
                scene.setRoot(root);
            }

            BaseController sceneController = fxmlLoader.getController();
            sceneController.setStage(stage);
            sceneController.setMain(this);

            // Show the stage after configuring its properties
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Show the Menu View in the stage
     */
    public void showMenu() {
        showView("views/menu-view.fxml", "css/menu.css");
    }

    /**
     * Show a view in the stage
     */
    public void showSolver() throws IOException {
        ArrayVisualization visualization = new ArrayVisualization();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("views/solver-view.fxml"));
        String cssFile = Objects.requireNonNull(MainApplication.class.getResource("css/solver.css")).toExternalForm();
        visualization.init(stage, loader, cssFile);
    }

    /**
     * Show a view in the stage
     */
    public void showParcelsView() {
        showView("views/parcels-view.fxml", "css/parcels.css");
    }

    /**
     * Show a view in the stage
     */
    public void showValueParcelsView() {
        showView("views/value-parcels-view.fxml", "css/value-parcels.css");
    }

    /**
     * Show a view in the stage
     */
    public void showLoadingView() {
        showView("views/loading-view.fxml", "css/loading.css");
    }

    /**
     * Show a view in the stage
     *
     * @param knapsack Knapsack Object
     */
    public void showCargoSpaceBacktrack(KnapsackBase<Parcel> knapsack) {
        // Get the cargo space
        CargoSpace cargoSpace = knapsack.getCargoSpace();

        // Create new BackTrack Solver
        BackTrack solver = new BackTrack(knapsack);

        // Show loading screen
        showLoadingView();

        // Start ParcelSolver asynchronously
        CompletableFuture<Void> future = CompletableFuture.runAsync(solver::startParcelSolver);

        // Use thenAccept to specify what should happen when the future completes
        future.thenAccept(result -> {
            // Update UI on JavaFX Application Thread
            Platform.runLater(() -> {

                // Set the group
                SmartGroup group = cargoSpace.getSpace();

                cargoSpaceSetup(
                        group,
                        0,
                        knapsack.getUsedTypeA(),
                        knapsack.getUsedTypeB(),
                        knapsack.getUsedTypeC()
                );

                // Check if the stage is already showing before trying to show it
                if (!stage.isShowing()) {
                    // Show the stage
                    stage.show();
                }
            });
        });
    }

    /**
     * Show a view in the stage
     * @param knapsack Knapsack Object
     */
    public void showCargoSpaceValues(KnapsackValue<ValueParcel> knapsack) {
        // Get the cargo space
        CargoSpace cargoSpace = knapsack.getCargoSpace();

        // Create new BackTrack Solver
        GeneticAlgo solver = new GeneticAlgo(knapsack);

        // Show loading screen
        showLoadingView();
        // solver.run();
        // Start ParcelSolver asynchronously
        CompletableFuture<Void> future = CompletableFuture.runAsync(solver::run);

        // Use thenAccept to specify what should happen when the future completes
        future.thenAccept(result -> {
            //     // Update UI on JavaFX Application Thread
            Platform.runLater(() -> {

                // Set the group
                SmartGroup group = cargoSpace.getSpace();

                cargoSpaceSetup(group, knapsack.getScore(), 0,0,0);

                // Check if the stage is already showing before trying to show it
                if (!stage.isShowing()) {
                    // Show the stage
                    stage.show();
                }
            });
        });
    }

    /**
     * Show a view in the stage
     */
    public void showEmptyCargoSpaceView() {
        SmartGroup group = BasicConfig.BASIC_CARGO_SPACE.getSpace();
        cargoSpaceSetup(group, 0, 0,0,0);

        // TODO: Implement Dynamic Parcel Creation and Visualization
        // Instantiate a Parcel object and set its type
        //Parcel parcel = Parcel.createParcelFromType('U');

        // Set the parcel in the cargo space
        // BasicConfig.BASIC_CARGO_SPACE.placeParcel(parcel, 0, 0, 0);
    }



    /**
     * Exit the program
     */
    public void exit() {
        stage.close();
    }

    /**
     * Group setup
     *
     * @param group SmartGroup
     */
    private void cargoSpaceSetup(SmartGroup group, int score, int usedParcel1, int usedParcel2, int usedParcel3) {
        Scene scene = stage.getScene();

        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("css/cargo-space.css")).toExternalForm());

        // Create a StackPane as the root node
        StackPane root = new StackPane();
        scene.setRoot(root);
        scene.setFill(Color.SILVER);

        // Create a new camera
        Camera camera = new PerspectiveCamera();
        scene.setCamera(camera);

        // Create an InstructionPanel and add it to the root
        InstructionPanel instructions = new InstructionPanel(score, usedParcel1, usedParcel2, usedParcel3);

        // Set the alignment of the InstructionPanel to top corner (e.g., top right)
        StackPane.setAlignment(instructions, Pos.TOP_LEFT);

        // Add the instruction panel to the root
        root.getChildren().add(instructions);

        // Reset the group's translate properties
        group.setTranslateX(0);
        group.setTranslateY(0);
        group.setTranslateZ(0);

        // Initialize gravity
        Gravity gravity = new Gravity(group);

        root.getChildren().add(group);

        // Initialize the mouse control
        initMouseControl(group, scene);

        stage.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            switch (event.getCode()) {
                case W -> group.translateZProperty().set(group.getTranslateZ() + 100);
                case S -> group.translateZProperty().set(group.getTranslateZ() - 100);
                case Q -> group.rotateByX(10);
                case E -> group.rotateByX(-10);
                case A -> group.rotateByY(10);
                case D -> group.rotateByY(-10);
            }
        });

        // Create a new Menu Button
        Button menuButton = new Button("Menu");
        menuButton.setOnAction(e -> {
            // Stop the gravity
            if(gravity.isRunning()){
                gravity.stop();
            }
            // Show the menu
            showMenu();
        });

        Button gravityButton = new Button("⏯");
        gravityButton.setOnAction(e -> {
            if(gravity.isRunning()){
                gravityButton.getStyleClass().remove("button-red");
                gravityButton.getStyleClass().add("button-common");
                gravity.stop();
            }
            else{
                gravity.start();
                gravityButton.getStyleClass().remove("button-common");
                gravityButton.getStyleClass().add("button-red");
            }
        });

        // Apply the CSS class to your button
        menuButton.getStyleClass().add("button-common");
        gravityButton.getStyleClass().add(gravity.isRunning() ? "button-red" : "button-common");
        // Set the alignment of the button to bottom center
        StackPane.setAlignment(menuButton, Pos.BOTTOM_CENTER);
        StackPane.setAlignment(gravityButton, Pos.TOP_RIGHT);
        // Set the margin of the button to 10px
        StackPane.setMargin(menuButton, new Insets(0, 0, 10, 0));
        StackPane.setMargin(gravityButton, new Insets(10, 5, 0, 0));
        // Add the buttons to the root
        root.getChildren().add(menuButton);
        root.getChildren().add(gravityButton);
    }

    /**
     * Initialize the mouse control
     *
     * @param group Group
     * @param scene Scene
     */
    private void initMouseControl(SmartGroup group, Scene scene) {
        Rotate rotateX;
        Rotate rotateY;

        group.getTransforms().addAll(
                rotateX = new Rotate(0, Rotate.X_AXIS),
                rotateY = new Rotate(0, Rotate.Y_AXIS)
        );

        rotateX.angleProperty().bind(angleX);
        rotateY.angleProperty().bind(angleY);

        scene.setOnMousePressed(event -> {
            anchorX = event.getSceneX();
            anchorY = event.getSceneY();
            anchorAngleX = rotateX.getAngle();
            anchorAngleY = rotateY.getAngle();
        });

        scene.setOnMouseDragged(event -> {
            angleX.set(anchorAngleX - (anchorY - event.getSceneY()));
            angleY.set(anchorAngleY + (anchorX - event.getSceneX()));
        });

        scene.addEventHandler(ScrollEvent.SCROLL, event -> {
            double delta = event.getDeltaY();
            group.translateZProperty().set(group.getTranslateZ() + delta);
        });
    }

}