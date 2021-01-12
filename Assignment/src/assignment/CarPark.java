/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ryan
 */
public class CarPark extends Thread {

    public Road Road;
    public CarPark CarPark;

    public Vehicle tempVehicle;

    String CarParkID;
    private Vehicle[] body;
    public int available = 0;
    private int nextIn = 0;
    private int nextOut = 0;

    public int carsParked;
    static Semaphore semaphore = new Semaphore(4);

    @Override
    public void run() {

        while (!Clock.interrupted()) {
            try {
                if (CarParkID == "UCP") {
                    Thread.sleep(1000);

                    this.insert(Assignment.UniversityRoad.extract());
                }
                if (CarParkID == "ICP") {
                    Thread.sleep(1000);

                    this.insert(Assignment.industiralRoad.extract());
                }
                if (CarParkID == "SCP") {
                    Thread.sleep(1000);

                    this.insert(Assignment.StationRoad.extract());
                }
                if (CarParkID == "SPCP") {
                    Thread.sleep(1000);

                    this.insert(Assignment.ShoppingCenterRoad.extract());
                }
                System.out.println("Car Parked");
                Assignment.Car_Amount--;
            } catch (InterruptedException ex) {
                Logger.getLogger(CarPark.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        if (Clock.interrupted()) {
            Thread.currentThread().interrupt();
        }
    }

    CarPark(int size, String CarParkID) {
        body = new Vehicle[size];
        this.CarParkID = CarParkID;
    }

    public synchronized void insert(Vehicle car) {
        while (available == body.length) {

            try {
                wait();
            } catch (InterruptedException ex) {
            }

        }

        body[nextIn] = car;

        car.timeParked = Assignment.clock.getFormattedTick();

        try {
            Thread.sleep((int) (Math.random() * 10));
        } catch (InterruptedException ex) {
        }
        nextIn++;
        carsParked++;

        if (nextIn == body.length) {
            nextIn = 0;
        }
        if (available == body.length) {
            System.out.println("CarPark full");
        }
        notifyAll();
    }

    public int getCarsParked() {
        return carsParked;
    }

    public Vehicle[] getBody() {
        return body;
    }

    public void endThread() {
        if (Clock.interrupted()) {
            Thread.currentThread().interrupt();
        }
    }

}
