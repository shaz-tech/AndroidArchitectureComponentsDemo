package shaz.livedata_locationupdate.livedata;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.support.annotation.Nullable;

import java.util.List;
import java.util.Locale;

import shaz.livedata_locationupdate.pojo.BestLocation;

/**
 * Created by ${Shahbaz} on 08-11-2017
 */

public class LocationAddressLiveData extends LiveData<String> {

    public LocationAddressLiveData(final LifecycleOwner owner, final Context context) {
        LocationLiveData.getInstance(context.getApplicationContext()).observe(owner, new Observer<BestLocation>() {
            @Override
            public void onChanged(@Nullable BestLocation bestLocation) {
                fetchAddress(context, bestLocation);
            }
        });
    }

    public void fetchAddress(final Context context, final BestLocation location) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                try {
                    Geocoder geocoder = new Geocoder(context, Locale.getDefault());
                    List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                    if (addresses != null && addresses.size() > 0) {
                        Address finalAddress = addresses.get(0);

                        //Calling from Background thread.
                        postValue(finalAddress.toString());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }
        }.execute();
    }
}
