package com.bsc36.project11cs.domain.entities.parcel;

import javafx.scene.paint.Color;
import com.bsc36.project11cs.domain.valueobjects.Size;

/**
 * The abstract Parcel class serves as a base for different types of parcels in the cargo space.
 * It defines common attributes and methods shared among parcel types.
 */
public abstract class Parcel {

    /** Unique identifier for the parcel. */
    protected int id;

    /** Type code representing the category of the parcel. */
    protected char type;

    /** Name of the parcel. */
    protected String name;

    /** Color associated with the parcel. */
    protected Color color;

    /** Size value object representing the dimensions of the parcel. */
    protected Size size;

    /** Volume of the parcel, calculated based on its size. */
    protected int volume;

    /** 3D shape representation of the parcel. */
    protected int[][][] shape;

    /** Array containing different rotations of the 3D shape for the parcel. */
    protected int[][][][] rotations;

    /**
     * Factory method to create a parcel based on its unique identifier.
     *
     * @param id int Id Value
     * @return Parcel instance corresponding to the provided identifier
     * @throws IllegalArgumentException if an invalid parcel id is provided
     */
    public static Parcel createParcelFromId(int id) {
        return switch (id) {
            case 0 -> new ParcelA();
            case 1 -> new ParcelB();
            case 2 -> new ParcelC();
            case 3 -> new ParcelL();
            case 4 -> new ParcelO();
            case 5 -> new ParcelP();
            case 6 -> new ParcelT();
            case 7 -> new ParcelU();
            default -> throw new IllegalArgumentException("Invalid parcel id: " + id);
        };
    }

    /**
     * Factory method to create a parcel based on its type code.
     *
     * @param type char Type Value
     * @return Parcel instance corresponding to the provided type
     * @throws IllegalArgumentException if an invalid parcel type code is provided
     */
    public static Parcel createParcelFromType(char type) {
        return switch (type) {
            case 'A' -> new ParcelA();
            case 'B' -> new ParcelB();
            case 'C' -> new ParcelC();
            case 'L' -> new ParcelL();
            case 'O' -> new ParcelO();
            case 'P' -> new ParcelP();
            case 'T' -> new ParcelT();
            case 'U' -> new ParcelU();
            default -> throw new IllegalArgumentException("Invalid parcel type: " + type);
        };
    }

    /**
     * Get the unique identifier of the parcel.
     *
     * @return int Id Value
     */
    public int getId() {
        return id;
    }

    /**
     * Get the type code of the parcel.
     *
     * @return char Type Value
     */
    public char getType() {
        return type;
    }

    /**
     * Get the 3D shape representation of the parcel.
     *
     * @return int[][][] Shape Value
     */
    public int[][][] getShape() {
        return shape;
    }

    /**
     * Set the 3D shape representation of the parcel.
     *
     * @param shape int[][][] Shape Value
     */
    public void setShape(int[][][] shape) {
        this.shape = shape;
    }

    /**
     * Get the name of the parcel.
     *
     * @return String Name of the parcel
     */
    public String getName() {
        return name;
    }

    /**
     * Get the color associated with the parcel.
     *
     * @return Color Value
     */
    public Color getColor() {
        return color;
    }

    /**
     * Get the color associated with the parcel.
     *
     * @return Color Value
    */
    public Size getSize() {
        return size;
    }

    /**
     * Get the volume of the parcel, calculated based on its size.
     *
     * @return int Volume Value
     */
    public int getVolume() {
        return volume;
    }

    /**
     * Get the array containing different rotations of the 3D shape for the parcel.
     *
     * @return int[][][][] Rotations Value
     */
    public int[][][][] getRotations() {
        return rotations;
    }

    /**
     * Rotate the 3D shape of the parcel around the X-axis.
     *
     * @param shape int[][][] Shape Value
     * @return int[][][] Rotated shape around the X-axis
     */
    protected int[][][] rotateX(int[][][] shape) {
        int[][][] newShape = new int[shape.length][shape[0].length][shape[0][0].length];
        for (int x = 0; x < shape.length; x++) {
            for (int y = 0; y < shape[0].length; y++) {
                System.arraycopy(shape[x][shape[0].length - y - 1], 0, newShape[x][y], 0, shape[0][0].length);
            }
        }
        return newShape;
    }

    /**
     * Rotate the 3D shape of the parcel around the Y-axis.
     *
     * @param shape int[][][] Shape Value
     * @return int[][][] Rotated shape around the Y-axis
     */
    protected int[][][] rotateY(int[][][] shape) {
        int[][][] newShape = new int[shape.length][shape[0].length][shape[0][0].length];
        for (int x = 0; x < shape.length; x++) {
            for (int y = 0; y < shape[0].length; y++) {
                System.arraycopy(shape[shape.length - x - 1][y], 0, newShape[x][y], 0, shape[0][0].length);
            }
        }
        return newShape;
    }

    /**
     * Rotate the 3D shape of the parcel around the Z-axis.
     *
     * @param shape int[][][] Shape Value
     * @return int[][][] Rotated shape around the Z-axis
     */
    protected int[][][] rotateZ(int[][][] shape) {
        int[][][] newShape = new int[shape.length][shape[0].length][shape[0][0].length];
        for (int x = 0; x < shape.length; x++) {
            for (int y = 0; y < shape[0].length; y++) {
                for (int z = 0; z < shape[0][0].length; z++) {
                    newShape[x][y][z] = shape[x][y][shape[0][0].length - z - 1];
                }
            }
        }
        return newShape;
    }

    /**
     * Calculate the volume of the parcel based on its size.
     *
     * @return int Volume Value
     */
    protected int calculateVolume() {
        return size.length() * size.width() * size.height();
    }
}
