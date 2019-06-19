package id.dana.data.persistence;

import android.content.Context;

import androidx.room.Room;

public class BasePersistence {

    private BasePersistenceDao basePersistenceDao;

    private Context context;

    public BasePersistence(Context context) {
        this.context = context;
    }

    public BasePersistenceDao getDatabase() {
        if (basePersistenceDao == null) {
            init();
        }
        return basePersistenceDao;
    }

    private void init() {
        basePersistenceDao = Room
            .databaseBuilder(context, BasePersistenceDao.class, DBConstant.DB_NAME)
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build();
    }
}
