# --- !Ups
CREATE TABLE USER(
  USER_ID BIGINT(20) NOT NULL AUTO_INCREMENT,
  USER_NAME VARCHAR(255) NOT NULL,
  EMAIL VARCHAR(255) NOT NULL,
  PASSWORD VARCHAR(255) NOT NULL,
  PRIMARY KEY (USER_ID)
) DEFAULT CHARSET=utf8;

INSERT INTO USER (USER_ID,USER_NAME, EMAIL, PASSWORD) VALUES ('1','natsumi','aaa@gamail.com', '0000');

CREATE TABLE TWEET(
  TWEET_ID BIGINT(20) NOT NULL AUTO_INCREMENT,
  USER_ID VARCHAR(255) NOT NULL,
  CONTENT VARCHAR(255) NOT NULL,
  IMAGE VARCHAR(255),
  DATE DATE,
  PRIMARY KEY (TWEET_ID)
) DEFAULT CHARSET=utf8;

INSERT INTO TWEET (TWEET_ID,USER_ID, CONTENT, IMAGE, DATE) VALUES ('1','1','hello', '0000', '2017/06/27')

# --- !Downs
#DROP TABLE ACCOUNT;