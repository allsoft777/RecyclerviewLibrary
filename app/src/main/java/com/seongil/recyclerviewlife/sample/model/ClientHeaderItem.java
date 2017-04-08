package com.seongil.recyclerviewlife.sample.model;

import com.seongil.recyclerviewlife.model.RecyclerViewHeaderItem;
import com.seongil.recyclerviewlife.model.common.ViewStatus;

/**
 * @author seong-il, kim
 * @since 17. 4. 7
 */
public class ClientHeaderItem extends RecyclerViewHeaderItem {

    // ========================================================================
    // constants
    // ========================================================================

    // ========================================================================
    // fields
    // ========================================================================

    // ========================================================================
    // constructors
    // ========================================================================
    public ClientHeaderItem() {
        super(ViewStatus.VISIBLE_LOADING_VIEW);
    }

    public ClientHeaderItem(ViewStatus statusCode) {
        super(statusCode);
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
