package com.bsc36.project11cs.domain.entities.knapsack;

import com.bsc36.project11cs.domain.entities.parcel.Parcel;
import com.bsc36.project11cs.domain.entities.parcel.ValueParcel;

import java.util.ArrayList;
import java.util.Arrays;

public class KnapsackGA extends KnapsackValue {
    private int parcelsUsed;
    private int score;
    private final int[][][] grid = new int[33][8][5];

    /**
     * Constructor
     * @param parcel1 ValueParcel 1
     * @param parcel2 ValueParcel 2
     * @param parcel3 ValueParcel 3
     */
    public KnapsackGA(ValueParcel parcel1, ValueParcel parcel2, ValueParcel parcel3) {
        super(parcel1, parcel2, parcel3);
    }

    /**
     * Get the first parcel
     * @return Parcel
     */
    public Parcel getParcel1(){
        return parcel1;
    }

    /**
     * Get the second parcel
     * @return Parcel
     */
    public Parcel getParcel2(){
        return parcel2;
    }

    /**
     * Get the third parcel
     * @return Parcel
     */
    public Parcel getParcel3(){
        return parcel3;
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

    /**
     * Translate the chromosome to the grid
     *
     * @param chromosomes ArrayList<int[][][]> Chromosomes
     */
    public void chromosomeTranslator(ArrayList<Parcel> chromosomes) {
        emptyGrid();
        parcelsUsed = 0;
        score = 0;
        for (Parcel chromosome : chromosomes) {
            placePackagesWithPoints(chromosome);
        }
    }


    public int[][][] rotateCubeX(int[][][] parcel) {
        int xLength = parcel.length;
        int yLength = parcel[0].length;
        int zLength = parcel[0][0].length;
        int[][][] rotatedParcel = new int[xLength][zLength][yLength];

        for (int x = 0; x < xLength; x++) {
            for (int y = 0; y < yLength; y++) {
                for (int z = 0; z < zLength; z++) {
                    rotatedParcel[x][z][yLength - 1 - y] = parcel[x][y][z];
                }
            }
        }
        return rotatedParcel;
    }

    public int[][][] rotateCubeY(int[][][] parcel) {
        int xLength = parcel.length;
        int yLength = parcel[0].length;
        int zLength = parcel[0][0].length;
        int[][][] rotatedParcel = new int[zLength][yLength][xLength];

        for (int y = 0; y < yLength; y++) {
            for (int x = 0; x < xLength; x++) {
                for (int z = 0; z < zLength; z++) {
                    rotatedParcel[zLength - 1 - z][y][x] = parcel[x][y][z];
                }
            }
        }
        return rotatedParcel;
    }

    public int[][][] rotateCubeZ(int[][][] parcel) {
        int xLength = parcel.length;
        int yLength = parcel[0].length;
        int zLength = parcel[0][0].length;
        int[][][] rotatedParcel = new int[yLength][xLength][zLength];

        for (int z = 0; z < zLength; z++) {
            for (int x = 0; x < xLength; x++) {
                for (int y = 0; y < yLength; y++) {
                    rotatedParcel[yLength - 1 - y][x][z] = parcel[x][y][z];
                }
            }
        }
        return rotatedParcel;
    }
    /**
     * Empty the grid
     */
    private void emptyGrid(){
        for (int[][] ints : this.grid) {
            for (int[] anInt : ints) {
                Arrays.fill(anInt, 0);
            }
        }
    }

    /**
     * Empty the cargo space
     */
    private void emptyCargoSpace() {
        this.cargoSpace.clearCargoSpace();
    }

    /**
     * Place packages with points
     *
     * @param pack int[][][] Package
     */
    private void placePackagesWithPoints(Parcel pack) {
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
    private void placePackagesWithoutPoints(Parcel parcel) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) { // Changed from 'i' to 'j'
                for (int k = 0; k < grid[i][j].length; k++) {
                    if (canPlacePackage(i, j, k, parcel.getShape())) {
                        // System.out.println("FINAL placing package at " + "x: "+ i + " z: " + j + " y: " + k);
                        placePackage(i, j, k, parcel.getShape());
                        if (Arrays.deepEquals(parcel.getShape(), parcel1.getShape())) {
                            cargoSpace.addParcel(parcel1, i, j, k);
                        }
                        if (Arrays.deepEquals(parcel.getShape(), parcel2.getShape())) {
                            cargoSpace.addParcel(parcel2, i, j, k);
                        }
                        if (Arrays.deepEquals(parcel.getShape(), parcel3.getShape())) {
                            cargoSpace.addParcel(parcel3, i, j, k);
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
    private boolean canPlacePackage(int startX, int startZ, int startY, int[][][] pack) {
        if (startX + pack.length > grid.length) {
            // System.out.println("cant place due to x out of bounds");
            return false;
        }
        if (startZ + pack[0].length > grid[0].length) {
            // System.out.println("cant place due to z out of bounds");
            return false;
        }
        if (startY + pack[0][0].length > grid[0][0].length) {
            // System.out.println("cant place due to y out of bounds");
            return false;
        }
        for (int i = 0; i < pack.length; i++) {
            for (int j = 0; j < pack[i].length; j++) {
                for (int k = 0; k < pack[i][j].length; k++) {
                    if (grid[startX + i][startZ + j][startY + k] == 1 && pack[i][j][k] == 1) {
                        // System.out.println("CANT RETURN DUE TO OVERLAP");
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
    private void placePackage(int startX, int startZ, int startY, int[][][] pack) {
        for (int i = 0; i < pack.length; i++) {
            for (int j = 0; j < pack[i].length; j++) {
                for (int k = 0; k < pack[i][j].length; k++) {
                    if (pack[i][j][k] == 1) {
                        grid[startX + i][startZ + j][startY + k] = 1;  // Correct grid update
                    }
                }
            }
        }
        parcelsUsed++;
    }

    public void updateCargoSpace(ArrayList<Parcel> winningChromosomes){
        emptyGrid();
        for(int i = 0; i < winningChromosomes.size(); i++){
            placePackagesWithoutPoints(winningChromosomes.get(i));
        }
        cargoSpace.setShape(grid);
    }
}
