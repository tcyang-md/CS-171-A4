# Playlist Application :headphones: 
I created a playlist application in **Java** that supports adding, deleting, and navigating through different podcast episodes. In order to make navigating through the playlist flexible, it made the most sense to use a **circular doubly linked list.** Through this assignment I was able to deepen my understanding of linked lists and apply the knowledge that I had learned in class.

## Classes
- Episode: Essentially a node where it contains `title`, `length`, `next` episode, and `prev` episode.
- Playlist: This is the circular doubly linked list that contains a list of Episode objects. The playlist will always keep track of the `head` or the first episode in the list `size` or the total number of episodes
- ITunes: This represents the application where you can test the functionality of the playlist and look at the episode.

## Usage
### Instructions
Download all files, ITunes.java is where you add episodes, create the playlist, and use all the functions listed below.
### Available Functions
```java
public class Playlist {
  public String displayPlaylistForward()
  public String displayPlaylistBackward()
  public void addFirst(String title, double length)
  public void add(String title, double length, int index)
  public Episode deleteFirst()
  public Episode deleteLast()
  public Episode deleteEpisode(String title)
  
  // Remove (delete) every m-th Episode in the user's circular Playlist,
	// until only one Episode survives. Return the survived Episode.
  public Episode deleteEveryMthEpisode(int m)
}
```

## Demo
``` Java
public class ITunes {
    // create playlist object
		Playlist pl = new Playlist();
		
    // populate playlist
		pl.addLast("PlanetMoney",26.0);
		pl.addLast("HowIBuiltThis",10);
		pl.addLast("EzraKleinShow",65.0);
		pl.addLast("RadioLab",25.5);
		pl.addLast("MakeMeSmart",24.5);
		pl.addLast("Worldly",55);

    // functions
		System.out.println("Forward: " + pl.displayPlaylistForward());
    // remove every nth episode in a loop until one episode remains
		System.out.println("Last Episode Remaining:" + pl.deleteEveryMthEpisode(3));
}
```
```
Forward: [BEGIN] (3|3.0MIN) -> (2|3.0MIN) -> (1|3.0MIN) -> (0|3.0MIN) [END]

Last Episode Remaining:[BEGIN] (3|3.0MIN) [END]
```
