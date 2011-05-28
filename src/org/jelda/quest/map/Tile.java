package org.jelda.quest.map;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;

import org.jelda.quest.actor.Actor;

public class Tile{
	public HashMap<Point, ArrayList<Actor>> actors;
	public Tile() {
		actors = new HashMap<Point, ArrayList<Actor>>();
	}
	public Tile(HashMap<Point, ArrayList<Actor>> actors) {
		this.actors = actors;
	}
	public void addActorAt(Point point, Actor actor) {
		ArrayList<Actor> actorList = actors.get(point);
		if (actorList == null) {
			actorList = new ArrayList<Actor>();
			actorList.add(actor);
			actors.put(point, actorList);
		}
		else {
			actorList.add(actor);
		}
	}
	public void addActorsAt(Point point, ArrayList<Actor> actorList) {
		ArrayList<Actor> currentActors = actors.get(point);
		if (currentActors == null) {
			actors.put(point, actorList);
		}
		else {
			currentActors.addAll(actorList);
		}
	}
	public ArrayList<Actor> getActorsAt(Point point) {
		return actors.get(point);
	}

}
