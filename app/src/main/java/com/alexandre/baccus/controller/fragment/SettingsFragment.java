package com.alexandre.baccus.controller.fragment;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;

import com.alexandre.baccus.R;
import com.alexandre.baccus.controller.activity.SettingsActivity;

/**
 * Created by alexandre on 4/2/17.
 */

public class SettingsFragment extends Fragment implements View.OnClickListener {
    public static final String ARG_WINE_IMAGE_SCALE_TYPE = "SettingsFragment.ARG_WINE_IMAGE_SCALE_TYPE";
    public static final String PREF_IMAGE_SCALE_TYPE = "PREF_IMAGE_SCALE_TYPE";

    // Vistas
    private RadioGroup mRadioGroup = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View root = inflater.inflate(R.layout.fragment_settings, container, false);

        // Asocio las vistas
        mRadioGroup = (RadioGroup) root.findViewById(R.id.scale_type_radios);

        // Compruebo cuál es la opción por defecto del usuario
        if (getArguments().getSerializable(ARG_WINE_IMAGE_SCALE_TYPE).equals(ImageView.ScaleType.FIT_XY)){
            mRadioGroup.check(R.id.fit_radio);
        } else if (getArguments().getSerializable(ARG_WINE_IMAGE_SCALE_TYPE).equals(ImageView.ScaleType.FIT_CENTER)){
            mRadioGroup.check(R.id.center_radio);
        }

        Button cancelButton = (Button) root.findViewById(R.id.cancel_button);
        Button saveButton = (Button) root.findViewById(R.id.save_button);

        // Asigno el onClickListener
        cancelButton.setOnClickListener(this);
        saveButton.setOnClickListener(this);

        return root;
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

        // Guardamos la configuración en las preferencias:
        // Declaramos el editor
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(getActivity()).edit();

        if (mRadioGroup.getCheckedRadioButtonId() == R.id.fit_radio){
            config.putExtra(SettingsActivity.EXTRA_WINE_IMAGE_SCALE_TYPE, ImageView.ScaleType.FIT_XY);
            // Lo añado al editor
            editor.putString(PREF_IMAGE_SCALE_TYPE, ImageView.ScaleType.FIT_XY.toString());

        } else if (mRadioGroup.getCheckedRadioButtonId() == R.id.center_radio){
            config.putExtra(SettingsActivity.EXTRA_WINE_IMAGE_SCALE_TYPE, ImageView.ScaleType.FIT_CENTER);
            // Lo añado al editor
            editor.putString(PREF_IMAGE_SCALE_TYPE, ImageView.ScaleType.FIT_CENTER.toString());
        }

        // Lo guardo
        editor.commit();
        getActivity().setResult(Activity.RESULT_OK, config);
        getActivity().finish();

    }

    private void cancelSettings() {
        getActivity().setResult(Activity.RESULT_CANCELED);
        getActivity().finish();
    }
}
