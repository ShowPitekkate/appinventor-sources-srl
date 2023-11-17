package com.google.appinventor.client.style.GSoC;

import com.google.appinventor.client.widgets.LabeledTextBox;
import com.google.appinventor.client.wizards.Dialog;
import com.google.appinventor.client.wizards.youngandroid.NewYoungAndroidProjectWizard;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;

import java.util.logging.Logger;

public class NewYoungAndroidProjectWizardGSoC extends NewYoungAndroidProjectWizard {
  private static final Logger LOG = Logger.getLogger(NewYoungAndroidProjectWizardGSoC.class.getName());
  interface NewYoungAndroidProjectWizardUiBinderGSoC
      extends UiBinder<Dialog, NewYoungAndroidProjectWizardGSoC> {}

  @UiField protected Dialog addDialog;
  @UiField protected Button addButton;
  @UiField protected Button cancelButton;
  @UiField protected LabeledTextBox projectNameTextBox;

  @Override
  public void bindUI() {
    NewYoungAndroidProjectWizardUiBinderGSoC UI_BINDER =
        GWT.create(NewYoungAndroidProjectWizardUiBinderGSoC.class);
    UI_BINDER.createAndBindUi(this);
    super.addDialog = addDialog;
    super.addButton = addButton;
    super.cancelButton = cancelButton;
    super.projectNameTextBox = projectNameTextBox;
  }

  @UiHandler("cancelButton")
  protected void cancelAdd(ClickEvent e) {
    LOG.warning("Cancel button pressed GSoC");
    super.cancelAdd(e);
  }

  @UiHandler("addButton")
  @Override
  protected void pressAdd(ClickEvent e) {
    LOG.warning("Add button pressed GSoC");
    addProject();
  }
}
