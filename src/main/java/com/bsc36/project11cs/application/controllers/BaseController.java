package com.bsc36.project11cs.application.controllers;

import com.bsc36.project11cs.MainApplication;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import com.bsc36.project11cs.domain.entities.parcel.Parcel;


public class BaseController {
    public Stage stage;

    public static String type;

    public MainApplication main;

    /**
     * Set Stage
     *
     * @param stage Stage Object
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * Set com.bsc36.project11cs.MainApplication
     *
     * @param mainApp MainApplication Object
     */
    public void setMain(MainApplication mainApp) {
        this.main = mainApp;
    }

}
