package com.bsc36.project11cs.infrastructure.configuration;

import com.bsc36.project11cs.domain.entities.CargoSpace;

/**
 * Basic Config
 */
public class BasicConfig {
    public static final CargoSpace BASIC_CARGO_SPACE = new CargoSpace(33, 8, 5);
    public static final int MAXIMUM_PARCEL_VALUE = 6;
    public static final int INCREASE_RATIO = 20;

    public static final int GA_MAX_GENERATION = 100;

    public static final String A_PARCEL_COLOR = "#FF1178";

    public static final String B_PARCEL_COLOR = "#FE0000";

    public static final String C_PARCEL_COLOR = "#FFF20A";
    public static final String L_PARCEL_COLOR = "#7CFF01";

    public static final String O_PARCEL_COLOR = "#FF7C01";

    public static final String P_PARCEL_COLOR = "#01FFF4";

    public static final String T_PARCEL_COLOR = "#FF00FF";

    public static final String U_PARCEL_COLOR = "#7C01FF";
}
