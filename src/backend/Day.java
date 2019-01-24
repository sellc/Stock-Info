package backend;


public class Day {
	
	private String date;
	private double open;
	private double high;
	private double low;
	private double close;
	private int volume;
	private double avg50;
	private double avg180;
	
	public Day(String date, double open, double high, double low, double close, int volume) {
		this(date, open, high, low, close, volume, 0, 0);
	}

	public Day(String date, double open, double high, double low, double close, int volume, double avg50) {
		this(date, open, high, low, close, volume, avg50, 0);
	}

	public Day(String date, double open, double high, double low, double close, int volume, double avg50,
			double avg180) {
		this.date = date;
		this.open = open;
		this.high = high;
		this.low = low;
		this.close = close;
		this.volume = volume;
		this.avg50 = avg50;
		this.avg180 = avg180;
	}

	public void setAvg50(double avg50) {
		this.avg50 = avg50;
	}

	public void setAvg180(double avg180) {
		this.avg180 = avg180;
	}
	
	public String getDate() {
		return date;
	}

	public double getOpen() {
		return roundToTwoPlaces(open);
	}

	public double getHigh() {
		return roundToTwoPlaces(high);
	}

	public double getLow() {
		return roundToTwoPlaces(low);
	}

	public double getClose() {
		return roundToTwoPlaces(close);
	}

	public int getVolume() {
		return volume;
	}

	public double getAvg50() {
		return roundToTwoPlaces(avg50);
	}

	public double getAvg180() {
		return roundToTwoPlaces(avg180);
	}
	
	public double getOpenCloseDiff() {
		return roundToTwoPlaces(((close/open)-1)*100);
	}
	
	public double getAvg50CloseDiff() {
		return roundToTwoPlaces(((close/avg50)-1)*100);
	}

	public double getAvg180CloseDiff() {
		return roundToTwoPlaces(((close/avg180)-1)*100);
	}
	
	public double roundToTwoPlaces(double value) {
		return Math.round(value*100.0)/100.0;
	}
	
	public String toString() {
		return date + " " + open + ", " + high + ", " + low + ", " + close + ", " + getOpenCloseDiff() + "," + volume
				+ ", " + avg50 + ", " + avg180 + "," + "\n";
	}

}
