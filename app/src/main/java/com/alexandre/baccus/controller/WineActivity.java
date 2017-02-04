package com.alexandre.baccus.controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.alexandre.baccus.R;
import com.alexandre.baccus.models.Wine;

import java.util.Arrays;

public class WineActivity extends AppCompatActivity {
    public static final String EXTRA_WINE = "WineActivity.EXTRA_WINE";
    private static final int SETTINGS_REQUEST = 1;
    private static final String STATE_IMAGE_SCALE_TYPE = "WineActivity.STATE_IMAGE_SCALE_TYPE";
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

        // Recogemos el modelo
        mWine = (Wine) getIntent().getSerializableExtra(EXTRA_WINE);

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

        // Configuramos cómo se ve la imagen
        if (savedInstanceState != null && savedInstanceState.containsKey(STATE_IMAGE_SCALE_TYPE)){
            mWineImage.setScaleType((ImageView.ScaleType) savedInstanceState.getSerializable(STATE_IMAGE_SCALE_TYPE));
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == SETTINGS_REQUEST && resultCode == RESULT_OK){
            ImageView.ScaleType scaleType = (ImageView.ScaleType) data.getSerializableExtra(SettingsActivity.EXTRA_WINE_IMAGE_SCALE_TYPE);
            mWineImage.setScaleType(scaleType);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(STATE_IMAGE_SCALE_TYPE, mWineImage.getScaleType());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        if (item.getItemId() == R.id.action_settings){
            Intent intent = new Intent(this, SettingsActivity.class);
            intent.putExtra(SettingsActivity.EXTRA_WINE_IMAGE_SCALE_TYPE, mWineImage.getScaleType());
            startActivityForResult(intent, SETTINGS_REQUEST);
            return true;
        }
        return false;
    }
}
