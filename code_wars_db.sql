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
  `evasion_boost` int(11) DEFAULT NULL,
  `armor_boost` int(11) DEFAULT NULL,
  `skin` varchar(255) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `artefacts`
--

INSERT INTO `artefacts` (`id`, `name`, `type`, `hp_boost`, `mana_boost`, `stamina_boost`, `hp_regen_boost`, `mana_regen_boost`, `evasion_boost`, `armor_boost`, `skin`) VALUES
(1, 'art_1', 'type1', 678, 657, 67, 67, 67, 67, 56, 'fgvdgh'),
(2, 'art2', 'type2', 34, 43, 34, 43, 34, 34, 43, 'fvb');

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
(1, 6, 1),
(2, 6, 2);

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
(2, 1, 2);

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
(6, 'set_2', 1, 'code_2'),
(7, 'set_3', 2, 'code_3'),
(9, 'set_1', 1, 'code_1');

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

