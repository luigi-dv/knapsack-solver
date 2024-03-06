package com.bsc36.project11cs.application.services;


import java.util.*;
import com.bsc36.project11cs.domain.entities.CargoSpace;
import com.bsc36.project11cs.domain.entities.Individual;
import com.bsc36.project11cs.domain.entities.parcel.ValueParcel;
import com.bsc36.project11cs.domain.entities.knapsack.KnapsackValue;
import com.bsc36.project11cs.infrastructure.configuration.BasicConfig;

/**
 * The GeneticAlgo class represents the implementation of a Genetic Algorithm for solving the knapsack problem.
 * It optimizes the selection of items to maximize the total value while staying within the cargo space capacity.
 */
public class GeneticAlgo {

    private final KnapsackValue<ValueParcel> knapsack;
    private ArrayList<Individual> populationList = new ArrayList<>();
    private final int population = 300;
    private int previousMax = 0;
    private int plateauCounter = 0;
    private final Random rn = new Random();
    private double mutationRate = 0.2;

    /**
     * Constructor for the GeneticAlgo class.
     *
     * @param knapsack The knapsack to be used in the genetic algorithm.
     */
    public GeneticAlgo(KnapsackValue<ValueParcel> knapsack) {
        this.knapsack = knapsack;
    }

    /**
     * Sets a new mutation rate for the genetic algorithm.
     *
     * @param newMutation The new mutation rate to be set.
     */
    public void setMutationRate(double newMutation) {
        this.mutationRate = newMutation;
    }

    /**
     * Runs the genetic algorithm. This method serves as the entry point for the algorithm.
     * It initializes the population, evolves it over multiple generations, and updates the knapsack
     * with the best solution found.
     */
    public void run() {
        CargoSpace cargoSpace = knapsack.getCargoSpace();
        // Clear the cargo space
        cargoSpace.clearCargoSpace();
        // Run the genetic algorithm
        int counter = 0;
        initializePopulation();
        int maxRounds = BasicConfig.GA_MAX_GENERATION;
        while (counter < maxRounds) {
            sortIndividuals(populationList);
            newGeneration();
            counter++;
            System.out.println(populationList.get(0).getScore());
            if (plateaus()) {
                reInitializePopulation();
            }
        }
        knapsack.updateCargoSpace(populationList.get(0).getChromosomes());
        System.out.println(populationList.get(0).getChromosomes().size());
    }

    /**
     * Initializes the population with randomly generated individuals.
     */
    private void initializePopulation() {
        populationList.clear();
        for (int i = 0; i < population; i++) {
            Individual individual = new Individual(this.knapsack, mutationRate);
            populationList.add(individual);
        }
    }

    /**
     * Sorts the individuals in the population based on their scores in descending order.
     *
     * @param populationList The population to be sorted.
     */
    private void sortIndividuals(ArrayList<Individual> populationList) {
        populationList.sort(new Comparator<Individual>() {
            @Override
            public int compare(Individual individual1, Individual individual2) {
                return Integer.compare(individual2.getScore(), individual1.getScore());
            }
        });
    }

    /**
     * Reinitializes the population by removing the bottom 15% of individuals and adding new ones.
     */
    private void reInitializePopulation() {
        populationList.subList((int) (populationList.size() * 0.15), populationList.size()).clear();
        for (int i = populationList.size(); i < population; i++) {
            populationList.add(new Individual(knapsack, mutationRate));
        }
    }

    /**
     * Creates a new generation by selecting the top 10% individuals and performing crossover to generate new individuals.
     */
    private void newGeneration() {
        ArrayList<Individual> newGeneration = new ArrayList<>();

        for (int i = 0; i < populationList.size() * 0.1; i++) {
            newGeneration.add(populationList.get(i));
        }
        for (int i = (int) (populationList.size() * 0.1); i < populationList.size(); i++) {
            Individual parent1 = populationList.get(rn.nextInt((int) (populationList.size() / 2)));
            Individual parent2 = populationList.get(rn.nextInt((int) (populationList.size() / 2)));

            Individual child = parent1.twoPointCrossover(parent2);
            newGeneration.add(child);
        }
        populationList = newGeneration;
    }

    /**
     * Checks if the population has plateaued, and adjusts the mutation rate accordingly.
     *
     * @return True if the population has plateaued.
     */
    private boolean plateaus() {
        int currentMax = populationList.get(0).getScore();
        if (currentMax <= previousMax) {
            plateauCounter++;
        } else {
            plateauCounter = 0;
        }
        double variance = calculateFitnessVariance();
        previousMax = currentMax;

        if (variance < 10.0) {
            setMutationRate(Math.min(0.5, mutationRate * 2));
        } else if (variance > 10.0) {
            setMutationRate(0.2);
        }
        int maxPlateauThreshold = 15;
        if (plateauCounter >= maxPlateauThreshold) {
            plateauCounter = 0;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Calculates the fitness variance of the population's scores.
     *
     * @return The fitness variance.
     */
    private double calculateFitnessVariance() {
        double mean = populationList.stream()
                .mapToInt(Individual::getScore)
                .average()
                .orElse(0.0);
        return populationList.stream()
                .mapToDouble(ind -> Math.pow(ind.getScore() - mean, 2))
                .average()
                .orElse(0.0);
    }
}
