
DROP TABLE IF EXISTS sys_user;
DROP TABLE IF EXISTS sys_role;
DROP TABLE IF EXISTS sys_permissions;
DROP TABLE IF EXISTS sys_user_role;
DROP TABLE IF EXISTS sys_role_permissions;

CREATE TABLE sys_user (
  user_id  BIGINT AUTO_INCREMENT,
  username VARCHAR(64),
  password VARCHAR(32),
  salt     VARCHAR(64),
  locked   BOOL   DEFAULT FALSE,
  salary   DECIMAL,
  birthday DATETIME,
  gender   CHAR(5),
  station  VARCHAR(32),
  tel      VARCHAR(11),
  remark   VARCHAR(255),
  PRIMARY KEY (user_id)
)
  CHARSET = utf8
  ENGINE = InnoDB
  COMMENT '用户表';
CREATE UNIQUE INDEX sys_user_username
  ON sys_user (username);

CREATE TABLE sys_role (
  role_id     BIGINT AUTO_INCREMENT,
  role_name   VARCHAR(64),
  description VARCHAR(128),
  code        VARCHAR(128),
  available   BOOL   DEFAULT FALSE,
  PRIMARY KEY (role_id)
)
  CHARSET = utf8
  ENGINE = InnoDB
  COMMENT '角色表';
CREATE UNIQUE INDEX sys_role_name
  ON sys_role (role_name);


CREATE TABLE sys_permissions (
  per_id      BIGINT AUTO_INCREMENT,
  per_pid     BIGINT,
  name        VARCHAR(64),
  description VARCHAR(128),
  code        VARCHAR(64),
  page        VARCHAR(128),
  available   BOOL   DEFAULT FALSE,
  PRIMARY KEY (per_id)
)
  CHARSET = utf8
  ENGINE = InnoDB
  COMMENT '权限表';

CREATE UNIQUE INDEX sys_permissions_name
  ON sys_permissions (name);

CREATE TABLE sys_user_role (
  user_id BIGINT,
  role_id BIGINT,
  PRIMARY KEY (user_id, role_id)
)
  CHARSET = utf8
  ENGINE = InnoDB
  COMMENT '用户-角色表';

CREATE TABLE sys_role_permissions (
  role_id BIGINT,
  per_id  BIGINT,
  PRIMARY KEY (role_id, per_id)
)
  CHARSET = utf8
  ENGINE = InnoDB
  COMMENT '角色-权限表';
