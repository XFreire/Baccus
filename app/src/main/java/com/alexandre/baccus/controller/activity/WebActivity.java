package com.alexandre.baccus.controller.activity;


import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.alexandre.baccus.R;
import com.alexandre.baccus.controller.fragment.WebFragment;
import com.alexandre.baccus.models.Wine;

/**
 * Created by alexandre on 3/2/17.
 */

public class WebActivity extends FragmentContainerActivity {
    public static final String EXTRA_WINE = "WebActivity.EXTRA_WINE";

    @Override
    protected Fragment createFragment() {
        Bundle arguments = new Bundle();
        arguments.putSerializable(WebFragment.ARG_WINE, getIntent().getSerializableExtra(EXTRA_WINE));

        WebFragment fragment = new WebFragment();

        fragment.setArguments(arguments);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        return fragment;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
