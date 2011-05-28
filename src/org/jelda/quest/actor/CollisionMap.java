package org.jelda.quest.actor;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

public class CollisionMap {
	public boolean[][] collisionMap;
	public boolean isFull = false, isEmpty = false; 
	/*isFull: if true, then the entire collisionMap is true, meaning that it is completly solid and impassable.
	 *isEmpty: if true, then the entire collisionMap is false, meaning that this map is empty and anything can pass through it.
	 *IMPORTANT NOTE: it is possible for both isFull and isEmpty to be false, and the collisionMap to be either empty or full. In otherwords, just because
	 *isEmpty and isFull are both false, there is no gaurantee of any particular state.
	 */
	public CollisionMap(int width, int height, boolean defaultValue) {
		collisionMap = new boolean[width][height];
		fillArray(defaultValue);
	}
	public CollisionMap(boolean[][] otherCollisionMap) {
		isFull = true;
		isEmpty = true;
		this.collisionMap = new boolean[otherCollisionMap.length][otherCollisionMap[0].length];
		for (int i = 0; i < otherCollisionMap.length; i++) {
			for (int j = 0; j < otherCollisionMap[i].length; j++) {
				boolean value = otherCollisionMap[i][j];
				if (value) {
					isEmpty = false;
				}
				else {
					isFull = false;
				}
				this.collisionMap[i][j] = value;
			}
		}
	}
	public CollisionMap(Sprite image) {
		BufferedImage spriteImage = image.sprite;
		int width = spriteImage.getWidth(), height = spriteImage.getHeight(), backgroundColor = spriteImage.getRGB(0, height -1);
		collisionMap = new boolean[width][height];
		isEmpty = isFull = false;
		floodFillGenerate(spriteImage, 0, height-1, collisionMap);
		if (spriteImage.getRGB(0, 0) == backgroundColor)
			floodFillGenerate(spriteImage, 0, 0, collisionMap);
		if (spriteImage.getRGB(width -1, 0) == backgroundColor)
			floodFillGenerate(spriteImage, width-1, 0, collisionMap);
		if (spriteImage.getRGB(width-1, height-1) == backgroundColor)
			floodFillGenerate(spriteImage, width-1, height-1, collisionMap);
	}
	public CollisionMap(Sprite image, boolean passable) {
		collisionMap = new boolean[image.getWidth()][image.getHeight()];
		if (passable) {
			setClear();
		}
		else {
			setSolid();
		}
	}
	private void fillArray(boolean value) {
		isFull = value;
		isEmpty = !value;
		for (int i = 0; i < collisionMap.length; i++) {
			for (int j = 0; j < collisionMap[i].length; j++) {
				collisionMap[i][j] = value;
			}
		}
	}
	public void setSolid() {
		fillArray(true);
	}
	
	public void setClear() {
		fillArray(false);
	}
	/**
	 * 
	 * Marks all cells reachable from the starting point along a path of the same color false in the returned boolean array. 
	 * This represents a passable location in the collision map. Used for collision checking.
	 * @param image The image to flood fill
	 * @param x x coordinate of the starting location
	 * @param y y coordinate of the starting locations
	 * @param fillMap The boolean map in which to store results. It is passed as a parameter so that multiple calls to this function can be made
	 * in the same boolean array
	 * @return A boolean array of the same dimensions of the image, in which all 
	 */
	public static void floodFillGenerate(BufferedImage image, int x, int y, boolean[][] fillMap) {
		int targetColor = image.getRGB(x, y);
		LinkedList<Point> queue = new LinkedList<Point>();
		queue.offer(new Point(x, y));
		while (!queue.isEmpty()) {
			Point node = queue.remove();
			if (fillMap[node.x][node.y]) {
				fillMap[node.x][node.y] = false;
				Point w = new Point(node.x, node.y), e = new Point(
						node.x + 1, node.y);
				while (w.x >= 0 && image.getRGB(w.x, w.y) == targetColor) {
					fillMap[w.x][w.y] = false;
					Point temp = new Point(w.x, w.y - 1);
					if (temp.y >= 0
							&& image.getRGB(temp.x, temp.y) == targetColor
							&& fillMap[temp.x][temp.y]) {
						queue.offer(temp);
					}
					temp = new Point(w.x, w.y + 1);
					if (temp.y < image.getHeight()
							&& image.getRGB(temp.x, temp.y) == targetColor
							&& fillMap[temp.x][temp.y]) {
						queue.offer(temp);
					}
					w.x--;
				}
				while (e.x < image.getWidth()
						&& image.getRGB(e.x, e.y) == targetColor) {
					fillMap[e.x][e.y] = false;
					Point temp = new Point(e.x, e.y - 1);
					if (temp.y >= 0
							&& image.getRGB(temp.x, temp.y) == targetColor
							&& fillMap[temp.x][temp.y]) {
						queue.offer(temp);
					}
					temp = new Point(e.x, e.y + 1);
					if (temp.y < image.getHeight()
							&& image.getRGB(temp.x, temp.y) == targetColor
							&& fillMap[temp.x][temp.y]) {
						queue.offer(temp);
					}
					e.x++;
				}
			}
		}
	}
	
	public boolean collidesWith(int x1, int y1, CollisionMap other, int x2, int y2) {
		if (isEmpty || other.isEmpty) {
			return false;
		}
		Rectangle r1 = new Rectangle(x1, y1, collisionMap.length, collisionMap[0].length), r2 = new Rectangle(x2, y2, other.collisionMap.length, other.collisionMap[0].length);
		if (!r1.intersects(r2)) {
			return false;
		}
		if (isFull && other.isFull) {
			return true;
		}
		Rectangle.intersect(r1, r2, r1);
		for (int i = r1.x - x1; i < r1.x - x1 + r1.width; i++) { //TODO: Convince yourself that this always works
			for (int j = r1.y - y1; j < r1.y - y1+ r1.height; j++) {
				if (collisionMap[i][j] && other.collisionMap[i][j]) {
					return true;
				}
			}
		}
		return false;
	}
}
