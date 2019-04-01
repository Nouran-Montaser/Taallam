package com.example.nouran.taallam.UI.Main.Category_Fragment.Books;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.example.nouran.taallam.R;
import com.github.barteksc.pdfviewer.PDFView;

import java.util.Objects;

public class ReadBookActivity extends AppCompatActivity {

    private PDFView pdfView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_book);

        String bookUrl = getIntent().getStringExtra("BOOK_URL");
        String bookName = getIntent().getStringExtra("BOOK_Name");
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowCustomEnabled(true); // enable overriding the default toolbar layout
        getSupportActionBar().setTitle(bookName);

//        pdfView = findViewById(R.id.pdfView);
//        pdfView.fromAsset("msms.pdf").load();
//        pdfView.fromUri(Uri.parse(bookUrl)).load();

        WebView webView = (WebView) findViewById(R.id.webView1);
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setAllowFileAccessFromFileURLs(true);
        settings.setAllowUniversalAccessFromFileURLs(true);
        settings.setBuiltInZoomControls(true);
        webView.setWebChromeClient(new WebChromeClient());
        webView.loadUrl("http://drive.google.com/viewerng/viewer?embedded=true&url="+bookUrl);

    }
}
