insert into db.k_pac_set(title) value('set1');
insert into db.k_pac_set(title) value('set2');

insert into db.k_pac(title, description, creation_date) values('title1', 'description1', DATE_SUB(CURDATE(), INTERVAL 4 DAY));
insert into db.k_pac(title, description, creation_date) values('title2', 'description2', DATE_SUB(CURDATE(), INTERVAL 1 DAY));
insert into db.k_pac(title, description, creation_date) values('title3', 'description3', current_date());
insert into db.k_pac(title, description, creation_date) values('title4', 'That element can be removed', DATE_SUB(CURDATE(), INTERVAL 2 DAY));
insert into db.k_pac(title, description, creation_date) values('title5', 'description5', DATE_ADD(CURDATE(), INTERVAL 3 DAY));
insert into db.k_pac(title, description, creation_date) values('title6', 'description6', DATE_ADD(CURDATE(), INTERVAL 1 DAY));

insert into db.k_pac_set_k_pacs(id_k_pac_set, id_k_pac) value(1, 1);
insert into db.k_pac_set_k_pacs(id_k_pac_set, id_k_pac) value(1, 2);
insert into db.k_pac_set_k_pacs(id_k_pac_set, id_k_pac) value(1, 3);
insert into db.k_pac_set_k_pacs(id_k_pac_set, id_k_pac) value(1, 6);

insert into db.k_pac_set_k_pacs(id_k_pac_set, id_k_pac) value(2, 2);
insert into db.k_pac_set_k_pacs(id_k_pac_set, id_k_pac) value(2, 5);