create table user_pw (
                         u_id varchar(20) primary key,
                         u_pwd varchar(20),
                         u_role integer
);

create table center(
                       center_id Integer primary key,
                       center_address varchar(50),
                       center_telephone integer,
                       open_sat char(1)
);

create table centerSalary(
                             cs_center_id Integer,
                             cs_min Integer,
                             cs_max Integer,
                             cs_hour Integer
);
alter table centerSalary add constraint centerSalary_fk foreign key (cs_center_id) references center(center_id);



create table customer (
                          CUSTOMER_ID INTEGER,
                          CUSTOMER_NAME VARCHAR(20),
                          ADDRESS VARCHAR(50),
                          C_EMAIL VARCHAR(20),
                          C_PHONE INTEGER,
                          C_USERNAME VARCHAR(20),
                          CUSTOMER_STATUS CHAR(1) DEFAULT 'f',
                          CUSTOMER_CENTER_ID INTEGER
);
alter table customer add constraint customer_fk foreign key (customer_center_id) references center(center_id);
alter table customer add constraint customer_pk primary key (customer_id, customer_center_id);

create table vehicle (
                         vin varchar(20) primary key,
                         manu varchar(10),
                         mileage varchar(10),
                         year integer
);

create table associate (
                           ass_vin varchar(20) PRIMARY key,
                           ass_customer_id integer,
                           ass_center_id integer
);
alter table associate add constraint associate_fk_customer foreign key (ass_customer_id, ass_center_id) references customer(CUSTOMER_ID, CUSTOMER_CENTER_ID);
alter table associate add constraint associate_fk foreign key (ass_vin) references vehicle(vin);

create table prices (
                        p_centerId Integer,
                        p_model varchar(10),
                        p_tier integer,
                        p_dollar integer
);
alter table prices add constraint prices_pk primary key (p_centerId, p_model, p_tier);

create table service (
                         s_id varchar(3) primary key,
                         s_time integer,
                         s_priceTier integer,
                         s_name varchar(30)
);

create table serviceType (
                             st_id varchar(3),
                             st_name varchar(30),
                             st_type varchar(3)
);
alter table serviceType add constraint st_fk foreign key (st_id) references service(s_id);
alter table serviceType add constraint st_pk primary key (st_id, st_name);


create table vehicleAndMaintenance (
                                       main_vin varchar(20) primary key,
                                       main_id varchar(3)
);
alter table vehicleAndMaintenance add constraint vehicleAndMaintenance_fk_vin foreign key (main_vin) references vehicle(vin);
alter table vehicleAndMaintenance add constraint vehicleAndMaintenance_fk_main foreign key (main_id) references service(s_id);



create table emp(
                    emp_id integer primary key,
                    emp_center integer
);
alter table emp add constraint emp_fk_center foreign key (emp_center) references center(center_id);


create table mechanic(
                         mechanic_id integer,
                         mechanic_name VARCHAR(20),
                         mechanic_address VARCHAR(50),
                         mechanic_email VARCHAR(40),
                         mechanic_phone INTEGER,
                         mechanic_compensation integer,
                         mechanic_startDate VARCHAR(20)
);
alter table mechanic add constraint mechanic_fk_id foreign key (mechanic_id) references emp(emp_id);
alter table mechanic add constraint mechanic_pk primary key (mechanic_id);


create table timeslot(
                         timeslot_center integer,
                         timeslot_week integer,
                         timeslot_days integer,
                         timeslot_slot integer,
                         timeslot_mechanic integer
);
alter table timeslot add constraint timeslot_fk_center foreign key (timeslot_center) references center(center_id);
alter table timeslot add constraint timeslot_fk_mechanic foreign key (timeslot_mechanic) references mechanic(mechanic_id);
alter table timeslot add constraint timeslot_pk primary key (timeslot_center, timeslot_week, timeslot_days, timeslot_slot, timeslot_mechanic);

create table timeoff (
                         timeoff_center integer,
                         timeoff_week integer,
                         timeoff_days integer,
                         timeoff_slot_start integer,
                         timeoff_slot_end integer,
                         timeoff_mechanic integer,
                         timeoff_accept integer default 0
);
alter table timeoff add constraint timeoff_fk_center foreign key (timeoff_center) references center(center_id);
alter table timeoff add constraint timeoff_fk_mechanic foreign key (timeoff_mechanic) references mechanic(mechanic_id);
alter table timeoff add constraint timeoff_pk primary key (timeoff_center, timeoff_week, timeoff_days, timeoff_slot_start, timeoff_slot_end, timeoff_mechanic);

create table manager(
                        manager_id integer,
                        manager_name VARCHAR(20),
                        manager_address VARCHAR(50),
                        manager_email VARCHAR(40),
                        manager_phone INTEGER,
                        manager_salary integer
);
alter table manager add constraint manager_fk_id foreign key (manager_id) references emp(emp_id);
alter table manager add constraint manager_pk primary key (manager_id, manager_name);

create table receptionist(
                             receptionist_id integer,
                             receptionist_name VARCHAR(20),
                             receptionist_address VARCHAR(50),
                             receptionist_email VARCHAR(40),
                             receptionist_phone INTEGER,
                             receptionist_salary integer
);
alter table receptionist add constraint receptionist_fk_id foreign key (receptionist_id) references emp(emp_id);
alter table receptionist add constraint receptionist_pk primary key (receptionist_id, receptionist_name);

create table invoice(
                        in_id integer primary key,
                        in_center integer,
                        in_vin varchar(20),
                        in_customer integer,
                        in_date varchar(20),
                        in_status char(1) default 'f',
                        in_mechanicName varchar(20),
                        in_cost integer
);
alter table invoice add constraint invoice_fk_customer foreign key (in_customer, in_center) references customer(customer_id, CUSTOMER_CENTER_ID);

create table invoiceAndService(
                                  inAndS_id integer,
                                  inAndS_service varchar(3),
                                  inAndS_type varchar(30),
                                  inAndS_price integer
);
alter table invoiceAndService add constraint invoiceAndService_fk_id foreign key (inAndS_id) references invoice(in_id);
alter table invoiceAndService add constraint invoiceAndService_fk_service foreign key (inAndS_service) references service(s_id);
alter table invoiceAndService add constraint invoiceAndService_pk primary key (inAndS_id, inAndS_service);

create table mechanicWeekHour(
                                 mwh_mechanic integer,
                                 mwh_week integer,
                                 mwh_hour integer,
                                 mwh_center integer
);
alter table mechanicWeekHour add constraint mechanicWeekHour_fk_me foreign key (mwh_mechanic) references mechanic(mechanic_id);
alter table mechanicWeekHour add constraint mechanicWeekHour_fk_center foreign key (mwh_center) references center(center_id);
alter table mechanicWeekHour add constraint mechanicWeekHour_pk primary key (mwh_mechanic, mwh_week);