package ru.ovchinnikov.springuserservice.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@Entity
@XmlRootElement(name="userachievement")
public class UserAchievement implements Serializable {


	//Код
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	@XmlElement
	private Long id;

	// Достижение
	private String achievement;

	public UserAchievement () {

	}
	public UserAchievement(String achievement) {
		this.achievement = achievement;
	}

	public Long getId() {
		return id;
	}

	public String getAchievement() {
		return achievement;
	}

	public void setAchievement(String achievement) {
		this.achievement = achievement;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("UserAchievement");
		sb.append("{id=").append(id);
		sb.append(", achievement='").append(achievement).append('\'');
		sb.append('}');
		return sb.toString();
	}
}
