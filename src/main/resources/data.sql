insert into user_details(id , birth_date , name)
values(10001 , current_date , 'Tinesh Sakthi') ;
insert into user_details(id , birth_date , name)
values(10002 , current_date , 'Ranga') ;
insert into user_details(id , birth_date , name)
values(10003 , current_date , 'Banu') ;

insert into post(id,user_id,description)
      values (20001 , 10001 , 'Learn ReactJs') ;

insert into post(id,user_id,description)
      values (20002 , 10001 , 'Learn NodeJs') ;

insert into post(id,user_id,description)
      values (20003 , 10002 , 'Learn MongoDb') ;

insert into post(id,user_id,description)
      values (20004 , 10002 , 'Learn Spring Boot') ;