package andy.com.criminalintent;

import android.app.Fragment;

/**
 * Created by andy on 2016/3/22.
 */
public class CrimeListActivity extends  SingleFragmentActivity {

    @Override
    protected Fragment createFragment(){
        return new CrimeListFragment();
    }
}
