import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

public class Game extends State {
	
	private Bow bow = new Bow(10);
	
	public Game(GSM gsm) {
		this.gsm = gsm;
	}
	
	public void mouseReleased(MouseEvent e) {
		bow.fire(e.getX(), e.getY());
	}
	
	public void update() {
		bow.update();
	}
	
	public void draw(Graphics g) {
		g.setColor(new Color(75 - (int) (Math.cos(System.currentTimeMillis() / 1000.0 / 2 * Math.PI / 10) * 50), 100 - (int) (Math.cos(System.currentTimeMillis() / 1000.0 / 2 * Math.PI / 10) * 50), 200 - (int) (Math.cos(System.currentTimeMillis() / 1000.0 / 2 * Math.PI / 10) * 50)));
		g.fillRect(0, 0, GSM.WIDTH, GSM.HEIGHT);
		g.setColor(new Color(250, 200, 100));
		g.fillOval(GSM.WIDTH / 2 + (int) (Math.sin(System.currentTimeMillis() / 1000.0 / 2 * Math.PI / 10) * 400) - 50, GSM.HEIGHT / 2 + (int) (Math.cos(System.currentTimeMillis() / 1000.0 / 2 * Math.PI / 10) * 400) - 50, 100, 100);
		g.setColor(new Color(220, 210, 240));
		g.fillOval(GSM.WIDTH / 2 - (int) (Math.sin(System.currentTimeMillis() / 1000.0 / 2 * Math.PI / 10) * 400) - 35, GSM.HEIGHT / 2 - (int) (Math.cos(System.currentTimeMillis() / 1000.0 / 2 * Math.PI / 10) * 400) - 35, 70, 70);
		g.setColor(new Color(20 - (int) (Math.cos(System.currentTimeMillis() / 1000.0 / 2 * Math.PI / 10) * 10), 90 - (int) (Math.cos(System.currentTimeMillis() / 1000.0 / 2 * Math.PI / 10) * 45), 60 - (int) (Math.cos(System.currentTimeMillis() / 1000.0 / 2 * Math.PI / 10) * 15)));
		g.fillRect(0, GSM.HEIGHT / 2, GSM.WIDTH, GSM.HEIGHT / 2);
		bow.draw(g);
	}
}
