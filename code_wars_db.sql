-- phpMyAdmin SQL Dump
-- version 4.4.15.7
-- http://www.phpmyadmin.net
--
-- Хост: 127.0.0.1:3306
-- Время создания: Мар 30 2018 г., 16:33
-- Версия сервера: 5.5.50
-- Версия PHP: 7.0.8

DROP DATABASE IF EXISTS code_wars_db;
CREATE DATABASE code_wars_db;
USE code_wars_db;

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- База данных: `code_wars_db`
--

-- --------------------------------------------------------

--
-- Структура таблицы `artefacts`
--

CREATE TABLE IF NOT EXISTS `artefacts` (
  `id` int(11) NOT NULL,
  `name` varchar(100) UNIQUE NOT NULL,
  `type` varchar(50) NOT NULL,
  `hp_boost` int(11) DEFAULT NULL,
  `mana_boost` int(11) DEFAULT NULL,
  `stamina_boost` int(11) DEFAULT NULL,
  `stamina_regen_boost` int(11) DEFAULT NULL,
  `hp_regen_boost` int(11) DEFAULT NULL,
  `mana_regen_boost` int(11) DEFAULT NULL,
  `attack_boost` int(11) DEFAULT NULL,
  `evasion_boost` int(11) DEFAULT NULL,
  `armor_boost` int(11) DEFAULT NULL,
  `skin` varchar(255) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `artefacts`
--

INSERT INTO `artefacts` (`id`, `name`, `type`, `hp_boost`, `mana_boost`, `stamina_boost`, `hp_regen_boost`, `mana_regen_boost`,`stamina_regen_boost`,attack_boost, `evasion_boost`, `armor_boost`, `skin`) VALUES
(1, 'helmet of despair', 'head', -10, -10, -10, 0, 0, 0, 20, 0, 0, 'fgvdgh'),
(2, 'typical chestplate', 'body', 0, 0, 0, 0, 0, 0, 0, 8, 0, 'fvb'),
(3, 'bracers of haste', 'arms', 0, 0, 0, 0, 0, 50, 0, 25, 0, 'fgvdgh'),
(4, 'stylish leggins', 'legs', 0, 0, 0, 0, 0, 0, 0, 0, 0, 'fvb'),
(5, 'crown of horror', 'head', 20, 0, 0, 0, 0, 0, 15, 2, 0, 'fvb'),
(6, 'pants of bard', 'legs', 1, 1, 1, 1, 1, 1, 0, 50, 0, 'fvb'),
(7, 'jacket', 'arms', 0, 0, 0, 0, 0, -1, 0, 0, 1, 'fvb'),
(8, 'feet', 'arms', 0, 0, 0, 0, 0, 0, 4, 30, 0, 'fvb'),
(9, 'dead pig', 'body', 300, 0, 0, 0, 0, 0, 0, 0, 5, 'fvb');

-- --------------------------------------------------------

--
-- Структура таблицы `artefact_in_set`
--

CREATE TABLE IF NOT EXISTS `artefact_in_set` (
  `id` int(11) NOT NULL,
  `id_set` int(11) NOT NULL,
  `id_artefact` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `artefact_in_set`
--

INSERT INTO `artefact_in_set` (`id`, `id_set`, `id_artefact`) VALUES
(1, 1, 9),
(2, 1, 8),
(3, 1, 1),
(4, 2, 4),
(5, 2, 7),
(6, 3, 2);

-- --------------------------------------------------------

--
-- Структура таблицы `backpack`
--

CREATE TABLE IF NOT EXISTS `backpack` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `artefact_id` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `backpack`
--

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

-- --------------------------------------------------------

--
-- Структура таблицы `battles_type`
--

CREATE TABLE IF NOT EXISTS `battles_type` (
  `id` int(11) NOT NULL,
  `players_count` int(10) NOT NULL,
  `battleground` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Структура таблицы `sets`
--

CREATE TABLE IF NOT EXISTS `sets` (
  `id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `user_id` int(11) NOT NULL,
  `code` text NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `sets`
--

INSERT INTO `sets` (`id`, `name`, `user_id`, `code`) VALUES
(1, 'set_2', 1, 'FighterI enemy =  env.getEnemies().get(0);
return new Tuple<FighterI.Action,FighterI>(FighterI.Action.ATTACK,enemy);
'),
(2, 'set_3', 2, ' FighterI enemy = env.getEnemies().get(0);
        if (enemy.getStance() == FighterI.Stance.FREE) {
            if (self.getStance() == FighterI.Stance.DEFENDING) {
                return new Tuple<FighterI.Action, FighterI>(FighterI.Action.FREE, self);
            } else if (self.getCurMana() >= 75) {
                new Tuple<FighterI.Action, FighterI>(FighterI.Action.FIREBALL, env.getEnemies().get(0));
            }
            return new Tuple<FighterI.Action, FighterI>(FighterI.Action.ATTACK, env.getEnemies().get(0));
        } else if (enemy.getStance() == FighterI.Stance.DEFENDING) {
            return new Tuple<FighterI.Action, FighterI>(FighterI.Action.DEFEND, self);
        }
        return new Tuple<FighterI.Action, FighterI>(FighterI.Action.ATTACK, enemy);'),
(3, 'set_1', 1, 'FighterI enemy = env.getEnemies().get(0);
        if (enemy.getStance() == FighterI.Stance.FREE) {
            return new Tuple<FighterI.Action, FighterI>(FighterI.Action.DEFEND, self);
        } else if (enemy.getStance() == FighterI.Stance.DEFENDING) {
            if (self.getStance() == FighterI.Stance.DEFENDING) {
                return new Tuple<FighterI.Action, FighterI>(FighterI.Action.FREE, self);
            } else if (self.getCurMana() >= 75) {
                new Tuple<FighterI.Action, FighterI>(FighterI.Action.FIREBALL, env.getEnemies().get(0));
            }
            return new Tuple<FighterI.Action, FighterI>(FighterI.Action.ATTACK, env.getEnemies().get(0));

        }
        return new Tuple<FighterI.Action, FighterI>(FighterI.Action.ATTACK,enemy);');

-- --------------------------------------------------------

--
-- Структура таблицы `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `id` int(11) NOT NULL,
  `username` varchar(50) UNIQUE NOT NULL,
  `email` varchar(50) UNIQUE NOT NULL,
  `password` varchar(255) NOT NULL,
  `experience` int(11) NOT NULL DEFAULT '0',
  `level` int(11) NOT NULL DEFAULT '1'
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `users`
--

INSERT INTO `users` (`id`, `username`, `email`, `password`, `experience`, `level`) VALUES
(1, 'username_1', 'email1@mail.com', '1234', 100, 1),
(2, 'username_2', 'emai2@mail.com', '1234', 2000, 1);

INSERT INTO `users` (`id`, `username`, `email`, `password`, `experience`, `level`) VALUES
(3, 'Thor', 'thor@mail.com', 'root', 0, 1),
(4, 'Louis the Musketeer', 'louis@mail.com', 'secret', 0, 1),
(5, 'Leonid the King', 'leonid@mail.com', 'secret', 0, 1);

--
-- Индексы сохранённых таблиц
--

--
-- Индексы таблицы `artefacts`
--
ALTER TABLE `artefacts`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `artefact_in_set`
--
ALTER TABLE `artefact_in_set`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_set` (`id_set`),
  ADD KEY `id_artifact` (`id_artefact`);

--
-- Индексы таблицы `backpack`
--
ALTER TABLE `backpack`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_id` (`user_id`),
  ADD KEY `artefact_id` (`artefact_id`);

--
-- Индексы таблицы `battles_type`
--
ALTER TABLE `battles_type`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `sets`
--
ALTER TABLE `sets`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_id` (`user_id`) USING BTREE;

--
-- Индексы таблицы `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT для сохранённых таблиц
--

--
-- AUTO_INCREMENT для таблицы `artefacts`
--
ALTER TABLE `artefacts`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT для таблицы `artefact_in_set`
--
ALTER TABLE `artefact_in_set`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT для таблицы `backpack`
--
ALTER TABLE `backpack`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT для таблицы `battles_type`
--
ALTER TABLE `battles_type`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT для таблицы `sets`
--
ALTER TABLE `sets`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=10;
--
-- AUTO_INCREMENT для таблицы `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- Ограничения внешнего ключа сохраненных таблиц
--

--
-- Ограничения внешнего ключа таблицы `artefact_in_set`
--
ALTER TABLE `artefact_in_set`
  ADD CONSTRAINT `artefact_in_set_ibfk_1` FOREIGN KEY (`id_set`) REFERENCES `sets` (`id`)  ON DELETE CASCADE,
  ADD CONSTRAINT `artefact_in_set_ibfk_2` FOREIGN KEY (`id_artefact`) REFERENCES `artefacts` (`id`);

--
-- Ограничения внешнего ключа таблицы `backpack`
--
ALTER TABLE `backpack`
  ADD CONSTRAINT `backpack_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)  ON DELETE CASCADE,
  ADD CONSTRAINT `backpack_ibfk_2` FOREIGN KEY (`artefact_id`) REFERENCES `artefacts` (`id`);

--
-- Ограничения внешнего ключа таблицы `sets`
--
ALTER TABLE `sets`
  ADD CONSTRAINT `sets_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;



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
  Ring of Apollo + 10000 healing

-- TODO add code to sets
-- TODO refactor types
-- TODO what is skin?
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
50,
80,
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
30,
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
30,
10,
10,
10,
100,
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
1000,
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
'Wool Cloak',
'torso',
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
(15,
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
(16,
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
(17,
'Hoplite shield',
'left arm',
50,
50,
0,
0,
100,
20,
10,
40,
100,
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
'Laconian boots',
'legs',
50,
0,
100,
20,
20,
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
'Toga of Apollo',
'torso',
70,
30,
50,
50,
100,
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
(10, 'Viking set', 3, 'TODO code'),
(11, 'Musketeer set', 4, 'TODO code'),
(12, 'Spartan set', 5, 'TODO code');

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
(5, 16),
(5, 17),
(5, 18),
(5, 19);

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
(12, 16),
(12, 17),
(12, 18),
(12, 19);
