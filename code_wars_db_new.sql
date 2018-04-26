
DROP DATABASE IF EXISTS code_wars_db;
CREATE DATABASE code_wars_db;
USE code_wars_db;

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

create table artefact_in_set
(
  id int auto_increment
    primary key,
  id_set int not null,
  id_artefact int not null
)
  engine=InnoDB
;

create index id_set
  on artefact_in_set (id_set)
;

create index id_artifact
  on artefact_in_set (id_artefact)
;

create table artefacts
(
  id int auto_increment
    primary key,
  name varchar(100) not null,
  type varchar(50) not null,
  hp_boost int null,
  mana_boost int null,
  stamina_boost int null,
  stamina_regen_boost int null,
  hp_regen_boost int null,
  mana_regen_boost int null,
  attack_boost int null,
  evasion_boost int null,
  armor_boost int null,
  skin varchar(255) not null,
  constraint name
  unique (name)
)
  engine=InnoDB
;

create table backpack
(
  id int auto_increment
    primary key,
  user_id int not null,
  artefact_id int not null,
  constraint backpack_ibfk_2
  foreign key (artefact_id) references artefacts (id)
)
  engine=InnoDB
;

create index user_id
  on backpack (user_id)
;

create index artefact_id
  on backpack (artefact_id)
;

create table battles_type
(
  id int auto_increment
    primary key,
  players_count int(10) not null,
  battleground varchar(255) not null
)
  engine=InnoDB
;

create table sets
(
  id int not null,
  name varchar(50) not null,
  user_id int not null,
  code text not null
)
  engine=InnoDB
;

create index sets_ibfk_1
  on sets (user_id)
;

create table users
(
  id int auto_increment
    primary key,
  username varchar(50) not null,
  email varchar(50) not null,
  password varchar(255) not null,
  experience int default '0' not null,
  level int default '1' not null,
  constraint username
  unique (username),
  constraint email
  unique (email)
)
  engine=InnoDB
;

alter table backpack
  add constraint backpack_ibfk_1
foreign key (user_id) references users (id)
  on delete cascade
;

alter table sets
  add constraint sets_ibfk_1
foreign key (user_id) references users (id)
  on delete cascade
;

INSERT INTO `users` (`id`, `username`, `email`, `password`, `experience`, `level`) VALUES
  (1, 'username_1', 'email1@mail.com', '1234', 100, 1),
  (2, 'username_2', 'email2@mail.com', '1234', 2000, 1);

INSERT INTO `users` (`id`, `username`, `email`, `password`, `experience`, `level`) VALUES
  (3, 'Thor', 'thor@mail.com', 'root', 0, 1),
  (4, 'Louis the Musketeer', 'louis@mail.com', 'root', 0, 1),
  (5, 'Leonid the King', 'leonid@mail.com', 'root', 0, 1);


INSERT INTO `artefacts` (`id`, `name`, `type`, `hp_boost`, `mana_boost`, `stamina_boost`, `hp_regen_boost`, `mana_regen_boost`,`stamina_regen_boost`,attack_boost, `evasion_boost`, `armor_boost`, `skin`) VALUES
  (1, 'helmet of despair', 'head', -10, -10, -10, 0, 0, 0, 20, 0, 0, 'fgvdgh'),
  (2, 'typical chestplate', 'body', 0, 0, 0, 0, 0, 0, 0, 8, 0, 'fvb'),
  (3, 'bracers of haste', 'two arms', 0, 0, 0, 0, 0, 50, 0, 25, 0, 'fgvdgh'),
  (4, 'stylish leggins', 'legs', 0, 0, 0, 0, 0, 0, 0, 0, 0, 'fvb'),
  (5, 'crown of horror', 'head', 20, 0, 0, 0, 0, 0, 15, 2, 0, 'fvb'),
  (6, 'pants of bard', 'legs', 1, 1, 1, 1, 1, 1, 0, 50, 0, 'fvb'),
  (7, 'jacket', 'two arms', 0, 0, 0, 0, 0, -1, 0, 0, 1, 'fvb'),
  (8, 'feet', 'two arms', 0, 0, 0, 0, 0, 0, 4, 30, 0, 'fvb'),
  (9, 'dead pig', 'body', 30, 0, 0, 0, 0, 0, 0, 0, 5, 'fvb');

