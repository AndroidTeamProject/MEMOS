package com.cop4656.zeronul.memos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by dulybon1 on 7/8/15.
 * Database adapter
 */
public class DatabaseAdapter
{
    //the database
    private final Context context;
    private DatabaseHelper myDBHelper;
    private SQLiteDatabase db;

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "myDatabase";


    /*******************************************************
     * MANAGER TABLE INFORMATION
     */

    //Manager field numbers
    public static final int COL_MANAGER_ID = 0;
    public static final int COL_MANAGER_FIRST = 1;
    public static final int COL_MANAGER_LAST = 2;
    public static final int COL_MANAGER_EMAIL = 3;
    public static final int COL_MANAGER_DEPT = 4;

    //Manager table name
    public static final String MANAGER_TABLE = "Manager";

    //the columns for MANAGER TABLE
    public static final String MANAGER_ID_COLUMN = "managerID";
    public static final String MANAGER_FIRST_NAME = "firstName";
    public static final String MANAGER_LAST_NAME = "lastName";
    public static final String MANAGER_EMAIL = "email";
    public static final String MANAGER_DEPARTMENT = "department";

    public static final String[] ALL_MANAGER_KEYS = new String[] {MANAGER_ID_COLUMN,
            MANAGER_FIRST_NAME, MANAGER_LAST_NAME, MANAGER_EMAIL, MANAGER_DEPARTMENT};

    //SQL Statement to create manager table
    public static final String CREATE_MANAGER_TABLE = "CREATE TABLE "
            + MANAGER_TABLE
            + "(" + MANAGER_ID_COLUMN +  " INTEGER PRIMARY KEY NOT NULL,"
            + MANAGER_FIRST_NAME + " TEXT NOT NULL, "
            + MANAGER_LAST_NAME +   " TEXT NOT NULL,"
            + MANAGER_DEPARTMENT + " INTEGER NOT NULL,"
            + MANAGER_EMAIL + " TEXT NOT NULL)";

    /*******************************************************************************
     * TECHNOLOGIST TABLE INFORMATION
     */

    //Tech field numbers
    public static final int COL_TECH_ID = 0;
    public static final int COL_TECH_FIRST = 1;
    public static final int COL_TECH_LAST = 2;
    public static final int COL_TECH_EMAIL = 3;

    //Tech table name
    public static final String TECH_TABLE = "Technologist";

    //the columns for TECH_TABLE
    public static final String TECH_ID_COLUMN = "techID";
    public static final String TECH_FIRST_NAME = "firstName";
    public static final String TECH_LAST_NAME = "lastName";
    public static final String TECH_EMAIL = "email";

    public static final String[] ALL_TECH_KEYS = new String[] {TECH_ID_COLUMN,
            TECH_FIRST_NAME, TECH_LAST_NAME, TECH_EMAIL};

    //SQL Statement to create tech table
    public static final String CREATE_TECH_TABLE = "CREATE TABLE "
            + TECH_TABLE
            + "(" + TECH_ID_COLUMN +  " INTEGER PRIMARY KEY NOT NULL,"
            + TECH_FIRST_NAME + " TEXT NOT NULL, "
            + TECH_LAST_NAME +   " TEXT NOT NULL,"
            + TECH_EMAIL + " TEXT NOT NULL)";

    /************************************************************************
     * PROCEDURE TABLE INFORMATION
     */

    //Procedure field numbers
    public static final int COL_PROC_ID = 0;
    public static final int COL_PROC_NAME = 1;
    public static final int COL_PROC_INST_ID_FOREIGN = 2;
    public static final int COL_PROC_FREQUENCY = 3;

    //Procedure table name
    public static final String PROCEDURE_TABLE = "Procedure";

    //the columns for Procedure_TABLE
    public static final String PROCEDURE_ID_COLUMN = "procID";
    public static final String PROCEDURE_NAME = "name";
    public static final String PROCEDURE_INST_ID_FOREIGN = "InstrumentID";
    public static final String PROCEDURE_FREQUENCY = "frequency";

    public static final String[] ALL_PROCEDURE_KEYS = new String[] {PROCEDURE_ID_COLUMN,
            PROCEDURE_NAME, PROCEDURE_INST_ID_FOREIGN, PROCEDURE_FREQUENCY};

