INSERT IGNORE INTO user_table(username, user_email, user_enabled, user_password, user_fname, user_lname)
    VALUES (
        'admin'
	    , 'webadmin@hdss.com'
	    , true
	    , '$2a$10$SLlNbnvkIqatweZxewyZUeF6yrGexjppQpJgntGXCxWMQCaT3ORdi'
	    , 'Administrator'
	    , 'System'
    );

-- pw: admin

	INSERT IGNORE INTO group_table(group_role, group_desc) values('ROLE_CONTROLLER', 'Systems Administrator');
  	INSERT IGNORE INTO group_table(group_role, group_desc) values('ROLE_COORDINATOR', 'Coordinator');
    INSERT IGNORE INTO group_table(group_role, group_desc) values('ROLE_MANAGER', 'Data Manager');


	DELETE FROM user_group WHERE username='webadmin';
    INSERT IGNORE INTO user_group(username, group_role) values('admin', 'ROLE_CONTROLLER');
    INSERT IGNORE INTO user_group(username, group_role) values('admin', 'ROLE_COORDINATOR');
    INSERT IGNORE INTO user_group(username, group_role) values('admin', 'ROLE_MANAGER');


ALTER TABLE visit ADD COLUMN submitDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP;
ALTER TABLE death ADD COLUMN submitDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP;
ALTER TABLE individual ADD COLUMN submitDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP;
ALTER TABLE inmigration ADD COLUMN submitDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP;
ALTER TABLE outmigration ADD COLUMN submitDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP;
ALTER TABLE pregnancyobservation ADD COLUMN submitDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP;
ALTER TABLE residency ADD COLUMN submitDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP;
ALTER TABLE location ADD COLUMN submitDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP;
ALTER TABLE relationship ADD COLUMN submitDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP;
ALTER TABLE socialgroup ADD COLUMN submitDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP;
ALTER TABLE sociodemographic ADD COLUMN submitDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP;
ALTER TABLE pregnancyoutcome ADD COLUMN submitDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP;
ALTER TABLE demographic ADD COLUMN submitDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP;
ALTER TABLE listing ADD COLUMN submitDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP;


-- fieldworker
INSERT IGNORE INTO fieldworker (fw_uuid, username, firstName, lastName, password, status,insertDate) 
VALUES ('UnknownFieldWorker','UNK', 'Unknown', 'FieldWorker', 'invalid-password-hash', 0,'2023-05-12 15:07:43');

-- individual
INSERT IGNORE INTO `individual`(uuid,extId,firstName,otherName,lastName,gender,dob,mother_uuid,father_uuid,insertDate,fw_uuid,dobAspect) 
VALUES('UNK','UNK','UNK',NULL,'UNK',1, '1800-12-19 15:07:43', 'UNK', 'UNK','2023-05-12 15:07:43','UnknownFieldWorker',1);
-- locationhierarchy
INSERT IGNORE INTO locationhierarchy(uuid,name,extId,level_uuid,parent_uuid) VALUES('hierarchy_root','', 'HIERARCHY_ROOT', NULL,NULL);


INSERT IGNORE INTO task (`data`, `fileName`, `timestamp`, `total`, `type`) VALUES ('294.00 bytes', 'individuals', '2023-05-19 11:32:55', 1, 'application/octet-stream');
INSERT IGNORE INTO task (`data`, `fileName`, `timestamp`, `total`, `type`) VALUES ('140.00 bytes', 'locations', '2023-05-19 11:32:57', 0, 'application/octet-stream');
INSERT IGNORE INTO task (`data`, `fileName`, `timestamp`, `total`, `type`) VALUES ('142.00 bytes', 'residency', '2023-05-19 11:32:59', 0, 'application/octet-stream');
INSERT IGNORE INTO task (`data`, `fileName`, `timestamp`, `total`, `type`) VALUES ('146.00 bytes', 'socialgroup', '2023-05-19 11:33:00', 0, 'application/octet-stream');
INSERT IGNORE INTO task (`data`, `fileName`, `timestamp`, `total`, `type`) VALUES ('148.00 bytes', 'relationship', '2023-05-19 11:33:01', 0, 'application/octet-stream');
INSERT IGNORE INTO task (`data`, `fileName`, `timestamp`, `total`, `type`) VALUES ('142.00 bytes', 'pregnancy', '2023-05-19 11:33:02', 0, 'application/octet-stream');
INSERT IGNORE INTO task (`data`, `fileName`, `timestamp`, `total`, `type`) VALUES ('148.00 bytes', 'demographics', '2023-05-19 11:33:04', 0, 'application/octet-stream');
INSERT IGNORE INTO task (`data`, `fileName`, `timestamp`, `total`, `type`) VALUES ('130.00 bytes', 'ses', '2023-05-19 11:33:05', 0, 'application/octet-stream');
INSERT IGNORE INTO task (`data`, `fileName`, `timestamp`, `total`, `type`) VALUES ('146.00 bytes', 'vaccination', '2023-05-19 11:33:05', 0, 'application/octet-stream');


-- KEYS

-- Inmigration
alter table inmigration add index FKD6922049851505F6 (residency_uuid), add constraint FKD6922049851505F6 foreign key (residency_uuid) references residency (uuid);

-- Outmigration
alter table outmigration add index FKE109DC40851505F6 (residency_uuid), add constraint FKE109DC40851505F6 foreign key (residency_uuid) references residency (uuid);
-- Profile
alter table sociodemographic add index FKFD3DA29930076DBC (socialgroup_uuid), add constraint FKFD3DA29930076DBC foreign key (socialgroup_uuid) references socialgroup (uuid);
-- Death
alter table death add index FK5B0927480470E9E (individual_uuid), add constraint FK5B0927480470E9E foreign key (individual_uuid) references individual (uuid);
alter table demographic add index FK5B0927480470E9C (individual_uuid), add constraint FK5B0927480470E9C foreign key (individual_uuid) references individual (uuid);


