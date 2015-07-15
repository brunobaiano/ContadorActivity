package br.com.brunobaiano.contadoractivity;

import android.app.Service;
import android.content.Intent;
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

    @Override
    public void onCreate() {
        ativo = true;
        myHandler.post(this);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public void run()
    {
        if (ativo && count < MAX){
            myHandler.postAtTime(this, SystemClock.uptimeMillis() + 1000);
            Log.i("exemplo", "ContadorService em execucao..."+ count);

            count++;
            return;
        }

        stopSelf();
    }

    @Override
    public void onDestroy() {
        ativo = false;
    }
}
