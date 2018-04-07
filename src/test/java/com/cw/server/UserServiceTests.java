package com.cw.server;

import com.cw.models.db.services.UserServiceI;
import com.cw.models.entities.Artefact;
import com.cw.models.entities.Set;
import com.cw.models.entities.User;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class UserServiceTests {

    @Autowired
    private ApplicationContext ctx;
    @Autowired
    private UserServiceI us;
    private static User userComplex, userSimple;

    @BeforeClass
    public static void initTests() {
        userComplex = new User("qwerty", "12345", "qwe@asd.cs", 10, 11);
        List<Set> sets = new LinkedList<>();
        sets.add(new Set("setname", "code:0xdeadbeef", userComplex));
        userComplex.setSets(sets);
        List<Artefact> artefacts = new LinkedList<>();
        artefacts.add(new Artefact("artname", "clothes", 10, 10, 10, 10, 10, 10, 10, 10, "Skin"));
        userComplex.setUserArtefacts(artefacts);
        userSimple = new User("asdfgh", "12345", "qwe2@asd.cs", 10, 11);
    }

    @Test
    public void verifyExistingUserDeletion() {
        us.addUser(userSimple);
        int id = us.getUserByEmail(userSimple.getEmail()).getId();
        assertTrue( us.deleteUserById(id) );
        User pulledUser = us.getUserById(id);
        assertNull(pulledUser);
    }

    @Test
    public void verifyNonExistingUserDeletion() {
        int id = -1;
        boolean expected = false,
                actual   = us.deleteUserById(id);
        assertEquals(expected, actual);
        User pulledUser = us.getUserById(id);
        assertNull(pulledUser);
    }

    // complex user with sets and artefacts
    @Test
    public void verifyComplexUserCreationIdentity() {
        us.addUser(userComplex);
        User pulledUser = us.getUserByEmail(userComplex.getEmail());
        userComplex.setId(pulledUser.getId()); // refresh user id
        assertEquals(userComplex, us.getUserById(userComplex.getId()));
        assertEquals(userComplex, us.getUserByEmail(userComplex.getEmail()));
        assertEquals(userComplex, us.getUserByUsername(userComplex.getUsername()));
        assertEquals(userComplex, us.getUserByEmailAndPassword(userComplex.getEmail(), userComplex.getPass()));
        assertEquals(userComplex.getUserArtefacts(), us.getUserById(userComplex.getId()).getUserArtefacts());
        assertEquals(userComplex.getSets(), us.getUserById(userComplex.getId()).getSets());
        us.deleteUserById(userComplex.getId());
    }

    // simple user WITH NO sets and artefacts
    @Test
    public void verifySimpleUserCreationIdentity() {
        us.addUser(userSimple);
        User pulledUser = us.getUserByEmail(userSimple.getEmail());
        userSimple.setId(pulledUser.getId()); // refresh user id
        assertEquals(userSimple, us.getUserById(userSimple.getId()));
        assertEquals(userSimple, us.getUserByEmail(userSimple.getEmail()));
        assertEquals(userSimple, us.getUserByUsername(userSimple.getUsername()));
        assertEquals(userSimple, us.getUserByEmailAndPassword(userSimple.getEmail(), userSimple.getPass()));
        assertEquals(userSimple.getUserArtefacts(), us.getUserById(userSimple.getId()).getUserArtefacts());
        assertEquals(userSimple.getSets(), us.getUserById(userSimple.getId()).getSets());
        us.deleteUserById(userSimple.getId());
    }

}
