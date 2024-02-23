package com.bsc36.project11cs.domain.entities;

import com.bsc36.project11cs.domain.entities.parcel.Parcel;
import com.bsc36.project11cs.infrastructure.configuration.BasicConfig;
import com.bsc36.project11cs.application.visualizer.SmartGroup;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.DrawMode;

public class CargoSpace {
    private final SmartGroup space;
    private int[][][] shape;
    private final int width;
    private final int height;
    private final int length;
    private int score;

    /**
     * Constructor
     * @param length Length
     * @param height Height
     * @param width Width
     */
    public CargoSpace(int length, int height, int width) {
        this.length = length;
        this.height = height;
        this.width = width;

        this.shape = new int[length][height][width];

        // Initialize the shape
        for (int x = 0; x < length; x++) {
            for (int y = 0; y < height; y++) {
                for (int z = 0; z < width; z++) {
                    this.shape[x][y][z] = 0;
                }
            }
        }

        Box spaceBox = createSpaceBox();

        // Add the cargo space to the SmartGroup
        this.space = new SmartGroup();
        setupSpace(length, height, width, spaceBox);
    }

    /**
     * Clear the cargo space
     */
    public void clearCargoSpace() {
        this.space.getChildren().clear();
        // Create the cargo space Box
        Box spaceBox = createSpaceBox();
        setupSpace(this.length, this.height, this.width, spaceBox);
    }

    /**
     * Get the shape of the cargo space
     * @return int[][][] Shape of the cargo space
     */
    public int[][][] getShape() {
      return this.shape;
    }

    /**
     * Set the shape of the cargo space
     * @param shape Shape of the cargo space
     */
    public void setShape(int[][][] shape) {
      this.shape = shape;
    }

    /**
     * Get the SmartGroup object
     * @return SmartGroup Object
     */
    public SmartGroup getSpace() {
        return this.space;
    }

    /**
     * Get the width of the cargo space
     * @return int Width of the cargo space
     */
    public int getWidth() {
        return width;
    }

    /**
     * Get the height of the cargo space
     * @return int Height of the cargo space
     */
    public int getHeight() {
        return height;
    }

    /**
     * Get the length of the cargo space
     * @return int Length of the cargo space
     */
    public int getLength() {
        return length;
    }

    /**
     * Get a small box
     * @param x X coordinate
     * @param y Y coordinate
     * @param z Z coordinate
     * @return Box
     */
    private Box getSmallBox(int x, int y, int z) {
        Box smallBox = new Box(BasicConfig.INCREASE_RATIO, BasicConfig.INCREASE_RATIO, BasicConfig.INCREASE_RATIO);
        smallBox.setTranslateX(x * BasicConfig.INCREASE_RATIO - this.length * BasicConfig.INCREASE_RATIO / 2.0 + BasicConfig.INCREASE_RATIO / 2.0);
        smallBox.setTranslateY(y * BasicConfig.INCREASE_RATIO - this.height * BasicConfig.INCREASE_RATIO / 2.0 + BasicConfig.INCREASE_RATIO / 2.0);
        smallBox.setTranslateZ(z * BasicConfig.INCREASE_RATIO - this.width * BasicConfig.INCREASE_RATIO / 2.0 + BasicConfig.INCREASE_RATIO / 2.0);
        // Use different colors for the small boxes based on their position
        if ((x + y + z) % 2 == 0) {
            smallBox.setMaterial(new PhongMaterial(Color.LIGHTGRAY));
        } else {
            smallBox.setMaterial(new PhongMaterial(Color.DARKGRAY));
        }
        return smallBox;
    }

    /**
     * Add a parcel to the cargo space
     * @param parcel Parcel
     * @param x X coordinate
     * @param y Y coordinate
     * @param z Z coordinate
     */
    public void addParcel(Parcel parcel, int x, int y, int z) {
        int[][][] shape = parcel.getShape();
        // Manage the color by the id
        for (int i = 0; i < shape.length; i++) {
            for (int j = 0; j < shape[0].length; j++) {
                for (int k = 0; k < shape[0][0].length; k++) {
                    if (shape[i][j][k] == 1) {
                        Box smallBox = getSmallBox(x + i, y + j, z + k);
                        smallBox.setMaterial(
                                new PhongMaterial(parcel.getColor())
                        );
                        // Update the scene in the JavaFX Application thread
                        this.space.getChildren().add(smallBox);
                    }
                }
            }
        }
    }

