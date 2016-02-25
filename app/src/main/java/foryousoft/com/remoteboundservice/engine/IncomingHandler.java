package foryousoft.com.remoteboundservice.engine;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import android.widget.Toast;

import com.fasterxml.jackson.databind.ObjectMapper;

import foryousoft.com.remoteboundservice.ui.MainActivity;


class IncomingHandler extends Handler {

    private String TAG = getClass().getName();
    public static final int COUNT_ACTION = 100;
    public static final int UPPERCASE_ACTION = 101;
    private static String TEXT_MESSAGE_KEY = "TEXT_MESSAGE_KEY";
    public static String COUNT_KEY = "COUNT_KEY";
    private static String UPPERCASE_KEY = "UPPERCASE_KEY";

    @Override
    public void handleMessage(final Message incomingMessage) {

        final Messenger replyTo = incomingMessage.replyTo;
        final Bundle incomingBundle = incomingMessage.getData();
        final int action = incomingMessage.what;
        Log.e(TAG, " action: " + action + " textMessage: "+ incomingBundle.getString(TEXT_MESSAGE_KEY));

        new Thread(new Runnable() {

            @Override
            public void run() {

                Message message = Message.obtain();
                Bundle bundle = message.getData();
                String textMessage = incomingBundle.getString(TEXT_MESSAGE_KEY);
                switch(action) {
                    case COUNT_ACTION:
                        message.what = COUNT_ACTION;
                        bundle.putInt(COUNT_KEY, textMessage.length());
                    break;

                    case UPPERCASE_ACTION:
                        message.what = UPPERCASE_ACTION;
                        bundle.putString(UPPERCASE_KEY, textMessage.toUpperCase());
                        break;
                }

                try {
                    replyTo.send(message);
                }
                catch (Exception e) {
                   throw new RuntimeException(e);
                }
            }
        }).start();
    }
}