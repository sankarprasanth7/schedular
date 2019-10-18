package com.incl.content.schedular.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author prasanth
 *
 */
@Entity
public class Log implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private int dropboxId;

	private long scanTime;

	@Column(columnDefinition = "text")
	private String files;

	private String username;

	private String hostname;

	private String status;
	@Column(columnDefinition = "text")
	private String log;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the dropboxId
	 */
	public int getDropboxId() {
		return dropboxId;
	}

	/**
	 * @param dropboxId the dropboxId to set
	 */
	public void setDropboxId(int dropboxId) {
		this.dropboxId = dropboxId;
	}

	/**
	 * @return the scanTime
	 */
	public long getScanTime() {
		return scanTime;
	}

	/**
	 * @param scanTime the scanTime to set
	 */
	public void setScanTime(long scanTime) {
		this.scanTime = scanTime;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the hostname
	 */
	public String getHostname() {
		return hostname;
	}

	/**
	 * @param hostname the hostname to set
	 */
	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the log
	 */
	public String getLog() {
		return log;
	}

	/**
	 * @param log the log to set
	 */
	public void setLog(String log) {
		this.log = log;
	}

	/**
	 * @return the files
	 */
	public String getFiles() {
		return files;
	}

	/**
	 * @param files the files to set
	 */
	public void setFiles(String files) {
		this.files = files;
	}

}
