package com.example.david.audiotracktest;

import android.app.Activity;
import android.media.AudioTrack;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by david on 2016-08-29.
 */
public class MainActivity extends Activity {
    private String TAG = "MainActivity";
    public void testAudio() throws IOException {
       int STREAM_MUSIC=3;
        int ENCODING_PCM_16BIT=2;
        int CHANNEL_OUT_MONO = 4;
        int MODE_STATIC=0;
        int MODE_STREAM=1;
        try {
            WavInfo wi = new WavInfo();
            InputStream is = getResources().openRawResource(R.raw.beat1_mono);
            int dataSize = wi.readHeader(is);
            byte[] data = new byte[dataSize];
            is.read(data,0,data.length);
            is.close();
            AudioTrack at = new AudioTrack(STREAM_MUSIC,44100,CHANNEL_OUT_MONO,ENCODING_PCM_16BIT,dataSize,MODE_STATIC);
            at.write(data,0,data.length);
            int frames = data.length/2; //2 bytes per frame.
            Log.d(TAG,"this is data length: "+data.length);
            Log.d(TAG,"this is assumed frame number:"+frames);
            at.setLoopPoints(0,frames,3);
            at.play();
        } catch (java.io.IOException e) {
            throw e;
        }
    }
    public void testAudio2() throws IOException {
        int STREAM_MUSIC=3;
        int ENCODING_PCM_16BIT=2;
        int CHANNEL_OUT_MONO = 4;
        int MODE_STATIC=0;
        int MODE_STREAM=1;
        try {
            WavInfo wi = new WavInfo();
            InputStream is = getResources().openRawResource(R.raw.beat2_mono);
            int dataSize = wi.readHeader(is);
            byte[] data = new byte[dataSize];
            is.read(data,0,data.length);
            is.close();

            AudioTrack at = new AudioTrack(STREAM_MUSIC,44100,CHANNEL_OUT_MONO,ENCODING_PCM_16BIT,dataSize,MODE_STATIC);
            at.write(data,0,data.length);
            int frames = data.length/2; //2 bytes per frame.
            Log.d(TAG,"this is data length: "+data.length);
            Log.d(TAG,"this is assumed frame number:"+frames);
            at.setLoopPoints(0,frames,3);
            at.play();
        } catch (java.io.IOException e) {
            throw e;
        }
    }
    public void testAudio3() throws IOException
    {
        int STREAM_MUSIC=3;
        int ENCODING_PCM_16BIT=2;
        int CHANNEL_OUT_MONO = 4;
        int MODE_STATIC=0;
        int MODE_STREAM=1;
        try {
            WavInfo wi = new WavInfo();
            InputStream is = getResources().openRawResource(R.raw.beat2_mono);
            int dataSize = wi.readHeader(is);
            byte[] data = new byte[dataSize];
            is.read(data,0,data.length);
            is.close();
            InputStream is2 = getResources().openRawResource(R.raw.beat1_mono);
            int dataSize2 = wi.readHeader(is2);
            byte[] data2 = new byte[dataSize];
            is2.read(data2,0,data2.length);
            is2.close();
            byte[] data3 = new byte[dataSize];
            Log.d(TAG,"Entering for-loop.");
            short resMax=0;
            for(int i=0;i<data2.length;i+=2)
            {

                short buf1a = data[i+1];
                short buf2a = data[i];
                buf1a = (short)((buf1a & 0xff) << 8);//? Converting to decimal somehow...
                buf2a = (short) (buf2a & 0xff);

                short buf1b = data2[i+1];
                short buf2b = data2[i];
                buf1b = (short) ((buf1b & 0xff) << 8);
                buf2b = (short) (buf2b & 0xff);

                short buf1c = (short) (buf1a + buf1b);
                short buf2c = (short) (buf2a + buf2b);

                short res = (short) (buf1c + buf2c);
                int res2 = res/2;
                res = (short)res2;
                if(res>resMax)
                {
                    resMax=res;
                }
                data3[i]=(byte)res;
                data3[i+1]=(byte)(res>>8);
             //   Log.d(TAG,""+a+"");
               // Log.d(TAG,""+b+"");
            }
            Log.d(TAG,"Exiting for-loop");
            Log.d(TAG,"Resmax = "+resMax+"");
            AudioTrack at = new AudioTrack(STREAM_MUSIC,44100,CHANNEL_OUT_MONO,ENCODING_PCM_16BIT,dataSize,MODE_STATIC);
            at.write(data3,0,data3.length);
            int frames = data3.length/2; //2 bytes per frame.
            Log.d(TAG,"this is data length: "+data3.length);
            Log.d(TAG,"this is assumed frame number:"+frames);
            at.setLoopPoints(0,frames,3);
            at.play();
        } catch (java.io.IOException e) {
            throw e;
        }
    }
    public void testAudio4() throws IOException {
        int STREAM_MUSIC=3;
        int ENCODING_PCM_16BIT=2;
        int CHANNEL_OUT_MONO = 4;
        int MODE_STATIC=0;
        int MODE_STREAM=1;
        try {
            WavInfo wi = new WavInfo();
            InputStream is = getResources().openRawResource(R.raw.sound1_plus_sound2);
            int dataSize = wi.readHeader(is);
            byte[] data = new byte[dataSize];
            is.read(data,0,data.length);
            is.close();
            ////
            for(int i=0;i<data.length;i+=2) {

                short buf1a = data[i + 1];
                short buf2a = data[i];
                buf1a = (short) ((buf1a & 0xff) << 8);//? Converting to decimal somehow...
                buf2a = (short) (buf2a & 0xff);
              //  short buf3 = (short) buf1a+buf2a;
            }
            ////
            AudioTrack at = new AudioTrack(STREAM_MUSIC,44100,CHANNEL_OUT_MONO,ENCODING_PCM_16BIT,dataSize,MODE_STATIC);
            at.write(data,0,data.length);
            int frames = data.length/2; //2 bytes per frame.
            Log.d(TAG,"this is data length: "+data.length);
            Log.d(TAG,"this is assumed frame number:"+frames);
            at.setLoopPoints(0,frames,3);
            at.play();
        } catch (java.io.IOException e) {
            throw e;
        }
    }
    @Override
    protected void onCreate  (Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button b1 = (Button)findViewById(R.id.buttonOne);
        Button b2 = (Button)findViewById(R.id.buttonTwo);
        Button b3 = (Button)findViewById(R.id.buttonThree);
        Button b4 = (Button)findViewById(R.id.buttonFour);
        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick (View v) {
                try
                {
                    testAudio();
                }
                catch (java.io.IOException e){throw new RuntimeException(e);}
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            public void onClick (View v) {
                try
                {
                    testAudio2();
                }
                catch (java.io.IOException e){throw new RuntimeException(e);}
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            public void onClick (View v) {
                try
                {
                    testAudio3();
                }
                catch (java.io.IOException e){throw new RuntimeException(e);}
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            public void onClick (View v) {
                try
                {
                    testAudio4();
                }
                catch (java.io.IOException e){throw new RuntimeException(e);}
            }
        });
    }
}
