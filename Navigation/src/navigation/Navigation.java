package miniproject1;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Navigation {

    public static void main(String[] args) {

        ArrayList<Location> locations = new ArrayList();

        locations.add(new Location("Cleveland", 0, 0, true));
        locations.add(new Location("North Pole", 500, -100, true));
        locations.add(new Location("Campbell Hill", -50, -50, false));
        locations.add(new Location("Tokyo", -100, 1000, true));
        locations.add(new Location("Chicago", -200, 75, true));
        locations.add(new Location("Erie, PA", 150, 0, true));
        locations.add(new Location("Allegheny National Forest", 300, -75, false));

        System.out.println("Please set the vehicle's maximum fuel capacity (gallons): ");
        double maxFuel = getSafePositiveDoubleInput();
        System.out.println("Please set the vehicle's fuel efficiency (miles per gallon): ");
        double gasMilage = getSafePositiveDoubleInput();

        Vehicle vehicle = new Vehicle(locations.get(0), maxFuel, gasMilage);

        System.out.println("The available locations are: ");

        int numLocations = locations.size();

        for (int i = 0; i < numLocations; i++) {
            System.out.println(String.format("Location %d: %s ", i + 1, locations.get(i).getName()));
        }

        int input;

        while (true) {
            System.out.println("Enter 1 to travel to a location,");
            System.out.println("enter 2 to get information for a location,");
            System.out.println("enter 3 to get information for the vehicle,");
            System.out.println("enter 4 to refuel if fuel is at the vehicle's current location,");
            System.out.println("and enter 0 to exit the program.");

            input = getSafeInt();

            switch (input) {
                case 1 -> {
                    System.out.println("Please input the number for the location you want to travel to: ");
                    input = getSafeInt();
                    if (input < 1 || input > numLocations) {
                        System.out.println("Not a valid location number!");
                        continue;
                    }
                    vehicle.travel(locations.get(input - 1));
                }
                case 2 -> {
                    System.out.println("Please input the number for the location you want information for: ");
                    input = getSafeInt();
                    if (input < 1 || input > numLocations) {
                        System.out.println("Not a valid location number!");
                        continue;
                    }
                    System.out.println(locations.get(input - 1));
                }
                case 3 -> {
                    System.out.println(vehicle);
                }
                case 4 -> {
                    vehicle.refuel();
                }
                case 0 -> {
                    return;
                }
                default ->
                    System.out.println("Please input one of the mentioned numbers!");
            }
        }
    }

    //the word "safe" is used here to refer to built in handling for improper input
    private static int getSafeInt() {
        Scanner input = new Scanner(System.in);
        int userInput = 0;
        boolean acceptableInput = false;
        do {
            try {
                userInput = input.nextInt();
                acceptableInput = true;
            } catch (InputMismatchException e) {
                System.out.println("Please input a number!");
                input.nextLine();
            }
        } while (!acceptableInput);

        return userInput;
    }

    private static double getSafePositiveDoubleInput() {
        Scanner input = new Scanner(System.in);
        double userInput = 0;
        boolean acceptableInput = false;
        do {
            try {
                userInput = input.nextDouble();
                if (userInput <= 0) {
                    throw new InputMismatchException();
                }
                acceptableInput = true;
            } catch (InputMismatchException e) {
                System.out.println("Please enter a number greater than 0!");
                input.nextLine();
            }
        } while (!acceptableInput);

        return userInput;
    }
}
