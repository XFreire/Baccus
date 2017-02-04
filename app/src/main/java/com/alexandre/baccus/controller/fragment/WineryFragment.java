package com.alexandre.baccus.controller.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alexandre.baccus.R;
import com.alexandre.baccus.models.Wine;
import com.alexandre.baccus.models.Winery;

/**
 * Created by alexandre on 4/2/17.
 */

public class WineryFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View root = inflater.inflate(R.layout.fragment_winery, container, false);

        Winery winery = Winery.getInstance();
        Wine bembibre = winery.getWine(0);
        Wine vegaval = winery.getWine(1);
        FragmentTabHost tabHost = (FragmentTabHost) root.findViewById(android.R.id.tabhost);
        tabHost.setup(getActivity(), getActivity().getSupportFragmentManager(), android.R.id.tabcontent);

        // Añadimos la primera pestaña
        Bundle arguments = new Bundle();
        arguments.putSerializable(WineFragment.ARG_WINE, bembibre);
        tabHost.addTab(tabHost.newTabSpec(bembibre.getName()).setIndicator(bembibre.getName()), WineFragment.class, arguments);

        // Añadimos la segunda pestaña
        arguments = new Bundle();
        arguments.putSerializable(WineFragment.ARG_WINE, vegaval);
        tabHost.addTab(tabHost.newTabSpec(vegaval.getName()).setIndicator(vegaval.getName()), WineFragment.class, arguments);

        return root;
    }
}
