-- Insert data for Mangaka table
INSERT INTO Mangaka (name, gender, years)
VALUES ('Eiichiro Oda', 'M', 47),
       ('Kouji Mori', 'M', 55),
       ('Masashi Kishimoto', 'M', 47),
       ('Tite Kubo', 'M', 45),
       ('Naoshi Komi', 'M', 36),
       ('Mikio Ikemoto', 'M', 45),
       ('Kentaro Miura', 'M', 54);

INSERT INTO Publisher (name, address, startdate)
VALUES ('Shueisha', 'Tokyo', '1926-08-08'),
       ('Konami', 'Tokyo', '1969-03-21'),
       ('Hakusensha', 'Tokyo', '1973-12-01');

-- Insert data for Manga table
INSERT INTO Manga (title, author, chapters, written, genre, pricepercopy, publisher_id)
VALUES ('One Piece', 'Eiichiro Oda', 1062, '2001-08-11', 'SHONEN', 12.5, 1),
       ('Naruto', 'Masashi Kishimoto', 700, '2002-12-16', 'ACTION', 10, 1),
       ('Bleach', 'Tite Kubo', 686, '2003-10-20', 'FANTASY', 13.6, 1),
       ('Nisekoi', 'Naoshi Komi', 282, '2004-06-02', 'ROMANCE', 11.3, 2),
       ('Berserk', 'Kentaro Miura', 370, '2003-03-29', 'HORROR', 10.99, 3),
       ('Jisatsutou', 'Kouji Mori', 168, '2002-04-01', 'HORROR', 11.99, 3);
-- Insert data for Publisher table


INSERT INTO Ownership (manga_id, mangaka_id)
VALUES
    -- Relationship between manga2 (Naruto) and mangaka6 (Mikio Ikemoto)
    (2, 6),
    -- Relationship between manga2 (Naruto) and mangaka3 (Masashi Kishimoto)
    (2, 3),
    -- Relationship between manga5 (Berserk) and mangaka7 (Kentaro Miura)
    (5, 7),
    -- Relationship between manga5 (Berserk) and mangaka2 (Kouji Mori)
    (5, 2),
    -- Relationship between manga6 (Jisatsutou) and mangaka2 (Kouji Mori)
    (6, 2),
    -- Relationship between manga1 (One Piece) and mangaka1 (Eiichiro Oda)
    (1, 1),
    -- Relationship between manga3 (Bleach) and mangaka4 (Tite Kubo)
    (3, 4),
    -- Relationship between manga4 (Nisekoi) and mangaka5 (Naoshi Komi)
    (4, 5);
