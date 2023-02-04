-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Хост: 127.0.0.1:3306
-- Время создания: Ноя 02 2022 г., 09:15
-- Версия сервера: 5.6.51
-- Версия PHP: 7.2.34

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- База данных: `spring_web_blog`
--

-- --------------------------------------------------------

--
-- Структура таблицы `candidate_info`
--

CREATE TABLE `candidate_info` (
  `id` bigint(20) NOT NULL,
  `submission_date` date DEFAULT NULL,
  `target_department_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Дамп данных таблицы `candidate_info`
--

INSERT INTO `candidate_info` (`id`, `submission_date`, `target_department_id`, `user_id`) VALUES
(1, '2022-10-31', 2, 1),
(2, '2022-11-01', 2, 2);

-- --------------------------------------------------------

--
-- Структура таблицы `department`
--

CREATE TABLE `department` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Дамп данных таблицы `department`
--

INSERT INTO `department` (`id`, `name`) VALUES
(2, 'Программист'),
(3, 'Веб разработка');

-- --------------------------------------------------------

--
-- Структура таблицы `document`
--

CREATE TABLE `document` (
  `id` bigint(20) NOT NULL,
  `archive_date` date DEFAULT NULL,
  `date` date DEFAULT NULL,
  `file_name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Дамп данных таблицы `document`
--

INSERT INTO `document` (`id`, `archive_date`, `date`, `file_name`, `user_id`) VALUES
(1, '2022-11-24', '2022-10-31', 'Заявка в военком', 1);

-- --------------------------------------------------------

--
-- Структура таблицы `education_institution`
--

CREATE TABLE `education_institution` (
  `id` bigint(20) NOT NULL,
  `address` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `instname` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Дамп данных таблицы `education_institution`
--

INSERT INTO `education_institution` (`id`, `address`, `name`, `instname`) VALUES
(1, 'Кукуево улица Потеряшка дом 42', 'МПТ', NULL);

-- --------------------------------------------------------

--
-- Структура таблицы `genre`
--

CREATE TABLE `genre` (
  `id` bigint(20) NOT NULL,
  `name` varchar(70) COLLATE utf8mb4_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Дамп данных таблицы `genre`
--

INSERT INTO `genre` (`id`, `name`) VALUES
(1, 'тооо');

-- --------------------------------------------------------

--
-- Структура таблицы `genres_movie`
--

CREATE TABLE `genres_movie` (
  `genre_id` bigint(20) NOT NULL,
  `movie_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Дамп данных таблицы `genres_movie`
--

INSERT INTO `genres_movie` (`genre_id`, `movie_id`) VALUES
(1, 1);

-- --------------------------------------------------------

--
-- Структура таблицы `groups`
--

CREATE TABLE `groups` (
  `id` bigint(20) NOT NULL,
  `preparation_program_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Дамп данных таблицы `groups`
--

INSERT INTO `groups` (`id`, `preparation_program_id`, `user_id`) VALUES
(3, 7, 1),
(4, 8, 6);

-- --------------------------------------------------------

--
-- Структура таблицы `hibernate_sequence`
--

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Дамп данных таблицы `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(2);

-- --------------------------------------------------------

--
-- Структура таблицы `movies`
--

CREATE TABLE `movies` (
  `id` bigint(20) NOT NULL,
  `comments_amount` int(11) NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `rating` double NOT NULL,
  `release_date` date NOT NULL,
  `series_amount` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Дамп данных таблицы `movies`
--

INSERT INTO `movies` (`id`, `comments_amount`, `name`, `rating`, `release_date`, `series_amount`) VALUES
(1, 0, 'm', 0, '2022-10-12', 0);

-- --------------------------------------------------------

--
-- Структура таблицы `personal_info`
--

CREATE TABLE `personal_info` (
  `id` bigint(20) NOT NULL,
  `birthdate` date DEFAULT NULL,
  `is_male` bit(1) NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `pass_id` int(11) NOT NULL,
  `sec_name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `surname` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Дамп данных таблицы `personal_info`
--

INSERT INTO `personal_info` (`id`, `birthdate`, `is_male`, `name`, `pass_id`, `sec_name`, `surname`, `user_id`) VALUES
(1, '2004-01-23', b'1', 'Dan', 4224, '', 'Hlud', NULL);

-- --------------------------------------------------------

--
-- Структура таблицы `post`
--

CREATE TABLE `post` (
  `id` bigint(20) NOT NULL,
  `anons` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `full_text` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `title` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `views` int(11) NOT NULL,
  `movie_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Дамп данных таблицы `post`
--

INSERT INTO `post` (`id`, `anons`, `full_text`, `title`, `views`, `movie_id`, `user_id`) VALUES
(1, 'ввв', 'в', 'вв', 0, 1, 1);

-- --------------------------------------------------------

--
-- Структура таблицы `preparation_program`
--

CREATE TABLE `preparation_program` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Дамп данных таблицы `preparation_program`
--

INSERT INTO `preparation_program` (`id`, `name`) VALUES
(7, 'Программист'),
(8, 'Веб дизайн');

-- --------------------------------------------------------

--
-- Структура таблицы `prep_to_subject`
--

CREATE TABLE `prep_to_subject` (
  `subject_id` bigint(20) NOT NULL,
  `preparation_program_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Дамп данных таблицы `prep_to_subject`
--

INSERT INTO `prep_to_subject` (`subject_id`, `preparation_program_id`) VALUES
(1, 7),
(2, 7),
(2, 8),
(3, 8);

-- --------------------------------------------------------

--
-- Структура таблицы `subject`
--

CREATE TABLE `subject` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `subject_code` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Дамп данных таблицы `subject`
--

INSERT INTO `subject` (`id`, `name`, `subject_code`) VALUES
(1, 'РПМ', '09090'),
(2, 'ИЗО', '99909090'),
(3, 'Верстка', '228009');

-- --------------------------------------------------------

--
-- Структура таблицы `summary`
--

CREATE TABLE `summary` (
  `id` bigint(20) NOT NULL,
  `mark` int(11) NOT NULL,
  `document_id` bigint(20) DEFAULT NULL,
  `education_institution_id` bigint(20) DEFAULT NULL,
  `subject_id` bigint(20) DEFAULT NULL,
  `candidate_info_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Структура таблицы `teacher_info`
--

CREATE TABLE `teacher_info` (
  `id` bigint(20) NOT NULL,
  `teach_since` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Дамп данных таблицы `teacher_info`
--

INSERT INTO `teacher_info` (`id`, `teach_since`) VALUES
(1, NULL);

-- --------------------------------------------------------

--
-- Структура таблицы `teacher_to_subject`
--

CREATE TABLE `teacher_to_subject` (
  `subject_id` bigint(20) NOT NULL,
  `teacher_info_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Дамп данных таблицы `teacher_to_subject`
--

INSERT INTO `teacher_to_subject` (`subject_id`, `teacher_info_id`) VALUES
(1, 1),
(1, 2);

-- --------------------------------------------------------

--
-- Структура таблицы `user`
--

CREATE TABLE `user` (
  `id` bigint(20) NOT NULL,
  `active` bit(1) NOT NULL,
  `login` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `password` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `department_id` bigint(20) DEFAULT NULL,
  `teacher_info_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Дамп данных таблицы `user`
--

INSERT INTO `user` (`id`, `active`, `login`, `password`, `department_id`, `teacher_info_id`) VALUES
(1, b'1', 'chu', '$2a$08$YHMu7dN0sX2g2bqHjPs6IuaDJWdluhIaSVc5f7B2BiUAXEVj5/zuC', NULL, NULL),
(2, b'1', 'chuj', '$2a$08$oun8Gi1l4iDbFeEQB9RPrumaZ4GyHGzZTNPa/u.v6.C/0TPWcjZ2i', NULL, NULL),
(3, b'1', 'dan', '$2a$08$Grpq6STK8BojVwE20tGIW.wrJNpjt1KQe0j4UOgMS50TrkGoYZ7s6', NULL, NULL),
(5, b'1', 'Dan2', '$2a$08$QiYkrUQrH9ijpUEc1khLROB/.kSBzmR8pERHfA.xWoHvp4m6dwRh2', NULL, NULL),
(6, b'1', 'chubaka', '$2a$08$CMWTFKvB/RGaQUC7UxJ9zeZMJlTsEHZJ34Zd/C6oqdMU26dMQfNWe', NULL, NULL);

-- --------------------------------------------------------

--
-- Структура таблицы `user_role`
--

CREATE TABLE `user_role` (
  `user_id` bigint(20) NOT NULL,
  `roles` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Дамп данных таблицы `user_role`
--

INSERT INTO `user_role` (`user_id`, `roles`) VALUES
(1, 'ADMIN'),
(2, 'TEACHER'),
(3, 'USER'),
(5, 'USER'),
(6, 'TEACHER');

-- --------------------------------------------------------

--
-- Структура таблицы `user_to_group`
--

CREATE TABLE `user_to_group` (
  `user_id` bigint(20) NOT NULL,
  `groups_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Дамп данных таблицы `user_to_group`
--

INSERT INTO `user_to_group` (`user_id`, `groups_id`) VALUES
(1, 3),
(2, 3),
(3, 3),
(3, 4),
(5, 4);

--
-- Индексы сохранённых таблиц
--

--
-- Индексы таблицы `candidate_info`
--
ALTER TABLE `candidate_info`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKawce9k97u5duam3s08yevfhol` (`target_department_id`),
  ADD KEY `FKnmmw264mxg0c1nsf3rr3tsavh` (`user_id`);

--
-- Индексы таблицы `department`
--
ALTER TABLE `department`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `document`
--
ALTER TABLE `document`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKjhdxdv9sijhujiynqbb5jc010` (`user_id`);

--
-- Индексы таблицы `education_institution`
--
ALTER TABLE `education_institution`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `genre`
--
ALTER TABLE `genre`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `genres_movie`
--
ALTER TABLE `genres_movie`
  ADD KEY `FKnhlqot1ak8viv4ig2q2o5egxd` (`movie_id`),
  ADD KEY `FKc9d0b1y4rtc6wgw4awtmmtjaw` (`genre_id`);

--
-- Индексы таблицы `groups`
--
ALTER TABLE `groups`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKkrwpk1mo6dnwu01w73lfb94nj` (`preparation_program_id`),
  ADD KEY `FKiv9ags22dibeded3dry2wjoay` (`user_id`);

--
-- Индексы таблицы `movies`
--
ALTER TABLE `movies`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `personal_info`
--
ALTER TABLE `personal_info`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK9lk7kc3eams2ytqcpiu72h0fw` (`user_id`);

--
-- Индексы таблицы `post`
--
ALTER TABLE `post`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKiiq109hcnp1i37iejucptsd8x` (`movie_id`),
  ADD KEY `FK72mt33dhhs48hf9gcqrq4fxte` (`user_id`);

--
-- Индексы таблицы `preparation_program`
--
ALTER TABLE `preparation_program`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `prep_to_subject`
--
ALTER TABLE `prep_to_subject`
  ADD PRIMARY KEY (`preparation_program_id`,`subject_id`),
  ADD KEY `FK75h44mj4m3ee5ju2qihw208qo` (`subject_id`);

--
-- Индексы таблицы `subject`
--
ALTER TABLE `subject`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `summary`
--
ALTER TABLE `summary`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKg8fbvq98t58jqv9ejsasvmm31` (`document_id`),
  ADD KEY `FKmolbsom8pq8xavbjiw2bec0g5` (`subject_id`),
  ADD KEY `FKms81gaiod1rv94urfoma6yx36` (`education_institution_id`),
  ADD KEY `FKnmrchtngch4dv20klsvydhd0m` (`candidate_info_id`);

--
-- Индексы таблицы `teacher_info`
--
ALTER TABLE `teacher_info`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `teacher_to_subject`
--
ALTER TABLE `teacher_to_subject`
  ADD PRIMARY KEY (`teacher_info_id`,`subject_id`),
  ADD KEY `FKlsos7o7akgd2lkkq0xppl9qsc` (`subject_id`);

--
-- Индексы таблицы `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKgkh2fko1e4ydv1y6vtrwdc6my` (`department_id`),
  ADD KEY `FK1fcrg47kftkbsr1cbm8ffybnn` (`teacher_info_id`);

--
-- Индексы таблицы `user_role`
--
ALTER TABLE `user_role`
  ADD KEY `FK859n2jvi8ivhui0rl0esws6o` (`user_id`);

--
-- Индексы таблицы `user_to_group`
--
ALTER TABLE `user_to_group`
  ADD PRIMARY KEY (`groups_id`,`user_id`),
  ADD KEY `FKmc3ldwvpe48shqdy7kn7yk31k` (`user_id`);

--
-- AUTO_INCREMENT для сохранённых таблиц
--

--
-- AUTO_INCREMENT для таблицы `candidate_info`
--
ALTER TABLE `candidate_info`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT для таблицы `department`
--
ALTER TABLE `department`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT для таблицы `document`
--
ALTER TABLE `document`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT для таблицы `education_institution`
--
ALTER TABLE `education_institution`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT для таблицы `groups`
--
ALTER TABLE `groups`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT для таблицы `movies`
--
ALTER TABLE `movies`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT для таблицы `personal_info`
--
ALTER TABLE `personal_info`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT для таблицы `post`
--
ALTER TABLE `post`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT для таблицы `preparation_program`
--
ALTER TABLE `preparation_program`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT для таблицы `subject`
--
ALTER TABLE `subject`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT для таблицы `summary`
--
ALTER TABLE `summary`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT для таблицы `teacher_info`
--
ALTER TABLE `teacher_info`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT для таблицы `user`
--
ALTER TABLE `user`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Ограничения внешнего ключа сохраненных таблиц
--

--
-- Ограничения внешнего ключа таблицы `candidate_info`
--
ALTER TABLE `candidate_info`
  ADD CONSTRAINT `FKawce9k97u5duam3s08yevfhol` FOREIGN KEY (`target_department_id`) REFERENCES `department` (`id`),
  ADD CONSTRAINT `FKnmmw264mxg0c1nsf3rr3tsavh` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

--
-- Ограничения внешнего ключа таблицы `document`
--
ALTER TABLE `document`
  ADD CONSTRAINT `FKjhdxdv9sijhujiynqbb5jc010` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

--
-- Ограничения внешнего ключа таблицы `genres_movie`
--
ALTER TABLE `genres_movie`
  ADD CONSTRAINT `FKc9d0b1y4rtc6wgw4awtmmtjaw` FOREIGN KEY (`genre_id`) REFERENCES `movies` (`id`),
  ADD CONSTRAINT `FKnhlqot1ak8viv4ig2q2o5egxd` FOREIGN KEY (`movie_id`) REFERENCES `genre` (`id`);

--
-- Ограничения внешнего ключа таблицы `groups`
--
ALTER TABLE `groups`
  ADD CONSTRAINT `FKiv9ags22dibeded3dry2wjoay` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `FKkrwpk1mo6dnwu01w73lfb94nj` FOREIGN KEY (`preparation_program_id`) REFERENCES `preparation_program` (`id`);

--
-- Ограничения внешнего ключа таблицы `personal_info`
--
ALTER TABLE `personal_info`
  ADD CONSTRAINT `FK9lk7kc3eams2ytqcpiu72h0fw` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

--
-- Ограничения внешнего ключа таблицы `post`
--
ALTER TABLE `post`
  ADD CONSTRAINT `FK72mt33dhhs48hf9gcqrq4fxte` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `FKiiq109hcnp1i37iejucptsd8x` FOREIGN KEY (`movie_id`) REFERENCES `movies` (`id`);

--
-- Ограничения внешнего ключа таблицы `prep_to_subject`
--
ALTER TABLE `prep_to_subject`
  ADD CONSTRAINT `FK75h44mj4m3ee5ju2qihw208qo` FOREIGN KEY (`subject_id`) REFERENCES `subject` (`id`),
  ADD CONSTRAINT `FKer7fssk8wspu4sliwvv4yiwnc` FOREIGN KEY (`preparation_program_id`) REFERENCES `preparation_program` (`id`);

--
-- Ограничения внешнего ключа таблицы `summary`
--
ALTER TABLE `summary`
  ADD CONSTRAINT `FKbtflbyplti5vm4vsv3xm12rl8` FOREIGN KEY (`education_institution_id`) REFERENCES `subject` (`id`),
  ADD CONSTRAINT `FKg8fbvq98t58jqv9ejsasvmm31` FOREIGN KEY (`document_id`) REFERENCES `document` (`id`),
  ADD CONSTRAINT `FKmolbsom8pq8xavbjiw2bec0g5` FOREIGN KEY (`subject_id`) REFERENCES `subject` (`id`),
  ADD CONSTRAINT `FKms81gaiod1rv94urfoma6yx36` FOREIGN KEY (`education_institution_id`) REFERENCES `education_institution` (`id`),
  ADD CONSTRAINT `FKnmrchtngch4dv20klsvydhd0m` FOREIGN KEY (`candidate_info_id`) REFERENCES `candidate_info` (`id`);

--
-- Ограничения внешнего ключа таблицы `teacher_to_subject`
--
ALTER TABLE `teacher_to_subject`
  ADD CONSTRAINT `FK9th2sskkfuvafilslfubtfxix` FOREIGN KEY (`teacher_info_id`) REFERENCES `subject` (`id`),
  ADD CONSTRAINT `FKlsos7o7akgd2lkkq0xppl9qsc` FOREIGN KEY (`subject_id`) REFERENCES `teacher_info` (`id`);

--
-- Ограничения внешнего ключа таблицы `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `FK1fcrg47kftkbsr1cbm8ffybnn` FOREIGN KEY (`teacher_info_id`) REFERENCES `teacher_info` (`id`),
  ADD CONSTRAINT `FKgkh2fko1e4ydv1y6vtrwdc6my` FOREIGN KEY (`department_id`) REFERENCES `department` (`id`);

--
-- Ограничения внешнего ключа таблицы `user_role`
--
ALTER TABLE `user_role`
  ADD CONSTRAINT `FK859n2jvi8ivhui0rl0esws6o` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

--
-- Ограничения внешнего ключа таблицы `user_to_group`
--
ALTER TABLE `user_to_group`
  ADD CONSTRAINT `FKeivkml1uik8wqgg6vio7mhvww` FOREIGN KEY (`groups_id`) REFERENCES `groups` (`id`),
  ADD CONSTRAINT `FKmc3ldwvpe48shqdy7kn7yk31k` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