    //SQL Statement to create Procedure table
    public static final String CREATE_PROCEDURE_TABLE = "CREATE TABLE "
            + PROCEDURE_TABLE
            + "(" + PROCEDURE_ID_COLUMN +  " INTEGER PRIMARY KEY NOT NULL,"
            + PROCEDURE_NAME + " TEXT NOT NULL, "
            + PROCEDURE_INST_ID_FOREIGN +   " INTEGER NOT NULL,"
            + PROCEDURE_FREQUENCY + " TEXT NOT NULL)";

    /************************************************************************
     * INSTRUMENT TABLE INFORMATION
     */

    //Instrument field numbers
    public static final int COL_INST_ID = 0;
    public static final int COL_INST_MODEL_NAME = 1;

    //Instrument table name
    public static final String INSTRUMENT_TABLE = "Instrument";

    //the columns for INSTRUMENT_TABLE
    public static final String INST_ID_COLUMN = "instrumentID";
    public static final String INST_MODEL_NAME = "modelName";

    public static final String[] ALL_INST_KEYS = new String[] {INST_ID_COLUMN, INST_MODEL_NAME};

    //SQL Statement to create Instrument table
    public static final String CREATE_INST_TABLE = "CREATE TABLE "
            + INSTRUMENT_TABLE
            + "(" + INST_ID_COLUMN +  " INTEGER PRIMARY KEY NOT NULL,"
            + INST_MODEL_NAME +   " TEXT NOT NULL)";

    /************************************************************************
     * DEPARTMENT TABLE INFORMATION
     */

    //DEPARTMENT field numbers
    public static final int COL_DEPT_ID = 0;
    public static final int COL_DEPT_NAME = 1;


    //DEPARTMENT table name
    public static final String DEPARTMENT_TABLE = "Department";

    //the columns for DEPARTMENT_TABLE
    public static final String DEPT_ID_COLUMN = "DepartmentID";
    public static final String DEPT_NAME = "departmentName";

    public static final String[] ALL_DEPT_KEYS = new String[] {DEPT_ID_COLUMN,
            DEPT_NAME};

    //SQL Statement to create Instrument table
    public static final String CREATE_DEPT_TABLE = "CREATE TABLE "
            + DEPARTMENT_TABLE
            + "(" + DEPT_ID_COLUMN +  " INTEGER PRIMARY KEY NOT NULL,"
            + DEPT_NAME + " TEXT NOT NULL)";
    
    /*
                       LOG TABLE INFORMATION
     */

    //LOG field numbers
    public static final int COL_LOD_ID = 0;
    public static final int COL_LOG_INST_ID = 1;
    public static final int COL_LOG_PROCEDURE_ID = 2;
    public static final int COL_LOG_TECH_ID = 3;
    public static final int COL_LOG_DATE = 4;
    public static final int COL_LOG_SHIFT = 5;
    public static final int COL_LOG_TIME = 6;

    //the columns for LOG TABLE
    public static final String LOG_ID_COLUMN = "logID";
    public static final String LOG_INST_ID = "instID";
    public static final String LOG_PROCEDURE_ID = "procID";
    public static final String LOG_TECH_ID = "email";
    public static final String LOG_DATE = "date";
    public static final String LOG_SHIFT = "shift";
    public static final String LOG_TIME = "time";

    //LOG table name
    public static final String LOG_TABLE = "Log";

    public static final String[] ALL_LOG_KEYS = new String[] {LOG_ID_COLUMN,
            LOG_INST_ID, LOG_PROCEDURE_ID, LOG_TECH_ID, LOG_DATE,LOG_SHIFT, LOG_TIME};

    //SQL Statement to create manager table
    public static final String CREATE_LOG_TABLE = "CREATE TABLE "
            + LOG_TABLE
            + "(" + LOG_ID_COLUMN +  " INTEGER PRIMARY KEY NOT NULL,"
            + LOG_INST_ID + " TEXT NOT NULL, "
            + LOG_PROCEDURE_ID +   " TEXT NOT NULL,"
            + LOG_TECH_ID + " INTEGER NOT NULL,"
            + LOG_DATE + " TEXT NOT NULL,"
            + LOG_SHIFT + " TEXT NOT NULL,"
            + LOG_TIME + " TEXT NOT NULL)" ;

    /******************************************************************
     *
     */

    DatabaseAdapter(Context ctx)
    {
        this.context = ctx;
        myDBHelper = new DatabaseHelper(context);
    }

    // public static DatabaseAdapterInterface
    // Open the database connection.
    public DatabaseAdapter open() {
        db = myDBHelper.getWritableDatabase();
        return this;
    }

