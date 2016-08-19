package com.is.health.sugar;

import android.content.Context;

import com.orm.SugarDb;

/**
 * Created by George on 2016/5/21.
 */
public class SugarDBHelper {

    private static volatile SugarDBHelper instance;

    private SugarDBHelper() {

    }

    public  static SugarDBHelper getInstance(){
        if (null == instance) {
            synchronized (SugarDBHelper.class) {
                if (null == instance) {
                    instance = new SugarDBHelper();
                }
            }
        }
        return instance;
    }
    public void initDB(Context context){
        SugarDb sugarDb=new SugarDb(context);
        SchemaGeneratorUtil schemaGeneratorUtil=new SchemaGeneratorUtil(context);
       // sugarDb.getDB().execSQL(schemaGeneratorUtil.createTableSQL(BizAreas.class));
        sugarDb.close();
    }

}
