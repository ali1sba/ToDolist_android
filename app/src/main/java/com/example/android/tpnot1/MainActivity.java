package com.example.android.tpnot1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.speech.tts.TextToSpeech;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener,AdapterView.OnItemLongClickListener {
    TweetAdapter adapter;
    Intent intent;
    List<Tweet> tweets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView mListView = findViewById(R.id.list);
        if (savedInstanceState != null) {
            tweets = (List<Tweet>) savedInstanceState.get("key");
        }else{
            tweets = new ArrayList<Tweet>();
            tweets.add(new Tweet("ali", "Vacances de février durent 2 semaines", new Date(), R.drawable.ic_baseline_account_circle_24));

        }

        adapter = new TweetAdapter(MainActivity.this, tweets);
        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(this);
        mListView.setOnItemLongClickListener(this);
        intent = new Intent(MainActivity.this, DetailActivity.class);


    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        intent.putExtra("name", tweets.get(i).getName());
        intent.putExtra("text", tweets.get(i).getText());
        intent.putExtra("date", tweets.get(i).getDate().toString());
        //intent.putExtra("img", tweets.get(i).getImgURL());
        startActivity(intent);
        }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        final Dialog addNewTweetDialog = new Dialog(this);
        addNewTweetDialog.setTitle("Ajouter");
        addNewTweetDialog.setContentView(R.layout.adddialog);
        Button btnValider = addNewTweetDialog.findViewById(R.id.dialog_btn_ajouter);
        EditText inputText = addNewTweetDialog.findViewById(R.id.dialog_input);
        addNewTweetDialog.show();
        btnValider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tweets.add(new Tweet("ali", inputText.getText().toString(),new Date(),R.drawable.ic_baseline_account_circle_24));
                adapter.notifyDataSetChanged();
                addNewTweetDialog.dismiss();
            }
        });
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onSaveInstanceState(Bundle etat) {
        etat.putParcelableArrayList("key",  new ArrayList<Tweet> (tweets));
        super.onSaveInstanceState(etat);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
        AlertDialog.Builder confirm = new AlertDialog.Builder(this);
        confirm.setTitle("Suppression");
        confirm.setIcon(android.R.drawable.ic_dialog_alert);
        confirm.setMessage("Vous confirmez la suppression ?");
        confirm.setPositiveButton(android.R.string.ok,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int idBtn) {
                        tweets.remove(i);
                        adapter.notifyDataSetChanged();
                    }
                });
        confirm.setNegativeButton(android.R.string.cancel, null);
        confirm.show();
        return true;
    }
}