# Create and Perform Operations Against a Table In Kotlin

This brief tutorial walks through **(A) setting up and starting Cockroach DB** then **(B) how to connect to Cockroach DB using Kotlin**.

Specifically, you'll learn how to

1. Download and install Cockroach DB.
1. Launch a single-node Cockroach DB cluster.
1. Connect to the single-node cluster using Kotlin.
1. Create a user, database, table, and permissions through the SQL client.

And, then how to accomplish the following using Kotlin:

5. Create table data through an INSERT statement data.
1. Read data from the table through SELECT statements.
1. Update data through an UPDATE statement.
1. Delete data through a DELETE statement.

## Step 1. Download and configure prerequisites

1. Install [CockroachDB 20.1](https://www.cockroachlabs.com/docs/stable/install-cockroachdb-mac.html)
1. Install [Java 1.8](https://www.oracle.com/java/technologies/javase-jre8-downloads.html)
1. Install [Gradle 6.5](https://gradle.org/)
1. Install [Kotlin 1.3.72](https://kotlinlang.org/)
1. (Optional) Install JetBrains [IntelliJ IDEA](https://kotlinlang.org/docs/tutorials/getting-started.html)

> For additional help getting set up, consult the [documentation](https://www.cockroachlabs.com/docs/stable/).


## Step 2. Spin up a single-node cluster

Next, you'll learn how to easily launch a Cockroach DB cluster. 

For the purposes of this tutorial, you'll only need one and it can be launched in `insecure mode`.

1. Get started by running the `cockroach start-single-node` command:

    ```bash
    cockroach start-single-node \
    --insecure \
    --store=kotlin-client \
    --listen-addr=localhost:26257 \
    --http-addr=localhost:8080 \
    --background
    ```

> These commands are included in the supplied Bash scripts. Execute them via: [$ bash 01-launch-cluster.sh](./01-launch-cluster.sh).
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

> These commands are included in the supplied Bash scripts. Execute them via: [$ bash 02-prepare-db.sh](./02-prepare-db.sh).

4. Verify that your database displays the database `kotlindb` and user grant `maxroach`!

    Navigate to **localhost:8080** to bring up the Admin UI.

That's it! You're ready to connect to the database programmatically.

## Step 3. Connect through the supplied Kotlin client

Most web applications will need to connect to a database or database cluster programmatically using powerful programming languages like Kotlin or Java.

Here, you'll learn how easy it is to do so!

> **Note:** the supplied code sample uses popular [JDBC Postgres SQL](https://jdbc.postgresql.org/) to connect Kotlin to CockroachDB.

1. Navigate to the [cockroachdb-kotlin-client](./cockroachdb-kotlin-client) root and execute:

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
    The row was removed once it was no longer needed.

    ```plaintext
    Closing connection ...
    ```

    Lastly, the Kotlin client closed its connection to save resources and prevent memory leaks.

1. Verify the above steps were successfully executed. You should see no rows in the `bugs` table within the Admin UI.

1. Bring up the SQL command-line client and execute: `SELECT * FROM kotlindb.bugs;`. 

    You should see `0` rows remaining in the `bugs` table.

1. Verify that the Kotlin queries are correctly modifying the `bugs` table. In [Main.kt](./cockroachdb-kotlin-client/src/main/kotlin/com/cockroachlabs/client/Main.kt) comment out **line 36**:   

    ```kotlin
    // Queries
    InsertRow.execute(conn)
    ReadRow.execute(conn)
    UpdateRow.execute(conn)
    ReadRow.execute(conn)
    //DeleteRow.execute(conn)
    ReadRow.execute(conn)
    ```

1. Rerun the Kotlin sample by calling `$ ./gradlew run`. You should now see a single row in you console and in the Admin UI.

    | id  | quantity  | timestamp  |
    |---|---|---|
    | 1  | 1  | 2020-06-0... |

1. Further verify that the Kotlin INSERT and UPDATE queries were successful by executing: `SELECT * FROM kotlindb.bugs;` from the command-line. 

    You should see `1` rows remaining in the `bugs` table.

## Cleanup tutorial resources

Feel free to continue exploring how to interact with your Cockroach DB cluster using the supplied Kotlin sample. The JDBC code queries can be viewed within the [queries](./cockroachdb-kotlin-client/src/main/kotlin/com/cockroachlabs/client/queries) directory.

When you're ready, execute the following commands to shut down and remove your tutorial resources:

1. Close the SQL client terminal.
1. Close the Kotlin client terminal.
1. Execute `cockroach quit --insecure` in the remaining terminal.
1. Verify that the database cluster has shut down by visiting **localhost:8080**. If you don't see the Admin UI, your cluster has successfully `quit`.
1. All files used by the tutorial resources should be visible within the tutorial root directory. Delete all files within the tutorial root directory to completely remove all tutorial resources.
1. To start with a fresh instance, remove ***only*** the `kotlin-client` folder which is created by Cockroach DB to store data instead.

## Conclusion

This brief tutorial described:

1. How to set up and start a Cockroach DB cluster
1. How to add tables, users, and permissions.
2. How to connect to Cockroach DB using Kotlin

Along the way you learned about how to use JDBC Postgres SQL queries within Kotlin to modify your database.

## What's next?

Check out:

1. The [Cockroach DB](https://www.cockroachlabs.com/docs/stable/) reference documentation
1. [Kotlin](https://kotlinlang.org/)
1. [Gradle](https://gradle.org/)
1. The [tutorial code sample](https://github.com/Thoughtscript/cockroachdb-kotlin-client)
