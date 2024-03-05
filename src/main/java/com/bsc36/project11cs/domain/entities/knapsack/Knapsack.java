package com.bsc36.project11cs.domain.entities.knapsack;

import com.bsc36.project11cs.domain.entities.CargoSpace;
import com.bsc36.project11cs.domain.entities.parcel.Parcel;

public interface Knapsack {
    Parcel getParcel1();
    Parcel getParcel2();
    Parcel getParcel3();
    CargoSpace getCargoSpace();

    /**
     * Place a parcel in the knapsack
     * @param parcel the parcel to place
     * @param x the x coordinate
     * @param y the y coordinate
     * @param z the z coordinate
     */
    void placeParcel(Parcel parcel, int x, int y, int z);

    /**
     * Remove a parcel from the knapsack
     * @param parcel the parcel to remove
     * @param x the x coordinate
     * @param y the y coordinate
     * @param z the z coordinate
     */
    void removeParcel(Parcel parcel, int x, int y, int z);

    /**
     * Check if the cargo space is full
     * @return true if the cargo space is full, false if the cargo space is not full
     */
    boolean isCargoSpaceFull();

    /**
     * Get the number of parcels type A used
     */
    int getUsedTypeA();

    /**
     * Get the number of parcels type B used
     */
    int getUsedTypeB();

    /**
     * Get the number of parcels type C used
     */
    int getUsedTypeC();
}