package com.bsc36.project11cs.application.controllers.services;

import com.bsc36.project11cs.domain.entities.parcel.Parcel;
import com.bsc36.project11cs.infrastructure.configuration.BasicConfig;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * The ParcelsController class extends ServiceController and is designed
 * to handle user interactions related to parcels without associated values.
 * It initializes ComboBoxes for selecting parcels and triggers knapsack calculations based on user input.
 */
public class ParcelsController extends ServiceController<Parcel> {

    /**
     * Initializes the controller by populating ComboBox options with Parcels.
     */
    public void initialize() {

        // Populate the ComboBox with parcels & pentominoes
        ObservableList<Parcel> options = FXCollections.observableArrayList();

        if(BasicConfig.SHOW_OLD_PARCELS) {
            // Create parcels if the old parcels are to be shown
            Parcel parcelA = Parcel.createParcelFromType('A');
            Parcel parcelB = Parcel.createParcelFromType('B');
            Parcel parcelC = Parcel.createParcelFromType('C');
            Parcel parcelL = Parcel.createParcelFromType('L');
            Parcel parcelO = Parcel.createParcelFromType('O');
            Parcel parcelP = Parcel.createParcelFromType('P');
            Parcel parcelT = Parcel.createParcelFromType('T');
            Parcel parcelU = Parcel.createParcelFromType('U');

            Parcel[] parcels = {parcelA, parcelB, parcelC, parcelL, parcelO, parcelP, parcelT, parcelU};

            // Add parcels to the list
            addParcelsToList(parcels, options);
        }
        else {
            // Create parcels if the old parcels are not to be shown
            Parcel parcelO = Parcel.createParcelFromType('O');
            Parcel parcelP = Parcel.createParcelFromType('P');
            Parcel parcelU = Parcel.createParcelFromType('U');

            Parcel[] parcels = {parcelO, parcelP, parcelU};

            // Add parcels to the list
            addParcelsToList(parcels, options);
        }

        // Set ComboBox options
        setComboBoxOptions(parcel1Combobox, options);
        setComboBoxOptions(parcel2Combobox, options);
        setComboBoxOptions(parcel3Combobox, options);
    }
}
