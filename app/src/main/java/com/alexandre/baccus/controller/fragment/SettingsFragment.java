package com.alexandre.baccus.controller.fragment;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
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

public class SettingsFragment extends DialogFragment implements View.OnClickListener {
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

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog =  super.onCreateDialog(savedInstanceState);
        dialog.setTitle(R.string.settings);
        return dialog;
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
        ImageView.ScaleType selectedScaleType = null;

        Intent config = new Intent();

        // Guardamos la configuración en las preferencias:
        // Declaramos el editor
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(getActivity()).edit();

        if (mRadioGroup.getCheckedRadioButtonId() == R.id.fit_radio){
            selectedScaleType = ImageView.ScaleType.FIT_XY;


        } else if (mRadioGroup.getCheckedRadioButtonId() == R.id.center_radio){
            selectedScaleType = ImageView.ScaleType.FIT_CENTER;
        }

        config.putExtra(SettingsActivity.EXTRA_WINE_IMAGE_SCALE_TYPE, selectedScaleType);
        // Lo añado al editor
        editor.putString(PREF_IMAGE_SCALE_TYPE, selectedScaleType.toString());

        // Lo guardo
        editor.commit();

        // Preguntamos si nos han llamado desde un fragment o desde un activiy:
        if (getTargetFragment() != null){
            getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, config);

            // Quito el dialogo de pantalla
            dismiss();
        }
        else {
            getActivity().setResult(Activity.RESULT_OK, config);
            getActivity().finish();
        }
    }

    private void cancelSettings() {
        if (getTargetFragment() != null) {
            getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_CANCELED, null);
            dismiss();
        }
        else {
            getActivity().setResult(Activity.RESULT_CANCELED);
            getActivity().finish();
        }

    }
}
