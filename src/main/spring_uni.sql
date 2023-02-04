SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;


CREATE TABLE `candidate_info` (
  `id` bigint(20) NOT NULL,
  `average_mark` double NOT NULL,
  `submission_date` date DEFAULT NULL,
  `education_institution_id` bigint(20) DEFAULT NULL,
  `prepatation_program_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `candidate_info` (`id`, `average_mark`, `submission_date`, `education_institution_id`, `prepatation_program_id`, `user_id`) VALUES
(1, 0, '2022-12-20', NULL, NULL, 3),
(2, 4.166666666666667, '2022-12-20', 1, 1, 3);

CREATE TABLE `department` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `department` (`id`, `name`) VALUES
(1, 'Преподаватель'),
(2, 'Программист');

CREATE TABLE `document` (
  `id` bigint(20) NOT NULL,
  `archive_date` date DEFAULT NULL,
  `average_mark` double NOT NULL,
  `date` date DEFAULT NULL,
  `file_name` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `to_admission` bit(1) NOT NULL,
  `user_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `document` (`id`, `archive_date`, `average_mark`, `date`, `file_name`, `status`, `to_admission`, `user_id`) VALUES
(2, '2023-01-20', 4.285714285714286, '2022-12-20', '4_PROFESSIONAL_ENGLISH.pdf', 0, b'1', 3);

CREATE TABLE `education_institution` (
  `id` bigint(20) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `education_institution` (`id`, `address`, `name`) VALUES
(1, 'ыфвфывфыв', 'Школ'),
(2, 'кукуево', 'Хогвартс');

CREATE TABLE `groups` (
  `id` bigint(20) NOT NULL,
  `capacity` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `preparation_program_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `groups` (`id`, `capacity`, `name`, `preparation_program_id`, `user_id`) VALUES
(1, 14, 'ИСИП-1-22', 1, 4);

CREATE TABLE `personal_info` (
  `id` bigint(20) NOT NULL,
  `birthdate` date DEFAULT NULL,
  `is_male` bit(1) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `pass_id` int(11) NOT NULL,
  `sec_name` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `personal_info` (`id`, `birthdate`, `is_male`, `name`, `pass_id`, `sec_name`, `surname`, `user_id`) VALUES
(1, '2021-10-08', b'1', 'ыва', 0, 'ыва', 'фыва', 1),
(2, '2018-09-16', b'1', 'фывф', 0, 'выфа', 'фыва', 2),
(3, '2013-09-17', b'1', 'выфавыф', 0, 'вфыа', 'выа', 3),
(4, '2017-09-18', b'1', 'чммаы', 0, 'фывфы', 'выфв', 4);

CREATE TABLE `preparation_program` (
  `id` bigint(20) NOT NULL,
  `max_group_capacity` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `department_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `preparation_program` (`id`, `max_group_capacity`, `name`, `department_id`) VALUES
(1, 5, 'ИСИП', 2),
(2, 4, 'Веб разработчик', 2);

CREATE TABLE `prep_to_subject` (
  `subject_id` bigint(20) NOT NULL,
  `preparation_program_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `prep_to_subject` (`subject_id`, `preparation_program_id`) VALUES
(1, 1);

CREATE TABLE `student_info` (
  `id` bigint(20) NOT NULL,
  `prepatation_program_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `subject` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `subject_code` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `subject` (`id`, `name`, `subject_code`) VALUES
(1, 'Мат мод', 'фыв'),
(2, 'Рус яз', '123'),
(3, 'Физ ра ', '123123'),
(4, 'Географи', '333'),
(5, 'История', '1333'),
(6, 'Химимя', '123'),
(7, 'Биология', '333333');

CREATE TABLE `summary` (
  `id` bigint(20) NOT NULL,
  `mark` int(11) NOT NULL,
  `candidate_info_id` bigint(20) DEFAULT NULL,
  `document_id` bigint(20) DEFAULT NULL,
  `subject_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `summary` (`id`, `mark`, `candidate_info_id`, `document_id`, `subject_id`) VALUES
(1, 5, 2, 2, 2),
(2, 4, 2, 2, 6),
(3, 3, 2, 2, 1),
(4, 3, 2, 2, 7),
(5, 5, 2, 2, 3),
(6, 5, 2, 2, 4),
(7, 5, 2, 2, 5);

CREATE TABLE `teacher_info` (
  `id` bigint(20) NOT NULL,
  `teach_since` date DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `teacher_to_subject` (
  `subject_id` bigint(20) NOT NULL,
  `teacher_info_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `user` (
  `id` bigint(20) NOT NULL,
  `active` bit(1) NOT NULL,
  `login` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `department_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `user` (`id`, `active`, `login`, `password`, `department_id`) VALUES
(1, b'1', 'Admin', '$2a$08$0pbNu1iYl1rJdjf.vDyEGOfbl6bcn8tSMtK1LZ3D7PILgMJ48ys2m', NULL),
(2, b'1', 'Commision', '$2a$08$WMHuKDjG2z/S6O3Epa6UjeqxkVWBE48yWGsoFR0Ev1zDQEi39t5au', NULL),
(3, b'1', 'user', '$2a$08$p0h/2AUyeSXfzbEJFofx9.8Qg4yNOgVAtHJJFHCADdlb0yAfwd2mu', NULL),
(4, b'1', 'Teacher', '$2a$08$c4lEyvLOWHiiexwf0F21L.hN7v0QgXns/QyyjpICmX.S288SrSfWC', NULL);

CREATE TABLE `user_role` (
  `user_id` bigint(20) NOT NULL,
  `roles` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `user_role` (`user_id`, `roles`) VALUES
(1, 'ADMIN'),
(2, 'ADMISSION'),
(3, 'USER'),
(3, 'CANDIDATE'),
(4, 'TEACHER');

CREATE TABLE `user_to_group` (
  `user_id` bigint(20) NOT NULL,
  `groups_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


ALTER TABLE `candidate_info`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK62nb5kdyo6tg5t4n7ni6cpp25` (`education_institution_id`),
  ADD KEY `FKmhwtm2fh20kto88bd6snqtxkk` (`prepatation_program_id`),
  ADD KEY `FKnmmw264mxg0c1nsf3rr3tsavh` (`user_id`);

ALTER TABLE `department`
  ADD PRIMARY KEY (`id`);

ALTER TABLE `document`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKjhdxdv9sijhujiynqbb5jc010` (`user_id`);

ALTER TABLE `education_institution`
  ADD PRIMARY KEY (`id`);

ALTER TABLE `groups`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKkrwpk1mo6dnwu01w73lfb94nj` (`preparation_program_id`),
  ADD KEY `FKiv9ags22dibeded3dry2wjoay` (`user_id`);

ALTER TABLE `personal_info`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK9lk7kc3eams2ytqcpiu72h0fw` (`user_id`);

ALTER TABLE `preparation_program`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK25um46j0c9x61i8gabhmvr676` (`department_id`);

ALTER TABLE `prep_to_subject`
  ADD PRIMARY KEY (`preparation_program_id`,`subject_id`),
  ADD KEY `FK75h44mj4m3ee5ju2qihw208qo` (`subject_id`);

ALTER TABLE `student_info`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKh50fsu4p9ng0i08e7uholptwp` (`prepatation_program_id`),
  ADD KEY `FKc6pqjw43eb9c5uu6m4088htg5` (`user_id`);

ALTER TABLE `subject`
  ADD PRIMARY KEY (`id`);

ALTER TABLE `summary`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKnmrchtngch4dv20klsvydhd0m` (`candidate_info_id`),
  ADD KEY `FKg8fbvq98t58jqv9ejsasvmm31` (`document_id`),
  ADD KEY `FKmolbsom8pq8xavbjiw2bec0g5` (`subject_id`);

ALTER TABLE `teacher_info`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKran96gqe8txgm7evtenso26t8` (`user_id`);

ALTER TABLE `teacher_to_subject`
  ADD PRIMARY KEY (`teacher_info_id`,`subject_id`),
  ADD KEY `FKlsos7o7akgd2lkkq0xppl9qsc` (`subject_id`);

ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKgkh2fko1e4ydv1y6vtrwdc6my` (`department_id`);

ALTER TABLE `user_role`
  ADD KEY `FK859n2jvi8ivhui0rl0esws6o` (`user_id`);

ALTER TABLE `user_to_group`
  ADD PRIMARY KEY (`groups_id`,`user_id`),
  ADD KEY `FKmc3ldwvpe48shqdy7kn7yk31k` (`user_id`);


ALTER TABLE `candidate_info`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

ALTER TABLE `department`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

ALTER TABLE `document`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

ALTER TABLE `education_institution`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

ALTER TABLE `groups`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

ALTER TABLE `personal_info`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

ALTER TABLE `preparation_program`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

ALTER TABLE `student_info`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

ALTER TABLE `subject`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

ALTER TABLE `summary`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

ALTER TABLE `teacher_info`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

ALTER TABLE `user`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;


ALTER TABLE `candidate_info`
  ADD CONSTRAINT `FK62nb5kdyo6tg5t4n7ni6cpp25` FOREIGN KEY (`education_institution_id`) REFERENCES `education_institution` (`id`),
  ADD CONSTRAINT `FKmhwtm2fh20kto88bd6snqtxkk` FOREIGN KEY (`prepatation_program_id`) REFERENCES `preparation_program` (`id`),
  ADD CONSTRAINT `FKnmmw264mxg0c1nsf3rr3tsavh` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

ALTER TABLE `document`
  ADD CONSTRAINT `FKjhdxdv9sijhujiynqbb5jc010` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

ALTER TABLE `groups`
  ADD CONSTRAINT `FKiv9ags22dibeded3dry2wjoay` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `FKkrwpk1mo6dnwu01w73lfb94nj` FOREIGN KEY (`preparation_program_id`) REFERENCES `preparation_program` (`id`);

ALTER TABLE `personal_info`
  ADD CONSTRAINT `FK9lk7kc3eams2ytqcpiu72h0fw` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

ALTER TABLE `preparation_program`
  ADD CONSTRAINT `FK25um46j0c9x61i8gabhmvr676` FOREIGN KEY (`department_id`) REFERENCES `department` (`id`);

ALTER TABLE `prep_to_subject`
  ADD CONSTRAINT `FK75h44mj4m3ee5ju2qihw208qo` FOREIGN KEY (`subject_id`) REFERENCES `subject` (`id`),
  ADD CONSTRAINT `FKer7fssk8wspu4sliwvv4yiwnc` FOREIGN KEY (`preparation_program_id`) REFERENCES `preparation_program` (`id`);

ALTER TABLE `student_info`
  ADD CONSTRAINT `FKc6pqjw43eb9c5uu6m4088htg5` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `FKh50fsu4p9ng0i08e7uholptwp` FOREIGN KEY (`prepatation_program_id`) REFERENCES `preparation_program` (`id`);

ALTER TABLE `summary`
  ADD CONSTRAINT `FKg8fbvq98t58jqv9ejsasvmm31` FOREIGN KEY (`document_id`) REFERENCES `document` (`id`),
  ADD CONSTRAINT `FKmolbsom8pq8xavbjiw2bec0g5` FOREIGN KEY (`subject_id`) REFERENCES `subject` (`id`),
  ADD CONSTRAINT `FKnmrchtngch4dv20klsvydhd0m` FOREIGN KEY (`candidate_info_id`) REFERENCES `candidate_info` (`id`);

ALTER TABLE `teacher_info`
  ADD CONSTRAINT `FKran96gqe8txgm7evtenso26t8` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

ALTER TABLE `teacher_to_subject`
  ADD CONSTRAINT `FK9th2sskkfuvafilslfubtfxix` FOREIGN KEY (`teacher_info_id`) REFERENCES `subject` (`id`),
  ADD CONSTRAINT `FKlsos7o7akgd2lkkq0xppl9qsc` FOREIGN KEY (`subject_id`) REFERENCES `teacher_info` (`id`);

ALTER TABLE `user`
  ADD CONSTRAINT `FKgkh2fko1e4ydv1y6vtrwdc6my` FOREIGN KEY (`department_id`) REFERENCES `department` (`id`);

ALTER TABLE `user_role`
  ADD CONSTRAINT `FK859n2jvi8ivhui0rl0esws6o` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

ALTER TABLE `user_to_group`
  ADD CONSTRAINT `FKeivkml1uik8wqgg6vio7mhvww` FOREIGN KEY (`groups_id`) REFERENCES `groups` (`id`),
  ADD CONSTRAINT `FKmc3ldwvpe48shqdy7kn7yk31k` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
