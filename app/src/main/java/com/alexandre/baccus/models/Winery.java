package com.alexandre.baccus.models;

import com.alexandre.baccus.R;

import java.util.Arrays;
import java.util.List;

/**
 * Created by alexandre on 4/2/17.
 */

public class Winery {
    private static Winery sInstance = null;
    private List<Wine> mWines = null;

    public static Winery getInstance() {
        if (sInstance == null) {
            sInstance = new Winery();
        }
        return sInstance;
    }

    public Winery() {
        Wine bembibre = new Wine("Bembibre",
                "Dominio de Tares",
                "tinto",
                "El Bierzo",
                "http://www.dominiodetares.com/index.php/es/vinos/baltos/74-bembibrevinos",
                "Este vino muestra toda la complejidad y la elegancia de la variedad Mencía. En fase visual luce un color rojo picota muy cubierto con tonalidades violáceas en el menisco. En nariz aparecen recuerdos frutales muy intensos de frutas rojas (frambuesa, cereza) y una potente ciruela negra, así como tonos florales de la gama de las rosas y violetas, vegetales muy elegantes y complementarios, hojarasca verde, tabaco y maderas aromáticas (sándalo) que le brindan un toque ciertamente perfumado.",
                R.drawable.bembibre,
                5);
        bembibre.addGrape("Mencía");

        Wine vegaval = new Wine("Vegaval",
                "Miguel Calatayud",
                "tinto",
                "Valdepeñas",
                "http://www.vegaval.com/es/",
                "Un vino de esmerado proceso de elaboración y larga crianza. Presenta un color rojo cereza con matices a teja y una brillante capa media alta. Nariz compleja, fina y elegante. Es excelentemente estructurado, amplio y muy sabroso. Recomendado para acompañar quesos curados, estofados y todo tipo de carnes rojas y de caza. La temperatura recomendada para servir está entre los 16º C y 18º C.",
                R.drawable.vegaval,
                5);
        vegaval.addGrape("Tempranillo");

        Wine zarate = new Wine("Zárate",
                "Miguel Calatayud",
                "white",
                "Rias Bajas",
                "http://www.albarino-zarate.com",
                "El albariño Zarate es un vino blanco monovarietal que pertenece a la Denominación de Origen Rías Baixas. Considerado por la crítica especializada como uno de los grandes vinos blancos del mundo, el albariño ya es todo un mito.",
                R.drawable.zarate,
                4);
        zarate.addGrape("Albariño");

        Wine champagne = new Wine("Comtes de Champagne",
                "Champagne Taittinger",
                "other",
                "Champagne",
                "http://www.taittinger.fr",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed ac nunc purus. Curabitur eu velit mauris. Curabitur magna nisi, ullamcorper ac bibendum ac, laoreet et justo. Praesent vitae tortor quis diam luctus condimentum. Suspendisse potenti. In magna elit, interdum sit amet facilisis dictum, bibendum nec libero. Maecenas pellentesque posuere vehicula. Vivamus eget nisl urna, quis egestas sem. Vivamus at venenatis quam. Sed eu nulla a orci fringilla pulvinar ut eu diam. Morbi nibh nibh, bibendum at laoreet egestas, scelerisque et nisi. Donec ligula quam, semper nec bibendum in, semper eget dolor. In hac habitasse platea dictumst. Maecenas adipiscing semper rutrum. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae;",
                R.drawable.champagne,
                5);
        champagne.addGrape("Chardonnay");

        // Añadimos los vinos

        mWines = Arrays.asList(new Wine[]{bembibre, vegaval, zarate, champagne});
    }

    public Wine getWine(int index){
        return mWines.get(index);
    }

    public int getWineCount() {
        return mWines.size();
    }

    public List<Wine> getWineList() {
        return mWines;
    }
}
