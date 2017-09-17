package com.leonxtp.ymodemble;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.leonxtp.library.YModemListener;
import com.leonxtp.library.YmodemBLE;

public class MainActivity extends AppCompatActivity {

    private YmodemBLE ymodemBLE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private void startTransmission() {

        ymodemBLE = new YmodemBLE.Builder()
                .with(this)
                .filePath("assets://demo.bin")
                .fileName("demo.bin")
                .checkMd5("lsfjlhoiiw121241l241lgljaf")
                .callback(new YModemListener() {
                    @Override
                    public void onDataReady(byte[] data) {
                        //send this data[] to your ble component here...
                    }

                    @Override
                    public void onProgress(int currentSent, int total) {
                        //the progress of the file data has transmitted
                    }

                    @Override
                    public void onSuccess() {
                        //we are well done with md5 checked
                    }

                    @Override
                    public void onFailed() {
                        //the task has failed for several times of trying
                    }
                }).build();

        ymodemBLE.start();
    }

    /**
     * When you received response from the ble terminal, tell ymodemBLE
     */
    public void onDataReceivedFromBLE(byte[] data) {
        ymodemBLE.onReceiveData(data);
    }

    /*stop the transmission*/
    public void onStopClick(View view) {
        ymodemBLE.stop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //When the activity finished unexpected, just call stop ymodemBLE
        ymodemBLE.stop();
    }
}
