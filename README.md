# eventsource

Simple Server Side Events (SSE) implementation using http kit.


## Usage

Build an executable jar with the following command.

    $ lein uberjar

Pipe output of some program to the service 

    $ some-program-that-dumps-into-stdout | java -jar eventsource-0.1.0-SNAPSHOT-standalone.jar

Access the event stream at http://localhost:8080/hello


