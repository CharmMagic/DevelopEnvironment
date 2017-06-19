package com.dx.bilibili.ui.test.mvp.presenter;

import android.util.Log;

import com.dx.bilibili.base.AbsBasePresenter;
import com.dx.bilibili.model.api.WeChatApis;
import com.dx.bilibili.model.db.User;
import com.dx.bilibili.model.db.UserDao;
import com.dx.bilibili.ui.test.mvp.contract.TestRoomContract;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscription;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by jiayiyang on 17/3/25.
 */

public class TestRoomPresenter extends AbsBasePresenter<TestRoomContract.View> implements TestRoomContract.Prensenter {

    private static final String TAG = TestRoomPresenter.class.getSimpleName();

    private UserDao userDao;

    @Inject
    public TestRoomPresenter(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void loadData() {

    }

    @Override
    public void testApi() {
        Disposable disposable = userDao.getAll()
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
        register(disposable);
    }

    @Override
    public void releaseData() {

    }
}
