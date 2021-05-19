// -*- mode: java; c-basic-offset: 2; -*-
// Copyright 2009-2011 Google, All Rights reserved
// Copyright 2011-2012 MIT, All rights reserved
// Released under the Apache License, Version 2.0
// http://www.apache.org/licenses/LICENSE-2.0

package com.google.appinventor.shared.rpc.user;

/**
 * Provides user information.
 *
 */
public interface UserInfoProvider {
  /**
   * Returns the unique user id.
   *
   * @return the user id
   */
  String getUserId();

  /**
   * Returns the user's email address.
   *
   * @return user email address
   */
  String getUserEmail();

  /**
   * Returns the user object.
   *
   * @return user object
   */
  User getUser();

  /**
   * Returns whether the user has accepted the terms of service.
   *
   * @return {@code true} if the user has accepted the terms of service,
   *         {@code false} otherwise
   */
  boolean getUserTosAccepted();
  
  /**
   * Returns whether the user has admin privileges
   * 
   * @return {@code true} if the user has admin privileges,
   *         {@code false} otherwise
   */
  boolean getIsAdmin();

  String getSessionId();

  /**
   * Returns the user's folders
   *
   * @return user's folders as a non-null, serialized JSON list
   */
  String getUserFolders();

  void setUserFolders(String folders);

  void setReadOnly(boolean value);

  boolean isReadOnly();

  void setSessionId(String SessionId);

}
