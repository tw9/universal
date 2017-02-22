

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
);


create table taskflow_task(
id int,
taskflow_id int,
task_id int,
sort_flag int
);

create table task_input_parameter(
id int,
task_id int,
taskflow_id int,
parameter_type int,
parameter_code varchar(30),
parameter_value varchar(100)
);

-- parameter_type
-- 0: commons parameters  , code/value  is the paramter's code and value
-- 1: the previous task's result as next test's parameter , parameter_code is previous task result code , parameter_value is next task parameter_code
-- 2: input parameters


create table task_timer(
id int ,
taskflow_id int,
active int,  -- 0:not active  1:active
start_time datetime,
end_time datetime,
first_run datetime,
run_period int,
v1_year varchar(15),
v2_month varchar(15),
v3_day varchar(15),
v4_weekday varchar(15),
v5_hour varchar(15),
v6_minute varchar(15),
v7_second varchar(15)
);



