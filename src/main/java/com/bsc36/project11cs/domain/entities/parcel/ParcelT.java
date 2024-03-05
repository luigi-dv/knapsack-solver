package com.bsc36.project11cs.domain.entities.parcel;

import com.bsc36.project11cs.domain.valueobjects.Size;
import com.bsc36.project11cs.infrastructure.configuration.BasicConfig;
import javafx.scene.paint.Color;

/**
 * The ParcelT class represents a specific type of parcel (Parcel T).
 * It extends the abstract class Parcel and defines the characteristics
 * and structure of Parcel T, including its ID, type, name, color, size,
 * volume, 3D shape, and rotations.
 */
public class ParcelT extends Parcel {

    /**
     * Constructor for ParcelT initializes the attributes of Parcel T.
     */
    public ParcelT() {
        this.id = 7;
        this.type = 'T';
        this.name = "Parcel T";
        this.color = Color.valueOf(BasicConfig.L_PARCEL_COLOR);
        this.size = new Size(3, 3, 1);
        this.volume = calculateVolume();
        // 3D shape of the parcel
        this.shape = new int[][][] {
                {
                        {1, 1, 1},
                        {0, 1, 0},
                        {0, 1, 0}
                }
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
