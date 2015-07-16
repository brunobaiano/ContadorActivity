package br.com.brunobaiano.contadoractivity;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import br.com.brunobaiano.contadoractivity.service.ContadorService;


public class MainActivity extends Activity implements ServiceConnection{

    private ContadorService contador;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button iniciar = (Button) findViewById(R.id.iniciar);

        iniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contador.iniciar();
            }
        });

        Button parar = (Button) findViewById(R.id.parar);

        parar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contador.parar();
            }
        });

        Button visualizar = (Button) findViewById(R.id.visualizar);
        visualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = contador.count();
                Toast.makeText(MainActivity.this, "C: " + count, Toast.LENGTH_LONG).show();
            }
        });
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

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        ContadorService.LocalBinder binder = (ContadorService.LocalBinder) service;
        contador = binder.getContador();
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        contador = null;
    }

    @Override
    protected void onResume() {
        super.onResume();
        bindService(new Intent(MainActivity.this, ContadorService.class), this, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unbindService(this);
    }
}
