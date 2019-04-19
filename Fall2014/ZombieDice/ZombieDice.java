//9 LOC
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ZombieDice {

	static JPanel zgui = new ZGUI();

	public static void main(String[] args) 
	{
		JFrame game = new JFrame(); //Create Frame
		game.setSize(496,400);
		game.getContentPane().add(zgui);
		game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.setVisible(true);
	}
}
