package frontend;

import java.awt.Color;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class Window extends JFrame {

	private static final long serialVersionUID = 6952311299448750774L;
	private MasterPanel masterPanel;
	private int xLoc = 0, yLoc = 0;
	private int width = Toolkit.getDefaultToolkit().getScreenSize().width;
	private int height = Toolkit.getDefaultToolkit().getScreenSize().height-100;

	public Window() {
		masterPanel = new MasterPanel(0, 0, width, height);
		addComponents();
		setFrame();
	}

	public void addComponents() {
		this.add(masterPanel);
	}

	public void setFrame() {
		this.setBackground(Color.gray);
		this.setBounds(xLoc, yLoc, width, height);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Stock Chart");
		this.setVisible(true);
	}

}
