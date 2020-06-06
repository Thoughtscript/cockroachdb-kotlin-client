package com.cockroachlabs.client.queries

import java.sql.Connection

class DeleteRow {

    companion object {
        @JvmStatic
        fun execute(conn: Connection) {
            val query = "DELETE FROM kotlindb.bugs WHERE id = 1;"
            println("Executing " + query)
            val deleteStatement = conn.prepareStatement(query)
            deleteStatement.execute()
            conn.commit()
        }
    }

}