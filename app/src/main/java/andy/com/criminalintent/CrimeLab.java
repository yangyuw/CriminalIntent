package andy.com.criminalintent;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by andy on 2016/3/22.
 */
public class CrimeLab {//创建单例
    private static CrimeLab sCrimeLab;
    private Context mAppContext;
    private ArrayList<Crime> mCrimes;

    private static final String TAG = "CrimeLab";
    private static final String FILENAME = "crime.json";
    private CriminalIntentJSONSerializer mSerializer;

    public boolean saveCrimes() {
        try {
            mSerializer.saveCrimes(mCrimes);
            Log.d(TAG, "保存成功！！");
            return true;
        } catch (Exception e) {
            Log.e(TAG, "保存失败！！！:", e);
            return false;
        }
    }

    private CrimeLab (Context appContext){//私有的构造方法
        mAppContext = appContext;
        mSerializer = new CriminalIntentJSONSerializer(mAppContext, FILENAME);

        try {
            mCrimes = mSerializer.loadCrimes();
        } catch (Exception e) {
            mCrimes = new ArrayList<>();
            Log.e(TAG, "ERROR loading Crimes:", e);
        }
    }

    public static CrimeLab get (Context c){
        if (sCrimeLab == null){
            sCrimeLab = new CrimeLab(c.getApplicationContext());
        }
        return sCrimeLab;
    }

    public ArrayList<Crime> getCrimes(){
        return mCrimes;
    }

    public Crime getCrime(UUID id){
        for (Crime c : mCrimes){
            if (c.getmId().equals(id)){
                return c;
            }
        }
        return null;
    }

    public void addCrime(Crime c) {
        mCrimes.add(c);
    }
}
