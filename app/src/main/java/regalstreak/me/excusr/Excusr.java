package regalstreak.me.excusr;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_excusr);

        customExcuse = (EditText)findViewById(R.id.customExcuse);
        customNumber = (EditText)findViewById(R.id.customNumber);
    }

    public void sendText(View view){

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

    }


}