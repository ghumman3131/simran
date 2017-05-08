package com.example.simran.simran;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by ghumman on 4/30/2017.
 */

public class Profile extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.profile_layout , container , false);

        TabLayout tabLayout = (TabLayout) v.findViewById(R.id.tab_layout2);
        ViewPager viewPager = (ViewPager) v.findViewById(R.id.pager2);

        viewPager.setAdapter(new SectionPagerAdapter2(getFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);
        return v ;
    }

    private class SectionPagerAdapter2 extends FragmentPagerAdapter {

        public SectionPagerAdapter2(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new Basic_info_frag();
                case 1:
                    return new Friends_frag();

                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "BASIC INFO";
                case 1:
                    return "FRIENDS";

                default:
                    return null;
            }
        }
    }
}
