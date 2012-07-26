update product set hazard='4' where hazard='3';
update product set hazard='3' where hazard='2';
update product set hazard='2' where hazard='1';
update product set hazard='1' where hazard='0';
update product set hazard='0' where hazard='00';

update e set category = category::int + 1;