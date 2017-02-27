

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
sort_id int,
taskflow_id int,
parameter_type int,
parameter_code varchar(30),
parameter_value varchar(100)
);

-- parameter_type
-- 0: 通用参数, 所有task 均可使用
-- 1: pipe , 管道, 上一task的结果作为下一task的输入 , parameter_code: 上一task结果的key , parameter_value:本task的参数key
-- 2: input parameters , 直接输入参数
-- 3: 保存結果到taskflow的总结果
-- 4: 从taskflow总结果中获取数据


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



