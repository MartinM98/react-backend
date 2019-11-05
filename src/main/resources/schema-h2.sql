CREATE TABLE users (
  id int NOT NULL COMMENT '',
  login varchar(10) not null COMMENT '',
  firstName varchar(10) not null COMMENT '',
  lastName varchar(10) not null COMMENT '',
dateOfBirth date null COMMENT '',
active boolean not null COMMENT ''



);
INSERT INTO users (id,login,firstName,lastName,active) VALUES
  (1,'Login1','Martin','Surname1',  true),
  (2,'Login2','Martin2','Surname2',  false),
  (3,'Login3','Martin3','Surname3', true);