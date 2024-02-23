package com.bsc36.project11cs.domain.entities.parcel;

import javafx.scene.paint.Color;
import com.bsc36.project11cs.infrastructure.configuration.BasicConfig;


public class ParcelA extends Parcel {

    /**
     * Constructor for ParcelA
     */
    public ParcelA() {
        this.id = 1;
        this.type = 'A';
        this.name = "Parcel A";
        this.color = Color.valueOf(BasicConfig.A_PARCEL_COLOR);
        this.volume = 16;
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
