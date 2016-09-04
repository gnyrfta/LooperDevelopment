package com.example.david.audiotracktest;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.provider.DocumentFile;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Random;

/**
 * Created by david on 2016-08-29.
 */
public class MainActivity extends Activity {
    private String TAG = "MainActivity";
    //These have to be at least accessible outside the scope of their methods, cause gonna have to mix them with each other.
    public byte[] data;
    public byte[] data2;
    public byte[] data3;
    //
    public byte[] data7;//buffer for button seven.
    public byte[] data8;//buffer for button eight.
    public byte[] data9;//buffer for button nine.
    public int dataSize7;
    public int dataSize8;
    public int dataSize9;
    public byte[] outputBuffer;
    //constants needed for the streaming:
    boolean m_stop = false; //Keep feeding data.
    AudioTrack m_audioTrack; //Our audiotrack that we write to.
    Thread audioTrackThread; //Our thread where we write to the AudioTrack.
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    /*This is already set in Manifest - might not be necessary. */
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
    //testAudio -> testAudio4 will not be used when trying to get STREAM to work.
    public void testAudio() throws IOException {
        int STREAM_MUSIC = 3;
        int ENCODING_PCM_16BIT = 2;
        int CHANNEL_OUT_MONO = 4;
        int MODE_STATIC = 0;
        int MODE_STREAM = 1;
        try {
            WavInfo wi = new WavInfo();
            InputStream is = getResources().openRawResource(R.raw.beat1_mono);
            int dataSize = wi.readHeader(is);
            byte[] data = new byte[dataSize];
            is.read(data, 0, data.length);
            is.close();
            AudioTrack at = new AudioTrack(STREAM_MUSIC, 44100, CHANNEL_OUT_MONO, ENCODING_PCM_16BIT, dataSize, MODE_STATIC);
            at.write(data, 0, data.length);
            int frames = data.length / 2; //2 bytes per frame.
            Log.d(TAG, "this is data length: " + data.length);
            Log.d(TAG, "this is assumed frame number:" + frames);
            at.setLoopPoints(0, frames, 3);
            at.play();
        } catch (IOException e) {
            throw e;
        }
    }

    public void testAudio2() throws IOException {
        int STREAM_MUSIC = 3;
        int ENCODING_PCM_16BIT = 2;
        int CHANNEL_OUT_MONO = 4;
        int MODE_STATIC = 0;
        int MODE_STREAM = 1;
        try {
            WavInfo wi = new WavInfo();
            InputStream is = getResources().openRawResource(R.raw.beat2_mono);
            int dataSize = wi.readHeader(is);
            byte[] data = new byte[dataSize];
            is.read(data, 0, data.length);
            is.close();

            AudioTrack at = new AudioTrack(STREAM_MUSIC, 44100, CHANNEL_OUT_MONO, ENCODING_PCM_16BIT, dataSize, MODE_STATIC);
            at.write(data, 0, data.length);
            int frames = data.length / 2; //2 bytes per frame.
            Log.d(TAG, "this is data length: " + data.length);
            Log.d(TAG, "this is assumed frame number:" + frames);
            at.setLoopPoints(0, frames, 3);
            at.play();
        } catch (IOException e) {
            throw e;
        }
    }

