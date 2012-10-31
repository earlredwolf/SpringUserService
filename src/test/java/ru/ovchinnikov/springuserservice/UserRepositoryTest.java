package ru.ovchinnikov.springuserservice;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.ovchinnikov.springuserservice.model.User;
import ru.ovchinnikov.springuserservice.repository.UserRepository;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:/META-INF/config.xml"})
public class UserRepositoryTest {

	@Autowired
	UserRepository repository;


	@Before
	public void setUp() {

		repository.saveAndFlush(new User("dee","ee","ww@ww.ww"));
		repository.saveAndFlush(new User("dee1","ee1","ww@ww.ww1"));
		repository.saveAndFlush(new User("dee2","ee2","ww@ww.ww2"));
		repository.saveAndFlush(new User("dee3","ee3","ww@ww.ww3"));
	}

	@Test
	public void testRepository() {
		assert(repository!=null);
	}

	@Test
	public void testFindById() {
		User u = repository.findById(1L);
		assert(u!=null);
	}

	@Test
	public void testFindByLogin() {
		List<User> u = repository.findByLogin("ee");
		assert(u!=null);
		assert(u.size()>0);
	}

	@Test
	public void testFindByNick() {
		List<User> u = repository.findByNick("dee");
		assert(u!=null);
		assert(u.size()>0);
	}


	@Test
	public void testFindByEmail() {
		List<User> u = repository.findByEmail("ww@ww.ww");
		assert(u!=null);
		assert(u.size()>0);
	}

	@Test
	public void testUpdate() {
		User u = repository.findById(1L);
		u.setLogin("test");
		repository.saveAndFlush(u);
		u = repository.findById(1L);
		assert(u.getLogin().equals("test"));
	}

}
