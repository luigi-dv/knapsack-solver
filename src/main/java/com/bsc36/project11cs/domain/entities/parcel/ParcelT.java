package com.bsc36.project11cs.domain.entities.parcel;

import com.bsc36.project11cs.infrastructure.configuration.BasicConfig;
import javafx.scene.paint.Color;

public class ParcelT extends Parcel {

        /**
        * Constructor for ParcelT
        */
        public ParcelT() {
            this.id = 7;
            this.type = 'T';
            this.name = "Parcel T";
            this.color = Color.valueOf(BasicConfig.L_PARCEL_COLOR);
            this.volume = 5;
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
