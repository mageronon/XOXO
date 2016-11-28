package com.abuse.kuka;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import acm.graphics.GCanvas;
import acm.graphics.GFillable;
import acm.graphics.GOval;
import acm.graphics.GPolygon;
import acm.graphics.GRect;

public class Goes extends GRect implements MouseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private GFillable myObject;
	
	private static boolean shouldDrawCross = true;	
	private boolean isFree;
	private int my_i, my_j;	
	
	private Games game;
	private GCanvas canvas;

	public Goes(double x, double y, double width, double height, int i, int j) {
		super(x, y, width, height);		
		this.setColor(Color.BLUE);
		this.setVisible(false);
		isFree = true;
		my_i = i;
		my_j = j;
		addMouseListener(this);
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		if (isFree && !game.isFinished()) {
			if (shouldDrawCross) {
				drawCross();
				game.changeBoardState(my_i, my_j, 1);
			} else {
				drawNaught();
				game.changeBoardState(my_i, my_j, 2);
			}
			isFree = false;
			shouldDrawCross = !shouldDrawCross;
			this.setColor(Color.RED);
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		this.setVisible(true);		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		this.setVisible(false);		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {		
	}
	
	public void setGame(Games game) {
		this.game = game;
	}
	
	public void setCanvas(GCanvas canvas) {
		this.canvas = canvas;
	}
	
	public GFillable getMyObject() {
		return myObject;
	}
	
	private void drawCross() {
		int x = (int) this.getX();
		int y = (int) this.getY();
		int width = (int) this.getWidth();
		int height = (int) this.getHeight();
		
		GPolygon cross = new GPolygon();		
		cross.addVertex(x + width/4, y - 1);
		cross.addVertex(x + width/2, y + height/4);
		cross.addVertex(x + 3*width/4, y - 1);
		cross.addVertex(x + width - 1, y + height/4);
		cross.addVertex(x + 3*width/4, y + height/2);
		cross.addVertex(x + width - 1, y + 3*height/4);
		cross.addVertex(x + 3*width/4 + 1, y + height);
		cross.addVertex(x + width/2, y + 3*height/4);
		cross.addVertex(x + width/4 + 1, y + height);
		cross.addVertex(x + 1, y + 3*height/4);
		cross.addVertex(x + width/4, y + height/2);
		cross.addVertex(x + 1, y + height/4);
		cross.setFilled(true);
		cross.setFillColor(Color.BLACK);
		canvas.add(cross);
		
		myObject = cross;
	}
	
	private void drawNaught() {
		int x = (int) this.getX();
		int y = (int) this.getY();
		int width = (int) this.getWidth();
		int height = (int) this.getHeight();
		
		GOval outerCircle = new GOval(x + 1, y + 1, width -2 , height - 2);
		outerCircle.setFilled(true);
		outerCircle.setFillColor(Color.BLUE);
		
		GOval innerCircle = new GOval(x + width/4 + 1, y + width/4 + 1, width/2 -2 , height/2 -2);
		innerCircle.setFilled(true);
		innerCircle.setFillColor(Color.WHITE);
		
		canvas.add(outerCircle);
		canvas.add(innerCircle);
		
		myObject = outerCircle;
	}
}