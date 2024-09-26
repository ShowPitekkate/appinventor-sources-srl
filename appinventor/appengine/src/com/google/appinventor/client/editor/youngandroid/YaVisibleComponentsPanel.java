// -*- mode: java; c-basic-offset: 2; -*-
// Copyright © 2017 Massachusetts Institute of Technology, All rights reserved.

package com.google.appinventor.client.editor.youngandroid;

import com.google.appinventor.client.editor.ProjectEditor;
import com.google.appinventor.client.editor.simple.SimpleVisibleComponentsPanel;
import com.google.appinventor.client.editor.simple.components.MockForm;
import com.google.appinventor.shared.settings.SettingsConstants;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * An implementation of SimpleVisibleComponentsPanel for the MockForm designer.
 *
 * @author ewpatton@mit.edu (Evan W. Patton)
 */
public class YaVisibleComponentsPanel extends SimpleVisibleComponentsPanel<MockForm> {
  // UI elements
  interface SimpleVisibleComponentsPanelUiBinder extends UiBinder<VerticalPanel, YaVisibleComponentsPanel> {}
  @UiField protected VerticalPanel phoneScreen;
  @UiField(provided = true) protected ListBox listboxPhoneTablet; // A ListBox for Phone/Tablet/Monitor preview sizes
  @UiField(provided = true) protected ListBox listboxPhonePreview; // A ListBox for Holo/Material/iOS preview styles
  protected final int[][] drop_lst = { {320, 505}, {480, 675}, {768, 1024} };
  protected final String[] drop_lst_phone_preview = { "Android Material", "Android Holo", "iOS" };

  protected final ProjectEditor projectEditor;

  /**
   * Creates new component design panel for visible components.
   *
   * @param projectEditor
   * @param nonVisibleComponentsPanel corresponding panel for non-visible
   */
  public YaVisibleComponentsPanel(final ProjectEditor projectEditor,
      YaNonVisibleComponentsPanel nonVisibleComponentsPanel) {
    super(nonVisibleComponentsPanel);

    this.projectEditor = projectEditor;

    initializeListboxes();
    bindUI();

    listboxPhoneTablet.addChangeHandler(new ChangeHandler() {
      @Override
      public void onChange(ChangeEvent event) {
        int idx = Integer.parseInt(listboxPhoneTablet.getSelectedValue());
        int width = drop_lst[idx][0];
        int height = drop_lst[idx][1];
        String val = idx + "," + width + "," + height;
        // here, we can change settings by putting val into it
        projectEditor.changeProjectSettingsProperty(SettingsConstants.PROJECT_YOUNG_ANDROID_SETTINGS,
            SettingsConstants.YOUNG_ANDROID_SETTINGS_PHONE_TABLET, val);
        changeFormPreviewSize(idx, width, height);
      }
    });
    listboxPhonePreview.addChangeHandler(new ChangeHandler() {
      @Override
      public void onChange(ChangeEvent event) {
        int idx = Integer.parseInt(listboxPhonePreview.getSelectedValue());
        String val = drop_lst_phone_preview[idx];
        // here, we can change settings by putting chosenStyle value into it
        projectEditor.changeProjectSettingsProperty(SettingsConstants.PROJECT_YOUNG_ANDROID_SETTINGS,
            SettingsConstants.YOUNG_ANDROID_SETTINGS_PHONE_PREVIEW, val);
        changeFormPhonePreview(idx, val);
      }
    });
    initWidget(phoneScreen);
  }

  protected void bindUI() {
    SimpleVisibleComponentsPanelUiBinder uibinder = GWT.create(SimpleVisibleComponentsPanelUiBinder.class);
    uibinder.createAndBindUi(this);
  }


