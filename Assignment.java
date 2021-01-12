/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.concurrent.Semaphore;

/**
 *
 * @author Ryan
 */
public class Assignment {

    static Clock clock = new Clock();
    //Entrance Point Roads
    static Road NorthRoad;
    static Road EastRoad;
    static Road SouthRoad;

    //Roads to Parking lots
    static Road ShoppingCenterRoad;
    static Road UniversityRoad;
    static Road StationRoad;
    static Road industiralRoad;

    //Roads between Junctions
    static Road NorthJtoWest;
    static Road NorthJtoEast;
    static Road EastJtoNorth;
    static Road EastJtoSouth;
    static Road SouthJtoEast;

    //Junctions
    static Junction NorthJunction;
    static Junction WestJucntion;
    static Junction EastJunction;
    static Junction SouthJunction;

    static CarPark UniCP;
    static CarPark StationCP;
    static CarPark ShoppingCP;
    static CarPark IndustrialCP;

    static Clock Clock;
    static boolean clockFinished = false;

    static int Car_Amount = 0;

    public static int uniAvailable = 0;
    public static int stationAvailable = 0;
    public static int shoppingAvailable = 0;
    public static int industrialAvailable = 0;

    static Semaphore semaphore = new Semaphore(4);

    public static int OutPutAvailble(String CarParkID) {

        if (CarParkID == "UCP") {
            return UniCP.available;
        } else if (CarParkID == "ICP") {
            return IndustrialCP.available;
        } else if (CarParkID == "SCP") {
            return StationCP.available;
        } else if (CarParkID == "SPCP") {
            return ShoppingCP.available;

        }
        return 0;
    }

    public static void main(String[] args) throws InterruptedException, FileNotFoundException {

        //Roads
        NorthRoad = new Road(50, "NR");
        EastRoad = new Road(30, "ER");
        SouthRoad = new Road(60, "SR");

        //Roads to Parking Lots
        ShoppingCenterRoad = new Road(7, "SCR");
        UniversityRoad = new Road(15, "UR");
        StationRoad = new Road(15, "SR");
        industiralRoad = new Road(15, "IR");

        // Roads between Junctions
        NorthJtoWest = new Road(10, "NJWR");
        NorthJtoEast = new Road(10, "NJER");
        EastJtoNorth = new Road(10, "EJNR");
        EastJtoSouth = new Road(7, "EJSR");
        SouthJtoEast = new Road(7, "SJER");

        //EntryPoints
        EntryPoint NorthEP = new EntryPoint("North");
        EntryPoint EastEP = new EntryPoint("East");
        EntryPoint SouthEP = new EntryPoint("South");

        // CarParks
        CarPark UniCP = new CarPark(100, "UCP");
        CarPark StationCP = new CarPark(150, "SCP");
        CarPark ShoppingCP = new CarPark(400, "SPCP");
        CarPark IndustrialCP = new CarPark(1000, "ICP");

        // Junctions
        NorthJunction = new Junction("NJ");
        EastJunction = new Junction("EJ");
        WestJucntion = new Junction("WJ");
        SouthJunction = new Junction("SJ");

        // Running threads
        new Thread(clock).start();

        new Thread(NorthEP).start();
        new Thread(EastEP).start();
        new Thread(SouthEP).start();
        
        new Thread(UniCP).start();
        new Thread(StationCP).start();
        new Thread(ShoppingCP).start();
        new Thread(IndustrialCP).start();

        new Thread(NorthJunction).start();
        new Thread(EastJunction).start();
        new Thread(WestJucntion).start();
        new Thread(SouthJunction).start();

        while (!clock.isClockFinished()) {
            // Works out the avalible spaces in each parking lot

        }

        if (clock.isClockFinished()) {
            uniAvailable = (UniCP.getBody().length - UniCP.carsParked);
            stationAvailable = (StationCP.getBody().length - StationCP.carsParked);
            shoppingAvailable = (ShoppingCP.getBody().length - ShoppingCP.carsParked);
            industrialAvailable = (IndustrialCP.getBody().length - IndustrialCP.carsParked);

        }
        System.out.println("Univeristy: ");
        System.out.print(UniCP.carsParked);
        System.out.print(" Cars Parked");

        System.out.println("Train Station: ");
        System.out.print(StationCP.carsParked);
        System.out.print(" Cars Parked");

        System.out.println("Shopping Center: ");
        System.out.print(ShoppingCP.carsParked);
        System.out.print(" Cars Parked");

        System.out.println("Industrial Park: ");
        System.out.print(IndustrialCP.carsParked);
        System.out.print(" Cars Parked");

    }

}
