## Used technology in project

•	Springboot

•	Akka(actor base)

•	Rabbitmq

•	postgresql

•	phantomjs 

------------------------------------------------------
## About

I will talk about the project briefly.

The project takes screenshots of the websites entered and saves them. When the request comes,

I throw the data to rabbitmq and consume it with another class and send it to the actor.

------------------------------------------------------
## Requirements

•	Postgresql needs to be installed.

•	Rabbitmq must be installed.

•	download phantomjs (download the corresponding os and put it in the directory you set: http://phantomjs.org/download.html, then set the index on the 29th line in the ScreenShoter class)

•	Required postgresql and rabbitmq configurations must be filled in the application.properties file under the project
rabbitmq exchange: screen topic name: screen-data defined to the project.



## Table  create script : 
```bash
CREATE TABLE public.screenshot_website (

	id varchar(255) NOT NULL,
	
	creation_date timestamp NULL,
	creation_user varchar(255) NULL,
	deleted bool NULL,
	last_update_date timestamp NULL,
	last_update_user varchar(255) NULL,
	site_url varchar(255) NOT NULL,
	site_image varchar(10000) NOT NULL,
	site_description varchar(255) NULL,
	CONSTRAINT challenge_pkey PRIMARY KEY (id)
)
```

--------------------------------------------------------------------------------
## Run :

If you write down goals for eclipse it will work.

clean install spring-boot: run

and the jar file that is placed under the target when the build is taken from the console

go to the relevant string

java -jar projename.jar

if you say it works

-------------------------------------------------------

## Rest paths:

create site scrrenShot

POST

http://localhost:8034/create-site-screenshot

body:
{
	"siteUrlList":["http://turkishh.com","http://www.4dsight.com"]
}


## get site screenShot

GET

http://localhost:8034/by/www.turkishh.com

---------------------------------------------------------------------
