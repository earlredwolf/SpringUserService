package ru.ovchinnikov.springuserservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ovchinnikov.springuserservice.model.User;

import java.util.List;

/**

 */
public interface UserRepository extends JpaRepository<User, Long>  {

	/**
	 * Поиск по коду пользователя
	 * @param id  код пользователя
	 * @return Пользователь
	 */
	public User findById( Long id );

	/**
	 * Поиск по нику пользователя
	 * @param nick  ник пользователя
	 * @return список пользователей
	 */
	public List<User> findByNick(String nick);

	/**
	 * Поиск по login пользователя
	 * @param login  логин пользователя
	 * @return список пользователей
	 */
	public List<User> findByLogin(String login);

	/**
	 * Поиск по email пользователя
	 * @param email email пользователя
	 * @return список пользователей
	 */
	public List<User> findByEmail(String email);

}
