insert into user_pw values ('1000130001', 'Parker', 3);
insert into user_pw values ('1000230001', 'Prince', 3);
insert into user_pw values ('1005330002', 'Batson', 3);
insert into user_pw values ('1001030002', 'Wayne', 3);
insert into user_pw values ('1000130002', 'Rogers', 3);
insert into user_pw values ('1003530002', 'Hogan', 3);
insert into user_pw values ('1000230003', 'Stark', 3);
insert into user_pw values ('1000330003', 'Romanoff', 3);
insert into user_pw values ('1001130003', 'Bullock', 3);
insert into user_pw values ('1006230003', 'Wilson', 3);
insert into user_pw values ('1050130003', 'Maximoff', 3);
insert into user_pw values ('1001030003', 'Potts', 3);

insert into user_pw values ('132457689', 'Williams', 5);
insert into user_pw values ('314275869', 'Johnson', 5);
insert into user_pw values ('241368579', 'Garcia', 5);
insert into user_pw values ('423186759', 'Clark', 5);
insert into user_pw values ('123789456', 'Martinez', 5);
insert into user_pw values ('789123456', 'Rodriguez', 5);
insert into user_pw values ('125689347', 'Hernandez', 5);
insert into user_pw values ('347812569', 'Brown', 5);
insert into user_pw values ('123456780', 'Gibson', 5);
insert into user_pw values ('123456708', 'Wilder', 5);
insert into user_pw values ('123456078', 'Titus', 5);
insert into user_pw values ('123450678', 'Mendez', 5);
insert into user_pw values ('123405678', 'Alberti', 5);

insert into user_pw values ('543243212', 'James', 4);
insert into user_pw values ('543243213', 'Jin', 4);
insert into user_pw values ('543243214', 'Curry', 4);

insert into user_pw values ('123456789', 'Doe', 2);
insert into user_pw values ('987654321', 'Brooks', 2);
insert into user_pw values ('987612345', 'Smith', 2);

insert into center values (30001, '3921 Western Blvd, Raleigh, NC 27606', 3392601234, 't');
insert into center values (30002, '4500 Preslyn Dr Suite 103, Raleigh, NC 27616', 8576890280, 't');
insert into center values (30003, '9515 Chapel Hill Rd, Morrisville, NC 27560', 7798182200, 'f');

insert into centerSalary values ('30001', 30, 40, 35);
insert into centerSalary values ('30002', 25, 35, 25);
insert into centerSalary values ('30003', 20, 25, 22);

insert into customer values (10001, 'Peter Parker', '', '', '', 1000130001, 't', 30001);
insert into customer values (10002, 'Diana Prince', '', '', '', 1000230001, 't', 30001);
insert into customer values (10053, 'Billy Batson', '', '', '', 1005330002, 't', 30002);
insert into customer values (10010, 'Bruce Wayne', '', '', '', 1001030002, 't', 30002);
insert into customer values (10001, 'Steve Rogers', '', '', '', 1000130002, 't', 30002);
insert into customer values (10035, 'Happy Hogan', '', '', '', 1003530002, 't', 30002);
insert into customer values (10002, 'Tony Stark', '', '', '', 1000230003, 't', 30003);
insert into customer values (10003, 'Natasha Romanoff', '', '', '', 1000330003, 't', 30003);
insert into customer values (10011, 'Harvey Bullock', '', '', '', 1001130003, 't', 30003);
insert into customer values (10062, 'Sam Wilson', '', '', '', 1006230003, 't', 30003);
insert into customer values (10501, 'Wanda Maximoff', '', '', '', 1050130003, 't', 30003);
insert into customer values (10010, 'Virginia Potts', '', '', '', 1001030003, 't', 30003);


insert into vehicle values ('4Y1BL658', 'Toyota', '53000', 2007);
insert into vehicle values ('7A1ST264', 'Honda', '117000', 1999);
insert into vehicle values ('5TR3K914', 'Nissan', '111000', 2015);
insert into vehicle values ('15DC9A87', 'Toyota', '21000', 2020);
insert into vehicle values ('18S5R2D8', 'Nissan', '195500', 2019);
insert into vehicle values ('9R2UHP54', 'Honda', '67900', 2013);
insert into vehicle values ('88TSM888', 'Honda', '10000', 2000);
insert into vehicle values ('71HK2D89', 'Toyota', '195550', 2004);
insert into vehicle values ('34KLE19D', 'Toyota', '123800', 2010);
insert into vehicle values ('29T56WC3', 'Nissan', '51300', 2011);
insert into vehicle values ('P39VN198', 'Nissan', '39500', 2013);
insert into vehicle values ('39YVS415', 'Honda', '49900', 2021);


