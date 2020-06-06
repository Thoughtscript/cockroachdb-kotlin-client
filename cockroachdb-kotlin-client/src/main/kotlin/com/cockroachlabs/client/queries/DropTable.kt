package com.cockroachlabs.client.queries

import java.sql.Connection

class DropTable {

    companion object {
        @JvmStatic
        fun execute(conn: Connection) {
            val query = "DROP TABLE IF EXISTS kotlindb.bugs;"
            println("Executing " + query)
            val dropTableStatement = conn.prepareStatement(query);
            dropTableStatement.execute()
            conn.commit()
        }
    }

}