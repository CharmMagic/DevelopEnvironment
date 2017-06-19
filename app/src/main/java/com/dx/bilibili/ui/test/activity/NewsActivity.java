package com.dx.bilibili.ui.test.activity;

import android.arch.persistence.room.Room;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.dx.bilibili.R;
import com.dx.bilibili.base.BaseActivity;
import com.dx.bilibili.model.db.AppDatabase;
import com.dx.bilibili.model.db.User;
import com.dx.bilibili.model.db.UserDao;
import com.dx.bilibili.ui.test.fragment.NewsFragment;
import com.dx.bilibili.util.StatusBarUtil;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscription;

import java.util.List;

import butterknife.BindView;
import io.reactivex.Flowable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class NewsActivity extends BaseActivity {

    private final String TAG = NewsActivity.class.getSimpleName();

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.content_ll)
    LinearLayout mLinearLayout;
    @BindView(R.id.nav_view)
    NavigationView mNavigationView;

    @Override
    protected int getLayoutId() {
        //布局中NavigationView设置fitsSystemWindows=false解决4.4左侧抽屉与状态栏merge问题
        return R.layout.activity_news;
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    public void initViewAndEvent() {
        StatusBarUtil.setColorForDrawerLayout(this, getResources().getColor(R.color.colorPrimary), mLinearLayout);
        //关闭右滑返回
        setSwipeBackEnable(false);

        mToolbar.setTitle("新闻");
        setSupportActionBar(mToolbar);

        NewsFragment newsFragment = new NewsFragment();
        loadRootFragment(R.id.news_container, newsFragment);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,
                mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.setDrawerListener(toggle);
        toggle.syncState();
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@android.support.annotation.NonNull MenuItem item) {
                // Handle navigation view item clicks here.
                int id = item.getItemId();

                if (id == R.id.nav_camera) {
                    // Handle the camera action
                } else if (id == R.id.nav_gallery) {

                } else if (id == R.id.nav_slideshow) {

                } else if (id == R.id.nav_manage) {

                } else if (id == R.id.nav_share) {

                } else if (id == R.id.nav_send) {

                }

                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);
                return true;
            }
        });
        mDrawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                //content布局随抽屉布局移动
                //获取屏幕的宽高
//                WindowManager manager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
//                Display display = manager.getDefaultDisplay();
                //设置右面的布局位置  根据左面菜单的right作为右面布局的left   左面的right+屏幕的宽度（或者right的宽度这里是相等的）为右面布局的right
//                mLinearLayout.layout(mNavigationView.getRight(), 0, mNavigationView.getRight() +  display.getWidth(), display.getHeight());
            }

            @Override
            public void onDrawerOpened(View drawerView) {

            }

            @Override
            public void onDrawerClosed(View drawerView) {

            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });
    }

    @Override
    protected void initData() {
        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "database-name").build();
        final UserDao userDao = db.userDao();
        userDao.getAll()
                .doOnSubscribe(new Consumer<Subscription>() {
                    @Override
                    public void accept(@NonNull Subscription subscription) throws Exception {
                        userDao.deleteAll();
                        User user1 = new User(1, "q", "w");
                        User user2 = new User(2, "a", "s");
                        User user3 = new User(3, "z", "x");
                        userDao.insertAll(user1, user2, user3);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.newThread())
                .flatMap(new Function<List<User>, Publisher<User>>() {
                    @Override
                    public Publisher<User> apply(@NonNull List<User> users) throws Exception {
                        return Flowable.fromIterable(users);
                    }
                }).subscribe(new Consumer<User>() {
            @Override
            public void accept(@NonNull User user) throws Exception {
                Log.d("misery", "user=" + user);
            }
        });
    }

    @Override
    public void onBackPressedSupport() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
                pop();
            } else {
                finish();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.news, menu);
        MenuItem item = menu.findItem(R.id.action_settings);
        item.setVisible(false);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
