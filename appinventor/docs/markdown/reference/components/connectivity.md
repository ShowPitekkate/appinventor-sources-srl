---
layout: documentation
title: Connectivity
---

[&laquo; Back to index](index.html)
# Connectivity

Table of Contents:

* [ActivityStarter](#ActivityStarter)
* [BluetoothClient](#BluetoothClient)
* [BluetoothServer](#BluetoothServer)
* [Web](#Web)

## ActivityStarter  {#ActivityStarter}

Implementation of a general Android Activity component.



### Properties  {#ActivityStarter-Properties}

{:.properties}

{:id="ActivityStarter.Action" .text} *Action*
: Returns the action that will be used to start the activity.

{:id="ActivityStarter.ActivityClass" .text} *ActivityClass*
: Returns the class part of the specific component that will be started.

{:id="ActivityStarter.ActivityPackage" .text} *ActivityPackage*
: Returns the package part of the specific component that will be started.

{:id="ActivityStarter.DataType" .text} *DataType*
: Returns the MIME type to pass to the activity.

{:id="ActivityStarter.DataUri" .text} *DataUri*
: Returns the data URI that will be used to start the activity.

{:id="ActivityStarter.ExtraKey" .text} *ExtraKey*
: Returns the extra key that will be passed to the activity.
 Obsolete. Should use Extras instead

{:id="ActivityStarter.ExtraValue" .text} *ExtraValue*
: Returns the extra value that will be passed to the activity.
 Obsolete. Should use Extras instead

{:id="ActivityStarter.Extras" .list .bo} *Extras*
: Specifies the list of key-value pairs that will be passed as extra data to the activity.

{:id="ActivityStarter.Result" .text .ro .bo} *Result*
: Returns the result from the activity.

{:id="ActivityStarter.ResultName" .text} *ResultName*
: Returns the name that will be used to retrieve a result from the activity.

{:id="ActivityStarter.ResultType" .text .ro .bo} *ResultType*
: Returns the MIME type from the activity.

{:id="ActivityStarter.ResultUri" .text .ro .bo} *ResultUri*
: Returns the URI from the activity.

### Events  {#ActivityStarter-Events}

{:.events}

{:id="ActivityStarter.ActivityCanceled"} ActivityCanceled()
: Event raised if this ActivityStarter returns because the activity was canceled.

{:id="ActivityStarter.AfterActivity"} AfterActivity(*result*{:.text})
: Event raised after this ActivityStarter returns.

### Methods  {#ActivityStarter-Methods}

{:.methods}

{:id="ActivityStarter.ResolveActivity" class="method returns text"} <i/> ResolveActivity()
: Returns the name of the activity that corresponds to this ActivityStarter,
 or an empty string if no corresponding activity can be found.

{:id="ActivityStarter.StartActivity" class="method"} <i/> StartActivity()
: Start the activity.

## BluetoothClient  {#BluetoothClient}

BluetoothClient component



### Properties  {#BluetoothClient-Properties}

{:.properties}

{:id="BluetoothClient.AddressesAndNames" .list .ro .bo} *AddressesAndNames*
: Returns the list of paired Bluetooth devices. Each element of the returned
 list is a String consisting of the device's address, a space, and the
 device's name.

 This method calls isDeviceClassAcceptable to determine whether to include
 a particular device in the returned list.

{:id="BluetoothClient.Available" .boolean .ro .bo} *Available*
: Returns true if Bluetooth is available on the device, false otherwise.

{:id="BluetoothClient.CharacterEncoding" .text} *CharacterEncoding*
: Sets the character encoding to use when sending and receiving text.

{:id="BluetoothClient.DelimiterByte" .number} *DelimiterByte*
: Sets the delimiter byte to use when passing a negative number for the
 numberOfBytes parameter when calling ReceiveText, ReceiveSignedBytes, or
 ReceiveUnsignedBytes.

{:id="BluetoothClient.Enabled" .boolean .ro .bo} *Enabled*
: Returns true if Bluetooth is enabled, false otherwise.

{:id="BluetoothClient.HighByteFirst" .boolean} *HighByteFirst*
: Returns true if numbers are sent and received with the most significant
 byte first.

{:id="BluetoothClient.IsConnected" .boolean .ro .bo} *IsConnected*
: Returns true if a connection to a Bluetooth device has been made.

{:id="BluetoothClient.Secure" .boolean} *Secure*
: Returns whether a secure connection should be used.

### Events  {#BluetoothClient-Events}

{:.events}

### Methods  {#BluetoothClient-Methods}

{:.methods}

{:id="BluetoothClient.BytesAvailableToReceive" class="method returns number"} <i/> BytesAvailableToReceive()
: Returns number of bytes available from the input stream.

{:id="BluetoothClient.Connect" class="method returns boolean"} <i/> Connect(*address*{:.text})
: Connect to a Bluetooth device with the given address.

{:id="BluetoothClient.ConnectWithUUID" class="method returns boolean"} <i/> ConnectWithUUID(*address*{:.text},*uuid*{:.text})
: Connect to a Bluetooth device with the given address and a specific UUID.

{:id="BluetoothClient.Disconnect" class="method"} <i/> Disconnect()
: Disconnects from the connected Bluetooth device.

{:id="BluetoothClient.IsDevicePaired" class="method returns boolean"} <i/> IsDevicePaired(*address*{:.text})
: Checks whether the Bluetooth device with the given address is paired.

{:id="BluetoothClient.ReceiveSigned1ByteNumber" class="method returns number"} <i/> ReceiveSigned1ByteNumber()
: Reads a signed 1-byte number.

{:id="BluetoothClient.ReceiveSigned2ByteNumber" class="method returns number"} <i/> ReceiveSigned2ByteNumber()
: Reads a signed 2-byte number.

{:id="BluetoothClient.ReceiveSigned4ByteNumber" class="method returns number"} <i/> ReceiveSigned4ByteNumber()
: Reads a signed 4-byte number.

{:id="BluetoothClient.ReceiveSignedBytes" class="method returns list"} <i/> ReceiveSignedBytes(*numberOfBytes*{:.number})
: Reads a number of signed bytes from the input stream and returns them as
 a List.

 If numberOfBytes is negative, this method reads until a delimiter byte
 value is read. The delimiter byte value is included in the returned list.

{:id="BluetoothClient.ReceiveText" class="method returns text"} <i/> ReceiveText(*numberOfBytes*{:.number})
: Reads a number of bytes from the input stream and converts them to text.

 If numberOfBytes is negative, read until a delimiter byte value is read.

{:id="BluetoothClient.ReceiveUnsigned1ByteNumber" class="method returns number"} <i/> ReceiveUnsigned1ByteNumber()
: Reads an unsigned 1-byte number.

{:id="BluetoothClient.ReceiveUnsigned2ByteNumber" class="method returns number"} <i/> ReceiveUnsigned2ByteNumber()
: Reads an unsigned 2-byte number.

{:id="BluetoothClient.ReceiveUnsigned4ByteNumber" class="method returns number"} <i/> ReceiveUnsigned4ByteNumber()
: Reads an unsigned 4-byte number.

{:id="BluetoothClient.ReceiveUnsignedBytes" class="method returns list"} <i/> ReceiveUnsignedBytes(*numberOfBytes*{:.number})
: Reads a number of unsigned bytes from the input stream and returns them as
 a List.

 If numberOfBytes is negative, this method reads until a delimiter byte
 value is read. The delimiter byte value is included in the returned list.

{:id="BluetoothClient.Send1ByteNumber" class="method"} <i/> Send1ByteNumber(*number*{:.text})
: Decodes the given number String to an integer and writes it as one byte
 to the output stream.

 If the number could not be decoded to an integer, or the integer would not
 fit in one byte, then the Form's ErrorOccurred event is triggered and this
 method returns without writing any bytes to the output stream.

{:id="BluetoothClient.Send2ByteNumber" class="method"} <i/> Send2ByteNumber(*number*{:.text})
: Decodes the given number String to an integer and writes it as two bytes
 to the output stream.

 If the number could not be decoded to an integer, or the integer would not
 fit in two bytes, then the Form's ErrorOccurred event is triggered and this
 method returns without writing any bytes to the output stream.

{:id="BluetoothClient.Send4ByteNumber" class="method"} <i/> Send4ByteNumber(*number*{:.text})
: Decodes the given number String to an integer and writes it as four bytes
 to the output stream.

 If the number could not be decoded to an integer, or the integer would not
 fit in four bytes, then the Form's ErrorOccurred event is triggered and this
 method returns without writing any bytes to the output stream.

{:id="BluetoothClient.SendBytes" class="method"} <i/> SendBytes(*list*{:.list})
: Takes each element from the given list, converts it to a String, decodes
 the String to an integer, and writes it as one byte to the output stream.

 If an element could not be decoded to an integer, or the integer would not
 fit in one byte, then the Form's ErrorOccurred event is triggered and this
 method returns without writing any bytes to the output stream.

{:id="BluetoothClient.SendText" class="method"} <i/> SendText(*text*{:.text})
: Converts the given text to bytes and writes them to the output stream.

## BluetoothServer  {#BluetoothServer}

BluetoothServer component



### Properties  {#BluetoothServer-Properties}

{:.properties}

{:id="BluetoothServer.Available" .boolean .ro .bo} *Available*
: Returns true if Bluetooth is available on the device, false otherwise.

{:id="BluetoothServer.CharacterEncoding" .text} *CharacterEncoding*
: Sets the character encoding to use when sending and receiving text.

{:id="BluetoothServer.DelimiterByte" .number} *DelimiterByte*
: Sets the delimiter byte to use when passing a negative number for the
 numberOfBytes parameter when calling ReceiveText, ReceiveSignedBytes, or
 ReceiveUnsignedBytes.

{:id="BluetoothServer.Enabled" .boolean .ro .bo} *Enabled*
: Returns true if Bluetooth is enabled, false otherwise.

{:id="BluetoothServer.HighByteFirst" .boolean} *HighByteFirst*
: Returns true if numbers are sent and received with the most significant
 byte first.

{:id="BluetoothServer.IsAccepting" .boolean .ro .bo} *IsAccepting*
: Returns true if this BluetoothServer component is accepting an
 incoming connection.

{:id="BluetoothServer.IsConnected" .boolean .ro .bo} *IsConnected*
: Returns true if a connection to a Bluetooth device has been made.

{:id="BluetoothServer.Secure" .boolean} *Secure*
: Returns whether a secure connection should be used.

### Events  {#BluetoothServer-Events}

{:.events}

{:id="BluetoothServer.ConnectionAccepted"} ConnectionAccepted()
: Indicates that a bluetooth connection has been accepted.

### Methods  {#BluetoothServer-Methods}

{:.methods}

{:id="BluetoothServer.AcceptConnection" class="method"} <i/> AcceptConnection(*serviceName*{:.text})
: Accept an incoming connection.

{:id="BluetoothServer.AcceptConnectionWithUUID" class="method"} <i/> AcceptConnectionWithUUID(*serviceName*{:.text},*uuid*{:.text})
: Accept an incoming connection with a specific UUID.

{:id="BluetoothServer.BytesAvailableToReceive" class="method returns number"} <i/> BytesAvailableToReceive()
: Returns number of bytes available from the input stream.

{:id="BluetoothServer.Disconnect" class="method"} <i/> Disconnect()
: Disconnects from the connected Bluetooth device.

{:id="BluetoothServer.ReceiveSigned1ByteNumber" class="method returns number"} <i/> ReceiveSigned1ByteNumber()
: Reads a signed 1-byte number.

{:id="BluetoothServer.ReceiveSigned2ByteNumber" class="method returns number"} <i/> ReceiveSigned2ByteNumber()
: Reads a signed 2-byte number.

{:id="BluetoothServer.ReceiveSigned4ByteNumber" class="method returns number"} <i/> ReceiveSigned4ByteNumber()
: Reads a signed 4-byte number.

{:id="BluetoothServer.ReceiveSignedBytes" class="method returns list"} <i/> ReceiveSignedBytes(*numberOfBytes*{:.number})
: Reads a number of signed bytes from the input stream and returns them as
 a List.

 If numberOfBytes is negative, this method reads until a delimiter byte
 value is read. The delimiter byte value is included in the returned list.

{:id="BluetoothServer.ReceiveText" class="method returns text"} <i/> ReceiveText(*numberOfBytes*{:.number})
: Reads a number of bytes from the input stream and converts them to text.

 If numberOfBytes is negative, read until a delimiter byte value is read.

{:id="BluetoothServer.ReceiveUnsigned1ByteNumber" class="method returns number"} <i/> ReceiveUnsigned1ByteNumber()
: Reads an unsigned 1-byte number.

{:id="BluetoothServer.ReceiveUnsigned2ByteNumber" class="method returns number"} <i/> ReceiveUnsigned2ByteNumber()
: Reads an unsigned 2-byte number.

{:id="BluetoothServer.ReceiveUnsigned4ByteNumber" class="method returns number"} <i/> ReceiveUnsigned4ByteNumber()
: Reads an unsigned 4-byte number.

{:id="BluetoothServer.ReceiveUnsignedBytes" class="method returns list"} <i/> ReceiveUnsignedBytes(*numberOfBytes*{:.number})
: Reads a number of unsigned bytes from the input stream and returns them as
 a List.

 If numberOfBytes is negative, this method reads until a delimiter byte
 value is read. The delimiter byte value is included in the returned list.

{:id="BluetoothServer.Send1ByteNumber" class="method"} <i/> Send1ByteNumber(*number*{:.text})
: Decodes the given number String to an integer and writes it as one byte
 to the output stream.

 If the number could not be decoded to an integer, or the integer would not
 fit in one byte, then the Form's ErrorOccurred event is triggered and this
 method returns without writing any bytes to the output stream.

{:id="BluetoothServer.Send2ByteNumber" class="method"} <i/> Send2ByteNumber(*number*{:.text})
: Decodes the given number String to an integer and writes it as two bytes
 to the output stream.

 If the number could not be decoded to an integer, or the integer would not
 fit in two bytes, then the Form's ErrorOccurred event is triggered and this
 method returns without writing any bytes to the output stream.

{:id="BluetoothServer.Send4ByteNumber" class="method"} <i/> Send4ByteNumber(*number*{:.text})
: Decodes the given number String to an integer and writes it as four bytes
 to the output stream.

 If the number could not be decoded to an integer, or the integer would not
 fit in four bytes, then the Form's ErrorOccurred event is triggered and this
 method returns without writing any bytes to the output stream.

{:id="BluetoothServer.SendBytes" class="method"} <i/> SendBytes(*list*{:.list})
: Takes each element from the given list, converts it to a String, decodes
 the String to an integer, and writes it as one byte to the output stream.

 If an element could not be decoded to an integer, or the integer would not
 fit in one byte, then the Form's ErrorOccurred event is triggered and this
 method returns without writing any bytes to the output stream.

{:id="BluetoothServer.SendText" class="method"} <i/> SendText(*text*{:.text})
: Converts the given text to bytes and writes them to the output stream.

{:id="BluetoothServer.StopAccepting" class="method"} <i/> StopAccepting()
: Stop accepting an incoming connection.

## Web  {#Web}

The Original Web component provided functions for HTTP GET and POST requests.
 This new version provides added PUT and DELETE requests.



### Properties  {#Web-Properties}

{:.properties}

{:id="Web.AllowCookies" .boolean} *AllowCookies*
: Returns whether cookies should be allowed

{:id="Web.RequestHeaders" .list .bo} *RequestHeaders*
: Returns the request headers.

{:id="Web.ResponseFileName" .text} *ResponseFileName*
: Returns the name of the file where the response should be saved.
 If SaveResponse is true and ResponseFileName is empty, then a new file
 name will be generated.

{:id="Web.SaveResponse" .boolean} *SaveResponse*
: Returns whether the response should be saved in a file.

{:id="Web.Timeout" .number} *Timeout*
: Returns the number of milliseconds that each request will wait for a response before they time out.
 If set to 0, then the request will wait for a response indefinitely.

{:id="Web.Url" .text} *Url*
: Returns the URL.

### Events  {#Web-Events}

{:.events}

{:id="Web.GotFile"} GotFile(*url*{:.text},*responseCode*{:.number},*responseType*{:.text},*fileName*{:.text})
: Event indicating that a request has finished.

{:id="Web.GotText"} GotText(*url*{:.text},*responseCode*{:.number},*responseType*{:.text},*responseContent*{:.text})
: Event indicating that a request has finished.

{:id="Web.TimedOut"} TimedOut(*url*{:.text})
: Event indicating that a request has timed out.

### Methods  {#Web-Methods}

{:.methods}

{:id="Web.BuildRequestData" class="method returns text"} <i/> BuildRequestData(*list*{:.list})
: Converts a list of two-element sublists, representing name and value pairs, to a
 string formatted as application/x-www-form-urlencoded media type, suitable to pass to
 PostText.

{:id="Web.ClearCookies" class="method"} <i/> ClearCookies()
: Clears all cookies for this Web component.

{:id="Web.Delete" class="method"} <i/> Delete()
: Performs an HTTP DELETE request using the Url property and retrieves the
 response.<br>
 If the SaveResponse property is true, the response will be saved in a file
 and the GotFile event will be triggered. The ResponseFileName property
 can be used to specify the name of the file.<br>
 If the SaveResponse property is false, the GotText event will be
 triggered.

{:id="Web.Get" class="method"} <i/> Get()
: Performs an HTTP GET request using the Url property and retrieves the
 response.<br>
 If the SaveResponse property is true, the response will be saved in a file
 and the GotFile event will be triggered. The ResponseFileName property
 can be used to specify the name of the file.<br>
 If the SaveResponse property is false, the GotText event will be
 triggered.

{:id="Web.HtmlTextDecode" class="method returns text"} <i/> HtmlTextDecode(*htmlText*{:.text})
: Decodes the given HTML text value.

 <pre>
 HTML Character Entities such as &amp;, &lt;, &gt;, &apos;, and &quot; are
 changed to &, <, >, ', and ".
 Entities such as &#xhhhh, and &#nnnn are changed to the appropriate characters.
 </pre>

{:id="Web.JsonTextDecode" class="method returns any"} <i/> JsonTextDecode(*jsonText*{:.text})
: Decodes the given JSON encoded value to produce a corresponding AppInventor value.
 A JSON list [x, y, z] decodes to a list (x y z),  A JSON object with name A and value B,
 (denoted as A:B enclosed in curly braces) decodes to a list
 ((A B)), that is, a list containing the two-element list (A B).

{:id="Web.PostFile" class="method"} <i/> PostFile(*path*{:.text})
: Performs an HTTP POST request using the Url property and data from the
 specified file, and retrieves the response.

{:id="Web.PostText" class="method"} <i/> PostText(*text*{:.text})
: Performs an HTTP POST request using the Url property and the specified text.

{:id="Web.PostTextWithEncoding" class="method"} <i/> PostTextWithEncoding(*text*{:.text},*encoding*{:.text})
: Performs an HTTP POST request using the Url property and the specified text.

{:id="Web.PutFile" class="method"} <i/> PutFile(*path*{:.text})
: Performs an HTTP PUT request using the Url property and data from the
 specified file, and retrieves the response.

{:id="Web.PutText" class="method"} <i/> PutText(*text*{:.text})
: Performs an HTTP PUT request using the Url property and the specified text.

{:id="Web.PutTextWithEncoding" class="method"} <i/> PutTextWithEncoding(*text*{:.text},*encoding*{:.text})
: Performs an HTTP PUT request using the Url property and the specified text.

{:id="Web.UriDecode" class="method returns text"} <i/> UriDecode(*text*{:.text})
: Decodes the encoded text value.

{:id="Web.UriEncode" class="method returns text"} <i/> UriEncode(*text*{:.text})
: Encodes the given text value so that it can be used in a URL.

{:id="Web.XMLTextDecode" class="method returns any"} <i/> XMLTextDecode(*XmlText*{:.text})
: Decodes the given XML string to produce a list structure. <tag>string</tag> decodes to
 a list that contains a pair of tag and string.  More generally, if obj1, obj2, ...
 are tag-delimited XML strings, then <tag>obj1 obj2 ...</tag> decodes to a list
 that contains a pair whose first element is tag and whose second element is the
 list of the decoded obj's, ordered alphabetically by tags.  Examples:
 <foo>123</foo> decodes to a one-item list containing the pair (foo, 123)
 <foo>1 2 3</foo> decodes to a one-item list containing the pair (foo,"1 2 3")
 <a><foo>1 2 3</foo><bar>456</bar></a> decodes to a list containing the pair
 (a,X) where X is a 2-item list that contains the pair (bar,123) and the pair (foo,"1 2 3").
 If the sequence of obj's mixes tag-delimited and non-tag-delimited
 items, then the non-tag-delimited items are pulled out of the sequence and wrapped
 with a "content" tag.  For example, decoding <a><bar>456</bar>many<foo>1 2 3</foo>apples</a>
 is similar to above, except that the list X is a 3-item list that contains the additional pair
 whose first item is the string "content", and whose second item is the list (many, apples).
 This method signals an error and returns the empty list if the result is not well-formed XML.
