package il.ma.mobile_security;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraManager;
import android.net.wifi.WifiManager;
import android.net.wifi.aware.WifiAwareManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {


    private EditText main_activity_ET_user_input;
    private Button main_activity_BTN_login;
    BluetoothAdapter bluetoothAdapter;
    CameraManager cameraManager;
    private boolean flashState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("pttt", "onCreate called");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpViews();

        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);


        CameraManager.TorchCallback torchCallback = new CameraManager.TorchCallback() {
            @Override
            public void onTorchModeUnavailable(String cameraId) {
                super.onTorchModeUnavailable(cameraId);
            }

            @Override
            public void onTorchModeChanged(String cameraId, boolean enabled) {
                super.onTorchModeChanged(cameraId, enabled);
                flashState = enabled;
            }
        };
        cameraManager.registerTorchCallback(torchCallback, null);// (callback, handler)





        main_activity_BTN_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkLogin();
            }
        });

    }

    private void setUpViews() {

        main_activity_ET_user_input = findViewById(R.id.main_activity_ET_user_input);
        main_activity_BTN_login = findViewById(R.id.main_activity_BTN_login);

    }
    private void checkBluetooth() {

        if (bluetoothAdapter != null) {
            Log.d("pttt", "bluetoothAdapter is not null");

            if (bluetoothAdapter.isEnabled()) {

                Log.d("pttt", "bluetoothAdapter is Enabled = true");

            }
        }


    }
    private void checkWifi() {
        Log.d("pttt", "checkWifi called");

//        if(getPackageManager().hasSystemFeature(PackageManager.FEATURE_WIFI_AWARE))
//        {
            Log.d("pttt", "WIFI is supported");
        WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);


            if(wifiManager.isWifiEnabled())
            {
                Log.d("pttt", "WIFI is enabled");
            }

        }

//    }
        private void checkLogin() {
        Log.d("pttt", "checkLogin called");
//        checkBluetooth();
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                checkWifi();
//            }
//            checkDoNotDisturb();
//            checkFlashlight();
//            checkAirplane();
            checkStorage();
        }
    public static String floatForm (double d)
    {
        return new DecimalFormat("#.##").format(d);
    }


    public static String bytesToHuman (long size)
    {
        long Kb = 1024;
        long Mb = Kb * 1024;
        long Gb = Mb * 1024;
        long Tb = Gb * 1024;
        long Pb = Tb * 1024;
        long Eb = Pb * 1024;

        if (size <  Kb)   return floatForm(size) + " byte";
        if (size < Mb)    return floatForm((double)size / Kb) + " Kb";
        if (size < Gb)    return floatForm((double)size / Mb) + " Mb";
        if (size < Tb)    return floatForm((double)size / Gb) + " Gb";
        if (size < Pb)    return floatForm((double)size / Tb) + " Tb";
        if (size < Eb)    return floatForm((double)size / Pb) + " Pb";
        return floatForm((double)size / Eb) + " Eb";

    }
    private void checkStorage() {
        StatFs stat = new StatFs(Environment.getDataDirectory().getAbsolutePath());
        long bytesAvailable = stat.getBlockSizeLong() * stat.getAvailableBlocksLong();
        Log.d("pttt","Available : "+bytesToHuman(bytesAvailable));
    }

    private void checkAirplane() {
        Log.d("pttt", "checkAirplane called");

        if(Settings.Global.getInt(getContentResolver(),
                    Settings.Global.AIRPLANE_MODE_ON, 0) != 0)
        {
            Log.d("pttt", "checkAirplane is enabled");
        }
    }
    private void checkFlashlight() {
        Log.d("pttt", "checkFlashlight called");
        if (getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH))
        {
            Log.d("pttt", "Flashlight is supported");
            if(flashState)
            {
                Log.d("pttt", "Flashlight is enabled");
            }

        }



    }

    private void checkDoNotDisturb()  {
        Log.d("pttt", "checkDoNotDisturb called");

        try {
            if(Settings.Global.getInt(getContentResolver(), "zen_mode") > 0)
            {
                Log.d("pttt", "checkDoNotDisturb is enabled");
            }
        } catch (Settings.SettingNotFoundException e) {
            Log.d("pttt", "SettingNotFoundException");

            e.printStackTrace();
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        Log.d("pttt", "onSaveInstanceState");
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        Log.d("pttt", "onRestoreInstanceState");
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onPause() {
        Log.d("pttt", "onPause");
        super.onPause();
    }

    @Override
    protected void onResume() {
        Log.d("pttt", "onResume");
        super.onResume();
    }



    @Override
    protected void onStart() {
        Log.d("pttt", "onStart");
        super.onStart();
    }

    @Override
    protected void onStop() {
        Log.d("pttt", "onStop");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d("pttt", "onDestroy");
        super.onDestroy();
    }
}