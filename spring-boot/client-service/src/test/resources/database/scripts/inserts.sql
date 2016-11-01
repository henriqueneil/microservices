-- Test data for search by email contains
INSERT INTO CLIENT (id, name, email)
    VALUES ('A4268B95-BF1B-41F6-8F53-93C61AC9E34A', 'Client 1', 'client1@contains.com');
INSERT INTO CLIENT (id, name, email)
    VALUES ('925F20D0-07E4-41AC-80E6-A20AD042C1DB', 'Client 2', 'client2@contains.com');
INSERT INTO CLIENT (id, name, email)
    VALUES ('9E1CC664-472F-453F-8A00-86653EEA466D', 'Client 3', 'client3@contains.com');

-- Test data for search by email starts with
INSERT INTO CLIENT (id, name, email)
    VALUES ('58C9DABF-D378-4D64-99F0-73387949DBFF', 'Client 4', 'startswith@e_mail.com');
INSERT INTO CLIENT (id, name, email)
    VALUES ('130B1B7D-3F03-4510-8855-DADBF80D15E9', 'Client 5', 'startswith.@e_mail.com');
INSERT INTO CLIENT (id, name, email)
    VALUES ('0BA0CF90-7C6B-4249-8C5F-E2A9C8A9D6DF', 'Client 6', 'startswith_@e_mail.com');

-- Test data for search by email ends with
INSERT INTO CLIENT (id, name, email)
    VALUES ('A8A7101B-AC6A-481D-8D3A-4A368CB6F8FD', 'Client 7', 'email@email.endswith');
INSERT INTO CLIENT (id, name, email)
    VALUES ('C8B9C2C0-B017-4B58-9A3B-A806F221F060', 'Client 8', 'email@email.e_endswith');
INSERT INTO CLIENT (id, name, email)
    VALUES ('950E8FC5-DC2E-4ECC-959A-08C43EC4C07A', 'Client 9', 'email@email._endswith');

-- Test data for search by name starts with
INSERT INTO CLIENT (id, name, email)
    VALUES ('EDFC0AFB-1A3D-4001-AEC0-58CAD14E39E5', 'StartsWith Client 10', 'email@email');
INSERT INTO CLIENT (id, name, email)
    VALUES ('5AD8D32D-6824-4762-8D9B-C02792B2BD8A', 'StartsWith.Client.11', 'email@email');
INSERT INTO CLIENT (id, name, email)
    VALUES ('F4CD00B3-A551-425A-B6C3-568385BAC5A2', 'StartsWith_Client_12', 'email@email');

-- Test data for search by name ends with
INSERT INTO CLIENT (id, name, email)
    VALUES ('4EBF6304-0478-4904-B932-53F731FA1737', 'Client 13 EndsWith', 'email@email');
INSERT INTO CLIENT (id, name, email)
    VALUES ('67B9EEFC-77A0-4C00-B585-C9C67B86A97C', 'Client.14.EndsWith', 'email@email');
INSERT INTO CLIENT (id, name, email)
    VALUES ('6E66E4FC-2736-4076-8FD3-704D36A995F9', 'Client_15_EndsWith', 'email@email');

-- Test data for search by name contains
INSERT INTO CLIENT (id, name, email)
    VALUES ('48D15E73-4C58-4214-B9E9-E7F92D2DC22B', 'Client Contains 16', 'email@email');
INSERT INTO CLIENT (id, name, email)
    VALUES ('370B3D93-3463-42A6-901C-BB9C441A9D46', 'Client.Contains.17', 'email@email');
INSERT INTO CLIENT (id, name, email)
    VALUES ('7627D2E5-9548-4D78-BE40-2AC7838A0FF5', 'Client_Contains_18', 'email@email');

-- Test data for update
INSERT INTO CLIENT (id, name, email)
    VALUES ('AADE6A2D-9424-4D83-8F31-5A96D501A4CA', 'Update Client Test 19', 'update@update.com');
INSERT INTO CLIENT (id, name, email)
    VALUES ('268D0E81-F5D7-40DB-A899-1C50D0B95B08', 'Update Client Test 20', 'update@update.com');
INSERT INTO CLIENT (id, name, email)
    VALUES ('5A49239A-E8CA-46A5-B7D3-2AD419AF32F1', 'Update Client Test 21', 'update@update.com');