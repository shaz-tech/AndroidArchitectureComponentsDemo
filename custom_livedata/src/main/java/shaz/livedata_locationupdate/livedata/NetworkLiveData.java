package shaz.livedata_locationupdate.livedata;

import android.annotation.TargetApi;
import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkRequest;
import android.os.Build;
import android.support.annotation.RequiresApi;

/**
 * Created by ${Shahbaz} on 08-11-2017
 */

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class NetworkLiveData extends LiveData<Network> {

    private ConnectivityManager mConnectivityManager;

    private ConnectivityManager.NetworkCallback mNetworkCallback = new ConnectivityManager.NetworkCallback(){
        @Override
        public void onAvailable(Network network) {
            super.onAvailable(network);
            postValue(network);
        }

        @Override
        public void onLost(Network network) {
            super.onLost(network);
            postValue(network);
        }
    };

    public NetworkLiveData(Context context) {
        mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    }

    @Override
    protected void onActive() {
        super.onActive();
        if(mConnectivityManager != null) {
            mConnectivityManager.registerNetworkCallback(getNetworkRequest(), mNetworkCallback);
        }
    }

    private NetworkRequest getNetworkRequest(){
        NetworkRequest.Builder builder = new NetworkRequest.Builder();
        return builder.addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
                .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR | NetworkCapabilities.TRANSPORT_WIFI)
                .build();
    }
}
