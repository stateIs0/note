create table p_person(
	id int,
	name varchar(100),
	primary key(id)
);

insert into p_person (id, name) 
	values (1, '花花');

insert into p_person (id, name) 
	values (2, 'Jerry');

create table p_comment(
	id int,
	content varchar(100),
	post_id int,
	primary key(id)
);

create table p_post(
	id int, 
	content varchar(100),
	person_id int,
	primary key(id)
);

insert into p_post (id, content, person_id)
	values (1, '今天下雪了', 1);
insert into p_post (id, content, person_id)
	values (2, '李洪鹤老师呵呵', 1);
insert into p_comment(id, content, post_id)
	values (1, '比雾霾好', 1);
insert into p_comment(id, content, post_id)
	values (2, '冻成狗', 1);
insert into p_comment(id, content, post_id)
	values (3, '喜欢下雪', 1);
	
-- 关联查询：查询一个帖子，是谁发的
-- 返回全部 post
select 
	p.id, p.content, p.person_id, 
	u.id, u.name
from p_post p
join p_person u on (p.person_id = u.id) 



	






