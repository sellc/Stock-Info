package backend;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Stock {

	private String symbol;
	private final int totalTradingDays = 251;
	private Day[] days;
	private File excelFile;

	private int index;
	private int dayCount;
	private double avg50;
	private double avg180;
	private double segmentLow;
	private double segmentHigh;

	public Stock() {
		symbol = "";
		days = new Day[totalTradingDays];
		excelFile = null;
		resetValuesToZero();
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
		excelFile = new File(symbol + ".csv");
		resetValuesToZero();
		if (excelFile.exists()) {
			readInDays();
		}
	}

	public String getSymbol() {
		return symbol;
	}

	public Day[] getDays() {
		return days;
	}

	public double getSegmentLow() {
		return segmentLow;
	}

	public double getSegmentHigh() {
		return segmentHigh;
	}

	public double getHighLowDifference() {
		return Math.ceil(segmentHigh - segmentLow);
	}

	public void readInDays() {
		try {
			Scanner reader = new Scanner(excelFile);
			String input = reader.nextLine();
			Scanner lineInput = null;
			resetValuesToZero();
			while (index < totalTradingDays) {
				input = reader.nextLine();
				lineInput = new Scanner(input);
				lineInput.useDelimiter(",");

				days[index] = new Day(lineInput.next(), lineInput.nextDouble(), lineInput.nextDouble(),
						lineInput.nextDouble(), lineInput.nextDouble(), lineInput.nextInt());
				updateAverages();
				checkForNewHighsAndLows();

				dayCount++;
				index++;
				lineInput.close();
			}
			reader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void updateAverages() {
		avg50 += days[index].getClose();
		avg180 += days[index].getClose();
		if (dayCount > 49) {
			update50Averages();
			if (dayCount > 179) {
				update180Averages();
			}
		}
	}

	public void update50Averages() {
		avg50 -= days[index - 50].getClose();
		days[index].setAvg50(avg50 / 50);
	}

	public void update180Averages() {
		avg180 -= days[index - 180].getClose();
		days[index].setAvg180(avg180 / 180);
	}

	public void checkForNewHighsAndLows() {
		if (days[index].getHigh() > segmentHigh || segmentHigh == 0) {
			segmentHigh = days[index].getHigh();
		}
		if (days[index].getLow() < segmentLow || segmentLow == 0) {
			segmentLow = days[index].getLow();
		}
	}

	public void resetValuesToZero() {
		index = 0;
		dayCount = 0;
		avg50 = 0;
		avg180 = 0;
		segmentLow = 0;
		segmentHigh = 0;
	}
}
