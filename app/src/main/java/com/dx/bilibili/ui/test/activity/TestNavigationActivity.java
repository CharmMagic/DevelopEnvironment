package com.dx.bilibili.ui.test.activity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.dx.bilibili.R;
import com.dx.bilibili.app.MyService;
import com.dx.bilibili.base.BaseActivity;
import com.dx.bilibili.util.StatusBarUtil;

import butterknife.BindView;
import butterknife.OnClick;

public class TestNavigationActivity extends BaseActivity {

    @BindView(R.id.news_btn)
    Button btnNews;
    @BindView(R.id.toolbar_behavior_mvp_btn)
    Button btnToolbarBehavior;
    @BindView(R.id.status_picture_mvp_btn)
    Button btnStatusWithPicture;
    @BindView(R.id.scroll_gradient_mvp_btn)
    Button btnScrollGradient;
    @BindView(R.id.test_api_btn)
    Button btnTestApi;
    @BindView(R.id.test_room_btn)
    Button btnTestRoom;
    @BindView(R.id.test_service_btn)
    Button btnTestService;
    @BindView(R.id.test_unbind_btn)
    Button btnTestUnBind;

    private MyService.MyBinder myBinder;

    private ServiceConnection connection = new ServiceConnection() {

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d("misery","onServiceDisconnected componentName : "+name);
        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            myBinder = (MyService.MyBinder) service;
            myBinder.startDownload();
        }
    };

    @Override
    protected int getLayoutId() {
        return R.layout.activity_test_navigation;
    }

    @Override
    protected void initInject() {
        //
        //master
        //dev
    }

    private void newMethod1(){
        //dev1
        //dev2
        //dev4
        //dev5
        //dev6
        //dev7
        //dev8
        //dev9
        //dev10
        //dev11
        //dev12
        //dev13
        //dev14
    }

    @Override
    protected void initViewAndEvent() {
        //关闭右滑返回
        setSwipeBackEnable(false);
        StatusBarUtil.setColor(this, getResources().getColor(R.color.colorPrimary));
    }

    @Override
    protected void initData() {
        Intent intent = new Intent(this, MyService.class);
        startService(intent);
    }

    @OnClick({R.id.toolbar_behavior_mvp_btn,
            R.id.news_btn,
            R.id.status_picture_mvp_btn,
            R.id.scroll_gradient_mvp_btn,
            R.id.test_api_btn,
            R.id.test_room_btn,
            R.id.test_service_btn,
            R.id.test_unbind_btn
    })
    public void jumpToPage(View view){
        switch (view.getId()){
            case R.id.toolbar_behavior_mvp_btn:
                startActivity(new Intent(mContext, ToolbarBehaviorActivity.class));
                break;
            case R.id.news_btn:
                startActivity(new Intent(mContext, NewsActivity.class));
                break;
            case R.id.status_picture_mvp_btn:
                startActivity(new Intent(mContext, StatusWithPictureActivity.class));
                break;
            case R.id.scroll_gradient_mvp_btn:
                startActivity(new Intent(mContext, ScrollGradientActivity.class));
                break;
            case R.id.test_api_btn:
                startActivity(new Intent(mContext, TestApiActivity.class));
                break;
            case R.id.test_room_btn:
                startActivity(new Intent(mContext, TestRoomActivity.class));
            case R.id.test_service_btn:
                Intent bindIntent = new Intent(this, MyService.class);
                bindService(bindIntent, connection, BIND_AUTO_CREATE);
                break;
            case R.id.test_unbind_btn:
                unbindService(connection);
                //master1
                //master2
                break;
        }
    }

}
