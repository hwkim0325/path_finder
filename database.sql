create table path(
startPoint varchar(10),				
endPoint varchar(10),
distance varchar(10),
path varchar(1024),
searchDate datetime,
id varchar(50),
FOREIGN KEY(id) REFERENCES user(id)
on delete cascade
on update cascade
);

create table user(
id varchar(50) primary key,
password varchar(50)
);

