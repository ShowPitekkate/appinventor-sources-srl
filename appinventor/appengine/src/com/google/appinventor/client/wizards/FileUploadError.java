package com.google.appinventor.client.wizards;

import com.google.appinventor.shared.rpc.project.FolderNode;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;

import java.util.Collection;
import java.util.logging.Logger;

import static com.google.appinventor.client.Ode.MESSAGES;

public class  FileUploadError {
  interface FileUploadErrorUiBinder extends UiBinder<Dialog, FileUploadError> {}

  private final FileUploadErrorUiBinder UI_BINDER = GWT.create(FileUploadError.FileUploadErrorUiBinder.class);
  private final Logger LOG = Logger.getLogger(FileUploadError.class.getName());

  @UiField Dialog uploadError;
  @UiField Button okButton;
  @UiField Button cancelButton;
  @UiField Button infoButton;
  @UiField HTML errorMessage;
  FolderNode folderNode;
  Collection<String> acceptableTypes;
  FileUploadWizard.FileUploadedCallback fileUploadedCallback;


  FileUploadError (String title, String body, Error e,
                   final FolderNode p_folderNode, final Collection<String> p_acceptableTypes,
                   final FileUploadWizard.FileUploadedCallback p_fileUploadedCallback) {
    folderNode = p_folderNode;
    acceptableTypes = p_acceptableTypes;
    fileUploadedCallback = p_fileUploadedCallback;

    UI_BINDER.createAndBindUi(this);
    uploadError.setText(title);
    errorMessage.setHTML(body);

    switch(e) {
      case AIAMEDIAASSET:
        infoButton.setVisible(true);
      case NOFILESELECETED:
      case MALFORMEDFILENAME:
      case FILENAMEBADSIZE:
      default:
        break;
    }
    uploadError.center();
  }

  @UiHandler("cancelButton")
  void cancelDialog(ClickEvent e) {
    uploadError.hide();
  }

  @UiHandler("okButton")
  void okDialog(ClickEvent e) {
    uploadError.hide();
    new FileUploadWizard(folderNode, acceptableTypes, fileUploadedCallback);
  }

  @UiHandler("infoButton")
  void infoDialog(ClickEvent e) {
    Window.open(MESSAGES.aiaMediaAssetHelp(), "AIA Help", "");
  }
}

enum Error {
  AIAMEDIAASSET, NOFILESELECETED, MALFORMEDFILENAME, FILENAMEBADSIZE
}