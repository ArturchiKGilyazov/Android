CREATE DATABASE projectdatabase;
CREATE DATABASE testDataBase;
-- \c projectdatabase
-- CREATE TABLE USER_TABLE(
--     user_id INTEGER PRIMARY KEY,
--     user_name TEXT,
--     password TEXT,
--     category INTEGER[]
-- );
-- CREATE TABLE CATEGORY_TABLE(
--     category_id INTEGER PRIMARY KEY,
--     category_name TEXT,
--     is_default boolean,
--     user_id INTEGER
-- );
-- CREATE TABLE WORD_TABLE (
--     word_id INTEGER PRIMARY KEY,
--     word_name TEXT,
--     category INTEGER,
--     path_to_sound TEXT
-- );
-- -- ALTER TABLE USER_TABLE ADD COLUMN user_id INTEGER;
-- CREATE SEQUENCE user_id_seq OWNED BY USER_TABLE.user_id;
-- ALTER TABLE USER_TABLE ALTER COLUMN user_id SET DEFAULT nextval('user_id_seq');
-- UPDATE USER_TABLE SET user_id = nextval('user_id_seq');


-- -- ALTER TABLE WORD_TABLE ADD COLUMN word_id INTEGER;
-- CREATE SEQUENCE word_id_seq OWNED BY WORD_TABLE.word_id;
-- ALTER TABLE WORD_TABLE ALTER COLUMN word_id SET DEFAULT nextval('word_id_seq');
-- UPDATE WORD_TABLE SET word_id = nextval('word_id_seq');


-- -- ALTER TABLE CATEGORY_TABLE ADD COLUMN category_id INTEGER;
-- CREATE SEQUENCE category_id_seq OWNED BY CATEGORY_TABLE.category_id;
-- ALTER TABLE CATEGORY_TABLE ALTER COLUMN category_id SET DEFAULT nextval('category_id_seq');
-- UPDATE CATEGORY_TABLE SET category_id = nextval('category_id_seq');

-- ALTER TABLE WORD_TABLE
-- ADD FOREIGN KEY (category) REFERENCES CATEGORY_TABLE(category_id);
-- ALTER TABLE USER_TABLE
-- ADD FOREIGN KEY (category) REFERENCES CATEGORY_TABLE(category_id);