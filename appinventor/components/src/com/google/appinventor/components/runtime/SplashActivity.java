// -*- mode: java; c-basic-offset: 2; -*-
// Copyright 2018 MIT, All rights reserved
// Released under the Apache License, Version 2.0
// http://www.apache.org/licenses/LICENSE-2.0

package com.google.appinventor.components.runtime;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;

import gnu.expr.Language;
import kawa.standard.Scheme;

import java.util.Random;

import android.net.Uri;

import android.app.Activity;

import android.os.Bundle;
import android.os.Handler;

import android.support.v4.content.ContextCompat;

import android.util.Log;

import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebStorage;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Create a An activity that display's the Repl's startup splash screen
 * which will introduce the REPL and ask for necessary permissions.
 *
 */
public final class SplashActivity extends AppInventorCompatActivity {

  WebView webview;
  Handler handler;
  Language scheme;

  public class JavaInterface {
    Context mContext;

    public JavaInterface(Context context) {
      mContext = context;
    }

    // This is for hacking around. Not sure what we will do with it
    // but you never know :-)
    @JavascriptInterface
    public String doScheme(String sexp) {
      try {
        return (String) scheme.eval(sexp);
      } catch (Throwable e) {
        return (e.toString());
      }
    }

    @JavascriptInterface
    public boolean hasPermission(String permission) {
      if (ContextCompat.checkSelfPermission(mContext, permission) ==
        PackageManager.PERMISSION_GRANTED) {
        return true;
      } else {
        return false;
      }
    }

    @JavascriptInterface
    public void askPermission(String permission) {
      ActivityCompat.requestPermissions((Activity) SplashActivity.this,
        new String[] { permission}, 1);
    }

    @JavascriptInterface
    public void finished() {
      SplashActivity.this.handler.post(new Runnable() {
          @Override
          public void run() {
            SplashActivity.this.webview.destroy();
            SplashActivity.this.finish();
          }
        });
    }
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    // Scheme hacking!
    scheme = Scheme.getInstance("scheme");
    gnu.expr.ModuleExp.mustNeverCompile();


    JavaInterface android = new JavaInterface(this);
    handler = new Handler();
    webview = new WebView(this);
    WebSettings webSettings = webview.getSettings();
    webSettings.setJavaScriptEnabled(true);
    webSettings.setDatabaseEnabled(true);
    webSettings.setDomStorageEnabled(true);
    String databasePath = this.getApplicationContext().getDir("database", Context.MODE_PRIVATE).getPath();
    webSettings.setDatabasePath(databasePath);

    // webview.setWebViewClient(new WebViewClient() {
    //     @Override
    //     public boolean shouldOverrideUrlLoading(WebView view, String url) {
    //       Log.i("WebView", "Handling url " + url);
    //       Uri uri = Uri.parse(url);
    //       String scheme = uri.getScheme();
    //       if (scheme.equals(Form.APPINVENTOR_URL_SCHEME)) {
    //         Intent resultIntent = new Intent();
    //         resultIntent.setData(uri);
    //         setResult(RESULT_OK, resultIntent);
    //         finish();
    //       } else {
    //         view.loadUrl(url);
    //       }
    //       return true;
    //     }
    //   });

    webview.setWebChromeClient(new WebChromeClient() {
        @Override
        public void onExceededDatabaseQuota(String url, String databaseIdentifier,
          long currentQuota, long estimatedSize, long totalUsedQuota,
          WebStorage.QuotaUpdater quotaUpdater) {
          quotaUpdater.updateQuota(5 * 1024 * 1024);
        }
      });
    setContentView(webview);
    webview.setWebContentsDebuggingEnabled(true);
    webview.loadUrl("file:///android_asset/splash.html");
    webview.addJavascriptInterface(android, "Android");
  }

  @Override
  public void onRequestPermissionsResult(int code,
    String permissions[], int[] grantResults) {
    for (int i = 0; i < permissions.length; i++ ) {
      String permission = permissions[i];
      int grantResult = grantResults[i];
      boolean granted = false;
      if (grantResult == PackageManager.PERMISSION_GRANTED) {
        granted = true;
      }
      webview.loadUrl("javascript:permresult('" + permission + "'," + granted + ")");
    }
  }

}
