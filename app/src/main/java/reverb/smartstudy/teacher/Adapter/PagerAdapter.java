package reverb.smartstudy.teacher.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import reverb.smartstudy.teacher.Fragment.FirstFragment;
import reverb.smartstudy.teacher.Fragment.SecondFragment;
import reverb.smartstudy.teacher.Fragment.ThirdFragment;

/**
 * Created by mdjahirulislam on 21/10/17.
 */

public class PagerAdapter extends FragmentStatePagerAdapter{

    int tabCount;
    public PagerAdapter(FragmentManager fm, int tabCount) {
        super(fm);
        this.tabCount = tabCount;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                FirstFragment tab1 = new FirstFragment();
                return tab1;
            case 1:
                SecondFragment tab2 = new SecondFragment();
                return tab2;
            case 2:
                ThirdFragment tab3 = new ThirdFragment();
                return tab3;
            default:
                return null;
        }
//        return null;
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
