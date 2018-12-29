package com.example.administrator.myapplication.bean;

import java.io.Serializable;

public class Info implements Serializable {
	private String id;
	private String name;
	private String time;


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public Info(String id, String name, String time) {
		super();
		this.id = id;
		this.name = name;
		this.time = time;
	}

	public Info() {
		super();
	}

	@Override
	public String toString() {
		return "Info [id=" + id + ", name=" + name +
				",time=" + time + "]";
	}

}
