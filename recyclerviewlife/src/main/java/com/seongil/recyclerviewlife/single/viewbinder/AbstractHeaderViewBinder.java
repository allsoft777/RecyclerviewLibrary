/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.seongil.recyclerviewlife.single.viewbinder;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;

/**
 * @author seong-il, kim
 * @since 17. 3. 21
 */
public abstract class AbstractHeaderViewBinder extends AbstractViewBinder {

    // ========================================================================
    // Constants
    // ========================================================================

    // ========================================================================
    // Fields
    // ========================================================================

    // ========================================================================
    // Constructors
    // ========================================================================
    public AbstractHeaderViewBinder(
          @NonNull LayoutInflater inflater,
          @Nullable RecyclerViewItemClickListener viewItemClickListener) {
        super(RECYCLER_HEADER_VIEW_TYPE, inflater, viewItemClickListener);
    }

    // ========================================================================
    // Getter & Setter
    // ========================================================================

    // ========================================================================
    // Methods for/from SuperClass/Interfaces
    // ========================================================================

    // ========================================================================
    // Methods
    // ========================================================================

    // ========================================================================
    // Inner and Anonymous Classes
    // ========================================================================
}