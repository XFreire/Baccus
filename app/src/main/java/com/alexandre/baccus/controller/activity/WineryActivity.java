package com.alexandre.baccus.controller.activity;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.TabHost;

import com.alexandre.baccus.R;
import com.alexandre.baccus.controller.fragment.WineryFragment;
import com.alexandre.baccus.models.Wine;

public class WineryActivity extends FragmentContainerActivity {

    @Override
    protected Fragment createFragment() {
        return new WineryFragment();
    }
}
