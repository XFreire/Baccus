package com.alexandre.baccus.controller;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.alexandre.baccus.R;
import com.alexandre.baccus.models.Wine;

import java.util.Arrays;

public class WineActivity extends Activity {
    private ImageView mWineImage = null; // Vista
    private Wine mWine = null; // Modelo
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wine);

        mWine = new Wine("Bembibre",
                "Dominio de Tares",
                "Tinto",
                "El Bierzo",
                "http://www.dominiodetares.com/web/esp/vino.php?id=4",
                "Este vino muestra toda la complejidad y la elegancia de la variedad Mencía. En fase visual luce un color rojo picota muy cubierto con tonalidades violáceas en el menisco. En nariz aparecen recuerdos frutales muy intensos de frutas rojas (frambuesa, cereza) y una potente ciruela negra, así como tonos florales de la gama de las rosas y violetas, vegetales muy elegantes y complementarios, hojarasca verde, tabaco y maderas aromáticas (sándalo) que le brindan un toque ciertamente perfumado.",
                R.drawable.bembibre,
                5);
        // Accedemos a la vista desde este controlador
        mWineImage = (ImageView)findViewById(R.id.wine_image);
		/*
		 *  Desde el controlador modificamos la vista para que muestre
		 *  otra imagen diferente usando identificadores
		 */
        mWineImage.setImageResource(R.drawable.vegaval);
		// Asociamos la vista con el modelo
        mWineImage.setImageResource(mWine.getPhoto());


    }

    public void changeModel(View view){
        // Cambiamos el modelo
        this.mWine = new Wine("Vegaval Plata Gran Reserva 2004",
                "Miguel Calatayud",
                "Tinto",
                "Valdepeñas",
                "http://www.vegaval.com/es/",
                "Un vino de esmerado proceso de elaboración y larga crianza. Presenta un color rojo cereza con matices a teja y una brillante capa media alta. Nariz compleja, fina y elegante. Es excelentemente estructurado, amplio y muy sabroso. Recomendado para acompañar quesos curados, estofados y todo tipo de carnes rojas y de caza. La temperatura recomendada para servir está entre los 16º C y 18º C.",
                R.drawable.vegaval,
                5);

        // Actualizamos la vista
        mWineImage.setImageResource(mWine.getPhoto());

    }
}
