package com.nikolasmoya.hltb.timeToBeat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nikolasmoya.hltb.R;
import com.nikolasmoya.hltb.legacy.RemoteImage;

import java.util.ArrayList;

public class ActivityGames extends Activity
{
    private LinearLayout _container;
    private Button _back;

    String formatHours(String prefix, String hours)
    {
        if (hours == null || hours.equals("--"))
        {
            return prefix + " N/A";
        }
        else
        {
            return prefix + " " + hours;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_games);

        _container = (LinearLayout) findViewById(R.id.container_games);
        _back = (Button) findViewById(R.id.btn_back);
        _back.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                finish();
            }
        });

        Intent i = getIntent();
        Bundle extras = i.getExtras();
        ArrayList<Game> games = (ArrayList<Game>) extras.getSerializable("games");

        LayoutInflater inflater = getLayoutInflater();
        for (Game g : games)
        {
            View v = inflater.inflate(R.layout.inflate_entry, null, false);
            ImageView cover = (ImageView) v.findViewById(R.id.img_gameCover);
            new RemoteImage(cover).execute(g.getImgSrc());

            TextView title = (TextView) v.findViewById(R.id.txt_title);
            title.setText(g.getTitle());

            TextView mainStory = (TextView) v.findViewById(R.id.txt_mainStory);
            mainStory.setText(formatHours("Main Story", g.getMainStory()));

            TextView mainExtras = (TextView) v.findViewById(R.id.txt_mainExtras);
            mainExtras.setText(formatHours("Main Extras", g.getMainExtra()));

            TextView completionist = (TextView) v.findViewById(R.id.txt_completionist);
            completionist.setText(formatHours("Completionist", g.getCompletionist()));

            TextView combined = (TextView) v.findViewById(R.id.txt_combined);
            combined.setText(formatHours("Combined", g.getCombined()));

            _container.addView(v);
        }
    }
}
