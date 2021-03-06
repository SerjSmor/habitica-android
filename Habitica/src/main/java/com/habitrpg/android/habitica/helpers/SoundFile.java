package com.habitrpg.android.habitica.helpers;

import android.media.AudioManager;
import android.media.MediaPlayer;

import java.io.File;

public class SoundFile {
    private String theme;
    private String fileName;
    private File file;
    private MediaPlayer mp;
    private Boolean playerPrepared = false;

    public SoundFile(String theme, String fileName){

        this.theme = theme;
        this.fileName = fileName;
        mp = new MediaPlayer();
    }

    public String getTheme() {
        return theme;
    }

    public String getFileName() {
        return fileName;
    }

    public String getWebUrl(){
        return "https://habitica.com/assets/audio/"+getTheme()+"/"+getFileName()+".mp3";
    }

    public String getFilePath() {
        return getTheme()+"_"+getFileName()+".mp3";
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public void prepareMediaPlayer(){
        if(playerPrepared) {
            return;
        }

        String path = file.getPath();

        try {
            mp.setDataSource(path);
            mp.setAudioStreamType(AudioManager.STREAM_NOTIFICATION);
            mp.prepare();

            playerPrepared = true;

            file = null;
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void play(){
        prepareMediaPlayer();
        if(mp.isPlaying()) {
            mp.stop();
        }

        mp.start();
    }
}
