-- phpMyAdmin SQL Dump
-- version 5.0.3
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : Dim 21 fév. 2021 à 21:46
-- Version du serveur :  10.4.14-MariaDB
-- Version de PHP : 7.4.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `transportation`
--

-- --------------------------------------------------------

--
-- Structure de la table `article`
--

CREATE TABLE `article` (
  `codeArticle` int(11) NOT NULL,
  `codeMarchandise` int(11) NOT NULL,
  `numWagon` int(11) DEFAULT NULL,
  `natureArticle` varchar(30) NOT NULL,
  `nombreUnites` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `article`
--

INSERT INTO `article` (`codeArticle`, `codeMarchandise`, `numWagon`, `natureArticle`, `nombreUnites`) VALUES
(1, 1, 661, 'voiture', 20),
(2, 1, 682, 'stère de bois', 30),
(3, 2, 663, 'essence m3', 15),
(4, 3, 668, 'bermile', 50),
(5, 3, 669, 'boite', 60),
(8, 7, 669, 'aluminium', 45);

-- --------------------------------------------------------

--
-- Structure de la table `client`
--

CREATE TABLE `client` (
  `numClient` int(11) NOT NULL,
  `nomClient` varchar(30) NOT NULL,
  `adresse` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `client`
--

INSERT INTO `client` (`numClient`, `nomClient`, `adresse`) VALUES
(1, 'ZAIDI Oussama', 'Rabat Hay Riad Mahaj'),
(2, 'ACHEHBOUN Achraf', 'Sale Hay Chmaaou '),
(3, 'TOUAHRI Walid', 'Kenitra Mehdia'),
(9, 'ALAOUI Ahmed', 'Temara '),
(10, 'AMRANI Omar', 'Meknes'),
(11, 'Benmer Abdessamad', 'Fes'),
(12, 'Allali Mokhtar', 'Mohammedia');

-- --------------------------------------------------------

--
-- Structure de la table `gare`
--

CREATE TABLE `gare` (
  `numGare` int(11) NOT NULL,
  `nomGare` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `gare`
--

INSERT INTO `gare` (`numGare`, `nomGare`) VALUES
(1, 'Rabat'),
(2, 'Sale'),
(3, 'Casablanca'),
(4, 'Tanger'),
(5, 'Meknes'),
(6, 'Fes'),
(7, 'Marrakesh'),
(8, 'El Jadida');

-- --------------------------------------------------------

--
-- Structure de la table `ligne`
--

CREATE TABLE `ligne` (
  `numLigne` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `ligne`
--

INSERT INTO `ligne` (`numLigne`) VALUES
(1),
(2),
(3),
(4);

-- --------------------------------------------------------

--
-- Structure de la table `marchandise`
--

CREATE TABLE `marchandise` (
  `codeMarchandise` int(11) NOT NULL,
  `numClient` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `marchandise`
--

INSERT INTO `marchandise` (`codeMarchandise`, `numClient`) VALUES
(1, 1),
(4, 1),
(7, 1),
(8, 1),
(2, 2),
(5, 2),
(3, 3),
(6, 3);

-- --------------------------------------------------------

--
-- Structure de la table `relier`
--

CREATE TABLE `relier` (
  `numLigne` int(11) NOT NULL,
  `numGare` int(11) NOT NULL,
  `dateDepart` date DEFAULT NULL,
  `dateArrivee` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `relier`
--

INSERT INTO `relier` (`numLigne`, `numGare`, `dateDepart`, `dateArrivee`) VALUES
(1, 1, '2021-03-01', '2021-02-03'),
(1, 2, '2021-03-03', '2021-02-05'),
(1, 3, '2021-03-17', '2021-03-18'),
(1, 4, '2021-02-25', '2021-02-26'),
(2, 5, '2021-02-28', '2021-03-03'),
(2, 6, '2021-03-10', '2021-03-13'),
(3, 7, '2021-02-27', '2021-02-28'),
(4, 8, '2021-03-31', '2021-04-02');

-- --------------------------------------------------------

--
-- Structure de la table `train`
--

CREATE TABLE `train` (
  `numTrain` int(11) NOT NULL,
  `numLigne` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `train`
--

INSERT INTO `train` (`numTrain`, `numLigne`) VALUES
(501, 1),
(502, 1),
(503, 2),
(592, 3);

-- --------------------------------------------------------

--
-- Structure de la table `utilisateurs`
--

CREATE TABLE `utilisateurs` (
  `id_user` int(11) NOT NULL,
  `username` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `utilisateurs`
--

INSERT INTO `utilisateurs` (`id_user`, `username`, `password`) VALUES
(1, 'Kaoutar', '2000'),
(2, 'Hatim', '2000'),
(3, 'Cloud', '2021');

-- --------------------------------------------------------

--
-- Structure de la table `wagon`
--

CREATE TABLE `wagon` (
  `numWagon` int(11) NOT NULL,
  `numTrain` int(11) NOT NULL,
  `nature` varchar(30) NOT NULL,
  `capacite` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `wagon`
--

INSERT INTO `wagon` (`numWagon`, `numTrain`, `nature`, `capacite`) VALUES
(661, 501, 'voiture', 30),
(662, 502, 'stère de bois', 40),
(663, 501, 'essence m3', 50),
(668, 503, 'bermile', 50),
(669, 503, 'boite', 60),
(680, 592, 'voiture', 60),
(682, 501, 'stère de bois', 50);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `article`
--
ALTER TABLE `article`
  ADD PRIMARY KEY (`codeArticle`),
  ADD KEY `codeMarchandise` (`codeMarchandise`),
  ADD KEY `numWagon` (`numWagon`);

--
-- Index pour la table `client`
--
ALTER TABLE `client`
  ADD PRIMARY KEY (`numClient`);

--
-- Index pour la table `gare`
--
ALTER TABLE `gare`
  ADD PRIMARY KEY (`numGare`);

--
-- Index pour la table `ligne`
--
ALTER TABLE `ligne`
  ADD PRIMARY KEY (`numLigne`);

--
-- Index pour la table `marchandise`
--
ALTER TABLE `marchandise`
  ADD PRIMARY KEY (`codeMarchandise`),
  ADD KEY `numClient` (`numClient`);

--
-- Index pour la table `relier`
--
ALTER TABLE `relier`
  ADD PRIMARY KEY (`numLigne`,`numGare`),
  ADD KEY `numGare` (`numGare`);

--
-- Index pour la table `train`
--
ALTER TABLE `train`
  ADD PRIMARY KEY (`numTrain`),
  ADD KEY `numLigne` (`numLigne`);

--
-- Index pour la table `utilisateurs`
--
ALTER TABLE `utilisateurs`
  ADD PRIMARY KEY (`id_user`);

--
-- Index pour la table `wagon`
--
ALTER TABLE `wagon`
  ADD PRIMARY KEY (`numWagon`),
  ADD KEY `numTrain` (`numTrain`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `client`
--
ALTER TABLE `client`
  MODIFY `numClient` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT pour la table `utilisateurs`
--
ALTER TABLE `utilisateurs`
  MODIFY `id_user` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `article`
--
ALTER TABLE `article`
  ADD CONSTRAINT `article_ibfk_1` FOREIGN KEY (`codeMarchandise`) REFERENCES `marchandise` (`codeMarchandise`),
  ADD CONSTRAINT `article_ibfk_2` FOREIGN KEY (`numWagon`) REFERENCES `wagon` (`numWagon`);

--
-- Contraintes pour la table `marchandise`
--
ALTER TABLE `marchandise`
  ADD CONSTRAINT `marchandise_ibfk_1` FOREIGN KEY (`numClient`) REFERENCES `client` (`numClient`);

--
-- Contraintes pour la table `relier`
--
ALTER TABLE `relier`
  ADD CONSTRAINT `relier_ibfk_1` FOREIGN KEY (`numLigne`) REFERENCES `ligne` (`numLigne`),
  ADD CONSTRAINT `relier_ibfk_2` FOREIGN KEY (`numGare`) REFERENCES `gare` (`numGare`);

--
-- Contraintes pour la table `train`
--
ALTER TABLE `train`
  ADD CONSTRAINT `train_ibfk_1` FOREIGN KEY (`numLigne`) REFERENCES `ligne` (`numLigne`);

--
-- Contraintes pour la table `wagon`
--
ALTER TABLE `wagon`
  ADD CONSTRAINT `wagon_ibfk_1` FOREIGN KEY (`numTrain`) REFERENCES `train` (`numTrain`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
