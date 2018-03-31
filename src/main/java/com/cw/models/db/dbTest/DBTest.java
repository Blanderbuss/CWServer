package com.cw.models.db.dbTest;

import com.cw.models.db.services.impl.ArtefactService;
import com.cw.models.db.services.impl.BattleTypeService;
import com.cw.models.db.services.impl.SetService;
import com.cw.models.db.services.impl.UserService;
import com.cw.models.entities.Artefact;
import com.cw.models.entities.BattleType;
import com.cw.models.entities.Set;
import com.cw.models.entities.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Макс on 31.03.2018.
 */
public class DBTest {
    public static void main(String[] args) {
        UserService userService = new UserService();
        ArtefactService artefactService = new ArtefactService();
        SetService setService = new SetService();
        BattleTypeService battleTypeService = new BattleTypeService();

        System.out.println("User Tests");
        // User Tests
//        User user1 = new User("username_3", "secret", "email3@mail.com", 100, 1);
        // Adding user
//        userService.addUser(user1);

        // Get User By ID
        System.out.println("Get User By ID");
        User userById_1 = userService.getUserById(1);
        System.out.println(userById_1);

        // Get User By Username
        System.out.println("Get User By Username");
        User userByUsername_1 = userService.getUserByUsername("username_3");
        System.out.println(userByUsername_1);

        // Get User By Email
        System.out.println("Get User By Email");
        User userByEmail_1 = userService.getUserByEmail("email3@mail.com");
        System.out.println(userByEmail_1);

        // Get User By Email And Password
        System.out.println("Get User By Email And Password (email=true,pass=true)");
        User userByEmailAndPassword_1 = userService.getUserByEmailAndPassword("email3@mail.com", "secret");
        System.out.println(userByEmailAndPassword_1);
        System.out.println("Get User By Email And Password (email=false,pass=true)");
        User userByEmailAndPassword_2 = userService.getUserByEmailAndPassword("email3_@mail.com", "secret");
        System.out.println(userByEmailAndPassword_2);
        System.out.println("Get User By Email And Password (email=false,pass=false)");
        User userByEmailAndPassword_3 = userService.getUserByEmailAndPassword("email3@mail.com", "not_secret");
        System.out.println(userByEmailAndPassword_3);
        System.out.println("Get User By Email And Password (email=false,pass=false)");
        User userByEmailAndPassword_4 = userService.getUserByEmailAndPassword("email3_@mail.com", "not_secret");
        System.out.println(userByEmailAndPassword_4);

        // Update User
        System.out.println("Update User");
        userById_1.setUsername("username_1:New USERNAME");
        userService.updateUser(userById_1);
        System.out.println(userService.getUserById(userById_1.getId()));

        // Delete User
        System.out.println("Delete User");
        User userForDelete_1 = new User("username_delete_1", "secret", "email_delete_1@mail.com", 100, 1);
        User userForDelete_2 = new User("username_delete_2", "secret", "email_delete_2@mail.com", 100, 1);

        userService.addUser(userForDelete_1);
        userService.addUser(userForDelete_2);

        System.out.println(userService.deleteUser(userForDelete_1));
        System.out.println(userService.deleteUserById(userForDelete_2.getId()));

        System.out.println();

        System.out.println("Sets Tests");

        // Add Set
//        System.out.println("Add Sets");
//        Set userWithId_1Set_1 = new Set("name_1", "code_1");
//        Set userWithId_1Set_2 = new Set("name_2", "code_2");
//        User userWithId1 = userService.getUserById(1);
        // if second parameter is userId then method returns updated User
//        userWithId1 = setService.addSet(userWithId_1Set_1, userWithId1.getId());
//        if second parameter is user object then method returns boolean and update this user object
//        setService.addSet(userWithId_1Set_2, userWithId1);
//        System.out.println("userWithId1 Sets: ");
//        System.out.println(userWithId1.getSets());

        // Get Set By ID
        System.out.println("Get Set By ID");
        Set setById = setService.getSetById(6);
        System.out.println(setById);

        // Get Sets By User Id
        System.out.println("Get Sets By User Id");
        List<Set> setsByUserId = setService.getAllSetsByUserId(1);
        System.out.println(setsByUserId);

        // Update Set
        System.out.println("Update Set");
        Set setForUpdate = setService.getSetById(6);
        setForUpdate.setCode("System.out.println(\"Updated\");");
        setService.updateSet(setForUpdate);

        // Delete Set
        System.out.println("Delete Set");
        Set setForDelete_1 = new Set("setForDelete_1", "code");
        Set setForDelete_2 = new Set("setForDelete_2", "code");
        User userForSetDelete = userService.getUserById(1);
        setService.addSet(setForDelete_1, userForSetDelete);
        setService.addSet(setForDelete_2, userForSetDelete);

        System.out.println("userForSetDelete Sets:");
        System.out.println(userForSetDelete.getSets());

        setService.deleteSetById(setForDelete_1.getId());
        setService.deleteSet(setForDelete_2);

        User userForSetDeleteUpdated = userService.getUserById(1);
        System.out.println("userForSetDeleteUpdated Sets:");
        System.out.println(userForSetDeleteUpdated.getSets());


        System.out.println("Artefacts Tests");

//         Add artefact
//        System.out.println("Add artefact");
//        Artefact artefact_1 = new Artefact("Name_1", "Type_1", 10, 10, 10, 10, 10, 10, 10, 10, "Skin");
//        artefactService.addArtefact(artefact_1);
//        System.out.println(artefact_1);
//         Add artefact to User backpack
//        System.out.println("Add Artefact To User BackPack");
//        Artefact artefactForAddingToUserWithId1Backpack = artefactService.getArtefactById(1);
//        User userForArtefactAddingToUserWithId1Backpack = artefactService.addArtefactToUserBackpack(artefactForAddingToUserWithId1Backpack, 1);
//        System.out.println(userForArtefactAddingToUserWithId1Backpack.getUserArtefacts());

        // Add Artifact To Set
//        System.out.println("Add Artifact To Set");
//        Artefact artefactForAddingToUserWithId1Set = artefactService.getArtefactById(1);
//        Set set1 = setService.getSetById(6);
//        set1 = artefactService.addArtefactToSet(set1.getId(), artefactForAddingToUserWithId1Set);
//        System.out.println("Set1 artefacts: ");
//        System.out.println(set1.getArtefacts());
//        artefactService.addArtefactToSet(set1, artefactForAddingToUserWithId1Set);
//        System.out.println("Set1 artefacts: ");
//        System.out.println(set1.getArtefacts());

        // Add artifacts to set
//        System.out.println("Add artifacts to set");
//        Set setForAddingArtifacts = setService.getSetById(7);
//        System.out.println("setForAddingArtifacts Artefacts:");
//        System.out.println(setForAddingArtifacts.getArtefacts());
//        List<Integer> artefactsIds = new ArrayList<>();
//        artefactsIds.add(1);
//        artefactsIds.add(2);
//        artefactService.addArtefactsToSet(setForAddingArtifacts, artefactsIds);
//        Set setForAddingArtifactsUpdated = setService.getSetById(7);
//        System.out.println("setForAddingArtifactsUpdated Artefacts:");
//        System.out.println(setForAddingArtifactsUpdated.getArtefacts());

        // Get Artefact
        System.out.println("Get Artefact By ID");
        Artefact artefactByID_1 = artefactService.getArtefactById(1);
        System.out.println(artefactByID_1);

        // Get All Artefact By Set Id
        System.out.println("Get All Artefact By Set Id");
        List<Artefact> artefactsBySetId = artefactService.getAllArtefactsBySetId(7);
        System.out.println(artefactsBySetId);

        // Get All Artefact By User Id
        System.out.println("Get All Artefact By User Id");
        List<Artefact> artefactsByUserId = artefactService.getAllArtefactByUserId(1);
        System.out.println(artefactsByUserId);

        // Update Artefact
        System.out.println("Update Artefact");
        Artefact artefactForUpdate = artefactService.getArtefactById(1);
        artefactForUpdate.setName("art_1:Updated");
        artefactService.updateArtefact(artefactForUpdate);
        System.out.println("Updated: ");
        System.out.println(artefactForUpdate);

        // Delete Artefact
        Artefact artefactForDelete_1 = new Artefact("Name_ForDelete_1", "Type_1", 10, 10, 10, 10, 10, 10, 10, 10, "Skin");
        Artefact artefactForDelete_2 = new Artefact("Name_ForDelete_1", "Type_1", 10, 10, 10, 10, 10, 10, 10, 10, "Skin");

        artefactService.addArtefact(artefactForDelete_1);
        artefactService.addArtefact(artefactForDelete_2);

        System.out.println(artefactService.deleteArtefact(artefactForDelete_1));
        System.out.println(artefactService.deleteArtefactById(artefactForDelete_2.getId()));

        System.out.println();

        // Battletype Tests
        System.out.println("Battletype Tests");

        // Add Battletype
        System.out.println("Add Battletype");
        BattleType battleType = new BattleType(10, "bg2");
        battleTypeService.addBattleType(battleType);
        System.out.println(battleType);

        // Get Battletype
        System.out.println("Get Battletype");
        BattleType battleTypeGetById = battleTypeService.getBattleTypeById(1);
        System.out.println(battleTypeGetById);

        // Update Battletype
        System.out.println("Update Battletype");
        BattleType battleTypeGetByIdForUpdate = battleTypeService.getBattleTypeById(1);
        battleTypeGetByIdForUpdate.setBattleground("New BG");
        battleTypeService.updateBattleType(battleTypeGetByIdForUpdate);
        System.out.println(battleTypeGetByIdForUpdate);

        // Delete Battletype
        BattleType battleTypeForDelete_1 = new BattleType(10, "bg");
        BattleType battleTypeForDelete_2 = new BattleType(10, "bg");

        battleTypeService.addBattleType(battleTypeForDelete_1);
        battleTypeService.addBattleType(battleTypeForDelete_2);

        System.out.println(battleTypeService.deleteBattleType(battleTypeForDelete_1));
        System.out.println(battleTypeService.deleteBattleTypeById(battleTypeForDelete_2.getId()));

        System.out.println();
        userService.closeConnection();
    }
}
