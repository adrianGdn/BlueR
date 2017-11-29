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

CREATE TABLE IF NOT EXISTS `device` (
  `idBluetooth` char(50) NOT NULL,
  `deviceName` char(50) NOT NULL,
  PRIMARY KEY (`idBluetooth`)
) ENGINE=InnoDB;

-- --------------------------------------------------------

--
-- Structure de la table `User`
--

CREATE TABLE IF NOT EXISTS `user` (
  `firstName` char(20) NOT NULL,
  `secondName` char(20) NOT NULL,
  `idBluetooth` char(50) NOT NULL,
  `mailAddress` char(50),
  `nbMobile` char(30),
  PRIMARY KEY (`firstName`),
  CONSTRAINT fk_user FOREIGN KEY(idBluetooth) REFERENCES Device(idBluetooth)
) ENGINE=InnoDB;

-- --------------------------------------------------------

--
-- Contenu de la table `Device`
--

INSERT INTO `device` (`idBluetooth`, `deviceName`) VALUES
('123456789102', 'Device A'),
('abcdef123456', 'The test man device'),
('156468453923', 'E.T. Téléphone maison');

-- --------------------------------------------------------

--
-- Contenu de la table `User`
--

INSERT INTO `user` (`firstName`, `secondName`, `idBluetooth`, `mailAddress`, `nbMobile`) VALUES
('A', 'B', '123456789102', 'a.device@gmail.com', '0654232248'),
('Test', 'Man', 'abcdef123456', 'testman.device@epsi.fr', '0623232323'),
('E.', 'T.', '156468453923', 'et.telmaison@hotmail.fr', '0637373737');

-- --------------------------------------------------------
