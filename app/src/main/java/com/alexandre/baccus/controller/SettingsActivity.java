package com.alexandre.baccus.controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;

import com.alexandre.baccus.R;

public class SettingsActivity extends Activity implements View.OnClickListener{
    public static final String EXTRA_WINE_IMAGE_SCALE_TYPE = "SettingsActivity.EXTRA_WINE_IMAGE_SCALE_TYPE";
    // Vistas
    private RadioGroup mRadioGroup = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        // Asocio las vistas
        mRadioGroup = (RadioGroup) findViewById(R.id.scale_type_radios);

        // Compruebo cuál es la opción por defecto del usuario
        if (getIntent().getSerializableExtra(EXTRA_WINE_IMAGE_SCALE_TYPE).equals(ImageView.ScaleType.FIT_XY)){
            mRadioGroup.check(R.id.fit_radio);
        } else if (getIntent().getSerializableExtra(EXTRA_WINE_IMAGE_SCALE_TYPE).equals(ImageView.ScaleType.FIT_CENTER)){
            mRadioGroup.check(R.id.center_radio);
        }

        Button cancelButton = (Button) findViewById(R.id.cancel_button);
        Button saveButton = (Button) findViewById(R.id.save_button);

        // Asigno el onClickListener
        cancelButton.setOnClickListener(this);
        saveButton.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.cancel_button: {
                cancelSettings();
                break;
            }
            case R.id.save_button: {
                saveSettings();
                break;
            }
        }
    }

    private void saveSettings() {
        Intent config = new Intent();
        if (mRadioGroup.getCheckedRadioButtonId() == R.id.fit_radio){
            config.putExtra(EXTRA_WINE_IMAGE_SCALE_TYPE, ImageView.ScaleType.FIT_XY);
        } else if (mRadioGroup.getCheckedRadioButtonId() == R.id.center_radio){
            config.putExtra(EXTRA_WINE_IMAGE_SCALE_TYPE, ImageView.ScaleType.FIT_CENTER);
        }

    }

    private void cancelSettings() {
        setResult(RESULT_CANCELED);
        finish();
    }
}
