package com.cockroachlabs.client.queries

import java.sql.Connection
import java.sql.Timestamp

class InsertRow {

    companion object {
        @JvmStatic
        fun execute(conn: Connection) {
            val currentTime = Timestamp(System.currentTimeMillis())
            val query = "INSERT INTO kotlindb.bugs VALUES (1,100,'" + currentTime.toString() + "');"
            println("Executing " + query)
            val insertStatement = conn.prepareStatement(query)
            insertStatement.executeUpdate()
            conn.commit()
        }
    }

}