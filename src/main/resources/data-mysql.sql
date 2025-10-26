INSERT INTO role (name) VALUES ("CUSTOMER");
INSERT INTO role (name) VALUES ("AUDITOR");

INSERT INTO permission (name) VALUES ("character:read-all");
INSERT INTO permission (name) VALUES ("character:read-detail");
INSERT INTO permission (name) VALUES ("comic:read-all");
INSERT INTO permission (name) VALUES ("comic:read-by-id");
INSERT INTO permission (name) VALUES ("user-interaction:read-my-interactions");
INSERT INTO permission (name) VALUES ("user-interaction:read-all");
INSERT INTO permission (name) VALUES ("user-interaction:read-by-username");

INSERT INTO granted_permission (role_id, permission_id) VALUES(1,1);
INSERT INTO granted_permission (role_id, permission_id) VALUES(1,2);
INSERT INTO granted_permission (role_id, permission_id) VALUES(1,3);
INSERT INTO granted_permission (role_id, permission_id) VALUES(1,4);
INSERT INTO granted_permission (role_id, permission_id) VALUES(1,5);

INSERT INTO granted_permission (role_id, permission_id) VALUES(2,1);
INSERT INTO granted_permission (role_id, permission_id) VALUES(2,2);
INSERT INTO granted_permission (role_id, permission_id) VALUES(2,3);
INSERT INTO granted_permission (role_id, permission_id) VALUES(2,4);
INSERT INTO granted_permission (role_id, permission_id) VALUES(2,5);
INSERT INTO granted_permission (role_id, permission_id) VALUES(2,6);
INSERT INTO granted_permission (role_id, permission_id) VALUES(2,7);

-- User ironman, pswd = Friday1234
INSERT INTO user (username, password, role_id, account_expired, account_locked, credentials_expired, enabled) VALUES ('ironman', '$2a$12$D7FW1fMq.dndz0wbgsLVju.S4EPx1HJOCC2JEPpIIdlsNhCrAZcaS', 1, false, false, false, true)
ON DUPLICATE KEY UPDATE username = username;
-- User thor, pswd = Mjolnir5678
INSERT INTO user (username, password, role_id, account_expired, account_locked, credentials_expired, enabled) VALUES ('thor', '$2a$12$lNgcWz.EfIFoKxGhOKFIA.QizfiHVBTDKBrnlSu.d6LSnczZiwysi', 2, false, false, false, true)
ON DUPLICATE KEY UPDATE username = username;