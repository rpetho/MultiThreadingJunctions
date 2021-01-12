package assignment;

/**
 *
 * @author Ryan
 */
class Road {

    private final Vehicle[] body;
    String RoadID;
    private int available = 0;
    private int nextIn = 0;
    private int nextOut = 0;
    public boolean hasCar = false;

    Road(int size, String curRoadID) {
        body = new Vehicle[size];
    }

    public synchronized void insert(Vehicle car) {
        while (available == body.length) {

            try {
                wait();
            } catch (InterruptedException ex) {
            }

        }
        body[nextIn] = car;
        available = 1 + available;

        try {
            Thread.sleep((int) (Math.random() * 10));
        } catch (InterruptedException ex) {
        }
        nextIn++;
        if (nextIn == body.length) {
            nextIn = 0;
        }
        if (available == body.length) {
            System.out.println("Road full");
        }
        notifyAll();
    }

    public synchronized Vehicle extract() {
        Vehicle car = null;
        while (available == 0) {
            try {
                wait();
            } catch (InterruptedException ex) {
            }
        }
        car = body[nextOut];
        try {
            Thread.sleep((int) (Math.random() * 10));
        } catch (InterruptedException ex) {
        }
        available--;
        nextOut++;
        if (nextOut == body.length) {
            nextOut = 0;
        }

        if (available == 0) {
            System.out.println("Road empty");
        }

        notifyAll();
        return car;
    }

    public boolean checkIfFull() {
        if (available == body.length) {
            return true;
        } else {
            return false;
        }
    }
    
    public int getUsage()
    {
        return available;
    }

    public boolean checkIfEmpty() {
        if (available > 0) {
            return false;
        } else {
            return true;
        }
    }


}