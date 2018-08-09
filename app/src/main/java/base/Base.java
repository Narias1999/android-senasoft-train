package base;

import android.app.Application;

import org.greenrobot.greendao.database.Database;

import db.DaoMaster;
import db.DaoSession;

public class Base extends Application {
    DaoSession daoSession;
    @Override
    public void onCreate(){
        super.onCreate();
        Database database = new DaoMaster.DevOpenHelper(this,"base2" ).getWritableDb();
        daoSession = new DaoMaster(database).newSession();
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }
}
