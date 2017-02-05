package com.alexandre.baccus.controller.activity;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.widget.TabHost;

import com.alexandre.baccus.R;
import com.alexandre.baccus.controller.fragment.WineryFragment;
import com.alexandre.baccus.models.Wine;

public class WineryActivity extends FragmentContainerActivity {
    public static final String EXTRA_WINE_INDEX = "WineryActivity.EXTRA_WINE_INDEX"; ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected Fragment createFragment() {
        return WineryFragment.newInstance(getIntent().getIntExtra(EXTRA_WINE_INDEX, 0));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            NavUtils.navigateUpFromSameTask(this);
        }
        return super.onOptionsItemSelected(item);

    }
}
