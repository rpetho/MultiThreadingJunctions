/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment;

import java.time.Clock;
import java.time.Instant;

/**
 *
 * @author Ryan
 */
public class Vehicle {

    public Vehicle() {

    }
    Clock clock = Clock.systemDefaultZone();

    String entryPointID = "";
    String Destination = "";
    String timeEntered;
    String timeParked;

    public String returnDestination() {
        return Destination;
    }

}
