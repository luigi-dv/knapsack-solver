package com.bsc36.project11cs.domain.entities.parcel;

import com.bsc36.project11cs.infrastructure.configuration.BasicConfig;
import javafx.scene.paint.Color;


public class ParcelP extends Parcel {

        /**
        * Constructor for ParcelP
        */
        public ParcelP() {
            this.id = 6;
            this.type = 'P';
            this.name = "Parcel P";
            this.color = Color.valueOf(BasicConfig.P_PARCEL_COLOR);
            this.volume = 5;
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
