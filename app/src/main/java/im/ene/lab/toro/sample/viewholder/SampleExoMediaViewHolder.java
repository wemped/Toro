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

package im.ene.lab.toro.sample.viewholder;

import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.devbrackets.android.exomedia.EMVideoView;
import com.squareup.picasso.Picasso;
import im.ene.lab.toro.ToroViewHolder;
import im.ene.lab.toro.sample.R;
import im.ene.lab.toro.sample.data.SimpleVideoObject;

/**
 * Created by eneim on 3/7/16.
 */
public class SampleExoMediaViewHolder extends ToroViewHolder {

  public static final int LAYOUT_RES = R.layout.vh_toro_exo_video_simple;

  @Bind(R.id.video) EMVideoView mVideoView;
  @Bind(R.id.thumbnail) ImageView mThumbnail;

  private SimpleVideoObject mItem;
  private boolean mPlayable = true;

  public SampleExoMediaViewHolder(View itemView) {
    super(itemView);
    ButterKnife.bind(this, itemView);

    mVideoView.setReleaseOnDetachFromWindow(false);

    mVideoView.setDefaultControlsEnabled(false);
    mVideoView.setOnPreparedListener(this);
    mVideoView.setOnCompletionListener(this);
    mVideoView.setOnErrorListener(this);
    mVideoView.setOnInfoListener(this);
    // mVideoView.setOnSeekCompleteListener(this);
  }

  @Override public void bind(@Nullable Object object) {
    if (mVideoView != null && object instanceof SimpleVideoObject) {
      mItem = (SimpleVideoObject) object;
      mVideoView.setVideoPath(((SimpleVideoObject) object).video);
    }
  }

  @Override public void onViewHolderBound() {
    Picasso.with(itemView.getContext())
        .load(R.drawable.toro_place_holder)
        .fit()
        .centerInside()
        .into(mThumbnail);
  }

  @Override public boolean wantsToPlay() {
    return super.visibleAreaOffset() >= 0.85;
  }

  @Override public boolean isAbleToPlay() {
    return mPlayable;
  }

  @Override public void onVideoPrepared(MediaPlayer mp) {
    super.onVideoPrepared(mp);
    mPlayable = true;
  }

  @Nullable @Override public String getVideoId() {
    return "TEST: " + getAdapterPosition();
  }

  @NonNull @Override public View getVideoView() {
    return mVideoView;
  }

  @Override public void start() {
    mVideoView.start();
  }

  @Override public void pause() {
    mVideoView.pause();
  }

  @Override public int getDuration() {
    return (int) mVideoView.getDuration();
  }

  @Override public int getCurrentPosition() {
    return (int) mVideoView.getCurrentPosition();
  }

  @Override public void seekTo(int pos) {
    mVideoView.seekTo(pos);
  }

  @Override public boolean isPlaying() {
    return mVideoView.isPlaying();
  }

  @Override protected boolean allowLongPressSupport() {
    return itemView != null && itemView.getResources().getBoolean(R.bool.accept_long_press);
  }

  @Override public void onPlaybackStarted() {
    mThumbnail.setVisibility(View.INVISIBLE);
  }

  @Override public void onPlaybackPaused() {
    mThumbnail.setVisibility(View.VISIBLE);
  }

  @Override public void onPlaybackStopped() {
    mThumbnail.setVisibility(View.INVISIBLE);
  }
}
