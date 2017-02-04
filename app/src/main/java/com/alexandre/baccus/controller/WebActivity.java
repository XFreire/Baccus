package com.alexandre.baccus.controller;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
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
import com.alexandre.baccus.models.Wine;

/**
 * Created by alexandre on 3/2/17.
 */

public class WebActivity extends Activity {
    // Atributos estáticos
    private static final String STATE_URL = "url";

    // Modelo
    private Wine mWine;

    // Vistas
    private WebView mBrowser = null;
    private ProgressBar mLoading = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        // Creamos el modelo
        mWine = new Wine("Bembibre",
                "Dominio de Tares",
                "Tinto",
                "El Bierzo",
                "http://www.dominiodetares.com/portfolio/bembibre/",
                "Este vino muestra toda la complejidad y la elegancia de la variedad Mencía. En fase visual luce un color rojo picota muy cubierto con tonalidades violáceas en el menisco. En nariz aparecen recuerdos frutales muy intensos de frutas rojas (frambuesa, cereza) y una potente ciruela negra, así como tonos florales de la gama de las rosas y violetas, vegetales muy elegantes y complementarios, hojarasca verde, tabaco y maderas aromáticas (sándalo) que le brindan un toque ciertamente perfumado.",
                R.drawable.bembibre,
                5);
        mWine.addGrape("Mencía");

        // Asocio vista y controlador
        mBrowser = (WebView) findViewById(R.id.browser);
        mLoading = (ProgressBar) findViewById(R.id.loading);

        // Configuro vistas
        mBrowser.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                mLoading.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                mLoading.setVisibility(View.GONE);
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                mLoading.setVisibility(View.GONE);
            }
        });

        mBrowser.getSettings().setJavaScriptEnabled(true);
        mBrowser.getSettings().setBuiltInZoomControls(true);


        // Cargamos la página. Si no existe un estado previo, cargamos la del modelo
        if (savedInstanceState == null || !savedInstanceState.containsKey(STATE_URL)) {
            mBrowser.loadUrl(mWine.getWineCompanyWeb());
        }
        else {
            mBrowser.loadUrl(savedInstanceState.getString(STATE_URL));
        }

    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(STATE_URL, mBrowser.getUrl());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_web, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        if (item.getItemId() == R.id.menu_reload){
            mBrowser.reload();
            return true;
        }
        return false;
    }
}
