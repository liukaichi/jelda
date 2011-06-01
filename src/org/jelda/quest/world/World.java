package org.jelda.quest.world;

import java.awt.Point;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

import org.jelda.quest.actor.Actor;

public class World {
	public static final Comparator<Actor> actorHeightComparator = new Comparator<Actor>() {

		@Override
		public int compare(Actor o1, Actor o2) {
			return o1.getPriority() - o2.getPriority();
		}
	};
	private HashMap<Point, PriorityQueue<Actor>> actors;

	public World() {
		this(null);
	}

	public World(Collection<Actor> actors) {
		this.actors = new HashMap<Point, PriorityQueue<Actor>>();
		addActors(actors);
	}

	public static void boundsAreValid(Point point) { //TODO WHY YOU THROW EXCEPTION LIKE LITTLE GIRL
		if (point == null) {
			throw new IllegalArgumentException("point cannot be null");
		}
		if (point.x < 0) {
			throw new IllegalArgumentException(
					"Tring to add outside map. X cannot be < 0");
		} else if (point.y < 0) {
			throw new IllegalArgumentException(
					"Tring to add outside map. Y cannot be < 0");
		}
	}

	public void addActorAt(Point point, Actor actor) {
		actor.setLocation(point);
		addActor(actor);
	}

	public void addActor(Actor actor) {
		if (actor != null) {
			Point point = actor.getLocation();
			boundsAreValid(point);
			PriorityQueue<Actor> actorList = actors.get(point);
			if (actorList == null) {
				actorList = new PriorityQueue<Actor>(11, actorHeightComparator);
				actorList.add(actor);
				actors.put(point, actorList);
			} else {
				actorList.add(actor);
			}
		}
	}

	public void addActors(Collection<Actor> actorList) {
		if (actorList != null) {
			for (Actor actor : actorList) {
				addActor(actor);
			}
		}
	}

	public void addActorsAt(Point point, Collection<Actor> actorList) {
		if (actorList != null) {
			for (Actor actor : actorList) {
				addActorAt(point, actor);
			}
		}
	}

	public Collection<Actor> getActorsAt(Point point) {
		return actors.get(point);
	}
	
	public boolean removeActor(Actor actor) {
		return actors.get(actor.getLocation()).remove(actor);
	}
	
	public boolean removActorsAt(Point point) {
		return actors.remove(point) != null;
	}
}
