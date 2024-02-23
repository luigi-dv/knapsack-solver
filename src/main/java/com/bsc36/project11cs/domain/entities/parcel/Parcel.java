package com.bsc36.project11cs.domain.entities.parcel;
import javafx.scene.paint.Color;


public abstract class Parcel {
    protected int id;
    protected char type;
    protected String name;
    protected Color color;
    protected int volume;
    protected int[][][] shape;
    protected int[][][][] rotations;

    /**
     * Create Parcel
     * @param id int Id Value
     * @return Parcel Parcel Value
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
     * Create Parcel
     * @param type char Type Value
     * @return Parcel Parcel Value
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
     * Get Parcel Id
     * @return int Id Value
     */
    public int getId() {
        return id;
    }

    /**
     * Get Parcel Type
     * @return char Type Value
     */
    public char getType() {
        return type;
    }

    /**
     * Get Parcel Shape
     * @return int[][][] Shape Value
     */
    public int[][][] getShape() {
        return shape;
    }

    /**
     * Set Parcel Shape
     * @param shape int[][][] Shape Value
     */
    public void setShape(int[][][] shape) {
        this.shape = shape;
    }

    /**
     * Get Parcel Value
     * @return int Value Value
     */
    public String getName() {
        return name;
    }

    /**
     * Get Parcel Color
     * @return String Color Value
     */
    public Color getColor() {
        return color;
    }

    /**
     * Get Parcel Volume
     * @return int Volume Value
     */
    public int getVolume(){
        return volume;
    }

    /**
     * Get Parcel Rotations
     * @return int[][][][] Rotations Value
     */
    public int[][][][] getRotations() {
        return rotations;
    }

    /**
     * Rotations of the 3D shape
     * @param shape int[][][] Shape Value
     * @return int[][][] Shape Value
     */
    protected int[][][] rotateX(int[][][] shape) {
        int[][][] newShape = new int[shape.length][shape[0].length][shape[0][0].length];
        for (int x = 0; x < shape.length; x++) {
            for (int y = 0; y < shape[0].length; y++) {
                for (int z = 0; z < shape[0][0].length; z++) {
                    newShape[x][y][z] = shape[x][shape[0].length - y - 1][z];
                }
            }
        }
        return newShape;
    }

    /**
     * Rotations of the 3D shape
     * @param shape int[][][] Shape Value
     * @return int[][][] Shape Value
     */
    protected int[][][] rotateY(int[][][] shape) {
        int[][][] newShape = new int[shape.length][shape[0].length][shape[0][0].length];
        for (int x = 0; x < shape.length; x++) {
            for (int y = 0; y < shape[0].length; y++) {
                for (int z = 0; z < shape[0][0].length; z++) {
                    newShape[x][y][z] = shape[shape.length - x - 1][y][z];
                }
            }
        }
        return newShape;
    }

    /**
     * Rotations of the 3D shape
     * @param shape int[][][] Shape Value
     * @return int[][][] Shape Value
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

}
