/**
 * 
 */
package com.incl.content.schedular.bean;

import java.io.Serializable;

/**
 * @author prasanth
 *
 */
public final class JobMessage implements Serializable{

    private String text;
    private int priority;
    private boolean secret;

    // Default constructor is needed to deserialize JSON
    public JobMessage() {
    }

    public JobMessage(String text, int priority, boolean secret) {
        this.text = text;
        this.priority = priority;
        this.secret = secret;
    }

    public String getText() {
        return text;
    }

    public int getPriority() {
        return priority;
    }

    public boolean isSecret() {
        return secret;
    }

    @Override
    public String toString() {
        return "CustomMessage{" +
                "text='" + text + '\'' +
                ", priority=" + priority +
                ", secret=" + secret +
                '}';
    }
}
