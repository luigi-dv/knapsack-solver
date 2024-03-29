package com.bsc36.project11cs.domain.entities;

import javafx.scene.shape.Box;
import javafx.scene.paint.Color;
import javafx.scene.shape.DrawMode;
import javafx.scene.paint.PhongMaterial;
import com.bsc36.project11cs.domain.valueobjects.Size;
import com.bsc36.project11cs.domain.entities.parcel.Parcel;
import com.bsc36.project11cs.application.visualizer.SmartGroup;
import com.bsc36.project11cs.infrastructure.configuration.BasicConfig;

/**
 * The CargoSpace class represents the physical space where parcels are placed.
 * It manages the visual representation of the cargo space and provides methods
 * for placing, removing, and updating parcels within the space.
 */
public class CargoSpace {
    private final SmartGroup space;
    private int[][][] shape;
    private final Size size;
    private int score;

    /**
     * Constructor
     * @param size Size of the cargo space
     */
    public CargoSpace(Size size) {
        this.size = size;
        this.shape = new int[size.length()][size.height()][size.width()];

        // Initialize the shape
        for (int x = 0; x < size.length(); x++) {
            for (int y = 0; y < size.height(); y++) {
                for (int z = 0; z < size.width(); z++) {
                    this.shape[x][y][z] = 0;
                }
            }
        }

        // Create the cargo space Box
        Box spaceBox = createSpaceBox();
        // Add the cargo space to the SmartGroup
        this.space = new SmartGroup();
        setupSpace(spaceBox);
    }

    /**
     * Clears the cargo space by removing all visual elements.
     */
    public void clearCargoSpace() {
        this.space.getChildren().clear();
        // Create the cargo space Box
        Box spaceBox = createSpaceBox();
        setupSpace(spaceBox);
    }

    /**
     * Gets the shape of the cargo space.
     *
     * @return The shape of the cargo space.
     */
    public int[][][] getShape() {
      return this.shape;
    }

    /**
     * Sets the shape of the cargo space.
     *
     * @param shape The new shape of the cargo space.
     */
    public void setShape(int[][][] shape) {
      this.shape = shape;
    }

    /**
     * Get the SmartGroup object
     *
     * @return SmartGroup Object
     */
    public SmartGroup getSpace() {
        return this.space;
    }



    /**
     * Get the length of the cargo space
     *
     * @return int Length of the cargo space
     */
    public int getLength() {
        return this.size.length();
    }

    /**
     * Get the width of the cargo space
     *
     * @return int Width of the cargo space
     */
    public int getWidth() {
        return this.size.width();
    }

    /**
     * Get the height of the cargo space
     *
     * @return int Height of the cargo space
     */
    public int getHeight() {
        return this.size.height();
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
        smallBox.setTranslateX(x * BasicConfig.INCREASE_RATIO - this.size.length() * BasicConfig.INCREASE_RATIO / 2.0 + BasicConfig.INCREASE_RATIO / 2.0);
        smallBox.setTranslateY(y * BasicConfig.INCREASE_RATIO - this.size.height() * BasicConfig.INCREASE_RATIO / 2.0 + BasicConfig.INCREASE_RATIO / 2.0);
        smallBox.setTranslateZ(z * BasicConfig.INCREASE_RATIO - this.size.width() * BasicConfig.INCREASE_RATIO / 2.0 + BasicConfig.INCREASE_RATIO / 2.0);
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
    public void placeParcel(Parcel parcel, int x, int y, int z) {
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

    /**
    * Update the state of the cargo space
    * @param newArr New array
    */
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
    * @param spaceBox Box
    */
    private void setupSpace(Box spaceBox) {
        // Add the cargo space Box to the cargo space
        this.space.getChildren().add(spaceBox);
        // Add the small boxes to the cargo space
        for (int x = 0; x < this.size.length(); x++) {
            for (int y = 0; y <  this.size.height(); y++) {
                for (int z = 0; z < this.size.width(); z++) {
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
                this.size.length() * BasicConfig.INCREASE_RATIO,
                this.size.height() * BasicConfig.INCREASE_RATIO,
                this.size.width() * BasicConfig.INCREASE_RATIO
        );
        // Setting the color of the cargo space
        spaceBox.setMaterial(new PhongMaterial(Color.DARKGRAY));
        spaceBox.setDrawMode(DrawMode.LINE);

        return spaceBox;
    }
}