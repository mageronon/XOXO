package com.abuse.kuka;

import java.awt.Color;

import acm.graphics.GCanvas;
import acm.graphics.GLine;


public class Games {
	
	private GCanvas canvas;
	
	private Goes[][] cells = new Goes[3][3];
	private int[][] boardState = new int[3][3];
	private boolean isFinished;
	
	public boolean isFinished() {
		return isFinished;
	}

	public Games(GCanvas canvas) {
		this.canvas = canvas;
	}
	
	public void newGame() {
		initialSetUp();
	}
	
	public void changeBoardState(int i, int j, int code) {
		boardState[i][j] = code;
		int whaw = weHaveAWinner();
		if (whaw != 0) {
			isFinished = true;
			switch (whaw) {
				case 4:
					cells[0][0].getMyObject().setFillColor(Color.GREEN);
					cells[1][1].getMyObject().setFillColor(Color.GREEN);
					cells[2][2].getMyObject().setFillColor(Color.GREEN);
					break;
				case -4:
					cells[2][0].getMyObject().setFillColor(Color.GREEN);
					cells[1][1].getMyObject().setFillColor(Color.GREEN);
					cells[0][2].getMyObject().setFillColor(Color.GREEN);
					break;
				case 1:
					cells[0][0].getMyObject().setFillColor(Color.GREEN);
					cells[0][1].getMyObject().setFillColor(Color.GREEN);
					cells[0][2].getMyObject().setFillColor(Color.GREEN);
					break;
				case 2:
					cells[1][0].getMyObject().setFillColor(Color.GREEN);
					cells[1][1].getMyObject().setFillColor(Color.GREEN);
					cells[1][2].getMyObject().setFillColor(Color.GREEN);
					break;
				case 3:
					cells[2][0].getMyObject().setFillColor(Color.GREEN);
					cells[2][1].getMyObject().setFillColor(Color.GREEN);
					cells[2][2].getMyObject().setFillColor(Color.GREEN);
					break;
				case -1:
					cells[0][0].getMyObject().setFillColor(Color.GREEN);
					cells[1][0].getMyObject().setFillColor(Color.GREEN);
					cells[2][0].getMyObject().setFillColor(Color.GREEN);
					break;
				case -2:
					cells[0][1].getMyObject().setFillColor(Color.GREEN);
					cells[1][1].getMyObject().setFillColor(Color.GREEN);
					cells[2][1].getMyObject().setFillColor(Color.GREEN);
					break;
				case -3:
					cells[0][2].getMyObject().setFillColor(Color.GREEN);
					cells[1][2].getMyObject().setFillColor(Color.GREEN);
					cells[2][2].getMyObject().setFillColor(Color.GREEN);
					break;
			}
		}
	}

	private int weHaveAWinner() {
		
		if ((boardState[0][0] == boardState[1][1]) && (boardState[1][1] == boardState[2][2])) {
			return 4;
		}
		
		if ((boardState[2][0] == boardState[1][1]) && (boardState[1][1] == boardState[0][2])) {
			return -4;
		}
		
		for (int i = 0; i < 3; i++) {
			if ((boardState[i][0] == boardState[i][1]) && (boardState[i][1] == boardState[i][2])) {
				return i + 1;
			}
			if ((boardState[0][i] == boardState[1][i]) && (boardState[1][i] == boardState[2][i])) {
				return -(i + 1);
			}
		}					
		
		return 0;
	}

	private void initialSetUp() {
		
		isFinished = false;
		
		int x = canvas.getX();
		int y = canvas.getY();
		int canvasWidth = canvas.getWidth();
		int canvasHeight = canvas.getHeight();
		int cellWidth = canvasWidth/3;
		int cellHeight = canvasHeight/3;
		
		GLine line = new GLine(x + cellWidth, y, x + cellWidth, y + canvasHeight);
		line.setColor(Color.BLACK);
		canvas.add(line);
		
		line = new GLine(x + 2*cellWidth, y, x + 2*cellWidth, y + canvasHeight);
		line.setColor(Color.BLACK);
		canvas.add(line);
		
		line = new GLine(x, y + cellHeight, x + canvasWidth, y + cellHeight);
		line.setColor(Color.BLACK);
		canvas.add(line);
		
		line = new GLine(x, y + 2*cellHeight, x + canvasWidth, y + 2*cellHeight);
		line.setColor(Color.BLACK);
		canvas.add(line);
		
		int counter = 0;
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++) {
				cells[i][j] = new Goes(x + cellWidth*i, y + cellHeight*j + 1, 
						cellWidth, cellHeight - 2, i, j);
				cells[i][j].setCanvas(canvas);
				cells[i][j].setGame(this);
				canvas.add(cells[i][j]);
				
				boardState[i][j] = 100 + counter++;
			}
	}
}
