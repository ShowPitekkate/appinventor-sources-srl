---
layout: documentation
title: Drawing and Animation
---

[&laquo; Back to index](index.html)
# Drawing and Animation

Table of Contents:

* [Ball](#Ball)
* [Canvas](#Canvas)
* [ImageSprite](#ImageSprite)

## Ball  {#Ball}

A round 'sprite' that can be placed on a `Canvas`, where it can react to touches and drags,
 interact with other sprites (`ImageSprite`s and other `Ball`s) and the edge of the `Canvas`, and
 move according to its property values.

 For example, to have a `Ball` move 4 pixels toward the top of a `Canvas` every 500 milliseconds
 (half second), you would set the Speed property to 4 [pixels], the `Interval` property to 500
 [milliseconds], the `Heading` property to 90 [degrees], and the `Enabled` property to `True`.
 These and its other properties can be changed at any time.

 The difference between a `Ball` and an `ImageSprite` is that the latter can get its appearance
 from an image file, while a `Ball`'s appearance can only be changed by varying its `PaintColor`
 and `Radius` properties.



### Properties  {#Ball-Properties}

{:.properties}

{:id="Ball.Enabled" .boolean} *Enabled*
: Controls whether the `Ball` moves when its speed is non-zero.

{:id="Ball.Heading" .number} *Heading*
: The sprite's heading in degrees above the positive x-axis. Zero degrees is toward the right of
 the screen; 90 degrees is toward the top of the screen.

{:id="Ball.Interval" .number} *Interval*
: The interval in milliseconds at which the `Ball`'s position is updated. For example, if the
 `Interval` is 50 and the `Speed` is 10, then the `Ball` will move 10 pixels every 50
 milliseconds.

{:id="Ball.OriginAtCenter" .boolean .wo .do} *OriginAtCenter*
: Whether the x- and y-coordinates should represent the center of the Ball (<code>true</code>) or its left and top edges (<code>false</code>).

{:id="Ball.PaintColor" .color} *PaintColor*
: The color of the Ball.

{:id="Ball.Radius" .number} *Radius*
: The distance from the edge of the Ball to its center.

{:id="Ball.Speed" .number} *Speed*
: The speed at which the sprite moves. The sprite moves this many pixels every interval.

{:id="Ball.Visible" .boolean} *Visible*
: The `Visible` property determines whether the %type is visible (`True`) or invisible (`False`).

{:id="Ball.X" .number} *X*
: The horizontal coordinate of the Ball, increasing as the Ball moves right. If the property OriginAtCenter is true, the coodinate is for the center of the Ball; otherwise, it is for the leftmost point of the Ball.

{:id="Ball.Y" .number} *Y*
: The vertical coordinate of the Ball, increasing as the Ball moves down. If the property OriginAtCenter is true, the coodinate is for the center of the Ball; otherwise, it is for the uppermost point of the Ball.

{:id="Ball.Z" .number} *Z*
: Sets the layer of the sprite, indicating whether it will appear in
 front of or behind other sprites.

### Events  {#Ball-Events}

{:.events}

{:id="Ball.CollidedWith"} CollidedWith(*other*{:.component})
: Event handler called when two enabled sprites (Balls or ImageSprites)
 collide. Note that checking for collisions with a rotated ImageSprite currently
 checks against its unrotated position. Therefore, collision
 checking will be inaccurate for tall narrow or short wide sprites that are
 rotated.

{:id="Ball.Dragged"} Dragged(*startX*{:.number},*startY*{:.number},*prevX*{:.number},*prevY*{:.number},*currentX*{:.number},*currentY*{:.number})
: Handler for Dragged events.  On all calls, the starting coordinates
 are where the screen was first touched, and the "current" coordinates
 describe the endpoint of the current line segment.  On the first call
 within a given drag, the "previous" coordinates are the same as the
 starting coordinates; subsequently, they are the "current" coordinates
 from the prior call. Note that the Sprite won't actually move
 anywhere in response to the Dragged event unless MoveTo is
 specifically called.

{:id="Ball.EdgeReached"} EdgeReached(*edge*{:.number})
: Event handler called when the sprite reaches an edge of the screen.
 If Bounce is then called with that edge, the sprite will appear to
 bounce off of the edge it reached.

{:id="Ball.Flung"} Flung(*x*{:.number},*y*{:.number},*speed*{:.number},*heading*{:.number},*xvel*{:.number},*yvel*{:.number})
: When a fling gesture (quick swipe) is made on the sprite: provides
 the (x,y) position of the start of the fling, relative to the upper
 left of the canvas. Also provides the speed (pixels per millisecond) and heading
 (0-360 degrees) of the fling, as well as the x velocity and y velocity
 components of the fling's vector.

{:id="Ball.NoLongerCollidingWith"} NoLongerCollidingWith(*other*{:.component})
: Handler for NoLongerCollidingWith events, called when a pair of sprites
 cease colliding.  This also registers the removal of the collision to a
 private variable [registeredCollisions](#Ball.registeredCollisions) so that
 [CollidedWith](#Ball.CollidedWith) and this event are only raised once per
 beginning and ending of a collision.

{:id="Ball.TouchDown"} TouchDown(*x*{:.number},*y*{:.number})
: When the user begins touching the sprite (places finger on sprite and
 leaves it there): provides the (x,y) position of the touch, relative
 to the upper left of the canvas

{:id="Ball.TouchUp"} TouchUp(*x*{:.number},*y*{:.number})
: When the user stops touching the sprite (lifts finger after a
 TouchDown event): provides the (x,y) position of the touch, relative
 to the upper left of the canvas.

{:id="Ball.Touched"} Touched(*x*{:.number},*y*{:.number})
: When the user touches the sprite and then immediately lifts finger: provides
 the (x,y) position of the touch, relative to the upper left of the canvas.

### Methods  {#Ball-Methods}

{:.methods}

{:id="Ball.Bounce" class="method"} <i/> Bounce(*edge*{:.number})
: Makes this sprite bounce, as if off of a wall by changing the
 [heading](#Ball.heading) (unless the sprite is not traveling toward the specified
 direction).  This also calls [MoveIntoBounds](#Ball.MoveIntoBounds) in case the
 sprite is out of bounds.

{:id="Ball.CollidingWith" class="method returns boolean"} <i/> CollidingWith(*other*{:.component})
: Indicates whether a collision has been registered between this sprite
 and the passed sprite.

{:id="Ball.MoveIntoBounds" class="method"} <i/> MoveIntoBounds()
: Moves the sprite back in bounds if part of it extends out of bounds,
 having no effect otherwise. If the sprite is too wide to fit on the
 canvas, this aligns the left side of the sprite with the left side of the
 canvas. If the sprite is too tall to fit on the canvas, this aligns the
 top side of the sprite with the top side of the canvas.

{:id="Ball.MoveTo" class="method"} <i/> MoveTo(*x*{:.number},*y*{:.number})
: Sets the x and y coordinates of the Ball. If CenterAtOrigin is true, the center of the Ball will be placed here. Otherwise, the top left edge of the Ball will be placed at the specified coordinates.

{:id="Ball.PointInDirection" class="method"} <i/> PointInDirection(*x*{:.number},*y*{:.number})
: Turns this sprite to point towards a given point.

{:id="Ball.PointTowards" class="method"} <i/> PointTowards(*target*{:.component})
: Turns this sprite to point towards a given other sprite.

## Canvas  {#Canvas}

A two-dimensional touch-sensitive rectangular panel on which drawing can
 be done and sprites can be moved.

 Conceptually, a sprite consists of the following layers, from back
 to front (with items in front being drawn on top):

 * background color
 * background image
 * the "drawing layer", populated through calls to
      [DrawPoint](#Canvas.DrawPoint), [DrawCircle](#Canvas.DrawCircle),
      [DrawText](#Canvas.DrawText), and
      [DrawTextAtAngle](#Canvas.DrawTextAtAngle), and
      [SetBackgroundPixelColor](#Canvas.SetBackgroundPixelColor)
 * the sprite layer, where sprites with higher Z values are drawn
      in front of (after) sprites with lower Z values.

 To the user, the first three layers are all the background, in terms
 of the behavior of [SetBackgroundPixelColor](#Canvas.SetBackgroundPixelColor) and
 [GetBackgroundPixelColor](#Canvas.GetBackgroundPixelColor).  For historical reasons,
 changing the background color or image clears the drawing layer.



### Properties  {#Canvas-Properties}

{:.properties}

{:id="Canvas.BackgroundColor" .color} *BackgroundColor*
: Returns the button's background color as an alpha-red-green-blue
 integer, i.e., `0xAARRGGBB`.  An alpha of `00`
 indicates fully transparent and `FF` means opaque.

{:id="Canvas.BackgroundImage" .text} *BackgroundImage*
: Returns the path of the canvas background image.

{:id="Canvas.ExtendMovesOutsideCanvas" .boolean} *ExtendMovesOutsideCanvas*
: Determines whether moves can extend beyond the canvas borders.   Default is false. This should normally be false, and the property is provided for backwards compatibility.

{:id="Canvas.FontSize" .number} *FontSize*
: The font size of text drawn on the canvas.

{:id="Canvas.Height" .number .bo} *Height*
: Set the canvas height

 The height can only be set to >0 or -1 (automatic) or -2 (fill parent) or
 to a value less then or equal to LENGTH_PERCENT_TAG (which is later
 converted to pixels.

{:id="Canvas.HeightPercent" .number .wo .bo} *HeightPercent*
: Specifies the vertical height of the Canvas as a percentage of the height of the Screen.

{:id="Canvas.LineWidth" .number} *LineWidth*
: Returns the currently specified stroke width

{:id="Canvas.PaintColor" .color} *PaintColor*
: Returns the currently specified paint color as an alpha-red-green-blue
 integer, i.e., `0xAARRGGBB`.  An alpha of `00`
 indicates fully transparent and `FF` means opaque.

{:id="Canvas.TextAlignment" .number} *TextAlignment*
: Returns the alignment of the canvas's text: center, normal
 (starting at the specified point in drawText()), or opposite
 (ending at the specified point in drawText()).

{:id="Canvas.Visible" .boolean} *Visible*
: Returns true iff the Canvas is visible.

{:id="Canvas.Width" .number .bo} *Width*
: Set the canvas width

 The width can only be set to >0 or -1 (automatic) or -2 (fill parent)
 or to a value less then or equal to LENGTH_PERCENT_TAG (which is later
 converted to pixels.

{:id="Canvas.WidthPercent" .number .wo .bo} *WidthPercent*
: Specifies the horizontal width of the Canvas as a percentage of the width of the Screen.

### Events  {#Canvas-Events}

{:.events}

{:id="Canvas.Dragged"} Dragged(*startX*{:.number},*startY*{:.number},*prevX*{:.number},*prevY*{:.number},*currentX*{:.number},*currentY*{:.number},*draggedAnySprite*{:.boolean})
: When the user does a drag from one point (prevX, prevY) to
 another (x, y).  The pair (startX, startY) indicates where the
 user first touched the screen, and "draggedAnySprite" indicates whether a
 sprite is being dragged.

{:id="Canvas.Flung"} Flung(*x*{:.number},*y*{:.number},*speed*{:.number},*heading*{:.number},*xvel*{:.number},*yvel*{:.number},*flungSprite*{:.boolean})
: When a fling gesture (quick swipe) is made on the canvas: provides
 the (x,y) position of the start of the fling, relative to the upper
 left of the canvas. Also provides the speed (pixels per millisecond) and heading
 (0-360 degrees) of the fling, as well as the x velocity and y velocity
 components of the fling's vector. The value "flungSprite" is true if a sprite
 was located near the the starting point of the fling gesture.

{:id="Canvas.TouchDown"} TouchDown(*x*{:.number},*y*{:.number})
: When the user begins touching the canvas (places finger on canvas and
 leaves it there): provides the (x,y) position of the touch, relative
 to the upper left of the canvas

{:id="Canvas.TouchUp"} TouchUp(*x*{:.number},*y*{:.number})
: When the user stops touching the canvas (lifts finger after a
 TouchDown event): provides the (x,y) position of the touch, relative
 to the upper left of the canvas

{:id="Canvas.Touched"} Touched(*x*{:.number},*y*{:.number},*touchedAnySprite*{:.boolean})
: When the user touches the canvas and then immediately lifts finger: provides
 the (x,y) position of the touch, relative to the upper left of the canvas.  TouchedAnySprite
 is true if the same touch also touched a sprite, and false otherwise.

### Methods  {#Canvas-Methods}

{:.methods}

{:id="Canvas.Clear" class="method"} <i/> Clear()
: Clears the canvas, without removing the background image, if one
 was provided.

{:id="Canvas.DrawArc" class="method"} <i/> DrawArc(*left*{:.number},*top*{:.number},*right*{:.number},*bottom*{:.number},*startAngle*{:.number},*sweepAngle*{:.number},*useCenter*{:.boolean},*fill*{:.boolean})
: Draw an arc on Canvas, by drawing an arc from a specified oval (specified by left, top, right & bottom).
 Start angle is 0 when heading to the right, and increase when rotate clockwise.
 When useCenter is true, a sector will be drawed instead of an arc.
 When fill is true, a filled arc (or sector) will be drawed instead of just an outline.

{:id="Canvas.DrawCircle" class="method"} <i/> DrawCircle(*centerX*{:.number},*centerY*{:.number},*radius*{:.number},*fill*{:.boolean})
: Draws a circle (filled in) with the given radius centered at the given coordinates on the canvas

{:id="Canvas.DrawLine" class="method"} <i/> DrawLine(*x1*{:.number},*y1*{:.number},*x2*{:.number},*y2*{:.number})
: Draws a line between the given coordinates on the canvas.

{:id="Canvas.DrawPoint" class="method"} <i/> DrawPoint(*x*{:.number},*y*{:.number})
: Draws a point at the given coordinates on the canvas.

{:id="Canvas.DrawShape" class="method"} <i/> DrawShape(*pointList*{:.list},*fill*{:.boolean})
: Draws a shape on the canvas.
 pointList should be a list contains sub-lists with two number which represents a coordinate.
 The first point and last point does not need to be the same. e.g. ((x1 y1) (x2 y2) (x3 y3))
 When fill is true, the shape will be filled.

{:id="Canvas.DrawText" class="method"} <i/> DrawText(*text*{:.text},*x*{:.number},*y*{:.number})
: Draws the specified text relative to the specified coordinates
 using the values of the [FontSize](#Canvas.FontSize) and
 [TextAlignment](#Canvas.TextAlignment) properties.

{:id="Canvas.DrawTextAtAngle" class="method"} <i/> DrawTextAtAngle(*text*{:.text},*x*{:.number},*y*{:.number},*angle*{:.number})
: Draws the specified text starting at the specified coordinates
 at the specified angle using the values of the [FontSize](#Canvas.FontSize) and
 [TextAlignment](#Canvas.TextAlignment) properties.

{:id="Canvas.GetBackgroundPixelColor" class="method returns color"} <i/> GetBackgroundPixelColor(*x*{:.number},*y*{:.number})
: Gets the color of the given pixel, ignoring sprites.

{:id="Canvas.GetPixelColor" class="method returns color"} <i/> GetPixelColor(*x*{:.number},*y*{:.number})
: Gets the color of the given pixel, including sprites.

{:id="Canvas.Save" class="method returns text"} <i/> Save()
: Saves a picture of this Canvas to the device's external storage.
 If an error occurs, the Screen's ErrorOccurred event will be called.

{:id="Canvas.SaveAs" class="method returns text"} <i/> SaveAs(*fileName*{:.text})
: Saves a picture of this Canvas to the device's external storage in the file
 named fileName. fileName must end with one of ".jpg", ".jpeg", or ".png"
 (which determines the file type: JPEG, or PNG).

{:id="Canvas.SetBackgroundPixelColor" class="method"} <i/> SetBackgroundPixelColor(*x*{:.number},*y*{:.number},*color*{:.color})
: Sets the color of the given pixel.  This has no effect if the
 coordinates are out of bounds.

## ImageSprite  {#ImageSprite}

Simple image-based Sprite.



### Properties  {#ImageSprite-Properties}

{:.properties}

{:id="ImageSprite.Enabled" .boolean} *Enabled*
: Controls whether the `ImageSprite` moves when its speed is non-zero.

{:id="ImageSprite.Heading" .number} *Heading*
: The sprite's heading in degrees above the positive x-axis. Zero degrees is toward the right of
 the screen; 90 degrees is toward the top of the screen.

{:id="ImageSprite.Height" .number .bo} *Height*
: Height property getter method.

{:id="ImageSprite.Interval" .number} *Interval*
: The interval in milliseconds at which the `ImageSprite`'s position is updated. For example, if the
 `Interval` is 50 and the `Speed` is 10, then the `ImageSprite` will move 10 pixels every 50
 milliseconds.

{:id="ImageSprite.Picture" .text} *Picture*
: Returns the path of the sprite's picture

{:id="ImageSprite.Rotates" .boolean} *Rotates*
: Rotates property getter method.

{:id="ImageSprite.Speed" .number} *Speed*
: The speed at which the sprite moves. The sprite moves this many pixels every interval.

{:id="ImageSprite.Visible" .boolean} *Visible*
: The `Visible` property determines whether the %type is visible (`True`) or invisible (`False`).

{:id="ImageSprite.Width" .number .bo} *Width*
: Width property getter method.

{:id="ImageSprite.X" .number} *X*
: The horizontal coordinate of the left edge of the ImageSprite, increasing as the ImageSprite moves right.

{:id="ImageSprite.Y" .number} *Y*
: The vertical coordinate of the top edge of the ImageSprite, increasing as the ImageSprite moves down.

{:id="ImageSprite.Z" .number} *Z*
: Sets the layer of the sprite, indicating whether it will appear in
 front of or behind other sprites.

### Events  {#ImageSprite-Events}

{:.events}

{:id="ImageSprite.CollidedWith"} CollidedWith(*other*{:.component})
: Event handler called when two enabled sprites (Balls or ImageSprites)
 collide. Note that checking for collisions with a rotated ImageSprite currently
 checks against its unrotated position. Therefore, collision
 checking will be inaccurate for tall narrow or short wide sprites that are
 rotated.

{:id="ImageSprite.Dragged"} Dragged(*startX*{:.number},*startY*{:.number},*prevX*{:.number},*prevY*{:.number},*currentX*{:.number},*currentY*{:.number})
: Handler for Dragged events.  On all calls, the starting coordinates
 are where the screen was first touched, and the "current" coordinates
 describe the endpoint of the current line segment.  On the first call
 within a given drag, the "previous" coordinates are the same as the
 starting coordinates; subsequently, they are the "current" coordinates
 from the prior call. Note that the Sprite won't actually move
 anywhere in response to the Dragged event unless MoveTo is
 specifically called.

{:id="ImageSprite.EdgeReached"} EdgeReached(*edge*{:.number})
: Event handler called when the sprite reaches an edge of the screen.
 If Bounce is then called with that edge, the sprite will appear to
 bounce off of the edge it reached.

{:id="ImageSprite.Flung"} Flung(*x*{:.number},*y*{:.number},*speed*{:.number},*heading*{:.number},*xvel*{:.number},*yvel*{:.number})
: When a fling gesture (quick swipe) is made on the sprite: provides
 the (x,y) position of the start of the fling, relative to the upper
 left of the canvas. Also provides the speed (pixels per millisecond) and heading
 (0-360 degrees) of the fling, as well as the x velocity and y velocity
 components of the fling's vector.

{:id="ImageSprite.NoLongerCollidingWith"} NoLongerCollidingWith(*other*{:.component})
: Handler for NoLongerCollidingWith events, called when a pair of sprites
 cease colliding.  This also registers the removal of the collision to a
 private variable [registeredCollisions](#ImageSprite.registeredCollisions) so that
 [CollidedWith](#ImageSprite.CollidedWith) and this event are only raised once per
 beginning and ending of a collision.

{:id="ImageSprite.TouchDown"} TouchDown(*x*{:.number},*y*{:.number})
: When the user begins touching the sprite (places finger on sprite and
 leaves it there): provides the (x,y) position of the touch, relative
 to the upper left of the canvas

{:id="ImageSprite.TouchUp"} TouchUp(*x*{:.number},*y*{:.number})
: When the user stops touching the sprite (lifts finger after a
 TouchDown event): provides the (x,y) position of the touch, relative
 to the upper left of the canvas.

{:id="ImageSprite.Touched"} Touched(*x*{:.number},*y*{:.number})
: When the user touches the sprite and then immediately lifts finger: provides
 the (x,y) position of the touch, relative to the upper left of the canvas.

### Methods  {#ImageSprite-Methods}

{:.methods}

{:id="ImageSprite.Bounce" class="method"} <i/> Bounce(*edge*{:.number})
: Makes this sprite bounce, as if off of a wall by changing the
 [heading](#ImageSprite.heading) (unless the sprite is not traveling toward the specified
 direction).  This also calls [MoveIntoBounds](#ImageSprite.MoveIntoBounds) in case the
 sprite is out of bounds.

{:id="ImageSprite.CollidingWith" class="method returns boolean"} <i/> CollidingWith(*other*{:.component})
: Indicates whether a collision has been registered between this sprite
 and the passed sprite.

{:id="ImageSprite.MoveIntoBounds" class="method"} <i/> MoveIntoBounds()
: Moves the sprite back in bounds if part of it extends out of bounds,
 having no effect otherwise. If the sprite is too wide to fit on the
 canvas, this aligns the left side of the sprite with the left side of the
 canvas. If the sprite is too tall to fit on the canvas, this aligns the
 top side of the sprite with the top side of the canvas.

{:id="ImageSprite.MoveTo" class="method"} <i/> MoveTo(*x*{:.number},*y*{:.number})
: Moves the ImageSprite so that its left top corner is at the specfied x and y coordinates.

{:id="ImageSprite.PointInDirection" class="method"} <i/> PointInDirection(*x*{:.number},*y*{:.number})
: Turns this sprite to point towards a given point.

{:id="ImageSprite.PointTowards" class="method"} <i/> PointTowards(*target*{:.component})
: Turns this sprite to point towards a given other sprite.
