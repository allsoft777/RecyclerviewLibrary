package com.seongil.recyclerviewlib.sample.model;

import com.seongil.recyclerviewlib.model.RecyclerViewFooterItem;
import com.seongil.recyclerviewlib.model.common.ViewStatus;

/**
 * @author seong-il, kim
 * @since 17. 4. 7
 */
public class ClientFooterItem extends RecyclerViewFooterItem {

    // ========================================================================
    // constants
    // ========================================================================

    // ========================================================================
    // fields
    // ========================================================================

    // ========================================================================
    // constructors
    // ========================================================================
    public ClientFooterItem() {
        super(ViewStatus.VISIBLE_LOADING_VIEW);
    }

    public ClientFooterItem(ViewStatus requestCode) {
        super(requestCode);
    }

    // ========================================================================
    // getter & setter
    // ========================================================================

    // ========================================================================
    // methods for/from superclass/interfaces
    // ========================================================================

    // ========================================================================
    // methods
    // ========================================================================

    // ========================================================================
    // inner and anonymous classes
    // ========================================================================
}
