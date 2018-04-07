package com.cw.models.entities;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class User implements Serializable {
    @NotNull
    private int id;

    @NotNull
    @Pattern(regexp = "[a-zA-Z0-9_-]{3,15}")
    private String username;
    @NotNull
    @Pattern(regexp = "[a-zA-Z0-9_.%+-]{6,30}")
    private String pass;
    @NotNull
    @Pattern(regexp = "[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,}")
    private String email;
    @NotNull
    private int experience;
    @NotNull
    @Max(value = 80)
    private int lvl;

    List<Set> sets = new LinkedList<>();

    List<Artefact> userArtefacts = new LinkedList<>();

    public User() {
    }

    public User(String username, String pass, String email, int experience, int lvl) {
        this.username = username;
        this.pass = pass;
        this.email = email;
        this.experience = experience;
        this.lvl = lvl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public int getLvl() {
        return lvl;
    }

    public void setLvl(int lvl) {
        this.lvl = lvl;
    }

    public List<Set> getSets() {
        return sets;
    }

    public void setSets(List<Set> sets) {
        this.sets = sets;
    }

    public List<Artefact> getUserArtefacts() {
        return userArtefacts;
    }

    public void setUserArtefacts(List<Artefact> userArtefacts) {
        this.userArtefacts = userArtefacts;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", pass='" + pass + '\'' +
                ", email='" + email + '\'' +
                ", experience=" + experience +
                ", lvl=" + lvl +
                ", sets=" + sets +
                ", userArtefacts=" + userArtefacts +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                experience == user.experience &&
                lvl == user.lvl &&
                Objects.equals(username, user.username) &&
                Objects.equals(pass, user.pass) &&
                Objects.equals(email, user.email) &&
                Objects.equals(sets, user.sets) &&
                Objects.equals(userArtefacts, user.userArtefacts);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, username, pass, email, experience, lvl, sets, userArtefacts);
    }
}
