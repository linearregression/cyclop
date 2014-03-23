# Overview
Cassandra has already a few CQL3 editors, but Cyclop is a bit different. It’s a lightweight  web based  editor - once installed you can access it from web browser and still enjoy native application feeling.

# Authorization
![Login](/doc/img_v1.3/login.png)

User name and password are used to create Cassandra session,  which is bind to active HTTP Session. Cyclop itself does not keep or persist credentials. Only remember-me function does it as encrypted cookie in browser.

# Query Editor
![Cyclop Pain Page](/doc/img_v1.3/overview.png)

#### Editor
* supports context based CQL 3 completion. So for example completion list contains tables that belong to active keyspace - if you previously executed "use myKespace" query, or tables that belong to keyspace that is part of the query that you are typing (select * from myKespace.xyz...), or columns of table if its already provided in query (insert into table values .... ).
* keyboard navigation:
   * Enter - confirms currently highlighted completion
   * Tab - next completion value
   * Ctrl+Enter - executes query
   * ESC - cancel completion
* completion hint - the green pop up on the right side shows all possible completion values. Font has different colors for table, keyspace, column, type and keyword.
* query syntax help - contains CQL syntax help copied from original Cassandra documentation. It is decorated with color highlighting matching "completion hint" colors.

#### Results Table
* results table is column-oriented, it’s reversed when compared to traditional SQL editors - rows are displayed horizontally, and columns vertically. When scrolling page from left to right you will switch between rows, scrolling from top to bottom will show follow up columns.
* columns are displayed in order returned by the query, but additionally they are grouped into two sections divided by separator line. The top of the table contains "static columns" - their values are not empty in multiple rows returned by the query. The second section contains columns, which value is not empty only for single row. Cassandra supports dynamic columns, and the idea is to have "static" columns at the top of the table, and "dynamic" ones on the bottom.
* table header for each row displays partition key value, assuming that query returns it
* long text is trimmed to fit into table cell, clicking on it opens popup containing the whole

#### General
* CQL query can be bookmarked. This is convenient for frequently used queries, or if you like to share it with somebody else.
* Query results can be exported to CSV file
  
# Screenshots
![CQL Syntax Help](/doc/img_v1.3/cql_syntax_help.png)
![Popup Display](/doc/img_v1.3/large_content.png)
![CQL Completion](/doc/img_v1.3/completion_space_tables.png)
![CQL Completion Colors](/doc/img_v1.3/completion_colors.png)

# Requirements
* java 7
* cassandra v1.2 or above (tested with 1.2 and 2.0)
* supports only schema created with CQL 3
* CLI schamas are NOT supported
* Tomcat v7 or another v3.x web container

# Technologies
* web app - v3.x
* maven - v3.x
* spring - v3.x
* wicket - v6.x
* wicket-auth-roles - v6.x
* bootstrap - v3.x (theme: cyborg from bootswatch)
* jQuery UI - v1.10.x
* cassandra-driver-core - v1.x
* slf4j/logback - v1.7.x
* hibernate validator - v4.x
* guava - v15.x (Cassandra 2.0 does not work with v16)


# Installation
1. Download last release: <code>https://github.com/maciejmiklas/cyclop/releases/latest</code>
2. Edit property file: <code>cyclop/src/main/resources/cyclop.properties</code> and set connection settings for Cassandra
``` properties
cassandra.hosts: localhost
cassandra.port: 9042
cassandra.useSsl: false
cassandra.timeoutMilis: 3600000
```
You can also overwrite each property from <code>cyclop.properties</code> by setting it as jvm parameter, for example to connect to different Cassandra host set:<code>-Dcassandra.hosts=server1,server2</code>. This gives you simple possibility to change properties after the war file has been assembled.

4. Optionally change logger settings by editing <code>logback.xml</code> (it's using console appender)
5. Build war file: <code>mvn package</code> 
5. Drop war file into tomcat

The created war can connect only to one Cassandra cluster, in order to serve multiple clusters from one Tomcat you have to deploy few cyclop war archives, each one with different  <code>cassandra.hosts</code> value


