import java.util.Comparator;

public class Node {
	
	private double gCost, hCost;
	
	private int x, y;
	
	public static Comparator<Node> comparator = new Comparator<Node>() {
		public int compare(Node n1, Node n2) {
			return Double.compare(n1.getFCost(), n2.getFCost());
		}
	};
	
	private Node parent;
	
	public Node(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void setParent(Node n) {
		parent = n;
	}
	
	public void setGCost(double value) {
		gCost = value;
	}
	
	public void setHCost(double value) {
		hCost = value;
	}
	
	public Node getParent() {
		return parent;
	}
	
	public double getFCost() {
		return getGCost() + getHCost();
	}
	
	public double getGCost() {
		return gCost;
	}
	
	public double getHCost() {
		return hCost;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
}
