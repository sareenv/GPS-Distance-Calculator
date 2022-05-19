package com.company;

import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

public class Main {


    public static class Coordinates {

        private double longitude;
        private double latitude;

        public Coordinates(double longitude, double latitude) {
            this.longitude = longitude;
            this.latitude = latitude;
        }

        public void setLongitude(double longitude) {
            this.longitude = longitude;
        }

        public void setLatitude(double latitude) {
            this.latitude = latitude;
        }

        public double getLongitude() {
            return longitude;
        }

        public double getLatitude() {
            return latitude;
        }


        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Coordinates)) return false;
            Coordinates that = (Coordinates) o;
            return getLongitude() == that.getLongitude() && getLatitude() == that.getLatitude();
        }

        @Override
        public int hashCode() {
            return Objects.hash(getLongitude(), getLatitude());
        }
    }


    public double calculateDistance(Coordinates c1, Coordinates c2, boolean inDegrees) {
        // compute the distance between the coordinates.

        if (inDegrees) {
            double c1Latitude = (Math.PI / 180) * c1.getLatitude();
            double c1Longitude = (Math.PI / 180) * c1.getLongitude();
            double c2Latitude = (Math.PI / 180) * c2.getLatitude();
            double c2Longitude = (Math.PI / 180) * c2.getLongitude();
            c1.setLatitude(c1Latitude);
            c1.setLongitude(c1Longitude);
            c2.setLongitude(c2Longitude);
            c2.setLatitude(c2Latitude);
        }

        /*
        * Haversine formula
        * References:
        * 1. https://en.wikipedia.org/wiki/Haversine_formula
        * 2. https://www.movable-type.co.uk/scripts/latlong.html
        * */

        double R = 6371;
        double latitudeChange = c2.getLatitude() - c1.getLatitude();
        double longitudeChange = c2.getLongitude() - c1.getLongitude();
        double a = Math.sin(latitudeChange/2) * Math.sin(latitudeChange/2) +
                Math.cos(c1.getLatitude()) * Math.cos(c2.getLatitude()) * Math.sin(longitudeChange/2)
                        * Math.sin(longitudeChange/2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c;

    }


    public static void main(String[] args) {

        try (Scanner snc = new Scanner(System.in)) {
            System.out.println("Enter the longitude of gps coordinate point 1 ");
            snc.nextLong();
            System.out.println("Enter the latitude of gps coordinate point 2");
            snc.nextLong();
        } catch (InputMismatchException exception) {
            System.out.println("Exception: " + exception.getMessage());
        }


        Main main = new Main();
        Coordinates c1 = new Coordinates(0, 51.5);
        Coordinates c2 = new Coordinates(-77.1, 38.8);
        double estimatedDistance = main.calculateDistance(c1, c2, true);
        System.out.println("Estimated Distance is : " + estimatedDistance);
    }

}
