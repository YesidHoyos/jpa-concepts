/*create table person
(
   id integer not null,
   name varchar(255) not null,
   location varchar(255),
   birth_date date,
   primary key(id)
);*/


INSERT INTO PERSON (ID, NAME, LOCATION, BIRTH_DATE ) 
VALUES(10001,  'Pepito Perez', 'Porallá',sysdate());
INSERT INTO PERSON (ID, NAME, LOCATION, BIRTH_DATE ) 
VALUES(10002,  'Julanito de Tales', 'Poracá',sysdate());
INSERT INTO PERSON (ID, NAME, LOCATION, BIRTH_DATE ) 
VALUES(10003,  'Juanito Nieves', 'Porahí',sysdate());


insert into course(id, name, created_date, last_updated_date,is_deleted) 
values(10001,'JPA in 50 Steps', sysdate(), sysdate(),false);
insert into course(id, name, created_date, last_updated_date,is_deleted) 
values(10002,'Spring in 50 Steps', sysdate(), sysdate(),false);
insert into course(id, name, created_date, last_updated_date,is_deleted) 
values(10003,'Spring Boot in 100 Steps', sysdate(), sysdate(),false);

insert into passport(id,number)
values(40001,'E123456');
insert into passport(id,number)
values(40002,'N123457');
insert into passport(id,number)
values(40003,'L123890');

insert into student(id,name,passport_id)
values(20001,'Yesid',40001);
insert into student(id,name,passport_id)
values(20002,'Fernando',40002);
insert into student(id,name,passport_id)
values(20003,'Ferho',40003);

insert into review(id,rating,description,course_id)
values(50001,5, 'Great Course',10001);
insert into review(id,rating,description,course_id)
values(50002,4, 'Wonderful Course',10001);
insert into review(id,rating,description,course_id)
values(50003,5, 'Awesome Course',10003);

insert into student_course(student_id,course_id)
values(20001,10001);
insert into student_course(student_id,course_id)
values(20002,10001);
insert into student_course(student_id,course_id)
values(20003,10001);
insert into student_course(student_id,course_id)
values(20001,10003);