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
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                parkingSpots.release();
            }
        }
    }

    public static void main(String[] args) {

        int n = args.length > 0 ? Integer.parseInt(args[0]) : 10;

        if (n<=0){
            throw new IllegalArgumentException("Number must be greater than 0.");
        }

        for (int i = 1; i <= 6; i++) {
            Car car = new Car("Car- " + i);
            car.start();
        }
    }

}