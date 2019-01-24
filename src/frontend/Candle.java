package frontend;

import java.awt.geom.Line2D;

import backend.Day;

public class Candle {

	private Line2D.Double candleOpenClose;
	private Line2D.Double candleHighLow;
	private final int candleWidth = 3;

	public Candle(Day day, int index, double scale, double chartBaseY, double segmentLow) {
		candleOpenClose = new Line2D.Double();
		candleHighLow = new Line2D.Double();
		candleOpenClose.x1 = candleOpenClose.x2 = index * candleWidth + (candleWidth / 2);
		candleOpenClose.x2 = candleHighLow.x1 = candleHighLow.x2 = candleOpenClose.x1;
		candleHighLow.y1 = chartBaseY - (day.getHigh() * scale) + segmentLow * scale;
		candleHighLow.y2 = chartBaseY - (day.getLow() * scale) + segmentLow * scale;
		candleOpenClose.y1 = chartBaseY - (day.getOpen() * scale) + segmentLow * scale;
		candleOpenClose.y2 = chartBaseY - (day.getClose() * scale) + segmentLow * scale;
	}

	public void setCandle(Day day, double scale, double chartBaseY, double segmentLow) {
		candleHighLow.y1 = chartBaseY - (day.getHigh() * scale) + segmentLow * scale;
		candleHighLow.y2 = chartBaseY - (day.getLow() * scale) + segmentLow * scale;
		candleOpenClose.y1 = chartBaseY - (day.getOpen() * scale) + segmentLow * scale;
		candleOpenClose.y2 = chartBaseY - (day.getClose() * scale) + segmentLow * scale;
	}

	public Line2D getCandleOpenClose() {
		return candleOpenClose;
	}

	public Line2D getCandleHighLow() {
		return candleHighLow;
	}
}
