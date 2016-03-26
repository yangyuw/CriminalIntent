package andy.com.criminalintent;

import java.util.Date;
import java.util.UUID;

/**
 * Created by andy on 2016/3/22.
 */
public class Crime {
    private UUID mId;
    private String mTitle;
    private Date mDate;
    private boolean mSolved;

    public UUID getmId() {
        return mId;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public Date getmDate() {
        return mDate;
    }

    public void setmDate(Date mDate) {
        this.mDate = mDate;
    }

    public boolean ismSolved() {
        return mSolved;
    }

    public void setmSolved(boolean mSolved) {
        this.mSolved = mSolved;
    }

    public Crime(){

        //生成唯一 标识符
        mId = UUID.randomUUID();
        mDate = new Date();

    }

    //默认的 ArrayAdapter<T>.getView(...) 实现方法依赖于 toString() 方法。它首先生成布
    //局视图， 然后找到指定位置的 Crime 对象并对其调用 toString() 方法， 最后得到字符串信息并传
    //递给 TextView 。
    @Override
    public String toString() {
        return mTitle;
    }
}
