package com.bsc36.project11cs.application.services;

import com.bsc36.project11cs.domain.entities.parcel.Parcel;
import com.bsc36.project11cs.domain.entities.knapsack.KnapsackBase;

/**
 * The BackTrack class represents a backtracking algorithm for solving the knapsack problem with parcels.
 * It attempts to find a valid placement for each parcel in the knapsack, considering different rotations.
 */
public class BackTrack {

    private final KnapsackBase<Parcel> knapsack;

    /**
     * Constructor for the BackTrack class.
     *
     * @param knapsack The knapsack to be solved using the backtracking algorithm.
     */
    public BackTrack(KnapsackBase<Parcel> knapsack) {
        this.knapsack = knapsack;
    }

    /**
     * Initiates the parcel solver using the backtracking algorithm.
     * Prints whether a solution is found or not.
     */
    public void startParcelSolver() {
        knapsack.emptyGrid();
        boolean solutionFound = backTrackSearchParcels();
        System.out.println(solutionFound ? "Solution found!" : "No solution found.");
    }

    /**
     * Performs a backtracking search for placing parcels in the knapsack.
     *
     * @return True if a valid placement is found, false otherwise.
     */
    private boolean backTrackSearchParcels() {
        if (knapsack.isCargoSpaceFull()) {
            return true;
        }

        Parcel parcel = knapsack.getRandomParcel();
        int[][][][] rotations = parcel.getRotations();

        for (int[][][] rotatedShape : rotations) {
            parcel.setShape(rotatedShape);

            if (placeParcelInAllPositions(parcel, rotatedShape)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Places the parcel in all possible positions within the knapsack.
     *
     * @param parcel       The parcel to be placed.
     * @param rotatedShape The rotated shape of the parcel.
     * @return True if the parcel is successfully placed, false otherwise.
     */
    private boolean placeParcelInAllPositions(Parcel parcel, int[][][] rotatedShape) {
        for (int x = 0; x < knapsack.getCargoSpace().getLength(); x++) {
            for (int y = 0; y < knapsack.getCargoSpace().getHeight(); y++) {
                for (int z = 0; z < knapsack.getCargoSpace().getWidth(); z++) {

                    if (knapsack.canPlaceParcel(rotatedShape, x, y, z)) {
                        knapsack.placeParcel(parcel, x, y, z);

                        if (knapsack.introducesHoles(rotatedShape, x, y, z)) {
                            knapsack.removeParcel(parcel, x, y, z);
                            continue;
                        }

                        if (backTrackSearchParcels()) {
                            return true;
                        }

                        knapsack.removeParcel(parcel, x, y, z);
                    }
                }
            }
        }

        return false;
    }
}