    public void testAudio3() throws IOException {
        int STREAM_MUSIC = 3;
        int ENCODING_PCM_16BIT = 2;
        int CHANNEL_OUT_MONO = 4;
        int MODE_STATIC = 0;
        int MODE_STREAM = 1;
        try {
            WavInfo wi = new WavInfo();
            InputStream is = getResources().openRawResource(R.raw.beat2_mono);
            int dataSize = wi.readHeader(is);
            byte[] data = new byte[dataSize];
            is.read(data, 0, data.length);
            is.close();
            InputStream is2 = getResources().openRawResource(R.raw.beat1_mono);
            int dataSize2 = wi.readHeader(is2);
            byte[] data2 = new byte[dataSize];
            is2.read(data2, 0, data2.length);
            is2.close();
            byte[] data3 = new byte[dataSize];
            Log.d(TAG, "Entering for-loop.");
            short resMax = 0;
            short resPrevious=0;
            for (int i = 0; i < data2.length; i += 2) {

                short buf1a = data[i + 1];
                short buf2a = data[i];
                buf1a = (short) ((buf1a & 0xff) << 8);
                buf2a = (short) (buf2a & 0xff);
                short buf1b = data2[i + 1];
                short buf2b = data2[i];
                buf1b = (short) ((buf1b & 0xff) << 8);
                buf2b = (short) (buf2b & 0xff);

                short buf1c = (short) (buf1a + buf1b);
                short buf2c = (short) (buf2a + buf2b);

                short res = (short) (buf1c + buf2c);

                if(res>10000) //Avoid 'normal' cases where amplitude shifts from f.ex. 4 to -2, which we want to keep.
                {
                    if((res*resPrevious)<0) //If the sign has changed suddenly for a large number, use the previous number.
                    {
                        Log.d(TAG,"res:"+res+"");
                        res = resPrevious;
                    }
                }
                if(res<-10000)
                {
                    if((res*resPrevious)<0) //If the sign has changed suddenly for a large number, use the previous number.
                    {
                        res = resPrevious;
                    }
                }
                resPrevious=res;
                data3[i] = (byte) res;
                data3[i + 1] = (byte) (res >> 8);
            }
            WavWriter ww = new WavWriter();
            ww.setDataSize((long) dataSize);
            ww.setDataChunk(data3);
            ww.writeToWav("awesome.wav");
            Log.d(TAG, "Exiting for-loop");
            Log.d(TAG, "Resmax = " + resMax + "");
            // AudioTrack at = new AudioTrack(STREAM_MUSIC,44100,CHANNEL_OUT_MONO,ENCODING_PCM_16BIT,dataSize,MODE_STATIC);
            // at.write(data3,0,data3.length);
            int frames = data3.length / 2; //2 bytes per frame.
            Log.d(TAG, "this is data length: " + data3.length);
            Log.d(TAG, "this is assumed frame number:" + frames);
            // at.setLoopPoints(0,frames,3);
            // at.play();
        } catch (IOException e) {
            throw e;
        }
    }

    public void testAudio4() throws IOException {
        int STREAM_MUSIC = 3;
        int ENCODING_PCM_16BIT = 2;
        int CHANNEL_OUT_MONO = 4;
        int MODE_STATIC = 0;
        int MODE_STREAM = 1;
        try {
            WavInfo wi = new WavInfo();
            InputStream is = getResources().openRawResource(R.raw.sound1_plus_sound2);
            int dataSize = wi.readHeader(is);
            byte[] data = new byte[dataSize];
            is.read(data, 0, data.length);
            is.close();
            ////
            for (int i = 0; i < data.length; i += 2) {

                short buf1a = data[i + 1];
                short buf2a = data[i];
                buf1a = (short) ((buf1a & 0xff) << 8);//? Converting to decimal somehow...
                buf2a = (short) (buf2a & 0xff);
                //  short buf3 = (short) buf1a+buf2a;
            }
            ////
            AudioTrack at = new AudioTrack(STREAM_MUSIC, 44100, CHANNEL_OUT_MONO, ENCODING_PCM_16BIT, dataSize, MODE_STATIC);
            at.write(data, 0, data.length);
            int frames = data.length / 2; //2 bytes per frame.
            Log.d(TAG, "this is data length: " + data.length);
            Log.d(TAG, "this is assumed frame number:" + frames);
            at.setLoopPoints(0, frames, 3);
            at.play();
        } catch (IOException e) {
            throw e;
        }
    }

