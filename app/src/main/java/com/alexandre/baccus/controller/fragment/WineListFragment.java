package com.alexandre.baccus.controller.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.alexandre.baccus.R;
import com.alexandre.baccus.controller.activity.WineryActivity;
import com.alexandre.baccus.models.Wine;
import com.alexandre.baccus.models.Winery;


public class WineListFragment extends Fragment {
    public OnWineSelectedListener mListener;

    public WineListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View root =  inflater.inflate(R.layout.fragment_wine_list, container, false);


        AsyncTask<Void, Void, Winery> wineryDownloader = new AsyncTask<Void, Void, Winery>() {
            @Override
            protected Winery doInBackground(Void... voids) {
                return Winery.getInstance();;
            }

            @Override
            protected void onPostExecute(Winery winery) {
                ListView listView = (ListView) root.findViewById(android.R.id.list);

                ArrayAdapter<Wine> adapter = new ArrayAdapter<Wine>(getActivity(), android.R.layout.simple_expandable_list_item_1, winery.getWineList());
                listView.setAdapter(adapter);

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        mListener.onWineSelected(i);
                    }
                });
            }
        };

        wineryDownloader.execute();
        
        return root;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        // Decimos a la actividad que implemente el interface
        mListener = (OnWineSelectedListener) getActivity();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        // Para evitar llamadas innecesarias cuando no formemos parte de la actividad
        mListener = null;

    }


    public interface OnWineSelectedListener {
        void onWineSelected(int wineIndex);
    }

}
