package tp1.logic;

import tp1.logic.gameobjects.ExitDoor;
import tp1.logic.gameobjects.GameObject;
import tp1.logic.gameobjects.Goomba;
import tp1.logic.gameobjects.Land;
import tp1.logic.gameobjects.Mario;

public  interface GameItem {
	public boolean isSolid();
	public boolean isAlive();
	public boolean isInPosition(Position pos);
	//TODO para private pos (me obliga a quitar protected del metodo)
	public boolean compartePosition(GameObject other);

	public boolean interactWith(GameItem item);

	public boolean receiveInteraction(Land obj);
	public boolean receiveInteraction(ExitDoor obj);
	public boolean receiveInteraction(Mario obj);
	public boolean receiveInteraction(Goomba obj);
}


