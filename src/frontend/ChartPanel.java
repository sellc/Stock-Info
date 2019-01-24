package frontend;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Line2D;

import backend.Day;

public class ChartPanel extends Panel {

	private static final long serialVersionUID = 1519911696285742946L;

	private InfoPanel ip;
	private int infoX, infoY;
	private int infoWidth, infoHeight;

	private Day[] days;
	private Candle[] candles;
	private Average50Line[] averages50;
	private Average180Line[] averages180;
	private final int candleWidth = 3;

	private int index;
	private int mousePosition;
	private int candleCount;

	private double chartBaseY;
	private double candleYScale;
	private double segmentLow;

	// Candle range should be limited to width - infoPanelWidth
	public ChartPanel(int x, int y, int width, int height) {
		super(x, y, width, height);

		infoWidth = 160;
		infoHeight = height;
		infoX = width - infoWidth;
		infoY = y;
		ip = new InfoPanel(infoX, infoY, infoWidth, infoHeight);

		days = null;
		candles = null;
		averages50 = null;
		averages180 = null;
		chartBaseY = height;
		candleYScale = 0;
		index = 0;
		mousePosition = -1;
		addComponents();
		setComponents();
	}

	public void addComponents() {
		this.add(ip);
	}

	public void setComponents() {
		super.setPanel();
		setPanel();
	}

	public void setPanel() {
		this.addMouseMotionListener(new MouseMotionListener() {

			public void mouseDragged(MouseEvent e) {
			}

			public void mouseMoved(MouseEvent e) {
				if (days != null) {
					mousePosition = e.getX();
					setDay(mousePosition);
					if (index < candleCount) {
						ip.setLabels(days[index].getDate(), days[index].getOpen(), days[index].getClose(),
								days[index].getHigh(), days[index].getLow(), days[index].getAvg50(),
								days[index].getAvg180(), days[index].getOpenCloseDiff(),
								days[index].getAvg50CloseDiff(), days[index].getAvg180CloseDiff());
					}
				}
			}
		});
	}

	public void setDay(int x) {
		if (candleWidth > 0 && x < candleWidth * candleCount) {
			index = (x / (int) candleWidth);
		}
		this.repaint();
	}

	public void loadGraphData(double segmentHigh, double segmentLow, Day[] days) {
		candleCount = days.length;
		this.days = days;
		this.candles = new Candle[candleCount];
		this.averages50 = new Average50Line[candleCount - 50];
		this.averages180 = new Average180Line[candleCount - 180];
		this.segmentLow = segmentLow;
		candleYScale = chartBaseY / (segmentHigh - segmentLow);
		this.repaint();
	}

	public void setCandle() {
		if (candles[index] == null) {
			candles[index] = new Candle(days[index], index, candleYScale, chartBaseY, segmentLow);
		} else {
			candles[index].setCandle(days[index], candleYScale, chartBaseY, segmentLow);
		}
	}

	public void setAverage50() {
		if (averages50[index - 50] == null) {
			averages50[index - 50] = new Average50Line(days[index], days[index - 1], index, candleYScale, chartBaseY,
					segmentLow);
		} else {
			averages50[index - 50].setAverageLine(days[index], days[index - 1], candleYScale, chartBaseY, segmentLow);
		}
	}

	public void setAverage180() {
		if (averages180[index - 180] == null) {
			averages180[index - 180] = new Average180Line(days[index], days[index - 1], index, candleYScale, chartBaseY,
					segmentLow);
		} else {
			averages180[index - 180].setAverageLine(days[index], days[index - 1], candleYScale, chartBaseY, segmentLow);
		}
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		drawCurrentDayLine((Graphics2D) g);
		if (days != null) {
			for (index = 0; index < candleCount; index++) {
				setCandle();
				drawCandle((Graphics2D) g);
				if (index > 50) {
					setAverage50();
					drawAverage50((Graphics2D) g);
					if (index > 180) {
						setAverage180();
						drawAverage180((Graphics2D) g);
					}
				}
			}
		}
	}

	public void drawCandle(Graphics2D g) {
		g.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
		g.setColor(Color.gray);
		g.draw(candles[index].getCandleHighLow());

		if (days[index].getClose() > days[index].getOpen()) {
			g.setColor(Color.green);
		} else {
			g.setColor(Color.red);
		}
		g.draw(candles[index].getCandleOpenClose());
	}

	public void drawAverage50(Graphics2D g) {
		g.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
		g.setColor(Color.orange);
		g.draw(averages50[index - 50].getAverageLine());
	}

	public void drawAverage180(Graphics2D g) {
		g.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
		g.setColor(Color.cyan);
		g.draw(averages180[index - 180].getAverageLine());
	}

	public void drawCurrentDayLine(Graphics2D g) {
		g.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
		g.setColor(Color.white);
		Line2D.Double line = new Line2D.Double(mousePosition - (mousePosition % candleWidth) + candleWidth / 2, 0,
				mousePosition - (mousePosition % candleWidth) + candleWidth / 2, height);
		g.draw(line);

	}

}
