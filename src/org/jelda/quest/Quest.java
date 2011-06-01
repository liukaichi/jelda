package org.jelda.quest;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import org.jelda.quest.world.World;

public class Quest implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -976023079823603620L;
	public static World world;

	public Quest() {
		this(new World());
	}

	public Quest(World world) {
		Quest.world = world;
	}

	public void writeOut(String filename) {
		FileOutputStream fos = null;
		ObjectOutputStream out = null;
		try {
			fos = new FileOutputStream(filename);
			out = new ObjectOutputStream(fos);
			out.writeObject(this);
			out.close();
		} 
		catch (IOException e) {}
	}
}
