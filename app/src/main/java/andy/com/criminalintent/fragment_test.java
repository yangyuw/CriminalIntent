package andy.com.criminalintent;

import android.app.Fragment;

/**
 * Created by andy on 2016/3/26.
 */
public class fragment_test extends  SingleFragmentActivity {

    @Override
    protected Fragment createFragment(){
        return new CrimeListFragment();
    }
}
