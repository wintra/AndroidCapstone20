package com.example.moccaCanary.menu.data;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CwDataAdapter {

    protected static final String TAG = "CwDataAdapter";
    private static String DB_NAME_CW = "data_all.db";
    // TODO : TABLE 이름을 명시해야함
    protected static final String TABLE_NAME = "cw_table";

    private final Context mContext;
    private SQLiteDatabase mDb;
    private DataBaseHelper mDbHelper;

    public CwDataAdapter(Context context)
    {
        this.mContext = context;
        mDbHelper = new DataBaseHelper(mContext, DB_NAME_CW, 1);
    }

    public CwDataAdapter createDatabase() throws SQLException
    {
        try
        {
            mDbHelper.createDataBase();
        }
        catch (IOException mIOException)
        {
            Log.e(TAG, mIOException.toString() + "  UnableToCreateDatabase");
            throw new Error("UnableToCreateDatabase");
        }
        return this;
    }

    public CwDataAdapter open() throws SQLException
    {
        try
        {
            mDbHelper.openDataBase(DB_NAME_CW);
            mDbHelper.close();
            mDb = mDbHelper.getReadableDatabase();
        }
        catch (SQLException mSQLException)
        {
            Log.e(TAG, "open >>"+ mSQLException.toString());
            throw mSQLException;
        }
        return this;
    }

    public void close()
    {
        mDbHelper.close();
    }

    public List getTableData()
    {
        try
        {
            String sql = "SELECT * FROM " + TABLE_NAME;

            // 모델 넣을 리스트 생성
            List placeList = new ArrayList();

            // TODO : 모델 선언
            CwData cwdata = null;

            Cursor mCur = mDb.rawQuery(sql, null);
            if (mCur!=null) {
                // 칼럼의 마지막까지
                while (mCur.moveToNext()) {

                    // TODO : 커스텀 모델 생성
                    cwdata = new CwData();

                    // TODO : Record 기술
                    // id, name, account, privateKey, secretKey, Comment


                    cwdata.setCnctrLghtFcltyYn(mCur.getInt(0));
                    cwdata.setBrllBlckYn(mCur.getInt(1));
                    cwdata.setTfcilndYn(mCur.getInt(2));
                    cwdata.setSondSgngnrYn(mCur.getInt(3));
                    cwdata.setTfclghtYn(mCur.getInt(4));
                    cwdata.setEt(mCur.getInt(5));
                    cwdata.setBt(mCur.getInt(6));
                    cwdata.setCartrkCo(mCur.getInt(7));
                    cwdata.setLatitude(mCur.getFloat(8));
                    cwdata.setLongitude(mCur.getFloat(9));
                    cwdata.setBcyclCrslkCmbnatYn(mCur.getInt(10));
                    cwdata.setCrslkKnd(mCur.getInt(11));
                    cwdata.setLnmadr(mCur.getString(12));
                    cwdata.setRoadNm(mCur.getString(13));
                    cwdata.setCtprvnNm(mCur.getInt(14));
                    cwdata.setIndex(mCur.getInt(15));

                    // 리스트에 넣기
                    placeList.add(cwdata);
                }
            }
            return placeList;
        }
        catch (SQLException mSQLException)
        {
            Log.e(TAG, "getTestData >>"+ mSQLException.toString());
            throw mSQLException;
        }
    }
}
