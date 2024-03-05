package com.bsc36.project11cs.application.controllers.services;


import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.collections.ObservableList;

import java.io.IOException;

/**
 * Interface defining the contract for a Service Controller in the application.
 * Service Controllers are responsible for managing and handling user interactions related to services.
 */
public interface ServiceControllerInterface<T> {

    /**
     * Set the options for a ComboBox to display parcels.
     * This method is responsible for populating the ComboBox with relevant parcel data.
     */
    void setComboBoxOptions(ComboBox<T> comboBox, ObservableList<T> options);

    /**
     * Set the options for a ComboBox to display values.
     * @param comboBox The ComboBox to be configured.
     * @param options The ObservableList containing the options for the ComboBox.
     */
    void setComboBoxValueOptions(ComboBox<Integer> comboBox, ObservableList<Integer> options);

    /**
     * Handle the event when the "Calculate" button is clicked.
     * @param event The ActionEvent triggered by the button click.
     *              This event can be used to gather information about the source of the event.
     */
    void onCalculateClick(ActionEvent event) throws IOException;

    /**
     * Handle the event when the "Back" button is clicked.
     * @param event The ActionEvent triggered by the button click.
     *              This event can be used to gather information about the source of the event.
     */
    void onBackButtonClick(ActionEvent event);
}
