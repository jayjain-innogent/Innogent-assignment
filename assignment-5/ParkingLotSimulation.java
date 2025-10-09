import java.util.concurrent.*;

public class ParkingLotSimulation {

    private static final Semaphore parkingSpots = new Semaphore(3);

    static class Car extends Thread {

        private String carName;

        public Car(String name) {
            this.carName = name;
        }

        public void run() {
            try {
                System.out.println(carName + " is trying to enter the parking area");

                parkingSpots.acquire();
                System.out.println(carName + " has Parked");

                Thread.sleep(2000);

                System.err.println(carName + " is leaving the parking Lot");

                parkingSpots.release();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        for (int i = 1; i <= 6; i++) {
            Car car = new Car("Car- " + i);
            car.start();
        }
    }

}