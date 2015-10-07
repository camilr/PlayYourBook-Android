package movil.camilr.playyourbooks.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

import movil.camilr.playyourbooks.PagerTitle;

/**
 * Created by Cami on 6/10/2015.
 */
public class PagerFrAdapter extends FragmentStatePagerAdapter {


    List<PagerTitle> data;

    public PagerFrAdapter(FragmentManager fm, List<PagerTitle> data) {
        super(fm);
        this.data = data;
    }

    @Override
    public Fragment getItem(int position) {
        return data.get(position);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return data.get(position).getTitle();
    }
}
