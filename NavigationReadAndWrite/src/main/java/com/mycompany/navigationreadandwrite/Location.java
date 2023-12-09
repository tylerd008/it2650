/*
 * 
 * 
 * 
 */
package com.mycompany.navigationreadandwrite;

import static java.lang.Math.hypot;
import java.util.Objects;

public class Location {

    //all are final because a location isnt changing its name, moving, or changing if it has gas
    private final String name;
    private final double xCoord;
    private final double yCoord;
    private final boolean hasGas;

    public Location(String name, double xCoord, double yCoord, boolean hasGas) {
        this.name = name;
        this.xCoord = xCoord;
        this.yCoord = yCoord;
        this.hasGas = hasGas;
    }

    public boolean hasGas() {
        return hasGas;
    }
    
    public double getX(){
        return xCoord;
    }
    
    public double getY(){
        return yCoord;
    }

    public double distanceTo(Location destination) {
        double yDiff = destination.yCoord - yCoord;
        double xDiff = destination.xCoord - xCoord;

        return hypot(xDiff, yDiff);
    }
    
    public String getInfo(){
        String gasStatus;
        if (hasGas) {
            gasStatus = "and has gas";
        } else {
            gasStatus = "and does not have gas";
        }

        return String.format("%s is at (%f, %f) %s.", name, xCoord, yCoord, gasStatus);
    }

    @Override
    public boolean equals(Object object) {
        Location other;
        if (object instanceof Location) {
            other = (Location) object;
        } else {
            return false;
        }

        return Objects.equals(name, other.name)
                && xCoord == other.xCoord
                && yCoord == other.yCoord
                && hasGas == other.hasGas;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.name);
        hash = 89 * hash + (int) (Double.doubleToLongBits(this.xCoord) ^ (Double.doubleToLongBits(this.xCoord) >>> 32));
        hash = 89 * hash + (int) (Double.doubleToLongBits(this.yCoord) ^ (Double.doubleToLongBits(this.yCoord) >>> 32));
        hash = 89 * hash + (this.hasGas ? 1 : 0);
        return hash;
    }

    @Override
    public String toString() {
        return name;
    }
}
