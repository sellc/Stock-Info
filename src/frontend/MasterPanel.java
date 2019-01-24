package frontend;

import java.awt.Color;

public class MasterPanel extends Panel {

	private static final long serialVersionUID = -6738401488623768220L;

	private LoadPanel loadPanel;

	public MasterPanel(int x, int y, int width, int height) {
		super(x, y, width, height);
		loadPanel = new LoadPanel(x, y, width, height);
		addComponents();
		setComponents();
	}

	public void addComponents() {
		this.add(loadPanel);
	}

	public void setComponents() {
		super.setPanel();
		setPanel();

	}

	public void setPanel() {
		this.setBackground(Color.LIGHT_GRAY);
	}
}
