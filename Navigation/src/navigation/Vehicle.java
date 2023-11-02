package miniproject1;

public class Vehicle {

    private Location location;
    private double currentFuel;

    //these two are final because these are base attributes of a vehicle that dont change
    private final double maxFuel;
    private final double gasMilage;

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

    public void travel(Location destination) {
        if (location.equals(destination)) {
            System.out.println("You're already there!");
            return;
        }

        double range = currentFuel * gasMilage;
        double distanceTo = location.distanceTo(destination);

        if (distanceTo > range) {
            System.out.println(String.format("Not enough gas to travel to %s.", destination.getName()));
        } else {
            double fuelCost = distanceTo / gasMilage;
            currentFuel -= fuelCost;
            location = destination;
            System.out.println(String.format("Successfully traveled to %s.", location.getName()));
        }
    }

    @Override
    public String toString() {
        return String.format("This vehicle is currently at %s, with %f gallons of a maximum of %f gallons of fuel remaining, with a gas milage of %s miles per gallon.",
                 location.getName(), currentFuel, maxFuel, gasMilage);
    }

}
