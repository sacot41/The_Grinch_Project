# The Grinch Project
Weekend project - The Grinch's remote to help him steal Christmas.

I couldn't have a simple christmas tree in my living room; I had to add some electronic stuff to make it more cool. But, I have a girlfriend, so, I can't add RJ-45 and ram stick like a ornament because she said no. Like the grinch, I was angry about this, so, I build a app that can steal christmas.
It's just an android app that connect to a Hc-06 bluetooth chip to open and close christmas light.

## Description
![]({{site.baseurl}}//screenshot_1.png)
![]({{site.baseurl}}//screenshot_off.png)
![]({{site.baseurl}}//screenshot_on.png)
## Improvement

- I use a 9V phone alimentation connected with a LM7505 to make my alimention, but the LM7505 really does not like it when the relay is on. Maybe I can find a direct 5V alimention or a AC/DC mount power (http://www.digikey.ca/product-detail/en/VSK-S1-5U/102-2595-ND/3465373) or a cheap Iphone recharger. 
- My friends (who really know nothing about electronic and programmation) ask me if I can steal all christmas tree light with this app, like the good old time when TV-B-Gone was use to close all tv. I don't know how, but I definitely work on that for the next christmas.

## Thanks

- SerialCommand : "An Arduino library to tokenize and parse commands received over a serial port."
https://github.com/scogswell/ArduinoSerialCommand
- A bluetooth server class for Android made by Coconauts
https://github.com/coconauts/Arduino-Android-Bluetooth/blob/master/android/app/src/main/java/quadcopter/coconauts/net/quadcopter/Bluetooth.java