    // Close the database connection.
    public void close() {
        myDBHelper.close();
    }

    /************************************************
             MANAGER FUNCTIONS
     ************************************************/


    //Insert a row in manager's table
    public long insertRowManager(int id, String first, String last, String em, int dept)
    {
        ContentValues vals = new ContentValues();

        vals.put(MANAGER_ID_COLUMN, id);
        vals.put(MANAGER_FIRST_NAME, first);
        vals.put(MANAGER_LAST_NAME, last);
        vals.put(MANAGER_EMAIL, em);
        vals.put(MANAGER_DEPARTMENT, dept);

        return db.insert(MANAGER_TABLE, null, vals);
    }

    //TO DO ADD MANAGER METHOD THAT TAKES IN A MANAGER AND RETURNS A BOOL.

    public Cursor getRowForManagerID(int managerID)
    {
        String where = MANAGER_ID_COLUMN + "=" + managerID;
        Cursor c = db.query(true,MANAGER_TABLE,ALL_MANAGER_KEYS,where,null,null,null,null,null);

        if(c != null)
        {
            c.moveToFirst();
        }
        return c;
    }


    public Manager getManagerForID(int managerID)
    {
        String where = MANAGER_ID_COLUMN + "=" + managerID;
        Cursor c = db.query(true,MANAGER_TABLE,ALL_MANAGER_KEYS,where,null,null,null,null,null);

        if(c != null)
        {
            c.moveToFirst();
        }

        Manager manager = new Manager(c.getInt(COL_MANAGER_ID),
                c.getString(COL_MANAGER_FIRST),c.getString(COL_MANAGER_LAST),c.getString(COL_MANAGER_EMAIL),c.getInt(COL_MANAGER_DEPT));
        return manager;
    }
    //Get all the rows in the manager table
    public Cursor getAllRowsForManager()
    {
        Cursor c = db.query(true, MANAGER_TABLE, ALL_MANAGER_KEYS ,null,null,null,null,null,null);
        if(c != null)
        {
            c.moveToFirst();
        }

        return c;
    }
    /************************************************
                TECH FUNCTIONS
     ************************************************/

    //Insert new tech to tech table

    /**
     *
     * @param id: id of the new tech
     * @param first : first name
     * @param last: last name
     * @param em: email
     * @return a long indicating success
     */
    public long insertRowTech(int id, String first, String last, String em)
    {
        //to do make this function take tech instead
        ContentValues vals = new ContentValues();

        vals.put(TECH_ID_COLUMN, id);
        vals.put(TECH_FIRST_NAME, first);
        vals.put(TECH_LAST_NAME, last);
        vals.put(TECH_EMAIL, em);

        return db.insert(TECH_TABLE, null, vals);
    }

    //Get all the rows from a table(string argument)

    /**
     * Get all the rows from a table
     * @param table: table name
     * @param keys: array containing the keys of the table
     * @return cursor at the beginning of returned table
     */
    public Cursor getAllRows(String table, String keys[])
    {
        Cursor c = db.query(true, table, keys ,null,null,null,null,null,null);
        if(c != null)
        {
            c.moveToFirst();
        }

        return c;
    }

    /**
     * get row for tech id
     * @param techID
     * @return cursor pointing to the row represented by techID
     */
    public Cursor getRowForTechID(int techID)
    {
        String where = TECH_ID_COLUMN + "=" + techID;
        Cursor t = db.query(true,TECH_TABLE,ALL_TECH_KEYS,where,null,null,null,null,null);

        if(t != null)
        {
            t.moveToFirst();
        }
        return t;
    }

    public Technologist getTechForID(int techID)
    {
        String where = TECH_ID_COLUMN + "=" + techID;
        Cursor t = db.query(true,TECH_TABLE,ALL_TECH_KEYS,where,null,null,null,null,null);

        if(t != null)
        {
            t.moveToFirst();
        }

        Technologist tech = new Technologist(t.getInt(COL_TECH_ID), t.getString(COL_TECH_FIRST),
                t.getString(COL_TECH_LAST), t.getString(COL_TECH_EMAIL));
        return tech;
    }

    /**
     * Get all the rows in the tech table
     * @return cursor at the beginning of tech table
     */
    public Cursor getAllRowsForTech()
    {
        Cursor c = db.query(true, TECH_TABLE, ALL_TECH_KEYS ,null,null,null,null,null,null);
        if(c != null)
        {
            c.moveToFirst();
        }

        return c;
    }

