package com.bean;

public class BaseObject {

	private final long id;
	private final String content;

	public BaseObject(long id, String content) {
		this.id = id;
		this.content = content;
	}

	public long getId() {
		return id;
	}

	public String getContent() {
		return content;
	}

}
