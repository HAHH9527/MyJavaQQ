/**
 * User�û���
 */
package com.myJavaQQ.common;

public class User implements java.io.Serializable {
	private String user_Number;// �˺�
	private String user_Password;// ����
	private String user_Regdate;// ע������
	private String user_Nickname;// �ǳ�
	private String user_Online;// �Ƿ�����
	private String user_Sex;// �Ա�
	private String user_Birthday;// ����
	private String user_Phone;// �ֻ�
	private String user_Address;// ��ַ
	private String user_Hometown;// ����
	private String user_Other;// ����˵��

	public User() {
	}

	public User(String user_Number, String user_Password) {
		this.user_Number = user_Number;
		this.user_Password = user_Password;
	}

	public User(String user_Number, String user_Password, String user_Nickname) {
		this.user_Number = user_Number;
		this.user_Password = user_Password;
		this.user_Nickname = user_Nickname;
	}

	public String getUser_Address() {
		return user_Address;
	}

	public String getUser_Birthday() {
		return user_Birthday;
	}

	public String getUser_Hometown() {
		return user_Hometown;
	}

	public String getUser_Nickname() {
		return user_Nickname;
	}

	public String getUser_Number() {
		return user_Number;
	}

	public String getUser_Online() {
		return user_Online;
	}

	public String getUser_Other() {
		return user_Other;
	}

	public String getUser_Password() {
		return user_Password;
	}

	public String getUser_Phone() {
		return user_Phone;
	}

	public String getUser_Regdate() {
		return user_Regdate;
	}

	public String getUser_Sex() {
		return user_Sex;
	}

	public void setUser_Address(String user_Address) {
		this.user_Address = user_Address;
	}

	public void setUser_Birthday(String user_Birthday) {
		this.user_Birthday = user_Birthday;
	}

	public void setUser_Hometown(String user_Hometown) {
		this.user_Hometown = user_Hometown;
	}

	public void setUser_Nickname(String user_Nickname) {
		this.user_Nickname = user_Nickname;
	}

	public void setUser_Number(String user_Number) {
		this.user_Number = user_Number;
	}

	public void setUser_Online(String user_Online) {
		this.user_Online = user_Online;
	}

	public void setUser_Other(String user_Other) {
		this.user_Other = user_Other;
	}

	public void setUser_Password(String user_Password) {
		this.user_Password = user_Password;
	}

	public void setUser_Phone(String user_Phone) {
		this.user_Phone = user_Phone;
	}

	public void setUser_Regdate(String user_Regdate) {
		this.user_Regdate = user_Regdate;
	}

	public void setUser_Sex(String user_Sex) {
		this.user_Sex = user_Sex;
	}

}
