package com.example.pagingretrofit.adapter;

import android.annotation.SuppressLint;
import android.arch.paging.PagedList;
import android.arch.paging.PagedListAdapter;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pagingretrofit.R;
import com.example.pagingretrofit.model.Hit;
import com.squareup.picasso.Picasso;

public class ImageListAdapter extends PagedListAdapter<Hit, ImageListAdapter.ImageHolder> {

    private static final DiffUtil.ItemCallback<Hit> CALLBACK = new DiffUtil.ItemCallback<Hit>() {
        @Override
        public boolean areItemsTheSame(@NonNull Hit oldHit, @NonNull Hit newHit) {
            return oldHit.getId() == newHit.getId();
        }

        @SuppressLint("DiffUtilEquals")
        @Override
        public boolean areContentsTheSame(@NonNull Hit oldHit, @NonNull Hit newHit) {
            return oldHit.equals(newHit);
        }
    };

    public ImageListAdapter() {
        super(CALLBACK);
    }

    @NonNull
    @Override
    public ImageHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int type) {
        return new ImageHolder(LayoutInflater.from(viewGroup.getContext()), viewGroup);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageHolder imageHolder, int position) {
        imageHolder.bind(getItem(position));
    }

    public class ImageHolder extends RecyclerView.ViewHolder{
        private ImageView image;
        private TextView imageAuthor;
        private TextView imageLikes;

        public ImageHolder(LayoutInflater inflater, ViewGroup viewGroup){
            super(inflater.inflate(R.layout.image_item, viewGroup, false));
            init();
        }

        private void init() {
            image = itemView.findViewById(R.id.image);
            imageAuthor = itemView.findViewById(R.id.image_author);
            imageLikes = itemView.findViewById(R.id.image_likes);
        }

        public void bind(Hit hit) {
            if (hit != null){
                Picasso.get().load(hit.getLargeImageURL()).error(R.drawable.error).into(image);
                imageAuthor.setText(hit.getUser());
                imageLikes.setText(String.valueOf(hit.getLikes()));
            } else {
                Picasso.get().load(R.drawable.loading).error(R.drawable.error).into(image);
                imageAuthor.setText(R.string.loading);
                imageLikes.setText(R.string.loading);
            }
        }
    }
}
