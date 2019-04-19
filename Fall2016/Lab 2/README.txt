Hello, this is the submission for SER421 Lab2 for Tyler Cole (tjcole2) and David McDonald (dtmcdona).

The format for our JSON file is as follows:
The file outlines an array of objects that have "key" values and "response" values.
"key and "response" are string arrays. "key" contains key phrases that Eliza is looking for.
"response" contains a multitude of potential responses to the presence of the keyword.
The initial dictionary found in the dictionary folder has reserved values for the first four items 
but all others follow this format. This makes it impossible to modify the values in the first four objects
which shall limit Eliza's functionality.

Eliza's "brain" is stored in the dictionaries folder. In order to function the "dictionary.json" file 
currently inside the folder MUST BE PRESENT. Additional JSON files can be added during runtime to make 
Eliza smarter. However, only the original dictionary will be read upon starting, regardless of the presence 
of other JSON files.

The "Additional Dictionary" and "ElizaInsultsAddon" may be used for grading purposes. 
They were obtained in the Week 2 Discussion Forum from Avery Bowen and Dallin Ray respectively.

When adding JSON files to the Dictionary folder during runtime, there is a tendency to double or triple the output
"I got smarter". After some research, I discovered this is due to a bug in the way Windows calls file changes.
We elected to not modify the code to handle this exception.