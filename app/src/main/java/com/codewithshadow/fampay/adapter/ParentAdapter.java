package com.codewithshadow.fampay.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.codewithshadow.fampay.R;
import com.codewithshadow.fampay.models.CardModel;
import java.util.List;

public class ParentAdapter extends RecyclerView.Adapter<ParentAdapter.MyViewHolder> {
    private final Context aCtx;
    final private List<List<CardModel>> listList;

    public ParentAdapter(Context aCtx, List<List<CardModel>> listList){
        this.aCtx=aCtx;
        this.listList=listList;
    }

    @NonNull
    @Override
    public ParentAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(aCtx).inflate(R.layout.custom_recycler, parent, false);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ParentAdapter.MyViewHolder holder, int position) {

        //Set the CardsAdapter in the recyclerView
        CardsAdapter cardsAdapter = new CardsAdapter(aCtx,listList.get(position));
        holder.recyclerView.setAdapter(cardsAdapter);
        holder.recyclerView.setLayoutManager(new LinearLayoutManager(aCtx,LinearLayoutManager.HORIZONTAL,false));

    }

    @Override
    public int getItemCount() {
        if (listList == null){
            return 0;
        }
        return listList.size();
    }



    public static class MyViewHolder extends RecyclerView.ViewHolder {
        RecyclerView recyclerView;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            recyclerView = itemView.findViewById(R.id.custom_main_recyclerView);

        }
    }


}




