package frontend;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import backend.Stock;

public class LoadPanel extends Panel {

	private static final long serialVersionUID = -1324382467633347217L;

	private Stock stock;

	private JTextField loadField;
	private JButton loadButton;
	private JLabel currentChartLabel;
	private ChartPanel chartPanel;

	public LoadPanel(int x, int y, int width, int height) {
		super(x, y, width, height);
		stock = new Stock();
		loadField = new JTextField();
		loadButton = new JButton("Load Chart");
		currentChartLabel = new JLabel();
		chartPanel = new ChartPanel(x, y, width, height - 100);
		addComponents();
		setComponents();
		this.setVisible(true);
	}

	public void addComponents() {
		this.add(loadField);
		this.add(loadButton);
		this.add(currentChartLabel);
		this.add(chartPanel);
	}

	public void setComponents() {
		setPanel();
		setLoadField();
		setLoadButton();
		setCurrentChartLabel();
	}

	public void setPanel() {
		super.setPanel();
	}

	public void setLoadField() {
		loadField.setBounds(xLoc, yLoc + height - 100, 100, 20);
		loadField.addKeyListener(new KeyListener() {

			public void keyTyped(KeyEvent e) {
			}

			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					stock.setSymbol(loadField.getText());
					chartPanel.loadGraphData(stock.getSegmentHigh(), stock.getSegmentLow(), stock.getDays());
					setCurrentChartLabelText();
					loadField.selectAll();
				}
			}

			public void keyReleased(KeyEvent e) {
			}

		});
	}

	public void setLoadButton() {
		loadButton.setBounds(xLoc + 110, yLoc + height - 100, 100, 20);
		loadButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				stock.setSymbol(loadField.getText());
				chartPanel.loadGraphData(stock.getSegmentHigh(), stock.getSegmentLow(), stock.getDays());
				setCurrentChartLabelText();
				loadField.selectAll();
			}

		});
	}

	public void setCurrentChartLabel() {
		currentChartLabel.setBounds(xLoc + 220, yLoc + height - 100, 100, 20);
		currentChartLabel.setForeground(Color.white);
	}

	public void setCurrentChartLabelText() {
		currentChartLabel.setText(loadField.getText());
	}

}