-- Outcome
alter table outcome add index FKBE0C0752948ED5FB (childuuid), add constraint FKBE0C0752948ED5FB foreign key (childuuid) references individual (uuid);

alter table listing add index FKD6922049851605F6 (compno), add constraint FKD6922049851605F6 foreign key (compno) references location (compno);
alter table vaccination add index FKD6922049851605FV (individual_uuid), add constraint FKD6922049851605FV foreign key (individual_uuid) references individual (uuid);
alter table individual ADD UNIQUE INDEX FK6B04D4BEC630DB8 (uuid);
alter table duplicate ADD UNIQUE INDEX FK6B04D4BEC630DBD (individual_uuid);


INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('assist', '1', 'Doctor');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('assist', '2', 'Nurse/Midwife');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('assist', '3', 'TBA');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('assist', '4', 'Relative/Friend');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('assist', '5', 'No One');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('assist', '6', 'Other');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('birthPlace', '5', 'At home');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('birthPlace', '1', 'Hospital');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('birthPlace', '4', 'Traditional Birth Attendant\'s Home');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('birthPlace', '2', 'Health Center/Clinic');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('birthPlace', '9', 'On the way to clinic/hosptal');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('birthPlace', '3', 'Private maternity home');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('birthPlace', '6', 'Other');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('birthPlace', '7', 'CHPS');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('comingfrom', '1', 'Within this community or village');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('comingfrom', '2', 'From another community or village within the HDSS area');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('comingfrom', '3', 'From another community within the Region (Bono East) but not in the HDSS area');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('comingfrom', '4', 'Within Ghana but outside the region (Bono East)');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('comingfrom', '5', 'Africa');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('comingfrom', '6', 'Europe');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('comingfrom', '7', 'North America');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('comingfrom', '8', 'South America');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('comingfrom', '9', 'Asia');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('comingfrom', '10', 'Australia');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('complete', '1', 'Yes');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('complete', '2', 'No');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('COOKING_LOC_FCORRES', '1', 'In a separate building');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('COOKING_LOC_FCORRES', '2', 'Fully outdoors (no structure)');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('COOKING_LOC_FCORRES', '3', 'Semi-enclosed outdoor structure');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('deathCause', '1', 'Malaria');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('deathCause', '2', 'HIV/AIDS');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('deathCause', '3', 'TB');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('deathCause', '4', 'Other viral/bacterial infection');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('deathCause', '5', 'Accident');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('deathCause', '6', 'Crime');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('deathCause', '7', 'Age');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('deathCause', '8', 'Unknown');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('deathCause', '77', 'Other');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('deathCause', '10', 'Disease, Illness');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('deathPlace', '1', 'HOME');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('deathPlace', '2', 'HOSPITAL');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('deathPlace', '3', 'HEALTH CENTER');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('deathPlace', '4', 'TRADITIONAL HEALER');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('deathPlace', '5', 'PRIVATE MATERNITY HOME');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('deathPlace', '6', 'ON ROUTE TO HEALTH FACILITY');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('deathPlace', '77', 'OTHER');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('education', '1', 'None');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('education', '6', 'Creche');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('education', '2', 'Primary');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('education', '3', 'JHS');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('education', '4', 'secondary');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('education', '5', 'Tertiary');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('endType', '3', 'Death');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('endType', '1', 'Active');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('endType', '2', 'Outmigration');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('EXT_WALL_FCORRES', '8', 'No walls');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('EXT_WALL_FCORRES', '9', 'Cane/palm/trunks');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('EXT_WALL_FCORRES', '10', 'Dirt/mud');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('EXT_WALL_FCORRES', '11', 'Dung');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('EXT_WALL_FCORRES', '12', 'Straw/grass/sod');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('EXT_WALL_FCORRES', '13', 'Bamboo with mud');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('EXT_WALL_FCORRES', '14', 'Stone with mud');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('EXT_WALL_FCORRES', '15', 'Uncovered adobe- clay bricks dried in the sun');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('EXT_WALL_FCORRES', '16', 'Covered adobe - clay bricks dried in the sun with protective covering (i.e. thin layer of cement)');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('EXT_WALL_FCORRES', '17', 'Plywood');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('EXT_WALL_FCORRES', '18', 'Cardboard');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('EXT_WALL_FCORRES', '19', 'Reused wood');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('EXT_WALL_FCORRES', '20', 'Cement');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('EXT_WALL_FCORRES', '21', 'Stone with lime/cement');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('EXT_WALL_FCORRES', '22', 'Bricks');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('EXT_WALL_FCORRES', '23', 'Cement bricks');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('EXT_WALL_FCORRES', '24', 'Wood planks/shingles');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('EXT_WALL_FCORRES', '77', 'Other');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('farm', '1', 'Food insecurity');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('farm', '2', 'Adequate rains during raining season');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('farm', '3', 'The soil in this area is rich in soil nutrients');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('farm', '4', 'Availability of market for farm produce');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('farm', '5', 'Due to crop failure or persistence crop failure in my previous place');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('farm', '6', 'Because of better land tenure system (Abunu, Abusa) ');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('farm', '7', 'Due to availability of farm lands');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('farm', '8', 'Due to family inheritance or family business');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('farm', '9', 'Farm destroyed by Herdsmen often (Grazing of cattle)');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('farm', '10', 'To generate income');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('farm', '11', 'Availability of land for Animal Grazing');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('farm', '77', 'Other');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('FLOOR_FCORRES', '1', 'Earth/sand');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('FLOOR_FCORRES', '2', 'Dung');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('FLOOR_FCORRES', '3', 'Wood planks');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('FLOOR_FCORRES', '4', 'Palm/bamboo');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('FLOOR_FCORRES', '5', 'Parquet or polished wood');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('FLOOR_FCORRES', '6', 'Vinyl or asphalt strips');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('FLOOR_FCORRES', '7', 'Ceramic tiles');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('FLOOR_FCORRES', '8', 'Cement/concrete');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('FLOOR_FCORRES', '9', 'Carpet');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('FLOOR_FCORRES', '77', 'Other');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('food', '1', 'Food items mainly for home consumption');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('food', '2', 'Food items mainly for sale on market');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('food', '3', 'Food items mainly for home consumption and sale on market');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('FRQ', '1', 'Daily');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('FRQ', '2', 'Weekly');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('FRQ', '3', 'Monthly');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('FRQ', '4', 'Less than once a month');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('FRQ', '5', 'Never (only smoke outside)');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('gender', '1', 'Male');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('gender', '2', 'Female');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('groupType', '1', 'Family');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('groupType', '2', 'Cohort');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('grow', '1', 'Cash crops: mango, cashew, cocoa, ginger, tobacco, etc.');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('H2O_FCORRES', '1', 'Piped water into dwelling');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('H2O_FCORRES', '2', 'Piped to yard/plot');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('H2O_FCORRES', '3', 'Public tap/standpipe');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('H2O_FCORRES', '4', 'Tube well or borehole');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('H2O_FCORRES', '5', 'Protected well');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('H2O_FCORRES', '6', 'Unprotected well');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('H2O_FCORRES', '7', 'Water from spring (protected)');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('H2O_FCORRES', '8', 'Water from spring (unprotected) ');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('H2O_FCORRES', '9', 'Tanker truck');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('H2O_FCORRES', '10', 'Cart with small tank');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('H2O_FCORRES', '11', 'Rainwater');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('H2O_FCORRES', '12', 'Surface water (river/dam/lake/pond/stream/canal/irrigation channel)');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('H2O_FCORRES', '13', 'Bottled water or sachets');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('H2O_FCORRES', '14', 'Water vendor (tap)');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('H2O_FCORRES', '77', 'Other');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('HEAD_HH_FCORRES', '1', 'Pregnant woman');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('HEAD_HH_FCORRES', '2', 'Husband/partner of pregnant woman');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('HEAD_HH_FCORRES', '3', 'Mother/mother in-law of pregnant woman');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('HEAD_HH_FCORRES', '4', 'Father/father in-law of pregnant woman');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('HEAD_HH_FCORRES', '77', 'Other');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('howdel', '1', 'Normal/Spontaneous');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('howdel', '2', 'Caesarian Section');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('howdel', '3', 'Forceps');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('howdel', '4', 'Other');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('INF_OHOLOC', '1', 'KINTAMPO HOSPITAL KINTAMPO');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('INF_OHOLOC', '2', 'ADOM MAT. HOME JEMA');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('INF_OHOLOC', '3', 'JEMA HOSPITAL, JEMA');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('INF_OHOLOC', '4', 'ANYIMA HEALTH CENTRE, ANYIMA');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('INF_OHOLOC', '5', 'AMOMA HEALTH CENTRE, AMOMA');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('INF_OHOLOC', '6', 'NEW LONGORO, HEALTH CENTRE');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('INF_OHOLOC', '7', 'DAWADAWA, HEALTH CENTRE');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('INF_OHOLOC', '8', 'PERPERTUAL MAT. HOME APESIKA');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('INF_OHOLOC', '9', 'KUNSU HEALTH CENTRE, KUNSU');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('INF_OHOLOC', '10', 'EBENEZER MAT. HOME KINTAMPO');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('INF_OHOLOC', '11', 'ARMS MAT. HOME AJINA');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('INF_OHOLOC', '12', 'BUIPE HEALTH CENTRE, BUIPE');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('INF_OHOLOC', '13', 'BUSUAMA HEALTH CENTRE, BUSUAMA');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('INF_OHOLOC', '14', 'ANNOA ASARE MEMORIAL CLINIC');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('INF_OHOLOC', '15', 'PRINCE OF PEACE MATERNITY CLINIC');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('INF_OHOLOC', '16', 'KADELSO HEALTH CENTRE');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('INF_OHOLOC', '17', 'GULUMPE HEALTH CENTRE(POLYCLINIC)');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('INF_OHOLOC', '18', 'MANSIE HEALTH CENTRE, MANSIE');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('INF_OHOLOC', '19', 'SUNKWA CLINIC KINTAMPO');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('INF_OHOLOC', '20', 'HOLY FAMILTY HOSPITAL, TECHIMAN');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('INF_OHOLOC', '21', 'ARMS MAT. HOME TECHIMAN');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('INF_OHOLOC', '22', 'AHAMADIYA HOSPITAL TECHIMAN');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('INF_OHOLOC', '23', 'BLUE CROSS CLINIC,TECHIMAN');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('INF_OHOLOC', '24', 'STATION CLINIC, TECHIMAN');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('INF_OHOLOC', '25', 'OPOKU AGYEMANG HOSPITAL, TECHIMAN');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('INF_OHOLOC', '26', 'FORIKROM HEALTH CENTRE, FORIKROM');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('INF_OHOLOC', '27', 'BOURYEM HEALTH CENTRE, BOURYEM');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('INF_OHOLOC', '28', 'OFFUMAN HEALTH CENTRE, OFFUMAN');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('INF_OHOLOC', '29', 'AYORYA CHIPS COMPOUND');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('INF_OHOLOC', '30', 'CHIPS COMPOUND, KAWAMPE');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('INF_OHOLOC', '31', 'CHIPS COMPOUND, PORTOR');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('INF_OHOLOC', '32', 'CHIPS COMPOUND, ATTA AKURA');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('INF_OHOLOC', '33', 'CHIPS COMPOUND, BABATOR');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('INF_OHOLOC', '34', 'CHIPS COMPOUND, ASANTEKWAA');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('INF_OHOLOC', '35', 'CHIPS COMPOUND, DWERE');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('INF_OHOLOC', '36', 'CHIPS COMPOUND, SABULE');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('INF_OHOLOC', '37', 'CHIPS COMPOUND, BANIANTWE');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('INF_OHOLOC', '38', 'CHIPS COMPOUND, AMPOMA');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('INF_OHOLOC', '39', 'CHIPS COMPOUND, NTANKRO');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('INF_OHOLOC', '40', 'TUPBODOM, HEALTH CENTRE, TUOBODOM');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('INF_OHOLOC', '41', 'AWOROWA HEALTH CENTRE, AWOROWA');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('INF_OHOLOC', '42', 'TANOSO HEALTH CENTRE, TANOSO');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('INF_OHOLOC', '43', 'WENCHI HOSPITAL, WENCHI');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('INF_OHOLOC', '44', 'EBENEZER MAT. HOME, WENCHI');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('INF_OHOLOC', '45', 'EMIL MEMORIAL HOSPITAL, WENCHI');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('INF_OHOLOC', '46', 'NSAWKAW, HEALTH CENTRE, NSAWKAW');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('INF_OHOLOC', '47', 'BADU HEALTH CENTRE, BADU');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('INF_OHOLOC', '48', 'ST. JOSEPH’S HEALTH CENTRE, KOASE');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('INF_OHOLOC', '49', 'NCHIRAA HEALTH CENTRE, NCHIRAA');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('INF_OHOLOC', '50', 'SUBINSO HEALTH CENTRE, SUBINSO');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('INF_OHOLOC', '51', 'MENJI HEALTH CENTRE, MENJI');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('INF_OHOLOC', '52', 'HOLY FAMILY, BEREKUM');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('INF_OHOLOC', '53', 'BANDA-AHENKRO CLINIC');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('INF_OHOLOC', '54', 'SAMPA HOSPITAL');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('INF_OHOLOC', '55', 'DEBIBI HEALTH CENTRE');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('INF_OHOLOC', '56', 'SEIKWA HEALTH CLINIC');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('INF_OHOLOC', '57', 'NYAASE CLINIC, NYAASE');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('INF_OHOLOC', '58', 'CHIPS COMPOUND, KWABIA');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('INF_OHOLOC', '59', 'CHIPS COMPOUND, KRABONSO');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('INF_OHOLOC', '60', 'ST. THERESA’S HOSPITAL, NKORANZA');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('INF_OHOLOC', '61', 'NKORANZA HEALTH CENTRE, NKORANZA');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('INF_OHOLOC', '62', 'BONSU HEALTH CENTRE, BONSU');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('INF_OHOLOC', '63', 'NKWABENG HEALTH CENTRE, NKWABEN');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('INF_OHOLOC', '64', 'YEFRI HEALTH CENTRE, YEFRI');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('INF_OHOLOC', '65', 'AKUMA HEALTH CENTRE, AKUMA');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('INF_OHOLOC', '66', 'KRANKA HEALTH CENTRE, NKRANKA');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('INF_OHOLOC', '67', 'DROMANKESE, HEALTH CENTRE, DROMANKESE');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('INF_OHOLOC', '68', 'BUSUNYA HEALTH CENTRE, BUSUNYA');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('INF_OHOLOC', '69', 'CHIPS COMPOUND, DUMSO');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('INF_OHOLOC', '70', 'CHIPS COMPOUND, PANINAMISA');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('INF_OHOLOC', '71', 'HOSPITAL YEZURA , KINTAMPO');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('INF_OHOLOC', '72', 'SUNYANI REGIONAL HOSPITAL, SUNYANI');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('INF_OHOLOC', '73', 'CHIPS COMPOUND, MIAWANI');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('INF_OHOLOC', '74', 'CHIPS COMPOUND, NANTE');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('INF_OHOLOC', '75', 'BAMBOI HEALTH CENTRE, BAMBOI');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('INF_OHOLOC', '76', 'NOT KNOWN');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('INF_OHOLOC', '77', 'HOSPITAL YEZURA, BUIPE(HOLISTIC MEDICARE)');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('INF_OHOLOC', '78', 'CLINIC ANNOR ASARE, BUIPE');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('INF_OHOLOC', '79', 'CLINIC INTEGRATED DEVPT. , KINTAMPO');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('INF_OHOLOC', '80', 'CHIPS COMPOUND, PRAMPOSO');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('INF_OHOLOC', '81', 'CHIPS COMPOUND, KURAWURAKURA');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('INF_OHOLOC', '82', 'CHIPS COMPOUND, KOKUMA');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('INF_OHOLOC', '83', 'CHIPS COMPOUND, BADU AKURA');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('INF_OHOLOC', '84', 'CHIPS COMPOUND, JATO AKURA');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('INF_OHOLOC', '85', 'CHIPS COMPOUND, NTAREBAN');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('INF_OHOLOC', '86', 'HOME');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('INF_OHOLOC', '87', 'CHIPS COMPOUND, MANSRA');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('INF_OHOLOC', '101', 'HEALTH CENTRE APESIKA');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('INF_OHOLOC', '89', 'CHIPS COMPOUND DROMANKUMA');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('INF_OHOLOC', '90', 'CHIPS COMPOUND BOMINI');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('INF_OHOLOC', '91', 'CHIPS COMPOUND FIEMA');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('INF_OHOLOC', '92', 'CHIPS COMPOUND BONTE');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('INF_OHOLOC', '93', 'CHIPS COMPOUND BODOM');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('INF_OHOLOC', '94', 'CHIPS COMPOUND, MANSO');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('INF_OHOLOC', '95', 'CHIPS COMPOUND ASEKYE');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('INF_OHOLOC', '96', 'CHIPS COMPOUND ASUEYI');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('INF_OHOLOC', '97', 'CHIPS COMPOUND MESIDAN');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('INF_OHOLOC', '98', 'CHIPS COMPOUND KROBO');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('INF_OHOLOC', '100', 'CHIPS COMPOUND AYEASU');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('JOB', '1', 'Salaried worker (e.g., teacher, nurse, office)');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('JOB', '2', 'Small business');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('JOB', '3', 'Business owner');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('JOB', '4', 'Skilled labor');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('JOB', '5', 'Unskilled labor');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('JOB', '6', 'Subsistence farming');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('JOB', '7', 'Commercial farming');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('JOB', '8', 'fishing');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('JOB', '9', 'Housewife');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('JOB', '10', 'Not applicable/Not working');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('JOB', '77', 'Other');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('live', '1', 'Livestock mainly for home consumption');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('live', '2', 'Livestock mainly for sale on market');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('locationType', '1', 'Rural');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('locationType', '2', 'Urban');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('marital', '1', 'Married');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('marital', '2', 'Widowed');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('marital', '3', 'Single');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('marital', '4', 'Divorced');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('marital', '5', 'Separated');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('marital', '6', 'Cohabitation');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('marital', '7', 'Child/Minor');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('MARITAL_SCORRES', '1', 'Married');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('MARITAL_SCORRES', '2', 'Not married but living with partner (cohabitating)');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('MARITAL_SCORRES', '3', 'Divorced or permanently separated');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('MARITAL_SCORRES', '4', 'Widowed');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('MARITAL_SCORRES', '5', 'Single, never married');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('migType', '1', 'External Inmigration');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('migType', '2', 'Internal Inmigration');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('MOBILE_ACCESS_FCORRES', '1', 'Yes- I have my own mobile phone');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('MOBILE_ACCESS_FCORRES', '2', 'Yes, but I share the mobile phone with other people');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('MOBILE_ACCESS_FCORRES', '3', 'No, I do not have access to a mobile phone');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('notdel', '1', 'Not necessary');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('notdel', '2', 'Not customary');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('notdel', '3', 'Lack of money');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('notdel', '4', 'Too far');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('notdel', '5', 'Transport problem');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('notdel', '6', 'No one to accompany');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('notdel', '7', 'Good services not available');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('notdel', '8', 'Not permitted by family');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('notdel', '9', 'Better service at home');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('notdel', '10', 'No female doctor available');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('notdel', '11', 'Inconvenient service hour');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('notdel', '12', 'Afraid to go');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('notdel', '13', 'Long waiting time');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('notdel', '14', 'Religious Reasons');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('notdel', '15', 'Other');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('occupation', '1', 'Farmer');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('occupation', '2', 'Employed farmer');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('occupation', '3', 'Merchant');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('occupation', '4', 'Apprentiship');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('occupation', '5', 'Private employee');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('occupation', '6', 'Petty trade');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('occupation', '7', 'Housewife ');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('occupation', '8', 'Student ');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('occupation', '9', 'Unemployed ');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('occupation', '10', 'Retired ');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('occupation', '11', 'Government Employee ');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('occupation', '12', 'NGO Worker ');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('occupation', '13', 'Daily Laborer ');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('occupation', '14', 'Self Employed ');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('occupation', '15', 'Child/Minor');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('occupation', '16', 'Religious leader');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('occupation', '77', 'Other');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('outcometype', '1', 'Live Birth');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('outcometype', '2', 'Still Birth');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('outcometype', '3', 'Miscarriage');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('outcometype', '4', 'Abortion');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('OWN_RENT_SCORRES', '1', 'Sole Ownership');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('OWN_RENT_SCORRES', '2', 'Joint Ownership');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('OWN_RENT_SCORRES', '3', 'Family/relation’s house');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('OWN_RENT_SCORRES', '4', 'House provided rent free');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('OWN_RENT_SCORRES', '5', 'Renting');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('OWN_RENT_SCORRES', '6', 'Perching');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('OWN_RENT_SCORRES', '77', 'Other');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('PD_DM_SCORRES', '1', 'Participant');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('PD_DM_SCORRES', '2', 'Husband/partner');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('PD_DM_SCORRES', '3', 'Participant and husband/partner jointly');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('PD_DM_SCORRES', '4', 'Mother-in-law');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('PD_DM_SCORRES', '5', 'Father-in-law');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('PD_DM_SCORRES', '77', 'Other');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('reason', '1', 'Due to family separation (Including Divorce)');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('reason', '2', 'Due to health conditions');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('reason', '3', 'Due to marriage');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('reason', '4', 'I lost my job and income ');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('reason', '5', 'Due to retirement');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('reason', '6', 'To learn a trade or apprentiship');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('reason', '7', 'Moved to own house');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('reason', '8', 'Due to Work or transfer by my organization (employer)');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('reason', '9', 'Farming');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('reason', '10', 'Business/Trade');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('reason', '11', 'Returned after completion of education ');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('reason', '12', 'Due to climate change (such as changes in temperature, rainfall pattern, increasing levels of greenhouse gases etc)');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('reason', '13', 'Natural disaster (such as Famine, drought, flooding etc) ');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('reason', '14', 'Due to political unrest or instability (communal, tribal or religious conflict) ');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('reason', '15', 'New House');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('reason', '16', 'Came with/to relative(s)/parents');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('reason', '17', 'Came with spouse');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('reason', '18', 'Education/School');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('reason', '19', 'MISSEDOUT');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('reason', '20', 'Death of benefactor/Guardian');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('reason', '77', 'Other');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('reasonForOutMigration', '1', 'Moved with relative(s)/Parents');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('reasonForOutMigration', '2', 'Farming');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('reasonForOutMigration', '3', 'Marital change');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('reasonForOutMigration', '4', 'Trading/Business');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('reasonForOutMigration', '5', 'Education/School');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('reasonForOutMigration', '6', 'Health Reasons');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('reasonForOutMigration', '7', 'Employment other  than farming');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('reasonForOutMigration', '8', 'Moved to new house/Change of Accomodation');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('reasonForOutMigration', '9', 'NOT KNOWN');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('reasonForOutMigration', '77', 'OTHER');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('relationshipType', '2', 'Married');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('relationshipType', '3', 'Separated/Divorced');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('relationshipType', '4', 'Widowed');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('relationshipType', '5', 'Living Together');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('relendType', '1', 'Active');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('relendType', '4', 'Dead');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('relendType', '2', 'Widowed');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('relendType', '3', 'Divorced');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('religion', '1', 'Christian');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('religion', '2', 'Muslim');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('religion', '3', 'Traditional');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('religion', '4', 'Hindu');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('religion', '5', 'Buddhism');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('religion', '6', 'Judaism');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('religion', '7', 'Zoroastrianism');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('religion', '8', 'Shintoism');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('religion', '9', 'Sikhism');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('religion', '10', 'Atheist');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('religion', '77', 'Other');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('rltnhead', '1', 'Head of Household');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('rltnhead', '2', 'Spouse');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('rltnhead', '3', 'Son/Daughter');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('rltnhead', '4', 'Brother/Sister');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('rltnhead', '5', 'Parent');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('rltnhead', '6', 'Grandchild');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('rltnhead', '7', 'Not Related');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('rltnhead', '8', 'Other Relative');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('rltnhead', '9', 'Don\'t Know');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('ROOF_FCORRES', '1', 'No roof');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('ROOF_FCORRES', '2', 'Thatch/palm leaf');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('ROOF_FCORRES', '3', 'Sod/grass');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('ROOF_FCORRES', '4', 'Rustic mat');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('ROOF_FCORRES', '5', 'Cardboard');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('ROOF_FCORRES', '6', 'Palm/bamboo');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('ROOF_FCORRES', '7', 'Wood planks (rudimentary roofing)');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('ROOF_FCORRES', '8', 'Wood (finished roofing)');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('ROOF_FCORRES', '9', 'Metal (e.g. iron, tin sheets');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('ROOF_FCORRES', '10', 'Calamine/cement fiber');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('ROOF_FCORRES', '11', 'Ceramic tiles');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('ROOF_FCORRES', '12', 'Cement');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('ROOF_FCORRES', '13', 'Roofing shingles');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('ROOF_FCORRES', '77', 'Other');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('shortlong', '1', 'Long (more than 1 year)');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('shortlong', '2', 'Short (up to a year)');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('shortlong', '3', 'Unknown');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('site', '1', 'KHDSS');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('site', '2', 'NHDSS');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('site', '3', 'DHDSS');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('size', '1', 'Very small');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('size', '2', 'Small');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('size', '3', 'Average');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('size', '4', 'Large');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('size', '5', 'Larger than usual');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('startType', '3', 'Enumeration');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('startType', '1', 'Inmigration');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('startType', '2', 'Birth');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('status', '1', 'Active');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('status', '2', 'Commercial');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('status', '3', 'Could not locate');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('status', '4', 'Deserted/Broken');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('status', '5', 'Incomplete');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('status', '6', 'Unoccupied');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('status', '7', 'Not Known');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('STOVE_FCORRES', '1', 'Electric stove');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('STOVE_FCORRES', '2', 'Solar cooker');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('STOVE_FCORRES', '3', 'Liquified petroleum gas/cooking gas stove');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('STOVE_FCORRES', '4', 'Piped natural gas stove');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('STOVE_FCORRES', '5', 'Biogas stove');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('STOVE_FCORRES', '6', 'Liquid fuel stove');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('STOVE_FCORRES', '7', 'Manufactured solid fuel stove');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('STOVE_FCORRES', '8', 'Traditional solid fuel stove');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('STOVE_FCORRES', '9', 'Three stone stove/open fire');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('STOVE_FCORRES', '10', 'No food cooked in household');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('STOVE_FCORRES', '77', 'Other');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('submit', '1', 'Yes');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('submit', '2', 'No');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('TOILET_FCORRES', '1', 'Flush/pour flush to piped sewer system');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('TOILET_FCORRES', '2', 'Flush/pour flush to septic tank');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('TOILET_FCORRES', '3', 'Flush/pour flush to pit latrine');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('TOILET_FCORRES', '4', 'Flush/pour flush to somewhere else');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('TOILET_FCORRES', '5', 'Flush/pour flush, don’t know where');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('TOILET_FCORRES', '6', 'Ventilated improved pit latrine');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('TOILET_FCORRES', '7', 'Pit latrine with slab');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('TOILET_FCORRES', '8', 'Pit latrine without slab/open pit');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('TOILET_FCORRES', '9', 'Composting toilet');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('TOILET_FCORRES', '10', 'Bucket toilet');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('TOILET_FCORRES', '11', 'Hanging toilet/hanging latrine');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('TOILET_FCORRES', '12', 'No facility/bush/field');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('TOILET_FCORRES', '77', 'Other ');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('TOILET_LOC_FCORRES', '1', 'Within the household structure');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('TOILET_LOC_FCORRES', '2', 'Neighbor\'s home/structure');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('TOILET_LOC_FCORRES', '3', 'Public toilet');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('TOILET_LOC_FCORRES', '77', 'other');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('TOILET_SHARE_NUM_FCORRES', '1', 'Less than 5 households');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('TOILET_SHARE_NUM_FCORRES', '2', '5-9 households');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('TOILET_SHARE_NUM_FCORRES', '3', '10 or more households (includes public toilet)');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('tribe', '1', 'Akan');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('tribe', '2', 'Bimoba (Gruma)');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('tribe', '3', 'Chokosi');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('tribe', '4', 'Dagarti');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('tribe', '5', 'Frafra');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('tribe', '6', 'Kusasi');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('tribe', '7', 'Fulani');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('tribe', '8', 'Ga');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('tribe', '9', 'Ewe');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('tribe', '10', 'Adangbe');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('tribe', '11', 'Sisala');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('tribe', '12', 'Wala');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('tribe', '13', 'Gonja');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('tribe', '14', 'Dagomba');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('tribe', '15', 'Mamprusi/Tampruma');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('tribe', '16', 'Konkomba');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('tribe', '17', 'Basare');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('tribe', '18', 'Mo');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('tribe', '19', 'Zambraba');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('tribe', '20', 'Banda');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('tribe', '21', 'Pantra');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('tribe', '22', 'Bawule');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('tribe', '23', 'Dwula (wangara)');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('tribe', '24', 'Grushie (Kasina, Nankana)');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('tribe', '25', 'Bissa(Busanga)');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('tribe', '26', 'Chumburu');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('tribe', '27', 'Kotokoli');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('tribe', '28', 'Mossi');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('tribe', '29', 'Yuroba');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('tribe', '30', 'Igbo');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('tribe', '31', 'Hausa');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('tribe', '32', 'Kambara');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('tribe', '33', 'Kanjaga/Builsa');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('tribe', '77', 'Other');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('whereoutside', '1', 'Within this community or village');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('whereoutside', '2', 'TO another community or village within the HDSS area');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('whereoutside', '3', 'To another community within the Region (Bono East) but not in the HDSS area');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('whereoutside', '4', 'Within Ghana but outside the region (Bono East)');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('whereoutside', '5', 'Africa');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('whereoutside', '6', 'Europe');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('whereoutside', '7', 'North America');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('whereoutside', '8', 'South America');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('whereoutside', '9', 'Asia');
INSERT IGNORE INTO codebook (`codeFeature`, `codeValue`, `codeLabel`) VALUES ('whereoutside', '10', 'Australia');
INSERT IGNORE INTO codebook (`codeFeature`, `codeLabel`, `codeValue`) VALUES ('bnetSou', 'Child Welfare clinic', 1);
INSERT IGNORE INTO codebook (`codeFeature`, `codeLabel`, `codeValue`) VALUES ('bnetSou', 'Bought it', 2);
INSERT IGNORE INTO codebook (`codeFeature`, `codeLabel`, `codeValue`) VALUES ('bnetSou', 'Received as a gift', 3);
INSERT IGNORE INTO codebook (`codeFeature`, `codeLabel`, `codeValue`) VALUES ('bnetSou', 'ANC visit', 4);
INSERT IGNORE INTO codebook (`codeFeature`, `codeLabel`, `codeValue`) VALUES ('bnetSou', 'Received during mass campaign', 5);
INSERT IGNORE INTO codebook (`codeFeature`, `codeLabel`, `codeValue`) VALUES ('bnetSou', 'From NGO', 6);
INSERT IGNORE INTO codebook (`codeFeature`, `codeLabel`, `codeValue`) VALUES ('bnetSou', 'Borrowed', 7);
INSERT IGNORE INTO codebook (`codeFeature`, `codeLabel`, `codeValue`) VALUES ('bnetSou', 'Other', 8);
INSERT IGNORE INTO codebook (`codeFeature`, `codeLabel`, `codeValue`) VALUES ('bnetLoc', 'Still in use', 1);
INSERT IGNORE INTO codebook (`codeFeature`, `codeLabel`, `codeValue`) VALUES ('bnetLoc', 'Being used by other household members', 2);
INSERT IGNORE INTO codebook (`codeFeature`, `codeLabel`, `codeValue`) VALUES ('bnetLoc', 'Lend', 3);
INSERT IGNORE INTO codebook (`codeFeature`, `codeLabel`, `codeValue`) VALUES ('bnetLoc', 'Destroyed', 4);
INSERT IGNORE INTO codebook (`codeFeature`, `codeLabel`, `codeValue`) VALUES ('bnetLoc', 'Other', 5);
INSERT INTO codebook (`codeFeature`, `codeLabel`, `codeValue`) VALUES ('nhis', 'Yes, Active', 1);
INSERT INTO codebook (`codeFeature`, `codeLabel`, `codeValue`) VALUES ('nhis', 'Yes, expired', 2);
INSERT INTO codebook (`codeFeature`, `codeLabel`, `codeValue`) VALUES ('nhis', 'No', 3);
INSERT INTO codebook (`codeFeature`, `codeLabel`, `codeValue`) VALUES ('nhis', 'Card not seen', 4);
INSERT INTO codebook (`codeFeature`, `codeLabel`, `codeValue`) VALUES ('HL', 'Immediate', 1);
INSERT INTO codebook (`codeFeature`, `codeLabel`, `codeValue`) VALUES ('HL', 'Later same day', 2);
INSERT INTO codebook (`codeFeature`, `codeLabel`, `codeValue`) VALUES ('HL', 'Day two(2)', 3);
INSERT INTO codebook (`codeFeature`, `codeLabel`, `codeValue`) VALUES ('HL', 'Three or more days', 4);
INSERT INTO codebook (`codeFeature`, `codeLabel`, `codeValue`) VALUES ('HL', 'Never', 5);
INSERT INTO codebook (`codeFeature`, `codeLabel`, `codeValue`) VALUES ('rea', 'Respiratory infection', 1);
INSERT INTO codebook (`codeFeature`, `codeLabel`, `codeValue`) VALUES ('rea', 'Fever', 2);
INSERT INTO codebook (`codeFeature`, `codeLabel`, `codeValue`) VALUES ('rea', 'Diarrhoea', 3);
INSERT INTO codebook (`codeFeature`, `codeLabel`, `codeValue`) VALUES ('rea', 'Accident', 4);
INSERT INTO codebook (`codeFeature`, `codeLabel`, `codeValue`) VALUES ('rea', 'Other', 5);
INSERT INTO codebook (`codeFeature`, `codeLabel`, `codeValue`) VALUES ('onet', 'Always', 1);
INSERT INTO codebook (`codeFeature`, `codeLabel`, `codeValue`) VALUES ('onet', 'Sometimes', 2);
INSERT INTO codebook (`codeFeature`, `codeLabel`, `codeValue`) VALUES ('onet', 'Rainy season', 3);
INSERT INTO codebook (`codeFeature`, `codeLabel`, `codeValue`) VALUES ('onet', 'Dry season', 4);
INSERT INTO codebook (`codeFeature`, `codeLabel`, `codeValue`) VALUES ('onet', 'Never', 5);
INSERT INTO codebook (`codeFeature`, `codeLabel`, `codeValue`) VALUES ('HC', 'Yes seen', 1);
INSERT INTO codebook (`codeFeature`, `codeLabel`, `codeValue`) VALUES ('HC', 'Yes, not seen', 2);
INSERT INTO codebook (`codeFeature`, `codeLabel`, `codeValue`) VALUES ('HC', 'No, never had a card', 3);
INSERT INTO codebook (`codeFeature`, `codeLabel`, `codeValue`) VALUES ('HC', 'No, had card but lost it', 4);
INSERT INTO codebook (`codeFeature`, `codeLabel`, `codeValue`) VALUES ('HC', 'No information', 5);
INSERT INTO codebook (`codeFeature`, `codeLabel`, `codeValue`) VALUES ('reavac', 'Was at health facility to get Vaccine but did not succeed', 1);
INSERT INTO codebook (`codeFeature`, `codeLabel`, `codeValue`) VALUES ('reavac', 'Mother does not know of vaccination schedule', 2);
INSERT INTO codebook (`codeFeature`, `codeLabel`, `codeValue`) VALUES ('reavac', 'Mother considers child too small/sick for vaccination', 3);
INSERT INTO codebook (`codeFeature`, `codeLabel`, `codeValue`) VALUES ('reavac', 'Religious/Cultural reasons', 4);
INSERT INTO codebook (`codeFeature`, `codeLabel`, `codeValue`) VALUES ('reavac', 'Distance to health facility', 5);
INSERT INTO codebook (`codeFeature`, `codeLabel`, `codeValue`) VALUES ('reavac', 'Received but not indicated on card', 6);
INSERT INTO codebook (`codeFeature`, `codeLabel`, `codeValue`) VALUES ('reavac', 'Other', 7);
INSERT INTO codebook (`codeFeature`, `codeLabel`, `codeValue`) VALUES ('reavac', 'Not Applicable', 8);


