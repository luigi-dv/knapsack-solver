package com.bsc36.project11cs.domain.entities.parcel;

import com.bsc36.project11cs.infrastructure.configuration.BasicConfig;
import javafx.scene.paint.Color;

public class ParcelB extends Parcel{

    /**
     * Constructor for ParcelB
     */
    public ParcelB() {
        this.id = 2;
        this.type = 'B';
        this.name = "Parcel B";
        this.color = Color.valueOf(BasicConfig.B_PARCEL_COLOR);
        this.volume = 8;
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
