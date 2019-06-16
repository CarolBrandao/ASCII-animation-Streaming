# ASCII animation streaming

This project is a ASCII animation streaming. It was used Java as programming language and the communication was made through sockets.


## How to run

To run the server, first you need to generate the .class file, using `javac UDPServer.java`. After that start the server using `java UDPServer`. 

To run the client, first generate all .class files as well and start using `java UDPServer`.

## How it works

The client can see the movies available through the `CATALOG` command. 
To start a movie the `WATCH` command must be used followed by the name of the animation.
