package org.jelda.quest.world;

import java.awt.Point;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

import org.jelda.quest.actor.Actor;

public class Tile{
	public static final Comparator<Actor> actorHeightComparator = new Comparator<Actor>() {

		@Override
		public int compare(Actor o1, Actor o2) {
			return o1.getHeight() - o2.getHeight();
		}
	};
	public final int width, height;
	public HashMap<Point, PriorityQueue<Actor>> actors;
	public ArrayList<Actor> persistentActors;
	public Tile() {
		this( new HashMap<Point, PriorityQueue<Actor>>(), new ArrayList<Actor>(), World.DEFAULT_TILE_WIDTH, World.DEFAULT_TILE_HEGHT);
	}
	public Tile(int width, int height) {
		this(new HashMap<Point, PriorityQueue<Actor>>(), new ArrayList<Actor>(), width, height);
	}
	public Tile(HashMap<Point, PriorityQueue<Actor>> actors, int width, int height) {
		this(actors, new ArrayList<Actor>(), width, height);
	}
	public Tile(ArrayList<Actor> persistentActors, int width, int height) {
		this(new HashMap<Point, PriorityQueue<Actor>>(), persistentActors, width, height);
	}
	public Tile(HashMap<Point, PriorityQueue<Actor>> actors, ArrayList<Actor> persistentActors,int width, int height) {
		this.actors = actors;
		this.persistentActors = persistentActors;
		this.width = width;
		this.height = height;
	}
	public void checkBounds(Point point) {
		if (point.x >= width) {
			throw new InvalidParameterException("Trying to add outside map. point.x cannot be >= width");
		}
		else if (point.y >= height) {
			throw new InvalidParameterException("Tring to add outside map. point.y cannot be >= height");
		}
		else if (point.x < 0) {
			throw new InvalidParameterException("Tring to add outside map. point.x cannot be < 0");
		}
		else if (point.y < 0) {
			throw new InvalidParameterException("Tring to add outside map. point.y cannot be < 0");
		}
	}
	public void addActorAt(Point point, Actor actor) {
		checkBounds(point);
		PriorityQueue<Actor> actorList = actors.get(point);
		if (actor.isPersistent) {
			persistentActors.add(actor);
		}
		if (actorList == null) {
			actorList = new PriorityQueue<Actor>(11,actorHeightComparator);
			actorList.add(actor);
			actors.put(point, actorList);
		}
		else {
			actorList.add(actor);
		}
	}
	public void addActorsAt(Point point, Collection<Actor> actorList) {
		checkBounds(point);
		PriorityQueue<Actor> currentActors = actors.get(point);
		if (currentActors == null) {
			PriorityQueue<Actor> sortedActorList = new PriorityQueue<Actor>(11,actorHeightComparator);
			sortedActorList.addAll(actorList);
			actors.put(point, sortedActorList);
		}
		else {
			currentActors.addAll(actorList);
		}
	}
	public PriorityQueue<Actor> getActorsAtAsPriorityQueueByHeight(Point point) {
		return actors.get(point);
	}
	public PriorityQueue<Actor> getActorsAtAsPriorityQueueByHeight(Coordinate point) {
		return actors.get(point.getPixelCoordinates());
	}
	public Collection<Actor> getActorsAt(Point point) {
		return actors.get(point);
	}
	public Collection<Actor> getActorsAt(Coordinate point) {
		return actors.get(point.getPixelCoordinates());
	}
	

}
