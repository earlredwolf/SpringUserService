package ru.ovchinnikov.springuserservice.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.ovchinnikov.springuserservice.model.User;
import ru.ovchinnikov.springuserservice.model.UserAchievement;
import ru.ovchinnikov.springuserservice.model.UserList;
import ru.ovchinnikov.springuserservice.repository.UserRepository;

import java.util.List;
import java.util.UUID;

/**
 * Контроллер api доступа к информации о пользователе
 * Все методы вместе с данным возвращают статус
 * HttpStatus.OK если все прошло хорошо и небыло ошибок
 * HttpStatus.NO_CONTENT если нечего не нашлось
 * HttpStatus.BAD_REQUEST если параметры null
 *
 */
@Controller
public class UserController {

	@Autowired
	UserRepository service;


	/**
	 * получение пользователя по id
	 * @param id id Пользователя
	 * @return Сущность пользователь
	 */
	private ResponseEntity<User> getUser(Long id) {
		User u = service.findById(id);
		if (u != null) return new ResponseEntity<User>(u, HttpStatus.OK);
		else return new ResponseEntity<User>(u, HttpStatus.NO_CONTENT);
	}

	/**
	 * получение пользователя по id
	 * @param id id Пользователя
	 * @return Сущность пользователь в формате xml
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/user/id/{id}/xml",produces = "application/xml")
	@ResponseBody
	public ResponseEntity<User> getUserXML(@PathVariable Long id) {
		return getUser(id);
	}

	/**
	 * получение пользователя по id
	 * @param id id Пользователя
	 * @return Сущность пользователь в формате json
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/user/id/{id}/json",produces = "application/json")
	@ResponseBody
	public ResponseEntity<User> getUserJSON(@PathVariable Long id) {
		return getUser(id);
	}



	/**
	 * получение пользователя по nick
	 * @param nick nick Пользователя
	 * @return Список пользователей
	 */
	private ResponseEntity<UserList> getUserNick(String nick) {
		if (nick != null) {
			List<User> result = service.findByNick(nick);
			if (result != null && result.size() > 0) return new ResponseEntity<UserList>(new UserList(result), HttpStatus.OK);
			else return new ResponseEntity<UserList>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<UserList>(HttpStatus.BAD_REQUEST);
	}


	/**
	 * получение пользователя по nick
	 * @param nick nick Пользователя
	 * @return Список пользователей в формате xml
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/user/nick/{nick}/xml",produces = "application/xml")
	public @ResponseBody ResponseEntity<UserList> getUserNickXML(@PathVariable String nick) {
		return getUserNick(nick);
	}

	/**
	 * получение пользователя по nick
	 * @param nick nick Пользователя
	 * @return Сущность пользователь в формате xml
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/user/nick/{nick}/json",produces = "application/json")
	@ResponseBody
	public ResponseEntity<UserList> getUserNickJSON(@PathVariable String nick) {
		return getUserNick(nick);
	}


	/**
	 * получение пользователя по login
	 * @param login login Пользователя
	 * @return Список пользователей
	 */
	private ResponseEntity<UserList> getUserLogin(String login) {
		if (login != null) {
			List<User> result = service.findByLogin(login);
			if (result != null && result.size() > 0) return new ResponseEntity<UserList>(new UserList(result), HttpStatus.OK);
			else return new ResponseEntity<UserList>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<UserList>(HttpStatus.BAD_REQUEST);
	}

	/**
	 * получение пользователя по login
	 * @param login login Пользователя
	 * @return Список пользователей в формате xml
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/user/login/{login}/xml",produces = "application/xml")
	@ResponseBody
	public ResponseEntity<UserList> getUserLoginXML(@PathVariable String login) {
		return getUserLogin(login);
	}

	/**
	 * получение списка пользователей по login
	 * @param login login Пользователя
	 * @return Список пользователей в формате json
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/user/login/{login}/json",produces = "application/json")
	@ResponseBody
	public ResponseEntity<UserList> getUserLoginJSON(@PathVariable String login) {
		return getUserLogin(login);
	}


	/**
	 * получение списка пользователей по email
	 * @param email email Пользователя
	 * @return Список пользователей
	 */
	private ResponseEntity<UserList> getUserEmail(String email) {
		if (email != null) {
			List<User> result = service.findByEmail(email);
			if (result != null && result.size() > 0) return new ResponseEntity<UserList>(new UserList(result), HttpStatus.OK);
			else return new ResponseEntity<UserList>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<UserList>(HttpStatus.BAD_REQUEST);
	}

	/**
	 * получение списка пользователей по email
	 * @param email email Пользователя
	 * @return Список пользователей  в формате xml
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/user/email/{email}/xml",produces = "application/xml")
	@ResponseBody
	public ResponseEntity<UserList> getUserEmailXML(@PathVariable String email) {
		return getUserEmail(email);
	}

	/**
	 * получение списка пользователей по email
	 * @param email email Пользователя
	 * @return Список пользователей в формате json
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/user/email/{email}/json",produces = "application/json")
	@ResponseBody
	public ResponseEntity<UserList> getUserEmailJSON(@PathVariable String email) {
		return getUserEmail(email);
	}


	/**
	 * обновление пользователя (изменение ника, email), POST-запрос
	 * @param update указывает какой поле обновить (nick , email)
	 * @param id id Пользователя
	 * @param value Новое значение
	 * @return HttpStatus.OK если все прошло удачно , иначе HttpStatus.BAD_REQUEST
	 */
	@RequestMapping(value = "/user/update", method = RequestMethod.POST)
	public ResponseEntity updateUser(@RequestParam String update, @RequestParam Long id, @RequestParam String value) {
		if (update != null && id != null && value != null) {
			User u = service.findById(id);
			if (u != null) {
				if (update.equalsIgnoreCase("nick")) {
					u.setNick(value);
				}
				if (update.equalsIgnoreCase("email")) {
					u.setEmail(value);
				}
				service.saveAndFlush(u);
				return new ResponseEntity(HttpStatus.OK);
			}
		}
		return new ResponseEntity(HttpStatus.BAD_REQUEST);
	}

	/**
	 * установка достижения
	 * @param id  код пользователя
	 * @param achievement достижение
	 * @return  HttpStatus.OK если все прошло удачно , иначе HttpStatus.BAD_REQUEST
	 */
	@RequestMapping(value = "/user/achievement/add", method = RequestMethod.POST)
	public ResponseEntity updateAchievement(@RequestParam Long id, @RequestParam String achievement) {
		if (id != null && achievement != null) {
			User u = service.findById(id);
			if (u != null) {
				u.addAchievement(achievement);
				service.saveAndFlush(u);
				return new ResponseEntity(HttpStatus.OK);
			}
		}
		return new ResponseEntity(HttpStatus.BAD_REQUEST);
	}


	/**
	 * Метод для создание пользователя т/к/ база пустая
	 * @return все ок
	 */
	@RequestMapping(value = "/user/add")
	public ResponseEntity addUser() {
		User u = new User(UUID.randomUUID().toString(), "asdasd", "q@w.r");
		u.getAchievements().add(new UserAchievement(UUID.randomUUID().toString()));
		service.saveAndFlush(u);
		return new ResponseEntity(HttpStatus.OK);
	}




}
