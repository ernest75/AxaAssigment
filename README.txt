SOME CONSIDERATIONS ABOUT THE APP

First I decided to do the assigment with CursorLoader arquitechture, I was thinking to aplly and RxJava aproach but I'm Knew on that framework and I didn't felt cofident enough to do so.
Also I designed a ContentProvider, I'm aware that I could skip that part by making my own custom CursorAdapter but I've seen on documentation and specially on stack overflow that that colud be tricky so I decided to play safe.

I used only one library, Volley. The reason I chosed it, it's because of the handeling of the images by url, and it's easy to make cahe for piuctures and also makes petitions to server.
In that part I had to crate and AssyncTask to handle the server response on the onResponse of the volley petition.I know it's a bit uncomon to do that, the reason why it's that even if creating the whole petition with and AssyncTask the onResponse method executes on the UI thread so I had to make it on the method to forcing the execution of the response on another thread and leave the UI thread free for the user to use.

I also aplied the patern Mvc, and I used Singleton for the Volley class, I know in that case dosen't make much sense as there's only one petition but again I decided was safer. Also used Singleton for the model, to make sure the object was the same in all places.

With the petition I had a litle problem and was that tokk over 30 seconds to finish, the reason was cause after inserting professions and Gnomes, I was doing petitions on db to get the id's of the gnomes and of the professions and that took ages. I decided to use Hashtables to save at the same time was inserting the gnomes and the professions the pair values (id, gnome) and (id,profession). I know this approach it's no so "elegant" but it reduced the execution time of the petition from 30 second to 10 so I decided was worth it.

About testing, I must confess that I don't have many experience on it cause I never worked on a company big enough for implenting it, but I studied them.
In that case I would do the testing with a smaller version of the Json modifing some results on the professions and friends arrays so they have interactions. Something like this:

String TestJson = "Brastlewark": [{
		"id": 0,
		"name": "Tobus Quickwhistle",
		"thumbnail": "http://www.publicdomainpictures.net/pictures/10000/nahled/thinking-monkey-11282237747K8xB.jpg",
		"age": 306,
		"weight": 39.065952,
		"height": 107.75835,
		"hair_color": "Pink",
		"professions": ["Metalworker", "Woodcarver", "Tailor", "Potter"],
		"friends": ["Fizkin Voidbuster", "Malbin Chromerocket"]
	}, {
		"id": 1,
		"name": "Fizkin Voidbuster",
		"thumbnail": "http://www.publicdomainpictures.net/pictures/120000/nahled/white-hen.jpg",
		"age": 288,
		"weight": 35.279167,
		"height": 110.43628,
		"hair_color": "Green",
		"professions": ["Brewer", "Medic", "Prospector", "Tailor"],
		"friends": []
	}, {
		"id": 2,
		"name": "Malbin Chromerocket",
		"thumbnail": "http://www.publicdomainpictures.net/pictures/30000/nahled/maple-leaves-background.jpg",
		"age": 166,
		"weight": 35.88665,
		"height": 106.14395,
		"hair_color": "Red",
		"professions": ["Cook", "Medic", "Miner"],
		"friends": ["Tobus Quickwhistle"]
	}
];


then I would test the method InsertTown()
And make sure I had that outcome:

Assert(GetNumProfessions() == 9)
Assert(GetNumGnomes() == 3)
Assert(GetNumFriends() == 3)

then I will check the first result of the array 
GetDataForDetailView(0);

And make sure I had this results
Assert(Professions = 4)
Assert(Friends = 2)

About the dB:

I decided to create 4 tables. Gnomes with all the info of the gnome. Professions with all the professions name. GnomeProfessions, with the id's of the gnome and the profession. 
And GnomeFriends with the idGnome and the idFriend. And then with InnerJoins retrieve all data needed.

Ps: Also I tried to find they gender but didn't have a clue!!
	
