# cockroachdb-swift-com.cockroachlabs.client

[![](https://img.shields.io/badge/CockroachDB-20.1-darkgreen.svg)](https://com.cockroachlabs.com/)
[![](https://img.shields.io/badge/Kotlin-1.3.72-green.svg)](https://kotlinlang.org/)
[![](https://img.shields.io/badge/Gradle-6.5-darkslategray.svg)](https://gradle.org/)
[![](https://img.shields.io/badge/JetBrains-Exposed-blue.svg)](https://github.com/JetBrains/Exposed)
[![](https://img.shields.io/badge/LICENSE-MIT-red.svg)](https://opensource.org/licenses/MIT)

This package supports the [tutorial sample](../README.md) written in Markdown.

I chose Kotlin since support for Postgres connectors (and hence, CockroachDB) is limited in Swift (bummer since Swift is used to build macOS apps now).

Kotlin is gaining a lot of ground backend-wise since it transpiles to Java and JavaScript.

## Setup

1. Install [CockroachDB 20.1](https://www.cockroachlabs.com/docs/stable/install-cockroachdb-mac.html)
1. Install [Java 1.8](https://www.oracle.com/java/technologies/javase-jre8-downloads.html)
1. Install [Gradle 6.5](https://gradle.org/)
1. Install [Kotlin 1.3.72](https://kotlinlang.org/)
1. (Optional) Install JetBrains [IntelliJ IDEA](https://kotlinlang.org/docs/tutorials/getting-started.html)

## Start and prep the database

1. Get started by running the `cockroach start-single-node` command:

    ```bash
    cockroach start-single-node \
    --insecure \
    --store=kotlin-client \
    --listen-addr=localhost:26257 \
    --http-addr=localhost:8080 \
    --background
    ```

> These commands are included in the supplied Bash scripts. Execute them via: [$ bash 01-launch-cluster.sh](../01-launch-cluster.sh).
> It's recommended that you open **three** Bash terminals - one to run the Cockroach DB cluster, one to execute commands in the SQL client, and one to connect using the Kotlin sample.

You'll want to create a few resources on your instance before launching the included Kotlin client.

2. Connect to Cockroach DB through the command-line SQL client:

    ```bash
    cockroach sql --insecure
    ```

3. Now, create a user, database, and assign permissions to that user:

    ```bash
    CREATE USER IF NOT EXISTS maxroach; 
    CREATE DATABASE kotlindb;
    GRANT ALL ON DATABASE kotlindb TO maxroach;
    ```

> These commands are included in the supplied Bash scripts. Execute them via: [$ bash 02-prepare-db.sh](../02-prepare-db.sh).

4. Verify that your database displays the database `kotlindb` and user grant `maxroach`!

    Navigate to **localhost:8080** to bring up the Admin UI.

That's it! You're ready to connect to the database programmatically.

## Start the Kotlin client

> **Note:** the supplied code sample uses popular [JDBC Postgres SQL](https://jdbc.postgresql.org/) to connect Kotlin to CockroachDB.

1. Within this directory root execute:

    ```bash
    $ ./gradlew run
    ```

1. If successful, you'll see the following output in your console:

    ```plaintext
    Initializating DB at jdbc:postgresql://localhost:26257/

    Executing DROP TABLE IF EXISTS kotlindb.bugs;   
    Executing CREATE TABLE IF NOT EXISTS kotlindb.bugs (id INT PRIMARY KEY, quantity INT, timestamp VARCHAR);
    ```
    
    The `bugs` table is dropped if it exists and is then created new.

    It's designed to track software bugs or issues  and therefore has the following table schema:

    | id  | quantity  | timestamp  |
    |---|---|---|
    | INT PRIMARY KEY  | INT  | STRING  |

    ```plaintext
    Executing INSERT INTO kotlindb.bugs VALUES (1,100,'2020-06-06 14:20:01.146');
    Executing SELECT * FROM kotlindb.bugs;
    Reading kotlindb.bugs row = [id: 1 quantity: 100 timestamp: 2020-06-06 14:20:01.146]
    ```
   A single row is inserted into `bugs` indicating that `100` bugs were found `2020-06-06 14:20:01.146`.

    ```plaintext
    Executing UPDATE kotlindb.bugs SET quantity = 1, timestamp = '2020-06-06 14:20:01.164' WHERE id = 1;
    Executing SELECT * FROM kotlindb.bugs;
    Reading kotlindb.bugs row = [id: 1 quantity: 1 timestamp: 2020-06-06 14:20:01.164]
    ```
    99 bugs were resolved or fixed, so the quantity was updated to `1` a little later.

    ```plaintext
    Executing DELETE FROM kotlindb.bugs WHERE id = 1;
    Executing SELECT * FROM kotlindb.bugs;
    Reading kotlindb.bugs row = [NO RESULTS FOUND]
    ```
    The row was removed once no longer needed.

    ```plaintext
    Closing connection ...
    ```

    Lastly, the Kotlin client closed its connection to save resources and prevent memory leaks.

1. Verify the above steps were successfully executed. You should see no rows in the `bugs` table within the Admin UI.

1. Bring up the SQL command-line client and execute: `SELECT * FROM kotlindb.bugs;`. 

    You should see `0` rows remaining in the `bugs` table.