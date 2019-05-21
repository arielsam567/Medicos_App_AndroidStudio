package com.example.medicos;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Intent it = new Intent(MainActivity.this, AdicionaPaciente.class);
                startActivity(it);
            }
        });


        ////
        ListView lvPacientes = findViewById(R.id.listview);

        DAL dal = new DAL(this);
        Cursor cursor = dal.loadAll();

        String[] fields = new String[] {CreateDatabase.ID, CreateDatabase.NOME,CreateDatabase.IDADE, CreateDatabase.MORTALIDADE};
        int[] ids = {R.id.tvId, R.id.tvNome,R.id.tvIdade,R.id.tvMortalidade};

        //Log.d(TAG, "onCreate: id " + cursor.getInt(0) + " title " + cursor.getString(1));
        Log.d(TAG, "onCreate: " + cursor.getCount());
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(MainActivity.this,
                R.layout.lista_pacientes, cursor, fields, ids, 0);

        lvPacientes.setAdapter(adapter);

       lvPacientes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this,  update_pacientr.class);
                intent.putExtra(CreateDatabase.ID,
                        Integer.valueOf(((TextView)view.findViewById(R.id.tvId)).getText().toString()));
                startActivity(intent);
            }
        });
        ////


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