    /************************************************
                 DEPARTMENT FUNCTIONS
     ************************************************/

    /**
     * Insert a row in the department table
     * @param id: row id
     * @param name: department name
     * @return long to confirm success
     */
    public long insertRowDepartment(int id, String name)
    {
        ContentValues vals = new ContentValues();

        vals.put(DEPT_ID_COLUMN, id);
        vals.put(DEPT_NAME, name);


        return db.insert(DEPARTMENT_TABLE, null, vals);
    }

    /**
     *
     * @param deptID
     * @return a cursor pointing to the row with id deptID
     */
    public Cursor getRowForDepatmentID(int deptID)
    {
        String where = DEPT_ID_COLUMN + "=" + deptID;
        Cursor t = db.query(true,DEPARTMENT_TABLE,ALL_DEPT_KEYS,where,null,null,null,null,null);

        if(t != null)
        {
            t.moveToFirst();
        }
        return t;
    }

    /**
     *
     * @return a cursor pointing to the first row in the department table
     */
    public Cursor getAllRowsForDept()
    {
        Cursor c = db.query(true, DEPARTMENT_TABLE, ALL_DEPT_KEYS ,null,null,null,null,null,null);
        if(c != null)
        {
            c.moveToFirst();
        }

        return c;
    }
    /************************************************
                INSTRUMENT FUNCTIONS
     ************************************************/

    /**
     * Inserts a row in the instrument table
     * @param id: instrument ID
     * @param model : Instrument model
     * @return long to confirm success
     */
    public long insertRowInstrument(int id, String model)
    {
        ContentValues vals = new ContentValues();

        vals.put(INST_ID_COLUMN, id);
        vals.put(INST_MODEL_NAME, model);

        return db.insert(INSTRUMENT_TABLE, null, vals);
    }


    /**
     * Gets a row in instrument table
     * @param InstID
     * @return a cursor that points to the row with id = InstID
     */
    public Cursor getRowForInstrumentID(int InstID)
    {
        String where = INST_ID_COLUMN + "=" + InstID;
        Cursor t = db.query(true,INSTRUMENT_TABLE,ALL_INST_KEYS,where,null,null,null,null,null);

        if(t != null)
        {
            t.moveToFirst();
        }
        return t;
    }

    //
    public Cursor getRowForInstrumentModel(String name)
    {
        String where = INST_MODEL_NAME + "=" + name;
        Cursor t = db.query(true,INSTRUMENT_TABLE,ALL_INST_KEYS,where,null,null,null,null,null);

        if(t != null)
        {
            t.moveToFirst();
        }
        return t;
    }

    /**
     * gets all the rows in the instrument table
     * @return a cursor pointing to the first row in Instrument table
     */
    public Cursor getAllRowsForInst()
    {
        Cursor c = db.query(true, INSTRUMENT_TABLE, ALL_INST_KEYS ,null,null,null,null,null,null);
        if(c != null)
        {
            c.moveToFirst();
        }

        return c;
    }
    /************************************************
                  PROCEDURE FUNCTIONS
     ************************************************/

    /**
     * Inserts a row in the procedure table
     * @param id: procedure ID
     * @param procName: name of procedure
     * @param instID: Instrument ID
     * @param freq: frequency to perform procedure
     * @return long to confirm success
     */
    public long insertRowProcedure(int id, String procName, int instID, String freq)
    {
        ContentValues vals = new ContentValues();

        vals.put(PROCEDURE_ID_COLUMN, id);
        vals.put(PROCEDURE_NAME, procName);
        vals.put(PROCEDURE_INST_ID_FOREIGN, instID);
        vals.put(PROCEDURE_FREQUENCY, freq);

        return db.insert(PROCEDURE_TABLE, null, vals);
    }

    /**
     * Gets a row in procedure table
     * @param procID: procedure ID
     * @return: a cursor that points to the row with id = procID
     */
    public Cursor getRowForProcedureID(int procID)
    {
        String where = PROCEDURE_ID_COLUMN + "=" + procID;
        Cursor t = db.query(true,PROCEDURE_TABLE,ALL_PROCEDURE_KEYS,where,null,null,null,null,null);

        if(t != null)
        {
            t.moveToFirst();
        }
        return t;
    }

