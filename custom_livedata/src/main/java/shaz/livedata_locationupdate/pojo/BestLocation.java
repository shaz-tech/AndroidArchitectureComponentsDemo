package shaz.livedata_locationupdate.pojo;

/**
 * Created by ${Shahbaz} on 08-11-2017
 */

public class BestLocation {
    private final double latitude;
    private final double longitude;
    private final float accuracy;

    public BestLocation(double latitude, double longitude, float accuracy) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.accuracy = accuracy;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public float getAccuracy() {
        return accuracy;
    }

    @Override
    public String toString() {
        return "Lat: " + latitude + ", Lon: " + longitude + ", Acc: " + accuracy;
    }
}
