package com.bsc36.project11cs.application.controllers;

import javafx.fxml.FXML;
import java.io.IOException;
import javafx.event.ActionEvent;

/**
 * The MenuController class controls the actions and navigation within the application's menu.
 * It handles events triggered by various buttons to show different views and manage the application flow.
 */
public class MenuController extends BaseController {

    /**
     * Shows the parcels view in response to the "Parcels" button click.
     *
     * @param event The ActionEvent triggered by the button click.
     */
    @FXML
    private void onParcelsButtonClick(ActionEvent event) {
        main.showParcelsView();
    }

    /**
     * Shows the value parcels view in response to the "Values" button click.
     *
     * @param event The ActionEvent triggered by the button click.
     */
    @FXML
    private void onValuesButtonClick(ActionEvent event) {
        main.showValueParcelsView();
    }

    /**
     * Exits the application in response to the "Exit" button click.
     *
     * @param event The ActionEvent triggered by the button click.
     */
    @FXML
    private void onExitButtonClick(ActionEvent event) {
        main.exit();
    }

    /**
     * Shows the empty cargo space view in response to the "Show Cargo Space" button click.
     *
     * @param event The ActionEvent triggered by the button click.
     */
    @FXML
    private void onShowCargoSpace(ActionEvent event) {
        main.showEmptyCargoSpaceView();
    }
}
