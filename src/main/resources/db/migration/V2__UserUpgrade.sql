ALTER TABLE tbl_user_details
ADD COLUMN email VARCHAR(40) DEFAULT '' NOT NULL;

ALTER TABLE tbl_user_details
ADD COLUMN telephone VARCHAR(20) NULL;

ALTER TABLE tbl_user_details
ALTER email DROP DEFAULT;