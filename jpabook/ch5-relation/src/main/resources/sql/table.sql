create table member (
  member_id varchar(255) not null,
  team_id varchar(255),
  username varchar(255),
  primary key (member_id)
);

create table team (
  team_id varchar(255) not null,
  name varchar(255),
  primary key (team_id)
);

alter table member add constraint FK_MEMBER_TEAM
foreign key (team_id)
references team;

insert into team (team_id, name) values ('t1', 'team1');
insert into member (member_id, team_id, username) values ('m1', 't1', 'member1');
insert into member (member_id, team_id, username) values ('m2', 't1', 'member2');

select t.*
from member m
       join team t on m.team_id = t.team_id
where m.member_id = 'm1';