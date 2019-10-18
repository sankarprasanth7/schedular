/**
 * 
 */
package com.incl.content.schedular.bean;

 

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
 

/**
 * @author prasanth
 *
 */

@Entity
@Table(name="job")
public class Job {

	/*

	 */
	@Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String hostname;
	private String type;
	private String username;
	private String password;
	private String cron;
	private String path;
	private String filter;
	private String publishername;
	private int group_id;
	private String onixversion;
	private String localpath;
	private long lastFetch;
	private String admin;
	private String rerunAdmin;
	private long lastRerun;
	@JsonProperty
	private boolean isActive;
	@JsonProperty
	private boolean isRunning;
	private int priority;
	private int backupType;

	private boolean deleteOnFetch=false;
	 @Column(
		        name = "sender", 
		        nullable = false, 
		        updatable = false
		    )
	private int sender;
	
	
	private String listeners;
	

	public String getListeners() {
		return listeners;
	}

	public void setListeners(String listeners) {
		this.listeners = listeners;
	}

	/**
	 * 
	 */
	public Job() {

	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	public int getGroup_id() {
		return group_id;
	}

	public void setGroup_id(int group_id) {
		this.group_id = group_id;
	}

	public String getHostname() {
		return hostname;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCron() {
		return cron;
	}

	public void setCron(String cron) {
		this.cron = cron;
	}

	public String getFilter() {
		return filter;
	}

	public void setFilter(String filter) {
		this.filter = filter;
	}

	public String getPublishername() {
		return publishername;
	}

	public void setPublishername(String publishername) {
		this.publishername = publishername;
	}

	public String getOnixversion() {
		return onixversion;
	}

	public void setOnixversion(String onixversion) {
		this.onixversion = onixversion;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	/**
	 * @return the localpath
	 */
	public String getLocalpath() {
		return localpath;
	}

	/**
	 * @param localpath
	 *            the localpath to set
	 */
	public void setLocalpath(String localpath) {
		this.localpath = localpath;
	}

	/**
	 * @return the lastFetch
	 */
	public long getLastFetch() {
		return lastFetch;
	}

	/**
	 * @param lastFetch the lastFetch to set
	 */
	public void setLastFetch(long lastFetch) {
		this.lastFetch = lastFetch;
	}

	/**
	 * @return the admin
	 */
	public String getAdmin() {
		return admin;
	}

	/**
	 * @param admin the admin to set
	 */
	public void setAdmin(String admin) {
		this.admin = admin;
	}

	/**
	 * @return the rerunAdmin
	 */
	public String getRerunAdmin() {
		return rerunAdmin;
	}

	/**
	 * @param rerunAdmin the rerunAdmin to set
	 */
	public void setRerunAdmin(String rerunAdmin) {
		this.rerunAdmin = rerunAdmin;
	}

	/**
	 * @return the lastRerun
	 */
	public long getLastRerun() {
		return lastRerun;
	}

	/**
	 * @param lastRerun the lastRerun to set
	 */
	public void setLastRerun(long lastRerun) {
		this.lastRerun = lastRerun;
	}

	/**
	 * @return the isActive
	 */
	public boolean getIsActive() {
		return isActive;
	}

	/**
	 * @param isActive the isActive to set
	 */
	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}

	/**
	 * @return the isRunning
	 */
	public boolean getIsRunning() {
		return isRunning;
	}

	/**
	 * @param isRunning the isRunning to set
	 */
	public void setIsRunning(boolean isRunning) {
		this.isRunning = isRunning;
	}

	/**
	 * @return the deleteOnFetch
	 */
	public boolean isDeleteOnFetch() {
		return deleteOnFetch;
	}

	/**
	 * @param deleteOnFetch the deleteOnFetch to set
	 */
	public void setDeleteOnFetch(boolean deleteOnFetch) {
		this.deleteOnFetch = deleteOnFetch;
	}

	/**
	 * @return the sender
	 */
	public int getSender() {
		return sender;
	}

	/**
	 * @param sender the sender to set
	 */
	public void setSender(int sender) {
		this.sender = sender;
	}
	
	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}
	
    public String toString(){
        Gson gson = new Gson();
        JsonElement jsonElement = gson.toJsonTree(this);
        JsonObject json = jsonElement.getAsJsonObject();
        return json.toString();

    }

	/**
	 * @return the backupType
	 */
	public int getBackupType() {
		return backupType;
	}

	/**
	 * @param backupType the backupType to set
	 */
	public void setBackupType(int backupType) {
		this.backupType = backupType;
	}

	

}
