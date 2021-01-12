/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ryan
 */
public class Junction extends Thread {

    public String junctionID;
    Road Road;
    double tick = 0;

    int CarsWaiting;
    Vehicle TempVehicle;

    static Semaphore semaphore = new Semaphore(4);

    public Junction(String CurJunctionID) {
        junctionID = CurJunctionID;
    }

    public void GreenLight() {
        tick = 0;
        while (tick < 100) {
            tick++;
            if (junctionID == "WJ") {
                junctionUpdate();

                TempVehicle = Assignment.NorthJtoWest.extract();
                if ("University".equals(TempVehicle.returnDestination())) {
                    Assignment.UniversityRoad.insert(TempVehicle);
                }
                if ("Station".equals(TempVehicle.returnDestination())) {
                    Assignment.StationRoad.insert(TempVehicle);
                }
                junctionUpdate();
            } else if (junctionID == "NJ") {
                junctionUpdate();

                if (!Assignment.NorthRoad.checkIfEmpty()) {
                    TempVehicle = Assignment.NorthRoad.extract();

                } else {
                    TempVehicle = Assignment.EastJtoNorth.extract();
                }
                try {

                    if ("University".equals(TempVehicle.returnDestination())) {
                        Assignment.NorthJtoWest.insert(TempVehicle);
                    } else if ("Station".equals(TempVehicle.returnDestination())) {
                        Assignment.NorthJtoWest.insert(TempVehicle);
                    } else if ("Shopping Center".equals(TempVehicle.returnDestination())) {
                        Assignment.ShoppingCenterRoad.insert(TempVehicle);
                    } else if ("Industrial Park".equals(TempVehicle.returnDestination())) {
                        Assignment.NorthJtoEast.insert(TempVehicle);
                    }

                } catch (Exception e) {
                    System.out.println("No Cars Waiting");
                }
                junctionUpdate();

            } else if (junctionID == "EJ") {
                junctionUpdate();

                if (!Assignment.EastRoad.checkIfEmpty()) {
                    TempVehicle = Assignment.EastRoad.extract();
                } else if (!Assignment.EastRoad.checkIfEmpty()) {
                    TempVehicle = Assignment.NorthJtoEast.extract();
                } else if (!Assignment.SouthJtoEast.checkIfEmpty()) {
                    TempVehicle = Assignment.NorthJtoEast.extract();
                }

                try {
                    if ("University".equals(TempVehicle.returnDestination())) {
                        Assignment.EastJtoNorth.insert(TempVehicle);
                    } else if ("Station".equals(TempVehicle.returnDestination())) {
                        Assignment.EastJtoNorth.insert(TempVehicle);
                    } else if ("Shopping Center".equals(TempVehicle.returnDestination())) {
                        Assignment.EastJtoNorth.insert(TempVehicle);
                    } else if ("Industrial Park".equals(TempVehicle.returnDestination())) {
                        Assignment.EastJtoSouth.insert(TempVehicle);
                    }
                } catch (Exception e) {
                    System.out.println("No Cars Waiting");
                }
                junctionUpdate();

            } else if (junctionID == "SJ") {
                junctionUpdate();

                if (!Assignment.SouthRoad.checkIfEmpty()) {
                    TempVehicle = Assignment.SouthRoad.extract();
                } else if (!Assignment.EastJtoSouth.checkIfEmpty()) {
                    TempVehicle = Assignment.EastJtoSouth.extract();
                }
                try {

                    if ("University".equals(TempVehicle.returnDestination())) {
                        Assignment.SouthJtoEast.insert(TempVehicle);
                    } else if ("Station".equals(TempVehicle.returnDestination())) {
                        Assignment.SouthJtoEast.insert(TempVehicle);
                    } else if ("Shopping Center".equals(TempVehicle.returnDestination())) {
                        Assignment.SouthJtoEast.insert(TempVehicle);
                    } else if ("Industrial Park".equals(TempVehicle.returnDestination())) {
                        Assignment.industiralRoad.insert(TempVehicle);
                    }

                } catch (Exception e) {
                    System.out.println("No Cars Waiting");
                }
                junctionUpdate();

            }
        }
    }

    public void RedLight() {

        try {
            Thread.sleep(7500);

        } catch (InterruptedException ex) {
            Logger.getLogger(Junction.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            RedLight();
            junctionUpdate();
            GreenLight();
            CarsWaiting = 0;
        }
        System.out.println("What?");
    }

    public void endThread() {
        if (Clock.interrupted()) {
            Thread.currentThread().interrupt();
        }

    }

    public void junctionUpdate() {
        FileWriter fstream = null;

        CarsWaiting = 0;
        if (junctionID == "WJ") {
            CarsWaiting = Assignment.NorthJtoWest.getUsage();
        } else if (junctionID == "NJ") {
            CarsWaiting = Assignment.NorthRoad.getUsage();
            CarsWaiting = CarsWaiting + Assignment.EastJtoNorth.getUsage();
        } else if (junctionID == "EJ") {
            CarsWaiting = Assignment.EastRoad.getUsage();
            CarsWaiting = CarsWaiting + Assignment.NorthJtoEast.getUsage();
            CarsWaiting = CarsWaiting + Assignment.SouthJtoEast.getUsage();

        } else if (junctionID == "SJ") {
            CarsWaiting = Assignment.SouthRoad.getUsage();
            CarsWaiting = CarsWaiting + Assignment.EastJtoSouth.getUsage();
        }

        try {
            fstream = new FileWriter("Junction" + junctionID + ".txt", true);
        } catch (IOException ex) {
            Logger.getLogger(Junction.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            BufferedWriter out = new BufferedWriter(fstream);
            out.newLine();
            fstream = new FileWriter("Junction" + junctionID + ".txt", true);

            try {
                out.write("Time:" + Assignment.clock.getFormattedTick() + " - Junction " + junctionID + ": " + CarsWaiting + " Cars Waiting");
                out.close();
            } catch (IOException ex1) {
                Logger.getLogger(Junction.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } catch (IOException ex) {
            Logger.getLogger(Junction.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

}
