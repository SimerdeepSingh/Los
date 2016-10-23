package los.valiance.com.los.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.LinkedHashMap;
import java.util.Map;

import static los.valiance.com.los.Helper.Constants.cityTable;
import static los.valiance.com.los.Helper.Constants.leadTypeTable;
import static los.valiance.com.los.Helper.Constants.loanPurposeTable;
import static los.valiance.com.los.Helper.Constants.loanTypeTable;
import static los.valiance.com.los.Helper.Constants.relationshipTable;
import static los.valiance.com.los.Helper.Constants.salesOfficerTable;
import static los.valiance.com.los.Helper.Constants.sourceTable;
import static los.valiance.com.los.Helper.Constants.stateTable;
import static los.valiance.com.los.Helper.Constants.statusTable;
import static los.valiance.com.los.Helper.Constants.teamManagerTable;
import static los.valiance.com.los.Helper.Constants.titleTable;

/**
 * Created by admin2 on 21-10-2016.
 */

public class LocalDatabase extends SQLiteOpenHelper {
    LinkedHashMap<Integer,String>dropdownDataList;
    public LocalDatabase(Context context) {
        super(context, "Los", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String leadStatusList = "create table "+statusTable+"(Id Int ,Name String)";
        String leadTitleList = "create table "+titleTable+"(Id Int ,Name String)";
        String StateList = "create table "+stateTable+"(Id Int ,Name String)";
        String CityList = "create table CityList"+cityTable+"(Id Int ,Name String)";
        String SourceList = "create table "+sourceTable+"(Id Int ,Name String)";
        String SalesOfficerList="create table "+salesOfficerTable+"(Id Int ,Name String)";
        String TeamManagerList="create table "+teamManagerTable+"(Id Int ,Name String)";
        String LoanTypeList="create table "+loanTypeTable+"(Id Int ,Name String)";
        String LoanPurposeList="create table "+loanPurposeTable+"(Id Int ,Name String)";
        String TypeList="create table "+leadTypeTable+"(Id Int ,Name String)";
        String RelationshipList="create table "+relationshipTable+"(Id Int ,Name String)";
       // String LeadDetails=""

        db.execSQL(leadStatusList);
        db.execSQL(leadTitleList);
        db.execSQL(StateList);
        db.execSQL(CityList);
        db.execSQL(SourceList);
        db.execSQL(SalesOfficerList);
        db.execSQL(TeamManagerList);
        db.execSQL(LoanTypeList);
        db.execSQL(LoanPurposeList);
        db.execSQL(TypeList);
        db.execSQL(RelationshipList);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addDropDownData(String tableName,LinkedHashMap<Integer, String> dropdownData)
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        for(Map.Entry<Integer, String> data : dropdownData.entrySet())
        {
            ContentValues values = new ContentValues();
            values.put("Id", data.getKey());
            values.put("Name", data.getValue());
            sqLiteDatabase.insert(tableName,null,values);
        }
    }

    public LinkedHashMap<Integer, String> getDropDownData(String tableName)
    {
        dropdownDataList=new LinkedHashMap<>();
        String fetchdata = "select * from "+tableName;
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(fetchdata, null);

        if (cursor.moveToFirst()) {
            do {
                dropdownDataList.put(cursor.getInt(0),cursor.getString(1));
            } while (cursor.moveToNext());
        }
        sqLiteDatabase.close();
        return dropdownDataList;
    }


}
