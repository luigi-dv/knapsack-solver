package com.bsc36.project11cs.domain.valueobjects;

/**
 * Represents the size of an object in three-dimensional space.
 * This is a value object implemented as a record.
 *
 * @param length The length dimension of the object.
 * @param width  The width dimension of the object.
 * @param height The height dimension of the object.
 */
public record Size(int length, int width, int height) {

    /**
     * Retrieves the length dimension of the object.
     *
     * @return int Length dimension value.
     */
    @Override
    public int length() {
        return length;
    }

    /**
     * Retrieves the width dimension of the object.
     *
     * @return int Width dimension value.
     */
    @Override
    public int width() {
        return width;
    }

    /**
     * Retrieves the height dimension of the object.
     *
     * @return int Height dimension value.
     */
    @Override
    public int height() {
        return height;
    }
}
