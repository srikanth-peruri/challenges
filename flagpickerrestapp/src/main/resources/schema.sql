CREATE TABLE continent (id int(11) NOT NULL AUTO_INCREMENT, continent varchar(255) DEFAULT NULL, PRIMARY KEY (id))

CREATE TABLE country (  id int(11) NOT NULL AUTO_INCREMENT,  flag varchar(255) DEFAULT NULL,  name varchar(255) DEFAULT NULL,  continent_id int(11) DEFAULT NULL,  PRIMARY KEY (id),  foreign key (CONTINENT_ID) references continent(ID)) 

