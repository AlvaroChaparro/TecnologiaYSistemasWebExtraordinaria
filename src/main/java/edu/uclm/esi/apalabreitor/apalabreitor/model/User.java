package edu.uclm.esi.apalabreitor.apalabreitor.model;

import java.io.IOException;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.json.JSONObject;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class User {
	@Id @Column
	private String userName;
	@Column(unique=true)
	private String email;
	@Column @JsonIgnore
	private String pwd;
	@JsonIgnore @Transient
	private WebSocketSession session;
	
	public User() {
	}
		
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	public String getPwd() {
		return pwd;
	}

	public void setWebSocketSession(WebSocketSession session) {
		this.session=session;
	}

	public void sendMessage(String msg) throws IOException {
		WebSocketMessage<?> message = new TextMessage(msg);
		this.session.sendMessage(message);
	}

	public WebSocketSession getSession() {
		return this.session;
	}

	public void sendMessage(ResultadoJugada resultado) throws Exception {
		JSONObject jso = resultado.toJSON();
		this.session.sendMessage(new TextMessage(jso.toString()));
	}
}
