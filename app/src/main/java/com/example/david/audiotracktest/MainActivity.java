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
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Random;

/**
 * Created by david on 2016-08-29.
 */
public class MainActivity extends Activity {
    private String TAG = "MainActivity";
    //These have to be at least accessible outside the scope of their methods, cause gonna have to mix them with each other.
    public byte[] data1;
    public byte[] data2;
    public byte[] data3;
    public byte[] data4;
    public byte[] data5;
    public byte[] data6;
    public byte[] data7;//buffer for button seven.
    public byte[] data8;//buffer for button eight.
    public byte[] data9;//buffer for button nine.
    public byte[] data10;
    public byte[] data11;
    public byte[] data12;
    public int dataSize1;
    public int dataSize2;
    public int dataSize3;
    public int dataSize4;
    public int dataSize5;
    public int dataSize6;
    public int dataSize7;
    public int dataSize8;
    public int dataSize9;
    public int dataSize10;
    public int dataSize11;
    public int dataSize12;

    public static volatile byte[] outputBuffer;
    public static volatile byte[] tempBuffer;
    public volatile boolean changedBuffer=false;
    //
    public volatile boolean addBuffer1=false;
    public volatile boolean addBuffer2=false;
    public volatile boolean addBuffer3=false;
    public volatile boolean addBuffer4=false;
    public volatile boolean addBuffer5=false;
    public volatile boolean addBuffer6=false;
    public volatile boolean addBuffer7=false;
    public volatile boolean addBuffer8=false;
    public volatile boolean addBuffer9=false;
    public volatile boolean addBuffer10=false;
    public volatile boolean addBuffer11=false;
    public volatile boolean addBuffer12=false;
    public volatile boolean addBuffer13=false;
    public volatile boolean addBuffer14=false;
    public volatile boolean addBuffer15=false;
    public volatile boolean addBuffer16=false;
    //
    public volatile int addCounter1=0;
    public volatile int addCounter2=0;
    public volatile int addCounter3=0;
    public volatile int addCounter4=0;
    public volatile int addCounter5=0;
    public volatile int addCounter6=0;
    public volatile int addCounter7=0;
    public volatile int addCounter8=0;
    public volatile int addCounter9=0;
    public volatile int addCounter10=0;
    public volatile int addCounter11=0;
    public volatile int addCounter12=0;
    public volatile int addCounter13=0;
    public volatile int addCounter14=0;
    public volatile int addCounter15=0;
    public volatile int addCounter16=0;
    //
    public volatile boolean removeBuffer1=false;
    public volatile boolean removeBuffer2=false;
    public volatile boolean removeBuffer3=false;
    public volatile boolean removeBuffer4=false;
    public volatile boolean removeBuffer5=false;
    public volatile boolean removeBuffer6=false;
    public volatile boolean removeBuffer7=false;
    public volatile boolean removeBuffer8=false;
    public volatile boolean removeBuffer9=false;
    public volatile boolean removeBuffer10=false;
    public volatile boolean removeBuffer11=false;
    public volatile boolean removeBuffer12=false;
    public volatile boolean removeBuffer13=false;
    public volatile boolean removeBuffer14=false;
    public volatile boolean removeBuffer15=false;
    public volatile boolean removeBuffer16=false;
    //
    public volatile int removeCounter1=0;
    public volatile int removeCounter2=0;
    public volatile int removeCounter3=0;
    public volatile int removeCounter4=0;
    public volatile int removeCounter5=0;
    public volatile int removeCounter6=0;
    public volatile int removeCounter7=0;
    public volatile int removeCounter8=0;
    public volatile int removeCounter9=0;
    public volatile int removeCounter10=0;
    public volatile int removeCounter11=0;
    public volatile int removeCounter12=0;
    public volatile int removeCounter13=0;
    public volatile int removeCounter14=0;
    public volatile int removeCounter15=0;
    public volatile int removeCounter16=0;

    public volatile int bufferLength=100;