INSERT INTO `artefact_in_set` (`id`, `id_set`, `id_artefact`) VALUES
  (1, 1, 9),
  (2, 1, 8),
  (3, 1, 1),
  (4, 2, 4),
  (5, 2, 7),
  (6, 3, 2);

INSERT INTO `backpack` (`id`, `user_id`, `artefact_id`) VALUES
  (1, 1, 1),
  (2, 1, 2),
  (3, 1, 3),
  (4, 1, 4),
  (5, 1, 5),
  (6, 1, 6),
  (7, 1, 7),
  (8, 1, 8),
  (9, 1, 9),
  (10, 2, 1),
  (11, 2, 2);


INSERT INTO `sets` (`id`, `name`, `user_id`, `code`) VALUES
  (1, 'typical_fighter', 1, 'FighterI enemy = env.getEnemies().get(0);
        if (enemy.getCurHp() == enemy.getMaxHp() / 6 && self.getCurHp() == self.getMaxHp() / 2) {
            if (self.getStance() == FighterI.Stance.DEFENDING) {
                return new Tuple<FighterI.Action, FighterI>(FighterI.Action.FREE, self);
            }
            return new Tuple<FighterI.Action, FighterI>(FighterI.Action.FIREBALL, enemy);
        } else if (enemy.getCurHp() == enemy.getMaxHp() / 5) {
            if (self.getStance() == FighterI.Stance.DEFENDING) {
                return new Tuple<FighterI.Action, FighterI>(FighterI.Action.FREE, self);
            }
            return new Tuple<FighterI.Action, FighterI>(FighterI.Action.ATTACK, enemy);
        } else if (self.getCurHp() == self.getMaxHp() / 2) {
            return new Tuple<FighterI.Action, FighterI>(FighterI.Action.DEFEND, self);
        }
        if (self.getStance() == FighterI.Stance.DEFENDING) {
            return new Tuple<FighterI.Action, FighterI>(FighterI.Action.FREE, self);
        }
        return new Tuple<FighterI.Action, FighterI>(FighterI.Action.ATTACK, enemy);
'),
  (2, 'fancy_fighter', 2, 'FighterI enemy = env.getEnemies().get(0);
        if (enemy.getStance() == FighterI.Stance.FREE) {
            if (self.getStance() == FighterI.Stance.DEFENDING) {
                return new Tuple<FighterI.Action, FighterI>(FighterI.Action.FREE, self);
            }
            new Tuple<FighterI.Action, FighterI>(FighterI.Action.FIREBALL, enemy);
        } else if (enemy.getStance() == FighterI.Stance.DEFENDING) {
            if (self.getCurStamina() > self.getMaxStamina() / 3) {
                if (self.getStance() == FighterI.Stance.DEFENDING) {
                    return new Tuple<FighterI.Action, FighterI>(FighterI.Action.FREE, self);
                }
                return new Tuple<FighterI.Action, FighterI>(FighterI.Action.ATTACK, enemy);
            }
            return new Tuple<FighterI.Action, FighterI>(FighterI.Action.DEFEND, self);
        }
        if (self.getStance() == FighterI.Stance.DEFENDING) {
            return new Tuple<FighterI.Action, FighterI>(FighterI.Action.FREE, self);
        }
        if (self.getCurStamina() > self.getMaxStamina() / 3) {
            return new Tuple<FighterI.Action, FighterI>(FighterI.Action.ATTACK, enemy);
        }
        return new Tuple<FighterI.Action, FighterI>(FighterI.Action.DEFEND, self);'),
  (1, 'weak_fighter', 1, 'FighterI enemy = env.getEnemies().get(0);
        if (self.getCurHp() < self.getMaxHp() / 2) return new Tuple<FighterI.Action, FighterI>(FighterI.Action.DEFEND, self);
        if (self.getStance() == FighterI.Stance.DEFENDING) return new Tuple<FighterI.Action, FighterI>(FighterI.Action.FREE, self);
        return new Tuple<FighterI.Action, FighterI>(FighterI.Action.ATTACK, enemy);');





/*
SETS:

Thor
has a
Viking set:
  Nordic iron helmet
  Large sword of Odin
  Leather boots of Valkyrie

Louis the Musketeer
has a
Musketeer set:
  Musket +100000 attack
  Leather Cavalier Hat
  Wool Cloak
  Cavalier Boots

Leonid the King
has a
Spartan set:
  King Leonid's spear
  Hoplite shield
  Laconian boots
  Toga of Apollo + 10000 healing
  Greek helmet

*/

INSERT INTO `code_wars_db`.`artefacts`
(`id`,
 `name`,
 `type`,
 `hp_boost`,
 `mana_boost`,
 `stamina_boost`,
 `stamina_regen_boost`,
 `hp_regen_boost`,
 `mana_regen_boost`,
 `attack_boost`,
 `evasion_boost`,
 `armor_boost`,
 `skin`)
VALUES
  (10,
    'Nordic iron helmet',
    'head',
    0,
    0,
    0,
    0,
    10,
    20,
    0,
    7,
   20,
   'TODO');

INSERT INTO `code_wars_db`.`artefacts`
(`id`,
 `name`,
 `type`,
 `hp_boost`,
 `mana_boost`,
 `stamina_boost`,
 `stamina_regen_boost`,
 `hp_regen_boost`,
 `mana_regen_boost`,
 `attack_boost`,
 `evasion_boost`,
 `armor_boost`,
 `skin`)
VALUES
  (11,
    'Large sword of Odin',
    'two arms',
    0,
    0,
    0,
    0,
    0,
    0,
    100,
    5,
   20,
   'TODO');

INSERT INTO `code_wars_db`.`artefacts`
(`id`,
 `name`,
 `type`,
 `hp_boost`,
 `mana_boost`,
 `stamina_boost`,
 `stamina_regen_boost`,
 `hp_regen_boost`,
 `mana_regen_boost`,
 `attack_boost`,
 `evasion_boost`,
 `armor_boost`,
 `skin`)
VALUES
  (12,
    'Leather boots of Valkyrie',
    'legs',
    0,
    0,
    30,
    15,
    5,
    10,
    10,
    10,
   20,
   'TODO');

INSERT INTO `code_wars_db`.`artefacts`
(`id`,
 `name`,
 `type`,
 `hp_boost`,
 `mana_boost`,
 `stamina_boost`,
 `stamina_regen_boost`,
 `hp_regen_boost`,
 `mana_regen_boost`,
 `attack_boost`,
 `evasion_boost`,
 `armor_boost`,
 `skin`)
VALUES
  (13,
    'Musket',
    'two arms',
    0,
    0,
    0,
    0,
    0,
    0,
    100,
    50,
   0,
   'TODO');

INSERT INTO `code_wars_db`.`artefacts`
(`id`,
 `name`,
 `type`,
 `hp_boost`,
 `mana_boost`,
 `stamina_boost`,
 `stamina_regen_boost`,
 `hp_regen_boost`,
 `mana_regen_boost`,
 `attack_boost`,
 `evasion_boost`,
 `armor_boost`,
 `skin`)
VALUES
  (14,
    'Leather Cavalier Hat',
    'head',
    0,
    0,
    0,
    0,
    0,
    0,
    100,
    50,
   0,
   'TODO');

INSERT INTO `code_wars_db`.`artefacts`
(`id`,
 `name`,
 `type`,
 `hp_boost`,
 `mana_boost`,
 `stamina_boost`,
 `stamina_regen_boost`,
 `hp_regen_boost`,
 `mana_regen_boost`,
 `attack_boost`,
 `evasion_boost`,
 `armor_boost`,
 `skin`)
VALUES
  (15,
    'Wool Cloak',
    'body',
    50,
    30,
    0,
    0,
    0,
    0,
    0,
    50,
   50,
   'TODO');

INSERT INTO `code_wars_db`.`artefacts`
(`id`,
 `name`,
 `type`,
 `hp_boost`,
 `mana_boost`,
 `stamina_boost`,
 `stamina_regen_boost`,
 `hp_regen_boost`,
 `mana_regen_boost`,
 `attack_boost`,
 `evasion_boost`,
 `armor_boost`,
 `skin`)
VALUES
  (16,
    'Cavalier Boots',
    'legs',
    20,
    0,
    50,
    30,
    0,
    0,
    10,
    20,
   50,
   'TODO');

INSERT INTO `code_wars_db`.`artefacts`
(`id`,
 `name`,
 `type`,
 `hp_boost`,
 `mana_boost`,
 `stamina_boost`,
 `stamina_regen_boost`,
 `hp_regen_boost`,
 `mana_regen_boost`,
 `attack_boost`,
 `evasion_boost`,
 `armor_boost`,
 `skin`)
VALUES
  (17,
    'King Leonid\'s spear',
    'right arm',
    0,
    0,
    0,
    0,
    0,
    0,
    100,
    50,
   0,
   'TODO');

INSERT INTO `code_wars_db`.`artefacts`
(`id`,
 `name`,
 `type`,
 `hp_boost`,
 `mana_boost`,
 `stamina_boost`,
 `stamina_regen_boost`,
 `hp_regen_boost`,
 `mana_regen_boost`,
 `attack_boost`,
 `evasion_boost`,
 `armor_boost`,
 `skin`)
VALUES
  (18,
    'Hoplite shield',
    'left arm',
    50,
    50,
    0,
    0,
    10,
    20,
    10,
    40,
   30,
   'TODO');

INSERT INTO `code_wars_db`.`artefacts`
(`id`,
 `name`,
 `type`,
 `hp_boost`,
 `mana_boost`,
 `stamina_boost`,
 `stamina_regen_boost`,
 `hp_regen_boost`,
 `mana_regen_boost`,
 `attack_boost`,
 `evasion_boost`,
 `armor_boost`,
 `skin`)
VALUES
  (19,
    'Laconian boots',
    'legs',
    50,
    0,
    100,
    5,
    5,
    5,
    10,
    40,
   30,
   'TODO');

INSERT INTO `code_wars_db`.`artefacts`
(`id`,
 `name`,
 `type`,
 `hp_boost`,
 `mana_boost`,
 `stamina_boost`,
 `stamina_regen_boost`,
 `hp_regen_boost`,
 `mana_regen_boost`,
 `attack_boost`,
 `evasion_boost`,
 `armor_boost`,
 `skin`)
VALUES
  (20,
    'Toga of Apollo',
    'body',
    70,
    30,
    50,
    5,
    10,
    3,
    0,
    30,
   0,
   'TODO');

INSERT INTO `code_wars_db`.`artefacts`
(`id`,
 `name`,
 `type`,
 `hp_boost`,
 `mana_boost`,
 `stamina_boost`,
 `stamina_regen_boost`,
 `hp_regen_boost`,
 `mana_regen_boost`,
 `attack_boost`,
 `evasion_boost`,
 `armor_boost`,
 `skin`)
VALUES
  (21,
    'Greek helmet',
    'head',
    70,
    30,
    50,
    15,
    8,
    10,
    0,
    30,
   0,
   'TODO');

INSERT INTO `code_wars_db`.`sets`
(`id`,
 `name`,
 `user_id`,
 `code`)
VALUES
  (10, 'Viking set', 3, 'FighterI enemy = env.getEnemies().get(0);
        if ((enemy.getStance() == FighterI.Stance.FREE && enemy.getCurHp() < enemy.getMaxHp() / 4) || (enemy.getStance() == FighterI.Stance.DEFENDING && enemy.getCurHp() < enemy.getMaxHp() / 4)) {
            if (enemy.getStance() == FighterI.Stance.DEFENDING && self.getCurStamina() < 10) {
                return new Tuple<FighterI.Action, FighterI>(FighterI.Action.DEFEND, self);
            }
            if (self.getCurStamina() < 10) {
                return new Tuple<FighterI.Action, FighterI>(FighterI.Action.DEFEND, self);
            }
            if (self.getStance() == FighterI.Stance.DEFENDING) {
                return new Tuple<FighterI.Action, FighterI>(FighterI.Action.FREE, self);
            }
            return new Tuple<FighterI.Action, FighterI>(FighterI.Action.ATTACK, enemy);
        } else if ((enemy.getStance() == FighterI.Stance.FREE && enemy.getCurHp() < enemy.getMaxHp() / 2) || (enemy.getStance() == FighterI.Stance.DEFENDING)) {
            if (enemy.getStance() == FighterI.Stance.DEFENDING && self.getCurStamina() < self.getMaxStamina() / 2) {
                return new Tuple<FighterI.Action, FighterI>(FighterI.Action.DEFEND, self);
            }
            if (self.getStance() == FighterI.Stance.DEFENDING) {
                return new Tuple<FighterI.Action, FighterI>(FighterI.Action.FREE, self);
            }
            if (self.getCurMana() >= 75) {
                return new Tuple<FighterI.Action, FighterI>(FighterI.Action.FIREBALL, enemy);
            }
            if (self.getCurStamina() < 10) {
                return new Tuple<FighterI.Action, FighterI>(FighterI.Action.DEFEND, self);
            }
            if (self.getStance() == FighterI.Stance.DEFENDING) {
                return new Tuple<FighterI.Action, FighterI>(FighterI.Action.FREE, self);
            }
            return new Tuple<FighterI.Action, FighterI>(FighterI.Action.ATTACK, enemy);
        } else if (enemy.getStance() == FighterI.Stance.DEFENDING) {
            return new Tuple<FighterI.Action, FighterI>(FighterI.Action.DEFEND, self);
        }
        if (self.getStance() == FighterI.Stance.DEFENDING) {
            return new Tuple<FighterI.Action, FighterI>(FighterI.Action.FREE, self);
        }
        if (self.getCurMana() >= 75) {
            return new Tuple<FighterI.Action, FighterI>(FighterI.Action.FIREBALL, enemy);
        }
        if (self.getCurStamina() < 10) {
            return new Tuple<FighterI.Action, FighterI>(FighterI.Action.DEFEND, self);
        }
        if (self.getStance() == FighterI.Stance.DEFENDING) {
            return new Tuple<FighterI.Action, FighterI>(FighterI.Action.FREE, self);
        }
        return new Tuple<FighterI.Action, FighterI>(FighterI.Action.ATTACK, enemy);'),
  (11, 'Musketeer set', 4, 'FighterI enemy = env.getEnemies().get(0);
        return new Tuple<FighterI.Action,FighterI>(FighterI.Action.ATTACK, enemy);'),
  (12, 'Spartan set', 5, 'FighterI enemy = env.getEnemies().get(0);
        if (enemy.getStance() == FighterI.Stance.FREE) {
            if (self.getCurStamina() <= 10) {
                return new Tuple<FighterI.Action, FighterI>(FighterI.Action.DEFEND, self);
            }
            if (self.getStance() == FighterI.Stance.DEFENDING) {
                return new Tuple<FighterI.Action, FighterI>(FighterI.Action.FREE, self);
            }
            return new Tuple<FighterI.Action, FighterI>(FighterI.Action.ATTACK, enemy);
        } else if (self.getCurMana() >= 75) {
            return new Tuple<FighterI.Action, FighterI>(FighterI.Action.FIREBALL, enemy);
        }
        if (self.getCurStamina() <= 10) {
            return new Tuple<FighterI.Action, FighterI>(FighterI.Action.DEFEND, self);
        }
        if (self.getStance() == FighterI.Stance.DEFENDING) {
            return new Tuple<FighterI.Action, FighterI>(FighterI.Action.FREE, self);
        }
        return new Tuple<FighterI.Action, FighterI>(FighterI.Action.ATTACK, enemy);');

INSERT INTO `code_wars_db`.`backpack`
(`user_id`,
 `artefact_id`)
VALUES
  (3, 10),
  (3, 11),
  (3, 12),
  (4, 13),
  (4, 14),
  (4, 15),
  (4, 16),
  (5, 17),
  (5, 18),
  (5, 19),
  (5, 20),
  (5, 21);

INSERT INTO `code_wars_db`.`artefact_in_set`
(`id_set`,
 `id_artefact`)
VALUES
  (10, 10),
  (10, 11),
  (10, 12),
  (11, 13),
  (11, 14),
  (11, 15),
  (11, 16),
  (12, 17),
  (12, 18),
  (12, 19),
  (12, 20),
  (12, 21);