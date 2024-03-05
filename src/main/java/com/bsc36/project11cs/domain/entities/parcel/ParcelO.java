package com.bsc36.project11cs.domain.entities.parcel;

import javafx.scene.paint.Color;
import com.bsc36.project11cs.domain.valueobjects.Size;
import com.bsc36.project11cs.infrastructure.configuration.BasicConfig;


/**
 * The ParcelO class represents a specific type of parcel (Parcel O).
 * It extends the abstract class Parcel and defines the characteristics
 * and structure of Parcel O, including its ID, type, name, color, size,
 * volume, 3D shape, and rotations.
 */
public class ParcelO extends Parcel {

    /**
     * Constructor for ParcelO initializes the attributes of Parcel O.
     */
    public ParcelO() {
        this.id = 5;
        this.type = 'O';
        this.name = "Parcel O";
        this.color = Color.valueOf(BasicConfig.O_PARCEL_COLOR);
        this.size = new Size(1, 3, 2);
        this.volume = calculateVolume();
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
