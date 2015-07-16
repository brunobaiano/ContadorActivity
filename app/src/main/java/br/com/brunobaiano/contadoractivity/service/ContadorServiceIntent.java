package br.com.brunobaiano.contadoractivity.service;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.util.Log;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p/>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class ContadorServiceIntent extends IntentService {
protected int count = 0;

    public ContadorServiceIntent() {
        super("ContadorServiceIntent");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.i("exemplo", "ContadorService em execucao..." + count);
        count ++;
    }


}
