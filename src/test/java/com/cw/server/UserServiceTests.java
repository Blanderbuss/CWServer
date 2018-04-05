package com.cw.server;

import com.cw.models.db.services.UserServiceI;
import com.cw.models.entities.User;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class UserServiceTests {

    @Autowired
    private ApplicationContext ctx;
    private static User u, u2;

    @BeforeClass
    public static void initTests() {
        u = new User("qwerty", "12345", "qwe@asd.cs", 10, 11);
        u2 = new User("asdfgh", "12345", "qwe2@asd.cs", 10, 11);
    }

    @Test
    public void verifyExistingUserDeletion() {
        UserServiceI us = ctx.getBean(UserServiceI.class);
        us.addUser(u2);
        int id = us.getUserByEmail(u2.getEmail()).getId();
        assertTrue( us.deleteUserById(id) );
        User pulledUser = us.getUserById(id);
        assertNull(pulledUser);
    }

    @Test
    public void verifyNonExistingUserDeletion() {
        UserServiceI us = ctx.getBean(UserServiceI.class);
        int id = -1;
        boolean expected = false,
                actual   = us.deleteUserById(id);
        assertEquals(expected, actual);
        User pulledUser = us.getUserById(id);
        assertNull(pulledUser);
    }

    @Test
    public void verifyUserCreationIdentity() {
        UserServiceI us = ctx.getBean(UserServiceI.class);
        us.addUser(u);
        User pulledUser = us.getUserByEmail(u.getEmail());
        u.setId(pulledUser.getId()); // refresh user id
        assertEquals(u, us.getUserById(u.getId()));
        assertEquals(u, us.getUserByEmail(u.getEmail()));
        assertEquals(u, us.getUserByUsername(u.getUsername()));
        assertEquals(u, us.getUserByEmailAndPassword(u.getEmail(), u.getPass()));
        us.deleteUserById(u.getId());
    }

}
