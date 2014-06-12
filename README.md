kfsSmssTest
===========

Torzo/Test server for Walley SMS droid application

Instant Use:
------------

<pre>
 git clone https://github.com/k0fis/kfsSmssTest.git
 cd kfsSmssTest
 mvn jetty:run
</pre>
For specific jetty port:
<pre>
 mvn jetty:run -Djetty.port=9000
</pre>
Servlet run in ROOT context in this case, so enter server: http://your_servername:9000

User name and password are stored in <i>src/main/realm.properties</i>
Default user is <b>novak</b> and password is <b>novak</b> 
there is possible to add new user, ie:
<pre>
walley=kreslo,kfsSmss
</pre>
Every incoming event is just logged into jetty log. Request for send SMS is filled every thirds.

This behavior can be changed in <i>src/main/java/kfs/kfsSmss/sms.java</i>


Standard use:
-------------

Change method "<b>doOutgoing(String user)</b>" in <i>src/main/java/kfs/kfsSmss/sms.java</i> or delete sending 
(test) SMS.

In You server define one or more user with group kfsSmss. 

Create package (mvn package) and copy war into sever.
