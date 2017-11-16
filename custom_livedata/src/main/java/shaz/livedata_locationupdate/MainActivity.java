package shaz.livedata_locationupdate;

import android.arch.core.util.Function;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.Transformations;
import android.net.Network;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import shaz.livedata_locationupdate.livedata.BatteryLevelLiveData;
import shaz.livedata_locationupdate.livedata.LocationLiveData;
import shaz.livedata_locationupdate.pojo.Battery;
import shaz.livedata_locationupdate.pojo.BestLocation;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Example 1
        /*if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            LiveData<Network> networkLiveData = new NetworkLiveData(this);
            networkLiveData.observe(this, mNetworkObserver);
        }*/

        //Example 2
        LocationLiveData.getInstance(getApplicationContext()).observe(this, mLocationObserver);

        //Example 3
        /*LiveData<String> addressLiveData = new LocationAddressLiveData(this, this);
        addressLiveData.observe(this, mAddressObserver);*/

        //Example 4
        /*LiveData<Battery> batteryLiveData = new BatteryLevelLiveData(this);
        batteryLiveData.observe(this, mBatteryObserver);*/

        //Example 4 (Transformations)
        /*LiveData<Boolean> batteryLowStatusLiveData = Transformations.map(batteryLiveData, new Function<Battery, Boolean>() {
            @Override
            public Boolean apply(Battery input) {
                return input.getPercentage() <= 15;
            }
        });
        batteryLowStatusLiveData.observe(this, mBatteryLowStatus);*/

    }

    private Observer<BestLocation> mLocationObserver = new Observer<BestLocation>() {
        @Override
        public void onChanged(@Nullable BestLocation bestLocation) {
            Toast.makeText(MainActivity.this, bestLocation.toString(), Toast.LENGTH_LONG).show();
        }
    };

    private Observer<String> mAddressObserver = new Observer<String>() {
        @Override
        public void onChanged(@Nullable String s) {
            Toast.makeText(MainActivity.this, s, Toast.LENGTH_LONG).show();
        }
    };

    private Observer<Network> mNetworkObserver = new Observer<Network>() {
        @Override
        public void onChanged(@Nullable Network network) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Toast.makeText(MainActivity.this, "" + network.describeContents(), Toast.LENGTH_LONG).show();
            }
        }
    };

    private Observer<Battery> mBatteryObserver = new Observer<Battery>() {
        @Override
        public void onChanged(@Nullable Battery battery) {
            //TODO
        }
    };

    private Observer<Boolean> mBatteryLowStatus = new Observer<Boolean>() {
        @Override
        public void onChanged(@Nullable Boolean aBoolean) {
            //TODO
        }
    };
}
