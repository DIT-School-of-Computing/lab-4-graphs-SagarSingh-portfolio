package ie.tudublin;

import processing.core.PApplet;

public class Arrays extends PApplet {
	String[] months = { "JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC" };

	float[] rainfall = { 200, 260, 300, 150, 100, 50, 10, 40, 67, 160, 400, 420 };

	int width = 700;
	int height = 700;

	public float map1(float a, float b, float c, float d, float e) {
		float r1 = c - b;
		float r2 = e - d;

		float howFar = a - b;
		return d + (howFar / r1) * r2;
	}

	void randomize() {
		for (int i = 0; i < rainfall.length; i++) {
			rainfall[i] = random(500);
		}
	}

	public void settings() {
		size(width, height);
		/*
		 * String[] m1 = months;
		 * months[0] = "XXX";
		 * print(m1[0]);
		 * for(int i = 0; i < months.length; i ++)
		 * {
		 * println("Month: " + months[i] + "\t" + rainfall[i]);
		 * }
		 * for (String s : m1) {
		 * println(s);
		 * }
		 * 
		 * int minIndex = 0;
		 * for(int i= 0 ; i < rainfall.length ; i ++)
		 * {
		 * if (rainfall[i] < rainfall[minIndex])
		 * {
		 * minIndex = i;
		 * }
		 * }
		 * 
		 * int maxIndex = 0;
		 * for(int i= 0 ; i < rainfall.length ; i ++)
		 * {
		 * if (rainfall[i] > rainfall[maxIndex])
		 * {
		 * maxIndex = i;
		 * }
		 * }
		 * 
		 * //println("The month with the minimum rainfall was " + months[minIndex] +
		 * " with " + rainfall[minIndex] + "rain");
		 * println("The month with the max rainfall was " + months[maxIndex] + " with "
		 * + rainfall[maxIndex] + "rain");
		 * 
		 * 
		 * float tot = 0;
		 * for(float f:rainfall)
		 * {
		 * tot += f;
		 * }
		 * 
		 * float avg = tot / (float) rainfall.length;
		 * 
		 * // a b-c d-e;
		 * println(map1(5, 0, 10, 0, 100));
		 * // 50
		 * 
		 * println(map1(25, 20, 30, 200, 300));
		 * // 250
		 * 
		 * println(map1(26, 25, 35, 0, 100));
		 * // 10
		 */

	}

	public void setup() {
		colorMode(HSB);
		background(0);
		randomize();

	}

	public float getMax() {
		int maxIndex = 0;
		for (int i = 0; i < rainfall.length; i++) {
			if (rainfall[i] > rainfall[maxIndex]) {
				maxIndex = i;
			}
		}

		return rainfall[maxIndex];
	}

	public void drawChart() {
		float xAxis = 50;
		float yAxis = 50;
		float textSize = 12;
		float maxAxis = getMax() + 30;
		int incrementX = 50;
		int xAxisRange = ceil(maxAxis / incrementX);

		textSize(textSize);
		textAlign(CENTER, CENTER);

		switch (mode) {
			case 0:
				text("Rainfall Bar Chart", width / 2, yAxis / 2);

				// axis lines
				stroke(0, 0, 255);
				strokeWeight(2);
				line(xAxis, yAxis, xAxis, (float) height - yAxis);
				line(xAxis, height - yAxis, (float) width - xAxis, (float) height - yAxis);

				// X-axis ticks and increments
				for (int i = 0; i < xAxisRange + 1; i++) {
					float y = map1(i, 0, xAxisRange, height - yAxis, yAxis);
					text(incrementX * i, xAxis - 20, y);
					line(xAxis - 10, y, xAxis, y);
				}

				// Y-axis labels and bars
				for (int i = 0; i < months.length; i++) {
					float x = map1(i, 0, months.length, xAxis, (float) width - xAxis);
					float x2 = map1(i + 1, 0, months.length, xAxis, (float) width - xAxis);
					float rainfallY = map1(rainfall[i], 0, 500, yAxis, (float) height - yAxis);
					fill(i * 10, 255, 255);
					rect(x, (float) height - yAxis, x2 - x, -rainfallY + yAxis);
					textAlign(CENTER, CENTER);
					fill(0, 0, 255);
					text(rainfall[i], (x2 + x) / 2, height - rainfallY - 20);
					text(months[i], x + 20, (float) height - yAxis + 15);
				}
				break;
			case 1:
				text("Rainfall Trend Chart", width / 2, yAxis / 2);

				// axis lines
				stroke(0, 0, 255);
				strokeWeight(2);
				line(xAxis, yAxis, xAxis, (float) height - yAxis);
				line(xAxis, height - yAxis, (float) width - xAxis, (float) height - yAxis);

				// X-axis ticks and increments
				for (int i = 0; i < xAxisRange + 1; i++) {
					float y = map1(i, 0, xAxisRange, height - yAxis, yAxis);
					text(incrementX * i, xAxis - 20, y);
					line(xAxis - 10, y, xAxis, y);
				}

				// Y-axis labels
				for (int i = 0; i < months.length; i++) {
					float x = map1(i, 0, months.length, xAxis, (float) width - xAxis);
					float x2 = map1(i + 1, 0, months.length, xAxis, (float) width - xAxis);
					float rainfallY = map1(rainfall[i], 0, 500, yAxis, (float) height - yAxis);

					fill(i * 10, 255, 255);
					if (i < months.length - 1) {
						float rainfallY2 = map1(rainfall[i + 1], 0, 500, yAxis, (float) height - yAxis);
						line((x + x2) / 2, height - rainfallY, (x2 - x) / 2 + x2, height - rainfallY2);
					}
					// rect(x, (float) height - yAxis, x2 - x, -rainfallY + yAxis);
					textAlign(CENTER, CENTER);
					fill(0, 0, 255);
					text(rainfall[i], (x2 + x) / 2, height - rainfallY - 20);
					text(months[i], x + 20, (float) height - yAxis + 15);
				}

				break;
			case 2:
				fill(0, 0, 255);
				textSize(20);
				text("Rainfall Pie Chart", width / 2, yAxis / 2);
				float midX = width / 2;
				float midY = height / 2;
				float pieSize = height / 2;
				float total = 0;
				float start = 0;
				float end = 0;
				float mid = 0;
				int i = 0;
				float textRadius = height / 4 + 20;

				for (float rain : rainfall) {
					total += rain;
				}

				for (float rain : rainfall) {
					fill((i++ * 20) % 360, 255, 255);
					end += map1(rain, 0, total, 0, TWO_PI);
					arc(midX, midY, pieSize, pieSize, start, end, PIE);
					mid = (start + end) / 2;

					fill(0, 0, 255);
					text(months[i - 1], midX + textRadius * cos(mid), midY + textRadius * sin(mid));
					start = end;
				}

				break;
			default:
				break;
		}
	}

	int mode = 0;

	public void keyPressed() {
		if (key >= '0' && key <= '9') {
			mode = key - '0';
		}
		println(mode);
	}

	public void draw() {

		background(0);
		drawChart();
	}
}