    Runnable feedToBuffer = new Runnable()
    {
        @Override
        public void run() {
            Thread.currentThread().setPriority(Thread.MIN_PRIORITY);//Don't know what this does. But I guess its good.
            //While we have not stopped the audio, feed the buffer data2 to output.
            while(!m_stop)
            {
                m_audioTrack.write(outputBuffer,0,outputBuffer.length);//To register changes faster, try writing shorter parts at a time.
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try
        {
            fillAllBuffers();
        }
        catch (Exception e){}
        Button b1 = (Button) findViewById(R.id.buttonOne);
        Button b2 = (Button) findViewById(R.id.buttonTwo);
        Button b3 = (Button) findViewById(R.id.buttonThree);
        Button b4 = (Button) findViewById(R.id.buttonFour);
        Button b5 = (Button) findViewById(R.id.buttonFive);
        Button b6 = (Button) findViewById(R.id.buttonSix);
        Button b7 = (Button) findViewById(R.id.buttonSeven);
        Button b8 = (Button) findViewById(R.id.buttonEight);
        Button b9 = (Button) findViewById(R.id.buttonNine);
        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    testAudio();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    testAudio2();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    testAudio3();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    testAudio4();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        b5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    startStreaming();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        b6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    stopStreaming();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        b7.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    outputBuffer = addBufferToMix(data7,dataSize7,outputBuffer,"plusbuttonseven.wav");
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        b8.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    outputBuffer=removeBufferFromMix(data9,dataSize9,outputBuffer,"minusbuttonnine.wav");
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        b9.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    outputBuffer = addBufferToMix(data9,dataSize9,outputBuffer,"plusbuttonnine.wav");
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        //Strange automatically added google stuff:
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public void onStart() {
        super.onStart();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.david.audiotracktest/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.david.audiotracktest/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
        //
    }
    void startStreaming()
    {
        int STREAM_MUSIC = 3;
        int ENCODING_PCM_16BIT = 2;
        int CHANNEL_OUT_MONO = 4;
        int MODE_STATIC = 0;
        int MODE_STREAM = 1;
        try
        {
            //Just doing all this to get the dataSize really (and feed to outputBuffer).
            WavInfo wi = new WavInfo();
            InputStream is = getResources().openRawResource(R.raw.beat1_mono);
            int dataSize = wi.readHeader(is);
            data2 = new byte[dataSize];
            is.read(data2, 0, data2.length);
            is.close();
            outputBuffer = data2;
            int frames = data2.length / 2; //2 bytes per frame.
            m_stop = false;
            m_audioTrack = new AudioTrack(AudioManager.STREAM_MUSIC, 44100, AudioFormat.CHANNEL_OUT_MONO,
                    AudioFormat.ENCODING_PCM_16BIT, dataSize,
                    AudioTrack.MODE_STREAM);
            m_audioTrack.play();
            audioTrackThread = new Thread(feedToBuffer);
            audioTrackThread.start();
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }
    void stopStreaming()
    {
        m_stop = true;
        m_audioTrack.stop();
    }
    //This should be called in onCreate.
    boolean fillAllBuffers() throws IOException
    {
        Log.d(TAG+"fill","Entering fill all buffers");
        int STREAM_MUSIC = 3;
        int ENCODING_PCM_16BIT = 2;
        int CHANNEL_OUT_MONO = 4;
        int MODE_STATIC = 0;
        int MODE_STREAM = 1;
        try {
            Log.d(TAG+"loading","Starting to load #7");
            WavInfo wi = new WavInfo();
            Log.d(TAG+"loading","Attempting to get resource");
            InputStream is = getResources().openRawResource(R.raw.epic_brass);
            Log.d(TAG+"loading","Got resource, attempting header");
            dataSize7 = wi.readHeader(is);
            Log.d(TAG+"loading","Read header, attempting to read resource to byte buffer.");
            data7 = new byte[dataSize7];
            is.read(data7, 0, data7.length);
            is.close();
            Log.d(TAG+"loading","Finished loading #7");
            Log.d(TAG+"loading","Starting loading #8");
            //
            WavInfo wi2 = new WavInfo();
            InputStream is2 = getResources().openRawResource(R.raw.beat2_mono);
            dataSize8 = wi2.readHeader(is2);
            data8 = new byte[dataSize8];
            is2.read(data8, 0, data8.length);
            is2.close();
            Log.d(TAG+"loading","Starting loading #9");
            //
            WavInfo wi3 = new WavInfo();
            InputStream is3 = getResources().openRawResource(R.raw.synth_bass);
            dataSize9 = wi3.readHeader(is3);
            data9 = new byte[dataSize9];
            is3.read(data9, 0, data9.length);
            is3.close();
            Log.d(TAG+"loading","Finished loading all 3 files.");

            //
        } catch (IOException e) {
            throw e;
        }
        Log.d(TAG+"fill","Exiting fill all buffers");
        return true;
    }
    byte[] addBufferToMix(byte[] buffer,int bufferSize,byte[] currentBuffer,String fileName)
    {
        Log.d(TAG+"add","Entering addBufferToMix");
        short resPrevious=0;
        byte[] resultingBuffer = new byte[bufferSize];

        for (int i = 0; i < buffer.length; i += 2) {

            short buf1a = currentBuffer[i + 1];
            short buf2a = currentBuffer[i];
            buf1a = (short) ((buf1a & 0xff) << 8);
            buf2a = (short) (buf2a & 0xff);
            short buf1b = buffer[i + 1];
            short buf2b = buffer[i];
            buf1b = (short) ((buf1b & 0xff) << 8);
            buf2b = (short) (buf2b & 0xff);

            short buf1c = (short) (buf1a + buf1b);
            short buf2c = (short) (buf2a + buf2b);

            short res = (short) (buf1c + buf2c);
            float temp = (float)res;
            float temp2 = temp/2;
            res = (short)temp2;

            if(res>10000) //Avoid 'normal' cases where amplitude shifts from f.ex. 4 to -2, which we want to keep.
            {
                if((res*resPrevious)<0) //If the sign has changed suddenly for a large number, use the previous number.
                {
                    Log.d(TAG,"res:"+res+"");
                    res = resPrevious;
                }
            }
            if(res<-10000)
            {
                if((res*resPrevious)<0) //If the sign has changed suddenly for a large number, use the previous number.
                {
                    res = resPrevious;
                }
            }
            resPrevious=res;
            resultingBuffer[i] = (byte) res;
            resultingBuffer[i + 1] = (byte) (res >> 8);
        }
        WavWriter ww = new WavWriter();
        ww.setDataSize((long) bufferSize);
        ww.setDataChunk(resultingBuffer);
        ww.writeToWav(fileName);
        Log.d(TAG+"add","Exiting addBufferToMix");
        return resultingBuffer;
    }
    byte[] removeBufferFromMix(byte[] buffer,int bufferSize,byte[] currentBuffer,String fileName)
    {
        short resPrevious=0;
        byte[] resultingBuffer = new byte[bufferSize];
        for (int i = 0; i < buffer.length; i += 2) {

            short buf1a = currentBuffer[i + 1];
            short buf2a = currentBuffer[i];
            buf1a = (short) ((buf1a & 0xff) << 8);
            buf2a = (short) (buf2a & 0xff);
            short buf1b = buffer[i + 1];
            short buf2b = buffer[i];
            buf1b = (short) ((buf1b & 0xff) << 8);
            buf2b = (short) (buf2b & 0xff);

            short buf1c = (short) (buf1a - buf1b);
            short buf2c = (short) (buf2a - buf2b);

            short res = (short) (buf1c + buf2c);

            if(res>10000) //Avoid 'normal' cases where amplitude shifts from f.ex. 4 to -2, which we want to keep.
            {
                if((res*resPrevious)<0) //If the sign has changed suddenly for a large number, use the previous number.
                {
                    Log.d(TAG,"res:"+res+"");
                    res = resPrevious;
                }
            }
            if(res<-10000)
            {
                if((res*resPrevious)<0) //If the sign has changed suddenly for a large number, use the previous number.
                {
                    res = resPrevious;
                }
            }
            resPrevious=res;
            resultingBuffer[i] = (byte) res;
            resultingBuffer[i + 1] = (byte) (res >> 8);
        }
        WavWriter ww = new WavWriter();
        ww.setDataSize((long) bufferSize);
        ww.setDataChunk(resultingBuffer);
        ww.writeToWav(fileName);
        byte[] returnBuffer = data2;//
        return returnBuffer;
    }
}
