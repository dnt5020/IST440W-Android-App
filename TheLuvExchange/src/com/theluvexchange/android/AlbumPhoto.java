package com.theluvexchange.android;

import android.graphics.drawable.Drawable;

public class AlbumPhoto {
	private String id;
	private String filename;
	private String caption;
	private String ratingAverage;
	private String ratingTotal;
	private String ratingCount;
	private String viewerRating;
	private Drawable thumbnail;
	
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the filename
	 */
	public String getFilename() {
		return filename;
	}
	/**
	 * @param filename the filename to set
	 */
	public void setFilename(String filename) {
		this.filename = filename;
	}
	/**
	 * @return the caption
	 */
	public String getCaption() {
		return caption;
	}
	/**
	 * @param caption the caption to set
	 */
	public void setCaption(String caption) {
		this.caption = caption;
	}
	/**
	 * @return the ratingAverage
	 */
	public String getRatingAverage() {
		return ratingAverage;
	}
	/**
	 * @param ratingAverage the ratingAverage to set
	 */
	public void setRatingAverage(String ratingAverage) {
		this.ratingAverage = ratingAverage;
	}
	/**
	 * @return the ratingTotal
	 */
	public String getRatingTotal() {
		return ratingTotal;
	}
	/**
	 * @param ratingTotal the ratingTotal to set
	 */
	public void setRatingTotal(String ratingTotal) {
		this.ratingTotal = ratingTotal;
	}
	/**
	 * @return the ratingCount
	 */
	public String getRatingCount() {
		return ratingCount;
	}
	/**
	 * @param ratingCount the ratingCount to set
	 */
	public void setRatingCount(String ratingCount) {
		this.ratingCount = ratingCount;
	}
	/**
	 * @return the viewerRating
	 */
	public String getViewerRating() {
		return viewerRating;
	}
	/**
	 * @param viewerRating the viewerRating to set
	 */
	public void setViewerRating(String viewerRating) {
		this.viewerRating = viewerRating;
	}
	/**
	 * @return the thumbnail
	 */
	public Drawable getThumbnail() {
		return thumbnail;
	}
	/**
	 * @param thumbnail the thumbnail to set
	 */
	public void setThumbnail(Drawable thumbnail) {
		this.thumbnail = thumbnail;
	}
}
