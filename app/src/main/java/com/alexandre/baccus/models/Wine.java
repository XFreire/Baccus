package com.alexandre.baccus.models;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by alexandre on 3/1/17.
 */

public class Wine {
    /**
     * Atributos
     */
    private String mType = null;
    private int mPhoto = 0;
    private String mWineCompanyWeb = null;
    private String mNotes = null;
    private String mOrigin = null;
    private int mRating = 0; // 0 to 5
    List<String> mGrapes = new LinkedList<String>();
    private String mName = null;
    private String mWineCompanyName = null;



    /**
     * Constructor
     */
    public Wine(String name, String wineCompanyName, String type, String origin,
                String wineCompanyWeb, String notes, int photo, int rating) {
        mType = type;
        mPhoto = photo;
        mWineCompanyWeb = wineCompanyWeb;
        mNotes = notes;
        mOrigin = origin;
        mRating = rating;
        mName = name;
        mWineCompanyName = wineCompanyName;
    }

    /**
     * Getters and setters
     */
    public String getWineCompanyName() {
        return mWineCompanyName;
    }

    public void setWineCompanyName(String wineCompanyName) {
        mWineCompanyName = wineCompanyName;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public int getRating() {
        return mRating;
    }

    public void setRating(int rating) {
        mRating = rating;
    }

    public String getOrigin() {
        return mOrigin;
    }

    public void setOrigin(String origin) {
        mOrigin = origin;
    }

    public String getNotes() {
        return mNotes;
    }

    public void setNotes(String notes) {
        mNotes = notes;
    }

    public String getWineCompanyWeb() {
        return mWineCompanyWeb;
    }

    public void setWineCompanyWeb(String wineCompanyWeb) {
        mWineCompanyWeb = wineCompanyWeb;
    }

    public int getPhoto() {
        return mPhoto;
    }

    public void setPhoto(int photo) {
        mPhoto = photo;
    }

    public String getType() {
        return mType;
    }

    public void setType(String type) {
        mType = type;
    }
}
