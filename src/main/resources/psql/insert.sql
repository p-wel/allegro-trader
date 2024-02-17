-- insert.sql

-- PRODUCT
INSERT INTO product (id, name, category, image, date_of_creation) VALUES (1, 'Szyba przednia Toyota', 'Motoryzacja', 'https://media.istockphoto.com/id/1150931120/pl/zdj%C4%99cie/ilustracja-3d-og%C3%B3lnego-kompaktowego-bia%C5%82ego-samochodu-widok-z-przodu.jpg?s=2048x2048&w=is&k=20&c=MAaPrTdvk4qkiDA5Bu3oU2BedxetsuAPsr372KZg73Q=', '2024-02-17');
INSERT INTO product (id, name, category, image, date_of_creation) VALUES (2, 'Filtr paliwa Toyota', 'Motoryzacja', 'https://media.istockphoto.com/id/1136053255/pl/zdj%C4%99cie/r%C4%99czne-nape%C5%82nianie-samochodu-paliwem-na-stacji-tankowania.jpg?s=2048x2048&w=is&k=20&c=haA3vUWI0d5PhmMgS3qPvn8RV5NTzECLQk94UpCL7uE=', '2024-02-17');
INSERT INTO product (id, name, category, image, date_of_creation) VALUES (3, 'Drzwi Ford', 'Motoryzacja', 'https://media.istockphoto.com/id/1377713917/pl/zdj%C4%99cie/otwarte-drzwi-samochodu-osobowego.jpg?s=2048x2048&w=is&k=20&c=qHPTrNAhWBddIa2l0kzPCm7E0Vqz6dLDuVo8TYNI3QY=', '2024-02-17');
INSERT INTO product (id, name, category, image, date_of_creation) VALUES (4, 'Lusterko Volkswagen', 'Motoryzacja', 'https://media.istockphoto.com/id/487091534/pl/zdj%C4%99cie/zach%C3%B3d-s%C5%82o%C5%84ca-w-lusterko-wsteczne.jpg?s=2048x2048&w=is&k=20&c=taSBtxXrHdCrrMykNEooOU95u4CUug7rxqt3Msn8czs=', '2024-02-17');

-- OFFER DRAFT
INSERT INTO offer_draft (id, product_id, title, description, price) VALUES (1, 4, 'Nazwa draftu oferty 1', 'Opis draftu oferty 1', 100.99);
INSERT INTO offer_draft (id, product_id, title, description, price) VALUES (2, 4, 'Nazwa draftu oferty 2', 'Opis draftu oferty 2', 200.99);
INSERT INTO offer_draft (id, product_id, title, description, price) VALUES (3, 4, 'Nazwa draftu oferty 3', 'Opis draftu oferty 3', 300.99);
INSERT INTO offer_draft (id, product_id, title, description, price) VALUES (4, 4, 'Nazwa draftu oferty 4', 'Opis draftu oferty 4', 400.99);

-- PUBLISHED OFFER
INSERT INTO published_offer (id, allegro_id, product_id, title, description, price, status) VALUES (1, 1009, 2, 'Tytuł opublikowanej oferty', 'Opis opublikowanej oferty', 111, 1);
INSERT INTO published_offer (id, allegro_id, product_id, title, description, price, status) VALUES (2, 2009, 2, 'Tytuł opublikowanej oferty', 'Opis opublikowanej oferty', 111, 1);
INSERT INTO published_offer (id, allegro_id, product_id, title, description, price, status) VALUES (3, 3009, 2, 'Tytuł opublikowanej oferty', 'Opis opublikowanej oferty', 111, 1);
INSERT INTO published_offer (id, allegro_id, product_id, title, description, price, status) VALUES (4, 4009, 2, 'Tytuł opublikowanej oferty', 'Opis opublikowanej oferty', 111, 1);

-- SEARCH CRITERIA
INSERT INTO search_criteria (id, search_phrase, min_price, max_price, category, city, publish_date_from, publish_date_to, refresh_interval, mailing_enabled, favorite)
VALUES (1, 'toyota', 20000, 50000, 'Motoryzacja', 'Poznań', '2024-02-17', '2024-02-17', 30, false, false);
INSERT INTO search_criteria (id, search_phrase, min_price, max_price, category, city, publish_date_from, publish_date_to, refresh_interval, mailing_enabled, favorite)
VALUES (2, 'ford', 10000, 30000, 'Motoryzacja', 'Poznań', '2024-02-17', '2024-02-17', 27, false, false);
INSERT INTO search_criteria (id, search_phrase, min_price, max_price, category, city, publish_date_from, publish_date_to, refresh_interval, mailing_enabled, favorite)
VALUES (3, 'mercedes', 25000, 55000, 'Motoryzacja', 'Kraków', '2024-02-17', '2024-02-17', 20, false, false);
INSERT INTO search_criteria (id, search_phrase, min_price, max_price, category, city, publish_date_from, publish_date_to, refresh_interval, mailing_enabled, favorite)
VALUES (4, 'audi', 15000, 20000, 'Motoryzacja', 'Poznań', '2024-02-17', '2024-02-17', 25, false, false);
