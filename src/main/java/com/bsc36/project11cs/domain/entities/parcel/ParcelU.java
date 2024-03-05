package com.bsc36.project11cs.domain.entities.parcel;

import javafx.scene.paint.Color;
import com.bsc36.project11cs.domain.valueobjects.Size;
import com.bsc36.project11cs.infrastructure.configuration.BasicConfig;


/**
 * The ParcelU class represents a specific type of parcel (Parcel U).
 * It extends the abstract class Parcel and defines the characteristics
 * and structure of Parcel U, including its ID, type, name, color, size,
 * volume, 3D shape, and rotations.
 */
public class ParcelU extends Parcel {

    /**
     * Constructor for ParcelU initializes the attributes of Parcel U.
     */
    public ParcelU() {
        this.id = 8;
        this.type = 'U';
        this.name = "Parcel U";
        this.color = Color.valueOf(BasicConfig.U_PARCEL_COLOR);
        this.size = new Size(2, 4, 1);
        this.volume = calculateVolume();
        // 3D shape of the parcel
        this.shape = new int[][][] {
                {
                    {1, 0, 0, 1},
                    {1, 1, 1, 1}
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
