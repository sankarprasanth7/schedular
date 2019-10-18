/**
 *
 */
package com.incl.content.schedular.bean;

import java.util.Date;

/**
 * @author prasanth
 *
 */
public class FetchRequest {

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "FetchRequest [type=" + type + ", from=" + from + ", to=" + to + ", count=" + count + "]";
	}
	/**
	 *
	 */
	int type;//1-fetch from dropbox, 2-rerun already fetched from downloader jobs
	long from;
	long to;
	int count;
	public FetchRequest() {
		// TODO Auto-generated constructor stub


	}
	/**
	 * @return the type
	 */
	public int getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(int type) {
		this.type = type;
	}

	/**
	 * @return the count
	 */
	public int getCount() {
		return count;
	}
	/**
	 * @param count the count to set
	 */
	public void setCount(int count) {
		this.count = count;
	}
	/**
	 * @return the from
	 */
	public long getFrom() {
		return from;
	}
	/**
	 * @param from the from to set
	 */
	public void setFrom(long from) {
		this.from = from;
	}
	/**
	 * @return the to
	 */
	public long getTo() {
		return to;
	}
	/**
	 * @param to the to to set
	 */
	public void setTo(long to) {
		this.to = to;
	}



}
