# RGB Colour Picker
An App which changes the colour of its background based on the position of the 3 sliders.


## Screenshots

<p align="center">
  <img src="https://github.com/danielmbutler/RGBColourPicker/blob/master/resources/screenshot1.png" width="250" >
  <img src="https://github.com/danielmbutler/RGBColourPicker/blob/master/resources/screenshot2.png" width="250">
  <img src="https://github.com/danielmbutler/RGBColourPicker/blob/master/resources/screenshot3.png" width="250">
</p>


## DEMO

![alt text](https://github.com/danielmbutler/RGBColourPicker/blob/master/resources/mp4.gif)

## Design Decisions
Whilst designing this app I identified an initial issue that when the background colour changes it could make the text/sliders difficult to see, to solve this, the app first checks the values provided by the slider and then will adjust the colour of the UI elements accordingly so they will always be seen by the user. I also added the functionality for the status bar to change colour inline with the rest of the background for consistency.

## Technology used
* Kotlin
* View Binding
* ViewModel (Used to persist colour values during rotation)
* LiveData
* Constraint Layout

## Installation Instructions
The code can be downloaded from this Github repository and run on any Android emulator, I have also provided an apk file labelled "app.apk" which can be installed on any android device.
