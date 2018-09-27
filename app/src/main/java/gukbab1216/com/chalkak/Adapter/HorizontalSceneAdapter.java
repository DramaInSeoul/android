package gukbab1216.com.chalkak.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

import gukbab1216.com.chalkak.Model.Picture;
import gukbab1216.com.chalkak.R;

public class HorizontalSceneAdapter extends RecyclerView.Adapter<HorizontalSceneAdapter.ViewHolder> {
    private List<Picture> mItems;
    private Context mContext;

    public HorizontalSceneAdapter(Context context, List<Picture> items) {
        mItems = items;
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
        final Picture picture = mItems.get(position);

        //가져온 사진 리스트에 맞게 view에 맵핑되는 부분.
        String posTitle = picture.getPosTitle();
        Glide.with(mContext).load(picture.getImgUrl()).into(holder.mThumbnail);
        holder.mTitle.setText(posTitle);

    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView mThumbnail;
        TextView mTitle;

        ViewHolder(View view) {
            super(view);
            mThumbnail = view.findViewById(R.id.thumbnail_scene);
            mTitle = view.findViewById(R.id.title_scene);
            mThumbnail.setOnClickListener(this);
            mTitle.setOnClickListener(this);

        }

        //지도 밑에 섬네일 사진 클릭하면 로그 찍힘.
        @Override
        public void onClick(View view) {
            Log.d("섬네일", String.valueOf(mTitle));
        }
    }
}