insert into associate values ('4Y1BL658', 10001, 30001);
insert into associate values ('7A1ST264', 10002, 30001);
insert into associate values ('5TR3K914', 10053, 30002);
insert into associate values ('15DC9A87', 10010, 30002);
insert into associate values ('18S5R2D8', 10001, 30002);
insert into associate values ('9R2UHP54', 10035, 30002);
insert into associate values ('88TSM888', 10002, 30003);
insert into associate values ('71HK2D89', 10003, 30003);
insert into associate values ('34KLE19D', 10011, 30003);
insert into associate values ('29T56WC3', 10062, 30003);
insert into associate values ('P39VN198', 10501, 30003);
insert into associate values ('39YVS415', 10010, 30003);


insert into prices values(30001, 'Honda', 1, 50);
insert into prices values(30001, 'Nissan', 1, 70);
insert into prices values(30001, 'Toyota', 1, 60);
insert into prices values(30001, 'Honda', 2, 140);
insert into prices values(30001, 'Nissan', 2, 175);
insert into prices values(30001, 'Toyota', 2, 160);
insert into prices values(30001, 'Honda', 3, 400);
insert into prices values(30001, 'Nissan', 3, 500);
insert into prices values(30001, 'Toyota', 3, 450);
insert into prices values(30001, 'Honda', 4, 590);
insert into prices values(30001, 'Nissan', 4, 700);
insert into prices values(30001, 'Toyota', 4, 680);
insert into prices values(30001, 'Honda', 5, 1000);
insert into prices values(30001, 'Nissan', 5, 1200);
insert into prices values(30001, 'Toyota', 5, 1100);
insert into prices values(30001, 'Honda', 6, 120);
insert into prices values(30001, 'Nissan', 6, 190);
insert into prices values(30001, 'Toyota', 6, 200);
insert into prices values(30001, 'Honda', 7, 195);
insert into prices values(30001, 'Nissan', 7, 210);
insert into prices values(30001, 'Toyota', 7, 215);
insert into prices values(30001, 'Honda', 8, 300);
insert into prices values(30001, 'Nissan', 8, 310);
insert into prices values(30001, 'Toyota', 8, 305);

insert into prices values(30002, 'Honda', 1, 70);
insert into prices values(30002, 'Nissan', 1, 90);
insert into prices values(30002, 'Toyota', 1, 80);
insert into prices values(30002, 'Honda', 2, 160);
insert into prices values(30002, 'Nissan', 2, 195);
insert into prices values(30002, 'Toyota', 2, 180);
insert into prices values(30002, 'Honda', 3, 420);
insert into prices values(30002, 'Nissan', 3, 520);
insert into prices values(30002, 'Toyota', 3, 470);
insert into prices values(30002, 'Honda', 4, 610);
insert into prices values(30002, 'Nissan', 4, 720);
insert into prices values(30002, 'Toyota', 4, 700);
insert into prices values(30002, 'Honda', 5, 1020);
insert into prices values(30002, 'Nissan', 5, 1220);
insert into prices values(30002, 'Toyota', 5, 1120);
insert into prices values(30002, 'Honda', 6, 125);
insert into prices values(30002, 'Nissan', 6, 195);
insert into prices values(30002, 'Toyota', 6, 205);
insert into prices values(30002, 'Honda', 7, 200);
insert into prices values(30002, 'Nissan', 7, 215);
insert into prices values(30002, 'Toyota', 7, 220);
insert into prices values(30002, 'Honda', 8, 305);
insert into prices values(30002, 'Nissan', 8, 315);
insert into prices values(30002, 'Toyota', 8, 310);

