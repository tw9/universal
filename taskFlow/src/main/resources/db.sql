

create table task(
id int,
name varchar(30),
class_path varchar(200)

);

create table taskflow(
id int,
name varchar(30),
create_time datetime,
creator varchar(30),
description varchar(200)
)


create table taskflow_task(
id int,
taskflow_id int,
task_id int,
sort_flag int,
)

create table task_input_properties(
id int,
taskflow_task_id int,
prop_code varchar(30),
prop_value varchar(100),
)


