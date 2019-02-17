public class CustomPagerAdapter extends FragmentPagerAdapter {

    Context context;

    FirebaseAuth mAuth;

    public CustomPagerAdapter(FragmentManager fmContext context) {
        super(fm);
        
        this.context = context;

    }

    @Override
    public Fragment getItem(int i) {

        switch (i) {

            case 0:
                return new HomeFragment(context);

            case 1:
                return new HomeFragment(context);

            case 2:
                return new HomeFragment(context);

            default:
                return null;

        }

    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {

        switch (position) {

            case 0:
                return "NEARBY";

            case 1:
                return "CHATS";

            case 2:
                return "CALLS";

            default:
                return null;

        }

    }
}

/*

Java Code

*/

private void pager() {

        ViewPager viewPager = findViewById(R.id.pager);

        PagerAdapter pagerAdapter = new CustomPagerAdapter(getSupportFragmentManager(), mAuth, MainActivity.this);

        viewPager.setAdapter(pagerAdapter);

        TabLayout tabLayout = findViewById(R.id.tabLayout);

        tabLayout.setupWithViewPager(viewPager);

    }

/*

XML

*/

<android.support.design.widget.TabLayout
            android:id="@+id/tabLayout"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:background="@color/colorPrimaryDark" />

        <android.support.v4.view.ViewPager
            android:id="@+id/pager"
            android:layout_width="wrap_content"
            android:layout_height="match_parent" />
                
