package gukbab1216.com.chalkak.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import gukbab1216.com.chalkak.Model.Scene;
import gukbab1216.com.chalkak.R;
import gukbab1216.com.chalkak.databinding.ItemSceneMapBinding;

public class HorizontalSceneAdapter extends RecyclerView.Adapter<HorizontalSceneAdapter.ViewHolder> {
    private List<Scene> mItems;
    private Context mContext;

    public HorizontalSceneAdapter(Context context, List<Scene> items) {
        mItems= items;
        mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_scene_map, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final Scene scene = mItems.get(position);

// displaying text view data
        if(position==0){
            holder.mThumbnail.setImageDrawable(mContext.getDrawable(R.drawable.myway_place01));
            holder.mTitle.setText("来自星星的你");
        }
        if(position==1){
            holder.mThumbnail.setImageDrawable(mContext.getDrawable(R.drawable.myway_place02));
            holder.mTitle.setText("来自星星的你");
        }
        if(position==2){
            holder.mThumbnail.setImageDrawable(mContext.getDrawable(R.drawable.myway_place03));
            holder.mTitle.setText("来自星星的你");
        }
        if(position==3){
            holder.mThumbnail.setImageDrawable(mContext.getDrawable(R.drawable.myway_place04));
            holder.mTitle.setText("来自星星的你");
        }
        if(position==4){
            holder.mThumbnail.setImageDrawable(mContext.getDrawable(R.drawable.myway_place05));
            holder.mTitle.setText("来自星星的你");
        }

    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView mThumbnail;
        public TextView mTitle;

        public ViewHolder(View view) {
            super(view);

            mThumbnail = view.findViewById(R.id.thumbnail_scene);
            mTitle = view.findViewById(R.id.title_scene);
        }
    }
}
