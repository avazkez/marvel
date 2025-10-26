SET FOREIGN_KEY_CHECKS=0;
DELETE FROM granted_permission;
DELETE FROM user;
DELETE FROM permission;
DELETE FROM role;
SET FOREIGN_KEY_CHECKS=1;

-- Insert parent tables first
INSERT INTO role (id, name) VALUES (1, "CUSTOMER");
INSERT INTO role (id, name) VALUES (2, "AUDITOR");

INSERT INTO permission (id, name) VALUES (1, "character:read-all");
INSERT INTO permission (id, name) VALUES (2, "character:read-detail");
INSERT INTO permission (id, name) VALUES (3, "comic:read-all");
INSERT INTO permission (id, name) VALUES (4, "comic:read-by-id");
INSERT INTO permission (id, name) VALUES (5, "user-interaction:read-my-interactions");
INSERT INTO permission (id, name) VALUES (6, "user-interaction:read-all");
INSERT INTO permission (id, name) VALUES (7, "user-interaction:read-by-username");

-- Insert child tables after parents
INSERT INTO granted_permission (role_id, permission_id) VALUES(1,1)
	ON DUPLICATE KEY UPDATE role_id = role_id;
INSERT INTO granted_permission (role_id, permission_id) VALUES(1,2)
	ON DUPLICATE KEY UPDATE role_id = role_id;
INSERT INTO granted_permission (role_id, permission_id) VALUES(1,3)
	ON DUPLICATE KEY UPDATE role_id = role_id;
INSERT INTO granted_permission (role_id, permission_id) VALUES(1,4)
	ON DUPLICATE KEY UPDATE role_id = role_id;
INSERT INTO granted_permission (role_id, permission_id) VALUES(1,5)
	ON DUPLICATE KEY UPDATE role_id = role_id;

INSERT INTO granted_permission (role_id, permission_id) VALUES(2,1)
	ON DUPLICATE KEY UPDATE role_id = role_id;
INSERT INTO granted_permission (role_id, permission_id) VALUES(2,2)
	ON DUPLICATE KEY UPDATE role_id = role_id;
INSERT INTO granted_permission (role_id, permission_id) VALUES(2,3)
	ON DUPLICATE KEY UPDATE role_id = role_id;
INSERT INTO granted_permission (role_id, permission_id) VALUES(2,4)
	ON DUPLICATE KEY UPDATE role_id = role_id;
INSERT INTO granted_permission (role_id, permission_id) VALUES(2,5)
	ON DUPLICATE KEY UPDATE role_id = role_id;
INSERT INTO granted_permission (role_id, permission_id) VALUES(2,6)
	ON DUPLICATE KEY UPDATE role_id = role_id;
INSERT INTO granted_permission (role_id, permission_id) VALUES(2,7)
	ON DUPLICATE KEY UPDATE role_id = role_id;

-- User ironman, pswd = Friday1234
INSERT INTO user (username, password, role_id, account_expired, account_locked, credentials_expired, enabled) VALUES ('ironman', '$2a$12$D7FW1fMq.dndz0wbgsLVju.S4EPx1HJOCC2JEPpIIdlsNhCrAZcaS', 1, false, false, false, true)
ON DUPLICATE KEY UPDATE username = username;
-- User thor, pswd = Mjolnir5678
INSERT INTO user (username, password, role_id, account_expired, account_locked, credentials_expired, enabled) VALUES ('thor', '$2a$12$lNgcWz.EfIFoKxGhOKFIA.QizfiHVBTDKBrnlSu.d6LSnczZiwysi', 2, false, false, false, true)
ON DUPLICATE KEY UPDATE username = username;