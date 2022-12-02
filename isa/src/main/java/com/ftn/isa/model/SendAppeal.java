package com.ftn.isa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="SendAppeal")
public class SendAppeal {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique=true, nullable = false)
	private Long id;
	
	@Column(name = "toUsername", nullable = false)
	private String toUsername;
	
	@Column(name = "subject", nullable = false)
	private String subject;
	
	@Column(name = "text", nullable = false)
	private String text;
	
	public SendAppeal() {}

	public SendAppeal(Long id, String toUsername, String subject, String text) {
		super();
		this.id = id;
		this.toUsername = toUsername;
		this.subject = subject;
		this.text = text;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getToUsername() {
		return toUsername;
	}

	public void setToUsername(String toUsername) {
		this.toUsername = toUsername;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	};

}
