/*
THIS CODE WAS MY OWN WORK, IT WAS WRITTEN WITHOUT CONSULTING
CODE WRITTEN BY OTHER STUDENTS OR COPIED FROM ONLINE RESOURCES.
Chris Yang
*/

/*  This class represents a Playlist of podcast episodes, where each
/*  episode is implemented as an object of type Episode. A user navigating
/*  a Playlist should be able to move between songs using Next or Previous.
/*
/*  To enable flexible navigation, the Playlist is implemented as
/*  a Circular Doubly Linked List where each episode has a link to both
/*  the next and the prev episodes in the list.
/*
/*  Additionally, the last Episode is linked to the head of the list (via next),
/*  and the head of the list is linked to that last Episode (via prev). That said,
/*  there is NO special "last" reference keeping track of the last Episode.
/*  But there is a "head" reference that should always refer to the first Episode.
*/
public class Playlist {
	private Episode head;
	private int size;

	public Playlist() {
		head = null;
		size = 0;
	}

	public boolean isEmpty() {
		return head == null;
	}

	// Ensure that "size" is updated properly in other methods, to
	// always reflect the correct number of episodes in the current playlist
	public int getSize() {
		return this.size;
	}

	// Displays the Episodes starting from the head and moving forward
	// Example code and its expected output:
	/*
	 * Playlist pl = new Playlist(); /* pl.addLast("PlanetMoney",26.0); /*
	 * pl.addLast("TodayExplained",10); /* pl.addLast("RadioLab",25.5); /*
	 * System.out.println(pl.displayPlaylistForward()); /* [BEGIN]
	 * (PlanetMoney|26.0MIN) -> (TodayExplained|10.0MIN) -> (RadioLab|25.5MIN) [END]
	 */
	public String displayPlaylistForward() {
		String output = "[BEGIN] ";
		Episode current = head;
		if (current != null) {
			while (current.next != head) {
				output += current + " -> ";
				current = current.next;
			}
			output += current + " [END]\n";
		} else {
			output += " [END]\n";
		}
		return output;
	}

	// Displays the Episodes starting from the end and moving backwards
	// Example code and its expected output:
	/*
	 * Playlist pl = new Playlist(); /* pl.addLast("PlanetMoney",26.0); /*
	 * pl.addLast("HowIBuiltThis",10); /* pl.addLast("RadioLab",25.5); /*
	 * System.out.println(pl.displayPlaylistForward()); /* [END] (RadioLab|25.5MIN)
	 * -> (HowIBuiltThis|10.0MIN) -> (PlanetMoney|26.0MIN) [BEGIN]
	 */
	public String displayPlaylistBackward() {
		String output = "[END] ";
		if (this.isEmpty()) { // check if empty playlist, return nothing
			return output += " [BEGIN]\n";
		}
		else {
			Episode last = head.prev; // make a note of where you started searching
			Episode current = last; // start at end
			
			while (current.prev != last) { // make sure that when it gets to the head doesn't run one more time
				output += current + " -> "; // add the current episode to the output
				current = current.prev; // new current
			}
			output += current + " [BEGIN]\n";

			return output;
		}
	}

	// Add a new Episode at the beginning of the Playlist
	public void addFirst(String title, double length) {
		if (this.isEmpty()) { // empty, refers to itself for next and previous
			Episode newFirst = new Episode(title, length, null, null);
			head = newFirst;
			head.next = head;
			head.prev = head;
			this.size++;
		} else { // non empty
			Episode newFirst = new Episode(title, length, head, head.prev); // new episode, next = head, prev = last thing
			head.prev.next = newFirst; // last's new next is now our new episode
			head.prev = newFirst; // make sure the old first's previous points to our new episode
			head = newFirst;
			this.size++;
		}

	}

	// Add a new Episode at the end of the Playlist
	public void addLast(String title, double length) {
		if (this.isEmpty()) { // empty, refers to itself for next and previous
			Episode newLast = new Episode(title, length, null, null);
			head = newLast;
			head.next = head;
			head.prev = head;
			this.size++;
		} else { // non empty
			Episode oldLast = head.prev; // holder for old last
			Episode newLast = new Episode(title, length, head, oldLast); // make new episode point to old last as previous and head as its next
			oldLast.next = newLast; // old last should point to new ep as next
			head.prev = newLast; // head should point to new ep as previous
			this.size++;			
		}
	}

