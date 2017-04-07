package com.seongil.recyclerviewlib.scroll;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.seongil.recyclerviewlib.LibUtils;
import com.seongil.recyclerviewlib.RecyclerListViewListener;
import com.seongil.recyclerviewlib.single.RecyclerListViewAdapter;

/**
 * @author seong-il, kim
 * @since 17. 4. 7
 */
public class LinearRecyclerViewScrollListener extends RecyclerView.OnScrollListener {

    // ========================================================================
    // constants
    // ========================================================================
    private static final String TAG = "LinearRecyclerViewScrollListener";

    // ========================================================================
    // fields
    // ========================================================================
    private int AUTO_LOADING_THRESHOLD = 5;

    // Represents the count of loaded items in the {@@link RecyclerView}
    private static final int CNT_OF_MINIMUM_LOADED_ITEMS = 20;

    private LinearLayoutManager mLinearLayoutManager;
    private RecyclerListViewListener mRecyclerListViewListener;

    @ScrollDirection.ScrollDir
    private int mScrollDirection = ScrollDirection.STOP;

    private int mCntOfPrevTotalItem;
    private int mPosOfFirstVisibleItem, mCntOfVisibleItem, mCntOfTotalItem;

    // ========================================================================
    // constructors
    // ========================================================================
    public LinearRecyclerViewScrollListener(final LinearLayoutManager llm,
          final RecyclerListViewAdapter recyclerListViewListener) {
        LibUtils.checkNotNull(llm, "LinearLayoutManager is null. You must pass a valid object.");
        LibUtils.checkNotNull(recyclerListViewListener,
              "RecyclerListViewListener is null. You must pass a valid object.");

        mLinearLayoutManager = llm;
        mRecyclerListViewListener = recyclerListViewListener;

        initScrollListener();
    }

    // ========================================================================
    // getter & setter
    // ========================================================================

    // ========================================================================
    // methods for/from superclass/interfaces
    // ========================================================================
    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        mScrollDirection = decideScrollDirection(dx, dy);

        mCntOfVisibleItem = recyclerView.getChildCount();
        mCntOfTotalItem = mLinearLayoutManager.getItemCount();
        mPosOfFirstVisibleItem = mLinearLayoutManager.findFirstVisibleItemPosition();

        if (mScrollDirection == ScrollDirection.UP || mScrollDirection == ScrollDirection.LEFT) {
            handleScrollUp(recyclerView);
        } else if (mScrollDirection == ScrollDirection.DOWN || mScrollDirection == ScrollDirection.RIGHT) {
            handleScrollDown(recyclerView);
        } else if (mScrollDirection == ScrollDirection.STOP) {
            loadItemsMoreForcibly();
        } else {
            throw new AssertionError("Undefined scrolling direction.");
        }
    }

    // ========================================================================
    // methods
    // ========================================================================
    public void initScrollListener() {
        mCntOfPrevTotalItem = 0;
        mPosOfFirstVisibleItem = 0;
        mCntOfVisibleItem = 0;
        mCntOfTotalItem = 0;
    }

    public void loadItemsMoreForcibly() {
        if (mCntOfTotalItem >= CNT_OF_MINIMUM_LOADED_ITEMS) {
            // We don't need to load more data forcibly.
            return;
        }

        if (mRecyclerListViewListener.registeredHeaderView()) {
            onLoadPrevData();
        }
        if (mRecyclerListViewListener.registeredFooterView()) {
            onLoadNextData();
        }
    }

    /**
     * Is the client view loading "next" items currently?
     * If you override {@link #onLoadPrevData}, you MUST override this method also.
     *
     * @return true, the client view is loading the "next" items currently. otherwise, false
     */
    protected boolean isLoadingNextItems() {
        return false;
    }

    /**
     * Is the client view loading "previous" items currently?
     * If you override {@link #onLoadPrevData}, you MUST override this method also.
     *
     * @return true, the client view is loading the "previous" items currently. otherwise, false
     */
    protected boolean isLoadingPrevItems() {
        return false;
    }

    /**
     * If you want to load the next data more, override this method.
     */
    public void onLoadNextData() {
        // Do nothing
    }

    /**
     * If you want to load previous data more, override this method.
     */
    public void onLoadPrevData() {
        // Do nothing
    }

    /**
     * This method is triggered when the {@@link RecyclerView} is scrolling to the top position.
     */
    private void handleScrollDown(final RecyclerView recyclerView) {
        if (!isLoadingPrevItems()
              && mRecyclerListViewListener.loadHeaderItemsMore()
              && (mPosOfFirstVisibleItem <= AUTO_LOADING_THRESHOLD)) {
            onLoadPrevData();
        }
    }

    /**
     * This method is triggered when the {@@link RecyclerView} is scrolling to the bottom position.
     */
    private void handleScrollUp(final RecyclerView recyclerView) {
        if (isLoadingNextItems() && mCntOfTotalItem > mCntOfPrevTotalItem) {
            mCntOfPrevTotalItem = mCntOfTotalItem;
        }

        if (!isLoadingNextItems() && mRecyclerListViewListener.loadFooterItemsMore()
              && (mCntOfTotalItem - mCntOfVisibleItem) <= (mPosOfFirstVisibleItem + AUTO_LOADING_THRESHOLD)) {
            onLoadNextData();
        }
    }

    /**
     * Decide the scrolling direction.
     */
    @ScrollDirection.ScrollDir
    private int decideScrollDirection(final int dx, final int dy) {
        if (dx > 0) {
            return ScrollDirection.RIGHT;
        } else if (dx < 0) {
            return ScrollDirection.LEFT;
        }
        if (dy > 0) {
            return ScrollDirection.UP;
        } else if (dy < 0) {
            return ScrollDirection.DOWN;
        }
        return ScrollDirection.STOP;
    }

    // ========================================================================
    // inner and anonymous classes
    // ========================================================================
}