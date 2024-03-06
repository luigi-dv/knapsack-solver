# Knapsack Solver

[![Knapsack Solver Image](https://github.com/luigi-dv/knapsack-solver/actions/workflows/docker-image.yml/badge.svg)](https://github.com/luigi-dv/knapsack-solver/actions/workflows/docker-image.yml)

Knapsack Solver is a Java application that solves the Knapsack Problem using a Genetic Algorithm. The application allows the user to define the parameters of the problem and the algorithm, and then run the algorithm to solve the problem. The application also allows the user to save the results of the algorithm to a file.

## Getting Started

To get a local copy up and running follow these simple steps.

### Prerequisites

- Java SDK 21
- Maven
- JavaFX

### Installation (Maven)

1. Clone the repo
   ```sh
   git clone https://github.com/luigi-dv/knapsack-solver
   cd knapsack-solver
   ```
2. Install Maven dependencies
   ```sh
    mvn install
    ```
3. Build the project
   ```sh
    mvn compile
   ```
4. Run the project
   ```sh
    mvn clean package
   ```

### Installation (Running the entry class)
Alternatively, you can run the application directly from the MainApplication class. Navigate to the root of the project and execute the following command:
```sh
   java --module-path /path/to/javafx-sdk-21.0.2/lib --add-modules javafx.controls,javafx.fxml src/main/java/com/bsc36/project11cs/application/MainApplication.java
```

## Authors

- [Luigelo Davila](mailto:l.davilavilchez@student.maastrichtuniversity.nl)

## License
This project is licensed under the MIT License - see the LICENSE.md file for details

