package com.codewithshadow.fampay.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.codewithshadow.fampay.R;
import com.codewithshadow.fampay.Utils.AppSharedPreferences;
import com.codewithshadow.fampay.activites.WebActivity;
import com.codewithshadow.fampay.models.CardModel;
import com.thekhaeng.pushdownanim.PushDownAnim;
import org.jetbrains.annotations.NotNull;
import java.util.List;

public class CardsAdapter extends RecyclerView.Adapter {
    final private Context aCtx;
    final private List<CardModel> list;
    public static final int TYPE_HC1 = 1;
    public static final int TYPE_HC3 = 3;
    public static final int TYPE_HC5 = 5;
    public static final int TYPE_HC6 = 6;
    public static final int TYPE_HC9 = 9;
    AppSharedPreferences appSharedPreferences;
    static boolean isClicked = true;


    public CardsAdapter(Context aCtx, List<CardModel> list) {
        this.aCtx = aCtx;
        this.list = list;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (viewType == TYPE_HC1) {
            View view = LayoutInflater.from(aCtx).inflate(R.layout.card_hc1, parent, false);
            return new CardsAdapter.HC1_Layout(view);
        }
        else if (viewType == TYPE_HC3) {
            View view = LayoutInflater.from(aCtx).inflate(R.layout.card_hc3, parent, false);
            return new CardsAdapter.HC3_Layout(view);
        } else if (viewType == TYPE_HC5) {
            View view = LayoutInflater.from(aCtx).inflate(R.layout.card_hc5, parent, false);
            return new CardsAdapter.HC5_Layout(view);
        } else if (viewType == TYPE_HC6) {
            View view = LayoutInflater.from(aCtx).inflate(R.layout.card_hc6, parent, false);
            return new CardsAdapter.HC6_Layout(view);
        } else {
            View view = LayoutInflater.from(aCtx).inflate(R.layout.card_hc9, parent, false);
            return new CardsAdapter.HC9_Layout(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        appSharedPreferences = new AppSharedPreferences(aCtx);

        //Check the DesignType
        switch (list.get(position).getDesign_type()){
            case "HC1":
                String Hc1_title = list.get(position).getFormatted_title();
                String Hc1_icon = list.get(position).getIcon();
                String Hc1_url  = list.get(position).getUrl();
                ((CardsAdapter.HC1_Layout)holder).setData(Hc1_title,Hc1_icon,aCtx,Hc1_url);
                break;

            case "HC3":
                String Hc3_title = list.get(position).getFormatted_title();
                String Hc3_des = list.get(position).getFormatted_description();
                String Hc3_color_code = list.get(position).getBg_color();
                String Hc3_img = list.get(position).getBg_image();
                String Hc3_url  = list.get(position).getUrl();
                String btn_text = list.get(position).getModel().getText();
                String btn_bg_color = list.get(position).getModel().getBg_color();
                String btn_text_color = list.get(position).getModel().getText_color();
                String btn_url = list.get(position).getModel().getUrl();
                List<CardModel.EntitiesModel> index = list.get(position).getEntitiesModel();
                ((CardsAdapter.HC3_Layout)holder).setData(Hc3_title,Hc3_color_code,Hc3_img,aCtx,Hc3_url,btn_text,btn_bg_color,btn_text_color,btn_url,index,appSharedPreferences,Hc3_des);
                break;

            case "HC5":
                String Hc5_title = list.get(position).getName();
                String Hc5_color_code = list.get(position).getBg_color();
                String bg_image = list.get(position).getBg_image();
                String Hc5_url  = list.get(position).getUrl();
                ((CardsAdapter.HC5_Layout)holder).setData(Hc5_title,Hc5_color_code,bg_image,aCtx,Hc5_url);
                break;

            case "HC6":
                String Hc6_title = list.get(position).getFormatted_title();
                String Hc6_bg_image = list.get(position).getIcon();
                String Hc6_url  = list.get(position).getUrl();
                List<CardModel.EntitiesModel> Hc6_list = list.get(position).getEntitiesModel();
                ((CardsAdapter.HC6_Layout)holder).setData(Hc6_title,Hc6_bg_image,aCtx,Hc6_url,Hc6_list);
                break;

            case "HC9":
                String Hc9_img = list.get(position).getBg_image();
                String Hc9_url  = list.get(position).getUrl();
                ((CardsAdapter.HC9_Layout)holder).setData(Hc9_img,aCtx,Hc9_url);
                break;

        }


    }

    //--------------------------------HC1_LAYOUT----------------------------------------//
    static class HC1_Layout extends RecyclerView.ViewHolder{
        TextView title;
        CardView big_card;
        ImageView item_image;


        public HC1_Layout(@NotNull View itemView){
            super(itemView);
            title = itemView.findViewById(R.id.item_text_0);
            big_card = itemView.findViewById(R.id.big_card);
            item_image = itemView.findViewById(R.id.item_image);

        }
        private void setData(String stringTitle,String image,Context aCtx,String url){
            title.setText(stringTitle);
            Glide.with(aCtx).load(image).into(item_image);
            big_card.setOnClickListener(v -> {
                Intent intent = new Intent(aCtx, WebActivity.class);
                intent.putExtra("url",url);
                aCtx.startActivity(intent);
            });
        }
    }

    //--------------------------------HC3_LAYOUT----------------------------------------//
    static class HC3_Layout extends RecyclerView.ViewHolder{
        TextView title,desc,action_btn_text;
        ImageView img;
        CardView big_card,action_btn,btn_remindLater,btn_dismissNow;


        public HC3_Layout(@NotNull View itemView){
            super(itemView);
            title = itemView.findViewById(R.id.item_text_0);
            desc = itemView.findViewById(R.id.item_text_2);
            big_card = itemView.findViewById(R.id.big_card);
            img = itemView.findViewById(R.id.img);
            action_btn = itemView.findViewById(R.id.card_action_btn);
            action_btn_text = itemView.findViewById(R.id.card_action_btn_text);
            btn_remindLater = itemView.findViewById(R.id.card_remind_later);
            btn_dismissNow = itemView.findViewById(R.id.card_dismiss_now);
        }

        private void setData(String fTitle, String colorCode, String image, Context aCtx, String url2, String btn_text, String btn_bg_color, String btn_text_color, String url,
                             List<CardModel.EntitiesModel> index, AppSharedPreferences appSharedPreferences, String fDesc){

            for (CardModel.EntitiesModel i : index) {
                fTitle = fTitle.replaceFirst("\\{\\}", i.getText());
            }

            title.setText(fTitle);
            desc.setText(fDesc);

            Glide.with(aCtx).load(image).into(img);
            action_btn_text.setText(btn_text);

            if (appSharedPreferences.getDismissNow()!=null && appSharedPreferences.getDismissNow().equals("dismissNow1")){
                btn_dismissNow.setVisibility(View.GONE);
            }
            if (appSharedPreferences.getRemindLater()!=null && appSharedPreferences.getRemindLater().equals("remindLater1")) {
                btn_remindLater.setVisibility(View.VISIBLE);
                big_card.animate().translationXBy(300f).setDuration(800).start();
            }


            big_card.setOnLongClickListener(v -> {
                if (isClicked){
                    big_card.animate().translationXBy(300f).setDuration(800);
                }else {
                    big_card.animate().translationXBy(-300f).setDuration(800);
                }
                isClicked = !isClicked;

                return false;
            });


            PushDownAnim.setPushDownAnimTo(btn_remindLater).setOnClickListener(v -> {
                        appSharedPreferences.setRemindLater("remindLater1");
                        btn_remindLater.setVisibility(View.GONE);
            });


            PushDownAnim.setPushDownAnimTo(btn_dismissNow).setOnClickListener(v -> {
                appSharedPreferences.setDismissNow("dismissNow1");
                btn_dismissNow.setVisibility(View.GONE);
            });


            PushDownAnim.setPushDownAnimTo(action_btn).setOnClickListener(v -> {
                Intent intent = new Intent(aCtx, WebActivity.class);
                intent.putExtra("url",url);
                aCtx.startActivity(intent);
            });


            try {
                big_card.setCardBackgroundColor(Color.parseColor(colorCode));
                action_btn.setCardBackgroundColor(Color.parseColor(btn_bg_color));
                action_btn_text.setTextColor(Color.parseColor(btn_text_color));

            }catch (IndexOutOfBoundsException e){
                e.printStackTrace();
            }
        }
    }

    //--------------------------------HC5_LAYOUT----------------------------------------//
    static class HC5_Layout extends RecyclerView.ViewHolder{
        TextView title;
        ImageView img;
        CardView big_card;

        public HC5_Layout(@NotNull View itemView){
            super(itemView);
            title = itemView.findViewById(R.id.item_text_0);
            img = itemView.findViewById(R.id.img);
            big_card = itemView.findViewById(R.id.big_card);

        }
        private void setData(String stringTitle,String colorCode,String image,Context aCtx,String url){
            title.setText(stringTitle);
            Glide.with(aCtx).load(image).into(img);
            try {
                big_card.setCardBackgroundColor(Color.parseColor(colorCode));
            }catch (IndexOutOfBoundsException e){
                e.printStackTrace();
            }
            big_card.setOnClickListener(v -> {
                Intent intent = new Intent(aCtx, WebActivity.class);
                intent.putExtra("url",url);
                aCtx.startActivity(intent);
            });
        }
    }

    //--------------------------------HC6_LAYOUT----------------------------------------//
    static class HC6_Layout extends RecyclerView.ViewHolder{
        TextView title;
        ImageView img;
        CardView bigCard;

        public HC6_Layout(@NotNull View itemView){
            super(itemView);
            title = itemView.findViewById(R.id.item_text_0);
            img = itemView.findViewById(R.id.img);
            bigCard = itemView.findViewById(R.id.big_card);

        }
        private void setData(String stringTitle,String image,Context aCtx,String url,List<CardModel.EntitiesModel> entitiesModelList){

            for (CardModel.EntitiesModel i : entitiesModelList) {
                stringTitle = stringTitle.replaceFirst("\\{\\}",i.getText());
            }
            title.setText(stringTitle);

            Glide.with(aCtx).load(image).into(img);

            bigCard.setOnClickListener(v -> {
                Intent intent = new Intent(aCtx, WebActivity.class);
                intent.putExtra("url",url);
                aCtx.startActivity(intent);
            });

        }
    }

    //--------------------------------HC9_LAYOUT----------------------------------------//
    static class HC9_Layout extends RecyclerView.ViewHolder{
        ImageView bg_card;

        public HC9_Layout(@NotNull View itemView){
            super(itemView);
            bg_card = itemView.findViewById(R.id.bg_card);

        }
        private void setData(String image,Context aCtx,String url){

            Glide.with(aCtx).load(image).into(bg_card);
            bg_card.setOnClickListener(v -> {
                Intent intent = new Intent(aCtx, WebActivity.class);
                intent.putExtra("url",url);
                aCtx.startActivity(intent);
            });
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    @Override
    public int getItemViewType(int position) {
        switch (list.get(position).getDesign_type()) {
            case "HC1":
                return TYPE_HC1;
            case "HC3":
                return TYPE_HC3;
            case "HC5":
                return TYPE_HC5;
            case "HC6":
                return TYPE_HC6;
            case "HC9":
                return TYPE_HC9;
            default:
                return -1;
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public void setHasStableIds(boolean hasStableIds) {
        super.setHasStableIds(hasStableIds);
    }


}

