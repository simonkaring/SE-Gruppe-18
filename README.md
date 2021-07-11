# Software Engineering Semester Projekt Gruppe 18
[Wiki logbog link](https://github.com/simonkaring/SE-Gruppe-18/wiki/Logbook)

## Running
Run KrediteringSystem to populate database with information. 

## Program set up checklist with intellij
- [ ] Download javafx
- [ ] Add javafx lib in File -> Project Structure -> Libraries
- [ ] Add VM options in run configurations: `--module-path /path/to/javafx-sdk-15.0.1/lib --add-modules javafx.controls,javafx.fxml`

## Scene builder 
To set up GUI

## Running with database
Create postgres Database with pgadmin or equivalent. 
Use the following for table set up
```
CREATE TABLE producenter (
	id SERIAL PRIMARY KEY,
	navn VARCHAR(80) NOT NULL
);

CREATE TABLE programmer (
	id SERIAL PRIMARY KEY,
	navn VARCHAR(120) NOT NULL,
	producent_id INTEGER NOT NULL REFERENCES producenter(id)
);

CREATE TABLE personer (
	id SERIAL PRIMARY KEY,
	fornavn VARCHAR(50) NOT NULL,
	efternavn VARCHAR(50) NOT NULL,
	nationalitet VARCHAR(50) NOT NULL,
	dag INTEGER NOT NULL,
	maaned INTEGER NOT NULL,
	aar INTEGER NOT NULL
);

CREATE TABLE roller (
	id SERIAL PRIMARY KEY,
	navn VARCHAR(100) NOT NULL,
	type VARCHAR(50) NOT NULL,
	person_id INTEGER REFERENCES personer(id)
);

CREATE TABLE program_rolle (
	program_id INTEGER NOT NULL REFERENCES programmer(id),
	rolle_id INTEGER NOT NULL REFERENCES roller(id)
);

```
