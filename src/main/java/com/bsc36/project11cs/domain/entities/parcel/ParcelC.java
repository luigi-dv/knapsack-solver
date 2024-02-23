package com.bsc36.project11cs.domain.entities.parcel;

import com.bsc36.project11cs.infrastructure.configuration.BasicConfig;
import javafx.scene.paint.Color;

public class ParcelC extends Parcel{

    /**
     * Constructor for ParcelC
     */
    public ParcelC() {
        this.id = 3;
        this.type = 'C';
        this.name = "Parcel C";
        this.color = Color.valueOf(BasicConfig.C_PARCEL_COLOR);
        this.volume = 27;
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
