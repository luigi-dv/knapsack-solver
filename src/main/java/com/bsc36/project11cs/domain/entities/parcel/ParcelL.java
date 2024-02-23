package com.bsc36.project11cs.domain.entities.parcel;

import javafx.scene.paint.Color;
import com.bsc36.project11cs.infrastructure.configuration.BasicConfig;

public class ParcelL extends Parcel{

        /**
        * Constructor for ParcelL
        */
        public ParcelL() {
            this.id = 4;
            this.type = 'L';
            this.name = "Parcel L";
            this.color = Color.valueOf(BasicConfig.L_PARCEL_COLOR);
            this.volume = 5;
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
