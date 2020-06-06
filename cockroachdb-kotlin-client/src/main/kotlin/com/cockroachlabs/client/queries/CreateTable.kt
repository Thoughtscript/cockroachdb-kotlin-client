package com.cockroachlabs.client.queries

import java.sql.Connection

class CreateTable {

    companion object {
        @JvmStatic
        fun execute(conn: Connection) {
            val query = "CREATE TABLE IF NOT EXISTS kotlindb.bugs (id INT PRIMARY KEY, quantity INT, timestamp VARCHAR);"
            println("Executing " + query + "\n")
            val createTableStatement = conn.prepareStatement(query)
            createTableStatement.execute()
            conn.commit()
        }
    }

}