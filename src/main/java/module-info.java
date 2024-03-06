module com.bsc36.project11cs {
    requires javafx.controls;
    requires javafx.fxml;
    requires sentry;

    // Global
    exports com.bsc36.project11cs;
    opens com.bsc36.project11cs to javafx.fxml;
    // Controllers
    exports com.bsc36.project11cs.application.controllers;
    opens com.bsc36.project11cs.application.controllers to javafx.fxml;
    // Controllers Services
    exports com.bsc36.project11cs.application.controllers.services;
    opens com.bsc36.project11cs.application.controllers.services to javafx.fxml;
    // Entities
    exports com.bsc36.project11cs.domain.entities;
    opens com.bsc36.project11cs.domain.entities to javafx.fxml;
    // Knapsack Entities
    exports com.bsc36.project11cs.domain.entities.knapsack;
    opens com.bsc36.project11cs.domain.entities.knapsack to javafx.fxml;
    // Parcel Entities
    exports com.bsc36.project11cs.domain.entities.parcel;
    opens com.bsc36.project11cs.domain.entities.parcel to javafx.fxml;
    // Visualizer
    exports com.bsc36.project11cs.application.visualizer;
    // Value Objects
    exports com.bsc36.project11cs.domain.valueobjects;
    // Infrastructure
    exports com.bsc36.project11cs.infrastructure.configuration;
    opens com.bsc36.project11cs.infrastructure.configuration to javafx.fxml;
    // Application Services
    exports com.bsc36.project11cs.application.services;
    opens com.bsc36.project11cs.application.services to javafx.fxml;
}