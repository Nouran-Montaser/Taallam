package com.example.nouran.taallam;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;

import java.util.Locale;

public  final class  ChangeLanguage {
    public static Context  updateResources(Context context, String language) {
        Locale locale = new Locale(language);
        Locale.setDefault(locale);

        Resources res = context.getResources();
        Configuration config = new Configuration(res.getConfiguration());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            config.setLocale(locale);

            return context.createConfigurationContext(config);
        } else {
            Configuration configuration = res.getConfiguration();
            configuration.locale = locale;

            res.updateConfiguration(configuration, res.getDisplayMetrics());

        }

        return context;
    }

}
