This application emulates the Storage System found in the Pokemon series of video games. 
In the game, a player has 32 "boxes" with 30 slots per box that can contain Pokemon. 
The app allows the user to scroll through the boxes to see the contents of each. 
The app will wrap back around when at the first and 32nd box. 
Boxes can have their names changed by tapping the name at the top center of the screen. 
Box name data is stored in the PC table of the StorageSystem database. 
The app stores Pokemon by allowing a user to manually enter the information for one of the creatures. 
Pokemon can be any of the 802 currently known species, can be of levels 1-100, and can have any of 25 different nautres. 
The Pokemon species are stored in the Pokedex table of the StoragesSystem database.
Every Pokemon has a value for each of their 6 stats and at least one move. Upon hitting save, the app remembers the Pokemon, which box it was stored in, and which location, just as in the games.
If the Pokemon is tapped again, the given values are populated into the Add Pokemon screen, allowing the user to edit an existing Pokemon. 
They can also hit delete to remove that Pokemon. 
Information regarding stored Pokemon, their box, and their position is stored in the Pokemon table of the StorageSystem database.