    /**
     * Gets all the rows in procedure table
     * @return: a cursor that points to the first row in the procedure table
     */
    public Cursor getAllRowsForProcedure()
    {
        Cursor c = db.query(true, PROCEDURE_TABLE, ALL_PROCEDURE_KEYS ,null,null,null,null,null,null);
        if(c != null)
        {
            c.moveToFirst();
        }

        return c;
    }
    /************************************************
                     LOG FUNCTIONS
     ************************************************/

    /**
     * Inserts a row in the log table
     * @param id: log ID
     * @param instID: Instrument ID
     * @param procID: Procedure ID
     * @param techID: Technologist ID
     * @param date: Date performed
     * @param shift: Which shift
     * @param time: Time performed
     * @return: long to confirm success
     */
    public long insertRowLog(int id,int instID, int procID, int techID, String date, String shift, String time)
    {
        ContentValues vals = new ContentValues();

        vals.put(LOG_ID_COLUMN, id);
        vals.put(LOG_INST_ID, instID);
        vals.put(LOG_PROCEDURE_ID, procID);
        vals.put(LOG_TECH_ID, techID);
        vals.put(LOG_DATE, date);
        vals.put(LOG_SHIFT, shift);
        vals.put(LOG_TIME, time);

        return db.insert(LOG_TABLE, null, vals);
    }

    /**
     * Gets one row from the log table
     * @param logID: id of a log
     * @return: a cursor that points to the row where id = logID
     */
    public Cursor getRowForLogID(int logID)
    {
        String where = LOG_ID_COLUMN + "=" + logID;
        Cursor t = db.query(true,LOG_TABLE,ALL_LOG_KEYS,where,null,null,null,null,null);

        if(t != null)
        {
            t.moveToFirst();
        }
        return t;
    }

    /**
     * Gets all the rows in the log table
     * @return a cursor that points to the first row in the log table
     */
    public Cursor getAllRowsForLog()
    {
        Cursor c = db.query(true, LOG_TABLE, ALL_LOG_KEYS ,null,null,null,null,null,null);
        if(c != null)
        {
            c.moveToFirst();
        }

        return c;
    }
    /**
     * Private DatabaseHelper class
     * creates the database
     * upgrades the database
     */
    private static class DatabaseHelper extends SQLiteOpenHelper
    {
        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase _db) {
            _db.execSQL(CREATE_MANAGER_TABLE);
            _db.execSQL(CREATE_TECH_TABLE);
            _db.execSQL(CREATE_INST_TABLE);
            _db.execSQL(CREATE_DEPT_TABLE);
            _db.execSQL(CREATE_PROCEDURE_TABLE);
            _db.execSQL(CREATE_LOG_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase _db, int oldVersion, int newVersion) {
            // Destroy old database:
            _db.execSQL("DROP TABLE IF EXISTS " + MANAGER_TABLE);
            _db.execSQL("DROP TABLE IF EXISTS " + TECH_TABLE);
            _db.execSQL("DROP TABLE IF EXISTS " + INSTRUMENT_TABLE);
            _db.execSQL("DROP TABLE IF EXISTS " + DEPARTMENT_TABLE);
            _db.execSQL("DROP TABLE IF EXISTS " + PROCEDURE_TABLE);
            _db.execSQL("DROP TABLE IF EXISTS " + LOG_TABLE);

            // Recreate new database:
            onCreate(_db);
        }

    }

    /**
     * QUICK TESTING FUNCTIONS
     * clearManagerTable(); DONE
     * clearTechnologistTable(); DONE
     * clearProcedureTable();
     * clearInstrumentTable();
     * clearDepartmentTable();
     * clearLogTable();
     * clearTable(); DONE
     */


    /**
     * delete one specific row in a table
     * @param table
     * @param idColumn
     * @param id
     * @return boolean that indicate if delete was successful or not
     */
    public boolean deleteRow(String table, String idColumn, int id)
    {
        idColumn += " = " + id;
        return db.delete(table,idColumn,null) != 0;
    }

    //Generic version of clearTable

    /**
     * This function clears the specified table
     * @param table: the name of the table
     * @param keys: a list of the keys in that table
     */
    public void clearTable(String table, String keys[])
    {
        Cursor c = getAllRows(table, keys);
        int id = c.getColumnIndexOrThrow(keys[0]);
        if(c.moveToFirst())
        {
            do
            {
                deleteRow(table,keys[0], c.getInt(id));
            }while (c.moveToNext());
        }
        c.close();
    }



