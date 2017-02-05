package com.alexandre.baccus.controller.activity;

import android.support.v4.app.Fragment;

import com.alexandre.baccus.controller.fragment.WineListFragment;

/**
 * Created by alexandre on 5/2/17.
 */

public class WineListActivity extends FragmentContainerActivity {
    @Override
    protected Fragment createFragment() {
        return new WineListFragment();
    }
}
