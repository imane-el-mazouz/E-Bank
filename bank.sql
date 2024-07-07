-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : dim. 07 juil. 2024 à 23:54
-- Version du serveur : 10.4.32-MariaDB
-- Version de PHP : 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `bank`
--

-- --------------------------------------------------------

--
-- Structure de la table `account`
--

CREATE TABLE `account` (
  `ida` bigint(20) NOT NULL,
  `account_closed` bit(1) DEFAULT NULL,
  `bank` enum('AttijariWafaBank','MaghrebBank','bmce','cih') NOT NULL,
  `closeure_reason` varchar(255) DEFAULT NULL,
  `date` datetime(6) DEFAULT NULL,
  `sold` double DEFAULT NULL,
  `typea` enum('currentAccount','savingAccount') NOT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `rib` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `account`
--

INSERT INTO `account` (`ida`, `account_closed`, `bank`, `closeure_reason`, `date`, `sold`, `typea`, `user_id`, `rib`) VALUES
(4, b'1', 'MaghrebBank', '{\r\n    \"reason\": \"pas de solde\r\n}\r\n', '2024-05-26 10:25:29.000000', 5573, 'currentAccount', 2, 11111),
(6, b'1', 'MaghrebBank', '{\r\n    \"reason\": \"pas de solde\"\r\n}\r\n', '2024-05-26 10:25:29.000000', 144, 'currentAccount', 2, 9999),
(7, b'0', 'MaghrebBank', NULL, '2024-05-26 10:25:29.000000', 1, 'currentAccount', 2, 8888),
(8, b'0', 'cih', NULL, '2024-05-26 10:25:29.000000', 5553, 'currentAccount', 2, 8767),
(9, b'1', 'cih', 'pas de solde', '2024-05-26 10:25:29.000000', 5553, 'currentAccount', 2, NULL),
(10, b'0', 'bmce', NULL, '2024-05-26 10:25:29.000000', 133333, 'currentAccount', 2, 555555),
(11, b'0', 'bmce', NULL, '2024-05-26 10:25:29.000000', 16666, 'currentAccount', 2, 7777),
(12, b'0', 'MaghrebBank', NULL, '2024-05-26 10:25:29.000000', 1, 'currentAccount', 2, 888866);

-- --------------------------------------------------------

--
-- Structure de la table `beneficiary`
--

CREATE TABLE `beneficiary` (
  `idb` bigint(20) NOT NULL,
  `bank` enum('AttijariWafaBank','MaghrebBank','bmce','cih') NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `rib` bigint(20) DEFAULT NULL,
  `account_id` bigint(20) DEFAULT NULL,
  `sold` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `beneficiary`
--

INSERT INTO `beneficiary` (`idb`, `bank`, `name`, `rib`, `account_id`, `sold`) VALUES
(3, 'MaghrebBank', 'anis', 11111, 4, 666),
(4, 'MaghrebBank', 'Fatima', 22222, 6, 81),
(5, 'MaghrebBank', 'lina', 333333, 4, 66666),
(6, 'MaghrebBank', 'lint', 333333, 4, 1111111111),
(7, 'MaghrebBank', 'ziad', 2222, 4, 11666),
(8, 'MaghrebBank', 'kesal', 3333, NULL, 11666),
(9, 'MaghrebBank', 'tran', 388, NULL, 11666);

-- --------------------------------------------------------

--
-- Structure de la table `card`
--

CREATE TABLE `card` (
  `idc` bigint(20) NOT NULL,
  `blocking_reason` enum('loss','none','theft') NOT NULL,
  `expiration_date` datetime(6) DEFAULT NULL,
  `status` enum('activated','blocked','desactivated') NOT NULL,
  `type_card` enum('credit','debit') NOT NULL,
  `account_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `card`
--

INSERT INTO `card` (`idc`, `blocking_reason`, `expiration_date`, `status`, `type_card`, `account_id`) VALUES
(1, 'none', '2027-07-22 09:53:49.000000', 'activated', 'debit', 4),
(3, 'none', '2026-07-05 09:57:40.000000', 'blocked', 'debit', 4),
(4, 'none', '2026-07-05 09:58:59.000000', 'activated', 'debit', 6),
(5, 'none', '2026-07-05 09:59:55.000000', 'activated', 'debit', 7),
(7, 'none', '2026-07-05 09:57:40.000000', 'activated', 'debit', 4),
(8, 'none', '2026-07-07 14:47:40.000000', 'activated', 'debit', 8),
(9, 'none', '2026-07-07 14:48:22.000000', 'activated', 'debit', 9),
(10, 'none', '2026-07-07 15:05:46.000000', 'activated', 'debit', 10),
(11, 'loss', '2026-07-05 09:57:40.000000', 'blocked', 'debit', 4),
(12, 'none', '2026-07-07 19:27:17.000000', 'activated', 'debit', 12);

-- --------------------------------------------------------

--
-- Structure de la table `transaction`
--

CREATE TABLE `transaction` (
  `idt` bigint(20) NOT NULL,
  `amount` double DEFAULT NULL,
  `date` datetime(6) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `ribt` bigint(20) DEFAULT NULL,
  `typet` enum('external','internal') NOT NULL,
  `beneficiary_id` bigint(20) DEFAULT NULL,
  `from_account_id` bigint(20) DEFAULT NULL,
  `to_account_id` bigint(20) DEFAULT NULL,
  `type_card` enum('credit','debit') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `transaction`
--

INSERT INTO `transaction` (`idt`, `amount`, `date`, `description`, `ribt`, `typet`, `beneficiary_id`, `from_account_id`, `to_account_id`, `type_card`) VALUES
(1, 50, '2024-07-07 00:11:07.000000', 'Transfer test', NULL, 'internal', NULL, 6, 4, 'debit'),
(2, 50, '2024-07-07 00:11:07.000000', 'Transfer test', NULL, 'internal', NULL, 6, 4, 'credit'),
(3, 5, '2024-07-07 00:45:51.000000', 'Transfer test', NULL, 'internal', NULL, 6, 4, 'debit'),
(4, 5, '2024-07-07 00:45:51.000000', 'Transfer test', NULL, 'internal', NULL, 6, 4, 'credit'),
(5, 5, '2024-07-07 14:41:11.000000', 'Transfert externe', NULL, 'external', 4, 4, NULL, 'debit'),
(6, 5, '2024-07-07 14:41:11.000000', 'Transfert externe', NULL, 'external', 4, NULL, NULL, 'credit'),
(7, 1, '2024-07-07 16:32:13.000000', 'Transfer test', NULL, 'internal', NULL, 6, 4, 'debit'),
(8, 1, '2024-07-07 16:32:13.000000', 'Transfer test', NULL, 'internal', NULL, 6, 4, 'credit'),
(9, 11, '2024-07-07 16:32:31.000000', 'Transfert externe', NULL, 'external', 4, 4, NULL, 'debit'),
(10, 11, '2024-07-07 16:32:31.000000', 'Transfert externe', NULL, 'external', 4, NULL, NULL, 'credit');

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

CREATE TABLE `user` (
  `idu` bigint(20) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `profession` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `user`
--

INSERT INTO `user` (`idu`, `email`, `name`, `password`, `profession`) VALUES
(2, 'imane@gmail.com', 'imane', '1234', 'STUDENT');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `account`
--
ALTER TABLE `account`
  ADD PRIMARY KEY (`ida`),
  ADD KEY `FK7m8ru44m93ukyb61dfxw0apf6` (`user_id`);

--
-- Index pour la table `beneficiary`
--
ALTER TABLE `beneficiary`
  ADD PRIMARY KEY (`idb`),
  ADD KEY `FKlp1yncdnefvnu7lx58o0i9ywv` (`account_id`);

--
-- Index pour la table `card`
--
ALTER TABLE `card`
  ADD PRIMARY KEY (`idc`),
  ADD KEY `FK8v67eys6tqflsm6hrdgru2phu` (`account_id`);

--
-- Index pour la table `transaction`
--
ALTER TABLE `transaction`
  ADD PRIMARY KEY (`idt`),
  ADD KEY `FK5r0nx1xqrmc2xlovgmh8lpn5` (`beneficiary_id`),
  ADD KEY `FKrff4jlxetafju1e5cks5mfcnk` (`from_account_id`),
  ADD KEY `FKluqt8k2pa8d4gmggx4rhl5vgv` (`to_account_id`);

--
-- Index pour la table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`idu`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `account`
--
ALTER TABLE `account`
  MODIFY `ida` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT pour la table `beneficiary`
--
ALTER TABLE `beneficiary`
  MODIFY `idb` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT pour la table `card`
--
ALTER TABLE `card`
  MODIFY `idc` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT pour la table `transaction`
--
ALTER TABLE `transaction`
  MODIFY `idt` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT pour la table `user`
--
ALTER TABLE `user`
  MODIFY `idu` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `account`
--
ALTER TABLE `account`
  ADD CONSTRAINT `FK7m8ru44m93ukyb61dfxw0apf6` FOREIGN KEY (`user_id`) REFERENCES `user` (`idu`);

--
-- Contraintes pour la table `beneficiary`
--
ALTER TABLE `beneficiary`
  ADD CONSTRAINT `FKlp1yncdnefvnu7lx58o0i9ywv` FOREIGN KEY (`account_id`) REFERENCES `account` (`ida`);

--
-- Contraintes pour la table `card`
--
ALTER TABLE `card`
  ADD CONSTRAINT `FK8v67eys6tqflsm6hrdgru2phu` FOREIGN KEY (`account_id`) REFERENCES `account` (`ida`);

--
-- Contraintes pour la table `transaction`
--
ALTER TABLE `transaction`
  ADD CONSTRAINT `FK5r0nx1xqrmc2xlovgmh8lpn5` FOREIGN KEY (`beneficiary_id`) REFERENCES `beneficiary` (`idb`),
  ADD CONSTRAINT `FKluqt8k2pa8d4gmggx4rhl5vgv` FOREIGN KEY (`to_account_id`) REFERENCES `account` (`ida`),
  ADD CONSTRAINT `FKrff4jlxetafju1e5cks5mfcnk` FOREIGN KEY (`from_account_id`) REFERENCES `account` (`ida`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
