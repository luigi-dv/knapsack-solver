package com.bsc36.project11cs.application.controllers.services;

import javafx.fxml.FXML;
import javafx.util.Callback;
import java.util.Collections;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.collections.ObservableList;
import com.bsc36.project11cs.domain.entities.parcel.Parcel;
import com.bsc36.project11cs.application.controllers.BaseController;
import com.bsc36.project11cs.domain.entities.knapsack.KnapsackBase;



/**
 * The ServiceController class is responsible for managing user interactions related to parcel services.
 * It extends the BaseController and implements the ServiceControllerInterface for handling parcel services.
 *
 * @param <T> The type of Parcel to be used in the service.
 */
public class ServiceController<T extends Parcel> extends BaseController implements ServiceControllerInterface<T> {

    @FXML
    protected ComboBox<T> parcel1Combobox;

    @FXML
    protected ComboBox<T> parcel2Combobox;

    @FXML
    protected ComboBox<T> parcel3Combobox;

    /**
     * Adds an array of parcels to the provided ObservableList.
     *
     * @param parcels An array of parcels to be added to the list.
     * @param list    The ObservableList to which parcels will be added.
     */
    public void addParcelsToList(T[] parcels, ObservableList<T> list) {
        Collections.addAll(list, parcels);
    }

    /**
     * Configures the provided ComboBox with options from the given ObservableList.
     *
     * @param comboBox The ComboBox to be configured.
     * @param options  The ObservableList containing the options for the ComboBox.
     */
    public void setComboBoxOptions(ComboBox<T> comboBox, ObservableList<T> options) {
        comboBox.setPromptText("Select a value");
        comboBox.setItems(options);

        Callback<ListView<T>, ListCell<T>> factory = new Callback<ListView<T>, ListCell<T>>() {
            @Override
            public ListCell<T> call(ListView<T> param) {
                return new ListCell<T>() {
                    @Override
                    protected void updateItem(T item, boolean empty) {
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
     * Set the options for a ComboBox to display values.
     * @param comboBox The ComboBox to be configured.
     * @param options The ObservableList containing the options for the ComboBox.
     */
    public void setComboBoxValueOptions(ComboBox<Integer> comboBox, ObservableList<Integer> options) {
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
     * Handles the event when the "Calculate" button is clicked.
     * Validates ComboBox values and triggers the calculation of the knapsack.
     *
     * @param event The ActionEvent triggered by the button click.
     */
    @FXML
    public void onCalculateClick(ActionEvent event) {
        // Check if the ComboBox values are not null before creating Parcel objects
        Parcel parcel1 = parcel1Combobox.getValue();
        Parcel parcel2 = parcel2Combobox.getValue();
        Parcel parcel3 = parcel3Combobox.getValue();

        if (parcel1 != null && parcel2 != null && parcel3 != null) {
            // Create a KnapsackBase instance and trigger the cargo space visualization
            KnapsackBase<Parcel> knapsack = new KnapsackBase<>(parcel1, parcel2, parcel3);
            main.showCargoSpaceBacktrack(knapsack);
        } else {
            System.out.println("Please select a value for all parcels.");
        }
    }

    /**
     * Handles the Back button click event by navigating back to the main menu.
     *
     * @param event The ActionEvent triggered by the button click.
     */
    @FXML
    public void onBackButtonClick(ActionEvent event) {
        main.showMenu();
    }
}
