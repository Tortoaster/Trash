public class Main {
	
	public static void main(String[] args) {
		Window window = new Window(800, 800, 60, "Sprite Generator");
		Game game = new Game(window);
		
		window.setState(game);
		
		new Thread(window).start();
	}
}
