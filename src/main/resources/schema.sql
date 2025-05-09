-- Table: events
CREATE TABLE events (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    date_time TIMESTAMP NOT NULL,
    location VARCHAR(255)
);

-- Table: teams
CREATE TABLE teams (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    country VARCHAR(100)
);

-- Table: event_teams (many-to-many between events and teams)
CREATE TABLE event_teams (
    event_id BIGINT,
    team_id BIGINT,
    PRIMARY KEY (event_id, team_id),
    FOREIGN KEY (event_id) REFERENCES events(id),
    FOREIGN KEY (team_id) REFERENCES teams(id)
);

-- Table: videos (one-to-many from events to videos)
CREATE TABLE videos (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    url VARCHAR(1024) NOT NULL,
    description VARCHAR(500),
    event_id BIGINT,
    FOREIGN KEY (event_id) REFERENCES events(id)
);

-- Insert Events
INSERT INTO events (id, name, date_time, location) VALUES
(1, 'FIFA World Cup Final', '2022-12-18 20:00:00', 'Lusail Stadium, Qatar'),
(2, 'Wimbledon Mens Final', '2023-07-16 14:00:00', 'Centre Court, London');

-- Insert Teams
INSERT INTO teams (id, name, country) VALUES
(1, 'Argentina', 'Argentina'),
(2, 'France', 'France'),
(3, 'Novak Djokovic', 'Serbia'),
(4, 'Carlos Alcaraz', 'Spain');

-- Map Teams to Events
INSERT INTO event_teams (event_id, team_id) VALUES
(1, 1), -- FIFA Final: Argentina
(1, 2), -- FIFA Final: France
(2, 3), -- Wimbledon: Djokovic
(2, 4); -- Wimbledon: Alcaraz

-- Insert Videos
INSERT INTO videos (id, url, description, event_id) VALUES
(1, 'https://www.youtube.com/embed/XvDNH2b3QZQ', 'Winning Goal - Argentina', 1),
(2, 'https://www.youtube.com/embed/8MH5bdnrDGQ', 'Penalty Shootout Highlights', 1),
(3, 'https://www.youtube.com/embed/wKxqwTLkkAM', 'Match Point - Djokovic vs Alcaraz', 2);