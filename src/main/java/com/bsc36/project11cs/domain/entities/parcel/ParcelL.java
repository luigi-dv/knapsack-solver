package com.bsc36.project11cs.domain.entities.parcel;

import javafx.scene.paint.Color;
import com.bsc36.project11cs.domain.valueobjects.Size;
import com.bsc36.project11cs.infrastructure.configuration.BasicConfig;


/**
 * The ParcelL class represents a specific type of parcel (Parcel L).
 * It extends the abstract class Parcel and defines the characteristics
 * and structure of Parcel L, including its ID, type, name, color, size,
 * volume, 3D shape, and rotations.
 */
public class ParcelL extends Parcel{

    /**
     * Constructor for ParcelL initializes the attributes of Parcel L.
     */
    public ParcelL() {
        this.id = 4;
        this.type = 'L';
        this.name = "Parcel L";
        this.color = Color.valueOf(BasicConfig.L_PARCEL_COLOR);
        this.size = new Size(1, 4, 2);
        this.volume = calculateVolume();
        // 3D shape of the parcel
        this.shape = new int[][][] {
                {{1, 0}},
                {{1, 0}},
                {{1, 0}},
                {{1, 1}}
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
