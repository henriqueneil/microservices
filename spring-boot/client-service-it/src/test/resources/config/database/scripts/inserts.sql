-- Test data for search by email contains
INSERT INTO CLIENT (id, name, email)
    VALUES ('a4268b95-bf1b-41f6-8f53-93c61ac9e34a', 'Client Email Contains 1', 'client1@contains.com');
INSERT INTO CLIENT (id, name, email)
    VALUES ('925f20d0-07e4-41ac-80e6-a20ad042c1db', 'Client Email Contains 2', 'client2@contains.com');
INSERT INTO CLIENT (id, name, email)
    VALUES ('9e1cc664-472f-453f-8a00-86653eea466d', 'Client Email Contains 3', 'client3@contains.com');

-- Test data for search by email starts with
INSERT INTO CLIENT (id, name, email)
    VALUES ('58c9dabf-d378-4d64-99f0-73387949dbff', 'Client Email Starts With 1', 'startswith@e_mail.com');
INSERT INTO CLIENT (id, name, email)
    VALUES ('130b1b7d-3f03-4510-8855-dadbf80d15e9', 'Client Email Starts With 2', 'startswith.@e_mail.com');
INSERT INTO CLIENT (id, name, email)
    VALUES ('0ba0cf90-7c6b-4249-8c5f-e2a9c8a9d6df', 'Client Email Starts With 3', 'startswith_@e_mail.com');

-- Test data for search by email ends with
INSERT INTO CLIENT (id, name, email)
    VALUES ('a8a7101b-ac6a-481d-8d3a-4a368cb6f8fd', 'Client Email Ends With 1', 'email@email.endswith');
INSERT INTO CLIENT (id, name, email)
    VALUES ('c8b9c2c0-b017-4b58-9a3b-a806f221f060', 'Client Email Ends With 2', 'email@email.e_endswith');
INSERT INTO CLIENT (id, name, email)
    VALUES ('950e8fc5-dc2e-4ecc-959a-08c43ec4c07a', 'Client Email Ends With 3', 'email@email._endswith');

-- Test data for search by name starts with
INSERT INTO CLIENT (id, name, email)
    VALUES ('edfc0afb-1a3d-4001-aec0-58cad14e39e5', 'StartsWith Client 1', 'email@email');
INSERT INTO CLIENT (id, name, email)
    VALUES ('5ad8d32d-6824-4762-8d9b-c02792b2bd8a', 'StartsWith.Client.2', 'email@email');
INSERT INTO CLIENT (id, name, email)
    VALUES ('f4cd00b3-a551-425a-b6c3-568385bac5a2', 'StartsWith_Client_3', 'email@email');

-- Test data for search by name ends with
INSERT INTO CLIENT (id, name, email)
    VALUES ('4ebf6304-0478-4904-b932-53f731fa1737', 'Client 1 EndsWith', 'email@email');
INSERT INTO CLIENT (id, name, email)
    VALUES ('67b9eefc-77a0-4c00-b585-c9c67b86a97c', 'Client.2.EndsWith', 'email@email');
INSERT INTO CLIENT (id, name, email)
    VALUES ('6e66e4fc-2736-4076-8fd3-704d36a995f9', 'Client_3_EndsWith', 'email@email');

-- Test data for search by name contains
INSERT INTO CLIENT (id, name, email)
    VALUES ('48d15e73-4c58-4214-b9e9-e7f92d2dc22b', 'Client Name Contains 1', 'email@email');
INSERT INTO CLIENT (id, name, email)
    VALUES ('370b3d93-3463-42a6-901c-bb9c441a9d46', 'Client Name.Contains.2', 'email@email');
INSERT INTO CLIENT (id, name, email)
    VALUES ('7627d2e5-9548-4d78-be40-2ac7838a0ff5', 'Client Name_Contains_3', 'email@email');

-- Test data for update
INSERT INTO CLIENT (id, name, email)
    VALUES ('aade6a2d-9424-4d83-8f31-5a96d501a4ca', 'Update Client Test 1', 'update@update.com');
INSERT INTO CLIENT (id, name, email)
    VALUES ('268d0e81-f5d7-40db-a899-1c50d0b95b08', 'Update Client Test 2', 'update@update.com');
INSERT INTO CLIENT (id, name, email)
    VALUES ('5a49239a-e8ca-46a5-b7d3-2ad419af32f1', 'Update Client Test 3', 'update@update.com');

-- Test data for deletion
INSERT INTO CLIENT (id, name, email)
    VALUES ('7804b871-626c-40bf-8aa3-459d52cc1331', 'Delete Client Test 1', 'delete@delete.com');
INSERT INTO CLIENT (id, name, email)
    VALUES ('e5d0015e-50b2-4e5f-8631-b6760274339d', 'Delete Client Test 2', 'delete@delete.com');
INSERT INTO CLIENT (id, name, email)
    VALUES ('3ee7a46b-977c-4028-b9ae-4dec7cbfac30', 'Delete Client Test 3', 'delete@delete.com');

-- Test data for get client
INSERT INTO CLIENT (id, name, email)
    VALUES ('6a13534c-da14-4916-98c5-26da758953b3', 'Get Client Test 1', 'getclient1@get.com');
INSERT INTO CLIENT (id, name, email)
    VALUES ('55657b95-9e86-4058-98df-db3b9250196d', 'Get Client Test 2', 'getclient2@get.com');
INSERT INTO CLIENT (id, name, email)
    VALUES ('4c4f2263-5c15-4e06-a72d-7bf02b2f8446', 'Get Client Test 3', 'getclient3@get.com');