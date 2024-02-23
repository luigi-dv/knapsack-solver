package com.bsc36.project11cs.domain.entities;

import com.bsc36.project11cs.domain.entities.knapsack.KnapsackGA;
import com.bsc36.project11cs.domain.entities.parcel.Parcel;

import java.util.ArrayList;
import java.util.Random;


public class Individual{
    private final KnapsackGA knapsackGA;
    private ArrayList<Parcel> chromosomes;
    private final Random rn1 = new Random();
    private final double mutationProbability;
    private int score;

    /**
     * Constructor
     * @param knapsackGA the knapsackGA to use
     * @param mutationRate the mutation rate
     */
    public Individual(KnapsackGA knapsackGA, double mutationRate) {
        this.mutationProbability = mutationRate;
        this.knapsackGA = knapsackGA;
        ArrayList<Parcel> chromosomes = setRandomChromosomes();
        setUpIndividual(chromosomes);
    }

    /**
     * Constructor
     * @param knapsackGA the knapsackGA to use
     * @param chromosomes the chromosomes to use
     */
    public Individual(KnapsackGA knapsackGA, ArrayList<Parcel> chromosomes, double mutationRate){
        this.mutationProbability = mutationRate;
        this.knapsackGA = knapsackGA;
        this.chromosomes = chromosomes;

        // System.out.println("Individual initialized with given chromosomes");
        setUpIndividual(chromosomes);
    }

    /**
     * Gets the score
     * @return the score
     */
    public int getScore(){
        return score;
    }

    /**
     * Gets the chromosomes
     * @return the chromosomes
     */
    public ArrayList<Parcel> getChromosomes() {
        return chromosomes;
    }

    /**
     * @param parcel The parcel to rotate
     * @return Rotated parcel
     */
    public Parcel rotateParcel(Parcel parcel) {
        int getRotation = rn1.nextInt(parcel.getRotations().length);
        parcel.setShape(parcel.getRotations()[getRotation]);
        return parcel;
    }
    

    /**
     * Sets random chromosomes
     * @return the random chromosomes
     */
    public ArrayList<Parcel> setRandomChromosomes() {
        int parcelToAdd;
        ArrayList<Parcel> temp = new ArrayList<>();
        for (int i = 0; i < rn1.nextInt(500 - 125) + 125; i++) {
            parcelToAdd = rn1.nextInt(3);
            if (parcelToAdd == 2) {
                temp.add(knapsackGA.getParcel1());
            }
            if (parcelToAdd == 1) {
                temp.add(knapsackGA.getParcel2());
            }
            if (parcelToAdd == 0) {
                temp.add(knapsackGA.getParcel2());
            }
            Parcel parcel = temp.get(i);
            temp.remove(i);
            parcel = rotateParcel(parcel);
            temp.add(parcel);
        }
        return temp;
    }

    /**
     * Mutates the individual
     * @param mutatingArrayList the individual to mutate
     * @return the mutated individual
     */
    public ArrayList<Parcel> mutation(ArrayList<Parcel> mutatingArrayList) {

        int numberOfMutations = rn1.nextInt(mutatingArrayList.size()/10)+1;
        for(int i = 0; i < numberOfMutations; i++){
            int mutationPoint = rn1.nextInt(mutatingArrayList.size());
            int itemToInput = rn1.nextInt(3);
            switch (itemToInput) {
                case 0:
                    mutatingArrayList.set(mutationPoint, knapsackGA.getParcel1());
                    break;
                case 1:
                    mutatingArrayList.set(mutationPoint, knapsackGA.getParcel2());
                    break;
                case 2:
                    mutatingArrayList.set(mutationPoint, knapsackGA.getParcel3());
                default:
                    break;
            }
        }

        return mutatingArrayList;
    }

