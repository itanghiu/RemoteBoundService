package foryousoft.com.remoteboundservice.engine;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.Messenger;
import android.util.Log;

/**
 * Created by I-Tang HIU  on 12/02/2016.
 */
public class RemoteService extends Service {

    private String TAG = getClass().getName();
    private IncomingHandler incomingHandler ;
    private Messenger messenger;
    private static Context context;

    public RemoteService() {
    }

    public RemoteService(Context context) {
        this.context = context;
    }

    @Override
    public void onCreate() {
        Log.d(TAG, "In onCreate ");
    }

    @Override
    public IBinder onBind(Intent intent) {

        incomingHandler =  new IncomingHandler();
        messenger = new Messenger(incomingHandler);
        return messenger.getBinder();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return false;
    }

}