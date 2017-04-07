package com.seongil.recyclerviewlib.sample.ui.linearlistview.adapter.viewbinder;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.seongil.recyclerviewlib.model.common.RecyclerViewItem;
import com.seongil.recyclerviewlib.sample.R;
import com.seongil.recyclerviewlib.sample.model.TitleDateItem;
import com.seongil.recyclerviewlib.single.viewbinder.AbstractViewBinder;

/**
 * @author seong-il, kim
 * @since 17. 4. 8
 */
public class LinearListTwoTextViewBinder extends AbstractViewBinder {

    // ========================================================================
    // constants
    // ========================================================================

    // ========================================================================
    // fields
    // ========================================================================

    // ========================================================================
    // constructors
    // ========================================================================
    public LinearListTwoTextViewBinder(int viewType, @NonNull LayoutInflater inflater,
          @Nullable RecyclerViewItemClickListener itemClickListener) {
        super(viewType, inflater, itemClickListener);
    }

    // ========================================================================
    // getter & setter
    // ========================================================================

    // ========================================================================
    // methods for/from superclass/interfaces
    // ========================================================================
    @Override
    public boolean isForViewType(@NonNull RecyclerViewItem item) {
        return item instanceof TitleDateItem;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent) {
        return new TwoTextViewHolder(
              mLayoutInflater.inflate(R.layout.linearlistview_item_with_two_text, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewItem item, @NonNull RecyclerView.ViewHolder holder) {
        final TitleDateItem data = (TitleDateItem) item;
        final TwoTextViewHolder viewHolder = (TwoTextViewHolder) holder;
        viewHolder.itemView.setOnClickListener(v -> {
            if (mItemViewClickListener != null) {
                mItemViewClickListener.onClickedRecyclerViewItem(holder, data, viewHolder.getLayoutPosition());
            }
        });
        viewHolder.title.setText(data.getTitle());
        viewHolder.dateTime.setText(data.getDateTime());
    }

    // ========================================================================
    // methods
    // ========================================================================

    // ========================================================================
    // inner and anonymous classes
    // ========================================================================
    public static class TwoTextViewHolder extends RecyclerView.ViewHolder {

        public final TextView title;
        public final TextView dateTime;

        public TwoTextViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.primary_text);
            dateTime = (TextView) view.findViewById(R.id.secondary_text);
        }
    }
}