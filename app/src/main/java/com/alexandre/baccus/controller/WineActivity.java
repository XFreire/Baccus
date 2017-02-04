package com.alexandre.baccus.controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.alexandre.baccus.R;
import com.alexandre.baccus.models.Wine;

import java.util.Arrays;

public class WineActivity extends Activity {
    /**
     * Vistas
     */
    private ImageView mWineImage = null;
    private TextView mWineNameText = null;
    private TextView mWineCompanyText = null;
    private TextView mWineTypeText = null;
    private TextView mWineOriginText = null;
    private ViewGroup mWineGrapesContainer = null;
    private TextView mWineNotesText = null;
    private RatingBar mWineRatingBar = null;
    private ImageButton mGoToWebButton = null;

    /**
     * Modelo
     */
    private Wine mWine = null; // Modelo

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wine);

        // Creamos el modelo
        mWine = new Wine("Bembibre",
                "Dominio de Tares",
                "Tinto",
                "El Bierzo",
                "http://www.dominiodetares.com/portfolio/bembibre/",
                "Este vino muestra toda la complejidad y la elegancia de la variedad Mencía. En fase visual luce un color rojo picota muy cubierto con tonalidades violáceas en el menisco. En nariz aparecen recuerdos frutales muy intensos de frutas rojas (frambuesa, cereza) y una potente ciruela negra, así como tonos florales de la gama de las rosas y violetas, vegetales muy elegantes y complementarios, hojarasca verde, tabaco y maderas aromáticas (sándalo) que le brindan un toque ciertamente perfumado.",
                R.drawable.bembibre,
                5);
        mWine.addGrape("Tempranillo");

        // Asociamos controlador con vistas
        mWineImage = (ImageView)findViewById(R.id.wine_image);
        mWineNameText = (TextView)findViewById(R.id.wine_name);
        mWineTypeText = (TextView)findViewById(R.id.wine_type);
        mWineCompanyText = (TextView)findViewById(R.id.wine_company);
        mWineOriginText = (TextView)findViewById(R.id.wine_origin);
        mWineGrapesContainer = (ViewGroup)findViewById(R.id.grapes_container);
        mWineNotesText = (TextView)findViewById(R.id.wine_notes);
        mWineRatingBar = (RatingBar)findViewById(R.id.wine_rating);
        mGoToWebButton = (ImageButton) findViewById(R.id.go_to_web_button);

        // Actualizamos la vista con el modelo
        mWineImage.setImageResource(mWine.getPhoto());
        mWineNameText.setText(mWine.getName());
        mWineTypeText.setText(mWine.getType());
        mWineCompanyText.setText(mWine.getWineCompanyName());
        mWineOriginText.setText(mWine.getOrigin());
        mWineNotesText.setText(mWine.getNotes());
        mWineRatingBar.setProgress(mWine.getRating());
        for (int i = 0; i < mWine.getGrapesCount(); i++) {
            TextView grapeText = new TextView(this);
            grapeText.setText(mWine.getGrape(i));
            grapeText.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            mWineGrapesContainer.addView(grapeText);
        }


        // Configuramos botones

        mGoToWebButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WineActivity.this, WebActivity.class);
                intent.putExtra(WebActivity.EXTRA_WINE, mWine);
                startActivity(intent);
            }
        });



    }
}
