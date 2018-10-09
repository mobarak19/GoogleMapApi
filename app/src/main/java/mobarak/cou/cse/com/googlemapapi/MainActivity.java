package mobarak.cou.cse.com.googlemapapi;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private static final int ERROE_DIALOG_REQUEST=9001;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(isServiceOk()){
            init();
        }
    }

    public void init(){

        Button button=findViewById(R.id.gotomap);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,MapActivity.class);
                startActivity(intent);
            }
        });
    }

    public boolean isServiceOk(){

        Log.d(TAG, "isServiceOk: ");
        int available= GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(MainActivity.this);
        if(available== ConnectionResult.SUCCESS){
            Log.d(TAG, "isServiceOk: google play service is working");
            return true;
            
        }else if(GoogleApiAvailability.getInstance().isUserResolvableError(available)){

            Log.d(TAG, "isServiceOk: an errod occord but we can resoulved it");
            Dialog dialog=GoogleApiAvailability.getInstance().getErrorDialog(MainActivity.this,available,ERROE_DIALOG_REQUEST);
            dialog.show();

        }else {
            Toast.makeText(this,"we cant make map request",Toast.LENGTH_SHORT).show();
        }
        return false;

    }
}