    public volatile float numberOfStreams=0;
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
        //TO USE BELOW CODE, ADD AUDIO TRACK.
        /*
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
        }*/
    }

    public void testAudio3() throws IOException {
        int STREAM_MUSIC = 3;
        int ENCODING_PCM_16BIT = 2;
        int CHANNEL_OUT_MONO = 4;
        int MODE_STATIC = 0;
        int MODE_STREAM = 1;
        //TO USE BELOW CODE, ADD AUDIO TRACK:
        /*
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
        }*/
    }

    public void testAudio4() throws IOException {
        int STREAM_MUSIC = 3;
        int ENCODING_PCM_16BIT = 2;
        int CHANNEL_OUT_MONO = 4;
        int MODE_STATIC = 0;
        int MODE_STREAM = 1;
        //TO USE BELOW CODE, ADD AUDIO FILE:
        /*
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
        }*/
    }

     volatile Runnable feedToBuffer = new Runnable()
    {
        @Override
        public synchronized void run() {
            int wavCounter=0;
            int wavFileNumber=0;
            Thread.currentThread().setPriority(Thread.MIN_PRIORITY);//Don't know what this does. But I guess its good.
            //While we have not stopped the audio, feed the buffer data2 to output.
            //All the buffers should be the same length, using data6 here but hopefully could have used any array:
            byte[] testBuffer=new byte[data6.length];//Should create an array of zeros, although there seems to be debate about whether the default value is
            //zero for local variables.
            int testBufferDataSize=dataSize6;
           // byte[] resultingBuffer;
            //Load testBuffer:
           /* try {
                Log.d(TAG + "loading", "Starting to load testbuffer");
                WavInfo wi = new WavInfo();
                Log.d(TAG + "loading", "Attempting to get resource");
                InputStream is = getResources().openRawResource(R.raw.beat1_mono);
                Log.d(TAG + "loading", "Got resource, attempting header");
                testBufferDataSize = wi.readHeader(is);
                Log.d(TAG + "loading", "Read header, attempting to read resource to byte buffer.");
                testBuffer = new byte[testBufferDataSize];
                is.read(testBuffer, 0,testBufferDataSize);
                is.close();
                Log.d(TAG + "loading", "Finished loading testbuffer");
            }
                catch(IOException e ){throw new RuntimeException(e);}*/
            //
            int i = 0;
           // byte[] temp;
            int startWritingPoint=0;
            int counter=0;
            while(!m_stop)
            {
                //Should store a files about twice per second.
                if(wavCounter>2000)
                {
                    WavWriter ww = new WavWriter();
                    ww.setDataSize((long) testBuffer.length);
                    ww.setDataChunk(testBuffer);
                    ww.writeToWav("noiseDebug"+wavFileNumber+".wav");
                    wavFileNumber++;
                    wavCounter=0;
                }
                //temp = Arrays.copyOfRange(testBuffer,i,i+bufferLength);
                byte[] temp = new byte[bufferLength];
                byte[] resultingBuffer = new byte[temp.length];

                //For going in here:
                    //changedBuffer = true
                    // Then check for value 1 -16 - each number is one byte array.
                    //Load correct byte array.

                if(addBuffer1)
                {
                    byte[] subBuffer = Arrays.copyOfRange(data1,i,i+bufferLength);
                    byte[] temporaryBufferWithAdjustedAmplitude = new byte[bufferLength];
                    for(int m=0;m<subBuffer.length;m+=2)
                    {
                        short buf1a = temp[m + 1];
                        short buf2a = temp[m];
                        buf1a = (short) ((buf1a & 0xff) << 8);
                        buf2a = (short) (buf2a & 0xff);
                        short buf1b = subBuffer[m + 1];
                        short buf2b = subBuffer[m];
                        buf1b = (short) ((buf1b & 0xff) << 8);
                        buf2b = (short) (buf2b & 0xff);
                        //Dividing amplitude by half and writing to temporary buffer.

                        //Dividing amplitude by half and writing to temporary other buffer.
                        short dudette = (short)(buf1b+buf2b);
                        float fdudette = (float)(dudette/numberOfStreams);
                        dudette = (short)fdudette;
                        temporaryBufferWithAdjustedAmplitude[m]=(byte)dudette;
                        temporaryBufferWithAdjustedAmplitude[m+1]=(byte)(dudette >> 8);
                        //Getting values from temporary buffer.

                        //Getting values from temporary other buffer.
                        buf1b=temporaryBufferWithAdjustedAmplitude[m+1];
                        buf2b=temporaryBufferWithAdjustedAmplitude[m];
                        buf1b = (short) ((buf1b & 0xff) << 8);
                        buf2b = (short) (buf2b & 0xff);
                        //Adding buffers.
                        short buf1c = (short) (buf1a + buf1b);
                        short buf2c = (short) (buf2a + buf2b);

                        short res = (short) (buf1c + buf2c);
                        resultingBuffer[m] = (byte) res;
                        resultingBuffer[m + 1] = (byte) (res >> 8);
                    }
                    temp=resultingBuffer;
                }

                if(addBuffer2)
                {
                    byte[] subBuffer = Arrays.copyOfRange(data2,i,i+bufferLength);
                    byte[] temporaryBufferWithAdjustedAmplitude = new byte[bufferLength];
                    for(int m=0;m<subBuffer.length;m+=2)
                    {
                        short buf1a = temp[m + 1];
                        short buf2a = temp[m];
                        buf1a = (short) ((buf1a & 0xff) << 8);
                        buf2a = (short) (buf2a & 0xff);
                        short buf1b = subBuffer[m + 1];
                        short buf2b = subBuffer[m];
                        buf1b = (short) ((buf1b & 0xff) << 8);
                        buf2b = (short) (buf2b & 0xff);
                        //Dividing amplitude by half and writing to temporary buffer.

                        //Dividing amplitude by half and writing to temporary other buffer.
                        short dudette = (short)(buf1b+buf2b);
                        float fdudette = (float)(dudette/numberOfStreams);
                        dudette = (short)fdudette;
                        temporaryBufferWithAdjustedAmplitude[m]=(byte)dudette;
                        temporaryBufferWithAdjustedAmplitude[m+1]=(byte)(dudette >> 8);
                        //Getting values from temporary buffer.

                        //Getting values from temporary other buffer.
                        buf1b=temporaryBufferWithAdjustedAmplitude[m+1];
                        buf2b=temporaryBufferWithAdjustedAmplitude[m];
                        buf1b = (short) ((buf1b & 0xff) << 8);
                        buf2b = (short) (buf2b & 0xff);
                        //Adding buffers.
                        short buf1c = (short) (buf1a + buf1b);
                        short buf2c = (short) (buf2a + buf2b);

                        short res = (short) (buf1c + buf2c);
                        resultingBuffer[m] = (byte) res;
                        resultingBuffer[m + 1] = (byte) (res >> 8);
                    }
                    temp=resultingBuffer;
                }

                if(addBuffer3)
                {
                    byte[] subBuffer = Arrays.copyOfRange(data3,i,i+bufferLength);
                    byte[] temporaryBufferWithAdjustedAmplitude = new byte[bufferLength];
                    for(int m=0;m<subBuffer.length;m+=2)
                    {
                        short buf1a = temp[m + 1];
                        short buf2a = temp[m];
                        buf1a = (short) ((buf1a & 0xff) << 8);
                        buf2a = (short) (buf2a & 0xff);
                        short buf1b = subBuffer[m + 1];
                        short buf2b = subBuffer[m];
                        buf1b = (short) ((buf1b & 0xff) << 8);
                        buf2b = (short) (buf2b & 0xff);
                        //Dividing amplitude by half and writing to temporary buffer.

                        //Dividing amplitude by half and writing to temporary other buffer.
                        short dudette = (short)(buf1b+buf2b);
                        float fdudette = (float)(dudette/numberOfStreams);
                        dudette = (short)fdudette;
                        temporaryBufferWithAdjustedAmplitude[m]=(byte)dudette;
                        temporaryBufferWithAdjustedAmplitude[m+1]=(byte)(dudette >> 8);
                        //Getting values from temporary buffer.

                        //Getting values from temporary other buffer.
                        buf1b=temporaryBufferWithAdjustedAmplitude[m+1];
                        buf2b=temporaryBufferWithAdjustedAmplitude[m];
                        buf1b = (short) ((buf1b & 0xff) << 8);
                        buf2b = (short) (buf2b & 0xff);
                        //Adding buffers.
                        short buf1c = (short) (buf1a + buf1b);
                        short buf2c = (short) (buf2a + buf2b);

                        short res = (short) (buf1c + buf2c);
                        resultingBuffer[m] = (byte) res;
                        resultingBuffer[m + 1] = (byte) (res >> 8);
                    }
                    temp=resultingBuffer;
                }

                if(addBuffer4)
                {
                    byte[] subBuffer = Arrays.copyOfRange(data4,i,i+bufferLength);
                    byte[] temporaryBufferWithAdjustedAmplitude = new byte[bufferLength];
                    for(int m=0;m<subBuffer.length;m+=2)
                    {
                        short buf1a = temp[m + 1];
                        short buf2a = temp[m];
                        buf1a = (short) ((buf1a & 0xff) << 8);
                        buf2a = (short) (buf2a & 0xff);
                        short buf1b = subBuffer[m + 1];
                        short buf2b = subBuffer[m];
                        buf1b = (short) ((buf1b & 0xff) << 8);
                        buf2b = (short) (buf2b & 0xff);
                        //Dividing amplitude by half and writing to temporary buffer.

                        //Dividing amplitude by half and writing to temporary other buffer.
                        short dudette = (short)(buf1b+buf2b);
                        float fdudette = (float)(dudette/numberOfStreams);
                        dudette = (short)fdudette;
                        temporaryBufferWithAdjustedAmplitude[m]=(byte)dudette;
                        temporaryBufferWithAdjustedAmplitude[m+1]=(byte)(dudette >> 8);
                        //Getting values from temporary buffer.

                        //Getting values from temporary other buffer.
                        buf1b=temporaryBufferWithAdjustedAmplitude[m+1];
                        buf2b=temporaryBufferWithAdjustedAmplitude[m];
                        buf1b = (short) ((buf1b & 0xff) << 8);
                        buf2b = (short) (buf2b & 0xff);
                        //Adding buffers.
                        short buf1c = (short) (buf1a + buf1b);
                        short buf2c = (short) (buf2a + buf2b);

                        short res = (short) (buf1c + buf2c);
                        resultingBuffer[m] = (byte) res;
                        resultingBuffer[m + 1] = (byte) (res >> 8);
                    }
                    temp=resultingBuffer;
                }

                if(addBuffer5)
                {
                    byte[] subBuffer = Arrays.copyOfRange(data5,i,i+bufferLength);
                    byte[] temporaryBufferWithAdjustedAmplitude = new byte[bufferLength];
                    for(int m=0;m<subBuffer.length;m+=2)
                    {
                        short buf1a = temp[m + 1];
                        short buf2a = temp[m];
                        buf1a = (short) ((buf1a & 0xff) << 8);
                        buf2a = (short) (buf2a & 0xff);
                        short buf1b = subBuffer[m + 1];
                        short buf2b = subBuffer[m];
                        buf1b = (short) ((buf1b & 0xff) << 8);
                        buf2b = (short) (buf2b & 0xff);
                        //Dividing amplitude by half and writing to temporary buffer.

                        //Dividing amplitude by half and writing to temporary other buffer.
                        short dudette = (short)(buf1b+buf2b);
                        float fdudette = (float)(dudette/numberOfStreams);
                        dudette = (short)fdudette;
                        temporaryBufferWithAdjustedAmplitude[m]=(byte)dudette;
                        temporaryBufferWithAdjustedAmplitude[m+1]=(byte)(dudette >> 8);
                        //Getting values from temporary buffer.

                        //Getting values from temporary other buffer.
                        buf1b=temporaryBufferWithAdjustedAmplitude[m+1];
                        buf2b=temporaryBufferWithAdjustedAmplitude[m];
                        buf1b = (short) ((buf1b & 0xff) << 8);
                        buf2b = (short) (buf2b & 0xff);
                        //Adding buffers.
                        short buf1c = (short) (buf1a + buf1b);
                        short buf2c = (short) (buf2a + buf2b);

                        short res = (short) (buf1c + buf2c);
                        resultingBuffer[m] = (byte) res;
                        resultingBuffer[m + 1] = (byte) (res >> 8);
                    }
                    temp=resultingBuffer;
                }
                //
                if(addBuffer6)
                {
                    byte[] subBuffer = Arrays.copyOfRange(data6,i,i+bufferLength);
                    byte[] temporaryBufferWithAdjustedAmplitude = new byte[bufferLength];
                    for(int m=0;m<subBuffer.length;m+=2)
                    {
                        short buf1a = temp[m + 1];
                        short buf2a = temp[m];
                        buf1a = (short) ((buf1a & 0xff) << 8);
                        buf2a = (short) (buf2a & 0xff);
                        short buf1b = subBuffer[m + 1];
                        short buf2b = subBuffer[m];
                        buf1b = (short) ((buf1b & 0xff) << 8);
                        buf2b = (short) (buf2b & 0xff);
                        //Dividing amplitude by half and writing to temporary buffer.

                        //Dividing amplitude by half and writing to temporary other buffer.
                        short dudette = (short)(buf1b+buf2b);
                        float fdudette = (float)(dudette/numberOfStreams);
                        dudette = (short)fdudette;
                        temporaryBufferWithAdjustedAmplitude[m]=(byte)dudette;
                        temporaryBufferWithAdjustedAmplitude[m+1]=(byte)(dudette >> 8);
                        //Getting values from temporary buffer.

                        //Getting values from temporary other buffer.
                        buf1b=temporaryBufferWithAdjustedAmplitude[m+1];
                        buf2b=temporaryBufferWithAdjustedAmplitude[m];
                        buf1b = (short) ((buf1b & 0xff) << 8);
                        buf2b = (short) (buf2b & 0xff);
                        //Adding buffers.
                        short buf1c = (short) (buf1a + buf1b);
                        short buf2c = (short) (buf2a + buf2b);

                        short res = (short) (buf1c + buf2c);
                        resultingBuffer[m] = (byte) res;
                        resultingBuffer[m + 1] = (byte) (res >> 8);
                    }
                    temp=resultingBuffer;
                }

                if(addBuffer7)
                {
                    byte[] subBuffer = Arrays.copyOfRange(data7,i,i+bufferLength);
                    byte[] temporaryBufferWithAdjustedAmplitude = new byte[bufferLength];
                    for(int m=0;m<subBuffer.length;m+=2)
                    {
                        short buf1a = temp[m + 1];
                        short buf2a = temp[m];
                        buf1a = (short) ((buf1a & 0xff) << 8);
                        buf2a = (short) (buf2a & 0xff);
                        short buf1b = subBuffer[m + 1];
                        short buf2b = subBuffer[m];
                        buf1b = (short) ((buf1b & 0xff) << 8);
                        buf2b = (short) (buf2b & 0xff);
                        //Dividing amplitude by half and writing to temporary buffer.

                        //Dividing amplitude by half and writing to temporary other buffer.
                        short dudette = (short)(buf1b+buf2b);
                        float fdudette = (float)(dudette/numberOfStreams);
                        dudette = (short)fdudette;
                        temporaryBufferWithAdjustedAmplitude[m]=(byte)dudette;
                        temporaryBufferWithAdjustedAmplitude[m+1]=(byte)(dudette >> 8);
                        //Getting values from temporary buffer.

                        //Getting values from temporary other buffer.
                        buf1b=temporaryBufferWithAdjustedAmplitude[m+1];
                        buf2b=temporaryBufferWithAdjustedAmplitude[m];
                        buf1b = (short) ((buf1b & 0xff) << 8);
                        buf2b = (short) (buf2b & 0xff);
                        //Adding buffers.
                        short buf1c = (short) (buf1a + buf1b);
                        short buf2c = (short) (buf2a + buf2b);

                        short res = (short) (buf1c + buf2c);
                        resultingBuffer[m] = (byte) res;
                        resultingBuffer[m + 1] = (byte) (res >> 8);
                    }
                    temp=resultingBuffer;
                }
                if(addBuffer8)
                {
                    byte[] subBuffer = Arrays.copyOfRange(data8,i,i+bufferLength);
                    byte[] temporaryBufferWithAdjustedAmplitude = new byte[bufferLength];
                    for(int m=0;m<subBuffer.length;m+=2)
                    {
                        short buf1a = temp[m + 1];
                        short buf2a = temp[m];
                        buf1a = (short) ((buf1a & 0xff) << 8);
                        buf2a = (short) (buf2a & 0xff);
                        short buf1b = subBuffer[m + 1];
                        short buf2b = subBuffer[m];
                        buf1b = (short) ((buf1b & 0xff) << 8);
                        buf2b = (short) (buf2b & 0xff);
                        //Dividing amplitude by half and writing to temporary buffer.

                        //Dividing amplitude by half and writing to temporary other buffer.
                        short dudette = (short)(buf1b+buf2b);
                        float fdudette = (float)(dudette/numberOfStreams);
                        dudette = (short)fdudette;
                        temporaryBufferWithAdjustedAmplitude[m]=(byte)dudette;
                        temporaryBufferWithAdjustedAmplitude[m+1]=(byte)(dudette >> 8);
                        //Getting values from temporary buffer.

                        //Getting values from temporary other buffer.
                        buf1b=temporaryBufferWithAdjustedAmplitude[m+1];
                        buf2b=temporaryBufferWithAdjustedAmplitude[m];
                        buf1b = (short) ((buf1b & 0xff) << 8);
                        buf2b = (short) (buf2b & 0xff);
                        //Adding buffers.
                        short buf1c = (short) (buf1a + buf1b);
                        short buf2c = (short) (buf2a + buf2b);

                        short res = (short) (buf1c + buf2c);
                        resultingBuffer[m] = (byte) res;
                        resultingBuffer[m + 1] = (byte) (res >> 8);
                    }
                    temp=resultingBuffer;
                }
                if(addBuffer9)
                {
                    byte[] subBuffer = Arrays.copyOfRange(data9,i,i+bufferLength);
                    byte[] temporaryBufferWithAdjustedAmplitude = new byte[bufferLength];
                    for(int m=0;m<subBuffer.length;m+=2)
                    {
                        short buf1a = temp[m + 1];
                        short buf2a = temp[m];
                        buf1a = (short) ((buf1a & 0xff) << 8);
                        buf2a = (short) (buf2a & 0xff);
                        short buf1b = subBuffer[m + 1];
                        short buf2b = subBuffer[m];
                        buf1b = (short) ((buf1b & 0xff) << 8);
                        buf2b = (short) (buf2b & 0xff);
                        //Dividing amplitude by half and writing to temporary buffer.

                        //Dividing amplitude by half and writing to temporary other buffer.
                        short dudette = (short)(buf1b+buf2b);
                        float fdudette = (float)(dudette/numberOfStreams);
                        dudette = (short)fdudette;
                        temporaryBufferWithAdjustedAmplitude[m]=(byte)dudette;
                        temporaryBufferWithAdjustedAmplitude[m+1]=(byte)(dudette >> 8);
                        //Getting values from temporary buffer.

                        //Getting values from temporary other buffer.
                        buf1b=temporaryBufferWithAdjustedAmplitude[m+1];
                        buf2b=temporaryBufferWithAdjustedAmplitude[m];
                        buf1b = (short) ((buf1b & 0xff) << 8);
                        buf2b = (short) (buf2b & 0xff);
                        //Adding buffers.
                        short buf1c = (short) (buf1a + buf1b);
                        short buf2c = (short) (buf2a + buf2b);

                        short res = (short) (buf1c + buf2c);
                        resultingBuffer[m] = (byte) res;
                        resultingBuffer[m + 1] = (byte) (res >> 8);
                    }
                    temp=resultingBuffer;
                }
                //
                if(addBuffer10)
                {
                    byte[] subBuffer = Arrays.copyOfRange(data10,i,i+bufferLength);
                    byte[] temporaryBufferWithAdjustedAmplitude = new byte[bufferLength];
                    for(int m=0;m<subBuffer.length;m+=2)
                    {
                        short buf1a = temp[m + 1];
                        short buf2a = temp[m];
                        buf1a = (short) ((buf1a & 0xff) << 8);
                        buf2a = (short) (buf2a & 0xff);
                        short buf1b = subBuffer[m + 1];
                        short buf2b = subBuffer[m];
                        buf1b = (short) ((buf1b & 0xff) << 8);
                        buf2b = (short) (buf2b & 0xff);
                        //Dividing amplitude by half and writing to temporary buffer.

                        //Dividing amplitude by half and writing to temporary other buffer.
                        short dudette = (short)(buf1b+buf2b);
                        float fdudette = (float)(dudette/numberOfStreams);
                        dudette = (short)fdudette;
                        temporaryBufferWithAdjustedAmplitude[m]=(byte)dudette;
                        temporaryBufferWithAdjustedAmplitude[m+1]=(byte)(dudette >> 8);
                        //Getting values from temporary buffer.

                        //Getting values from temporary other buffer.
                        buf1b=temporaryBufferWithAdjustedAmplitude[m+1];
                        buf2b=temporaryBufferWithAdjustedAmplitude[m];
                        buf1b = (short) ((buf1b & 0xff) << 8);
                        buf2b = (short) (buf2b & 0xff);
                        //Adding buffers.
                        short buf1c = (short) (buf1a + buf1b);
                        short buf2c = (short) (buf2a + buf2b);

                        short res = (short) (buf1c + buf2c);
                        resultingBuffer[m] = (byte) res;
                        resultingBuffer[m + 1] = (byte) (res >> 8);
                    }
                    temp=resultingBuffer;
                }
                //
                if(addBuffer11)
                {
                    byte[] subBuffer = Arrays.copyOfRange(data11,i,i+bufferLength);
                    byte[] temporaryBufferWithAdjustedAmplitude = new byte[bufferLength];
                    for(int m=0;m<subBuffer.length;m+=2)
                    {
                        short buf1a = temp[m + 1];
                        short buf2a = temp[m];
                        buf1a = (short) ((buf1a & 0xff) << 8);
                        buf2a = (short) (buf2a & 0xff);
                        short buf1b = subBuffer[m + 1];
                        short buf2b = subBuffer[m];
                        buf1b = (short) ((buf1b & 0xff) << 8);
                        buf2b = (short) (buf2b & 0xff);
                        //Dividing amplitude by half and writing to temporary buffer.

                        //Dividing amplitude by half and writing to temporary other buffer.
                        short dudette = (short)(buf1b+buf2b);
                        float fdudette = (float)(dudette/numberOfStreams);
                        dudette = (short)fdudette;
                        temporaryBufferWithAdjustedAmplitude[m]=(byte)dudette;
                        temporaryBufferWithAdjustedAmplitude[m+1]=(byte)(dudette >> 8);
                        //Getting values from temporary buffer.

                        //Getting values from temporary other buffer.
                        buf1b=temporaryBufferWithAdjustedAmplitude[m+1];
                        buf2b=temporaryBufferWithAdjustedAmplitude[m];
                        buf1b = (short) ((buf1b & 0xff) << 8);
                        buf2b = (short) (buf2b & 0xff);
                        //Adding buffers.
                        short buf1c = (short) (buf1a + buf1b);
                        short buf2c = (short) (buf2a + buf2b);

                        short res = (short) (buf1c + buf2c);
                        resultingBuffer[m] = (byte) res;
                        resultingBuffer[m + 1] = (byte) (res >> 8);
                    }
                    temp=resultingBuffer;
                }

                Log.d(TAG,"writing to audioTrack");
                m_audioTrack.write(temp,0,temp.length);//To register changes faster, try writing shorter parts at a time.
                i=i+bufferLength;
                wavCounter++;
                if(i>outputBuffer.length-bufferLength)
                {
                    i=0;
                }
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        try
        {
            fillAllBuffers();//Load data7, data8, data9.
           // bufferLength=data7.length;
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
        Button b10 = (Button) findViewById(R.id.buttonTen);
        Button b11 = (Button) findViewById(R.id.buttonEleven);

        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    if(!addBuffer6)
                    {
                        if (numberOfStreams == 0) {
                            numberOfStreams += 1.0;
                            addBuffer6 = true;
                            startStreaming();
                        } else {
                            numberOfStreams += 1.0;
                            addBuffer6 = true;
                        }
                    }
                    else if(addBuffer6)
                    {
                        addBuffer6=false;
                        numberOfStreams-=1.0;
                    }
                    //final CharSequence text = "Testing 1 2 3";
                    //Toast toast = Toast.makeText(getApplicationContext(),text,Toast.LENGTH_LONG);
                    //toast.show();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    if(!addBuffer7)
                    {
                        if (numberOfStreams == 0) {
                            numberOfStreams += 1.0;
                            addBuffer7 = true;
                            startStreaming();
                        } else {
                            numberOfStreams += 1.0;
                            addBuffer7 = true;
                        }
                    }
                    else if(addBuffer7)
                    {
                        addBuffer7=false;
                        numberOfStreams-=1.0;
                    }
                    //final CharSequence text = "Testing 1 2 3";
                    //Toast toast = Toast.makeText(getApplicationContext(),text,Toast.LENGTH_LONG);
                    //toast.show();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    if(!addBuffer8)
                    {
                        if (numberOfStreams == 0) {
                            numberOfStreams += 1.0;
                            addBuffer8 = true;
                            startStreaming();
                        } else {
                            numberOfStreams += 1.0;
                            addBuffer8 = true;
                        }
                    }
                    else if(addBuffer8)
                    {
                        addBuffer8=false;
                        numberOfStreams-=1.0;
                    }
                    //final CharSequence text = "Testing 1 2 3";
                    //Toast toast = Toast.makeText(getApplicationContext(),text,Toast.LENGTH_LONG);
                    //toast.show();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    if(!addBuffer9)
                    {
                        if (numberOfStreams == 0) {
                            numberOfStreams += 1.0;
                            addBuffer9 = true;
                            startStreaming();
                        } else {
                            numberOfStreams += 1.0;
                            addBuffer9 = true;
                        }
                    }
                    else if(addBuffer9)
                    {
                        addBuffer9=false;
                        numberOfStreams-=1.0;
                    }
                    //final CharSequence text = "Testing 1 2 3";
                    //Toast toast = Toast.makeText(getApplicationContext(),text,Toast.LENGTH_LONG);
                    //toast.show();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        b5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    if(!addBuffer1)
                    {
                        if (numberOfStreams == 0) {
                            numberOfStreams += 1.0;
                            addBuffer1 = true;
                            startStreaming();
                        } else {
                            numberOfStreams += 1.0;
                            addBuffer1 = true;
                        }
                    }
                    else if(addBuffer1)
                    {
                        addBuffer1=false;
                        numberOfStreams-=1.0;
                    }
                    //final CharSequence text = "Testing 1 2 3";
                    //Toast toast = Toast.makeText(getApplicationContext(),text,Toast.LENGTH_LONG);
                    //toast.show();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        b6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    if(!addBuffer2)
                    {
                        if (numberOfStreams == 0) {
                            numberOfStreams += 1.0;
                            addBuffer2 = true;
                            startStreaming();
                        } else {
                            numberOfStreams += 1.0;
                            addBuffer2 = true;
                        }
                    }
                    else if(addBuffer2)
                    {
                        addBuffer2=false;
                        numberOfStreams-=1.0;
                    }
                    //final CharSequence text = "Testing 1 2 3";
                    //Toast toast = Toast.makeText(getApplicationContext(),text,Toast.LENGTH_LONG);
                    //toast.show();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        b7.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    if(!addBuffer3)
                    {
                        if (numberOfStreams == 0) {
                            numberOfStreams += 1.0;
                            addBuffer3 = true;
                            startStreaming();
                        } else {
                            numberOfStreams += 1.0;
                            addBuffer3 = true;
                        }
                    }
                    else if(addBuffer3)
                    {
                        addBuffer3=false;
                        numberOfStreams-=1.0;
                    }
                    //final CharSequence text = "Testing 1 2 3";
                    //Toast toast = Toast.makeText(getApplicationContext(),text,Toast.LENGTH_LONG);
                    //toast.show();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        b8.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    if(!addBuffer4)
                    {
                        if (numberOfStreams == 0) {
                            numberOfStreams += 1.0;
                            addBuffer4 = true;
                            startStreaming();
                        } else {
                            numberOfStreams += 1.0;
                            addBuffer4 = true;
                        }
                    }
                    else if(addBuffer4)
                    {
                        addBuffer4=false;
                        numberOfStreams-=1.0;
                    }
                    //final CharSequence text = "Testing 1 2 3";
                    //Toast toast = Toast.makeText(getApplicationContext(),text,Toast.LENGTH_LONG);
                    //toast.show();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        b9.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    if(!addBuffer5)
                    {
                        if (numberOfStreams == 0) {
                            numberOfStreams += 1.0;
                            addBuffer5 = true;
                            startStreaming();
                        } else {
                            numberOfStreams += 1.0;
                            addBuffer5 = true;
                        }
                    }
                    else if(addBuffer5)
                    {
                        addBuffer5=false;
                        numberOfStreams-=1.0;
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });

        b10.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    if(!addBuffer10)
                    {
                        if (numberOfStreams == 0) {
                            numberOfStreams += 1.0;
                            addBuffer10 = true;
                            startStreaming();
                        } else {
                            numberOfStreams += 1.0;
                            addBuffer10 = true;
                        }
                    }
                    else if(addBuffer10)
                    {
                        addBuffer10=false;
                        numberOfStreams-=1.0;
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });

        b11.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    if(!addBuffer11)
                    {
                        if (numberOfStreams == 0) {
                            numberOfStreams += 1.0;
                            addBuffer11 = true;
                            startStreaming();
                        } else {
                            numberOfStreams += 1.0;
                            addBuffer11 = true;
                        }
                    }
                    else if(addBuffer11)
                    {
                        addBuffer11=false;
                        numberOfStreams-=1.0;
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
       //START APPLIES TO activity_main.xml:
       /* b1.setOnClickListener(new View.OnClickListener() {
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
                  //  outputBuffer = addBufferToMix(data7,dataSize7,outputBuffer,"plusbuttonseven.wav");
                   // tempBuffer=outputBuffer;
                    addBuffer7=true;
                    Log.d(TAG,"set addBuffer7 to true");
                   // m_audioTrack.write(outputBuffer,0,outputBuffer.length);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        b8.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    removeBuffer7=true;
                    Log.d(TAG,"set removeBuffer7 to true");
                   // outputBuffer=removeBufferFromMix(data9,dataSize9,outputBuffer,"minusbuttonnine.wav");
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
        //END APPLIES TO activity_main.xml
        */
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
                    AudioFormat.ENCODING_PCM_16BIT, bufferLength,
                    AudioTrack.MODE_STREAM);//100 is hardcoded buffer size in bytes. 'datasize' is size of sample.
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
            Log.d(TAG+"loading","Starting to load #6");
            WavInfo wi0 = new WavInfo();
            Log.d(TAG+"loading","Attempting to get resource");
            InputStream is0 = getResources().openRawResource(R.raw.beat1_mono);
            Log.d(TAG+"loading","Got resource, attempting header");
            dataSize6 = wi0.readHeader(is0);
            Log.d(TAG+"loading","Read header, attempting to read resource to byte buffer.");
            data6 = new byte[dataSize6];
            is0.read(data6, 0, data6.length);
            is0.close();
            Log.d(TAG+"loading","Finished loading #6");

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
            InputStream is2 = getResources().openRawResource(R.raw.sub_bass2);
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

            WavInfo wi4 = new WavInfo();
            InputStream is4 = getResources().openRawResource(R.raw.beat3_mono);
            dataSize1 = wi4.readHeader(is4);
            data1 = new byte[dataSize1];
            is4.read(data1, 0, data1.length);
            is4.close();

            WavInfo wi5 = new WavInfo();
            InputStream is5 = getResources().openRawResource(R.raw.boxy_synth);
            dataSize2 = wi5.readHeader(is5);
            data2 = new byte[dataSize2];
            is5.read(data2, 0, data2.length);
            is5.close();

            WavInfo wi6 = new WavInfo();
            InputStream is6 = getResources().openRawResource(R.raw.bright_strings);
            dataSize3 = wi6.readHeader(is6);
            data3 = new byte[dataSize3];
            is6.read(data3, 0, data3.length);
            is6.close();

            WavInfo wi7 = new WavInfo();
            InputStream is7 = getResources().openRawResource(R.raw.chords);
            dataSize4 = wi7.readHeader(is7);
            data4 = new byte[dataSize4];
            is7.read(data4, 0, data4.length);
            is7.close();

            WavInfo wi8 = new WavInfo();
            InputStream is8 = getResources().openRawResource(R.raw.hihat);
            dataSize5 = wi8.readHeader(is8);
            data5 = new byte[dataSize5];
            is8.read(data5, 0, data5.length);
            is8.close();

            WavInfo wi9 = new WavInfo();
            InputStream is9 = getResources().openRawResource(R.raw.sub_bass1);
            dataSize10 = wi9.readHeader(is9);
            data10 = new byte[dataSize10];
            is9.read(data10, 0, data10.length);
            is9.close();

            WavInfo wi10 = new WavInfo();
            InputStream is10 = getResources().openRawResource(R.raw.lead1);
            dataSize11 = wi10.readHeader(is10);
            data11 = new byte[dataSize11];
            is10.read(data11, 0, data11.length);
            is10.close();


            Log.d(TAG+"loading","Finished loading all 11 files.");
            //
        } catch (IOException e) {
            throw e;
        }
        Log.d(TAG+"fill","Exiting fill all buffers");
        return true;
    }
    synchronized byte[] addBufferToMix(byte[] buffer,int bufferSize,byte[] currentBuffer,String fileName)
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
    public synchronized byte[] getBufferUpdate()
    {
        return tempBuffer;
    }
    //Adding array data7 at the correct place
    //increment until you have changed everything back to where
    //you started. Then set false.
    public synchronized byte[] addBufferToStream(byte[] newBuffer,byte[] currentBuffer,int i) {
        //i = position in the array that is next to be fed to the outputbuffer.
        //i + the 99 next elements will be written to the Audiobuffer.
        Log.d(TAG, "Entered addBufferToStream.");
        short resPrevious = 0;
        byte[] subBuffer = Arrays.copyOfRange(newBuffer, i, i + 100);
        byte[] resultingBuffer = new byte[subBuffer.length];

        for (int m = 0; m < subBuffer.length; m += 2) {
            //  Log.d(TAG,"this is index: "+m);
            short buf1a = currentBuffer[m + 1];
            short buf2a = currentBuffer[m];
            buf1a = (short) ((buf1a & 0xff) << 8);
            buf2a = (short) (buf2a & 0xff);
            short buf1b = subBuffer[m + 1];
            short buf2b = subBuffer[m];
            buf1b = (short) ((buf1b & 0xff) << 8);
            buf2b = (short) (buf2b & 0xff);

            short buf1c = (short) (buf1a + buf1b);
            short buf2c = (short) (buf2a + buf2b);

            short res = (short) (buf1c + buf2c);
            float temporary = (float) res;
            float temp2 = temporary / 2;
            res = (short) temp2;

            if (res > 10000) //Avoid 'normal' cases where amplitude shifts from f.ex. 4 to -2, which we want to keep.
            {
                if ((res * resPrevious) < 0) //If the sign has changed suddenly for a large number, use the previous number.
                {
                    Log.d(TAG, "res:" + res + "");
                    res = resPrevious;
                }
            }
            if (res < -10000) {
                if ((res * resPrevious) < 0) //If the sign has changed suddenly for a large number, use the previous number.
                {
                    res = resPrevious;
                }
            }
            resPrevious = res;
            resultingBuffer[m] = (byte) res;
            resultingBuffer[m + 1] = (byte) (res >> 8);
            //counter += 2;
        }
        // temp=resultingBuffer;
        //Log.d(TAG, "temp just got changed.");
        //
        //outputBuffer=addBufferToMix(data7,dataSize7,outputBuffer,"thisFile");

        Log.d(TAG,"Exiting addBufferToStream. ");
        return resultingBuffer;
    }
    //Test for using a method outside the thread.
    public synchronized byte[] addByteArray(byte[] dataBuffer, byte[] temp,int i)
    {
        short resPrevious=0;
        byte[] subBuffer2 = Arrays.copyOfRange(data7,i,i+bufferLength);
        //Log.d(TAG,"this is testBuffer.length: "+testBuffer.length);
        byte[] temporaryBufferWithHalfAmplitude = new byte[bufferLength];
        byte[] temporaryOtherBufferWithHalfAmplitude = new byte[bufferLength];
        byte[] resultingBuffer = new byte[bufferLength];
        for (int m = 0; m < subBuffer2.length; m += 2)
        {
            short buf1a = temp[m + 1];
            short buf2a = temp[m];
            buf1a = (short) ((buf1a & 0xff) << 8);
            buf2a = (short) (buf2a & 0xff);
            short buf1b = subBuffer2[m + 1];
            short buf2b = subBuffer2[m];
            buf1b = (short) ((buf1b & 0xff) << 8);
            buf2b = (short) (buf2b & 0xff);
            //Dividing amplitude by half and writing to temporary buffer.
            short dude = (short) (buf1a + buf2a);
            float fdude = (float) (dude / 2.0);
            dude = (short) fdude;
            temporaryBufferWithHalfAmplitude[m] = (byte) dude;
            temporaryBufferWithHalfAmplitude[m + 1] = (byte) (dude >> 8);
            //Dividing amplitude by half and writing to temporary other buffer.
            short dudette = (short) (buf1b + buf2b);
            float fdudette = (float) (dudette / 2.0);
            dudette = (short) fdudette;
            temporaryOtherBufferWithHalfAmplitude[m] = (byte) dudette;
            temporaryOtherBufferWithHalfAmplitude[m + 1] = (byte) (dudette >> 8);
            //Getting values from temporary buffer.
            buf1a = temporaryBufferWithHalfAmplitude[m + 1];
            buf2a = temporaryBufferWithHalfAmplitude[m];
            buf1a = (short) ((buf1a & 0xff) << 8);
            buf2a = (short) (buf2a & 0xff);
            //Getting values from temporary other buffer.
            buf1b = temporaryOtherBufferWithHalfAmplitude[m + 1];
            buf2b = temporaryOtherBufferWithHalfAmplitude[m];
            buf1b = (short) ((buf1b & 0xff) << 8);
            buf2b = (short) (buf2b & 0xff);
            //Adding buffers.
            short buf1c = (short) (buf1a + buf1b);
            short buf2c = (short) (buf2a + buf2b);
            short res = (short) (buf1c + buf2c);
            //write to return buffer.
            resultingBuffer[m] = (byte) res;
            resultingBuffer[m + 1] = (byte) (res >> 8);
            addCounter7 += 2;
        }
            return resultingBuffer;
    }
}
