securesocial-play-2.3.x-sample
==============================

Google Oauth using Securesocial (https://github.com/jaliss/securesocial) and play 2.3.2 with scala 2.10.4

Steps to make it work:
======================

#1) Create a google app @ https://console.developers.google.com
#2) On developers console, new app -> credentials -> create client ID
#3) On developers console, new app -> Consent Screen => give a name to product and configure email address
#4) On developers console, new app -> APIs => add Contacts API and add Google+ API

#5) On your local host, /etc/hosts file add a dns name mapping to ur localhost
127.0.0.1   newapp.com

Configure the App
=================
#1) add the client secret and client id to the securesocial.conf file

Run the app
===========
To run the app,
Incase you use acticator, it should be 
acticator run (else)
play run

launch => http://newapp.com/custom/login

