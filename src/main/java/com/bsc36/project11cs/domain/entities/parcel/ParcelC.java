package com.bsc36.project11cs.domain.entities.parcel;

import javafx.scene.paint.Color;
import com.bsc36.project11cs.domain.valueobjects.Size;
import com.bsc36.project11cs.infrastructure.configuration.BasicConfig;


/**
 * The ParcelC class represents a specific type of parcel (Parcel C).
 * It extends the abstract class Parcel and defines the characteristics
 * and structure of Parcel C, including its ID, type, name, color, size,
 * volume, 3D shape, and rotations.
 */
public class ParcelC extends Parcel{

    /**
     * Constructor for ParcelC initializes the attributes of Parcel C.
     */
    public ParcelC() {
        this.id = 3;
        this.type = 'C';
        this.name = "Parcel C";
        this.color = Color.valueOf(BasicConfig.C_PARCEL_COLOR);
        this.size = new Size(3, 3, 3);
        this.volume = calculateVolume();
        // 3D shape of the parcel
        this.shape = new int[][][] {
                {{1, 1, 1}, {1, 1, 1}, {1, 1, 1}},
                {{1, 1, 1}, {1, 1, 1}, {1, 1, 1}},
                {{1, 1, 1}, {1, 1, 1}, {1, 1, 1}}
        };
        // Rotations of the 3D shape
        this.rotations = new int[][][][] {
                this.shape,
                this.rotateX(this.shape),
                this.rotateY(this.shape),
                this.rotateZ(this.shape)
        };
    }
}
