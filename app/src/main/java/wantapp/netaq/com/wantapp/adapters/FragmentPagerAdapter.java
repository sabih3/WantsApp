package wantapp.netaq.com.wantapp.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

import wantapp.netaq.com.wantapp.models.FragmentContainer;

/**
 * Created by Sabih Ahmed on 17-Sep-17.
 */

public class FragmentPagerAdapter extends FragmentStatePagerAdapter{

    private ArrayList<FragmentContainer> mDataset;

    public FragmentPagerAdapter(FragmentManager fm, ArrayList<FragmentContainer> fragmentList) {
        super(fm);
        this.mDataset = fragmentList;

    }

    @Override
    public Fragment getItem(int position) {
        return mDataset.get(position).getFragment();
    }

    @Override
    public int getCount() {
        return mDataset.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mDataset.get(position).getFragmentTitle();
    }


}
