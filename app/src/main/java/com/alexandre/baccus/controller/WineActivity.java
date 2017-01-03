package com.alexandre.baccus.controller;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.alexandre.baccus.R;

public class WineActivity extends Activity {
    private ImageView mWineImage = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wine);

        // Accedemos a la vista desde este controlador
        mWineImage = (ImageView)findViewById(R.id.wine_image);
		/*
		 *  Desde el controlador modificamos la vista para que muestre
		 *  otra imagen diferente usando identificadores
		 */
        Log.v("Baccus", "Estamos viendo una imagen de un vino");
        mWineImage.setImageResource(R.drawable.vegaval);
    }

}
