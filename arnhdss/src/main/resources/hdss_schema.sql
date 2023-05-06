ALTER TABLE visit ADD COLUMN submitDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP;
ALTER TABLE death ADD COLUMN submitDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP;
ALTER TABLE individual ADD COLUMN submitDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP;
ALTER TABLE inmigration ADD COLUMN submitDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP;
ALTER TABLE outmigration ADD COLUMN submitDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP;
ALTER TABLE pregnancyobservation ADD COLUMN submitDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP;
ALTER TABLE residency ADD COLUMN submitDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP;
ALTER TABLE location ADD COLUMN submitDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP;
ALTER TABLE relationship ADD COLUMN submitDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP;
ALTER TABLE sociodemographic ADD COLUMN submitDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP;
ALTER TABLE pregnancyoutcome ADD COLUMN submitDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP;
ALTER TABLE demographic ADD COLUMN submitDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP;
ALTER TABLE visit ADD UNIQUE INDEX `extId_UNIQUE` (`visitExtId` ASC);
ALTER TABLE location ADD UNIQUE INDEX `extId_loc_UNIQUE` (`compextId` ASC);
ALTER TABLE location ADD UNIQUE INDEX `extId_comp_UNIQUE` (`compno` ASC);

INSERT IGNORE INTO `user` (`firstName`, `lastName`, `password`, `username`)
 VALUES ('Administrator', 'System', '$2a$10$t8y21LV71ZS7HmJaF19u.urUf4xuHydQ8QsF952TWKJhdXUbMRAVO', 'admin');

-- password: data
-- fieldworker
INSERT IGNORE INTO fieldworker (fw_uuid, username, firstName, lastName, password, status,insertDate) 
VALUES ('UnknownFieldWorker','UNK', 'Unknown', 'FieldWorker', 'invalid-password-hash', 0,'2023-05-12 15:07:43');
-- individual
INSERT IGNORE INTO `individual`(individual_uuid,extId,firstName,otherName,lastName,gender,dob,mother_uuid,father_uuid,insertDate,fw_uuid,dobAspect) 
VALUES('Unknown Individual','UNK','UNK',NULL,'UNK',1, '1900-12-19 15:07:43', 'Unknown Individual', 'Unknown Individual','2023-05-12 15:07:43','UnknownFieldWorker',1);
-- locationhierarchy
INSERT IGNORE INTO locationhierarchy(uuid,name,villcode,level_uuid,parent_uuid) VALUES('hierarchy_root','', 'HIERARCHY_ROOT', NULL,NULL);

