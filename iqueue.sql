insert into doctorinfo (dId, name, cId, profile,
preTreatId, inTreatId, afterTreatId) values("doc1378", "李子峰", "ks301", "主任医师 心导管室副主任", "pre1378", "in1378", "after1378")


update doctorinfo set startTime=Now(), endTime=Now() where dId = "doc5437"

CREATE TABLE `clinic` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `cId` varchar(255) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `name` varchar(255) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `oId` varchar(255) CHARACTER SET utf8 NOT NULL DEFAULT '',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=latin1;

CREATE TABLE `doctorinfo` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `dId` varchar(255) NOT NULL DEFAULT '',
  `name` varchar(255) NOT NULL DEFAULT '',
  `cId` varchar(255) NOT NULL DEFAULT '',
  `profile` varchar(255) DEFAULT '',
  `startTime` varchar(255) DEFAULT NULL,
  `endTime` varchar(255) DEFAULT NULL,
  `preTreatId` varchar(255) NOT NULL DEFAULT '0000-00-00',
  `inTreatId` varchar(255) NOT NULL DEFAULT '',
  `afterTreatId` varchar(255) NOT NULL DEFAULT '',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;


CREATE TABLE `office` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `o_id` varchar(25) NOT NULL DEFAULT '',
  `name` varchar(255) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `firTreatId` varchar(255) DEFAULT NULL,
  `secTreatId` varchar(255) DEFAULT NULL,
  `dispatchTreatId` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;

CREATE TABLE `patientinfo` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `pId` varchar(20) NOT NULL DEFAULT '',
  `name` varchar(255) NOT NULL DEFAULT '',
  `cardNumber` varchar(255) NOT NULL DEFAULT '',
  `sex` varchar(20) NOT NULL DEFAULT '',
  `age` varchar(10) NOT NULL DEFAULT '0',
  `registerTime` varchar(100) NOT NULL DEFAULT '0000-00-00 00:00:00',
  `arriveTime` varchar(100) NOT NULL DEFAULT '0000-00-00 00:00:00',
  `qId` varchar(255) DEFAULT '',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

CREATE TABLE `signinfo` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `pId` varchar(255) DEFAULT NULL,
  `height` varchar(24) DEFAULT NULL,
  `weight` varchar(24) DEFAULT '0',
  `temperature` varchar(24) DEFAULT '0',
  `respiration` varchar(51) DEFAULT '0',
  `pulse` varchar(51) DEFAULT NULL,
  `bloodPressure` varchar(51) DEFAULT NULL,
  `bloodSugar` varchar(60) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;


CREATE TABLE `user` (
  `u_Id` int(11) NOT NULL AUTO_INCREMENT,
  `o_id` varchar(20) NOT NULL DEFAULT '',
  `username` varchar(20) NOT NULL DEFAULT '',
  `password` varchar(20) NOT NULL,
  `status` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`u_Id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;
