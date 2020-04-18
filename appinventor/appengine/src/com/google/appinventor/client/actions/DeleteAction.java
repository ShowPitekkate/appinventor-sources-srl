package com.google.appinventor.client.actions;

import static com.google.appinventor.client.Ode.MESSAGES;

import com.google.appinventor.client.ErrorReporter;
import com.google.appinventor.client.GalleryClient;
import com.google.appinventor.client.Ode;
import com.google.appinventor.client.boxes.ProjectListBox;
import com.google.appinventor.client.explorer.project.Project;
import com.google.appinventor.shared.rpc.project.GallerySettings;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;
import java.util.ArrayList;
import java.util.List;

public class DeleteAction implements Command {
  @Override
  public void execute() {
    Ode.getInstance().getEditorManager().saveDirtyEditors(new Command() {
      @Override
      public void execute() {
        if (Ode.getInstance().getCurrentView() == Ode.PROJECTS) {
          List<Project> selectedProjects =
              ProjectListBox.getProjectListBox().getProjectList().getSelectedProjects();
          if (selectedProjects.size() > 0) {
            // Show one confirmation window for selected projects.
            if (deleteConfirmation(selectedProjects)) {
              for (Project project : selectedProjects) {
                project.moveToTrash();
              }
            }
          } else {
            // The user can select a project to resolve the
            // error.
            ErrorReporter.reportInfo(MESSAGES.noProjectSelectedForDelete());
          }
        } else { //We are deleting a project in the designer view
          List<Project> selectedProjects = new ArrayList<Project>();
          Project currentProject = Ode.getInstance().getProjectManager().getProject(Ode.getInstance().getCurrentYoungAndroidProjectId());
          selectedProjects.add(currentProject);
          if (deleteConfirmation(selectedProjects)) {
            currentProject.moveToTrash();
            //Add the command to stop this current project from saving
          }
        }
        Ode.getInstance().switchToProjectsView();
      }
    });
  }


  private boolean deleteConfirmation(List<Project> projects) {
    String message;
    GallerySettings gallerySettings = GalleryClient.getInstance().getGallerySettings();
    if (projects.size() == 1) {
      if (projects.get(0).isPublished())
        message = MESSAGES.confirmDeleteSinglePublishedProjectWarning(projects.get(0).getProjectName());
      else
        message = MESSAGES.confirmMoveToTrashSingleProject(projects.get(0).getProjectName());
    } else {
      StringBuilder sb = new StringBuilder();
      String separator = "";
      for (Project project : projects) {
        sb.append(separator).append(project.getProjectName());
        separator = ", ";
      }
      String projectNames = sb.toString();
      if(!gallerySettings.galleryEnabled()){
        message = MESSAGES.confirmMoveToTrash(projectNames);
      } else {
        message = MESSAGES.confirmDeleteManyProjectsWithGalleryOn(projectNames);
      }
    }
    return Window.confirm(message);
  }
}
