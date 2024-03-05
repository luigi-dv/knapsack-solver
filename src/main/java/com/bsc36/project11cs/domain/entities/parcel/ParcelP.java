package com.bsc36.project11cs.domain.entities.parcel;

import javafx.scene.paint.Color;
import com.bsc36.project11cs.domain.valueobjects.Size;
import com.bsc36.project11cs.infrastructure.configuration.BasicConfig;


/**
 * The ParcelP class represents a specific type of parcel (Parcel P).
 * It extends the abstract class Parcel and defines the characteristics
 * and structure of Parcel P, including its ID, type, name, color, size,
 * volume, 3D shape, and rotations.
 */
public class ParcelP extends Parcel {

    /**
     * Constructor for ParcelP initializes the attributes of Parcel P.
     */
    public ParcelP() {
        this.id = 6;
        this.type = 'P';
        this.name = "Parcel P";
        this.color = Color.valueOf(BasicConfig.P_PARCEL_COLOR);
        this.size = new Size(2, 3, 1);
        this.volume = calculateVolume();
        // 3D shape of the parcel
        this.shape = new int[][][] {
                {
                    {1, 1, 1},
                    {1, 1, 0}
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