	// Add a new Episode at the given index, assuming that index
	// zero corresponds to the first node
	public void add(String title, double length, int index) {
		if (index < 0 || index > size) { // won't add negative or larger than playlist
			if (index < 0) throw new RuntimeException("Index " + index + " is a negative number");
			if (index > size) throw new RuntimeException("Index " + index + " is larger than playlist size " + this.size);
		} else if (index == size) { // adds to the end of list
			this.addLast(title, length);
		} else if (index == 0) { // adds to beginning of list
			this.addFirst(title, length);
		} else {
			Episode current = head; // scannner
			for (int i = 0; i < index; i++) { // identifies episode at index value stored in current
				current = current.next; // current = index node
			}
			Episode newEpisode = new Episode(title, length, current, current.prev); // new episode that points to current as next, current.previous as before
			Episode beforeCurrent = current.prev; // this was previous episode before current
			beforeCurrent.next = newEpisode; // previous episode before current needs to point to our new episode as the next one
			current.prev = newEpisode; // our current episode needs to point to our new episode as its previous
			this.size++;
		}
	}

	// Delete the first Episode in the Playlist
	public Episode deleteFirst() {
		if (this.size == 0) {
			throw new RuntimeException("Cannot delete first episode from empty playlist");
		}
		else if (this.size == 1) {
			Episode output = head;
			head.next = null;
			head.prev = null;
			head = null;
			this.size--;
			return output;
		} else {
		
			Episode second = head.next;
			Episode last = head.prev;
			
			second.prev = last;
			last.next = second;
			Episode oldHead = head;
			head = second;
			
			this.size--;
			return oldHead;
		}
	}

	// Delete the last Episode in the Playlist
	// (There is no special "last" variable in this Playlist;
	// think of alternative ways to find that last Episode)
	public Episode deleteLast() {
		if (this.size == 0) {
			throw new RuntimeException("Cannot delete last episode from empty playlist");
		}
		else if (this.size == 1) { // one thing in playlist
			Episode output = head;
			head.next = null;
			head.prev = null;
			head = null;
			this.size--;
			return output; 
		} else { // normal playlist
		
			Episode newLast = head.prev.prev;
			Episode currentLast = head.prev;
			
			newLast.next = head;
			head.prev = newLast;
			this.size--;
			return currentLast; // returns what was deleted
		}
	}

	// Remove (delete) the Episode that has the given "title"
	// You can assume there will be no duplicate titles in any Playlist
	public Episode deleteEpisode(String title) {
		Episode current = head;
		int count = 0;
		
		if (head == null) { // empty
			throw new RuntimeException("Cannot delete episode from empty playlist");
		} else { // non empty
			while (current.next != null && current.getTitle() != title && count != size) { // traverse until you get to episode
				current = current.next;
				count++;
			}
			if (count == size) { // cannot find episode in playlist
				throw new RuntimeException("title: <" + title + "> not found in playlist.");
			}
			if (count == 0) { // remove first episode
				return this.deleteFirst();
			} else if (count == size-1) { // remove last episode
				return this.deleteLast();
			} else { // remove everything in between
				Episode before = current.prev;
				Episode after = current.next;
				
				before.next = after;
				after.prev = before;
				this.size--;
			}
			
		}
		
		return current;
	}

	// Remove (delete) every m-th Episode in the user's circular Playlist,
	// until only one Episode survives. Return the survived Episode.
	public Episode deleteEveryMthEpisode(int m) {
		Episode current = head;
		int count = 1;
		
		if (this.size == 0 || m <= 0 || m > size) {
			if (this.size == 0) throw new RuntimeException("Empty playlist");
			if (m <= 0) throw new RuntimeException("Cannot reach, index " + m + " too small for playlist size: " + this.size);
			if (m > size) throw new RuntimeException("Cannot reach, index " + m + " too large for playlist size: " + this.size);
		} else if (this.size == 1) { // 1 element playlist, remove first element
			this.deleteFirst();
		} else {
			while (this.size != 1) {
				
				if (count % m == 0) { // removal! if our count is divisible by our m
					Episode before = current.prev;
					Episode after = current.next;
					before.next = after;
					after.prev = before;
					size--;
				}
				current = current.next; // go through the playlist
				count++;
				
			}
			this.head = current;
		}
		return current;
	}


} // End of Playlist class