    /**
     * This function clears the manager table
     */
    public void clearManagerTable()
    {
        Cursor manager_row = getAllRowsForManager();
        int rowID = manager_row.getColumnIndexOrThrow(MANAGER_ID_COLUMN);
        if(manager_row.moveToFirst())
        {
            do {
                deleteRow(MANAGER_TABLE, MANAGER_ID_COLUMN, manager_row.getInt(rowID));
            } while (manager_row.moveToNext());
        }
        manager_row.close();
    }

    /**
     * This function clears the procedure table
     */
    public void clearProcedureTable()
    {
        Cursor procedure_row = getAllRowsForProcedure();
        int rowID = procedure_row.getColumnIndexOrThrow(PROCEDURE_ID_COLUMN);
        if(procedure_row.moveToFirst())
        {
            do {
                deleteRow(PROCEDURE_TABLE, PROCEDURE_ID_COLUMN, procedure_row.getInt(rowID));
            } while (procedure_row.moveToNext());
        }
        procedure_row.close();
    }

    /**
     * This function clears the department table
     */
    public void clearDepartmentTable()
    {
        Cursor department_row = getAllRowsForDept();
        int rowID = department_row.getColumnIndexOrThrow(DEPARTMENT_TABLE);
        if(department_row.moveToFirst())
        {
            do {
                deleteRow(DEPARTMENT_TABLE, DEPT_ID_COLUMN, department_row.getInt(rowID));
            } while (department_row.moveToNext());
        }
        department_row.close();
    }

    /**
     * this function clears the technologist table
     */
    public void clearTechnologistTable()
    {
        Cursor tech_row = getAllRowsForTech();
        int rowID = tech_row.getColumnIndexOrThrow(TECH_ID_COLUMN);
        if(tech_row.moveToFirst())
        {
            do {
                deleteRow(TECH_TABLE, TECH_ID_COLUMN, tech_row.getInt(rowID));
            } while (tech_row.moveToNext());
        }
        tech_row.close();

    }

    /**
     * this function clears the instrument table
     */
    public void clearInstrumentTable()
    {
        Cursor instrument_row = getAllRowsForTech();
        int rowID = instrument_row.getColumnIndexOrThrow(INST_ID_COLUMN);
        if(instrument_row.moveToFirst())
        {
            do {
                deleteRow(INSTRUMENT_TABLE, INST_ID_COLUMN, instrument_row.getInt(rowID));
            } while (instrument_row.moveToNext());
        }
        instrument_row.close();

    }

    /**
     * this function clears the log table
     */
    public void clearLogTable()
    {
        Cursor log_row = getAllRowsForLog();
        int rowID = log_row.getColumnIndexOrThrow(LOG_ID_COLUMN);
        if(log_row.moveToFirst())
        {
            do {
                deleteRow(LOG_TABLE, LOG_ID_COLUMN, log_row.getInt(rowID));
            } while (log_row.moveToNext());
        }
        log_row.close();

    }

    public boolean addManager(Manager manager)
    {

       return insertRowManager(manager.getEmployeeID(), manager.getFirstName(), manager.getLastName(),
               manager.getEmail(), manager.getDepartmentNumber()) > 0;

    }
    public boolean addTech(Technologist tech)
    {

        return insertRowTech(tech.getEmployeeID(), tech.getFirstName(),
                tech.getLastName(), tech.getEmail()) > 0;
    }
    public Manager getManagerById(int managerId)
    {
        return getManagerForID(managerId);
    }
    public Technologist getTechById(int techId)
    {
        return getTechForID(techId);
    }

    public boolean addInstrument(Instrument instrument)
    {
        return insertRowInstrument(instrument.getInstrumentID(),
                instrument.getModel()) > 0;
    }
    public Instrument getInstrumentById(int instrumentId)
    {
        //to do
        Cursor  i =  getRowForInstrumentID(instrumentId);
        Instrument instrument = new Instrument(i.getInt(COL_INST_ID),i.getString(COL_INST_MODEL_NAME));
        return instrument;
    }

    public boolean addProcedure(Procedure proc)
    {
        return insertRowProcedure(proc.getProcedureID(), proc.getProcedureName(),proc.getInstrumentID(), proc.getFrequency()) > 0;
    }

    public Procedure getProcedureByID(int procedureID)
    {
        Cursor p = getRowForProcedureID(procedureID);
        Procedure proc = new Procedure(p.getInt(COL_PROC_ID), p.getString(COL_PROC_NAME),
                p.getInt(COL_PROC_INST_ID_FOREIGN), p.getString(COL_PROC_FREQUENCY));
        return proc;
    }

}
