package com.bsc36.project11cs.application.controllers;

import javafx.fxml.FXML;
import java.io.IOException;
import javafx.event.ActionEvent;


public class MenuController extends BaseController {

    /**
     * Show the knapsack view
     *
     * @param event ActionEvent
     */
    @FXML
    private void onSolverButtonClick(ActionEvent event) throws IOException {
        main.showSolver();
    }

    /**
     * Show the parcels view
     *
     * @param event ActionEvent
     */
    @FXML
    private void onParcelsButtonClick(ActionEvent event) {
        main.showParcelsView();
    }

    /**
     * Show the value parcels view
     *
     * @param event ActionEvent
     */
    @FXML
    private void onValuesButtonClick(ActionEvent event) {
        main.showValueParcelsView();
    }

    /**
     * Show the defined parcels view
     *
     * @param event ActionEvent
     */
    @FXML
    private void onExitButtonClick(ActionEvent event) {
        main.exit();
    }

    /**
     * Show the empty cargo space view
     *
     * @param event ActionEvent
     */
    @FXML
    private void onShowCargoSpace(ActionEvent event) {
        main.showEmptyCargoSpaceView();
    }
}

