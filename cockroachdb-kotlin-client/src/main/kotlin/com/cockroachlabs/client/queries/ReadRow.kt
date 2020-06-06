package com.cockroachlabs.client.queries

import java.sql.Connection

class ReadRow {

    companion object {
        @JvmStatic
        fun execute(conn: Connection) {
            val query = "SELECT * FROM kotlindb.bugs;"
            println("Executing " + query)
            val selectStatement = conn.prepareStatement(query)
            val resultSet = selectStatement.executeQuery()
            var isEmpty = true
            while (resultSet.next()) {
                isEmpty = false
                val msg = "Reading kotlindb.bugs row = [id: " + resultSet.getInt(1).toString() + " quantity: " + resultSet.getInt(2).toString() + " timestamp: " + resultSet.getString(3) + "]\n"
                println(msg);
            }
            if (isEmpty) {
                println("Reading kotlindb.bugs row = [NO RESULTS FOUND]" + "\n")
            }
        }
    }

}