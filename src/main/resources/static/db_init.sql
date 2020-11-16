create table comments
(
  id      bigint auto_increment
    primary key,
  comment varchar(255)                        null,
  time    timestamp default CURRENT_TIMESTAMP not null
  on update CURRENT_TIMESTAMP,
  constraint comments_id_uindex
  unique (id)
)
  engine = InnoDB;


create table notices
(
  id         bigint auto_increment
    primary key,
  comment_id bigint                              not null,
  time       timestamp default CURRENT_TIMESTAMP not null
  on update CURRENT_TIMESTAMP,
  delivered  tinyint(1)                          null,
  constraint notices_id_uindex
  unique (id)
)
  engine = InnoDB;
