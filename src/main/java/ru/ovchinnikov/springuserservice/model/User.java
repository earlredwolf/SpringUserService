package ru.ovchinnikov.springuserservice.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Модель пользователя
 */
@Entity
@XmlRootElement(name="user")
public class User implements Serializable {
	//Код пользователя
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	@XmlElement
	private Long id;
	// Ник
	private String nick;
	//Логин
	private String login;
	//эл. почта
	private String email;

	@OneToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	private List<UserAchievement> achievements = new ArrayList<UserAchievement>();

	public User() {

	}

	public User(String nick, String login, String email) {
		this.nick = nick;
		this.login = login;
		this.email = email;
	}

	public Long getId() {
		return id;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<UserAchievement> getAchievements() {
		return achievements;
	}

	public void setAchievements(List<UserAchievement> achievements) {
		this.achievements = achievements;
	}

	public void addAchievement(String name) {
		getAchievements().add(new UserAchievement(name));
	}

	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer();
		sb.append("User");
		sb.append("{id=").append(id);
		sb.append(", nick='").append(nick).append('\'');
		sb.append(", login='").append(login).append('\'');
		sb.append(", email='").append(email).append('\'');
		sb.append(", achievements=").append(achievements);
		sb.append('}');
		return sb.toString();
	}
}
