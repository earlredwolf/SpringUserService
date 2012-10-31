package ru.ovchinnikov.springuserservice.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.ovchinnikov.springuserservice.model.User;
import ru.ovchinnikov.springuserservice.model.UserAchievement;
import ru.ovchinnikov.springuserservice.repository.UserRepository;
import ru.ovchinnikov.springuserservice.service.Security;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

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

	@Autowired
	Security security;

	/**
	 * получение пользователя по id
	 * @param id id Пользователя
	 * @return Сущность пользователь в формате xml
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/user/id/{id}.xml",produces = "application/xml")
	@ResponseBody
	public ResponseEntity<User> getUserXML(@PathVariable Long id) {
		User u = service.findById(id);
		if (u != null) return new ResponseEntity<User>(u, HttpStatus.OK);
		else return new ResponseEntity<User>(u, HttpStatus.NO_CONTENT);
	}

	/**
	 * получение пользователя по id
	 * @param id id Пользователя
	 * @return Сущность пользователь в формате json
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/user/id/{id}.json",produces = "application/json")
	@ResponseBody
	public ResponseEntity<User> getUserJSON(@PathVariable Long id) {
		User u = service.findById(id);
		if (u != null) return new ResponseEntity<User>(u, HttpStatus.OK);
		else return new ResponseEntity<User>(u, HttpStatus.NO_CONTENT);
	}


	/**
	 * получение списка пользователей по nick
	 * @param nick nick Пользователя
	 * @return Массив сущностей пользователь в формате xml
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/user/nick/{nick}.xml",produces = "application/xml")
	@ResponseBody
	public ResponseEntity<List<User>> getUserNickXML(@PathVariable String nick) {
		if (nick != null) {
			List<User> result = service.findByNick(nick);
			if (result != null && result.size() > 0) return new ResponseEntity<List<User>>(result, HttpStatus.OK);
			else return new ResponseEntity<List<User>>(result, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<User>>(new ArrayList<User>(), HttpStatus.BAD_REQUEST);
	}

	/**
	 * получение списка пользователей по nick
	 * @param nick nick Пользователя
	 * @return Массив сущностей пользователь в формате json
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/user/nick/{nick}.json",produces = "application/json")
	@ResponseBody
	public ResponseEntity<List<User>> getUserNickJSON(@PathVariable String nick) {
		if (nick != null) {
			List<User> result = service.findByNick(nick);
			if (result != null && result.size() > 0) return new ResponseEntity<List<User>>(result, HttpStatus.OK);
			else return new ResponseEntity<List<User>>(result, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<User>>(new ArrayList<User>(), HttpStatus.BAD_REQUEST);
	}

	/**
	 * получение списка пользователей по login
	 * @param login login Пользователя
	 * @return Массив сущностей пользователь в формате xml
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/user/login/{login}.xml",produces = "application/xml")
	@ResponseBody
	public ResponseEntity<List<User>> getUserLoginXML(@PathVariable String login) {
		if (login != null) {
			List<User> result = service.findByLogin(login);
			if (result != null && result.size() > 0) return new ResponseEntity<List<User>>(result, HttpStatus.OK);
			else return new ResponseEntity<List<User>>(result, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<User>>(new ArrayList<User>(), HttpStatus.BAD_REQUEST);
	}

	/**
	 * получение списка пользователей по login
	 * @param login login Пользователя
	 * @return Массив сущностей пользователь в формате json
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/user/login/{login}.json",produces = "application/json")
	@ResponseBody
	public ResponseEntity<List<User>> getUserLoginJSON(@PathVariable String login) {
		if (login != null) {
			List<User> result = service.findByLogin(login);
			if (result != null && result.size() > 0) return new ResponseEntity<List<User>>(result, HttpStatus.OK);
			else return new ResponseEntity<List<User>>(result, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<User>>(new ArrayList<User>(), HttpStatus.BAD_REQUEST);
	}


	/**
	 * получение списка пользователей по email
	 * @param email email Пользователя
	 * @return Массив сущностей пользователь в формате xml
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/user/email/{email}.xml",produces = "application/xml")
	@ResponseBody
	public ResponseEntity<List<User>> getUserEmailXML(@PathVariable String email) {
		if (email != null) {
			List<User> result = service.findByEmail(email);
			if (result != null && result.size() > 0) return new ResponseEntity<List<User>>(result, HttpStatus.OK);
			else return new ResponseEntity<List<User>>(result, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<User>>(new ArrayList<User>(), HttpStatus.BAD_REQUEST);
	}

	/**
	 * получение списка пользователей по email
	 * @param email email Пользователя
	 * @return Массив сущностей пользователь в формате json
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/user/email/{email}.json",produces = "application/json")
	@ResponseBody
	public ResponseEntity<List<User>> getUserEmailJSON(@PathVariable String email) {
		if (email != null) {
			List<User> result = service.findByEmail(email);
			if (result != null && result.size() > 0) return new ResponseEntity<List<User>>(result, HttpStatus.OK);
			else return new ResponseEntity<List<User>>(result, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<User>>(new ArrayList<User>(), HttpStatus.BAD_REQUEST);
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
	 * @return
	 */
	@RequestMapping(value = "/user/add")
	public ResponseEntity addUser() {
		User u = new User(UUID.randomUUID().toString(), "asdasd", "adasd");
		u.getAchievements().add(new UserAchievement(UUID.randomUUID().toString()));
		service.saveAndFlush(u);
		return new ResponseEntity(HttpStatus.OK);
	}




}
