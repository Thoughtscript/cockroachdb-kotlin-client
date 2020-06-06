package com.cockroachlabs.client.queries

import java.sql.Connection
import java.sql.Timestamp

class UpdateRow {

    companion object {
        @JvmStatic
        fun execute(conn: Connection) {
            val currentTime = Timestamp(System.currentTimeMillis())
            val query = "UPDATE kotlindb.bugs SET quantity = 1, timestamp = '" + currentTime.toString() + "' WHERE id = 1;"
            println("Executing " + query)
            val updateStatement = conn.prepareStatement(query)
            updateStatement.executeUpdate()
            conn.commit()
        }
    }

}