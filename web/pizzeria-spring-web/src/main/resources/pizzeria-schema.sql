CREATE TABLE `pizza` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `categorie` varchar(255) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `prix` decimal(19,2) DEFAULT NULL,
  `url_image` varchar(255) DEFAULT NULL
);

ALTER TABLE `pizza` ADD CONSTRAINT `code_unique` UNIQUE(`code`);