INSERT INTO tool_type (ID , Name, DAILY_CHARGE_AMOUNT, WEEK_DAY_CHARDGE, WEEK_END_CHARGE, HOLIDAY_CHARGE)
VALUES (1 , 'Ladder', 1.99, true, true, false);
INSERT INTO tool_type (ID , Name, DAILY_CHARGE_AMOUNT, WEEK_DAY_CHARDGE, WEEK_END_CHARGE, HOLIDAY_CHARGE)
VALUES (3,'Chainsaw', 1.49, true, false, true);
INSERT INTO tool_type (ID, Name, DAILY_CHARGE_AMOUNT, WEEK_DAY_CHARDGE, WEEK_END_CHARGE, HOLIDAY_CHARGE)
VALUES ( 2 , 'Jackhammer', 2.99, true, false, false);

INSERT INTO tool_brand (ID , name) VALUES (1 , 'Stihl');
INSERT INTO tool_brand (ID , name) VALUES (2 , 'Werner');
INSERT INTO tool_brand (ID , name) VALUES (3 , 'DeWalt');
INSERT INTO tool_brand (ID , name) VALUES (4 , 'Ridgid');

INSERT INTO tool (tool_code, TOOL_TYPE_ID, BRAND_ID) VALUES ('CHNS', 3, 1);
INSERT INTO tool (tool_code, TOOL_TYPE_ID, BRAND_ID) VALUES ('LADW', 1, 2);
INSERT INTO tool (tool_code, TOOL_TYPE_ID, BRAND_ID) VALUES ('JAKD', 2, 3);
INSERT INTO tool (tool_code, TOOL_TYPE_ID, BRAND_ID) VALUES ('JAKR', 3, 4);