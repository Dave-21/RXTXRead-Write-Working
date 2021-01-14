"# RXTXRead-Write-Working" 
#   R X T X R e a d - W r i t e - W o r k i n g 
# You need Java EE 15 or SE 15 86x(32 bit). You'll need to get the distribution from here:
# https://adoptopenjdk.net/releases.html?variant=openjdk15&jvmVariant=hotspot
# Select OpenJDK 15 (Latest), Your operation System, Architecture: x86
# for RXTX, you'll also need 32 bit. Go to http://fizzed.com/oss/rxtx-for-java and install
# x86 version. If you can't find your operating system, simply google your operating system
# and RXTX x86 (32 bit).
# Since Java SE 64 bit has some issues, it doesn't currently work with RXTX 64 bit. Hopefully
# by the time you're at this page, they have fixed their issues though.
#
#
# To setup RXTX, simply go into the root of the directory you're working in, make a folder
# called lib, extract the RXTX .zip into the root of your the directory you're working in,
# copy ONLY the .jar file into the lib folder. Leave the other RXTX files in the directory.
#
#
# For InteliJ, to add the file to your library, Just right-click your root folder, click
# Open Module Settings, tap the plus(+) button, JARS or Directories, go into the lib folder
# that you put the .jar file into, tap the .jar file, and tap Ok. then just make sure it's
# checked, and you've just installed RXTX.
#
#
# To use the x86(32 bit) Java distribution in InteliJ, tap the run tab, tap Edit configurations
# make a configuration for the file you want to run (you'll need to make to do this when
# running any future Java files with RXTX. Other wise it defaults to Java SE 15 64 bit), then
# where is says JRE: tap the folder icon to the right, and go into the Java EE x86(32 bit)
# version we downloaded earlier. It should be somewhere like
# C:\Program Files (x86)\AdoptOpenJDK\jdk-15.0.1.9-hotspot
#
#
# Ok. It's all setup