package org.jelda.quest.world;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class Room {
	@Override
	public String toString() {
		return "Room with tiles: " + Arrays.toString(persistentTiles.toArray());
	}

	private PriorityQueue<Tile> persistentTiles;
	public Room(File f) {
		persistentTiles = new PriorityQueue<Tile>(32, new Comparator<Tile>() {

			@Override
			public int compare(Tile o1, Tile o2) {
				if (o1.getZ() < o2.getZ()) {
					return -1;
				}
				else if (o1.getZ() > o2.getZ()) {
					return 1;
				}
				else {
					return 0;
				}
			}
		});
		loadFromXML(f);
	}

	private void loadFromXML(File f) {
		try {

			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxParser = factory.newSAXParser();

			DefaultHandler handler = new DefaultHandler() {

				boolean room = false;
				boolean tile = false;
				boolean xlocation = false;
				boolean ylocation = false;
				boolean zlocation = false;
				boolean actorid = false;
				Tile currentTile;

				public void startElement(String uri, String localName,
						String qName, Attributes attributes)
						throws SAXException {
					
					if (qName.equalsIgnoreCase("room")) {
						room = true;
					}

					if (qName.equalsIgnoreCase("tile")) {
						tile = true;
						currentTile = new Tile();
					}

					if (qName.equalsIgnoreCase("xlocation")) {
						xlocation = true;
					}

					if (qName.equalsIgnoreCase("ylocation")) {
						ylocation = true;
					}
					if (qName.equalsIgnoreCase("zlocation")) {
						zlocation = true;
					}
					if (qName.equalsIgnoreCase("actorid")) {
						actorid = true;
					}

				}

				public void endElement(String uri, String localName,
						String qName) throws SAXException {
					if (qName.equalsIgnoreCase("tile")) {
						persistentTiles.add(currentTile);
						currentTile = null;
					}

				}

				public void characters(char ch[], int start, int length)
						throws SAXException {

					if (room) {
						room = false;
					}

					if (tile) {
						tile = false;
					}

					if (xlocation) {
						currentTile.setX(Integer.parseInt(new String(ch, start, length)));
						xlocation = false;
					}

					if (ylocation) {
						currentTile.setY(Integer.parseInt(new String(ch, start, length)));
						ylocation = false;
					}
					

					if (zlocation) {
						currentTile.setZ(Integer.parseInt(new String(ch, start, length)));
						zlocation = false;
					}
					
					if (actorid) {
						currentTile.setActorID(new String(ch, start, length));
						actorid = false;
					}
					

				}

			};

			saxParser.parse(f, handler);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
