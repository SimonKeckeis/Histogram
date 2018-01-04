package at.fhv.histogram;

import java.text.DecimalFormat;
import java.util.Scanner;

public class Histogram {

	public static double[] charFreq(String s) {

		double[] freqArray = new double[26];
		int letterCounter = 0;

		for (int i = 0; i < s.length(); i++) {
			if (Character.isLetter(s.charAt(i))) {
				if (Character.isLowerCase(s.charAt(i))) {
					freqArray[s.charAt(i) - 'a'] += 1;
				} else {
					freqArray[s.charAt(i) - 'A'] += 1;
				}

				letterCounter++;
			}
		}

		for (int i = 0; i < freqArray.length; i++) {
			freqArray[i] = (freqArray[i] / letterCounter)*100;
		}

		return freqArray;
	}

	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		String input = reader.nextLine();

		System.out.println("input: " + input + "\n");

		double[] freq = charFreq(input);
		DecimalFormat df = new DecimalFormat("0.000");

		for (int i = 0; i < freq.length; i++) {
			char letter = (char) ('A' + i);
			System.out.println(letter + ": " + df.format(freq[i]));
		}
		
		HistogramFrame cff = new HistogramFrame(freq);

	}

}
