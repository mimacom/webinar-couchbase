#README for polls

## Requisites

You need to install Java7, Node, Couchbase server, Maven

###Steps to configure your couchbase server in polls:

#### Initialise the database
1. Set the following **maven properties**:
  * **couchbase.username:** administrator username
  * **couchbase.pass:** administrator password
  * **couchbase.host:** couchbase host
  * **couchbase.bucket:** name of the bucket to create (polls)
  * **couchbase.ramsize:** ram size for the bucket

2. Run maven plugin **exec** with init profile to create the buckets in your Couchbase server 
3. Run maven plugin **exec** with local profile to load the views

#### Download frontend dependencies

1. Open a console and run **npm install** from the root directory of the project to download all dependencies
2. Open a console and run **bower install** from the root directory of the project to download all dependencies

#### Build frontend

Run grunt task **build** to generate the frontend

#### Java tests

To run the tests, change the properites in *couchbase.properties* file in test folder.

### Run the application!
Configure a server, i.e. tomcat 7, and run the application!

