
create table student
(
	sid smallint auto_increment not null primary key,
    fname varchar(20) not null,
    lname varchar(20) null,
    age smallint null,
    address varchar(80) null,
    phone varchar(30) null,
    email varchar(40) not null
)
;
alter table student
	add constraint uq_email_student unique (email)
;

alter table student
	add constraint fk_user_student foreign key (email)
		references user (email)
;

create table club
(
	cid smallint auto_increment not null primary key,
    title varchar(100) not null,
    sdate date null,
    pfile varchar(20) null,
    comments varchar(500) null
)
;

create table instructor
(
	iid smallint auto_increment primary key,
    name varchar(60),
    phone varchar(20),
    email varchar(20)
)
;
CREATE INDEX idx_email
ON instructor (email)
;
ALTER TABLE instructor
MODIFY email varchar(20) NOT NULL
;
alter table instructor
	add constraint fk_user_instructor foreign key (email)
		references user (email)
;

create table club_course
(
	ccid smallint auto_increment primary key,
    cid smallint not null,
    iid smallint,
    course varchar(100),
    comments varchar(600)
)
;

alter table club_course
	add constraint fk_club_club_course foreign key (cid)
		references club (cid)
;
alter table club_course
	add constraint fk_instructor_club_course foreign key (iid)
		references instructor (iid)
;

create table course_student
(
    ccid smallint,
    sid smallint,
    status smallint,
    primary key (ccid, sid, status)
)
;

alter table course_student
	add constraint fk_student_course_student foreign key (sid)
		references student (sid)
;
alter table course_student
	add constraint fk_club_course_course_student foreign key (ccid)
		references club_course (ccid)
;

create table user
(
    email varchar(40) primary key,
    pword varchar(40) not null,
    flag smallint
)
;


SELECT
    course_student.ccid,
    course_student.sid,
    course_student.status,
    club_course.cid,
    club_course.iid,
    club_course.course,
    club_course.comments,
    student.fname,
    student.lname,
    student.email,
    club.title,
    club.sdate,
    instructor.name
FROM course_student
INNER JOIN club_course ON course_student.ccid = club_course.ccid
INNER JOIN club ON club.cid = club_course.cid
INNER JOIN student on course_student.sid = student.sid
INNER JOIN instructor on club_course.iid = instructor.iid
;