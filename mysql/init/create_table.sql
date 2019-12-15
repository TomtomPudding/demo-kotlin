CREATE TABLE users(
    user_id        INT(10) UNSIGNED ZEROFILL NOT NULL PRIMARY KEY COMMENT 'ユーザID',
    user_name           VARCHAR(50) NOT NULL COMMENT '名前',
    password       VARCHAR(50) NOT NULL COMMENT 'パスワード',
    user_icon      VARCHAR(200) COMMENT 'ユーザアイコンパス',
    create_at      timestamp default CURRENT_TIMESTAMP COMMENT '作成日時',
    updated_at     timestamp default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP COMMENT '更新日時'
)COMMENT='ユーザ情報';

CREATE TABLE group_room(
    room_id        INT(8) UNSIGNED ZEROFILL NOT NULL PRIMARY KEY COMMENT 'ルームID',
    room_name      VARCHAR(50) NOT NULL COMMENT 'ルーム名',
    member_count   INT(4) NOT NULL COMMENT '総ユーザ数',
    create_at      timestamp default CURRENT_TIMESTAMP COMMENT '作成日時',
    updated_at     timestamp default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP COMMENT '更新日時'
)COMMENT='ルーム情報';

CREATE TABLE group_member(
    room_id        INT(8) UNSIGNED ZEROFILL NOT NULL COMMENT 'ルームID',
    user_id        INT(10) UNSIGNED ZEROFILL NOT NULL COMMENT 'ユーザID',
    group_icon     VARCHAR(200) COMMENT 'グループアイコンパス',
    PRIMARY KEY(room_id, user_id)
)COMMENT='グループメンバー';

CREATE TABLE chatapp_setting(
    max_group_member INT(3) NOT NULL  COMMENT '最大グループユーザ数'
)COMMENT='システム設定';
