CREATE TABLE users(
    id             INT(10) UNSIGNED ZEROFILL NOT NULL PRIMARY KEY COMMENT 'ユーザID',
    name           VARCHAR(50) NOT NULL COMMENT '名前',
    password       VARCHAR(50) NOT NULL COMMENT 'パスワード',
    create_at      timestamp default CURRENT_TIMESTAMP COMMENT '作成日時',
    updated_at     timestamp default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP COMMENT '更新日時'
)COMMENT='ユーザ情報';
