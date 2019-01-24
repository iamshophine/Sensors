package com.example.shophine.sensors;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private static final String TAG = "MainActivity";

    private SensorManager sensorManager;
    private Sensor accelerometer, mPressure, mMagno, mGyro, mLight, mTemp, mHumid;

    TextView xValue, yValue, zValue;
    TextView xMagnoValue, yMagnoValue, zMagnoValue;
    TextView xGyroValue, yGyroValue, zGyroValue;
    TextView light, pressure, temperature, humidity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        xValue = (TextView)findViewById(R.id.xValue);
        yValue = (TextView)findViewById(R.id.yValue);
        zValue = (TextView)findViewById(R.id.zValue);

        xGyroValue = (TextView)findViewById(R.id.xGyroValue);
        yGyroValue = (TextView)findViewById(R.id.yGyroValue);
        zGyroValue = (TextView)findViewById(R.id.zGyroValue);

        xMagnoValue = (TextView)findViewById(R.id.xMagnoValue);
        yMagnoValue = (TextView)findViewById(R.id.yMagnoValue);
        zMagnoValue = (TextView)findViewById(R.id.zMagnoValue);

        light = (TextView)findViewById(R.id.light);
        pressure = (TextView)findViewById(R.id.pressure);
        temperature = (TextView)findViewById(R.id.temperature);
        humidity = (TextView)findViewById(R.id.humidity);

        Log.d(TAG,"onCreate : Initialising Sensor Services");
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if(accelerometer!=null){
            sensorManager.registerListener(MainActivity.this,accelerometer,SensorManager.SENSOR_DELAY_NORMAL);
            Log.d(TAG,"onCreate : Registered Sensor Listener");
        }else {
            Log.d(TAG,"onCreate : Accelometer not supported");
            xValue.setText("Accelometer not supported ");
            yValue.setText("Accelometer not supported ");
            zValue.setText("Accelometer not supported ");


        }
        mGyro = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        if(mGyro!=null){
            sensorManager.registerListener(MainActivity.this,mGyro,SensorManager.SENSOR_DELAY_NORMAL);
            Log.d(TAG,"onCreate : Registered Gyroscope Listener");
        }else {
            Log.d(TAG,"onCreate : Gyroscope not supported");
            xGyroValue.setText("Gyroscope not supported ");
            yGyroValue.setText("Gyroscope not supported ");
            zGyroValue.setText("Gyroscope not supported ");

        }
        mMagno = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        if(mMagno!=null){
            sensorManager.registerListener(MainActivity.this,mMagno,SensorManager.SENSOR_DELAY_NORMAL);
            Log.v(TAG,"onCreate : Registered Magnetometer Listener");
        }else {
            Log.v(TAG,"onCreate :  Magnetometer not supported");
            xMagnoValue.setText("Magnetometer not supported");
            yMagnoValue.setText("Magnetometer not supported");
            zMagnoValue.setText("Magnetometer not supported");
        }

        mLight = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        if(mLight!=null){
            sensorManager.registerListener(MainActivity.this,mLight,SensorManager.SENSOR_DELAY_NORMAL);
            Log.v(TAG,"onCreate : Registered Light Listener");

        }else {
            Log.v(TAG,"onCreate :  Light not supported");
            light.setText("Light not supported");
        }
        mTemp = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
        if(mTemp!=null){
            sensorManager.registerListener(MainActivity.this,mTemp,SensorManager.SENSOR_DELAY_NORMAL);
            Log.v(TAG,"onCreate : Registered Temperature Listener");

        }else {
            Log.v(TAG,"onCreate :  Temperature not supported");
            temperature.setText("Temperature not supported");
        }
        mPressure = sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);
        if(mPressure!=null){
            sensorManager.registerListener(MainActivity.this,mPressure,SensorManager.SENSOR_DELAY_NORMAL);
            Log.v(TAG,"onCreate : Registered Pressure Listener");

        }else {
            Log.v(TAG,"onCreate :  Pressure not supported");
            pressure.setText("Pressure not supported");
        }
        mHumid = sensorManager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY);
        if(mHumid!=null){
            sensorManager.registerListener(MainActivity.this,mHumid,SensorManager.SENSOR_DELAY_NORMAL);
            Log.v(TAG,"onCreate : Registered Humidity Listener");

        }else {
            Log.v(TAG,"onCreate :  Humidity not supported");
            humidity.setText("Humidity not supported");
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        Sensor sensor = sensorEvent.sensor;
        if(sensor.getType()==Sensor.TYPE_ACCELEROMETER){
            Log.v(TAG,"onSensorChanged/Accelerometer:  X: "+sensorEvent.values[0]+"Y: "+sensorEvent.values[1]+"Z: "+sensorEvent.values[2]);
            xValue.setText("X: "+sensorEvent.values[0]);
            yValue.setText("Y: "+sensorEvent.values[1]);
            zValue.setText("Z: "+sensorEvent.values[2]);


        }else if(sensor.getType()==Sensor.TYPE_MAGNETIC_FIELD){
            //Log.v(TAG,"onSensorChanged/Magnetometer :  X: "+sensorEvent.values[0]+"Y: "+sensorEvent.values[1]+"Z: "+sensorEvent.values[2]);
            xMagnoValue.setText("xMagnoValue: "+sensorEvent.values[0]);
            yMagnoValue.setText("yMagnoValue: "+sensorEvent.values[1]);
            zMagnoValue.setText("zMagnoValue: "+sensorEvent.values[2]);
        }else if(sensor.getType()==Sensor.TYPE_GYROSCOPE){
            //Log.v(TAG,"onSensorChanged/Magnetometer :  X: "+sensorEvent.values[0]+"Y: "+sensorEvent.values[1]+"Z: "+sensorEvent.values[2]);
            xGyroValue.setText("xGyroValue: "+sensorEvent.values[0]);
            yGyroValue.setText("yGyroValue: "+sensorEvent.values[1]);
            zGyroValue.setText("zGyroValue: "+sensorEvent.values[2]);
        }else if(sensor.getType()==Sensor.TYPE_LIGHT){
            //Log.v(TAG,"onSensorChanged/Magnetometer :  X: "+sensorEvent.values[0]+"Y: "+sensorEvent.values[1]+"Z: "+sensorEvent.values[2]);
            light.setText("Light: "+sensorEvent.values[0]);
        }else if(sensor.getType()==Sensor.TYPE_PRESSURE){
            //Log.v(TAG,"onSensorChanged/Magnetometer :  X: "+sensorEvent.values[0]+"Y: "+sensorEvent.values[1]+"Z: "+sensorEvent.values[2]);
            pressure.setText("Pressure: "+sensorEvent.values[0]);
        }else if(sensor.getType()==Sensor.TYPE_AMBIENT_TEMPERATURE){
            //Log.v(TAG,"onSensorChanged/Magnetometer :  X: "+sensorEvent.values[0]+"Y: "+sensorEvent.values[1]+"Z: "+sensorEvent.values[2]);
            temperature.setText("Temperature: "+sensorEvent.values[0]);
        }else if(sensor.getType()==Sensor.TYPE_RELATIVE_HUMIDITY){
            //Log.v(TAG,"onSensorChanged/Magnetometer :  X: "+sensorEvent.values[0]+"Y: "+sensorEvent.values[1]+"Z: "+sensorEvent.values[2]);
            humidity.setText("Humidity: "+sensorEvent.values[0]);
        }

    }
}
