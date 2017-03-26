package com.alexandre.baccus.models;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by alexandre on 3/1/17.
 */

public class Wine implements Serializable{
    /**
     * Atributos
     */
    private String mType = null;
    private Bitmap mPhoto = null;
    private String mPhotoURL = null;
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
                String wineCompanyWeb, String notes, String photoUrl, int rating) {
        mType = type;
        mPhotoURL = photoUrl;
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

    public Bitmap getPhoto() {
        if (mPhoto == null) {
            // Nos bajamos la imagen
            mPhoto = getBitmapFromURL(getPhotoURL());
        }
        return mPhoto;
    }

    public String getPhotoURL() {
        return mPhotoURL;
    }

    public String getType() {
        return mType;
    }

    public void setType(String type) {
        mType = type;
    }

    public String getGrape(int index) {
        return this.mGrapes.get(index);
    }

    public int getGrapesCount() {
        return this.mGrapes.size();
    }

    /**
     * Método para añadir una uva
     */
    public void addGrape(String grape) {
        this.mGrapes.add(grape);
    }

    @Override
    public String toString() {
        return getName();
    }

    /**
     * Método para bajar una ImageView de una URL
     */
    private Bitmap getBitmapFromURL(String url) {
        InputStream in = null;
        try {
            in = new java.net.URL(url).openStream();
            return BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            Log.e("Baccus", "Error downloading image", e);
            return null;
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {}
        }
    }


}
