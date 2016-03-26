package andy.com.criminalintent;

import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by andy on 2016/3/22.
 */
public class CrimeListFragment extends ListFragment {

    private static final String TAG = "CrimeListFragment";

    private ArrayList<Crime> mCrimes;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        getActivity().setTitle(R.string.crime_title);
        mCrimes = CrimeLab.get(getActivity()).getCrimes();

        //ArrayAdapter<Crime> adapter = new ArrayAdapter<Crime>(getActivity(), android.R.layout.simple_list_item_1, mCrimes);
        //现在可以在 CrimeListFragment 中绑定定制adapter了
        CrimeAdapter adapter = new CrimeAdapter(mCrimes);
        setListAdapter(adapter);
    }

    //使adapter返回被点击的列表项所对应的 Crime 对象，然后日志记录 Crime 对象的标题.使用 onListItemClick(...) 方法的 position 参数调用adapter的
    //getItem(int) 方法，最后把结果转换成 Crime 对象
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        //Crime c = (Crime)(getListAdapter()).getItem(position);
        //既然已转换为 CrimeAdapter 类，自然也获得了类型检查的能力。 CrimeAdapter 只能容纳Crime 对象，因此 Crime 类的强制类型转换也就不需要了
        Crime c = ((CrimeAdapter)getListAdapter()).getItem(position);
        //Log.d(TAG, c.getmTitle() + "was clicked");
        //打开crimeActivity
        Intent i = new Intent(getActivity(), CrimeActivity.class);
        i.putExtra(CrimeFragment.EXTRA_CRIME_ID, c.getmId());
        startActivity(i);
    }

    //添加定制的adapter内部类
    private class CrimeAdapter extends ArrayAdapter<Crime> {

        public CrimeAdapter(ArrayList<Crime> crimes) {
            super(getActivity(), 0, crimes);
        }

        //在 getView(...) 实现方法里，首先检查传入的视图对象是否是复用对象。如不是，则从定制布局里产生一个新的视图对象
        @Override
        public View getView(int positon, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getActivity().getLayoutInflater().inflate(R.layout.list_item_crime, null);
            }

            //无论是新对象还是复用对象，都应调用 Adapter 的 getItem(int) 方法获取列表中当前position 的 Crime 对象
            //获取 Crime 对象后，引用视图对象中的各个组件，并以 Crime 的数据信息对应配置视图对象。最后，把视图对象返回给 ListView
            //config the view for the crime
            Crime c = getItem(positon);
            TextView titleTextView = (TextView)convertView.findViewById(R.id.crime_list_item_titleTextView);
            titleTextView.setText(c.getmTitle());
            TextView dateTextView  = (TextView)convertView.findViewById(R.id.crime_list_item_dateTextView);
            dateTextView.setText(c.getmDate().toString());
            CheckBox solvedCheckBox = (CheckBox)convertView.findViewById(R.id.crime_list_item_solvedCheckBox);
            solvedCheckBox.setChecked(c.ismSolved());

            return convertView;
        }
    }
}
