package at.fhv.histogram;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class HistogramFrame extends Frame{
	
	double[] _values;
	int _border = 50;
	int _factor = 1; //used to test zooming

	public HistogramFrame(double[] values){
		_values = values;
		setSize(1200, 700);
		setVisible(true);
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e){
				setVisible(false);
				dispose();
				System.exit(0);
			}
		});

		/*
		for zooming in

		addMouseListener(new MouseAdapter() {
			
			public void mousePressed(MouseEvent e){
				_factor++;
				repaint();
			}
		});
		*/
	}

	//not be the best way to do it in the paint method but sufficient for now
	public void paint(Graphics g){
		int x = _border;
		int y = _border;
		int xMax = getWidth()- _border;
		int yMax = getHeight()- _border;
		char letter = 'A';
		
		int maxGraphLen = getHeight()- _border *2;
		int highestPercent = 0;
		
		g.drawLine(x, yMax, xMax, yMax);
		g.drawLine(x, yMax, x, yMax-maxGraphLen);

		int stepX = (getWidth()- _border *2)/26;
		int stepY = Math.round((maxGraphLen)/20)* _factor;
		
		
		//x-axis desc
		for (int i = _border +stepX; i < xMax;) {
			g.drawLine(i, yMax, i, yMax+10);
			g.drawString(letter+"", i, yMax+25);
			letter = (char) (letter+1);
						
			i=i+stepX;
		}

		//y-axis desc
		int percent = 5/ _factor;
		for (int i = yMax-stepY; percent <= 100 || i >= maxGraphLen;) {
			g.drawLine(x, i, x-10, i);
			g.drawString(percent+"", x-25, i);
			
			highestPercent = yMax-i;
			i = i-stepY;
			percent += 5;
		}

		//graphs
		g.setColor(Color.red);
		
		int graph;
		int firstStep = _border +stepX;
		
		for (int i = 0; i < _values.length; i++) {
			graph = (int) (highestPercent*(_values[i]/100)* _factor);
			
			g.drawLine(firstStep, yMax-graph, firstStep, yMax);
			firstStep += stepX;
		}
	}
}
