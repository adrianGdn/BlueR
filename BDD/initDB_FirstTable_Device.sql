-- --------------------------------------------------------
-- phpMyAdmin SQL
-- http://www.phpmyadmin.net
-- --------------------------------------------------------

--
-- Base de données : `bluer`
--

-- --------------------------------------------------------

--
-- Structure de la table `Device`
--

CREATE TABLE IF NOT EXISTS `Device` (
  `idBluetooth` char(50) NOT NULL,
  `deviceName` char(50) NOT NULL,
  `mailAddress` char(30),
  PRIMARY KEY (`idBluetooth`)
) ENGINE=InnoDB;

-- --------------------------------------------------------
