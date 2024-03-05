package com.bsc36.project11cs.domain.entities.knapsack;

import com.bsc36.project11cs.domain.entities.parcel.Parcel;
import com.bsc36.project11cs.domain.entities.parcel.ValueParcel;

import java.util.ArrayList;
import java.util.Arrays;

public class KnapsackValue<T extends ValueParcel> extends KnapsackBase<T> {
    protected int parcelsUsed;
    protected int score;

    /**
     * Constructor
     * @param parcel1 ValueParcel 1
     * @param parcel2 ValueParcel 2
     * @param parcel3 ValueParcel 3
     */
    public KnapsackValue(T parcel1, T parcel2, T parcel3) {
        super(parcel1, parcel2, parcel3);
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
     * Place packages with points
     *
     * @param pack int[][][] Package
     */
    private void placePackagesWithPoints(Parcel pack) {
        int[][][] grid = this.cargoSpace.getShape();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) { // Changed from 'i' to 'j'
                for (int k = 0; k < grid[i][j].length; k++) {
                    if (canPlacePackage(i, j, k, pack.getShape())) {
                        // System.out.println("placing package at " + "x: "+ i + " z: " + j + " y: " + k);
                        placePackage(i, j, k, pack.getShape());
                        if (Arrays.deepEquals(pack.getShape(), parcel1.getShape())) {
                            score += parcel1.getValue();
                        }
                        if (Arrays.deepEquals(pack.getShape(), parcel2.getShape())) {
                            score += parcel2.getValue();
                        }
                        if (Arrays.deepEquals(pack.getShape(), parcel3.getShape())) {
                            score += parcel3.getValue();
                        }
                        return;
                    }
                }
            }
        }
    }

    /**
     * Place packages without points
     * @param parcel Parcel
     */
    private void placePackagesWithoutPoints(Parcel parcel) {
        int[][][] grid = this.cargoSpace.getShape();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) { // Changed from 'i' to 'j'
                for (int k = 0; k < grid[i][j].length; k++) {
                    if (canPlacePackage(i, j, k, parcel.getShape())) {
                        // System.out.println("FINAL placing package at " + "x: "+ i + " z: " + j + " y: " + k);
                        placePackage(i, j, k, parcel.getShape());
                        if (Arrays.deepEquals(parcel.getShape(), parcel1.getShape())) {
                            cargoSpace.placeParcel(parcel1, i, j, k);
                        }
                        if (Arrays.deepEquals(parcel.getShape(), parcel2.getShape())) {
                            cargoSpace.placeParcel(parcel2, i, j, k);
                        }
                        if (Arrays.deepEquals(parcel.getShape(), parcel3.getShape())) {
                            cargoSpace.placeParcel(parcel3, i, j, k);
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

    /**
     * Place packages without points
     * @param winningChromosomes Parcel
     */
    public void updateCargoSpace(ArrayList<Parcel> winningChromosomes){
        emptyGrid();
        for(int i = 0; i < winningChromosomes.size(); i++){
            placePackagesWithoutPoints(winningChromosomes.get(i));
        }
        cargoSpace.setShape(this.cargoSpace.getShape());
    }

    /**
     * Chromosome translator
     * @param chromosomes ArrayList<Parcel> Chromosomes
     */
    public void chromosomeTranslator(ArrayList<Parcel> chromosomes) {
        emptyGrid();
        parcelsUsed = 0;
        score = 0;
        for (Parcel chromosome : chromosomes) {
            placePackagesWithPoints(chromosome);
        }
    }

}