insert into prices values(30003, 'Honda', 1, 85);
insert into prices values(30003, 'Nissan', 1, 105);
insert into prices values(30003, 'Toyota', 1, 95);
insert into prices values(30003, 'Honda', 2, 175);
insert into prices values(30003, 'Nissan', 2, 210);
insert into prices values(30003, 'Toyota', 2, 195);
insert into prices values(30003, 'Honda', 3, 435);
insert into prices values(30003, 'Nissan', 3, 535);
insert into prices values(30003, 'Toyota', 3, 485);
insert into prices values(30003, 'Honda', 4, 625);
insert into prices values(30003, 'Nissan', 4, 735);
insert into prices values(30003, 'Toyota', 4, 715);
insert into prices values(30003, 'Honda', 5, 1035);
insert into prices values(30003, 'Nissan', 5, 1235);
insert into prices values(30003, 'Toyota', 5, 1135);
insert into prices values(30003, 'Honda', 6, 140);
insert into prices values(30003, 'Nissan', 6, 180);
insert into prices values(30003, 'Toyota', 6, 195);
insert into prices values(30003, 'Honda', 7, 210);
insert into prices values(30003, 'Nissan', 7, 220);
insert into prices values(30003, 'Toyota', 7, 215);
insert into prices values(30003, 'Honda', 8, 310);
insert into prices values(30003, 'Nissan', 8, 305);
insert into prices values(30003, 'Toyota', 8, 310);


insert into service values ('101', 1, 2, 'Belt Replacement');
insert into service values ('102', 5, 3, 'Engine Repair');
insert into service values ('103', 4, 4, 'Exhaust Repair');
insert into service values ('104', 2, 2, 'Muffler Repair');
insert into service values ('105', 4, 3, 'Alternator Repair');
insert into service values ('106', 5, 3, 'Power Lock Repair');
insert into service values ('107', 7, 5, 'Axle Repair');
insert into service values ('108', 3, 3, 'Brake Repair');
insert into service values ('109', 2, 1, 'Tire Balancing');
insert into service values ('110', 1, 2, 'Wheel Alignment');
insert into service values ('111', 3, 4, 'Compressor Repair');
insert into service values ('112', 4, 3, 'Evaporator Repair');
insert into service values ('113', 3, 6, 'Maintenance Service A');
insert into service values ('114', 6, 7, 'Maintenance Service B');
insert into service values ('115', 9, 8, 'Maintenance Service C');


insert into vehicleAndMaintenance values ('4Y1BL658', '114');
insert into vehicleAndMaintenance values ('7A1ST264', '113');
insert into vehicleAndMaintenance values ('5TR3K914', '115');
insert into vehicleAndMaintenance values ('15DC9A87', '113');
insert into vehicleAndMaintenance values ('18S5R2D8', '115');
insert into vehicleAndMaintenance values ('9R2UHP54', '114');
insert into vehicleAndMaintenance values ('88TSM888', '113');
insert into vehicleAndMaintenance values ('71HK2D89', '114');
insert into vehicleAndMaintenance values ('34KLE19D', '115');
insert into vehicleAndMaintenance values ('29T56WC3', '113');
insert into vehicleAndMaintenance values ('P39VN198', '114');
insert into vehicleAndMaintenance values ('39YVS415', '113');

insert into emp values ('123456789', '30001');
insert into emp values ('132457689', '30001');
insert into emp values ('314275869', '30001');
insert into emp values ('241368579', '30001');
insert into emp values ('543243212', '30001');

insert into emp values ('987654321', '30002');
insert into emp values ('423186759', '30002');
insert into emp values ('123789456', '30002');
insert into emp values ('789123456', '30002');
insert into emp values ('125689347', '30002');
insert into emp values ('543243213', '30002');



insert into emp values ('347812569', '30003');
insert into emp values ('123456780', '30003');
insert into emp values ('123456708', '30003');
insert into emp values ('123456078', '30003');
insert into emp values ('123450678', '30003');
insert into emp values ('123405678', '30003');
insert into emp values ('543243214', '30003');
insert into emp values ('987612345', '30003');


