package com.bircan.ozgur.devamsizliktakip;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ozgur on 23.4.2016.
 */
public class Veritabani extends SQLiteOpenHelper {
    private  static final String VERITABANI_ISMI="veritabani";
    private  static final int VERITABANI_VERSION=1;
    private  static final String TABLO_ISMI="ders_takip_tablosu";

    private static final String ID="_id";
    private static final String DERS="ders";
    private static final String TARIH="tarih";

    public Veritabani(Context context) {
        super(context, VERITABANI_ISMI, null, VERITABANI_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String tablo_olustur="CREATE TABLE "+TABLO_ISMI+
                " ("+ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                DERS+" TEXT, "+
                TARIH+" TEXT)";
        sqLiteDatabase.execSQL(tablo_olustur);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLO_ISMI);
        onCreate(sqLiteDatabase);


    }


    public long  kayitEkle(Ogrenci ogrenci) {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
           cv.put(DERS,ogrenci.getDersAdi());
           cv.put(TARIH,ogrenci.getTarih());
        long id=db.insert(TABLO_ISMI,null,cv);
        db.close();
        return id;
    }
}
