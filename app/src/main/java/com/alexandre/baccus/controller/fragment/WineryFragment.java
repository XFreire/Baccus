package com.alexandre.baccus.controller.fragment;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.annotation.BoolRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringDef;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.alexandre.baccus.R;
import com.alexandre.baccus.controller.adapter.WineryPagerAdapter;
import com.alexandre.baccus.models.Wine;
import com.alexandre.baccus.models.Winery;

/**
 * Created by alexandre on 4/2/17.
 */

public class WineryFragment extends Fragment implements ViewPager.OnPageChangeListener {
    public static  final String ARG_WINE_INDEX = "WineryFragment.ARG_WINE_INDEX";
    public static  final String PREF_LAST_WINE_INDEX = "PREF_LAST_WINE_INDEX";

    private ViewPager mPager = null;
    private ActionBar mActionBar;
    private Winery mWinery;
    private ProgressDialog mProgress;

    public static WineryFragment newInstance(int wineIndex) {
        Bundle arguments = new Bundle();
        arguments.putInt(ARG_WINE_INDEX, wineIndex);
        WineryFragment fragment = new WineryFragment();
        fragment.setArguments(arguments);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        final View root = inflater.inflate(R.layout.fragment_winery, container, false);





        mWinery = Winery.getInstance();

        AsyncTask<Void, Void, Winery> wineryDownloader = new AsyncTask<Void, Void, Winery>() {
            @Override
            protected Winery doInBackground(Void... voids) {
                return Winery.getInstance();
            }

            @Override
            protected void onPostExecute(Winery winery) {
                Winery mWinery = winery;

                mPager = (ViewPager) root.findViewById(R.id.pager);
                mPager.setAdapter(new WineryPagerAdapter(getFragmentManager()));
                // Actualizamos la UI
                mActionBar = (ActionBar) ((AppCompatActivity) getActivity()).getSupportActionBar();
                mPager.addOnPageChangeListener(WineryFragment.this);


                int initialWineIndex = getArguments().getInt(ARG_WINE_INDEX);
                mPager.setCurrentItem(initialWineIndex);

                mProgress.dismiss();
            }
        };
        mProgress = new ProgressDialog(getActivity());
        mProgress.setTitle(R.string.loading);

        if (!Winery.isInstanceAvailable()) {
            mProgress.show();
        }
        wineryDownloader.execute();


        return root;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_winery, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        boolean superValue = super.onOptionsItemSelected(item);

        if (mPager != null){
            if (item.getItemId() == R.id.menu_next && mPager.getCurrentItem() < mWinery.getWineCount() - 1){
                mPager.setCurrentItem(mPager.getCurrentItem() + 1);
                return true;
            } else if (item.getItemId() == R.id.menu_prev && mPager.getCurrentItem() > 0){
                mPager.setCurrentItem(mPager.getCurrentItem() - 1);
                return true;
            } else {
                return superValue;
            }
        }
        else {
            return superValue;
        }


    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);

        if (mPager != null){
            MenuItem nextItem = (MenuItem) menu.findItem(R.id.menu_next);
            MenuItem prevItem = (MenuItem) menu.findItem(R.id.menu_prev);

            nextItem.setEnabled(mPager.getCurrentItem() < mWinery.getWineCount() - 1);
            prevItem.setEnabled(mPager.getCurrentItem() > 0);
        }

    }


    private void updateActionBar(int index) {
        mActionBar.setTitle(mWinery.getWine(index).getName());
    }

    public void changeWine(int wineIndex) {
        mPager.setCurrentItem(wineIndex);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        mActionBar.setTitle(mWinery.getWine(position).getName());

        // Como cada vez que se muestra un vino, el programa pasa por este método,
        // es aquí donde vamos a guardar el último vino mostrado
        PreferenceManager
                .getDefaultSharedPreferences(getActivity())
                .edit()
                .putInt(PREF_LAST_WINE_INDEX, position)
                .commit();
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        if (mPager != null){
            getArguments().putInt(ARG_WINE_INDEX, mPager.getCurrentItem());
        }
    }
}
