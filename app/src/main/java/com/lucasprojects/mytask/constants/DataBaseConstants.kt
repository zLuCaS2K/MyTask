package com.lucasprojects.mytask.constants

class DataBaseConstants {

    object USER {
        val TABLE_NAME = "user"

        object COLUMNS {
            val ID = "_id"
            val NAME = "name"
            val EMAIL = "email"
            val PASSWORD = "password"
        }

    }

    object PRIORITY {
        val TABLE_NAME = "priority"

        object COLUMNS {
            val ID = "_id"
            val DESCRIPTION = "description"
        }

    }

    object TASK {
        val TABLE_NAME = "task"

        object COLUMNS {
            val ID = "_id"
            val USERID = "userid"
            val DESCRIPTION = "description"
            val DUEDATE = "duedate"
            val PRIORITYID = "priorityid"
            val COMPLETE = "complete"
        }

    }

}