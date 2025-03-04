package com.openfx.erdiagram;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.shape.Line;

public class LineNode extends Line {
	
	Line newLine;
	Line lineAtStart;
	Line lineAtEnd;
	TitledPaneNode leftTitledPaneNode ;
	TitledPaneNode rightTitledPaneNode ;
	
	List<Line> alladdedLines = new ArrayList<Line>();
	
	public Line getLine() {
		return newLine;
	}
	
	public List<Line> getAlladdedLines() {
		return alladdedLines;
	}

	public void setAlladdedLines(List<Line> alladdedLines) {
		this.alladdedLines = alladdedLines;
	}

	public Line getLineAtStart() {
		return lineAtStart;
	}

	public void setLineAtStart(Line lineAtStart) {
		this.lineAtStart = lineAtStart;
	}

	public Line getLineAtEnd() {
		return lineAtEnd;
	}

	public void setLineAtEnd(Line lineAtEnd) {
		this.lineAtEnd = lineAtEnd;
	}
	
	public TitledPaneNode getLeftTitledPaneNode() {
		return leftTitledPaneNode;
	}

	public void setLeftTitledPaneNode(TitledPaneNode leftTitledPaneNode) {
		this.leftTitledPaneNode = leftTitledPaneNode;
	}

	public TitledPaneNode getRightTitledPaneNode() {
		return rightTitledPaneNode;
	}

	public void setRightTitledPaneNode(TitledPaneNode rightTitledPaneNode) {
		this.rightTitledPaneNode = rightTitledPaneNode;
	}

	private Line makeNewLine() {
		newLine = new Line();
		return newLine;
	}
	
	public List<Line> addNewLine(){
		// get the last line in the list and attach new line to it
		 Line newLine = makeNewLine();
		 Line lastLine = alladdedLines.get(alladdedLines.size()-1);
		 lastLine.setEndX(newLine.getStartX());		
		 newLine.startXProperty().bind( lastLine.layoutXProperty().add(0));
		 newLine.startYProperty().bind( lastLine.layoutYProperty().add(0));
		 
		 return alladdedLines;
	}
	
	public LineNode() {
		Line line = makeNewLine();
		alladdedLines.add(line);
	}

}
