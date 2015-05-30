package com.cinsoftwares.materialdesign;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;

/**
 * Created by rahul on 5/17/15.
 */
public class RecyclerAdapter  extends RecyclerView.Adapter<RecyclerAdapter.ItemViewHolder>{


    LayoutInflater inflater;
    List<ListItem> itemList = Collections.emptyList();
    Context context;

    public RecyclerAdapter(Context context, List<ListItem> items) {
        this.context = context;
        itemList = items;
        inflater = LayoutInflater.from(context);
    }
    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View view = inflater.inflate(R.layout.custom_item_row, viewGroup, false);
        ItemViewHolder itemViewHolder = new ItemViewHolder(view);
        return itemViewHolder;
    }

    @Override
    public void onBindViewHolder(ItemViewHolder viewHolder, int position) {

        ListItem item = itemList.get(position);
        viewHolder.image.setImageResource(item.getImageId());
        viewHolder.text.setText(item.getTitle());
    }

    public void deleteItem(int position) {
        itemList.remove(position);
        notifyItemRemoved(position);

    }
    @Override
    public int getItemCount() {
        return itemList.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView image;
        TextView text;
        public ItemViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.itemImageView);
            text = (TextView) itemView.findViewById(R.id.itemTextView);
            image.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            Toast.makeText(context, "Adapter - Removing at " +getAdapterPosition(), Toast.LENGTH_SHORT).show();
            deleteItem(getAdapterPosition());
        }
    }
}


