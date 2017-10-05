SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';


DROP DATABASE IF EXISTS`database-here`;

-- -----------------------------------------------------
-- Schema database-here
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `database-here` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `database-here` ;

-- -----------------------------------------------------
-- Table `database-here`.`Artist`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `database-here`.`Artist` (
  `Name` VARCHAR(45) NULL,
  `Song` VARCHAR(60) NULL,
  `Album` VARCHAR(45) NULL,
  `Year` INT NULL,
  `Nationality` VARCHAR(45) NULL)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Table `database-here`.`Highscore`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `database-here`.`Highscore` (
  `Username` VARCHAR(45) NULL,
  `Score` INT NULL)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;


-- -----------------------------------------------------
-- Table `database-here`.`Artist` Insertion values
-- -----------------------------------------------------

insert into Artist values
  ('Katy Perry feat. Snoop Dogg', 'California Gurls', 'Teenage Dream', 2010, 'America'),
  ('The Lovin'' Spoonful', 'Summer in the City', 'Hums of the Lovin'' Spoonful', 1966, 'America'),
  ('The Surfaris', 'Wipe Out', 'Wipe Out', 1963, 'America'),
  ('Brian Hyland', 'Itsy Bitsy Teenie Weenie Yellow Polka Dot Bikini', 'The Bashful Blond', 1960, 'America'),
  ('Jan and Dean', 'Surf City', 'Surf City and Other Swingin'' Cities', 1963, 'America'),
  ('Richard Marx', 'Endless Summer Nights', 'Richard Marx', 1988, 'America'),
  ('DJ Jazzy Jeff and the Fresh Prince', 'Summertime', 'Homebase', 1991, 'America'),
  ('The Beach Boys', 'Surfin'' U.S.A.', 'Surfin'' U.S.A.', 1963, 'America'),
  ('Sly and the Family Stone', 'Hot Fun in the Summertime', 'I''m Back! Family & Friends', 1969, 'America'),
  ('John Travolta and Olivia Newton-John ', 'Summer Nights', 'Grease', 1978, 'America'),
  ('Don Henley', 'The Boys of Summer', 'Building the Perfect Beast', 1985, 'America'),
  ('Mungo Jerry', 'In the Summertime', 'In the Summertime', 1970, 'England'),
  ('Bryan Adams', 'Summer of 69', 'Reckless', 1985, 'Canada'),
  ('The Drifters', 'Under the Boardwalk', 'Under the Boardwalk', 1964, 'America'),
  ('War', 'Summer', 'Greatest Hits', 1976, 'America'),
  ('The Beach Boys', 'California Girls', 'Summer Days (And Summer Nights!!)', 1965, 'America'),
  ('LFO', 'Summer Girls', 'LFO', 1999, 'America'),
  ('Chicago', 'Saturday in the Park', 'Chicago V', 1972, 'America'),
  ('Justin Timberlake', 'Summer Love', 'FutureSex/LoveSounds', 2007, 'America'),
  ('Nat King Cole', 'Those Lazy-Hazy-Crazy Days of Summer', 'Those Lazy-Hazy-Crazy Days of Summer', 1963, 'America'),
  ('Alice Cooper', 'School''s Out', 'School''s Out', 1972, 'America'),
  ('Seals and Crofts', 'Summer Breeze', 'Summer Breeze', 1972, 'America'),
  ('The Beach Boys', 'Surfer Girl', 'Surfer Girl', 1963, 'America'),
  ('The Motels', 'Suddenly Last Summer', 'Little Robbers', 1983, 'America'),
  ('Chad and Jeremy', 'A Summer Song', 'Yesterday''s Gone', 1964, 'England'),
  ('Eddie Cochran', 'Summertime Blues', 'The Eddie Cochran Memorial Album', 1958, 'America'),
  ('Bananarama', 'Cruel Summer', 'Bananarama', 1983, 'England'),
  ('Fat Boys feat. The Beach Boys', 'Wipeout', 'Wipeout', 1987, 'America'),
  ('Billy Stewart', 'Summertime', 'Single', 1966, 'America'),
  ('The Beach Boys', 'Surfin'' Safari', 'Surfin'' Safari', 1962, 'America')
;

-- -----------------------------------------------------
-- Table `database-here`.`Highscore` Insertion values
-- -----------------------------------------------------

insert into Highscore values
  ('Jon', 10),
  ('Andreas', 7),
  ('Unknown', 3)
;