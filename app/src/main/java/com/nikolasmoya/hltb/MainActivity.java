package com.nikolasmoya.hltb;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.jsoup.nodes.Document;

import java.util.ArrayList;

public class MainActivity extends Activity implements HttpRequestListener, View.OnClickListener
{
    private ProgressDialog _dialog;
    private Button _submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        _dialog = new ProgressDialog(MainActivity.this);
        _dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        _dialog.setMessage(getString(R.string.http_loading_dialog));
        _dialog.setIndeterminate(true);
        _dialog.setCanceledOnTouchOutside(false);

        _submitButton = (Button) findViewById(R.id.btn_submit);
        _submitButton.setOnClickListener(this);
    }

    void processSubmit()
    {
        TextView query = (TextView) findViewById(R.id.txt_queryString);
        String queryString = query.getText().toString();
        if (queryString.length() > 0)
        {
            new Remote(this).execute("http://howlongtobeat.com/search_main.php?page=1", queryString);
            _dialog.show();
        }
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.btn_submit:
                processSubmit();
                break;
        }
    }

    @Override
    public void onHttpResponse(Document response)
    {
        _dialog.dismiss();
        if (response == null)
        {
            return;
        }
        ParseHLTBResponse parser = new ParseHLTBResponse(response);
        ArrayList<Game> games = parser.deserialize();

        Intent intent = new Intent(MainActivity.this, ActivityGames.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("games", games);
        intent.putExtras(bundle);
        startActivityForResult(intent, 0);
    }

}
