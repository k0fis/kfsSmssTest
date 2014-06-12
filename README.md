kfsSmssTest
===========

Torzo/Test server for Walley SMS droid application

Instant Use:
------------

1) git clone git@github.com:k0fis/kfsSmssTest.git

2) cd kfsSmssTest

3) mvn jetty:run

For specific jetty port:
3) mvn jetty:run -Djetty.port=9000

Servlet run in ROOT context in this case, so enter server: http://<your_servername>:9000

User name and password are stored in src/maiin/realm.properties
Default user is novak and password is novak 
there is possible to add new user, ie:
walley=kreslo,kfsSmss

Every incoming event is just logged into jetty log. Request for send SMS is filled every thirds.

This behavior can be changed in src/main/java/kfs/kfsSmss/sms.java


Standard use:
-------------

Change method "doOutgoing(String user)" in src/main/java/kfs/kfsSmss/sms.java or delete sending 
(test) SMS.

In You server define one or more user with group kfsSmss. 

Create package (mvn package) and copy war into sever.
