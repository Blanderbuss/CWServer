package com.cw.server;

import com.cw.exceptions.UserNotFoundException;
import com.cw.models.db.services.ArtefactServiceI;
import com.cw.models.db.services.SetServiceI;
import com.cw.models.db.services.UserServiceI;
import com.cw.models.entities.Artefact;
import com.cw.models.entities.Set;
import com.cw.models.entities.User;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class UserServiceTests {

    @Autowired
    private ApplicationContext ctx;
    @Autowired
    private UserServiceI us;
    @Autowired
    private SetServiceI ss;
    @Autowired
    private ArtefactServiceI as;
    private static User userComplex, userSimple;
    // is meant to be added to userComplex;
    private static List<Artefact> artefacts;
    // is meant to be added to userComplex;
    private static List<Set> sets;

    @BeforeClass
    public static void initTests() {
        userComplex = new User("qwertywerwr", "123456", "qwesadf@asd.cs", 10, 11);
        sets = new LinkedList<>();
        sets.add(new Set("setname", "code:0xdeadbeef", userComplex));
        sets.add(new Set("setname2", "code:0xbeef", userComplex));
        artefacts = new LinkedList<>();
        artefacts.add(new Artefact("artname", "clothes", 10, 10, 10, 10, 10, 10, 10, 10, "Skin"));
        artefacts.add(new Artefact("artname2", "arm", 20, 20, 10, 10, 10, 10, 10, 10, "Skin"));
        userSimple = new User("asdfgh", "asdfgh", "qwe2@asd.cs", 10, 11);
    }

    @After
    public void cleanUpATest() {
        // during user creation an error could happen so that User object's id is not set!
        // it as important to note because User gets deleted by his id
        User userSimplePulled = us.getUserByEmail(userSimple.getEmail());
        if (userSimplePulled != null)
            us.deleteUser(userSimplePulled);
        User userComplexPulled = us.getUserByEmail(userComplex.getEmail());
        if (userComplexPulled != null)
            us.deleteUser(userComplexPulled);
        artefacts.forEach(as::deleteArtefact);
    }

    @Test
    public void verifyExistingUserDeletion() {
        assertTrue( us.addUser(userSimple) );
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
    public void verifyComplexUserCreationIdentity() throws UserNotFoundException {
        assertTrue( us.addUser(userComplex) );
        for (Artefact a : artefacts) {
            assertTrue(as.addArtefact(a));
            assertTrue(as.addArtefactToUserBackpack(a, userComplex));
        }
        for (Set s : sets)
            assertTrue(ss.addSet(s, userComplex));
        as.addArtefactsToSet(sets.get(0), artefacts.stream().map(a -> a.getId()).collect(Collectors.toList()));
        assertEquals(userComplex, us.getUserById(userComplex.getId()));
        assertEquals(userComplex, us.getUserByEmail(userComplex.getEmail()));
        assertEquals(userComplex, us.getUserByUsername(userComplex.getUsername()));
        assertEquals(userComplex, us.getUserByEmailAndPassword(userComplex.getEmail(), userComplex.getPass()));
        assertEquals(userComplex.getUserArtefacts(), us.getUserById(userComplex.getId()).getUserArtefacts());
        assertEquals(userComplex.getSets(), us.getUserById(userComplex.getId()).getSets());
    }

    // simple user WITH NO sets and artefacts
    @Test
    public void verifySimpleUserCreationIdentity() throws UserNotFoundException {
        assertTrue( us.addUser(userSimple) );
        assertEquals(userSimple, us.getUserById(userSimple.getId()));
        assertEquals(userSimple, us.getUserByEmail(userSimple.getEmail()));
        assertEquals(userSimple, us.getUserByUsername(userSimple.getUsername()));
        assertEquals(userSimple, us.getUserByEmailAndPassword(userSimple.getEmail(), userSimple.getPass()));
        assertEquals(userSimple.getUserArtefacts(), us.getUserById(userSimple.getId()).getUserArtefacts());
        assertEquals(userSimple.getSets(), us.getUserById(userSimple.getId()).getSets());
    }

    // should not allow adding users with equal name
    @Test
    public void verifyUserNameUniqueConstraint() {
        assertTrue( us.addUser(userSimple) );
        User anotherUserWithEqualName = new User(userSimple.getUsername(), "anotherPwd", "another@em.ail", 0, 0);
        assertEquals(userSimple.getUsername(), anotherUserWithEqualName.getUsername());
        boolean added = us.addUser(anotherUserWithEqualName);
        if (added) // if added, perform a clean-up
            assertTrue(us.deleteUser(anotherUserWithEqualName));
        assertFalse( added );
    }


    // should not allow adding users with equal email
    @Test
    public void verifyUserEmailUniqueConstraint() {
        assertTrue( us.addUser(userSimple) );

        User anotherUserWithEqualEmail = new User("anotherName", "anotherPwd", userSimple.getEmail(), 0, 0);
        assertEquals(userSimple.getEmail(), anotherUserWithEqualEmail.getEmail());
        boolean added = us.addUser(anotherUserWithEqualEmail);
        if (added) // if added, perform a clean-up
            assertTrue(us.deleteUser(anotherUserWithEqualEmail));
        assertFalse( added );
    }

    // should not allow adding invalid users to database
    @Test
    public void verifyInvalidUserCreation() {
        User[] invalidUsers = {
                new User(),
                new User("a", "b", "c", 0, 0),
                new User( "ww", "asdsf", "valid@em.ail", 0, 0),
                new User( "validName", "validPwd", "invalid@email", 0, 0),
                new User( "validName", "validPwd", "invalid@.email", 0, 0),
                new User( "validName", "validPwd", "invalid@email.", 0, 0),
                new User( "validName", "validPwd", "invalid@emai.l", 0, 0),
                new User( "validName", "validPwd", "@invalidema.il", 0, 0),
                new User( "validName", "validPwd", "invalid@emai.l", 0, 81),
                new User( "thisIsAnInvalidName", "validPwd", "invalid@emai.l", 0, 0),
                new User( "thisIsAnInvalidName", "validPwd", "invalid@emai.l", 0, 0)
        };
        for (User u : invalidUsers) {
            boolean added = us.addUser(u);
            if (added) // clean-up
                assertTrue(us.deleteUser(u));
            assertFalse(added);
        }
    }

    @Test
    public void verifyUserUpdate() {
        assertTrue(false); // TODO
    }

}
