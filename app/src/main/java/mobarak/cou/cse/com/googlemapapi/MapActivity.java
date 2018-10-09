package mobarak.cou.cse.com.googlemapapi;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {

    private static final String TAG = "MapActivity";

    private static final String FINE_LOCATION=Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String COURSE_LOCATION= Manifest.permission.ACCESS_COARSE_LOCATION;

    private boolean mLocation_Permission_granted=false;

    private static final int LOCATION_PERMISSION_REQUEST_CODE=1234;

    GoogleMap map;
    @Override
    public void onMapReady(GoogleMap googleMap) {
        map=googleMap;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        getLocationPermission();

    }

    private void initMap(){
        SupportMapFragment supportMapFragment= (SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map);
        supportMapFragment.getMapAsync(this);
    }


    public void getLocationPermission(){

        String[] permissions={Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.ACCESS_FINE_LOCATION};
        if(ContextCompat.checkSelfPermission(MapActivity.this,FINE_LOCATION)== PackageManager.PERMISSION_GRANTED){
            if(ContextCompat.checkSelfPermission(MapActivity.this,COURSE_LOCATION)== PackageManager.PERMISSION_GRANTED){
                mLocation_Permission_granted=true;
            }else {

                ActivityCompat.requestPermissions(MapActivity.this,permissions,LOCATION_PERMISSION_REQUEST_CODE);
            }
        }else {
            ActivityCompat.requestPermissions(MapActivity.this,permissions,LOCATION_PERMISSION_REQUEST_CODE);

        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        mLocation_Permission_granted=false;

        if(requestCode>0){
            for (int i=0;i<grantResults.length;i++){
                if(grantResults[0]!=PackageManager.PERMISSION_GRANTED){
                    mLocation_Permission_granted=false;

                    return;
                }

            }
            mLocation_Permission_granted=true;
            initMap();

        }

    }


}
