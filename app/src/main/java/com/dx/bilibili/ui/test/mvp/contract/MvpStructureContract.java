package com.dx.bilibili.ui.test.mvp.contract;

import com.dx.bilibili.base.BasePresenter;
import com.dx.bilibili.base.BaseView;
import com.dx.bilibili.model.bean.WeiXinJingXuanBean;

import java.util.List;

/**
 * Created by jiayiyang on 17/3/25.
 */

public interface MvpStructureContract {

    interface View extends BaseView {

        void updateData(List<WeiXinJingXuanBean.NewsList> list);

        void setRefreshing();

    }

    interface Prensenter extends BasePresenter {

        void loadData();

        void deleteData();

    }

}
