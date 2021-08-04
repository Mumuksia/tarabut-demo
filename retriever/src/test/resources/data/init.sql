DROP TABLE IF EXISTS `preferences`;
CREATE TABLE `preferences` (
                               `ID` int(11) NOT NULL,
                               `USER_IDENTIFIER` text NOT NULL,
                               `SMS` BOOLEAN NOT NULL DEFAULT 0,
                               `POST` BOOLEAN NOT NULL DEFAULT 0,
                               `EMAIL` BOOLEAN NOT NULL DEFAULT 0,
                               `CREATED_AT` DATETIME DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
ALTER TABLE `preferences` ADD PRIMARY KEY (`ID`);
ALTER TABLE `preferences` MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
