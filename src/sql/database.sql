CREATE DATABASE pairsystem;
USE pairsystem;

CREATE TABLE user (
  id         BIGINT      NOT NULL AUTO_INCREMENT
  COMMENT '动态的id',
  name       VARCHAR(12) NOT NULL
  COMMENT '姓名',
  studentid  INT         NOT NULL
  COMMENT '学号',
  qq         VARCHAR(15) NOT NULL
  COMMENT 'qq',
  phone      VARCHAR(11) NOT NULL
  COMMENT '手机号',
  email      VARCHAR(30) NOT NULL
  COMMENT '邮箱',
  selfsex    TINYINT COMMENT '性别，1男，2女',
  createtime TIMESTAMP            DEFAULT CURRENT_TIMESTAMP
  COMMENT '创建时间',
  ip         VARCHAR(15) NOT NULL
  COMMENT 'IP地址',
  pairid     INT                  DEFAULT 0
  COMMENT '匹配的学号',
  pairtype   TINYINT              DEFAULT 1
  COMMENT '匹配规则，1系统，2自行，3服从调剂',
  type       TINYINT COMMENT '1早餐，2自习',
  status     TINYINT              DEFAULT 0
  COMMENT '1已发送邮件,0未发送',
  PRIMARY KEY (id),
  KEY (studentid)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE userinfo (
  studentid   INT         NOT NULL
  COMMENT '学号',
  selfcollege VARCHAR(15) NOT NULL
  COMMENT '学院',
  college     VARCHAR(15) NOT NULL
  COMMENT '期望配对的学院',
  time        VARCHAR(10) NOT NULL
  COMMENT '期望被叫醒的时间',
  sex         TINYINT     NOT NULL
  COMMENT '性别，1同性，0异性，-1随意',
  type        TINYINT COMMENT '1早餐，2自习',
  KEY (studentid)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE teacher (
  id        BIGINT      NOT NULL AUTO_INCREMENT
  COMMENT '动态的id',
  name      VARCHAR(12) NOT NULL
  COMMENT '姓名',
  studentid INT         NOT NULL
  COMMENT '学号',
  qq        VARCHAR(15) NOT NULL
  COMMENT 'qq',
  phone     VARCHAR(12) NOT NULL
  COMMENT '手机号',
  email     VARCHAR(30) NOT NULL
  COMMENT '邮箱',
  basecourse     VARCHAR(14) DEFAULT '无'
  COMMENT '基础课程',
  professional      VARCHAR(35) DEFAULT '无'
  COMMENT '专业课程',
  college   VARCHAR(15) NOT NULL
  COMMENT '学院',
  selfsex   TINYINT COMMENT '性别，1男，2女',
  status    TINYINT              DEFAULT 0
  COMMENT '状态，0空闲，1已被预约',
  pairid    INT                  DEFAULT 0
  COMMENT '匹配的学号',
  ip        VARCHAR(15) NOT NULL
  COMMENT 'IP地址',
  PRIMARY KEY (id),
  KEY (studentid)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE student (
  id        BIGINT      NOT NULL AUTO_INCREMENT
  COMMENT '动态的id',
  name      VARCHAR(12) NOT NULL
  COMMENT '姓名',
  studentid INT         NOT NULL
  COMMENT '学号',
  qq        VARCHAR(15) NOT NULL
  COMMENT 'qq',
  phone     VARCHAR(12) NOT NULL
  COMMENT '手机号',
  email     VARCHAR(30) NOT NULL
  COMMENT '邮箱',
  pairid    INT                  DEFAULT 0
  COMMENT '匹配的学号',
  college   VARCHAR(15) NOT NULL
  COMMENT '学院',
  ip        VARCHAR(15) NOT NULL
  COMMENT 'IP地址',
  PRIMARY KEY (id),
  KEY (studentid)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE assistinfo (
  teastudentid INT NOT NULL
  COMMENT '辅学者学号',
  stustudentid INT NOT NULL
  COMMENT '被辅学者学号',
  time         TINYINT DEFAULT 1
  COMMENT '辅学次数',
  KEY (teastudentid),
  KEY (stustudentid)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
