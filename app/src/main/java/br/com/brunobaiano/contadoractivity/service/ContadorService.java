package br.com.brunobaiano.contadoractivity.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;

/**
 * Created by Aluno on 15/07/2015.
 */
public class ContadorService extends Service implements Runnable {
private static final int MAX = 10;
    protected int count;
    private boolean ativo;
    private Handler myHandler = new Handler();
    private final IBinder conexao = new LocalBinder();

    @Override
    public void onCreate() {
        ativo = true;

    }

    @Override
    public IBinder onBind(Intent intent) {
        return conexao;
    }

    public void iniciar()
    {
        ativo = true;
        count = 0;
        myHandler.post(this);
    }

    public void parar()
    {
        myHandler.removeCallbacks(this);
        ativo = false;
    }

    public void run()
    {
        if (ativo && count < MAX){
            myHandler.postAtTime(this, SystemClock.uptimeMillis() + 1000);
            Log.i("exemplo", "ContadorService em execucao..."+ count);

            count++;
            return;
        }
    }

    @Override
    public void onDestroy() {
        ativo = false;
        Log.i("exemplo", "ContadorService passando pelo destroy...");
    }

    public int count(){
        return count;
    }

    public class LocalBinder extends Binder
    {
        public ContadorService getContador(){
            return ContadorService.this;
        }
    }
}
