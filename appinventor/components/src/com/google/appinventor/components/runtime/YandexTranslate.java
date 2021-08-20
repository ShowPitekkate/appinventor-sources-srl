// -*- mode: java; c-basic-offset: 2; -*-
// Copyright 2009-2011 Google, All Rights reserved
// Copyright 2011-2014 MIT, All rights reserved
// Released under the Apache License, Version 2.0
// http://www.apache.org/licenses/LICENSE-2.0

package com.google.appinventor.components.runtime;

import android.app.Activity;

import android.text.TextUtils;

import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesPermissions;

import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.common.PropertyTypeConstants;
import com.google.appinventor.components.common.YaVersion;

import com.google.appinventor.components.runtime.util.AsynchUtil;
import com.google.appinventor.components.runtime.util.ErrorMessages;

import java.io.IOException;
import java.io.InputStreamReader;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Use this component to translate words and sentences between different languages. This component
 * needs Internet access, as it will request translations to the Yandex.Translate service.
 * Specify the source and target language in the form source-target using two letter language codes.
 * So "en-es" will translate from English to Spanish while "es-ru" will translate from Spanish to
 * Russian. If you leave out the source language, the service will attempt to detect the source
 * language. So providing just "es" will attempt to detect the source language and translate it
 * to Spanish.
 *
 * This component is powered by the Yandex translation service. See
 * http://api.yandex.com/translate/ for more information, including the list of available languages
 * and the meanings of the language codes and status codes.
 *
 * **Note:** Translation happens asynchronously in the background. When the translation is complete,
 * the {@link #GotTranslation(String, String)} event is triggered.
 */
@DesignerComponent(version = YaVersion.YANDEX_COMPONENT_VERSION,
    description = "Use this component to translate words and sentences between different " +
        "languages. This component needs Internet access, as it will request " +
        "translations to the Yandex.Translate service. Specify the source and " +
        "target language in the form source-target using two letter language " +
        "codes. So\"en-es\" will translate from English to Spanish while " +
        "\"es-ru\" will translate from Spanish to Russian. If you leave " +
        "out the source language, the service will attempt to detect the source " +
        "language. So providing just \"es\" will attempt to detect the source " +
        "language and translate it to Spanish.<p /> This component is powered by the " +
        "Yandex translation service.  See http://api.yandex.com/translate/ " +
        "for more information, including the list of available languages and the " +
        "meanings of the language codes and status codes. " +
        "<p />Note: Translation happens asynchronously in the background. When the " +
        "translation is complete, the \"GotTranslation\" event is triggered.",
    category = ComponentCategory.MEDIA,
    nonVisible = true,
    iconName = "images/yandex.png")
@UsesPermissions(permissionNames = "android.permission.INTERNET")
@SimpleObject
public final class YandexTranslate extends AndroidNonvisibleComponent {

  public static final String YANDEX_TRANSLATE_SERVICE_URL =
      "https://translate.yandex.net/api/v1.5/tr.json/translate?key=";
  private final String yandexKey;
  private String userYandexKey = "DEFAULT";
  private final Activity activity;
  private final byte [] key1 = { -127, -88, 79, 80, 65, 112, -80, 87, -62, 126,
                                 -125, -25, -31, 55, 107, -42, -63, -62, 33, -122,
                                 1, 89, -33, 23, -19, 18, -81, 37, -67, 114, 92, -60,
                                 -76, -50, -59, -49, -114, -64, -96, -75, 117, -116, 53,
                                 -8, 44, 111, 120, 48, 41, 30, 85, -116, -31, 17,
                                 87, -89, -49, -51, 47, 92, 121, -58, -80, -25, 86,
                                 123, -36, -9, 101, -112, -22, -28, -29, -14, -125,
                                 46, -103, -36, 125, 114, 35, -31, 1, 123 };
  private final byte [] key2 = { -11, -38, 33, 35, 45, 94, -127, 121, -13, 80, -79,
                                 -41, -48, 3, 91, -29, -15, -9, 117, -74, 49, 105,
                                 -26, 34, -35, 72, -127, 64, -116, 69, 111, -12, -48,
                                 -81, -11, -83, -69, -12, -108, -42, 65, -72, 86,
                                 -42, 27, 12, 26, 2, 28, 122, 51, -24, -45, 36, 54,
                                 -106, -87, -3, 27, 62, 65, -16, -126, -42, 99, 77,
                                 -70, -49, 83, -12, -114, -35, -44, -109, -77, 28,
                                 -84, -66, 72, 22, 18, -126, 50, 78 };

  /**
   * Creates a new component.
   *
   * @param container  container, component will be placed in
   */
  public YandexTranslate(ComponentContainer container) {
    super(container.$form());

    // Set up the Yandex.Translate Tagline in the 'About' screen
    form.setYandexTranslateTagline();

    // TODO (user) To provide users with this component you will need to obtain a key with the
    // Yandex.Translate service at http://api.yandex.com/translate/
    yandexKey = gk(); /* "" in master branch */
    activity = container.$context();
  }

  /**
   * By providing a target language to translate to (for instance, 'es' for Spanish, 'en' for
   * English, or 'ru' for Russian), and a word or sentence to translate, this method will request
   * a translation to the Yandex.Translate service. Once the text is translated by the external
   * service, the event {@link #GotTranslation(String, String)} will be executed.
   *
   *   **Note:** Yandex.Translate will attempt to detect the source language. You can also specify
   * prepending it to the language translation, e.g., es-ru will specify Spanish to Russian
   * translation.
   */
  @SimpleFunction(description = "By providing a target language to translate to (for instance, " +
      "'es' for Spanish, 'en' for English, or 'ru' for Russian), and a word or sentence to " +
      "translate, this method will request a translation to the Yandex.Translate service.\n" +
      "Once the text is translated by the external service, the event GotTranslation will be " +
      "executed.\nNote: Yandex.Translate will attempt to detect the source language. You can " +
      "also specify prepending it to the language translation. I.e., es-ru will specify Spanish " +
      "to Russian translation.")
  public void RequestTranslation(final String languageToTranslateTo,
                                 final String textToTranslate) {

    if (TextUtils.isEmpty(yandexKey) &&
      (TextUtils.isEmpty(userYandexKey) || TextUtils.equals(userYandexKey, "DEFAULT"))) {
      form.dispatchErrorOccurredEvent(YandexTranslate.this, "RequestTranslation",
          ErrorMessages.ERROR_TRANSLATE_NO_KEY_FOUND);
      return;
    }

    AsynchUtil.runAsynchronously(new Runnable() {
      @Override
      public void run() {
        try {
          performRequest(languageToTranslateTo, textToTranslate);
        } catch (IOException e) {
          form.dispatchErrorOccurredEvent(YandexTranslate.this, "RequestTranslation",
              ErrorMessages.ERROR_TRANSLATE_SERVICE_NOT_AVAILABLE);
        } catch (JSONException je) {
          form.dispatchErrorOccurredEvent(YandexTranslate.this, "RequestTranslation",
              ErrorMessages.ERROR_TRANSLATE_JSON_RESPONSE);
        }
      }
    });
  }

  /**
   * This is the actual request to the Yandex.Translate service. It opens a https connection to
   * their web based API and parses the JSON response.
   * @param languageToTranslateTo the language that the text will be translated into.
   * @param textToTranslate the word or sentence to translate.
   * @throws IOException if the connection is not successful.
   * @throws JSONException if the JSON response cannot be parsed.
   */
  private void performRequest(String languageToTranslateTo, String textToTranslate)
      throws IOException, JSONException {

    final String finalURL = YANDEX_TRANSLATE_SERVICE_URL +
      ((TextUtils.equals(this.userYandexKey, "DEFAULT") ||
        TextUtils.isEmpty(this.userYandexKey))?this.yandexKey:this.userYandexKey) +
        "&lang=" + languageToTranslateTo +
        "&text=" + URLEncoder.encode(textToTranslate, "UTF-8");

    URL url = new URL(finalURL);
    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
    if (connection != null) {
      try {
        final String responseContent = getResponseContent(connection);

        JSONObject jsonResponse = new JSONObject(responseContent);

        final String responseCode = jsonResponse.getString("code");

        // The translation will be in position Zero of the array returned with key 'text'
        org.json.JSONArray response = jsonResponse.getJSONArray("text");
        final String translation = (String)response.get(0);

        // Dispatch the event.
        activity.runOnUiThread(new Runnable() {
          @Override
          public void run() {
            GotTranslation(responseCode, translation);
          }
        });

      } finally {
        connection.disconnect();
      }
    }
  }

  /**
   * This method reads from a stream based on the passed connection
   * @param connection the connection to read from
   * @return the contents of the stream
   * @throws IOException if it cannot read from the http connection
   */
  private static String getResponseContent(HttpURLConnection connection) throws IOException {
    // Use the content encoding to convert bytes to characters.
    String encoding = connection.getContentEncoding();
    if (encoding == null) {
      encoding = "UTF-8";
    }
    InputStreamReader reader = new InputStreamReader(connection.getInputStream(), encoding);
    try {
      int contentLength = connection.getContentLength();
      StringBuilder sb = (contentLength != -1)
          ? new StringBuilder(contentLength)
          : new StringBuilder();
      char[] buf = new char[1024];
      int read;
      while ((read = reader.read(buf)) != -1) {
        sb.append(buf, 0, read);
      }
      return sb.toString();
    } finally {
      reader.close();
    }
  }

  /**
   * Event indicating that a request has finished and has returned data (translation).
   *
   * @param responseCode the response code from the server
   * @param translation the response content from the server
   */
  @SimpleEvent(description = "Event triggered when the Yandex.Translate service returns the " +
      "translated text. This event also provides a response code for error handling. If the " +
      "responseCode is not 200, then something went wrong with the call, and the translation will " +
      "not be available.")
  public void GotTranslation(String responseCode, String translation) {
    EventDispatcher.dispatchEvent(this, "GotTranslation", responseCode, translation);
  }

  /**
   * The Yandex API Key to use. If set to DEFAULT the platform default key (if any)
   * will be used. Otherwise should be set to a valid API key which can be obtained
   * from https://tech.yandex.com/translate/. If the platform doesn't have a default
   * key and one isn't provided here, an error will be raised.
   */

  @DesignerProperty(editorType = PropertyTypeConstants.PROPERTY_TYPE_STRING,
      defaultValue = "DEFAULT")
  @SimpleProperty(description = "Set the API Key to use with Yandex. " +
      "You do not need to set this if you are using the MIT system because " +
      "MIT has its own key builtin. If set, the key provided here will be " +
      "used instead")
  public void ApiKey(String apiKey) {
    this.userYandexKey = apiKey;
  }

  // This routine exists only in the branding to build the yandex key from
  // the byte arrays "key1" and "key2" above. This is done by xor'ing the
  // byte arrays together and turning the result into a string
  private String gk() {
    byte retval [] = new byte[key1.length];
    for (int i = 0; i < key1.length; i++) {
      retval[i] = (byte) (key1[i] ^ key2[i]);
    }
    return new String(retval);
  }

}
