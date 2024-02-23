package com.bsc36.project11cs.application.controllers;

import com.bsc36.project11cs.domain.entities.knapsack.KnapsackGA;
import com.bsc36.project11cs.domain.entities.parcel.Parcel;
import com.bsc36.project11cs.domain.entities.parcel.ValueParcel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

import java.io.IOException;

public class ValueParcelsController extends BaseController {
    @FXML
    private ComboBox<Parcel> parcel1Combobox;

    @FXML
    private ComboBox<Integer> valueParcel1;

    @FXML
    private ComboBox<Parcel> parcel2Combobox;

    @FXML
    private ComboBox<Integer> valueParcel2;

    @FXML
    private ComboBox<Parcel> parcel3Combobox;

    @FXML
    private ComboBox<Integer> valueParcel3;

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
        setComboBoxParcelOptions(parcel1Combobox, options);
        setComboBoxParcelOptions(parcel2Combobox, options);
        setComboBoxParcelOptions(parcel3Combobox, options);

        // Populate the ComboBox with values
        ObservableList<Integer> values = FXCollections.observableArrayList();

        // Add values
        for (int i = 1; i <= 10; i++) {
            values.add(i);
        }

        // Set ComboBox options
        setComboBoxValueOptions(valueParcel1, values);
        setComboBoxValueOptions(valueParcel2, values);
        setComboBoxValueOptions(valueParcel3, values);

    }

    /**
     * Set ComboBox options
     *
     * @param comboBox ComboBox
     * @param options  ObservableList
     */

    private void setComboBoxParcelOptions(ComboBox<Parcel> comboBox, ObservableList<Parcel> options) {
        comboBox.setPromptText("Select a parcel");
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
     * Set ComboBox options
     *
     * @param comboBox ComboBox
     * @param options  ObservableList
     */
    private void setComboBoxValueOptions(ComboBox<Integer> comboBox, ObservableList<Integer> options) {
        comboBox.setPromptText("Select a value");
        comboBox.setItems(options);

        Callback<ListView<Integer>, ListCell<Integer>> factory = new Callback<ListView<Integer>, ListCell<Integer>>() {
            @Override
            public ListCell<Integer> call(ListView<Integer> param) {
                return new ListCell<Integer>() {
                    @Override
                    protected void updateItem(Integer item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item == null || empty) {
                            setText(null);
                        } else {
                            setText(item.toString());
                        }
                    }
                };
            }
        };

        comboBox.setCellFactory(factory);
        comboBox.setButtonCell(factory.call(null));
    }

    /**
     * Calculate the cargo space values
     *
     * @param event ActionEvent
     * @throws IOException IOException
     */
    @FXML
    private void onCalculateClick(ActionEvent event) throws IOException {
        // Check if the ComboBox values are not null before creating Parcel objects
        Parcel parcel1 = parcel1Combobox.getValue();
        Parcel parcel2 = parcel2Combobox.getValue();
        Parcel parcel3 = parcel3Combobox.getValue();

        Integer parcel1Value = valueParcel1.getValue();
        Integer parcel2Value = valueParcel2.getValue();
        Integer parcel3Value = valueParcel3.getValue();

        if (parcel1 != null && parcel2 != null && parcel3 != null
                && parcel1Value != null && parcel2Value != null && parcel3Value != null) {

            KnapsackGA knapsack = new KnapsackGA(
                    new ValueParcel(parcel1.getType(), parcel1Value),
                    new ValueParcel(parcel2.getType(), parcel1Value),
                    new ValueParcel(parcel3.getType(), parcel1Value)
            );
            main.showCargoSpaceValues(knapsack);


        } else {
            System.out.println("Please select a value for all parcels.");
        }
    }

    /**
     * Go back to the menu
     *
     * @param event ActionEvent
     */
    @FXML
    private void onBackButtonClick(ActionEvent event) {
        main.showMenu();
    }
}