insert into mechanic values ('132457689', 'James Williams', '1400 Gorman St, Raleigh, NC 27606-2972 ', 'jwilliams@gmail.com', '4576312882', '35', '7/2/2021');
insert into mechanic values ('314275869', 'David Johnson', '686 Stratford Court, Raleigh, NC 27606', 'djohnson@ymail.com', '9892321100', '35', '8/25/2021');
insert into mechanic values ('241368579', 'Maria Garcia', '1521 Graduate Lane, Raleigh, NC 27606', 'mgarcia@yahoo.com', '4573459090', '35', '8/26/2021');
insert into mechanic values ('423186759', 'Ellie Clark', '3125 Avent Ferry Road, Raleigh, NC 27605', 'eclark@gmail.com', '9892180921', '25', '9/1/2021');
insert into mechanic values ('123789456', 'Robert Martinez', '1232 Tartan Circle, Raleigh, NC 27607', 'rmartinez@ymail.com', '7652304282', '25', '10/11/2021');
insert into mechanic values ('789123456', 'Charles Rodriguez', '218 Patton Lane, Raleigh, NC 27603', 'crodriguez@yahoo.com', '9092234281', '25', '10/11/2021');
insert into mechanic values ('125689347', 'Jose Hernandez', '4747 Dola Mine Road, Raleigh, NC 27609', 'jhernandez@gmail.com', '7653241714', '25', '10/17/2021');
insert into mechanic values ('347812569', 'Charlie Brown', '1 Rockford Mountain Lane, Morrisville, NC 27560', 'cbrown@ymail.com', '9091237568', '22', '10/18/2021');
insert into mechanic values ('123456780', 'Jeff Gibson', '900 Development Drive, Morrisville, NC 27560', 'jgibson@yahoo.com', '3390108899', '22', '10/20/2021');
insert into mechanic values ('123456708', 'Isabelle Wilder', '6601 Koppers Road, Morrisville, NC 27560', 'iwilder@gmail.com', '3394561231', '22', '10/22/2021');
insert into mechanic values ('123456078', 'Peter Titus', '2860 Slater Road, Morrisville, NC 27560', 'ptitus@ymail.com', '3396723418', '22', '10/28/2021');
insert into mechanic values ('123450678', 'Mark Mendez', '140 Southport Drive, Morrisville, NC 27560', 'mmendez@yahoo.com', '3395792080', '22', '10/31/2021');
insert into mechanic values ('123405678', 'Lisa Alberti', '100 Valley Glen Drive, Morrisville, NC 27560', 'lalberti@yahoo.com', '3391126787', '22', '11/01/2021');

insert into manager values ('123456789', 'John Doe', '1378 University Woods, Raleigh, NC 27612', 'jdoe@gmail.com', '8636368778', '44');
insert into manager values ('987654321', 'Rachel Brooks', '2201 Gorman Parkwood, Raleigh, NC 27618', 'rbrooks@ymail.com', '8972468552', '35');
insert into manager values ('987612345', 'Caleb Smith', '1538 Red Bud Lane, Morrisville, NC 27560', 'csmith@yahoo.com', '8547963210', '25');

insert into Receptionist values ('543243212', 'Lebron James', '1401 Gorman St, Raleigh, NC 27606-2972', 'Jamesl@gmail.com', '9199463727', '22');
insert into Receptionist values ('543243213', 'Jerry Jin', '1402 Gorman St, Raleigh, NC 27606-2972', 'Jinj@gmail.com', '9199463722', '22');
insert into Receptionist values ('543243214', 'Stephen Curry', '1403 Gorman St, Raleigh, NC 27606-2972', 'currys@gmail.com', '9199463723', '22');

insert into mechanicWeekHour values ('132457689', '1', '0', 30001);
insert into mechanicWeekHour values ('132457689', '2', '0', 30001);
insert into mechanicWeekHour values ('132457689', '3', '0', 30001);
insert into mechanicWeekHour values ('132457689', '4', '0', 30001);

insert into mechanicWeekHour values ('314275869', '1', '0', 30001);
insert into mechanicWeekHour values ('314275869', '2', '0', 30001);
insert into mechanicWeekHour values ('314275869', '3', '0', 30001);
insert into mechanicWeekHour values ('314275869', '4', '0', 30001);

insert into mechanicWeekHour values ('241368579', '1', '0', 30001);
insert into mechanicWeekHour values ('241368579', '2', '0', 30001);
insert into mechanicWeekHour values ('241368579', '3', '0', 30001);
insert into mechanicWeekHour values ('241368579', '4', '0', 30001);

