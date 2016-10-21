package los.valiance.com.los.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by admin2 on 21-10-2016.
 */

public class LocalDatabase extends SQLiteOpenHelper {
    public LocalDatabase(Context context) {
        super(context, "Los", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String leadStatusList = "create table leadStatusList(Id Int ,Name String)";
        String leadTitleList = "create table leadTitleList(Id Int ,Name String)";
        String StateList = "create table StateList(Id Int ,Name String)";
        String CityList = "create table CityList(Id Int ,Name String)";
        String SourceList = "create table SourceList(Id Int ,Name String)";
        String SalesOfficerList="create table SalesOfficerList(Id Int ,Name String)";
        String TeamManagerList="create table TeamManagerList(Id Int ,Name String)";
        String LoanTypeList="create table LoanTypeList(Id Int ,Name String)";
        String LoanPurposeList="create table LoanPurposeList(Id Int ,Name String)";
        String TypeList="create table TypeList(Id Int ,Name String)";
        String RelationshipList="create table RelationshipList(Id Int ,Name String)";
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
}
