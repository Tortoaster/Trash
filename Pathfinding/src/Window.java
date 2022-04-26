import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Window extends JPanel implements MouseListener, Runnable {
    
	private boolean running = false;
	
	private Random random = new Random();
	
	private int startX = 1, startY = 1, endX = 3, endY = 3;
	private List<Node> path;
	private boolean[][] map;
	
    public static void main(String[] args) {
        new Window(800, 600, "Test");
    }
    
    public Window(int width, int height, String title) {
		createWindow(width, height, title, this);
		addMouseListener(this);
		startRunning(this);
	}
    
	public void createWindow(int width, int height, String title, JPanel panel) {
		JFrame frame = new JFrame(title);
		frame.add(panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setSize(width, height);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	public void startRunning(Runnable runnable) {
		new Thread(runnable).start();
		running = true;
	}
	
	public void stopRunning() {
    	running = false;
    	System.exit(0);
	}
	
	public void mouseClicked(MouseEvent e) {}
	
	public void mousePressed(MouseEvent e) {}
	
	public void mouseReleased(MouseEvent e) {
    	if(e.getButton() == MouseEvent.BUTTON1) {
    		startX = e.getX() / 10;
    		startY = e.getY() / 10;
		} else if(e.getButton() == MouseEvent.BUTTON3) {
			endX = e.getX() / 10;
			endY = e.getY() / 10;
		}
	}
	
	public void mouseEntered(MouseEvent e) {}
	
	public void mouseExited(MouseEvent e) {}
	
    public void run() {
    	int width = 25, height = 25;
    	map = new boolean[width][height];
    	
    	for(int y = 0; y < height; y++) {
    		for(int x = 0; x < width; x++) {
    			map[x][y] = random.nextBoolean();
			}
		}
		
    	while(running) {
    		path = findPath(startX, startY, endX, endY, map);
    		repaint();
		}
	}
	
	public void paint(Graphics g) {
		for(int y = 0; y < map[0].length; y++) {
			for(int x = 0; x < map.length; x++) {
				if(map[x][y]) {
					g.setColor(Color.DARK_GRAY);
				} else {
					g.setColor(Color.LIGHT_GRAY);
				}
				g.fillRect(x * 10, y * 10, 10, 10);
			}
		}
		
		g.setColor(Color.YELLOW);
		if(path != null) {
			for(int i = 0; i < path.size(); i++) {
				Node n = path.get(i);
				g.fillRect(n.getX() * 10, n.getY() * 10, 10, 10);
			}
		}
	}
	
	public List<Node> findPath(int startX, int startY, int endX, int endY, boolean[][] map) {
		
		int width = map.length;
		int height = map[0].length;
		
		Node[][] node = new Node[width][height];
		
		startX = Math.max(0, Math.min(width - 1, startX));
		startY = Math.max(0, Math.min(height - 1, startY));
		endX = Math.max(0, Math.min(width - 1, endX));
		endY = Math.max(0, Math.min(height - 1, endY));
		
		List<Node> path = new ArrayList<>();
		List<Node> open = new ArrayList<>();
		List<Node> closed = new ArrayList<>();
		
    	for(int y = 0; y < height; y++) {
    		for(int x = 0; x < width; x++) {
    			node[x][y] = new Node(x, y);
    			node[x][y].setHCost(Math.sqrt(Math.pow(x - endX, 2) + Math.pow(y - endY, 2)));
			}
		}
		
    	Node start = node[startX][startY];
    	Node end = node[endX][endY];
		
		open.add(start);
		
    	Node current;
    	
    	while(open.size() > 0) {
    		
    		// Set node with lowest value from open to current.
			open.sort(Node.comparator);
			current = open.get(0);
			
			open.remove(current);
			closed.add(current);
			
			// Found route, DO THIS AFTERWARDS!!!!!!!!!!!!!!!!!!!!!
			if(current.equals(end)) {
				
				// Backtracking using node's parents
				Node n = current;
				while(n != null) {
					path.add(n);
					n = n.getParent();
				}
				
				return path;
			}
			
			// Checking neighbors
			for(int a = 0; a < 4; a++) {
				
				int x = current.getX() + (int) Math.cos(a * Math.PI / 2);
				int y = current.getY() + (int) Math.sin(a * Math.PI / 2);
				
				if(x >= 0 && x < width && y >= 0 && y < height) {
					Node n = node[x][y];
					if(!(map[x][y] || closed.contains(n))) {
						
						double gCost = current.getGCost() + 1;
						if(!open.contains(n) || gCost < n.getGCost()) {
							
							n.setGCost(gCost);
							n.setParent(current);
							
							if(!open.contains(n)) {
								open.add(n);
							}
						}
					}
				}
			}
		}
		path.add(start);
    	return path;
	}
}
