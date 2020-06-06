package com.cockroachlabs.client

import org.postgresql.ds.PGSimpleDataSource
import java.sql.Connection
import com.cockroachlabs.client.queries.*

fun main() {

    var conn: Connection
    val URL = "jdbc:postgresql://localhost:26257/"
    println("Initializating DB at " + URL + "\n")

    try {

        // Cockroach DB settings
        var source = PGSimpleDataSource()
        source.databaseName = "kotlindb"
        source.setURL(URL)
        source.user = "maxroach"
        source.password = null
        source.applicationName = "kotlin-client"

        // Get a SQL connection
        conn = source.connection
        conn.autoCommit = false

        // Prep
        DropTable.execute(conn)
        CreateTable.execute(conn)

        // Queries
        InsertRow.execute(conn)
        ReadRow.execute(conn)
        UpdateRow.execute(conn)
        ReadRow.execute(conn)
        DeleteRow.execute(conn)
        ReadRow.execute(conn)

        if (conn != null) {
            println("Closing connection ...")
            conn.close()
        }

    } catch (e: Exception) {

        println("Exception encountered! " + e)

    }
}