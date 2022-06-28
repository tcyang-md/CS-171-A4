public class ITunes{

	public static void main(String[] args){

		// Sample testing code ...
		Playlist pl = new Playlist();

//		pl.addLast("PlanetMoney",26.0);
//		pl.addLast("HowIBuiltThis",10);
//		pl.addLast("EzraKleinShow",65.0);
//		pl.addLast("RadioLab",25.5);
//		pl.addLast("MakeMeSmart",24.5);
//		pl.addLast("Worldly",55);
//		pl.addLast("Explained",23.0);
//		pl.addLast("Invisibilia",33.5);
//		
		pl.add("3", 3, 0);
		pl.add("2", 3, 1);
		pl.add("1", 3, 2);
		pl.add("0", 3, 3);
		
//		System.out.println(pl.displayPlaylistForward());
		System.out.println("Forward: " + pl.displayPlaylistForward());
		System.out.println(pl.deleteEveryMthEpisode(5));
		System.out.println(pl.displayPlaylistForward());
//		System.out.println(pl.getSize());
//		System.out.println("Backward: " + pl.displayPlaylistBackward());
		//pl.deleteEveryMthEpisode(3);

		//System.out.println(pl.displayPlaylistForward());
		//System.out.println(pl.displayPlaylistBackward());

	}
}
