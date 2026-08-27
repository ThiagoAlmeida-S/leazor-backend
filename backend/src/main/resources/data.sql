-- Cadastra o robô único
IF NOT EXISTS (SELECT 1 FROM tb_dispositivo WHERE id = 1)
BEGIN
INSERT INTO tb_dispositivo (id, nome, status) VALUES (1, 'Leazor Bot', 'PARADO');
END

-- Cadastra os 6 sensores vinculados ao robô (ID 1)
IF NOT EXISTS (SELECT 1 FROM tb_sensor WHERE id = 1)
BEGIN
INSERT INTO tb_sensor (id, nome, tipo, unidade_medida, dispositivo_id) VALUES (1, 'Bateria', 'BATERIA', '%', 1);
INSERT INTO tb_sensor (id, nome, tipo, unidade_medida, dispositivo_id) VALUES (2, 'Ultrassônico Frente', 'ULTRASSONICO', 'cm', 1);
INSERT INTO tb_sensor (id, nome, tipo, unidade_medida, dispositivo_id) VALUES (3, 'Módulo GPS', 'GPS', 'COORD', 1);
INSERT INTO tb_sensor (id, nome, tipo, unidade_medida, dispositivo_id) VALUES (4, 'Temperatura DHT', 'TEMPERATURA', '°C', 1);
INSERT INTO tb_sensor (id, nome, tipo, unidade_medida, dispositivo_id) VALUES (5, 'Umidade DHT', 'UMIDADE', '%', 1);
INSERT INTO tb_sensor (id, nome, tipo, unidade_medida, dispositivo_id) VALUES (6, 'Giroscópio Inclinação', 'INCLINACAO', '°', 1);
END