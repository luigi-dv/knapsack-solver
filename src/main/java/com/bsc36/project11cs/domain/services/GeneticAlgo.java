package com.bsc36.project11cs.domain.services;

import com.bsc36.project11cs.domain.entities.CargoSpace;
import com.bsc36.project11cs.domain.entities.Individual;
import com.bsc36.project11cs.domain.entities.knapsack.KnapsackValue;
import com.bsc36.project11cs.domain.entities.parcel.ValueParcel;
import com.bsc36.project11cs.infrastructure.configuration.BasicConfig;

import java.util.*;


public class GeneticAlgo{
    private final KnapsackValue<ValueParcel>  knapsack;
    private ArrayList<Individual> populationList = new ArrayList<>();
    private final int population = 300;
    private int previousMax = 0;
    private int plateauCounter = 0;
    private final Random rn = new Random();
    private double mutationRate = 0.2;

    /**
     * Constructor
     * @param knapsack the knapsackGA to use
     */
    public GeneticAlgo(KnapsackValue<ValueParcel>  knapsack){
        this.knapsack = knapsack;
    }
    public void setMutationRate(double newMutation){
        this.mutationRate = newMutation;
    }

    /**
     * Runs the genetic algorithm (Entry point)
     */
    public void run(){
        CargoSpace cargoSpace = knapsack.getCargoSpace();
        // Clear the cargo space
        cargoSpace.clearCargoSpace();
        // Run the genetic algorithm
        int counter = 0;
        intializePopulation();
        int maxRounds = BasicConfig.GA_MAX_GENERATION;
        while(counter < maxRounds){
            sortIndividuals(populationList);
            newGeneration();
            counter++;
            System.out.println(populationList.get(0).getScore()); 
            if(plateaus()){
                reInitializePopulation();
            }
        }
        knapsack.updateCargoSpace(populationList.get(0).getChromosomes());
        System.out.println(populationList.get(0).getChromosomes().size());
    }

    /**
     * Initializes the population
     */
    private void intializePopulation(){
        populationList.clear();
        for(int i = 0; i < population; i++){
            Individual individual = new Individual(this.knapsack, mutationRate);
            populationList.add(individual);
        }
    }

    /**
     * Sorts the individuals in the population
     * @param populationList the population to sort
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
     * Reinitializes the population
     * Removes the bottom 15% of the population and adds new individuals to the population
     */
    private void reInitializePopulation(){
        populationList.subList((int) (populationList.size()*0.15), populationList.size()).clear();
        for(int i = populationList.size(); i < population; i++){
            populationList.add(new Individual(knapsack, mutationRate));
        }
    }

    /**
     * Creates a new generation
     * Removes the bottom 10% of the population and adds new individuals to the population
     */
    private void newGeneration(){
        ArrayList<Individual> newGeneration = new ArrayList<>();

        for(int i = 0; i < populationList.size() * 0.1; i++){
            newGeneration.add(populationList.get(i));
        }
        for(int i = (int) (populationList.size()*0.1); i < populationList.size(); i ++){
            Individual parent1 = populationList.get(rn.nextInt((int) (populationList.size()/2)));
            Individual parent2 = populationList.get(rn.nextInt((int) (populationList.size()/2)));
            
            Individual child = parent1.twoPointCrossover(parent2);
            newGeneration.add(child);
        }
        populationList = newGeneration;
    }

    /**
     * Checks if the population has plateaued
     * @return true if the population has plateaued
     */
    private boolean plateaus(){
        int currentMax = populationList.get(0).getScore();
        if (currentMax <= previousMax) {
            plateauCounter++;
        } else {
            plateauCounter = 0;
        }
        double variance = calculateFitnessVariance();
        previousMax = currentMax;

        if (variance < 10.0){
            setMutationRate(Math.min(0.5, mutationRate*2));
        }
        else if(variance > 10.0){
            setMutationRate(0.2);
        }
        int maxPlateauThreshold = 15;
        if(plateauCounter >= maxPlateauThreshold){
            plateauCounter = 0;
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * Calculates the fitness variance
     * @return the fitness variance
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