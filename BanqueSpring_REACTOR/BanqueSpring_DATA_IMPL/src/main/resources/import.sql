INSERT INTO banque_personne (personne_id, personne_nom, personne_login, personne_mot_de_passe) VALUES (1, 'conseiller', 'conseil', 'conseil'), (2 , 'client', 'client', 'client');
INSERT INTO banque_conseiller (personne_id) VALUES (1);
INSERT INTO banque_client (personne_id, client_id_conseil) VALUES (2, 1);
INSERT INTO banque_compte (compte_numero, compte_date_creation, compte_id_client) VALUES (1, '2012-12-21', 2), (2, '2012-12-22', 2);
INSERT INTO banque_compte_courant (compte_numero, compte_courant_decouvert) VALUES (1, 500), (2, 1000);