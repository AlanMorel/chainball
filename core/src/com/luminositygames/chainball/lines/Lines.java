package com.luminositygames.chainball.lines;

import java.util.ArrayList;

import com.luminositygames.chainball.Chainball;
import com.luminositygames.chainball.Constants;
import com.luminositygames.chainball.balls.Ball;

/**
 * This file is part of Chainball
 * 
 * Copyright (c) 2013 - 2014 Luminosity Games
 * 
 * @author Alan Morel
 * @since July 1, 2014
 * @version 1.0
 */

public class Lines {

	private ArrayList<Line> lines;
	private Line currentLine;

	private float lastX;
	private float lastY;

	public Lines(){
		lines = new ArrayList<Line>();
		lastX = 0;
		lastY = 0;
	}

	public void touchHead() {
		lastX = Chainball.getX();
		lastY = Chainball.getY();
		updateCurrentLine(lastX, lastY);
	}

	public void addLine(float x1, float x2, float y1, float y2){
		Line line = new Line(x1, x2, y1, y2);
		lines.add(line);
	}

	public void drawLines(){
		for (Line line : lines){
			line.draw();
		}
	}

	public void addCurrentLine(float x2, float y2){
		addLine(currentLine.getX1(), currentLine.getY1(), x2, y2);
		lastX = x2;
		lastY = y2;
	}

	public void addCurrentLine(Ball ball) {
		float x2 = ball.getCircle().x + Constants.RADIUS;
		float y2 = ball.getCircle().y;
		addCurrentLine(x2, y2);
	}

	public void updateCurrentLine(float x1, float y2){
		currentLine = new Line(lastX, lastY, x1, y2);
	}

	public void updateCurrentLine(float speed){
		currentLine.update(speed);
	}

	public void drawCurrentLine(){
		currentLine.draw();
	}

	public void update(float speed) {
		lastY += speed;
		for (Line line : lines){
			line.update(speed);
		}
	}

	public void cleanUp() {
		for (int i = 0; i < lines.size(); i++){
			Line line = lines.get(i);
			if (!line.isVisible()){
				lines.remove(line);
			}
		}
	}

	public void reset() {
		//for (int i = 0; i < lines.size(); i++){
		//	Line line = lines.get(i);
		//	lines.remove(line);
		//}
	}
}
