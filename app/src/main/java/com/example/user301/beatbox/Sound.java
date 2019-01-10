package com.example.user301.beatbox;

import android.util.Log;

import java.util.Arrays;

public class Sound {
    public static final String TAG = "BeatBox";
    private String rAsserPath;
    private String rName;
    private Integer rSoundId;

    public Sound(String rAsserPath) {
        this.rAsserPath = rAsserPath;
        // определяется имя файла
        String [] components = rAsserPath.split("/");
        // получение
        String fileName = components[components.length-1];

        // удаляет расширения
        rName = fileName.replace(".wav","");
    }

    public String getrAsserPath() {
        return rAsserPath;
    }

    public String getrName() {
        return rName;
    }

    public Integer getrSoundId() {
        return rSoundId;
    }

    public void setrSoundId(Integer rSoundId) {
        this.rSoundId = rSoundId;
    }
}
