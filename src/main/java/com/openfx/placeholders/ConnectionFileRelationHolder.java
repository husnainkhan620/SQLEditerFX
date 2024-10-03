package com.openfx.placeholders;

import javafx.scene.image.ImageView;

public class ConnectionFileRelationHolder {
	
	private ImageView imageView;
	private String firstSeperator ;
	private String connectionName;
	private String secondSeperator;
	private String connectionFileName;
	
	public ConnectionFileRelationHolder(Builder builder) {
		this.imageView = builder.imageView;
		this.firstSeperator = " [ ";
		this.connectionName = builder.connectionName;
		this.secondSeperator = " ] ";
		this.connectionFileName = builder.connectionFileName;
	}

	public ImageView getImageView() {
		return imageView;
	}
	public String getFirstSeperator() {
		return firstSeperator;
	}

	public String getConnectionName() {
		return connectionName;
	}

	public String getSecondSeperator() {
		return secondSeperator;
	}

	public String getconnectionFileName() {
		return connectionFileName;
	}

	public void setImageView(ImageView imageView) {
		this.imageView = imageView;
	}


	public static Builder newBuilder() {
		return new Builder();
	}
	
	public static class Builder {
		
		private ImageView imageView;
		private String firstSeperator;
		private String connectionName;
		private String secondSeperator;
		private String connectionFileName;
		
		public Builder imageView(ImageView imageView) {
			this.imageView = imageView;
			return this;
		}

		
		public Builder connectionName(String connectionName) {
			this.connectionName = connectionName;
			return this;
		}

		
		public Builder connectionFileName(String connectionFileName) {
			this.connectionFileName = connectionFileName;
			return this;		
		}
		
		public ConnectionFileRelationHolder build() {
			return new ConnectionFileRelationHolder(this);
		}
	}
	
}
