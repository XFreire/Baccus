package com.alexandre.baccus.controller.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;

import com.alexandre.baccus.R;
import com.alexandre.baccus.controller.activity.WineActivity;
import com.alexandre.baccus.models.Wine;

/**
 * Created by alexandre on 4/2/17.
 */

public class WineryFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View root = inflater.inflate(R.layout.activity_winery, container, false);

        Wine bembibre = new Wine("Bembibre",
                "Dominio de Tares",
                "Tinto",
                "El Bierzo",
                "http://www.dominiodetares.com/portfolio/bembibre/",
                "Este vino muestra toda la complejidad y la elegancia de la variedad Mencía. En fase visual luce un color rojo picota muy cubierto con tonalidades violáceas en el menisco. En nariz aparecen recuerdos frutales muy intensos de frutas rojas (frambuesa, cereza) y una potente ciruela negra, así como tonos florales de la gama de las rosas y violetas, vegetales muy elegantes y complementarios, hojarasca verde, tabaco y maderas aromáticas (sándalo) que le brindan un toque ciertamente perfumado.",
                R.drawable.bembibre,
                5);
        bembibre.addGrape("Mencía");

        Wine vegaval = new Wine("Vegaval",
                "Miguel Calatayud",
                "Tinto",
                "Valdepeñas",
                "http://www.vegaval.com/es/",
                "Un vino de esmerado proceso de elaboración y larga crianza. Presenta un color rojo cereza con matices a teja y una brillante capa media alta. Nariz compleja, fina y elegante. Es excelentemente estructurado, amplio y muy sabroso. Recomendado para acompañar quesos curados, estofados y todo tipo de carnes rojas y de caza. La temperatura recomendada para servir está entre los 16º C y 18º C.",
                R.drawable.vegaval,
                5);
        vegaval.addGrape("Tempranillo");

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
