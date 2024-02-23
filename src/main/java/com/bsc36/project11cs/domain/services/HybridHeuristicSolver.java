package com.bsc36.project11cs.domain.services;

import com.bsc36.project11cs.domain.entities.knapsack.Knapsack;
import com.bsc36.project11cs.domain.entities.parcel.Parcel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HybridHeuristicSolver {
    private final Knapsack knapsack; // Reference to the Knapsack instance
    public static int cargoLength = 33;
    public static int cargoWidth = 5;
    public static int cargoHeight = 8;


    public HybridHeuristicSolver(Knapsack knapsack) {
        this.knapsack = knapsack;
    }

    /**
     * EntryPoint: Solve the knapsack problem
     */
    public void startParcelSolver() {
        // Create a list of heuristics
        List<String> heuristics = new ArrayList<>();
        heuristics.add("FirstFit");
        heuristics.add("BestFit");
        heuristics.add("NextFit");
        heuristics.add("WorstFit");

        // Shuffle the list to randomize the order of heuristics
        Collections.shuffle(heuristics);

        // Iterate through the knapsack using different heuristics
        for (String currentHeuristic : heuristics) {
            switch (currentHeuristic) {
                case "FirstFit":
                    packItemsFirstFit();
                    break;
                case "BestFit":
                    packItemsBestFit();
                    break;
                case "NextFit":
                    packItemsNextFit();
                    break;
                case "WorstFit":
                    packItemsWorstFit();
                    break;
            }

            // Check if the cargo space is full
            if (knapsack.isCargoSpaceFull()) {
                // Check if the current solution is valid
                if (isValidSolution()) {
                    // Optionally, you can visualize or print the solution
                    return;  // Exit the method if a valid solution is found
                } else {
                    // Clear the grid and try the next heuristic
                    //  Will turn it to objects (same in Knapsack class)
                    knapsack.emptyGrid();
                }
            }
        }

        // If the cargo space is not full, call solve() again
        startParcelSolver();
    }

    private boolean isValidSolution() {
        return knapsack.isEachTypeUsedAtLeastOnce() && knapsack.hasNoGaps();
    }

    /**
     * Pack the items using the First Fit heuristic
     */
    private void packItemsFirstFit() {
        // Retrieve the current parcel character from the input array
        Parcel parcel = knapsack.getNextParcel();
        int parcelID = parcel.getId();
    
        // Iterate through the cargo space
        for (int x = 0; x < cargoLength; x++) {
            for (int y = 0; y < cargoHeight; y++) {
                for (int z = 0; z < cargoWidth; z++) {

                    // Check if it's valid to place the parcel at the current position
                    if (knapsack.canPlaceParcel(parcel.getShape(), x, y, z)) {
                        // If valid, add the parcel to the cargo space
                        knapsack.addParcel(parcel, x, y, z);

                        // Check if the piece introduces a single hole
                        /*if (knapsack.introducesHoles(parcel.getShape(), x, y, z)) {
                            // If it introduces a hole, remove the parcel and continue to the next position
                            knapsack.removeParcel(parcel, x, y, z);
                            continue;
                        }

                        // Check if the piece introduces larger, but not refillable spaces
                        if (knapsack.introducesNotRefillableSpaces(parcel.getShape(), x, y, z)) {
                            // If it introduces not refillable spaces, remove the parcel and continue
                            knapsack.removeParcel(parcel, x, y, z);
                            continue;
                        }*/

                        // Live Display the cargo space with the current placement while resolving

    
                        // Exit the method as the parcel is placed successfully
                        return;
                    }
                }
            }
        }
    }

    public void packItemsBestFit() {
        // Retrieve the current parcel character from the input array
        Parcel parcel = knapsack.getNextParcel();
        int parcelID = parcel.getId();

        // Implement Best Fit heuristic packing logic
        int[] bestFitCoordinates = findBestFitCoordinates(parcel.getShape());

        if (bestFitCoordinates != null) {
            int x = bestFitCoordinates[0];
            int y = bestFitCoordinates[1];
            int z = bestFitCoordinates[2];

            if (knapsack.canPlaceParcel(parcel.getShape(), x, y, z)) {
                knapsack.addParcel(parcel, x, y, z);
                // Display the cargo space with the current placement
                // ...GUI
                return;
            }
        }
    }

    private int[] findBestFitCoordinates(int[][][] parcel) {
        int[] bestFitCoordinates = null;
        int minWastedSpace = Integer.MAX_VALUE;

        for (int x = 0; x < cargoLength; x++) {
            for (int y = 0; y < cargoHeight; y++) {
                for (int z = 0; z < cargoWidth; z++) {
                    int wastedSpace = calculateWastedSpace(parcel, x, y, z);

                    if (wastedSpace < minWastedSpace) {
                        minWastedSpace = wastedSpace;
                        bestFitCoordinates = new int[]{x, y, z};
                    }
                }
            }
        }

        return bestFitCoordinates;
    }

    private int calculateWastedSpace(int[][][] parcel, int x, int y, int z) {
        int wastedSpace = 0;

        for (int i = 0; i < parcel.length; i++) {
            for (int j = 0; j < parcel[i].length; j++) {
                for (int k = 0; k < parcel[i][j].length; k++) {
                    int localX = i;
                    int localY = j;
                    int localZ = k;

                    int newX = x + localX;
                    int newY = y + localY;
                    int newZ = z + localZ;

                    if (!knapsack.isValidPosition(newX, newY, newZ)) {
                        wastedSpace++;
                    }

                    if (knapsack.isValidEmpty(newX, newY, newZ)) {
                        wastedSpace++;
                    }
                }
            }
        }

        return wastedSpace;
    }
    

    private void packItemsNextFit() {
        // Retrieve the current parcel character from the input array
        Parcel parcel = knapsack.getNextParcel();
        int parcelID = parcel.getId();
    
        // Initialize starting coordinates for the current parcel
        int startX = 0;
        int startY = 0;
        int startZ = 0;
    
        // Iterate through the cargo space
        for (int x = startX; x < cargoLength; x++) {
            for (int y = startY; y < cargoHeight; y++) {
                for (int z = startZ; z < cargoWidth; z++) {
    
                    // Check if it's valid to place the parcel at the current position
                    if (knapsack.canPlaceParcel(parcel.getShape(), x, y, z)) {
                        // If valid, add the parcel to the cargo space
                        knapsack.addParcel(parcel, x, y, z);
    
                        // Check if the piece introduces a single hole
                        if (knapsack.introducesHoles(parcel.getShape(), x, y, z)) {
                            // If it introduces a hole, remove the parcel and continue to the next position
                            knapsack.removeParcel(parcel, x, y, z);
                            continue;
                        }
    
                        // Check if the piece introduces larger, but not refillable spaces
                        if (knapsack.introducesNotRefillableSpaces(parcel.getShape(), x, y, z)) {
                            // If it introduces not refillable spaces, remove the parcel and continue
                            knapsack.removeParcel(parcel, x, y, z);
                            continue;
                        }
    
                        // Display the cargo space with the current placement
                        // ...GUI
                        // ...
    
                        // Update starting coordinates for the next parcel
                        startX = x;
                        startY = y;
                        startZ = z;
    
                        // Exit the method as the parcel is placed successfully
                        return;
                    }
                }
                // Reset startZ for the next iteration of the y-loop
                startZ = 0;
            }
            // Reset startY and startZ for the next iteration of the x-loop
            startY = 0;
            startZ = 0;
        }
    }
    

    private void packItemsWorstFit() {
        Parcel parcel = knapsack.getNextParcel();
        int parcelID = parcel.getId();

        //keep track of worst fit position
        int worstFitX = -1;
        int worstFitY = -1;
        int worstFitZ = -1;
        int worstFitSpace = Integer.MIN_VALUE;

        for (int i = 0; i < cargoHeight; i++) {
            for (int j = 0; j < cargoWidth; j++) {
                for (int z = 0; z < cargoLength; z++) {
                    if (knapsack.canPlaceParcel(parcel.getShape(), i, j, z)) {
                        // calculate available space at current position
                        int availableSpace = calculateAvailableSpace(knapsack, parcel.getShape(), i, j, z);

                        // check if it has worst fitter than current worst fit
                        if (availableSpace > worstFitSpace) {
                            worstFitSpace = availableSpace;
                            worstFitX = i;
                            worstFitY = j;
                            worstFitZ = z;
                        }
                    }
                }
            }
        }

        // place parcel at worst fit position
        if (worstFitX != -1 && worstFitY != -1 && worstFitZ != -1) {
            knapsack.addParcel(parcel, worstFitX, worstFitY, worstFitZ);
        }
    }

    // method to calculate available space at a given position
    private int calculateAvailableSpace(Knapsack knapsack, int[][][] parcel, int x, int y, int z) {
        int availableSpace = 0;

        for (int i = 0; i < parcel.length; i++) {
            for (int j = 0; j < parcel[i].length; j++) {
                for (int k = 0; k < parcel[i][j].length; k++) {
                    // coordinates in parcel's local space
                    int localX = i;
                    int localY = j;
                    int localZ = k;

                    // coordinates the cargo space
                    int newX = x + localX;
                    int newY = y + localY;
                    int newZ = z + localZ;

                    // Check for out-of-bounds
                    if (newX >= knapsack.getX() || newY >= knapsack.getY() || newZ >= knapsack.getZ()) {
                        return Integer.MIN_VALUE; //very large negative value for out of bounds
                    }

                    // Check for overlapping
                    if (knapsack.getCargoSpace().getShape()[newX][newY][newZ] == Knapsack.EMPTY_SPACE_CARGOSPACE) {
                        availableSpace++;
                    }
                }
            }
        }

        return availableSpace;
    }
}
