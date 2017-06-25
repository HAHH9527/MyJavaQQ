/**
 * 消息包
 */
package com.myJavaQQ.common;

public class Message implements java.io.Serializable {
	private String messageType;// 消息类型
	private String sender;// 发送者
	private String getter;// 接收者
	private Object info;// 消息对象
	private String sendTime;// 发送时间

	public Message() {
	}

	public Message(String messageType) {
		this.messageType = messageType;
	}

	public String getGetter() {
		return getter;
	}

	public Object getInfo() {
		return info;
	}

	public String getMessageType() {
		return messageType;
	}

	public String getSender() {
		return sender;
	}

	public String getSendTime() {
		return sendTime;
	}

	public void setGetter(String getter) {
		this.getter = getter;
	}

	public void setInfo(Object info) {
		this.info = info;
	}

	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public void setSenderTime(String sendTime) {
		this.sendTime = sendTime;
	}

}
