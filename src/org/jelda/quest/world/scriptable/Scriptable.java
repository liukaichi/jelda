package org.jelda.quest.world.scriptable;

/**
 * A scriptable object is one that has custom defined behavior. A scriptable object defines three methods:
 * -start: called when the object enters scope. If it is persistent, then this is when the quest is first loaded. If it is a regular 
 * object, then this is when the object enters the view. Returns an int representing the number of milliseconds to wait before calling update
 * -update: called while the object is in scope. It returns an int that represents the number of milliseconds that we should wait until calling update again.
 * -stop is called when the object goes out of scope. It should be used to stop timers, clear state variables, etc.
 * @author David Watson
 *
 */
public interface Scriptable {
	public int start();
	public int update();
	public void stop();
}
