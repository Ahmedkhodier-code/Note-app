package com.example.lastiti.viewPager;

import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


public class ViewPagerViewModel extends ViewModel {
    private MutableLiveData<ViewPagerAdapter> viewPagerAdapter  = new MutableLiveData<>();

    public LiveData<ViewPagerAdapter> getViewPagerAdapter() {
        if (viewPagerAdapter == null) {
            viewPagerAdapter = new MutableLiveData<>();
        }
        return viewPagerAdapter;
    }

    public void setViewPagerAdapter(ViewPagerAdapter VPA) {
        // Do an asynchronous operation to fetch ViewPagerAdapter.
        viewPagerAdapter.setValue(VPA);
    }
}
