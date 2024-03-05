package com.bsc36.project11cs.domain.entities.knapsack;

import java.util.List;
import java.util.Random;
import java.util.Arrays;
import java.util.ArrayList;
import com.bsc36.project11cs.domain.entities.CargoSpace;
import com.bsc36.project11cs.domain.entities.parcel.Parcel;
import com.bsc36.project11cs.infrastructure.configuration.BasicConfig;

public class KnapsackBase<T extends Parcel> implements Knapsack {

    // Parcel as a generic type
    protected final T parcel1;
    protected final T parcel2;
    protected final T parcel3;
    // Cargo space
    protected final CargoSpace cargoSpace;
    private int usedTypeA;  // Flag to track if type A is used
    private int usedTypeB;  // Flag to track if type B is used
    private int usedTypeC;  // Flag to track if type C is used
    private final List<T> parcelList;
    private int currentIndex;

    public static final int EMPTY_SPACE_CARGO_SPACE = -1;
    public static final int EMPTY_SPACE_PENTOMINO = 0;

    public KnapsackBase(T parcel1, T parcel2, T parcel3) {
        // Initialize the parcels
        this.parcel1 = parcel1;
        this.parcel2 = parcel2;
        this.parcel3 = parcel3;
        // Initialize the cargo space
        this.cargoSpace = BasicConfig.BASIC_CARGO_SPACE;
        // Initialize the flags
        this.usedTypeA = 0;
        this.usedTypeB = 0;
        this.usedTypeC = 0;
        // Initialize the list of parcels
        this.parcelList = new ArrayList<>();
        this.parcelList.add(parcel1);
        this.parcelList.add(parcel2);
        this.parcelList.add(parcel3);
        // Start with the first parcel
        this.currentIndex = 0;
    }

    /**
     * Get the first parcel
     * @return Parcel 1
     */
    public T getParcel1() {
        return parcel1;
    }

    /**
     * Get the second parcel
     * @return Parcel 2
     */
    public T getParcel2() {
        return parcel2;
    }

    /**
     * Get the third parcel
     * @return Parcel 3
     */
    public T getParcel3() {
        return parcel3;
    }

    /**
     * Get the cargo space
     * @return Cargo space
     */
    public CargoSpace getCargoSpace() {
        return cargoSpace;
    }


    /**
     * Get the used type flags
     * @return int number of used type flags
     */
    public int getUsedTypeA() {
        return usedTypeA;
    }

    /**
     * Get the used type flags
     * @return int number of used type flags
     */
    public int getUsedTypeB() {
        return usedTypeB;
    }

    /**
     * Get the used type flags
     * @return int number of used type flags
     */
    public int getUsedTypeC() {
        return usedTypeC;
    }

    /**
     * Empty the grid:
     * 1. Set all values to 0
     * 2. Reset the flags
     */
    public void emptyGrid(){
        for(int i = 0; i < this.cargoSpace.getLength(); i++){
            for(int j = 0; j < this.cargoSpace.getHeight(); j++){
                for(int k = 0; k < this.cargoSpace.getWidth(); k++){
                    this.cargoSpace.getShape()[i][j][k] = EMPTY_SPACE_CARGO_SPACE;
                }
            }
        }

        // Reset flags when clearing the grid
        usedTypeA = 0;
        usedTypeB = 0;
        usedTypeC = 0;
    }

    /**
     * Get the next parcel in order.
     * If all parcels have been returned, it wraps around to the beginning.
     *
     * @return The next parcel in order.
     */
    public Parcel getNextParcel() {
        Parcel nextParcel = parcelList.get(currentIndex);
        currentIndex = (currentIndex + 1) % parcelList.size(); // Move to the next index, wrap around if necessary
        return nextParcel;
    }


    /**

     Check if the current solution is valid
     @return True if the solution is valid*/
    public Parcel getRandomParcel() {// Array to store the parcel variables
        Parcel[] parcels = {
                this.parcel1,
                this.parcel2,
                this.parcel3};

        // Create an instance of Random
        Random random = new Random();

        // Get a random index from the parcels array
        int randomIndex = random.nextInt(parcels.length);

        // Now, randomParcel contains a random value from {parcelA, parcelB, parcelC}
        return parcels[randomIndex];

    }


