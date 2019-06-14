package id.dana.data.persistence;

import android.content.Context;

import androidx.room.Room;

/**
 * @author Abraham Ginting (abraham.ginting@dana.id)
 * @version BasePersistence.java, v 0.1 12/04/19 16.26 by Abraham Ginting
 */
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