    /**
     * Remove a parcel from the cargo space
     * @param parcel Parcel
     * @param x X coordinate
     * @param y Y coordinate
     * @param z Z coordinate
     */
    public void removeParcel(Parcel parcel, int x, int y, int z) {
        int[][][] shape = parcel.getShape();
        // Manage the color by the id
        for (int i = 0; i < shape.length; i++) {
            for (int j = 0; j < shape[0].length; j++) {
                for (int k = 0; k < shape[0][0].length; k++) {
                    if (shape[i][j][k] == 1) {
                        Box smallBox = getSmallBox(x + i, y + j, z + k);
                        // Update the scene in the JavaFX Application thread
                        this.space.getChildren().add(smallBox);
                    }
                }
            }
        }
    }

    /**
     * Get the score of the cargo space
     * @return int Score of the cargo space
     */
    public int getScore() {
        return score;
    }

    /**
     * Set the score of the cargo space
     * @param score Score of the cargo space
     */
    public void setScore(int score) {
        this.score = score;
    }

    public void updateState(int[][][] newArr) {
        space.getChildren().removeAll(space.getChildren());
        placeBox(newArr);
    }

    /**
     * Place the box
     * @param newArr New array
     */
   private void placeBox(int[][][] newArr) {
        for (int i = 0; i < newArr.length; i++) {
            for (int j = 0; j < newArr[0].length; j++) {
                for (int k = 0; k < newArr[0][0].length; k++) {
                    if (newArr[i][j][k] != 0) {
                        System.out.println("i: " + i + " j: " + j + " k: " + k);
                        setBox(i, j, k, newArr[i][j][k]);
                    }
                }
            }
        }
    }

    /**
     * Set up the cargo space
     * @param length Length of the cargo space
     * @param height Height of the cargo space
     * @param width Width of the cargo space
     * @param spaceBox Box
     */
    private void setupSpace(int length, int height, int width, Box spaceBox) {
        // Add the cargo space Box to the cargo space
        this.space.getChildren().add(spaceBox);
        // Add the small boxes to the cargo space
        for (int x = 0; x < length; x++) {
            for (int y = 0; y < height; y++) {
                for (int z = 0; z < width; z++) {
                    Box smallBox = getSmallBox(x, y, z);
                    this.space.getChildren().add(smallBox);
                }
            }
        }
    }

    /**
     * Set a box
     * @param x X coordinate
     * @param y Y coordinate
     * @param z Z coordinate
     * @param id Parcel's ID
     */
    private void setBox(int x, int y, int z, int id) {
        for (int i = 0; i < shape.length; i++) {
            for (int j = 0; j < shape[0].length; j++) {
                for (int k = 0; k < shape[0][0].length; k++) {
                    if (shape[i][j][k] == 1) {
                        Box smallBox = getSmallBox(x + i, y + j, z + k);
                        Parcel parcel = getParcelFromId(id);
                        assert parcel != null;
                        smallBox.setMaterial(new PhongMaterial(parcel.getColor()));
                        // Update the scene in the JavaFX Application thread
                        this.space.getChildren().add(smallBox);
                    }
                }
            }
        }
    }

    /**
     * Get the parcel from the id
     * @param id Parcel's ID
     * @return Parcel
     */
    private Parcel getParcelFromId(int id) {
       return Parcel.createParcelFromId(id);
    }

    /**
     * Create the cargo space Box
     * @return Box
     */
    private Box createSpaceBox() {
        // Create the cargo space Box
        Box spaceBox = new Box(
                this.length * BasicConfig.INCREASE_RATIO,
                this.height * BasicConfig.INCREASE_RATIO,
                this.width * BasicConfig.INCREASE_RATIO
        );

        // Setting the color of the cargo space
        spaceBox.setMaterial(new PhongMaterial(Color.DARKGRAY));
        spaceBox.setDrawMode(DrawMode.LINE);

        return spaceBox;
    }
}