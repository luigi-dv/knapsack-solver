module com.bsc36.project11cs {
    requires javafx.controls;
    requires javafx.fxml;
    requires sentry;

    // Controllers & Models
    exports com.bsc36.project11cs.application.controllers;
    opens com.bsc36.project11cs.application.controllers to javafx.fxml;
    exports com.bsc36.project11cs.domain.entities;
    opens com.bsc36.project11cs.domain.entities to javafx.fxml;
    exports com.bsc36.project11cs.application.visualizer;

    // Algorithms
    exports com.bsc36.project11cs.infrastructure.configuration;
    opens com.bsc36.project11cs.infrastructure.configuration to javafx.fxml;
    exports com.bsc36.project11cs.application.services;
    exports com.bsc36.project11cs;
    opens com.bsc36.project11cs to javafx.fxml;
    exports com.bsc36.project11cs.domain.services;
    opens com.bsc36.project11cs.domain.services to javafx.fxml;
    exports com.bsc36.project11cs.domain.entities.parcel;
    opens com.bsc36.project11cs.domain.entities.parcel to javafx.fxml;
    exports com.bsc36.project11cs.domain.entities.knapsack;
    opens com.bsc36.project11cs.domain.entities.knapsack to javafx.fxml;
}