# Android_APP_MA1
Course Rating App

My solution to the problem presented in https://docs.google.com/document/d/1iz3SWSJrfpHMgI2jUhShl-juGdJCGLjg_Y48HlzIVHs/edit

The major guiding point in my solution here, was how to add persistence to the application, as well as dynamic content.
In order to solve this, I used the native Android Database library, which creates a local SQLite database on the unit.
This is instantiated in my MainActivity class and is used all throughout the program.

  Testing / Enviroment:
My application has been developed and tested on 3.4 WQVG AND Nexus 4, both using API level 28 and a minimum of API level 15. 
The VQVG Emulator was running with 512 RAM and the Nexus Emulator was running with 750 RAM.

  Problems:

Requirement 6) <br>
By creating my contract and handler class outside of the application context, I was unable to avoid having hardcoded values.
This is due to the fact, that they can't refer to the ApplicationContext and thus can't reach the String.XML system resource.
Furthermore my contract class i a static class, which means it is instantiated before any context is created.
So my DatabaseHandler and DatabaseContract class contains hardcoded values, however the rest of my Activities do not.

Requirement 7)
I found it to be a challenge, to validate the rating input, given that I used responsive Ratingbars in order to show the users input.
Now the values of said ratings bars is shown in the e-mail intent that is triggered upon giving a rating. This is a form of validation in my opinion. 
However I added the responsive validation on the request to delete a given rating. This is also a form of input validation to me.

Teacher Rating System)
Instead of the ratings system that uses a single score and combines it into a single grade, I decided to use a star-rating system to judge the six criterias. This is in my opinion more intuitive than a slider or other forms of rating.

Requirement 9)
I found this requirement particularly hard to meet, as an Android app in general has high coupling to the underlying MVP pattern.
However I believe that my Contract/Handler pattern in database operations is best practice, but this can definately be improved across the App.

Requirement 11)
My new feature is a scoreboard of teachers, plus an option for sending a message email to the teacher. 
The scoreboard is based on their average value across their star rating.
