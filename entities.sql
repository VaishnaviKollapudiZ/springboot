
use `hb-05-many-to-many`; #database name

SET FOREIGN_KEY_CHECKS = 0;


DROP TABLE IF EXISTS `course`;

CREATE TABLE `course` (
  `id` integer NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `title` varchar(128) DEFAULT NULL,
  `duration_in_weeks` integer
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=latin1;


DROP TABLE IF EXISTS `student`;

CREATE TABLE `student` (
  `id` integer NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL

) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `student_has_course`;

CREATE TABLE `student_has_course` (
  `course_id` integer NOT NULL,
  `student_id` integer NOT NULL,
  
  PRIMARY KEY (`course_id`,`student_id`),
  
  KEY `FK_STUDENT_idx` (`student_id`),
  
  CONSTRAINT `FK_COURSE_05` FOREIGN KEY (`course_id`) 
  REFERENCES `course` (`id`) 
  ON DELETE NO ACTION ON UPDATE NO ACTION,
  
  CONSTRAINT `FK_STUDENT` FOREIGN KEY (`student_id`) 
  REFERENCES `student` (`id`) 
  ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

SET FOREIGN_KEY_CHECKS = 1;

#select * from student;
#select * from course;
#select * from student_has_course;
#select * from users;
#select * from authorities;



