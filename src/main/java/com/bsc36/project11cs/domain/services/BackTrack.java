package com.bsc36.project11cs.domain.services;

import com.bsc36.project11cs.domain.entities.knapsack.Knapsack;
import com.bsc36.project11cs.domain.entities.parcel.Parcel;

/**
 * BackTrack class
 */
public class BackTrack {
    private final Knapsack knapsack;

    /**
     * Constructor
     * @param knapsack the knapsack to solve
     */
    public BackTrack(Knapsack knapsack) {
        this.knapsack = knapsack;
    }

    /**
     * Start the parcel solver
     */
    public void startParcelSolver() {
        knapsack.emptyGrid();
        boolean solutionFound = backTrackSearchParcels();
        System.out.println(solutionFound ? "Solution found!" : "No solution found.");
    }

    /**
     * Backtrack search for parcels
     * @return true if the parcel is placed, false if the parcel is not placed
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
     * Place the parcel in all possible positions
     * @param parcel the parcel to place
     * @param rotatedShape the rotated shape of the parcel
     * @return true if the parcel is placed, false if the parcel is not placed
     */
    private boolean placeParcelInAllPositions(Parcel parcel, int[][][] rotatedShape) {
        for (int x = 0; x < knapsack.getCargoSpace().getLength(); x++) {
            for (int y = 0; y < knapsack.getCargoSpace().getHeight(); y++) {
                for (int z = 0; z < knapsack.getCargoSpace().getWidth(); z++) {

                    if (knapsack.canPlaceParcel(rotatedShape, x, y, z)) {
                        knapsack.addParcel(parcel, x, y, z);

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
