package com.gnyrfta.david.looperdevelopment;

import android.media.AudioTrack;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class WavInfo
{
    AudioTrack track;
    short[] buffer = new short[1024];

    private static final String RIFF_HEADER = "RIFF";
    private static final String WAVE_HEADER = "WAVE";
    private static final String FMT_HEADER = "fmt ";
    private static final String DATA_HEADER = "data";
    private static final String TAG="AndroidAudioDevice";

    private static final int HEADER_SIZE = 44;

    private static final String CHARSET = "ASCII";

        /* ... */

    public int readHeader(InputStream wavStream)
            throws IOException {

        ByteBuffer buffer = ByteBuffer.allocate(HEADER_SIZE);
        buffer.order(ByteOrder.LITTLE_ENDIAN);
        int dataSize=0;
        try {
            wavStream.read(buffer.array(), buffer.arrayOffset(), buffer.capacity());

            buffer.rewind();
            buffer.position(buffer.position() + 20);
            int format = buffer.getShort();
            //checkFormat(format == 1, "Unsupported encoding: " + format); // 1 means Linear PCM
            int channels = buffer.getShort();
            //checkFormat(channels == 1 || channels == 2, "Unsupported channels: " + channels);
            int rate = buffer.getInt();
            //checkFormat(rate <= 48000 && rate >= 11025, "Unsupported rate: " + rate);
            buffer.position(buffer.position() + 6);
            int bits = buffer.getShort();
            //checkFormat(bits == 16, "Unsupported bits: " + bits);

            while (buffer.getInt() != 0x61746164) { // "data" marker
                Log.d(TAG, "Skipping non-data chunk");
                int size = buffer.getInt();
                wavStream.skip(size);
                buffer.rewind();
                wavStream.read(buffer.array(), buffer.arrayOffset(), 8);
                buffer.rewind();
            }
            dataSize = buffer.getInt();
        }
        catch (IOException e){throw e;}
        return dataSize;
        //checkFormat(dataSize > 0, "wrong datasize: " + dataSize);

      //  return new WavInfo(new FormatSpec(rate, channels == 2), dataSize);
    }


}