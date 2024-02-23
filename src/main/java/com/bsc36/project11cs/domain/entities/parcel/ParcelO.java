package com.bsc36.project11cs.domain.entities.parcel;

import com.bsc36.project11cs.infrastructure.configuration.BasicConfig;
import javafx.scene.paint.Color;

public class ParcelO extends Parcel {

    /**
     * Constructor for ParcelO
     */
    public ParcelO() {
        this.id = 5;
        this.type = 'O';
        this.name = "Parcel O";
        this.color = Color.valueOf(BasicConfig.O_PARCEL_COLOR);
        this.volume = 1;
        // 3D shape of the parcel
        this.shape = new int[][][]{
                {
                        {1, 1},
                        {1, 1},
                        {1, 1}
                }
        };
        // Rotations of the 3D shape
        this.rotations = new int[][][][]{
                this.shape
        };
    }
}