-- Dashboard
CREATE TABLE `admin`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `email` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `password` varchar(300) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `reg_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updation_date` date NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = DYNAMIC;

SET FOREIGN_KEY_CHECKS = 1;
INSERT INTO `admin` (`id`, `username`, `email`, `password`, `reg_date`, `updation_date`) VALUES (1, 'admin', 'hdss@gmail.com', 'admin', '2022-04-04 20:31:45', '2016-04-17');


CREATE TABLE `adminlog`  (
  `id` int(11) NOT NULL,
  `adminid` int(11) NOT NULL,
  `ip` varbinary(16) NOT NULL,
  `logintime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = DYNAMIC;

SET FOREIGN_KEY_CHECKS = 1;


CREATE TABLE `userregistration`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `regNo` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `firstName` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `lastName` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `project` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `email` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `password` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `regDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updationDate` varchar(45) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `passUdateDate` varchar(45) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `approve` int(255) UNSIGNED NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `email`(`email`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 31 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = DYNAMIC;

SET FOREIGN_KEY_CHECKS = 1;

CREATE TABLE `userlog`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL,
  `userEmail` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `userIp` varbinary(16) NOT NULL,
  `city` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `country` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `loginTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2674 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = DYNAMIC;

SET FOREIGN_KEY_CHECKS = 1;
