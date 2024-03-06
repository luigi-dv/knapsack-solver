package com.bsc36.project11cs.application.controllers.services;

import com.bsc36.project11cs.infrastructure.configuration.BasicConfig;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import com.bsc36.project11cs.domain.entities.parcel.ValueParcel;
import com.bsc36.project11cs.domain.entities.knapsack.KnapsackValue;

/**
 * The ValueParcelsController class extends the ServiceController and is specifically designed for managing
 * user interactions related to parcels with associated values (ValueParcel). It handles the initialization of
 * ComboBox options and the calculation of a knapsack with ValueParcel objects.
 */
public class ValueParcelsController extends ServiceController<ValueParcel> {

    @FXML
    protected ComboBox<Integer> valueParcel1;

    @FXML
    protected ComboBox<Integer> valueParcel2;

    @FXML
    protected ComboBox<Integer> valueParcel3;

    /**
     * Initializes the ValueParcelsController by populating ComboBox options and configuring initial settings.
     */
    public void initialize() {

        // Populate the ComboBox with ValueParcels
        ObservableList<ValueParcel> options = FXCollections.observableArrayList();

        // Create ValueParcels
        ValueParcel[] parcels = getValueParcels();
        // Add ValueParcels to the list
        addParcelsToList(parcels, options);

        // Set ComboBox options
        setComboBoxOptions(parcel1Combobox, options);
        setComboBoxOptions(parcel2Combobox, options);
        setComboBoxOptions(parcel3Combobox, options);

        // Populate the ComboBox with values
        ObservableList<Integer> values = FXCollections.observableArrayList();
        for (int i = 1; i <= 10; i++) {
            values.add(i);
        }

        // Set ComboBox options
        setComboBoxValueOptions(valueParcel1, values);
        setComboBoxValueOptions(valueParcel2, values);
        setComboBoxValueOptions(valueParcel3, values);

    }

    /**
     * Creates an array of ValueParcel objects.
     * @return An array of ValueParcel objects.
     */
    private ValueParcel[] getValueParcels() {
        if (BasicConfig.SHOW_OLD_PARCELS) {
            ValueParcel parcelA = new ValueParcel('A', 0);
            ValueParcel parcelB = new ValueParcel('B', 0);
            ValueParcel parcelC = new ValueParcel('C', 0);
            ValueParcel parcelL = new ValueParcel('L', 0);
            ValueParcel parcelO = new ValueParcel('O', 0);
            ValueParcel parcelP = new ValueParcel('P', 0);
            ValueParcel parcelT = new ValueParcel('T', 0);
            ValueParcel parcelU = new ValueParcel('U', 0);
            // Add ValueParcels to an array
            return new ValueParcel[]{parcelA, parcelB, parcelC, parcelL, parcelO, parcelP, parcelT, parcelU};

        } else {
            ValueParcel parcelO = new ValueParcel('O', 0);
            ValueParcel parcelP = new ValueParcel('P', 0);
            ValueParcel parcelU = new ValueParcel('U', 0);
            // Add ValueParcels to an array
            return new ValueParcel[]{parcelO, parcelP, parcelU};
        }



    }

    /**
     * Handles the event when the "Calculate" button is clicked.
     * Validates ComboBox values and triggers the calculation of the knapsack with ValueParcel objects.
     *
     * @param event The ActionEvent triggered by the button click.
     */
    @FXML
    @Override
    public void onCalculateClick(ActionEvent event) {
        // Get the selected ValueParcel objects
        ValueParcel parcel1 = parcel1Combobox.getValue();
        ValueParcel parcel2 = parcel2Combobox.getValue();
        ValueParcel parcel3 = parcel3Combobox.getValue();
        // Get the value of the selected parcels
        Integer parcel1Value = valueParcel1.getValue();
        Integer parcel2Value = valueParcel2.getValue();
        Integer parcel3Value = valueParcel3.getValue();

        if (parcel1 != null && parcel2 != null && parcel3 != null
                && parcel1Value != null && parcel2Value != null && parcel3Value != null) {

            parcel1.setValue(parcel1Value);
            parcel2.setValue(parcel2Value);
            parcel3.setValue(parcel3Value);

            KnapsackValue<ValueParcel> knapsack = new KnapsackValue<>(parcel1, parcel2, parcel3);
            main.showCargoSpaceValues(knapsack);

        } else {
            System.out.println("Please select a value for all parcels.");
        }
    }
}
