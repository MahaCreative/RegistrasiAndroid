package com.example.registrasimahasiswa.Model.Login;

import com.google.gson.annotations.SerializedName;

public class LoginData {

	@SerializedName("google_id")
	private Object googleId;

	@SerializedName("nim")
	private long nim;

	@SerializedName("updated_at")
	private Object updatedAt;

	@SerializedName("created_at")
	private Object createdAt;

	@SerializedName("email_verified_at")
	private Object emailVerifiedAt;

	@SerializedName("id")
	private int id;

	@SerializedName("avatar")
	private Object avatar;

	@SerializedName("email")
	private String email;

	@SerializedName("username")
	private String username;

	public void setGoogleId(Object googleId){
		this.googleId = googleId;
	}

	public Object getGoogleId(){
		return googleId;
	}

	public void setNim(long nim){
		this.nim = nim;
	}

	public long getNim(){
		return nim;
	}

	public void setUpdatedAt(Object updatedAt){
		this.updatedAt = updatedAt;
	}

	public Object getUpdatedAt(){
		return updatedAt;
	}

	public void setCreatedAt(Object createdAt){
		this.createdAt = createdAt;
	}

	public Object getCreatedAt(){
		return createdAt;
	}

	public void setEmailVerifiedAt(Object emailVerifiedAt){
		this.emailVerifiedAt = emailVerifiedAt;
	}

	public Object getEmailVerifiedAt(){
		return emailVerifiedAt;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setAvatar(Object avatar){
		this.avatar = avatar;
	}

	public Object getAvatar(){
		return avatar;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
	}

	public void setUsername(String username){
		this.username = username;
	}

	public String getUsername(){
		return username;
	}
}