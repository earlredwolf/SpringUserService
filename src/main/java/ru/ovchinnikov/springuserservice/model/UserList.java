package ru.ovchinnikov.springuserservice.model;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 Для возврата в xml необходим корневой элемент, внутри которого список пользователей
 */
@XmlRootElement(name = "root")
public class UserList {

	public List<User> userlist;

	public UserList() {
	}

	public UserList(List<User> userlist) {
		this.userlist = userlist;
	}
}
