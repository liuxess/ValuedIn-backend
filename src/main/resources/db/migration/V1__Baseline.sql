
CREATE TABLE tbl_user_credentials (
    created TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by VARCHAR(30) NULL,
    updated TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    updated_by VARCHAR(30) NULL,
    login VARCHAR(30) NOT NULL PRIMARY KEY,
    password VARCHAR(254) NOT NULL,
    is_expired BOOLEAN NOT NULL DEFAULT 0,
    Last_Active TIMESTAMP NULL
);

INSERT INTO tbl_user_credentials
(login, password, is_expired, created, created_by, updated, updated_by, Last_Active)
VALUES('SetupUser', '$2a$10$VIZTI71ZbpnNcyDgow1Y4ufCA1MvNMtinxL8vZMpFQlHx5Ud0snA2', 0, /*psw: Password1*/
       '2000-01-01 00:00:00.0', 'root', '2000-01-01 00:00:00.0', 'root', NULL);


CREATE TABLE tbl_user_details (
   login VARCHAR(30) NOT NULL,
   role VARCHAR(10),
   first_name VARCHAR(30) NOT NULL,
   last_name VARCHAR(30) NOT NULL,
   created TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
   created_by VARCHAR(30) NULL,
   updated TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   updated_by VARCHAR(30) NULL,
   PRIMARY KEY (login),
   FOREIGN KEY (login) REFERENCES tbl_user_credentials(login)
);

INSERT INTO tbl_user_details
(login, role, first_name,last_name, created, created_by, updated, updated_by)
VALUES('SetupUser', NULL, 'Setup', 'User',
       '2000-01-01 00:00:00.0', 'root', '2000-01-01 00:00:00.0', 'root');