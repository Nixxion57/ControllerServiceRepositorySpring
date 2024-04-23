drop table if exists Manga cascade;
drop table if exists Mangaka cascade;
drop table if exists Publisher cascade;
drop table if exists Ownership cascade;

CREATE TABLE Publisher
(
    id        INTEGER AUTO_INCREMENT PRIMARY KEY UNIQUE,
    name      VARCHAR(255) NOT NULL,
    address   VARCHAR(255) NOT NULL,
    startdate DATE         NOT NULL
);

CREATE TABLE Manga
(
    id           INTEGER AUTO_INCREMENT PRIMARY KEY UNIQUE,
    title        VARCHAR(255) NOT NULL,
    author       VARCHAR(255),
    chapters     INT,
    written      DATE,
    genre        VARCHAR(50),
    pricepercopy DOUBLE,
    publisher_id int,
    constraint fk_publisher_id foreign key (publisher_id) references Publisher on delete cascade
);

CREATE TABLE Mangaka
(
    id     INTEGER AUTO_INCREMENT PRIMARY KEY UNIQUE,
    name   VARCHAR(255) NOT NULL,
    gender CHAR(1),
    years  INT          NOT NULL

);

CREATE TABLE Ownership
(
    manga_id   INT not null,
    mangaka_id int not null,
    constraint fk_manga_id foreign key (manga_id) references Manga(id) on delete cascade ,
    constraint fk_mangaka_id foreign key (mangaka_id) references Mangaka(id) on delete cascade
);


