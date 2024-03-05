package com.bsc36.project11cs.domain.entities.parcel;

import javafx.scene.paint.Color;
import com.bsc36.project11cs.domain.valueobjects.Size;
import com.bsc36.project11cs.infrastructure.configuration.BasicConfig;


/**
 * The ParcelA class represents a specific type of parcel (Parcel A).
 * It extends the abstract class Parcel and defines the characteristics
 * and structure of Parcel A, including its ID, type, name, color, size,
 * volume, 3D shape, and rotations.
 */
public class ParcelA extends Parcel {

    /**
     * Constructor for ParcelA initializes the attributes of Parcel A.
     */
    public ParcelA() {
        this.id = 1;
        this.type = 'A';
        this.name = "Parcel A";
        this.color = Color.valueOf(BasicConfig.A_PARCEL_COLOR);
        this.size = new Size(2, 2, 4);
        this.volume = calculateVolume();

        // 3D shape of the parcel
        this.shape = new int[][][] {
                {{1, 1}, {1, 1}},
                {{1, 1}, {1, 1}},
                {{1, 1}, {1, 1}},
                {{1, 1}, {1, 1}}
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
