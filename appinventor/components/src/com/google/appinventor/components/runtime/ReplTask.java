// -*- mode: java; c-basic-offset: 2; -*-
// Copyright 2017 MIT, All rights reserved
// Released under the Apache License, Version 2.0
// http://www.apache.org/licenses/LICENSE-2.0

package com.google.appinventor.components.runtime;

import java.util.HashMap;

import android.os.Handler;

import android.content.Intent;
import android.util.Log;


public class ReplTask extends Task {

  public static ReplTask replTask;
  private static final String LOG_TAG = "ReplTask";
  private static final HashMap<String, ReplTaskThread> taskThreads = new HashMap<String, ReplTaskThread>();
  private boolean assetsLoaded = true;


  protected static class ReplTaskThread extends TaskThread {

    protected ReplTaskThread(String taskName, ReplTask task) {
      super(taskName, task);
    }
  }



  public ReplTask() {
    super();
    replTask = this;
  }

  @Override
  public void onCreate() {
    super.onCreate();
  }

  @Override
  public int onStartCommand(Intent intent, int flags, int startId) {
    Log.d("ReplTask", "ReplTask onStartCommand Called");
    String taskName = intent.getStringExtra(Form.SERVICE_NAME);
    String startValue = intent.getStringExtra(Form.SERVICE_ARG);
    final Object decodedStartVal = Form.decodeJSONStringForForm(startValue, "get start value");
    if (taskName == null) { // This is ReplTask itself Starting
      return START_STICKY;
    }
    Runnable runnable = new Runnable() {
      @Override
      public void run() {
        TaskStarted(decodedStartVal);
      }
    };
    runTaskCode(taskName, runnable);
    Log.d("Task", "Done Dispatch about to Return");
    return START_NOT_STICKY;
  }


  public static void runTaskCode(String taskName, Runnable runnable) {
    Log.d("ReplTask", "Got executed. Thank God");
    ReplTaskThread taskThread = taskThreads.get(taskName);
    if (taskThread == null) {
      taskThread = new ReplTaskThread(taskName, ReplTask.replTask);
      taskThreads.put(taskName, taskThread);
    }
    Handler taskHandler = taskThread.getTaskHandler();
    taskHandler.post(runnable);
  }

  @Override
  protected void triggerReceivedFromScreen(String taskName, final String title, final Object message) {
    Runnable runnable = new Runnable() {
      @Override
      public void run() {
        ReceivedFromScreen(title, message);
      }
    };
    runTaskCode(taskName, runnable);
  }


  @Override
  public void onDestroy() {
    super.onDestroy();

    stopSelf();
  }



  public boolean isAssetsLoaded() {
    return assetsLoaded;
  }


  @Override
  public String getDispatchContext() {
    Log.d(LOG_TAG, "getDispatchContext called " + Thread.currentThread().getName());
    return Thread.currentThread().getName();
  }

  @Override
  public boolean canDispatchEvent(Component component, String eventName) {
    Log.i(LOG_TAG, "canDispatch true"  );
    return true;
  }

  /**
   * Returns the Task of the Thread this function is called on.
   * @return
   */
  @Override
  public String getTaskName() {
    return Thread.currentThread().getName();
  }
}