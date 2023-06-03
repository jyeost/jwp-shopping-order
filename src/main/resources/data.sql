INSERT INTO product (name, price, image_url) VALUES ('치킨', 10000, 'https://images.unsplash.com/photo-1626082927389-6cd097cdc6ec?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=2370&q=80');
INSERT INTO product (name, price, image_url) VALUES ('샐러드', 20000, 'https://images.unsplash.com/photo-1512621776951-a57141f2eefd?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=2370&q=80');
INSERT INTO product (name, price, image_url) VALUES ('피자', 13000, 'https://images.unsplash.com/photo-1595854341625-f33ee10dbf94?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1740&q=80');

-- CREATE PROCEDURE create_user(
--     PARAM_EMAIL VARCHAR(255),
--     PARAM_PASSWORD VARCHAR(255)
-- )
-- BEGIN
--     SET @PARAM_MEMBER_ID = NULL;
--
-- INSERT INTO member (email, password) VALUES (PARAM_EMAIL, PARAM_PASSWORD);
--
-- SELECT id INTO @PARAM_MEMBER_ID FROM member WHERE email = PARAM_EMAIL;
--
-- INSERT INTO point (member_id, point) VALUES (@PARAM_MEMBER_ID, 1000);
-- END;
--
-- CALL create_user('a@a.com', '1234');
-- CALL create_user('b@b.com', '1234');


INSERT INTO member (email, password) VALUES ('a@a.com', '1234');
INSERT INTO member (email, password) VALUES ('b@b.com', '1234');

INSERT INTO point (member_id, point) VALUES (1, 10000);
INSERT INTO point (member_id, point) VALUES (2, 20000);


INSERT INTO cart_item (member_id, product_id, quantity) VALUES (1, 1, 2);
INSERT INTO cart_item (member_id, product_id, quantity) VALUES (1, 2, 4);

INSERT INTO cart_item (member_id, product_id, quantity) VALUES (2, 3, 5);


-- INSERT INTO orders (member_id) VALUES (1);
-- INSERT INTO order_item (order_id, product_id, quantity, price) VALUES (1, 1, 10, 1000);
-- INSERT INTO order_item (order_id, product_id, quantity, price) VALUES (1, 2, 20, 2000);
-- INSERT INTO order_item (order_id, product_id, quantity, price) VALUES (1, 3, 30, 3000);
--
-- INSERT INTO orders (member_id) VALUES (1);
-- INSERT INTO order_item (order_id, product_id, quantity, price) VALUES (2, 1, 100, 10);
-- INSERT INTO order_item (order_id, product_id, quantity, price) VALUES (2, 2, 200, 20);
--
--
-- INSERT INTO orders (member_id) VALUES (2);
-- INSERT INTO order_item (order_id, product_id, quantity, price) VALUES (3, 1, 1, 100);
-- INSERT INTO order_item (order_id, product_id, quantity, price) VALUES (3, 2, 2, 200);
