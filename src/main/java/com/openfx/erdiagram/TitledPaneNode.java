package com.openfx.erdiagram;

import java.util.Comparator;
import java.util.TreeSet;

import javafx.scene.control.TitledPane;

class TitlePaneComparator implements Comparator<TitledPaneNode> {

	@Override
	public int compare(TitledPaneNode o1, TitledPaneNode o2) {
		return o1.getText().compareTo(o2.getText());
	}
	
}

public class TitledPaneNode extends TitledPane {
	
	
	TreeSet<TitledPaneNode> topRelatedTitledPanes = new TreeSet<TitledPaneNode>(new TitlePaneComparator());
	TreeSet<TitledPaneNode> rigtRelatedTitledPanes = new TreeSet<TitledPaneNode>(new TitlePaneComparator());
	TreeSet<TitledPaneNode> bottomRelatedTitledPanes = new TreeSet<TitledPaneNode>(new TitlePaneComparator());
	TreeSet<TitledPaneNode> leftRelatedTitledPanes = new TreeSet<TitledPaneNode>(new TitlePaneComparator());
	
//	private TitledPane titledPane;
	/*
	 * private List<LineNode> attachedLines = new ArrayList<LineNode>();
	 * 
	 * private List<TitledPaneNode> relatedTitledPaneNodes = new
	 * ArrayList<TitledPaneNode>();
	 
	
	public TitledPane getTitledPane() {
		return titledPane;
	}
	
	public void setTitledPane(TitledPane titledPane) {
		this.titledPane = titledPane;
	}
	
	public List<LineNode> getAttachedLines() {
		return attachedLines;
	}
	public void setAttachedLines(List<LineNode> attachedLines) {
		this.attachedLines = attachedLines;
	}

	public List<TitledPaneNode> getRelatedTitledPaneNodes() {
		return relatedTitledPaneNodes;
	}

	public void setRelatedTitledPaneNodes(List<TitledPaneNode> relatedTitledPaneNodes) {
		this.relatedTitledPaneNodes = relatedTitledPaneNodes;
	}
	*/
	
	
	
	
	public boolean equals(Object object) {
		
		TitledPaneNode titledPaneNode = (TitledPaneNode)object;
		
		if(this.getText().equals(titledPaneNode.getText()))
			return true;
		
		return false;
	}
	

	public TreeSet<TitledPaneNode> getTopRelatedTitledPanes() {
		return topRelatedTitledPanes;
	}


	public void setTopRelatedTitledPanes(TreeSet<TitledPaneNode> topRelatedTitledPanes) {
		this.topRelatedTitledPanes = topRelatedTitledPanes;
	}


	public TreeSet<TitledPaneNode> getRigtRelatedTitledPanes() {
		return rigtRelatedTitledPanes;
	}


	public void setRigtRelatedTitledPanes(TreeSet<TitledPaneNode> rigtRelatedTitledPanes) {
		this.rigtRelatedTitledPanes = rigtRelatedTitledPanes;
	}


	public TreeSet<TitledPaneNode> getBottomRelatedTitledPanes() {
		return bottomRelatedTitledPanes;
	}


	public void setBottomRelatedTitledPanes(TreeSet<TitledPaneNode> bottomRelatedTitledPanes) {
		this.bottomRelatedTitledPanes = bottomRelatedTitledPanes;
	}


	public TreeSet<TitledPaneNode> getLeftRelatedTitledPanes() {
		return leftRelatedTitledPanes;
	}


	public void setLeftRelatedTitledPanes(TreeSet<TitledPaneNode> leftRelatedTitledPanes) {
		this.leftRelatedTitledPanes = leftRelatedTitledPanes;
	}


	public int hashcode() {
		return this.getText().length();
	}

	
	
}
