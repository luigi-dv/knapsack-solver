package com.bsc36.project11cs.domain.entities.parcel;

import com.bsc36.project11cs.domain.valueobjects.Size;
import javafx.scene.paint.Color;

/**
 * The ValueParcel class represents a parcel with an associated value.
 * It extends the abstract class Parcel and introduces an additional attribute 'value'.
 * This class is suitable for parcels where the value is a significant characteristic.
 */
public class ValueParcel extends Parcel {

    /**
     * Value associated with the parcel
     */
    private int value;

    /**
     * Constructor for ValueParcel initializes the type and value of the parcel.
     *
     * @param type  char Type Value
     * @param value int Value associated with the parcel
     */
    public ValueParcel(char type, int value) {
        // Call the appropriate factory method to create an instance of the subclass
        Parcel parcelInstance = createParcelFromType(type);

        // Initialize common properties
        initCommonProperties(parcelInstance);

        // Set the value associated with the parcel
        this.value = value;
    }

    /**
     * Get the value associated with the parcel.
     *
     * @return int Value associated with the parcel
     */
    public int getValue() {
        return value;
    }

    /**
     * Set the value associated with the parcel.
     *
     * @param value int Value to be set for the parcel
     */
    public void setValue(int value) {
        this.value = value;
    }

    /**
     * Initialize common properties from the provided parcel instance.
     *
     * @param parcelInstance Parcel instance to copy common properties from
     */
    protected void initCommonProperties(Parcel parcelInstance) {
        // Copy common properties from the provided parcel instance
        this.id = parcelInstance.getId();
        this.type = parcelInstance.getType();
        this.name = parcelInstance.getName();
        this.color = parcelInstance.getColor();
        this.size = parcelInstance.getSize();
        this.volume = parcelInstance.getVolume();
        this.shape = parcelInstance.getShape();
        this.rotations = parcelInstance.getRotations();
    }
}
