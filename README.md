This is a repackaging of the tcpmon application, available at
https://archive.apache.org/dist/ws/tcpmon/1.0/

I am a big fan of this application, been using it on
and off for decades. Have always found it simple and 
easy to use. Perfect for my needs.

Had to reach for it again recently after a longish while.
Still worked, but the font size on my current displays
would require me to make my eyes at least 20 years 
younger.  (Which i would love to do anyway)

Luckily the source is still available at:
https://archive.apache.org/dist/ws/tcpmon/1.0/

The website for the project is:
https://ws.apache.org/tcpmon/tcpmontutorial.html

### Changes
This project is a simple repackaging of the original app,
with a few changes.  Namely:

- I have Mavenised the app.  Which meant changing the 
   directory structure to make Maven happy, and adding a
   minimal pom file.

   The project does make some reference to maven, e.g. it has a 
   "maven.xml" file, but that looks like it belongs in the 
   prehistory of Maven.

- I have added some code to the main class (TCPMon.java)
   to increase the Font size of all widgets in the GUI.

- I have added a line of code in Listener.java to 
   change the row height of the Tables to reflect the
   larger Fonts.

- I have added the ttl.FontFiddler class to... fiddle 
   with the Fonts.  It works for me, but i'm sure it can be 
   improved.

### Compiling:
To build the project:

- mvn clean package

### Running:
To run the app:
- cd to the project directory
- java -cp ./target/tcpmon-ttl-1.0.jar org.apache.ws.commons.tcpmon.TCPMon 

Note:  The original distribution has a shell 
script called "tcpmon.(sh|bat)" to start the app.  For now, 
you will have to change it manually to refer to 
the location of the jar file above.

### Legal:  
- All original licenses etc. are in the project.  
- Hope I'm legal.  Please don't sue me.

### Caveat Emptor:
"Works for me" is the operative term here.  

Hope it does for you too.