    /**
     * Check if a parcel can fit in the cargo space
     *
     * @return True if the parcel can fit, false otherwise
     */
    public boolean isEachTypeUsedAtLeastOnce() {
        return usedTypeA > 0 && usedTypeB > 0 && usedTypeC > 0;
    }

    /**
     * Set the flag for the used type
     *
     * @param type Type
     */
    private void setUsedTypeFlag(int type) {
        switch (type) {
            case 1, 4 -> usedTypeA++;
            case 2, 5 -> usedTypeB++;
            case 3, 6 -> usedTypeC++;
        }
    }

    /**
     * Set the flag for the unused type
     *
     * @param type Type
     */
    private void setUnUsedTypeFlag(int type) {
        switch (type) {
            case 1, 4 -> usedTypeA--;
            case 2, 5 -> usedTypeB--;
            case 3, 6 -> usedTypeC--;
        }

    }


    /**
     * Check if a parcel can be placed in the cargo space
     *
     * @param parcelToPlace Parcel to place
     * @param x             X coordinate
     * @param y             Y coordinate
     * @param z             Z coordinate
     * @return True if the parcel can be placed, false otherwise
     */
    public boolean canPlaceParcel(int[][][] parcelToPlace, int x, int y, int z) {
        for (int i = 0; i < parcelToPlace.length; i++) {
            for (int j = 0; j < parcelToPlace[i].length; j++) {
                for (int k = 0; k < parcelToPlace[i][j].length; k++) {

                    if (parcelToPlace[i][j][k] == EMPTY_SPACE_PENTOMINO) {
                        // We are not interested in these i and j values, they are empty spaces and don't provide collisions
                        continue;
                    }
                    // Coordinates in the cargo space
                    int newX = x + i;
                    int newY = y + j;
                    int newZ = z + k;

                    // Check for out-of-bounds
                    if (newX >= cargoSpace.getShape().length || newY >= cargoSpace.getShape()[0].length || newZ >= cargoSpace.getShape()[0][0].length) {
                        return false;
                    }

                    // Check for overlapping with other parcels
                    if (cargoSpace.getShape()[newX][newY][newZ] != EMPTY_SPACE_CARGO_SPACE) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public void placeParcel(Parcel parcel, int x, int y, int z) {
        System.out.println("Adding parcel " + parcel.getId() + " at position (" + x + ", " + y + ", " + z + ")");
        for (int i = 0; i < parcel.getShape().length; i++) {
            for (int j = 0; j < parcel.getShape()[i].length; j++) {
                for (int k = 0; k < parcel.getShape()[i][j].length; k++) {
                    // Coordinates in the cargo space
                    int newX = x + i;
                    int newY = y + j;
                    int newZ = z + k;

                    // Add the parcel to the cargo space if the parcel occupies this cube
                    if (parcel.getShape()[i][j][k] != EMPTY_SPACE_PENTOMINO) {
                        cargoSpace.getShape()[newX][newY][newZ] = 1;
                    }
                }
            }
        }
        setUsedTypeFlag(parcel.getId());
        this.cargoSpace.placeParcel(parcel, x, y, z);
    }

    public void removeParcel(Parcel parcel, int x, int y, int z) {
        System.out.println("Removing parcel " + parcel.getId() + " at position (" + x + ", " + y + ", " + z + ")");
        for (int i = 0; i < parcel.getShape().length; i++) {
            for (int j = 0; j < parcel.getShape()[i].length; j++) {
                for (int k = 0; k < parcel.getShape()[i][j].length; k++) {
                    // Coordinates in the cargo space
                    int newX = x + i;
                    int newY = y + j;
                    int newZ = z + k;

                    // Remove the parcel from the cargo space if the parcel occupies this cube
                    if (parcel.getShape()[i][j][k] != EMPTY_SPACE_PENTOMINO) {
                        cargoSpace.getShape()[newX][newY][newZ] = EMPTY_SPACE_CARGO_SPACE;
                    }
                }
            }
        }
        setUnUsedTypeFlag(parcel.getId());
        // Cargo Space Remove Parcel
        this.cargoSpace.removeParcel(parcel, x, y, z);
    }


    /**
     * Check if placing the 3D piece introduces holes (which invalidates the solution) - assumes that the piece is already placed in the cargo space!
     *
     * @param piece the 3D piece to be placed
     * @param x     x position of the piece
     * @param y     y position of the piece
     * @param z     z position of the piece
     * @return true if the piece introduces holes, false otherwise
     */
    public boolean introducesHoles(int[][][] piece, int x, int y, int z) {
        System.out.println("Checking introducesHoles at position: (" + x + ", " + y + ", " + z + ")");

        for (int i = 0; i < piece.length; i++) {
            for (int j = 0; j < piece[0].length; j++) {
                for (int k = 0; k < piece[0][0].length; k++) {
                    int newX = x + i;
                    int newY = y + j;
                    int newZ = z + k;

                    if (!isValidPosition(newX, newY, newZ)) {
                        System.out.println("Invalid position at: (" + newX + ", " + newY + ", " + newZ + ")");
                        continue;
                    }

                    if (cargoSpace.getShape()[newX][newY][newZ] != EMPTY_SPACE_CARGO_SPACE) {
                        // This is an empty space that is not enclosed
                        continue;
                    }

                    if(isEnclosed(newX, newY, newZ)){
                        System.out.println("Single holes introduced.");
                        return true;
                    }
                }
            }
        }
        System.out.println("No holes introduced.");
        return false;
    }


    /**
     * Check if placing the 3D piece introduces larger, but not refillable spaces (which invalidates the solution) - assumes that
     * the piece is already placed in the cargo space!
     *
     * @param piece the 3D piece to be placed
     * @param x     x position of the piece
     * @param y     y position of the piece
     * @param z     z position of the piece
     * @return true if the piece introduces larger, but not refillable spaces, false otherwise
     */
    public boolean introducesNotRefillableSpaces(int[][][] piece, int x, int y, int z) {
        int[][][] visited = new int[cargoSpace.getShape().length][cargoSpace.getShape()[0].length][cargoSpace.getShape()[0][0].length];
        cleanCargoSpace(visited);
        List<Parcel> parcels = new ArrayList<>();
        parcels.add(parcel1);
        parcels.add(parcel2);
        parcels.add(parcel3);

        for (int i = 0; i < piece.length; i++) {
            for (int j = 0; j < piece[0].length; j++) {
                for (int k = 0; k < piece[0][0].length; k++) {
                    int newX = x + i;
                    int newY = y + j;
                    int newZ = z + k;

                    if (!isValidPosition(newX, newY, newZ)) {
                        continue;
                    }

                    System.out.println("Cargo Space Value: " + cargoSpace.getShape()[newX][newY][newZ]);
                    System.out.println("Visited Value: " + visited[newX][newY][newZ]);
                    if (cargoSpace.getShape()[newX][newY][newZ] != EMPTY_SPACE_CARGO_SPACE) {
                        continue;
                    }
                    if (visited[newX][newY][newZ] == 1){
                        continue;
                    }

                    int connectedSpaces = countConnectedSpaces(visited, newX, newY, newZ);

                    for (Parcel tempParcel : List.of(parcel1, parcel2, parcel3)) {
                        if (connectedSpaces % tempParcel.getVolume() != 0) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    /**
     * Check if the location x, y, z is enclosed by other pieces (i.e., it's a hole which can't be filled by another parcel)
     *
     * @param x x position
     * @param y y position
     * @param z z position
     * @return true if the position is enclosed, false otherwise
     */
    public boolean isEnclosed(int x, int y, int z) {
        // Above
        if (isValidEmpty(x, y - 1, z)) {
            return false;
        }

        // Below
        if (isValidEmpty(x, y + 1, z)) {
            return false;
        }

        // Left
        if (isValidEmpty(x - 1, y, z)) {
            return false;
        }

        // Right
        if (isValidEmpty(x + 1, y, z)) {
            return false;
        }

        // Front
        if (isValidEmpty(x, y, z - 1)) {
            return false;
        }

        // Back
        return !isValidEmpty(x, y, z + 1);
    }


    /**
     * Helper function to check if a position is valid (not out of bounds)
     *
     * @param x          x position
     * @param y          y position
     * @param z          z position
     * @return true if the position is valid, false otherwise
     */
    public boolean isValidPosition(int x, int y, int z) {
        return x >= 0 && x < cargoSpace.getShape().length && y >= 0 && y < cargoSpace.getShape()[0].length && z >= 0 && z < cargoSpace.getShape()[0][0].length;
    }


    /**
     * Helper function to check if a position is valid and empty (not out of bounds and not occupied by another piece)
     *
     * @param x          x position
     * @param y          y position
     * @param z          z position
     * @return true if the position is valid and empty, false otherwise
     */
    public boolean isValidEmpty(int x, int y, int z) {
        // Invalid position - consider it occupied
        return isValidPosition(x, y, z) && cargoSpace.getShape()[x][y][z] == EMPTY_SPACE_CARGO_SPACE;
    }


    /**
     * Helper function to clean the visited array (set all elements to 0)
     *
     * @param visited the 3D array to be cleaned
     */
    public void cleanCargoSpace(int[][][] visited) {
        for (int[][] ints : visited) {
            for (int j = 0; j < visited[0].length; j++) {
                Arrays.fill(ints[j], EMPTY_SPACE_CARGO_SPACE);
            }
        }
    }

    /**
     * Helper function to count connected empty spaces starting from a given position in the 3D cargo space
     *
     * @param visited array to keep track of visited positions
     * @param x       x position
     * @param y       y position
     * @param z       z position
     * @return the number of connected empty spaces
     */
    public int countConnectedSpaces(int[][][] visited, int x, int y, int z) {
        int connectedSpaces = 0;

        if (!isValidPosition(x, y, z)) {
            return connectedSpaces;
        }

        if (visited[x][y][z] == 1) {
            // We have already visited this cube
            return connectedSpaces;
        }
        visited[x][y][z] = 1;

        if (cargoSpace.getShape()[x][y][z] != EMPTY_SPACE_CARGO_SPACE) {
            // This is not an empty space, so it can't be enclosed
            return connectedSpaces;
        }

        return (1 +
                countConnectedSpaces(visited, x - 1, y, z) +
                countConnectedSpaces(visited, x + 1, y, z) +
                countConnectedSpaces(visited, x, y - 1, z) +
                countConnectedSpaces(visited, x, y + 1, z) +
                countConnectedSpaces(visited, x, y, z - 1) +
                countConnectedSpaces(visited, x, y, z + 1));
    }

    /**
     * Check if the cargo space is full
     *
     * @return True if the cargo space is full, false otherwise
     */
    public boolean isCargoSpaceFull() {
        for (int i = 0; i < cargoSpace.getLength(); i++) {
            for (int j = 0; j < cargoSpace.getHeight(); j++) {
                for (int k = 0; k < cargoSpace.getWidth(); k++) {
                    if (cargoSpace.getShape()[i][j][k] == EMPTY_SPACE_CARGO_SPACE && canPlaceAnyPackage(i, j, k)) {
                        return false; // If there is empty space and a package can fit, cargo space is not full
                    }
                }
            }
        }
        System.out.println(calculateEmptySpaces());
        return true; // All cells are either occupied or can't be filled with a package
    }

    /**
     * Check if any of the parcels can be placed in the cargo space
     *
     * @param startX X coordinate
     * @param startY Y coordinate
     * @param startZ Z coordinate
     * @return True if any of the parcels can be placed, false otherwise
     */
    private boolean canPlaceAnyPackage(int startX, int startY, int startZ) {
        // Iterate through all parcels
        for (Parcel tempParcel : List.of(parcel1, parcel2, parcel3)) {
            int[][][][] rotations = tempParcel.getRotations();
            // Check if the parcel can fit in any rotation at this position
            for (int[][][] rotation : rotations) {
                return canPlaceParcel(rotation, startX, startY, startZ);
            }
        }
        return false;
    }

    /**
     * Calculate the percentage of empty spaces in the cargo space
     *
     * @return Percentage of empty spaces
     */
    public double calculateEmptySpaces() {
        double emptySpaces = 0;

        for (int i = 0; i < cargoSpace.getLength(); i++) {
            for (int j = 0; j < cargoSpace.getHeight(); j++) {
                for (int k = 0; k < cargoSpace.getWidth(); k++) {
                    if (cargoSpace.getShape()[i][j][k] == EMPTY_SPACE_CARGO_SPACE) {
                        emptySpaces++;
                    }
                }
            }
        }

        return (emptySpaces * 100)/ (cargoSpace.getLength() * cargoSpace.getHeight() * cargoSpace.getWidth());
    }
}