    /**
     * Fills the remaining chromosomes
     * @param chromosomes the chromosomes to fill
     * @param targetLength the target length
     * @return the filled chromosomes
     */
    public ArrayList<Parcel> fillRemaining(ArrayList<Parcel> chromosomes, int targetLength){
        for(int i = chromosomes.size(); i < targetLength + 1; i++){
            int parcelToAdd = rn1.nextInt(3);
            if(parcelToAdd == 0){
                chromosomes.add(knapsackGA.getParcel1());
                // temp.add(knapsackGA.getPackageA());
            }
            if (parcelToAdd == 1) {
                // temp.add(knapsackGA.getPackageB());
                chromosomes.add(knapsackGA.getParcel2());
            }
            if (parcelToAdd == 2) {
                // temp.add(knapsackGA.getPackageC());
                chromosomes.add(knapsackGA.getParcel3());
            }
            Parcel item = chromosomes.get(i);
            // TODO: Suspicious 'List.remove()' in loop
            chromosomes.remove(i);
            item = rotateParcel(item);
            chromosomes.add(item);
        }
        return chromosomes;
    }

    /**
     * Crosses two individuals
     * @param parent2 the second parent
     * @return the child
     */
     public Individual twoPointCrossover(Individual parent2) {
        ArrayList<Parcel> childChromosomes = new ArrayList<>();
        int parent1Score = this.getScore();
        int parent2Score = parent2.getScore();
    
        int chromoSize = Math.min(this.chromosomes.size(), parent2.getChromosomes().size());
        int crossoverPoint1 = rn1.nextInt(chromoSize);
        int crossoverPoint2 = rn1.nextInt(chromoSize);
    
        if (crossoverPoint1 > crossoverPoint2) {
            int temp = crossoverPoint1;
            crossoverPoint1 = crossoverPoint2;
            crossoverPoint2 = temp;
        }
    
        if (parent1Score > parent2Score) {
            for (int i = 0; i < crossoverPoint1; i++) {
                childChromosomes.add(this.chromosomes.get(i));
            }
            for (int i = crossoverPoint2; i < chromoSize; i++) {
                childChromosomes.add(this.chromosomes.get(i));
            }
            for (int i = crossoverPoint1; i < crossoverPoint2; i++) {
                childChromosomes.add(parent2.getChromosomes().get(i));
            }
        } else {
            for (int i = 0; i < crossoverPoint1; i++) {
                childChromosomes.add(parent2.getChromosomes().get(i));
            }
            for (int i = crossoverPoint2; i < chromoSize; i++) {
                childChromosomes.add(parent2.getChromosomes().get(i));
            }
            // Minority from this parent
            for (int i = crossoverPoint1; i < crossoverPoint2; i++) {
                childChromosomes.add(this.chromosomes.get(i));
            }
        }
    
        fillRemaining(childChromosomes, Math.max(this.chromosomes.size(), parent2.getChromosomes().size()));
    
        if (rn1.nextDouble() < mutationProbability) {
            childChromosomes = mutation(childChromosomes);
        }
    
        return new Individual(this.knapsackGA, childChromosomes, mutationProbability);
    }
    

    /**
     * Sets up the individual
     * @param chromosomes the chromosomes to use
     */
    private void setUpIndividual(ArrayList<Parcel> chromosomes){
        // System.out.println("Setting up individual");
        this.chromosomes = chromosomes;
        // System.out.println("Chromosomes set");
        knapsackGA.chromosomeTranslator(chromosomes);
        // System.out.println("Chromosome translated");
        this.score = knapsackGA.getScore();
        // System.out.println("Score:" + score);
        int parcelsUsed = knapsackGA.getParcelsUsed();
        if (parcelsUsed <= chromosomes.size()) {
            chromosomes.subList(parcelsUsed, chromosomes.size()).clear();
            // System.out.println("Chromosomes cleared");
        } else {
            // System.out.println("Number of parcels used is greater than the number of chromosomes. Cannot clear chromosomes." + knapsackGA.getParcelsUsed());
        }
        // System.out.println(score);
        // System.out.println(knapsackGA.getParcelsUsed());
    }
}