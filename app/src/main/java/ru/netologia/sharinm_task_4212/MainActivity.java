package ru.netologia.sharinm_task_4212;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ItemDataAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        ListView listView = findViewById(R.id.listView);

        setSupportActionBar(toolbar);

        adapter = new ItemDataAdapter(this, null);

        adapter.addItem(new ItemData("Урок № 1", getString(R.string.app_name_calendar), ContextCompat.getDrawable(MainActivity.this, R.drawable.android1)));
        adapter.addItem(new ItemData("Урок № 2", getString(R.string.app_name_checkbox), ContextCompat.getDrawable(MainActivity.this, R.drawable.android1)));
        adapter.addItem(new ItemData("Урок № 3", getString(R.string.app_name_notes), ContextCompat.getDrawable(MainActivity.this, R.drawable.android2)));
        adapter.addItem(new ItemData("Урок № 4", getString(R.string.app_name_spinner), ContextCompat.getDrawable(MainActivity.this, R.drawable.android2)));

        adapter.addItem(new ItemData("Очень-очень длинное название урока, по которому что-то надо было сделать!",
                "Длиннющее сообщение под темой урока, не опонятно ля чего такое длинное!", ContextCompat.getDrawable(MainActivity.this, R.drawable.android)));
        adapter.addItem(new ItemData("Ещё один урок!",
                "Это длинный комментарий под уроком, который может быть вообще........  ну, честное слово...... вот теперь точно длинее всех!",
                        ContextCompat.getDrawable(MainActivity.this, R.drawable.android)));
        adapter.addItem(new ItemData("Длинная тема урока, но при этом будет короткая подтема, потому что длинее урока подтема не может быть!",
                "И правда, короткая подтема!", ContextCompat.getDrawable(MainActivity.this, R.drawable.android)));


        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showItemData(position);
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                adapter.removeItem(position);
                return true;
            }
        });
    }

    private void showItemData(int position) {
        ItemData itemData = adapter.getItem(position);
        Toast.makeText(MainActivity.this,
                ": " + itemData.getTitle() + "\n" +
                        "Подтема: " + itemData.getSubtitle() + "\n",
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        Intent intent = null;

        switch (id) {
            case R.id.action_open_notes:
                intent = new Intent(MainActivity.this, NotesActivity.class);
                break;
            case R.id.action_open_calendar:
                intent = new Intent(MainActivity.this, CalendarActivity.class);
                break;
            case R.id.action_open_spinner:
                intent = new Intent(MainActivity.this, SpinnerActivity.class);
                break;
            case R.id.action_open_checkbox:
                intent = new Intent(MainActivity.this, CheckBoxActivity.class);
                break;
            default:
                Toast.makeText(MainActivity.this, getString(R.string.textMessageErrorNotChecked), Toast.LENGTH_LONG).show();
                break;
        }

        if (intent != null) {
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}
