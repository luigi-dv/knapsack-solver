package com.bsc36.project11cs.domain.entities.parcel;

import com.bsc36.project11cs.domain.entities.parcel.Parcel;

public class ValueParcel extends Parcel {
    private int value;

    public ValueParcel(char type, int value) {
        this.type = type;
        this.value = value;
    }

    /**
     * Get Parcel Value
     * @return int Value Value
     */
    public int getValue() {
        return value;
    }

    /**
     * Set Parcel Value
     * @param value int Value Value
     */
    public void setValue(int value) {
        this.value = value;
    }
}
