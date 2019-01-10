package com.example.user301.beatbox;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.Log;
import java.io.IOException;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BeatBox {

    public static final String TAG = "BeatBox";
    private static final String SOUNDS_FOLDER = "sample_sounds";
    // для звуков
    public static final int SOUND_MAX = 5;

    private AssetManager rAssets;
    private List<Sound> rSounds = new ArrayList<>();
    // класс для звуков
    SoundPool rSoundPool;

    public BeatBox(Context context) {
        rAssets = context.getAssets();
        // инициализация конструктора для звуков
        /**
         * 1 - макс кол звуков
         * 2 - тип аудио потока
         * 3 - дискретизация
         */
        rSoundPool = new SoundPool(SOUND_MAX, AudioManager.STREAM_MUSIC, 0);
        loadSound();
    }

    private void loadSound() {
        String[] soundNames;

        try {
            soundNames = rAssets.list(SOUNDS_FOLDER);
            Log.i(TAG, "Found " + soundNames.length + " sounds");
            //Log.i(TAG, Arrays.toString(soundNames));
        } catch (IOException e) {
            Log.d(TAG, "Could not list assets", e);
            e.printStackTrace();
            return;
        }
        // связываем экземпляр класса с обьектом Sound
        for (String fileName : soundNames){
            String assetPath = SOUNDS_FOLDER + "/" + fileName;
            Sound sound = new Sound(assetPath);
            load(sound);
            rSounds.add(sound);
        }
    }

    private void load (Sound sound){
        try {
            AssetFileDescriptor assetFileDescriptor = rAssets.openFd(sound.getrAsserPath());
            // загружает файл для послед воспроизведения
            int soundID = rSoundPool.load(assetFileDescriptor, 1);
            //сохрание идентификатора
            sound.setrSoundId(soundID);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void play (Sound sound){
        Integer soundID = sound.getrSoundId();
        if (sound == null){
            return;
        }
        rSoundPool.play(soundID,1.0f, 1.0f, 1, 0, 1.0f);
    }

    public void replays (){
        rSoundPool.release();
    }

    public List<Sound> getrSounds() {
        return rSounds;
    }
}
