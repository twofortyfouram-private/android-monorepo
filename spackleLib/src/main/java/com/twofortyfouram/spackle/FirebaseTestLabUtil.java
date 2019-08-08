/*
 * android-spackle
 * https://github.com/twofortyfouram/android-monorepo
 * Copyright (C) 2008–2019 two forty four a.m. LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use
 * this file except in compliance with the License. You may obtain a copy of the
 * License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed
 * under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR
 * CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */

package com.twofortyfouram.spackle;

import android.content.Context;
import android.provider.Settings;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import net.jcip.annotations.ThreadSafe;

import static com.twofortyfouram.assertion.Assertions.*;


/**
 * Utility class for Firebase Test Lab.  This class is safe to use even if the client is not using
 * Firebase Test Lab.
 */
/*
 * This lives in spackleLib instead of testLib because there are a few code paths hit during
 * Google Play pre-launch reports that should be bypassed.
 */
@ThreadSafe
public final class FirebaseTestLabUtil {

    @NonNull
    private static final String FIREBASE_TEST_LAB_SETTING = "firebase.test.lab"; //$NON-NLS

    @NonNull
    private static final String SETTING_TRUE = "true"; //$NON-NLS

    /**
     * @return True if the environment is Firebase Test Lab.  Useful for certain tests that fail
     * in the test lab environment.
     */
    public static boolean isFirebaseTestLab(@NonNull final Context context) {
        assertNotNull(context, "context"); //$NON-NLS

        /*
         * Per the documentation at https://firebase.google.com/docs/test-lab/android-studio
         */
        // Tested with the benchmark library, this is very fast.
        @Nullable final String testLabSetting =
                Settings.System.getString(context.getContentResolver(), FIREBASE_TEST_LAB_SETTING);

        return SETTING_TRUE.equals(testLabSetting);
    }

    /**
     * Private constructor prevents instantiation.
     *
     * @throws UnsupportedOperationException because this class cannot be instantiated.
     */
    private FirebaseTestLabUtil() {
        throw new UnsupportedOperationException("This class is non-instantiable"); //$NON-NLS-1$
    }
}
