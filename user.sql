CREATE DATABASE  IF NOT EXISTS `user_directory`;
USE `user_directory`;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `mobile` BIGINT(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

--
-- Data for table `user`
--

INSERT INTO `user` VALUES 
	(1,'Leslie','Andrews','leslie@luv2code.com',9949975888),
	(2,'Emma','Baumgarten','emma@luv2code.com',9949975881),
	(3,'Avani','Gupta','avani@luv2code.com',9949975882),
	(4,'Yuri','Petrov','yuri@luv2code.com',9949975883),
	(5,'Juan','Vega','juan@luv2code.com',9949975884);

	
	
	DROP TABLE IF EXISTS `interview`;

CREATE TABLE `interview` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) DEFAULT NULL,
  `interviewer_name` varchar(45) DEFAULT NULL,
  `interview_name` varchar(45) DEFAULT NULL,
  `userskills` varchar(45) DEFAULT NULL,
  `time` TIMESTAMP DEFAULT NULL,
  `date` DATETIME DEFAULT NULL,
  `interview_status` varchar(45) DEFAULT NULL,
  `remarks` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_INSTRUCTOR_idx` (`instructor_id`),
  
  CONSTRAINT `FK_INSTRUCTOR` 
  FOREIGN KEY (`userid`) 
  REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


INSERT INTO `interview` VALUES 
	(1,1,'Leslie Andrews','java full stack','Springboot components',CURTIME(),curdate(),'assigned','lets see'),
	(2,1,'Leslie Andrews','java full stack','Springboot components',CURTIME(),curdate(),'assigned','lets see'),
	(3,2,'Emma Baumgarten','java full stack','Hibernate,Springboot components',CURTIME(),curdate(),'assigned','lets see'),
	(4,2,'Emma Baumgarten','java full stack','Hibernate,Springboot components',CURTIME(),curdate(),'assigned','lets see');
