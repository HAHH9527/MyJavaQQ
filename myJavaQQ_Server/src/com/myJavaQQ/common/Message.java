/**
 * ��Ϣ��
 */
package com.myJavaQQ.common;

public class Message implements java.io.Serializable {
	private String messageType;// ��Ϣ����
	private String sender;// ������
	private String getter;// ������
	private Object info;// ��Ϣ����
	private String sendTime;// ����ʱ��

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