insert into mechanicWeekHour values ('423186759', '1', '0', 30002);
insert into mechanicWeekHour values ('423186759', '2', '0', 30002);
insert into mechanicWeekHour values ('423186759', '3', '0', 30002);
insert into mechanicWeekHour values ('423186759', '4', '0', 30002);

insert into mechanicWeekHour values ('123789456', '1', '0', 30002);
insert into mechanicWeekHour values ('123789456', '2', '0', 30002);
insert into mechanicWeekHour values ('123789456', '3', '0', 30002);
insert into mechanicWeekHour values ('123789456', '4', '0', 30002);

insert into mechanicWeekHour values ('789123456', '1', '0', 30002);
insert into mechanicWeekHour values ('789123456', '2', '0', 30002);
insert into mechanicWeekHour values ('789123456', '3', '0', 30002);
insert into mechanicWeekHour values ('789123456', '4', '0', 30002);

insert into mechanicWeekHour values ('125689347', '1', '0', 30002);
insert into mechanicWeekHour values ('125689347', '2', '0', 30002);
insert into mechanicWeekHour values ('125689347', '3', '0', 30002);
insert into mechanicWeekHour values ('125689347', '4', '0', 30002);

insert into mechanicWeekHour values ('347812569', '1', '0', 30003);
insert into mechanicWeekHour values ('347812569', '2', '0', 30003);
insert into mechanicWeekHour values ('347812569', '3', '0', 30003);
insert into mechanicWeekHour values ('347812569', '4', '0', 30003);

insert into mechanicWeekHour values ('123456780', '1', '0', 30003);
insert into mechanicWeekHour values ('123456780', '2', '0', 30003);
insert into mechanicWeekHour values ('123456780', '3', '0', 30003);
insert into mechanicWeekHour values ('123456780', '4', '0', 30003);

insert into mechanicWeekHour values ('123456708', '1', '0', 30003);
insert into mechanicWeekHour values ('123456708', '2', '0', 30003);
insert into mechanicWeekHour values ('123456708', '3', '0', 30003);
insert into mechanicWeekHour values ('123456708', '4', '0', 30003);

insert into mechanicWeekHour values ('123456078', '1', '0', 30003);
insert into mechanicWeekHour values ('123456078', '2', '0', 30003);
insert into mechanicWeekHour values ('123456078', '3', '0', 30003);
insert into mechanicWeekHour values ('123456078', '4', '0', 30003);

insert into mechanicWeekHour values ('123450678', '1', '0', 30003);
insert into mechanicWeekHour values ('123450678', '2', '0', 30003);
insert into mechanicWeekHour values ('123450678', '3', '0', 30003);
insert into mechanicWeekHour values ('123450678', '4', '0', 30003);

insert into mechanicWeekHour values ('123405678', '1', '0', 30003);
insert into mechanicWeekHour values ('123405678', '2', '0', 30003);
insert into mechanicWeekHour values ('123405678', '3', '0', 30003);
insert into mechanicWeekHour values ('123405678', '4', '0', 30003);

insert into invoice values(1, 30003, 'P39VN198', 10501, '11/2/2022', 'f', 'Lisa Alberti', 210);
insert into invoiceAndService values(1, '110', 'Tire Services', 210);
insert into invoice values(2, 30003, '39YVS415', 10010, '11/2/2022', 'f', 'Mark Mendez', 175);
insert into invoiceAndService values(2, '101', 'Engine Services', 175);
insert into invoice values(3, 30003, '29T56WC3', 10062, '11/1/2022', 't', 'Jeff Gibson', 105);
insert into invoiceAndService values(3, '109', 'Tire Services', 105);
insert into invoice values(4, 30002, '9R2UHP54', 10035, '11/1/2022', 't', 'Ellie Clark', 420);
insert into invoiceAndService values(4, '105', 'Electrical Services', 420);
insert into invoice values(5, 30002, '5TR3K914', 10053, '11/8/2022', 't', 'Jose Hernandez', 720);
insert into invoiceAndService values(5, '111', 'Heating and A/C Services', 720);
insert into invoice values(6, 30002, '15DC9A87', 10010, '11/13/2022', 't', 'Charles Rodriguez', 220);
insert into invoiceAndService values(6, '114', 'Maintenance Services', 220);
insert into invoice values(7, 30002, '18S5R2D8', 10001, '11/19/2022', 't', 'Jose Hernandez', 195);
insert into invoiceAndService values(7, '113', 'Maintenance Services', 195);


