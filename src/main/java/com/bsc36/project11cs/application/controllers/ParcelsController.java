package com.bsc36.project11cs.application.controllers;

import com.bsc36.project11cs.domain.entities.knapsack.Knapsack;
import com.bsc36.project11cs.domain.entities.parcel.Parcel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

import java.io.IOException;

public class ParcelsController extends BaseController {

    @FXML
    private ComboBox<Parcel> parcel1Combobox;

    @FXML
    private ComboBox<Parcel> parcel2Combobox;

    @FXML
    private ComboBox<Parcel> parcel3Combobox;

    /**
     * Initialize the controller
     */
    public void initialize() {

        // Populate the ComboBox with parcels & pentominoes
        ObservableList<Parcel> options = FXCollections.observableArrayList();

        // Add parcels
        options.add(Parcel.createParcelFromType('A'));
        options.add(Parcel.createParcelFromType('B'));
        options.add(Parcel.createParcelFromType('C'));
        options.add(Parcel.createParcelFromType('P'));
        options.add(Parcel.createParcelFromType('L'));
        options.add(Parcel.createParcelFromType('T'));

        // Set ComboBox options
        setComboBoxOptions(parcel1Combobox, options);
        setComboBoxOptions(parcel2Combobox, options);
        setComboBoxOptions(parcel3Combobox, options);
    }

    /**
     * Set ComboBox options
     *
     * @param comboBox ComboBox
     * @param options  ObservableList
     */

    private void setComboBoxOptions(ComboBox<Parcel> comboBox, ObservableList<Parcel> options) {
        comboBox.setPromptText("Select a value");
        comboBox.setItems(options);

        Callback<ListView<Parcel>, ListCell<Parcel>> factory = new Callback<ListView<Parcel>, ListCell<Parcel>>() {
            @Override
            public ListCell<Parcel> call(ListView<Parcel> param) {
                return new ListCell<Parcel>() {
                    @Override
                    protected void updateItem(Parcel item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item == null || empty) {
                            setText(null);
                        } else {
                            setText(item.getName());
                        }
                    }
                };
            }
        };

        comboBox.setCellFactory(factory);
        comboBox.setButtonCell(factory.call(null));
    }

    /**
     * Handle the Calculate button click event
     *
     * @param event ActionEvent
     * @throws IOException IOException
     */
    @FXML
    private void onCalculateClick(ActionEvent event) throws IOException {
        // Check if the ComboBox values are not null before creating Parcel objects
        Parcel parcel1Value = parcel1Combobox.getValue();
        Parcel parcel2Value = parcel2Combobox.getValue();
        Parcel parcel3Value = parcel3Combobox.getValue();

        if (parcel1Value != null && parcel2Value != null && parcel3Value != null) {

            Knapsack knapsack = new Knapsack(
                    parcel1Value,
                    parcel2Value,
                    parcel3Value
            );
            main.showCargoSpaceBacktrack(knapsack);

        } else {
            System.out.println("Please select a value for all parcels.");
        }
    }

    /**
     * Handle the Back button click event
     *
     * @param event ActionEvent
     */
    @FXML
    private void onBackButtonClick(ActionEvent event) {
        main.showMenu();
    }
}
