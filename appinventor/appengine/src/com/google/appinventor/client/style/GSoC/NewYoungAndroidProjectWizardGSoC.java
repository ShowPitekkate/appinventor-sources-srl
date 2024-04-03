package com.google.appinventor.client.style.GSoC;

import com.google.appinventor.client.editor.youngandroid.properties.YoungAndroidThemeChoicePropertyEditor;
import com.google.appinventor.client.widgets.LabeledTextBox;
import com.google.appinventor.client.widgets.properties.SubsetJSONPropertyEditor;
import com.google.appinventor.client.wizards.Dialog;
import com.google.appinventor.client.wizards.youngandroid.NewYoungAndroidProjectWizard;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;

public class NewYoungAndroidProjectWizardGSoC extends NewYoungAndroidProjectWizard {
  interface NewYoungAndroidProjectWizardUiBinderGSoC
      extends UiBinder<Dialog, NewYoungAndroidProjectWizardGSoC> {}

  @UiField protected Dialog addDialog;
  @UiField protected Button addButton;
  @UiField protected Button cancelButton;
  @UiField protected LabeledTextBox projectNameTextBox;
  @UiField(provided = true) YoungAndroidThemeChoicePropertyEditor themeEditor;
  @UiField(provided = true) SubsetJSONPropertyEditor blockstoolkitEditor;
  @UiField protected FlowPanel horizontalThemePanel;
  @UiField protected FlowPanel horizontalBlocksPanel;

  @Override
  public void bindUI() {
    NewYoungAndroidProjectWizardUiBinderGSoC UI_BINDER =
        GWT.create(NewYoungAndroidProjectWizardUiBinderGSoC.class);
    UI_BINDER.createAndBindUi(this);
    super.addDialog = addDialog;
    super.addButton = addButton;
    super.cancelButton = cancelButton;
    super.projectNameTextBox = projectNameTextBox;
    super.horizontalThemePanel = horizontalThemePanel;
    super.horizontalBlocksPanel = horizontalBlocksPanel;
  }

  // These are here to bind the button click events. Despite the
  // annotation being on the parent class, compile is failing withput
  // the overrides.
  @Override
  protected void cancelAdd(ClickEvent e) {
    super.cancelAdd(e);
  }

  @Override
  protected void addProject(ClickEvent e) {
    super.addProject(e);
  }
}
