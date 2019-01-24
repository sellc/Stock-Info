package frontend;

import java.awt.geom.Line2D;

import backend.Day;

public class Average50Line {
	
	private Line2D.Double average;
	private final int candleWidth = 3;
	
	public Average50Line(Day day, Day prevDay, int index, double scale, double chartBaseY, double segmentLow) {
		average = new Line2D.Double();
		average.x1 = index * candleWidth;
		average.y1 = chartBaseY - prevDay.getAvg50() * scale + segmentLow * scale;
		average.x2 = (index+1) * candleWidth;
		average.y2 = chartBaseY - day.getAvg50() * scale + segmentLow * scale;
	}
	
	public void setAverageLine(Day day, Day prevDay, double scale, double chartBaseY, double segmentLow) {
		average.y1 = chartBaseY - prevDay.getAvg50() * scale + segmentLow * scale;
		average.y2 = chartBaseY - day.getAvg50() * scale + segmentLow * scale;
	}
	
	public Line2D getAverageLine() {
		return average;
	}

}
