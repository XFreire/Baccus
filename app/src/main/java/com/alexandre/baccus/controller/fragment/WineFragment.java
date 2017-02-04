package com.alexandre.baccus.controller.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
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
import com.alexandre.baccus.controller.activity.SettingsActivity;
import com.alexandre.baccus.controller.activity.WebActivity;
import com.alexandre.baccus.models.Wine;

/**
 * Created by alexandre on 4/2/17.
 */

public class WineFragment extends Fragment {
    public static final String ARG_WINE = "WineFragment.ARG_WINE";
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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View root = inflater.inflate(R.layout.fragment_wine, container, false);

        // Recogemos el modelo
        mWine = (Wine) getArguments().getSerializable(ARG_WINE);

        // Asociamos controlador con vistas
        mWineImage = (ImageView) root.findViewById(R.id.wine_image);
        mWineNameText = (TextView) root.findViewById(R.id.wine_name);
        mWineTypeText = (TextView) root.findViewById(R.id.wine_type);
        mWineCompanyText = (TextView) root.findViewById(R.id.wine_company);
        mWineOriginText = (TextView) root.findViewById(R.id.wine_origin);
        mWineGrapesContainer = (ViewGroup) root.findViewById(R.id.grapes_container);
        mWineNotesText = (TextView) root.findViewById(R.id.wine_notes);
        mWineRatingBar = (RatingBar) root.findViewById(R.id.wine_rating);
        mGoToWebButton = (ImageButton)  root.findViewById(R.id.go_to_web_button);

        // Actualizamos la vista con el modelo
        mWineImage.setImageResource(mWine.getPhoto());
        mWineNameText.setText(mWine.getName());
        mWineTypeText.setText(mWine.getType());
        mWineCompanyText.setText(mWine.getWineCompanyName());
        mWineOriginText.setText(mWine.getOrigin());
        mWineNotesText.setText(mWine.getNotes());
        mWineRatingBar.setProgress(mWine.getRating());
        for (int i = 0; i < mWine.getGrapesCount(); i++) {
            TextView grapeText = new TextView(getActivity());
            grapeText.setText(mWine.getGrape(i));
            grapeText.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            mWineGrapesContainer.addView(grapeText);
        }


        // Configuramos botones

        mGoToWebButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), WebActivity.class);
                intent.putExtra(WebActivity.EXTRA_WINE, mWine);
                startActivity(intent);
            }
        });

        // Configuramos cómo se ve la imagen
        if (savedInstanceState != null && savedInstanceState.containsKey(STATE_IMAGE_SCALE_TYPE)){
            mWineImage.setScaleType((ImageView.ScaleType) savedInstanceState.getSerializable(STATE_IMAGE_SCALE_TYPE));
        }

        return root;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == SETTINGS_REQUEST && resultCode == Activity.RESULT_OK){
            ImageView.ScaleType scaleType = (ImageView.ScaleType) data.getSerializableExtra(SettingsActivity.EXTRA_WINE_IMAGE_SCALE_TYPE);
            mWineImage.setScaleType(scaleType);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(STATE_IMAGE_SCALE_TYPE, mWineImage.getScaleType());

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_main, menu);

    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        if (item.getItemId() == R.id.action_settings){
            Intent intent = new Intent(getActivity(), SettingsActivity.class);
            intent.putExtra(SettingsActivity.EXTRA_WINE_IMAGE_SCALE_TYPE, mWineImage.getScaleType());
            startActivityForResult(intent, SETTINGS_REQUEST);
            return true;
        }
        return false;
    }
}
