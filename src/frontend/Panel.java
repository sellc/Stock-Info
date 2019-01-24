package frontend;

import java.awt.Color;

import javax.swing.JPanel;

public abstract class Panel extends JPanel{
	
	private static final long serialVersionUID = -8022715150631566058L;

	protected int xLoc, yLoc;
	protected int width, height;
	
	public Panel() {
		this(0, 0, 0, 0);
	}
	
	public Panel(int x, int y, int width, int height) {
		this.xLoc = x;
		this.yLoc = y;
		this.width = width;
		this.height = height;
	}
	
	public void setPanel() {
		this.setLayout(null);
		this.setBackground(Color.black);
		this.setBounds(xLoc, yLoc, width, height);
		this.setVisible(true);
	}
	
	public abstract void addComponents();
	public abstract void setComponents();
	
}
