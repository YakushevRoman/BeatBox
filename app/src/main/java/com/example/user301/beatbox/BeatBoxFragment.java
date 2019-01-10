package com.example.user301.beatbox;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.List;

import static com.example.user301.beatbox.BeatBox.TAG;

public class BeatBoxFragment extends Fragment {

    private BeatBox rBeatBox;
    public static BeatBoxFragment newInstance(){
        return new BeatBoxFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setRetainInstance(true);

        rBeatBox = new BeatBox(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_beat_box, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.fragment_beat_box_recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        recyclerView.setAdapter(new SoundAdapter(rBeatBox.getrSounds()));
        return view;
    }
    // холдер
    private class SoundHolder extends RecyclerView.ViewHolder{

        private Button rButton;
        private Sound rSound;

        public SoundHolder(LayoutInflater inflater, ViewGroup container) {
            super(inflater.inflate(R.layout.list_item_sound, container, false));

            rButton = (Button) itemView.findViewById(R.id.list_item_sound_button);
            rButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    rBeatBox.play(rSound);
                }
            });
        }
        // передаем название файлв в
        public void bindSound (Sound sound){
            rSound = sound;
            rButton.setText(rSound.getrName());
        }
    }
    // адаптер
    private  class SoundAdapter extends RecyclerView.Adapter<SoundHolder>{

        private List<Sound> rSounds;

        public SoundAdapter(List<Sound> rSounds) {
            this.rSounds = rSounds;
        }

        @Override
        public SoundHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());

            return new SoundHolder(layoutInflater, viewGroup);
        }

        @Override
        public void onBindViewHolder(SoundHolder soundHolder, int i) {
            Sound sound = rSounds.get(i);
            soundHolder.bindSound(sound);
        }

        @Override
        public int getItemCount() {
            return rSounds.size();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        rBeatBox.replays();
    }
}
