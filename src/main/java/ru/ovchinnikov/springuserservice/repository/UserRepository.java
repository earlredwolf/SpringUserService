package ru.ovchinnikov.springuserservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ovchinnikov.springuserservice.model.User;

import java.util.List;

/**

 */
public interface UserRepository extends JpaRepository<User, Long>  {

	public User findById( Long id );

	public List<User> findByNick(String Nick);

	public List<User> findByLogin(String login);

	public List<User> findByEmail(String email);

}
