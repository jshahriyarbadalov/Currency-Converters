package com.shahriyar.currencyconventer.utils;

import android.content.Context;

public class CurrencyUtil {
   public static int getImageIdByImageName(Context context, String imgName){
        String imageName = imgName.trim().toLowerCase();
        if (imageName.equals("try"))
            imageName = "try1";
        return context.getResources().getIdentifier(
                imageName,
                "drawable",
                context.getPackageName());
    }
}
