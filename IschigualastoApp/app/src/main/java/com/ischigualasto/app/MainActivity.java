package com.ischigualasto.app;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.fragment.app.Fragment;

public class MainActivity extends AppCompatActivity {
    private WebView webView;
    private BottomNavigationView bottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = findViewById(R.id.webview);
        bottomNav = findViewById(R.id.bottom_navigation);

        // Configurar WebView
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("https://www.ischigualasto.gob.ar/");

        // Configurar navegación inferior
        bottomNav.setOnItemSelectedListener(item -> {
            Fragment selectedFragment = null;
            
            if (item.getItemId() == R.id.nav_web) {
                webView.setVisibility(View.VISIBLE);
                return true;
            } else {
                webView.setVisibility(View.GONE);
                
                if (item.getItemId() == R.id.nav_map) {
                    selectedFragment = new MapFragment();
                } else if (item.getItemId() == R.id.nav_gallery) {
                    selectedFragment = new GalleryFragment();
                } else if (item.getItemId() == R.id.nav_info) {
                    selectedFragment = new InfoFragment();
                }

                if (selectedFragment != null) {
                    getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, selectedFragment)
                        .commit();
                    return true;
                }
            }
            return false;
        });
    }
}
