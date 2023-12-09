/*
 * 
 * 
 * 
 */
package com.mycompany.navigationreadandwrite;



public class Vehicle {

    private Location location;
    private double currentFuel;

    //these two are final because these are base attributes of a vehicle that dont change
    private final double maxFuel;
    private final double gasMilage;
    
    public enum travelOutcome{
        alreadyThere, notEnoughFuel, success
    }

    public Vehicle(Location location, double maxFuel, double gasMilage) {
        this.location = location;
        this.maxFuel = maxFuel;
        this.gasMilage = gasMilage;
        currentFuel = maxFuel;
    }

    public void refuel() {
        if (location.hasGas()) {
            currentFuel = maxFuel;
            System.out.println("Vehicle refueled.");
        } else {
            System.out.println("Cannot refuel, location does not have gas.");
        }
    }

    public travelOutcome travel(Location destination) {
        if (location.equals(destination)) {
            System.out.println("You're already there!");
            return travelOutcome.alreadyThere;
        }

        double range = currentFuel * gasMilage;
        double distanceTo = location.distanceTo(destination);

        if (distanceTo > range) {
            return travelOutcome.notEnoughFuel;
        } else {
            double fuelCost = distanceTo / gasMilage;
            currentFuel -= fuelCost;
            location = destination;
            return travelOutcome.success;
        }
    }
    
    public double getCurrentFuel(){
        return currentFuel;
    }
    
    public Location getLocation(){
        return location;
    }
    
    @Override
    public String toString() {
        return String.format("This vehicle is currently at %s, with %f gallons of a maximum of %f gallons of fuel remaining, with a gas milage of %s miles per gallon.",
                 location.toString(), currentFuel, maxFuel, gasMilage);
    }

}