  protected void initializeListboxes() {
    // Initialize UI
    listboxPhoneTablet = new ListBox() {
      @Override
      protected void onLoad() {
        // onLoad is called immediately after a widget becomes attached to the browser's document.
        String sizing = projectEditor.getProjectSettingsProperty(SettingsConstants.PROJECT_YOUNG_ANDROID_SETTINGS,
            SettingsConstants.YOUNG_ANDROID_SETTINGS_SIZING);
        boolean fixed = (sizing.equals("Fixed"));
        listboxPhoneTablet.setVisible(!fixed);
        if (fixed) {
          changeFormPreviewSize(0, 320, 505);
        } else {
          getUserSettingChangeSize();
        }
      }
    };

    listboxPhonePreview = new ListBox() {
      @Override
      protected void onLoad() {
        // onLoad is called immediately after a widget becomes attached to the browser's document.
        String previewStyle = projectEditor.getProjectSettingsProperty(SettingsConstants.PROJECT_YOUNG_ANDROID_SETTINGS,
            SettingsConstants.YOUNG_ANDROID_SETTINGS_THEME);
        boolean classic = (previewStyle.equals("Classic"));
        listboxPhonePreview.setVisible(!classic);
        if (classic) {
          changeFormPhonePreview(-1, "Classic");
        } else {
          getUserSettingChangePreview();
        }
      }
    };
  }

  /**
   * Associates a Simple root component with this panel.
   *
   * @param form  backing mocked root component
   */
  @Override
  public void setRoot(MockForm form) {
    this.root = form;
    phoneScreen.add(form);
  }

  // get width and height stored in user settings, and change the preview size.
  protected void getUserSettingChangeSize() {
    String val = projectEditor.getProjectSettingsProperty(SettingsConstants.PROJECT_YOUNG_ANDROID_SETTINGS,
        SettingsConstants.YOUNG_ANDROID_SETTINGS_PHONE_TABLET);
    int idx = 0;
    int width = 320;
    int height = 505;

    if (val.equals("True")) {
      idx = 1;
      width = drop_lst[idx][0];
      height = drop_lst[idx][1];
    }

    String[] parts = val.split(",");
    if (parts.length == 3) {
      idx = Integer.parseInt(parts[0]);
      width = Integer.parseInt(parts[1]);
      height = Integer.parseInt(parts[2]);
    }
    listboxPhoneTablet.setItemSelected(idx, true);
    changeFormPreviewSize(idx, width, height);
  }

  // get Phone Preview stored in user settings, and change the preview style.
  protected void getUserSettingChangePreview() {
    String val = projectEditor.getProjectSettingsProperty(SettingsConstants.PROJECT_YOUNG_ANDROID_SETTINGS,
        SettingsConstants.YOUNG_ANDROID_SETTINGS_PHONE_PREVIEW);
    int idx = 0;

    if (val.equals("Classic")) {
      val = "Android Material";
    }

    if (val.equals("Android Holo")) {
      idx = 1;
    } else if (val.equals("iOS")) {
      idx = 2;
    }
    listboxPhonePreview.setItemSelected(idx, true);
    changeFormPhonePreview(idx, val);
  }

  protected void changeFormPreviewSize(int idx, int width, int height) {
    if (root == null) {
      return;
    }
    root.changePreviewSize(width, height, idx);
  }

  protected void changeFormPhonePreview(int idx, String chosenVal) {

    if (form == null)
      return;

    form.changePhonePreview(idx, chosenVal);
  }

  public void enableTabletPreviewCheckBox(boolean enable){
    if (root != null) {
      if (!enable){
        changeFormPreviewSize(0, 320, 505);
      } else {
        getUserSettingChangeSize();
      }
    }
    listboxPhoneTablet.setEnabled(enable);
  }

  public void enablePhonePreviewCheckBox(boolean enable){
    if (root != null) {
      if (!enable) {
        changeFormPhonePreview(-1, "Classic");
      } else {
        getUserSettingChangePreview();
      }
    }
    listboxPhonePreview.setEnabled(enable);
  }
}
