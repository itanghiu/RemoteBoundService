package foryousoft.com.remoteboundservice.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import foryousoft.com.remoteboundservice.R;

public class MainActivity extends AppCompatActivity {

    private TextView messageTextView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        messageTextView= (TextView)findViewById(R.id.info);
    }

    public void displayMessage(String message) {
        messageTextView.setText(message);
    }

}
