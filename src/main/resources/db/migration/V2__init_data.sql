-- 관리자 계정 (password: 1111)
INSERT INTO "user" (login_id, password, name, role, use_yn, create_id)
VALUES ('admin', '$2a$10$JHRc1ScPG1dQncw9Jh8oJOyCtIzgi2uCYbQnu4OYz1QVcDs8kWpD2', '관리자', 'ADM','Y', 1);

-- 성별 코드 그룹
INSERT INTO code_group (id, code_group, name, create_id)
VALUES (1, '001', '성별', 1);

-- 성별 코드
INSERT INTO code (id, code_group_id, code, name, info, create_id)
VALUES (1, 1, '001', '남', '남자', 1);
INSERT INTO code (id, code_group_id, code, name, info, create_id)
VALUES (2, 1, '002', '여', '여자', 1);
