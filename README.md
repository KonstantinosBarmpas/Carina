# **Carina - 1st Year Project**

## 1st year project at Imperial College London department of Electrical and Electronic Engineering (2017).

---

[//]: # (Images)

[image1]: ./images/Image1.jpg "Image 1"
[image2]: ./images/Image2.png "Image 2"
[image3]: ./images/Image3.png "Image 3"
[image4]: ./images/Image4.png "Image 4"
[image5]: ./images/Image5.png "Image 5"
[image6]: ./images/Image6.png "Image 6"
[image7]: ./images/Image7.png "Image 7"

**Carina - 1st Year Project**

Watch the video:

https://www.barmpas.com/eerover-carina?wix-vod-video-id=e6b788e70de34505bf81c93bbc14aae0&wix-vod-comp-id=comp-jixe7y4l#

Goal of the project:

A​ remotely-controlled rover that can explore a distant planet and identify unusual minerals. Scientists have postulated the
properties of the minerals and the rover must detect these properties and classify the deposits that it finds.

My part was to code the intelligence unit, Arduino Uno Wifi, and build the Carina App for navigation.

Rover movements:

To control the Carina rover, capture data and process it, the intelligence unit used is an Arduino UNO Wi-Fi featuring digital and analogue input and output pins. The Wi-Fi interface can host a basic webpage in the local IP: http://192.168.0.x/arduino/web where x is 16 for the Carina rover. To access the webpage the device and the Arduino should be connected to the same network.

Carina App: (see code for more)

In the “Move” tab there are several buttons to control the rover’s motion. Firstly, the “Quick Navigation Buttons” allow easy and fast navigation of the rover since these are On-Touch buttons meaning that the rover moves as long as the user touches the buttons forward, backward, right and left. If the rover needs to move a precise distance the “Button for Customized Navigation” allows for more accuracy. The user can enter a distance for forward/backwards moves as well as degrees for rotational movement, noting that a positive value translates into a right rotation and a negative value a left rotation.
Additionally, the “Button for Voice Commands” or C-Button which enables Carina-Assistant using voice commands “forwards”, “back”, “left” or “right” to make the rover move 3 cm or 90 degrees.

Furthermore, Carina-Assistant is not only activated with the C-Button it always communicates the rover’s movement after each action entered. Lastly, the “Panic Button” stops the rover when pressed in the case of an unexpected obstacle.

![image2]

In the Detect tab, upon pressing, the “Detect” button the corresponding URL is sent and the detection process starts however pressing the “Result” button loads the main webpage hosted by the Arduino then displaying the number of the rock detected.

![image3]

Urls:

![image4]

Final movement remarks:

When testing the rover locomotion using the app, the movements were accurate except the On-Touch buttons which have a delay dependent on signal strength. This delay was calculated using a timer and the maximum time was 2 seconds.

As for the voice commands, the idea was simple “Rover pilot using voice” however it was difficult to implement. Fortunately, Google offers a way of voice recognition simply by sending voice data to their servers. The results are sent back to the phone then used to send the desired URL. The voice commands worked well as it displayed the right tabs when asked. However, the whole process needed internet connection something that the EEERover Network did not provide.

Arduino functionality: (see code for more)

Furthermore, if the user sends the stop URL both motors stop running however if the detect URL is sent, the detection function is called. The function calculates the period of the square wave it detects by sampling 200 (determined as most accurate sampling after tests) the signal and stores them into an array. During the first sample, it measures the reading time of one measurement and placing it in an array. This method ensures that the tracking of each measurement taken by indexing it.

![image5]

The code reads each measurement until 0.5V is reached and the next measurement is positive (e.g. point A): this will be the starting time. When an identical point is found (e.g. point B), the timer stops yielding an approximation of the period. It is worth noting that If more than one pair of points is detected then the average is taken. It is worth noting that measuring at 0.5V ensured that noise was not processed as it had a much lower amplitude.

![image7]

Although the code works for four out of seven rocks, it did not work for the IR pulses. The code was thus modified to eliminate noise and obtain accurate result. In other words, if the frequencies sampled were not those of the RMS then the code would measure at 2V. If the frequencies did not correspond to IR pulses, then a constant voltage of approximately 4V was measured for the ultrasonic. Based on measurements taken, a range of frequencies processed by Arduino was determined for each sensor.


Running time of code:

![image6]


The rover was tested in a specific lab area:

![image1]

---


