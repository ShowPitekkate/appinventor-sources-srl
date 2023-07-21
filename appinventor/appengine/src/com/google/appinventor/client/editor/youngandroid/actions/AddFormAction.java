package com.google.appinventor.client.editor.youngandroid.actions;

import com.google.appinventor.client.editor.ProjectEditor;
import com.google.appinventor.client.editor.youngandroid.DesignToolbar;
import com.google.appinventor.client.Ode;
import com.google.appinventor.client.explorer.commands.AddFormCommand;
import com.google.appinventor.client.explorer.commands.ChainableCommand;
import com.google.appinventor.client.tracking.Tracking;
import com.google.appinventor.shared.rpc.project.ProjectRootNode;
import com.google.gwt.user.client.Command;

public class AddFormAction implements Command {
  @Override
  public void execute() {
    Ode ode = Ode.getInstance();
    if (ode.screensLocked()) {
      return;                 // Don't permit this if we are locked out (saving files)
    }
    final ProjectRootNode projectRootNode = ode.getCurrentYoungAndroidProjectRootNode();
    if (projectRootNode != null) {
      Runnable doSwitch = new Runnable() {
        @Override
        public void run() {
          ChainableCommand cmd = new AddFormCommand();
          cmd.startExecuteChain(Tracking.PROJECT_ACTION_ADDFORM_YA, projectRootNode);
        }
      };
      // take a screenshot of the current blocks if we are in the blocks editor
      if (Ode.getCurrentProjectEditor().currentView == ProjectEditor.View.BLOCKS) {
        Ode.getCurrentProjectEditor().screenShotMaybe(doSwitch, false);
      } else {
        doSwitch.run();
      }
    }
  }
}
