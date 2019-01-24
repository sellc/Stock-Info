package frontend;

import java.awt.Color;

import javax.swing.JLabel;

public class InfoPanel extends Panel {

	private static final long serialVersionUID = -9140738834892666381L;
	private JLabel[] labels;
	private int compCount;

	public InfoPanel(int x, int y, int width, int height) {
		super(x, y, width, height);
		compCount = 10;
		labels = new JLabel[compCount];
		labels[0] = new JLabel("Date:");
		labels[1] = new JLabel("O:");
		labels[2] = new JLabel("C:");
		labels[3] = new JLabel("H:");
		labels[4] = new JLabel("L:");
		labels[5] = new JLabel("Avg50:");
		labels[6] = new JLabel("Avg180:");
		labels[7] = new JLabel("Daily % Change:");
		labels[8] = new JLabel("Avg50 % Change:");
		labels[9] = new JLabel("Avg180 % Change:");

		addComponents();
		setComponents();
	}

	public void addComponents() {
		int index = 0;
		while(index < compCount) {
			this.add(labels[index]);
			index++;
		}
	}
	
	public void setComponents() {
		super.setPanel();
		setPanel();
	}

	public void setPanel() {
		this.setBackground(Color.white);
		int index = 0;
		while(index < compCount) {
			labels[index].setBounds(0, (height / compCount) * index, width, height/compCount);
			index++;
		}
		this.setVisible(true);
	}

	public void setLabels(String date, double open, double close, double high, double low, double avg50,
			double avg180, double openCloseDiff, double avg50CloseDiff, double avg180CloseDiff) {
		labels[0].setText("Date: " + date);
		labels[1].setText("O: " + open);
		labels[2].setText("C: " + close);
		labels[3].setText("H: " + high);
		labels[4].setText("L: " + low);
		labels[5].setText("Avg50: " + avg50);
		labels[6].setText("Avg180: " + avg180);
		labels[7].setText("Daily %: " + openCloseDiff);
		labels[8].setText("Avg 50%: " + avg50CloseDiff);
		labels[9].setText("Avg 180%: " + avg180CloseDiff);
	}

}
