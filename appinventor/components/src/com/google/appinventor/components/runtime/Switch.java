// -*- mode: java; c-basic-offset: 2; -*-
// Copyright 2011-2018 MIT, All rights reserved
// Released under the Apache License, Version 2.0
// http://www.apache.org/licenses/LICENSE-2.0

package com.google.appinventor.components.runtime;

import android.content.res.ColorStateList;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.widget.SwitchCompat;
import com.google.appinventor.components.annotations.*;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.common.PropertyTypeConstants;
import com.google.appinventor.components.common.YaVersion;

/**
 * Toggle switch with the ability to detect initialization, focus
 * change (mousing on or off of it), and user clicks.
 *
 */
@DesignerComponent(version = YaVersion.SWITCH_COMPONENT_VERSION,
    description = "Toggle switch that raises an event when the user clicks on it. " +
    "There are many properties affecting its appearance that can be set in " +
    "the Designer or Blocks Editor.",
    category = ComponentCategory.USERINTERFACE)
@SimpleObject
public final class Switch extends ToggleBase {

  // Backing for thumb color
  private int thumbColorActive;
  private int thumbColorInactive;

  // Backing for track color
  private int trackColorActive;
  private int trackColorInactive;

  /**
   * Creates a new Switch component.
   *
   * @param container  container, component will be placed in
   */
  public Switch(ComponentContainer container) {
    super(container);
    view = new SwitchCompat(container.$context());

    ThumbColorActive(Component.COLOR_WHITE);
    ThumbColorInactive(Component.COLOR_LTGRAY);
    TrackColorActive(Component.COLOR_GREEN);
    TrackColorInactive(Component.COLOR_GRAY);
    initToggle();
  }

  private ColorStateList createSwitchColors(int active_color, int inactive_color) {
    return new ColorStateList(new int[][]{
            new int[]{android.R.attr.state_checked},
            new int[]{}
            },
            new int[]{
                    active_color,
                    inactive_color
            });
  }

  /**
   * Returns the checkbox's thumb color as an alpha-red-green-blue
   * integer.
   *
   * @return  thumb RGB color with alpha
   */
  @SimpleProperty(category = PropertyCategory.APPEARANCE, userVisible = false)
  public int ThumbColorActive() {
    return thumbColorActive;
  }

  /**
   * Specifies the checkbox's thumb color as an alpha-red-green-blue
   * integer.
   *
   * @param argb  thumb RGB color with alpha
   */
  @DesignerProperty(editorType = PropertyTypeConstants.PROPERTY_TYPE_COLOR,
          defaultValue = Component.DEFAULT_VALUE_COLOR_WHITE)
  @SimpleProperty
  public void ThumbColorActive(int argb) {
    thumbColorActive = argb;
    DrawableCompat.setTintList(((SwitchCompat)view).getThumbDrawable(), createSwitchColors(argb, thumbColorInactive));
    view.invalidate();
  }

  @SimpleProperty(category = PropertyCategory.APPEARANCE, userVisible = true)
  public int ThumbColorInactive() {
    return thumbColorInactive;
  }

  @DesignerProperty(editorType = PropertyTypeConstants.PROPERTY_TYPE_COLOR,
          defaultValue = Component.DEFAULT_VALUE_COLOR_LTGRAY)
  @SimpleProperty
  public void ThumbColorInactive(int argb) {
    thumbColorInactive = argb;
    DrawableCompat.setTintList(((SwitchCompat)view).getThumbDrawable(), createSwitchColors(thumbColorActive, argb));
    view.invalidate();
  }

  /**
   * Returns the switch's track color as an alpha-red-green-blue
   * integer.
   *
   * @return  track RGB color with alpha
   */
  @SimpleProperty(category = PropertyCategory.APPEARANCE, userVisible = true)
  public int TrackColorActive() {
    return trackColorActive;
  }
  @SimpleProperty(category = PropertyCategory.APPEARANCE, userVisible = true)
  public int TrackColorInactive() {
    return trackColorInactive;
  }

  /**
   * Specifies the switch's track color as an alpha-red-green-blue
   * integer.
   *
   * @param argb  track RGB color with alpha
   */
  @DesignerProperty(editorType = PropertyTypeConstants.PROPERTY_TYPE_COLOR,
          defaultValue = Component.DEFAULT_VALUE_COLOR_GREEN)
  @SimpleProperty(description = "Color of the toggle track when switched on", userVisible = true)
  public void TrackColorActive(int argb) {
    trackColorActive = argb;
    DrawableCompat.setTintList(((SwitchCompat)view).getTrackDrawable(), createSwitchColors(argb, trackColorInactive));
    view.invalidate();
  }
  @DesignerProperty(editorType = PropertyTypeConstants.PROPERTY_TYPE_COLOR,
          defaultValue = Component.DEFAULT_VALUE_COLOR_DKGRAY)
  @SimpleProperty(description = "Color of the toggle track when switched off", userVisible = true)
  public void TrackColorInactive(int argb) {
    trackColorInactive = argb;
    DrawableCompat.setTintList(((SwitchCompat)view).getTrackDrawable(), createSwitchColors(trackColorActive, argb));
    view.invalidate();
  }

}
