Purpose 
------------------------
All around the world, lots of people aspire to learn new languages. Currently, 43% of the world is bilingual. By 2050, 85% of the world is predicted to be multilingual. However, current language learning techniques are largely limited to reading and writing, with little emphasis on spoken language. This often affects people’s ability to actually speak the language in real world situations, such as to communicate effectively in the workplace, to start conversations with locals while travelling and to pass on their second language to their children. Using Google’s speech API we created to a game that helps people improve their spoken language abilities. 

Game Play
------------------------
Mr Mump has been impeached from his presidency for cultural insensitivty and now has the navigate the wilderness. The only way he can avoid being attacked by people is to correctly pronounce the names of objects that appear in front of him. Everytime he sees an object in front of him, he needs to say it out loud with the correct pronounciation to jump over the obstacle. Currently, the game is only available for English words. 

Platform
------------------------
This game is a desktop game that you can set up on your PC. 

Installation Instructions
------------------------
1. After pulling this github repository, import the local repository into Eclipse. 
2. Add M2e plugin in eclipse. You can do this by going to Help > Install new software > Maven (m2e))
3. Once this is done, right click on Sidescroller project that appears under your project navigator. Select Run as > Maven Install.
4. Now, time to set up Google's Speech API! 

If you are Professor Perrault, follow this next step and then skip to step X.
5. Find the auth.json file we provided you with as a part of our submission and add it into the Sidescroller folder (you can find it in sayordie > Sidescroller)

If you are anyone other than Professor Perrault, follow steps X to X before you go to X. 
[setting up of google service account]

Go back to Eclipse and right click on the folder Sidescroller (you'll find it in your navigation window under the sayordie master folder). Right click on it and select run as > configurations. You'll see a pop up window with multiple tabs. Go to the tab called 'environment variable'. In the field called name, enter 'GOOGLE_APPLICATION_CREDENTIALS'. In value, enter 'auth.json'. 

And that's all folks! You're done setting up the game. You can now right click on Side scroller project > Run as > Java Application 

Click on the microphone on the top right and shout 'begin' at your computer to get Mr Mump moving. 


