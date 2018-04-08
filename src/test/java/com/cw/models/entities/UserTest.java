package com.cw.models.entities;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

public class UserTest {

    private static User userComplex, userSimple;

    @BeforeClass
    public static void initTests() {
        userComplex = new User("qwerty", "123456", "qwe@asd.cs", 10, 11);
        List<Set> sets = new LinkedList<>();
        sets.add(new Set("setname", "code:0xdeadbeef", userComplex));
        sets.add(new Set("setname2", "code:0xbeef", userComplex));
        userComplex.setSets(sets);
        List<Artefact> artefacts = new LinkedList<>();
        artefacts.add(new Artefact("artname", "clothes", 10, 10, 10, 10, 10, 10, 10, 10, "Skin"));
        artefacts.add(new Artefact("artname2", "arm", 20, 20, 10, 10, 10, 10, 10, 10, "Skin"));
        userComplex.setUserArtefacts(artefacts);
        userSimple = new User("asdfgh", "asdfgh", "qwe2@asd.cs", 10, 11);
    }

    @Test
    public void verifyUserConstructorForUserSimple() {
        User newUserSimple = new User(userSimple);
        assertEquals(newUserSimple, userSimple);
    }

    @Test
    public void verifyUserConstructorForUserComplex() {
        User newUserComplex = new User(userComplex);
        assertEquals(newUserComplex, userComplex);
    }
}