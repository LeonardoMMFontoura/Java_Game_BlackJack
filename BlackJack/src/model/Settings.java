//Ian Geraldi
//Kevin Abreu
//Leonardo Monteiro


package model;

/**
 * Just setting some basic variables , that way things are less hard-coded.
 * More things will likely be added here in the future like the Path for creating saves, window resolution etc.
 * 
 */
public abstract class Settings 
{
	public static int howManyDecks = 1;
	public static float deckPenetration = 0.1f;
	public static int maxPlayers = 4;
	public static int[] deckStartingPosition = {286,106};
	public static Vector2[] BancaHandPosition = {new Vector2(445,240),new Vector2 (525,240),new Vector2 (605,240),new Vector2 (685,240), new Vector2 (765,240),new Vector2 (845,240)};
	public static Vector2[] PlayerHandPosition = {new Vector2(410,385),new Vector2 (460,385),new Vector2 (510,385),new Vector2 (560,385),new Vector2 (660,385),
			new Vector2 (710,385),new Vector2 (760,385),new Vector2 (810,385),new Vector2 (860,385),new Vector2 (910,385),new Vector2 (960,385)}; 
	public static int startingChips = 500;
}