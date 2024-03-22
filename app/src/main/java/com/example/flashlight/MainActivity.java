package com.example.flashlight;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import com.example.flashlight.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding=ActivityMainBinding.inflate(getLayoutInflater());
        getSupportActionBar().hide();

        setContentView(binding.getRoot());

        binding.power.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(binding.power.getText().toString().equals("Turn On"))
                {
                    binding.power.setText(R.string.turnoff);
                    binding.flashimage.setImageResource(R.drawable.flashlight_on);
                    change_light(true);
                }else{
                    binding.power.setText(R.string.turnon);
                    binding.flashimage.setImageResource(R.drawable.flashlight_off);
                    change_light(false);
                }

            }
        });
    }

    private void change_light(boolean state)
    {
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M)
        {
            CameraManager cameraManager= (CameraManager) getSystemService(CAMERA_SERVICE);
            String cam_id=null;

            try {
                cam_id=cameraManager.getCameraIdList()[0];
                cameraManager.setTorchMode(cam_id,state);
            } catch (CameraAccessException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onStart()
    {
        super.onStart();
        binding.power.setText(R.string.turnon);
    }


}