package gukbab1216.com.chalkak.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

import java.util.List;

import gukbab1216.com.chalkak.R;
import gukbab1216.com.chalkak.model.Picture;

public class HorizontalSceneAdapter extends RecyclerView.Adapter<HorizontalSceneAdapter.ViewHolder> {
    private List<Picture> mItems;
    private Context mContext;
    private GoogleMap mGmap;

    public HorizontalSceneAdapter(Context context, List<Picture> items, GoogleMap gmap) {
        mItems = items;
        mContext = context;
        mGmap = gmap;
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

        holder.mThumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch(position) {
                    case 0:
                        Log.d("TAGTAGTAG", String.valueOf(mItems.get(0).getPosTitle()));

                        break;

                    case 1:
                        Log.d("TAGTAGTAG", String.valueOf(mItems.get(1).getPosTitle()));
                        break;

                    case 2:
                        Log.d("TAGTAGTAG", String.valueOf(mItems.get(2).getPosTitle()));
                        break;

                    default:
                        Log.d("TAGTAGTAG", String.valueOf(position));
                }
            }
        });

        mGmap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                //구글맵 자체가 로딩이안되서 태그 확인을 못함
                Log.d("TAGTAGTAG", String.valueOf(marker.getTitle()));
                return false;
            }
        });


    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView mThumbnail;
        TextView mTitle;

        ViewHolder(View view) {
            super(view);
            mThumbnail = view.findViewById(R.id.thumbnail_scene);
            mTitle = view.findViewById(R.id.title_scene);
        }
    }
}
