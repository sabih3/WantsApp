package wantapp.netaq.com.wantapp.models;

import android.support.v4.app.Fragment;

/**
 * Created by Sabih on 17-Sep-17.
 */

public class FragmentContainer {

    private Fragment fragment;
    private String fragmentTitle;

    public FragmentContainer(Fragment fragment, String fragmentTitle) {
        this.fragment = fragment;
        this.fragmentTitle = fragmentTitle;
    }

    public Fragment getFragment() {
        return fragment;
    }

    public void setFragment(Fragment fragment) {
        this.fragment = fragment;
    }

    public String getFragmentTitle() {
        return fragmentTitle;
    }

    public void setFragmentTitle(String fragmentTitle) {
        this.fragmentTitle = fragmentTitle;
    }
}
