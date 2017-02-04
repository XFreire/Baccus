package com.alexandre.baccus.controller;

import android.app.IntentService;
import android.app.TabActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TabHost;

import com.alexandre.baccus.R;
import com.alexandre.baccus.models.Wine;

public class WineryActivity extends TabActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winery);

        Wine bembibre = new Wine("Bembibre",
                "Dominio de Tares",
                "Tinto",
                "El Bierzo",
                "http://www.dominiodetares.com/portfolio/bembibre/",
                "Este vino muestra toda la complejidad y la elegancia de la variedad Mencía. En fase visual luce un color rojo picota muy cubierto con tonalidades violáceas en el menisco. En nariz aparecen recuerdos frutales muy intensos de frutas rojas (frambuesa, cereza) y una potente ciruela negra, así como tonos florales de la gama de las rosas y violetas, vegetales muy elegantes y complementarios, hojarasca verde, tabaco y maderas aromáticas (sándalo) que le brindan un toque ciertamente perfumado.",
                R.drawable.bembibre,
                5);
        bembibre.addGrape("Mencía");

        Wine vegaval = new Wine("Vegaval",
                "Miguel Calatayud",
                "Tinto",
                "Valdepeñas",
                "http://www.vegaval.com/es/",
                "Un vino de esmerado proceso de elaboración y larga crianza. Presenta un color rojo cereza con matices a teja y una brillante capa media alta. Nariz compleja, fina y elegante. Es excelentemente estructurado, amplio y muy sabroso. Recomendado para acompañar quesos curados, estofados y todo tipo de carnes rojas y de caza. La temperatura recomendada para servir está entre los 16º C y 18º C.",
                R.drawable.vegaval,
                5);
        vegaval.addGrape("Tempranillo");


        TabHost tabHost = getTabHost();

        // Creamos la primera pestaña
        Intent intent = new Intent(this, WineActivity.class);
        intent.putExtra(WineActivity.EXTRA_WINE, bembibre);
        TabHost.TabSpec spec = tabHost
                .newTabSpec(bembibre.getName())
                .setIndicator(bembibre.getName())
                .setContent(intent);
        tabHost.addTab(spec);

        // Creamos la segunda pestaña
        intent = new Intent(this, WineActivity.class);
        intent.putExtra(WineActivity.EXTRA_WINE, vegaval);
        spec = tabHost
                .newTabSpec(vegaval.getName())
                .setIndicator(vegaval.getName())
                .setContent(intent);
        tabHost.addTab(spec);
    }

}
