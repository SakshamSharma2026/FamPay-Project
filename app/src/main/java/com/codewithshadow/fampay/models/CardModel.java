package com.codewithshadow.fampay.models;

import java.util.ArrayList;
import java.util.List;

public class CardModel {
    private String name;
    private String title;
    private String design_type;
    private String bg_color;
    private String bg_image;
    private boolean is_scrollable;

    public boolean isIs_scrollable() {
        return is_scrollable;
    }

    public void setIs_scrollable(boolean is_scrollable) {
        this.is_scrollable = is_scrollable;
    }

    public CtaModel getModel() {
        return model;
    }

    public void setModel(CtaModel model) {
        this.model = model;
    }

    CardModel.CtaModel model = new CtaModel();



    List<CardModel.EntitiesModel> entitiesModel = new ArrayList<>();

    public List<EntitiesModel> getEntitiesModel() {
        return entitiesModel;
    }

    public void setEntitiesModel(List<EntitiesModel> entitiesModel) {
        this.entitiesModel = entitiesModel;
    }

    public static class CtaModel{
        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getBg_color() {
            return bg_color;
        }

        public void setBg_color(String bg_color) {
            this.bg_color = bg_color;
        }

        public String getText_color() {
            return text_color;
        }

        public void setText_color(String text_color) {
            this.text_color = text_color;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        private String text;
        private String bg_color;
        private String text_color;
        private String url;
    }


    public static class EntitiesModel{
        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        private String text;
        private String color;
    }


    private String text_color;

    public String getText_color() {
        return text_color;
    }

    public void setText_color(String text_color) {
        this.text_color = text_color;
    }

    public String getBg_color() {
        return bg_color;
    }

    public void setBg_color(String bg_color) {
        this.bg_color = bg_color;
    }


    public String getBg_image() {
        return bg_image;
    }

    public void setBg_image(String bg_image) {
        this.bg_image = bg_image;
    }

    public String getDesign_type() {
        return design_type;
    }

    public void setDesign_type(String design_type) {
        this.design_type = design_type;
    }

    public String getFormatted_title() {
        return formatted_title;
    }

    public void setFormatted_title(String formatted_title) {
        this.formatted_title = formatted_title;
    }

    public String getFormatted_description() {
        return formatted_description;
    }

    public void setFormatted_description(String formatted_description) {
        this.formatted_description = formatted_description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    private String formatted_title;
    private String formatted_description;
    private String icon;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    private String url;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
