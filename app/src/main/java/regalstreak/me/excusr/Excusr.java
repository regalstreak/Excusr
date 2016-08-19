package regalstreak.me.excusr;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Excusr extends AppCompatActivity {

    private EditText customExcuse;
    private EditText customNumber;
    private CoordinatorLayout coordinatorLayout;
    static final int MY_PERMISSIONS_REQUEST_SEND_SMS = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_excusr);

        customExcuse = (EditText)findViewById(R.id.customExcuse);
        customNumber = (EditText)findViewById(R.id.customNumber);
        coordinatorLayout = (CoordinatorLayout)findViewById(R.id.coordinatorLayout);
    }

    public void sendText(View view){

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED){

            ActivityCompat.requestPermissions(Excusr.this,
                    new String[]{Manifest.permission.SEND_SMS},
                    MY_PERMISSIONS_REQUEST_SEND_SMS);
            return;
        }


        String number,excuse;

        number = customNumber.getText().toString();

        if(view.getId() == R.id.fab){
            excuse = customExcuse.getText().toString();
        }
        else{
            Button sender = (Button)view;
            excuse = sender.getText().toString();
        }

        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(number, null, "I won't be able to come today. " + excuse, null, null);

        Snackbar.make(coordinatorLayout, "Some shot", Snackbar.LENGTH_LONG)
                .show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_SEND_SMS: {
                    if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                        Toast.makeText(Excusr.this, "Permission for sending SMS was granted", Toast.LENGTH_LONG).show();

                        sendText(coordinatorLayout);

                    }
                    else {
                        Toast.makeText(Excusr.this, "Permisssion for sending SMS was denied.", Toast.LENGTH_LONG).show();
                    }

                    return;
            }
        }
    }
}