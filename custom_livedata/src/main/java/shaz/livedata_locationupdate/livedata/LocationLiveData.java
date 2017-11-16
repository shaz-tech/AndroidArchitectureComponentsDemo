package shaz.livedata_locationupdate.livedata;

import android.Manifest;
import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Looper;
import android.support.v4.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;

import shaz.livedata_locationupdate.pojo.BestLocation;

/**
 * Created by ${Shahbaz} on 08-11-2017
 */

public class LocationLiveData extends LiveData<BestLocation> {
    private static LocationLiveData mInstance;
    private Context mContext;
    private FusedLocationProviderClient mFusedLocationProviderClient;
    private LocationRequest mLocationRequest;

    public static LocationLiveData getInstance(Context applicationContext) {
        if (mInstance == null)
            mInstance = new LocationLiveData(applicationContext);
        return mInstance;
    }

    private LocationLiveData(Context context) {
        this.mContext = context;
    }

    @Override
    protected void onActive() {
        super.onActive();
        if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        FusedLocationProviderClient providerClient = getLocationProviderClient();
        LocationRequest locationRequest = getLocationRequest();
        Looper looper = Looper.myLooper();
        providerClient.requestLocationUpdates(locationRequest, mLocationCallback, looper);
    }

    @Override
    protected void onInactive() {
        super.onInactive();
        if (mFusedLocationProviderClient != null)
            mFusedLocationProviderClient.removeLocationUpdates(mLocationCallback);
    }

    private LocationRequest getLocationRequest() {
        if (mLocationRequest == null)
            mLocationRequest = new LocationRequest()
                    .setInterval(10000)
                    .setMaxWaitTime(30000)
                    .setFastestInterval(5000)
                    .setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        return mLocationRequest;
    }

    private FusedLocationProviderClient getLocationProviderClient() {
        if (mFusedLocationProviderClient == null) {
            mFusedLocationProviderClient = new FusedLocationProviderClient(mContext);
        }
        return mFusedLocationProviderClient;
    }

    private LocationCallback mLocationCallback = new LocationCallback() {
        @Override
        public void onLocationResult(LocationResult locationResult) {
            super.onLocationResult(locationResult);
            Location lastLocation = locationResult.getLastLocation();
            BestLocation bestLocation = new BestLocation(lastLocation.getLatitude(), lastLocation.getLongitude(), lastLocation.getAccuracy());

            //Calling from Non-Background thread.
            setValue(bestLocation);
        }
    };
}
