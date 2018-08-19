create table board (
  id bigint not null primary key,
  data varchar(255)
)

create sequence board_seq start with 1 increment by 1;
