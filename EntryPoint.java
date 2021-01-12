/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ryan
 */
public class EntryPoint extends Thread {

    Vehicle Vehicle;
    String EntryPoint;

    public void setDestination() {
        float random = (float) Math.random();
        if (random < 0.1) {
            Vehicle.Destination = "University";
        } else if (random > 0.1 && random < 0.3) {
            Vehicle.Destination = "Station";
        } else if (random > 0.3 && random < 0.6) {
            Vehicle.Destination = "Shopping Center";
        } else if (random > 0.6) {
            Vehicle.Destination = "Industrial Park";
        }
    }

    public void endThread() throws InterruptedException {

        Thread.currentThread().interrupt();
        Thread.currentThread().join();

        if (Clock.interrupted()) {
        }
    }

    public EntryPoint(String CarEntryPoint) {
        EntryPoint = CarEntryPoint;
    }

    @Override
    public synchronized void run() {
        Thread.currentThread().setPriority(10);
        switch (EntryPoint) {
            case "North":
                for (int i = 0; i < 550; i++) {
                    this.Vehicle = new Vehicle();
                    Assignment.Car_Amount++;
                    setDestination();
                    Vehicle.entryPointID = EntryPoint;
                    Vehicle.timeEntered = Assignment.clock.getFormattedTick();
                    Assignment.NorthRoad.insert(Vehicle);
                }
                
                break;
            case "East":
                for (int i = 0; i < 300; i++) {
                    this.Vehicle = new Vehicle();
                    Assignment.Car_Amount++;
                    setDestination();
                    Vehicle.entryPointID = EntryPoint;
                    Vehicle.timeEntered = Assignment.clock.getFormattedTick();
                    Assignment.EastRoad.insert(Vehicle);
                }
                break;
            case "South":
                for (int i = 0; i < 550; i++) {


                    this.Vehicle = new Vehicle();
                    Assignment.Car_Amount++;
                    setDestination();
                    Vehicle.entryPointID = EntryPoint;
                    Vehicle.timeEntered = Assignment.clock.getFormattedTick();
                    Assignment.SouthRoad.insert(Vehicle);
                }
                System.out.println("WHy ");
                break;

        }

//        while (!Clock.interrupted()) {
//
//            if (EntryPoint == "North") {
//                
//                
//                try {
//                    Thread.sleep(900);
//                } catch (InterruptedException ex) {
//                    Logger.getLogger(EntryPoint.class.getName()).log(Level.SEVERE, null, ex);
//                }
//
//                this.Vehicle = new Vehicle();
//                Assignment.Car_Amount++;
//                setDestination();
//                Vehicle.entryPointID = EntryPoint;
//                Vehicle.timeEntered = Assignment.Clock.getFormattedTick();
//                Assignment.NorthRoad.insert(Vehicle);
//
//            } else if (EntryPoint == "East") {
//
//                try {
//                    Thread.sleep(1500);
//                } catch (InterruptedException ex) {
//                    Logger.getLogger(EntryPoint.class.getName()).log(Level.SEVERE, null, ex);
//                }
//
//                this.Vehicle = new Vehicle();
//                Assignment.Car_Amount++;
//
//                setDestination();
//                Vehicle.entryPointID = EntryPoint;
//                Vehicle.timeEntered = Assignment.Clock.getFormattedTick();
//
//                Assignment.EastRoad.insert(Vehicle);
//            } else if (EntryPoint == "South") {
//
//                try {
//                    Thread.sleep(900);
//                } catch (InterruptedException ex) {
//                    Logger.getLogger(EntryPoint.class.getName()).log(Level.SEVERE, null, ex);
//                }
//
//                this.Vehicle = new Vehicle();
//                Assignment.Car_Amount++;
//                setDestination();
//                Vehicle.entryPointID = EntryPoint;
//                Vehicle.timeEntered = Assignment.Clock.getFormattedTick();
//                Assignment.SouthRoad.insert(Vehicle);
//            }
//        }
    }

}
