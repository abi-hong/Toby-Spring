package org.example;

import org.example.user.dao.DaoFactory;
import org.example.user.dao.UserDao;
import org.example.user.domain.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;

import static org.hamcrest.MatcherAssert.assertThat;
import static sun.nio.cs.Surrogate.is;

public class UserDaoTest {

    private UserDao dao;
    private User user1;
    private User user2;
    private User user3;


    @Before
    public void setUp() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
        UserDao dao = context.getBean("UserDao", UserDao.class);

        this.user1 = new User("id1", "연정1", "password1");
        this.user2 = new User("id2", "연정2", "password2");
        this.user3 = new User("id3", "연정3", "password3");

    }

    @Test
    public void addAndGet() throws SQLException, ClassNotFoundException {
        dao.deleteAll();
        assertThat(dao.getCount(), is(0));

        dao.add(user1);
        dao.add(user2);
        assertThat(dao.getCount(), is(2));

        User userGet1 = dao.get(user1.getId());
        assertThat(userGet1.getName(), is(Integer.parseInt(user1.getName())));
        assertThat(userGet1.getPassword(), is(Integer.parseInt(user1.getPassword())));

        User userGet2 = dao.get(user2.getId());
        assertThat(userGet2.getName(), is(Integer.parseInt(user2.getName())));
        assertThat(userGet2.getPassword(), is(Integer.parseInt(user2.getPassword())));
    }

    @Test
    public void count() throws SQLException, ClassNotFoundException {
        dao.deleteAll();
        assertThat(dao.getCount(), is(0));

        dao.add(user1);
        assertThat(dao.getCount(), is(1));

        dao.add(user2);
        assertThat(dao.getCount(), is(2));

        dao.add(user3);
        assertThat(dao.getCount(), is(3));
    }

    @Test(expected = EmptyResultDataAccessException.class)
    public void getUserFailure() throws SQLException, ClassNotFoundException {
        dao.deleteAll();
        assertThat(dao.getCount(), is(0));

        dao.get("unknown_id");
    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        JUnitCore.main("org.example.UserDaoTest");
    }
}