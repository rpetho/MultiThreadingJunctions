/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment;

import static assignment.Assignment.NorthJunction;

public class Clock extends Thread {

    private static Clock instance = null;
    double tick = 0;
    public boolean clockFinished = false;

    Clock() {

    }

    public static Clock getInstance() {
        if (instance == null) {
            instance = new Clock();
        }

        return instance;
    }

    @Override
    public void run() {
        //Run the code inside while loop over and over until the tick reaches 360
        while (!Thread.currentThread().isInterrupted() && tick <= 360) {
            try {
                //Increases count of clock every 0.25 seconds
                tick++;
                System.out.println(getFormattedTick());

                Thread.sleep(250);

                //Prints out current time in program in format XmXs
//                System.out.print("Time: ");
//                System.out.println(getFormattedTick());
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
                clockFinished = true;
            }

//            Every "5 minutes" print out car park reports
            if (tick % 300 == 0) {
                System.out.print("Time: ");
                System.out.println(getFormattedTick());

                System.out.print("University: ");
                System.out.println(Assignment.uniAvailable);

                System.out.print("Station: ");
                System.out.println(Assignment.stationAvailable);

                System.out.print("Shopping Center: ");
                System.out.println(Assignment.shoppingAvailable);

                System.out.print("Industrial Park: ");
                System.out.println(Assignment.industrialAvailable);
            }

        }
        clockFinished = true;
    }

    public boolean isClockFinished() {
        return clockFinished;
    }

    //Gets the current count of clock in minutes and seconds form
    public String getFormattedTick() {
        //If time less than a minute
        if (tick < 60.0) {
            return ((int) tick + "s");
        } //If time more than a minute
        else {
            int x = (int) tick;
            int minCount = 0;
            while (x > 60) {
                minCount++;
                x -= 60;
            }

            return (minCount + "m" + x + "s");
        }
    }

}
