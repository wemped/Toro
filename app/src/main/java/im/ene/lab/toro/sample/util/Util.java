/*
 * Copyright 2016 eneim@Eneim Labs, nam@ene.im
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package im.ene.lab.toro.sample.util;

import android.support.v4.util.TimeUtils;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by eneim on 2/3/16.
 */
public class Util {

  public static String timeStamp(int position, int duration) {
    StringBuilder posTime = new StringBuilder();
    TimeUtils.formatDuration(position, posTime);
    StringBuilder durationTime = new StringBuilder();
    TimeUtils.formatDuration(duration, durationTime);

    return posTime + " / " + durationTime.toString();
  }

  private static final AtomicInteger sNextGeneratedId = new AtomicInteger(1);

  // Copy from AOSP
  /**
   * Generate a value suitable for use in {@link #setId(int)}.
   * This value will not collide with ID values generated at build time by aapt for R.id.
   *
   * @return a generated ID value
   */
  public static int generateViewId() {
    for (; ; ) {
      final int result = sNextGeneratedId.get();
      // aapt-generated IDs have the high byte nonzero; clamp to the range under that.
      int newValue = result + 1;
      if (newValue > 0x00FFFFFF) newValue = 1; // Roll over to 1, not 0.
      if (sNextGeneratedId.compareAndSet(result, newValue)) {
        return result;
      }
    }
  }
}
