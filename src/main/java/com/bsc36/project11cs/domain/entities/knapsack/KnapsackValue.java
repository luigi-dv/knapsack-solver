package com.bsc36.project11cs.domain.entities.knapsack;

import com.bsc36.project11cs.domain.entities.parcel.ValueParcel;
import com.bsc36.project11cs.infrastructure.configuration.BasicConfig;

import java.util.Arrays;

public class KnapsackValue extends KnapsackBase{
    protected final ValueParcel parcel1;
    protected final ValueParcel parcel2;
    protected final ValueParcel parcel3;
    protected int parcelsUsed;
    protected int score;

    /**
     * Constructor
     * @param parcel1 ValueParcel 1
     * @param parcel2 ValueParcel 2
     * @param parcel3 ValueParcel 3
     */
    public KnapsackValue(ValueParcel parcel1, ValueParcel parcel2, ValueParcel parcel3) {
        super(BasicConfig.BASIC_CARGO_SPACE);
        // Assign to instance variables
        this.parcel1 = parcel1;
        this.parcel2 = parcel2;
        this.parcel3 = parcel3;
    }

    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Get the score
     * @return int Score
     */
    public int getScore() {
        return score;
    }

    /**
     * Get the parcels used
     * @return int Parcels used
     */
    public int getParcelsUsed() {
        return parcelsUsed;
    }

    /**
     * Set the parcels used
     * @param parcelsUsed int Parcels used
     */
    public void setParcelsUsed(int parcelsUsed) {
        this.parcelsUsed = parcelsUsed;
    }


    public int[][][] rotateCubeX(int[][][] cube) {
        for (int i = 0; i < cube.length / 2; i++) {
            int[][] temp = cube[i];
            cube[i] = cube[cube.length - 1 - i];
            cube[cube.length - 1 - i] = temp;
        }
        return cube;
    }

    public int[][][] rotateCubeY(int[][][] cube) {
        for (int i = 0; i < cube[0].length; i++) {
            for (int j = 0; j < cube[0][0].length / 2; j++) {
                for (int k = 0; k < cube.length; k++) {
                    int temp = cube[k][i][j];
                    cube[k][i][j] = cube[k][i][cube[0][0].length - 1 - j];
                    cube[k][i][cube[0][0].length - 1 - j] = temp;
                }
            }
        }
        return cube;
    }

    public int[][][] rotateCubeZ(int[][][] cube) {
        for (int i = 0; i < cube[0][0].length; i++) {
            for (int j = 0; j < cube.length / 2; j++) {
                for (int k = 0; k < cube[0].length; k++) {
                    int temp = cube[j][k][i];
                    cube[j][k][i] = cube[cube.length - 1 - j][k][i];
                    cube[cube.length - 1 - j][k][i] = temp;
                }
            }
        }
        return cube;
    }
    /**
     * Empty the grid
     */
    private void emptyGrid() {
        this.cargoSpace.clearCargoSpace();
        for (int i = 0; i < cargoSpace.getLength(); i++) {
            for (int j = 0; j < cargoSpace.getHeight(); j++) {
                for (int k = 0; k < cargoSpace.getWidth(); k++) {
                    cargoSpace.getShape()[i][j][k] = 0;
                }
            }
        }
    }

    /**
     * Place packages with points
     *
     * @param pack int[][][] Package
     */
    private void placePackagesWithPoints(int[][][] pack) {
        for (int i = 0; i < cargoSpace.getShape().length; i++) {
            for (int j = 0; j < cargoSpace.getShape()[i].length; j++) { // Changed from 'i' to 'j'
                for (int k = 0; k < cargoSpace.getShape()[i][j].length; k++) {
                    if (canPlacePackage(i, j, k, pack)) {
                        placePackage(i, j, k, pack);
                        if (Arrays.deepEquals(pack, parcel1.getShape())) {
                            cargoSpace.addParcel(parcel1, i, j, k);
                            score += parcel1.getValue();
                        }
                        if (Arrays.deepEquals(pack, parcel2.getShape())) {
                            cargoSpace.addParcel(parcel2, i, j, k);
                            score += parcel2.getValue();
                        }
                        if (Arrays.deepEquals(pack, parcel3.getShape())) {
                            cargoSpace.addParcel(parcel3, i, j, k);
                            score += parcel3.getValue();
                        }
                        return;
                    }
                }
            }
        }
    }

    /**
     * Check if the package can be placed
     * @param startX Start X
     * @param startY Start Y
     * @param startZ Start Z
     * @param pack int[][][] Package
     * @return boolean Can place package
     */
    private boolean canPlacePackage(int startX, int startY, int startZ, int[][][] pack) {
        if (startX + pack.length > cargoSpace.getShape().length) {
            return false;
        }
        if (startY + pack[0].length > cargoSpace.getShape()[0].length) {
            return false;
        }
        if (startZ + pack[0][0].length > cargoSpace.getShape()[0][0].length) {
            return false;
        }
        for (int i = 0; i < pack.length && (startX + i) < cargoSpace.getShape().length; i++) {
            for (int j = 0; j < pack[i].length && (startY + j) < cargoSpace.getShape()[i].length; j++) {
                for (int k = 0; k < pack[i][j].length && (startZ + k) < cargoSpace.getShape()[i][j].length; k++) {
                    if (cargoSpace.getShape()[startX + i][startY + j][startZ + k] == 1 && pack[i][j][k] == 1) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
    /**
     * Place the package
     * @param startX Start X
     * @param startY Start Y
     * @param startZ Start Z
     * @param pack int[][][] Package
     */
    private void placePackage(int startX, int startY, int startZ, int[][][] pack) {
        // System.out.println("Placing package at " + startX + ", " + startY + ", " + startZ);
        for (int i = 0; i < pack.length && (startX + i) < cargoSpace.getShape().length; i++) {
            for (int j = 0; j < pack[i].length && (startY + j) < cargoSpace.getShape()[i].length; j++) {
                for (int k = 0; k < pack[i][j].length && (startZ + k) < cargoSpace.getShape()[i][j].length; k++) {
                    if (pack[i][j][k] == 1) {
                        int[][][] cargoSpaceShape = cargoSpace.getShape();
                        cargoSpaceShape[startX + i][startY + j][startZ + k] = 1;
                        cargoSpace.setShape(cargoSpaceShape);
                    }
                }
            }
        }
        parcelsUsed++;
    }

}
