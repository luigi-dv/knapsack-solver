package com.bsc36.project11cs.domain.entities.parcel;

import javafx.scene.paint.Color;
import com.bsc36.project11cs.domain.valueobjects.Size;
import com.bsc36.project11cs.infrastructure.configuration.BasicConfig;


/**
 * The ParcelB class represents a specific type of parcel (Parcel B).
 * It extends the abstract class Parcel and defines the characteristics
 * and structure of Parcel B, including its ID, type, name, color, size,
 * volume, 3D shape, and rotations.
 */
public class ParcelB extends Parcel{

    /**
     * Constructor for ParcelB initializes the attributes of Parcel B.
     */
    public ParcelB() {
        this.id = 2;
        this.type = 'B';
        this.name = "Parcel B";
        this.color = Color.valueOf(BasicConfig.B_PARCEL_COLOR);
        this.size = new Size(2, 3, 4);
        this.volume = calculateVolume();
        // 3D shape of the parcel
        this.shape = new int[][][] {
                {{1, 1}, {1, 1}, {1, 1}, {1, 1}},
                {{1, 1}, {1, 1}, {1, 1}, {1, 1}},
                {{1, 1}, {1, 1}, {1, 1}, {1, 1}}
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
