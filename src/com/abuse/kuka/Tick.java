package com.abuse.kuka;

import java.awt.event.ActionEvent;

import javax.swing.JButton;

import acm.program.GraphicsProgram;

public class Tick extends GraphicsProgram {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Games tttg;
	
	public void init() {
		this.setSize(600, 600);
		this.tttg = new Games(this.getGCanvas());
		add(new JButton("New Game"), SOUTH);
		addActionListeners();
	}
	
	public void run() {
		this.tttg.newGame();
	}
	
	public void actionPerformed(ActionEvent e) {
		this.getGCanvas().removeAll();
		this.tttg.newGame();
	}
}
