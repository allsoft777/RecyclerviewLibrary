package com.seongil.recyclerviewlib.sample.ui.linearlistview.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;

import com.seongil.recyclerviewlib.model.common.ViewStatus;
import com.seongil.recyclerviewlib.sample.model.ClientFooterItem;
import com.seongil.recyclerviewlib.sample.model.ClientHeaderItem;
import com.seongil.recyclerviewlib.sample.ui.dialog.OptionDialogFragment;
import com.seongil.recyclerviewlib.sample.ui.linearlistview.adapter.viewbinder.LinearListAdvertisementViewBinder;
import com.seongil.recyclerviewlib.sample.ui.linearlistview.adapter.viewbinder.LinearListFooterViewBinder;
import com.seongil.recyclerviewlib.sample.ui.linearlistview.adapter.viewbinder.LinearListHeaderViewBinder;
import com.seongil.recyclerviewlib.sample.ui.linearlistview.adapter.viewbinder.LinearListThumbnailTextViewBinder;
import com.seongil.recyclerviewlib.sample.ui.linearlistview.adapter.viewbinder.LinearListTwoTextViewBinder;
import com.seongil.recyclerviewlib.single.RecyclerListViewAdapter;
import com.seongil.recyclerviewlib.single.viewbinder.AbstractFooterViewBinder;
import com.seongil.recyclerviewlib.single.viewbinder.AbstractHeaderViewBinder;
import com.seongil.recyclerviewlib.single.viewbinder.AbstractViewBinder.RecyclerViewItemClickListener;

import java.util.ArrayList;

/**
 * @author seong-il, kim
 * @since 17. 4. 8
 */
public class LinearListViewAdapter extends RecyclerListViewAdapter {

    // ========================================================================
    // constants
    // ========================================================================
    private static final int VIEW_TYPE_TITLE_DATE = 1;
    private static final int VIEW_TYPE_THUMBNAIL_TITLE = 2;
    private static final int VIEW_TYPE_ADVERTISEMENT = 3;

    // ========================================================================
    // fields
    // ========================================================================
    private final int mLoadingMode; // For testing..
    @Nullable
    private RecyclerViewItemClickListener mViewItemClickListener;

    // ========================================================================
    // constructors
    // ========================================================================
    public LinearListViewAdapter(int loadingMode, // For testing
          @NonNull LayoutInflater layoutInflater,
          @Nullable RecyclerViewItemClickListener viewItemClickListener) {
        super(layoutInflater);
        setDataSet(new ArrayList());

        mLoadingMode = loadingMode;
        mViewItemClickListener = viewItemClickListener;

        addViewBinder(new LinearListTwoTextViewBinder(VIEW_TYPE_TITLE_DATE, layoutInflater, viewItemClickListener));
        addViewBinder(
              new LinearListThumbnailTextViewBinder(VIEW_TYPE_THUMBNAIL_TITLE, layoutInflater, viewItemClickListener));
        addViewBinder(
              new LinearListAdvertisementViewBinder(VIEW_TYPE_ADVERTISEMENT, layoutInflater, viewItemClickListener));
    }

    // ========================================================================
    // getter & setter
    // ========================================================================

    // ========================================================================
    // methods for/from superclass/interfaces
    // ========================================================================
    @Override
    protected AbstractFooterViewBinder getNewInstanceOfFooterViewBinder() {
        return new LinearListFooterViewBinder(mLayoutInflater, mViewItemClickListener);
    }

    @Override
    protected ClientFooterItem getNewInstanceOfFooterItem() {
        ViewStatus code = ViewStatus.VISIBLE_LOADING_VIEW;
        if (OptionDialogFragment.LOADING_TOUCH == mLoadingMode) {
            code = ViewStatus.VISIBLE_LABEL_VIEW;
        }
        return new ClientFooterItem(code);
    }

    @Override
    protected AbstractHeaderViewBinder getNewInstanceOfHeaderViewBinder() {
        return new LinearListHeaderViewBinder(mLayoutInflater, mViewItemClickListener);
    }

    @Override
    protected ClientHeaderItem getNewInstanceOfHeaderItem() {
        ViewStatus code = ViewStatus.VISIBLE_LOADING_VIEW;
        if (OptionDialogFragment.LOADING_TOUCH == mLoadingMode) {
            code = ViewStatus.VISIBLE_LABEL_VIEW;
        }
        return new ClientHeaderItem(code);
    }

    // ========================================================================
    // methods
    // ========================================================================

    // ========================================================================
    // inner and anonymous classes
    // ========================================================================
}