insert into timeslot values(30003, 1, 2, 1, 123405678);
insert into timeslot values(30003, 1, 2, 1, 123450678);
insert into timeslot values(30003, 1, 1, 1, 123456780);
insert into timeslot values(30002, 2, 4, 2, 423186759);
insert into timeslot values(30002, 2, 4, 3, 423186759);
insert into timeslot values(30002, 2, 4, 4, 423186759);
insert into timeslot values(30002, 2, 4, 5, 423186759);
insert into timeslot values(30002, 2, 4, 6, 423186759);

insert into timeslot values(30002, 2, 1, 3, 125689347);
insert into timeslot values(30002, 2, 1, 4, 125689347);
insert into timeslot values(30002, 2, 1, 5, 125689347);
insert into timeslot values(30002, 2, 1, 6, 125689347);

insert into timeslot values(30002, 2, 6, 2, 789123456);
insert into timeslot values(30002, 2, 6, 3, 789123456);
insert into timeslot values(30002, 2, 6, 4, 789123456);
insert into timeslot values(30002, 2, 6, 5, 789123456);
insert into timeslot values(30002, 2, 6, 6, 789123456);
insert into timeslot values(30002, 2, 6, 7, 789123456);
insert into timeslot values(30002, 2, 6, 8, 789123456);

insert into timeslot values(30002, 3, 5, 5, 125689347);
insert into timeslot values(30002, 3, 5, 6, 125689347);
insert into timeslot values(30002, 3, 5, 7, 125689347);
insert into timeslot values(30002, 3, 5, 8, 125689347);

update mechanicWeekHour set mwh_hour = 1 where mwh_mechanic = 123405678 and mwh_week = 1;
update mechanicWeekHour set mwh_hour = 1 where mwh_mechanic = 123450678 and mwh_week = 1;
update mechanicWeekHour set mwh_hour = 1 where mwh_mechanic = 123456780 and mwh_week = 1;
update mechanicWeekHour set mwh_hour = 5 where mwh_mechanic = 423186759 and mwh_week = 2;
update mechanicWeekHour set mwh_hour = 4 where mwh_mechanic = 125689347 and mwh_week = 2;
update mechanicWeekHour set mwh_hour = 7 where mwh_mechanic = 789123456 and mwh_week = 2;
update mechanicWeekHour set mwh_hour = 4 where mwh_mechanic = 125689347 and mwh_week = 3;

insert into serviceType values('101', 'Belt Replacement', 'r');
insert into serviceType values('102', 'Engine Repair', 'r');
insert into serviceType values('103', 'Exhaust Repair', 'r');
insert into serviceType values('104', 'Muffler Repair', 'r');
insert into serviceType values('105', 'Alternator Repair', 'r');
insert into serviceType values('106', 'Power Lock Repair', 'r');
insert into serviceType values('107', 'Axle Repair', 'r');
insert into serviceType values('108', 'Brake Repair', 'r');
insert into serviceType values('109', 'Tire Balancing', 'r');
insert into serviceType values('110', 'Wheel Alignment', 'r');
insert into serviceType values('111', 'Compressor Repair', 'r');
insert into serviceType values('112', 'Evaporator Repair', 'r');
insert into serviceType values('113', 'Oil Changes', 'm');
insert into serviceType values('113', 'Filter Replacements', 'm');
insert into serviceType values('114', 'Oil Changes', 'm');
insert into serviceType values('114', 'Filter Replacements', 'm');
insert into serviceType values('114', 'Brake Repair', 'm r');
insert into serviceType values('114', 'Check Engine Light Diagnostics', 'm');
insert into serviceType values('115', 'Oil Changes', 'm');
insert into serviceType values('115', 'Filter Replacements', 'm');
insert into serviceType values('115', 'Brake Repair', 'm r');
insert into serviceType values('115', 'Check Engine Light Diagnostics', 'm');
insert into serviceType values('115', 'Suspension Repair', 'm');
insert into serviceType values('115', 'Evaporator Repair', 'm r');