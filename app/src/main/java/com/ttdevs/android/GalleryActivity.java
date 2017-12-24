package com.ttdevs.android;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.ttdevs.gallery.CardScaleHelper;

public class GalleryActivity extends BaseActivity {

    private RecyclerView rvSnapHelper;
    private GalleryAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        rvSnapHelper = findViewById(R.id.rv_snap_helper);

        init();
    }

    private void init() {
        mAdapter = new GalleryAdapter();
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        rvSnapHelper.setLayoutManager(manager);
        rvSnapHelper.setAdapter(mAdapter);
        new LinearSnapHelper().attachToRecyclerView(rvSnapHelper);
//        new PagerSnapHelper().attachToRecyclerView(rvSnapHelper);
//        CardScaleHelper mCardScaleHelper = new CardScaleHelper();
//        mCardScaleHelper.setCurrentItemPos(2);
//        mCardScaleHelper.attachToRecyclerView(rvSnapHelper);
    }

    class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.ViewHolder> {
        private final String[] IMAGES = new String[]{
                "https://photo.tuchong.com/1452780/m/20154233.webp",
                "https://photo.tuchong.com/1452780/m/20154214.webp",
                "https://photo.tuchong.com/1452780/m/20154215.webp",
                "https://photo.tuchong.com/1452780/m/20154216.webp",
                "https://photo.tuchong.com/1452780/m/20154183.webp",
                "https://photo.tuchong.com/1452780/m/20154185.webp",
                "https://photo.tuchong.com/1452780/m/20154187.webp",
                "https://photo.tuchong.com/1452780/m/20154051.webp",
                "https://photo.tuchong.com/1452780/m/20154050.webp",
                "https://photo.tuchong.com/1452780/m/20154046.webp",
                "https://photo.tuchong.com/1452780/m/20153162.webp"
        };

        private final RequestOptions OPTIONS = new RequestOptions().centerCrop();

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            View item = layoutInflater.inflate(R.layout.item_gallery_snap_helper, parent, false);
            item.getLayoutParams().width = screenWidth(parent.getContext()) - 400;
            return new ViewHolder(item);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {

            Glide.with(GalleryActivity.this).load(IMAGES[position]).apply(OPTIONS).into(holder.ivContent);
        }

        @Override
        public int getItemCount() {
            return IMAGES.length;
        }

        public int screenWidth(Context context) {
            return context.getResources().getDisplayMetrics().widthPixels;
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public ImageView ivContent;

            public ViewHolder(View itemView) {
                super(itemView);

                ivContent = itemView.findViewById(R.id.iv_content);
            }
        }
    }
}
