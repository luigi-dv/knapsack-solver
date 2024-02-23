package com.bsc36.project11cs.domain.entities.knapsack;


import com.bsc36.project11cs.domain.entities.CargoSpace;

public class KnapsackBase {
    protected final CargoSpace cargoSpace;

    public KnapsackBase(CargoSpace cargoSpace) {
        this.cargoSpace = cargoSpace;
    }

    /**
     * Get the cargo space
     * @return CargoSpace Cargo space
     */
    public CargoSpace getCargoSpace() {
        return cargoSpace;
    }
}