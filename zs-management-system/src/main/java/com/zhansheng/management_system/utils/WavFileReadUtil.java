package com.zhansheng.management_system.utils;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import java.io.File;
import java.util.LinkedList;

/**
 * 绘制波形图
 *
 * FileName: Wavdemo
 * Author:   xufu
 * Date:     2019/10/24 17:16
 * Description:
 */
public class WavFileReadUtil {

    private static LinkedList<Short> deque = new LinkedList<Short>();
    public static void put(short v) {
        synchronized (deque) {
            deque.add(v);
            if(deque.size() > 100000) {
                deque.removeFirst();
            }
        }
    }

    public static LinkedList<Short> readWav(String filename) throws Exception {

        File file = new File(filename);
        AudioInputStream ais = AudioSystem.getAudioInputStream(file);
        printFormat(ais.getFormat());

        byte[] buf = new byte[4];
        int len;
        while((len=ais.read(buf))!=-1) {

            if(ais.getFormat().getChannels() == 2) {
                if(ais.getFormat().getSampleRate() == 16) {
                    put((short) ((buf[1] << 8) | buf[0]));//左声道
                } else {
                    put(buf[1]);//左声道
                    put(buf[3]);//左声道
                }
            } else {
                if(ais.getFormat().getSampleRate() == 16) {
                    put((short) ((buf[1] << 8) | buf[0]));
                    put((short) ((buf[3] << 8) | buf[2]));
                } else {
                    put(buf[0]);
                    put(buf[1]);
                    put(buf[2]);
                    put(buf[3]);

                }
            }
        }
        ais.close();
        return  deque;
    }

    public static void printFormat(AudioFormat format) {
        System.out.println(format.getEncoding() + " => "
                + format.getSampleRate()+" hz, "
                + format.getSampleSizeInBits() + " bit, "
                + format.getChannels() + " channel, "
                + format.getFrameRate() + " frames/second, "
                + format.getFrameSize() + " bytes/frame");
    }
}
