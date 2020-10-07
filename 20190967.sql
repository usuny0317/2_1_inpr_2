CREATE TABLE `s_member` (
        `uid` varchar(10) NOT NULL,
        `name` varchar(15) NOT NULL,
        `passwd` varchar(12) NOT NULL,
        `email` varchar(15) DEFAULT NULL,
        `date` datetime NOT NULL,
        PRIMARY KEY (`uid`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

    CREATE TABLE `s_message` (
        `mid` int(11) NOT NULL AUTO_INCREMENT,
        `uid` varchar(15) NOT NULL,
        `msg` varchar(100) NOT NULL,
        `favcount` int(11) DEFAULT '0',
        `replycount` int(11) DEFAULT '0',
        `date` datetime NOT NULL,
        PRIMARY KEY(mid)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

    CREATE TABLE `s_reply` (
    `rid` int(11) NOT NULL AUTO_INCREMENT,
    `mid` int(11) NOT NULL,
    `uid` varchar(12) NOT NULL,
    `date` datetime NOT NULL,
    `rmsg` varchar(50) NOT NULL,
    PRIMARY KEY (rid),
    KEY `meaage_FX_idx` (`mid`),
    CONSTRAINT `message_FX` FOREIGN KEY (`mid`) REFERENCES `s_message` (`mid`) ON DELETE CASCADE ON UPDATE NO ACTION
    ) ENGINE=InnoDB AUTO_INCREMENT=134 DEFAULT CHARSET=UTF8;