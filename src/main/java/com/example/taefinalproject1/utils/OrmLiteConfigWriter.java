package com.example.taefinalproject1.utils;

import com.example.taefinalproject1.persistence.LocalUser;
import com.example.taefinalproject1.persistence.LocalUsersLeagueAccounts;
import com.example.taefinalproject1.persistence.teambuilder.Champion;
import com.example.taefinalproject1.persistence.teambuilder.Player;
import com.example.taefinalproject1.persistence.teambuilder.Team;
import com.j256.ormlite.android.apptools.OrmLiteConfigUtil;

import java.io.File;

/**
 * Created by TAE_user2 on 21/01/2016.
 */
public class OrmLiteConfigWriter extends OrmLiteConfigUtil {
    /*
    For some time we have been struggling with DAO startup issues under Android that we thought were due to ORMLite object bandwidth. Although improvements and DAO caching has been made, creating a couple of DAOs when your application starts can still take too long and generate far too much garbage collection activity. Turns out that one of the major culprits is some ugly code down in the Android OS – especially in Method.equals(). Because annotations use this method, looking up annotation values is extremely expensive, often garbage collecting thousands of objects and megabytes of space. Android knows about the issues and a fix has been made but we have no idea when these performance improvements will make it into an Android release.

We have made a couple of changes in ORMLite to help work around this issue. Firstly, we added some reflection hacks to work around these problems in the short run. Annotations using this mechanism run 20 times faster than with the native Android calls.

With a little bit of work (and some caveats), you can remove all annotation work from your application and make DAO creation an extremely fast operation. ORMLite supports the loading of the data configurations from a text configuration file. When a DAO is created, these configurations will be used, removing the need for any annotation method calls entirely.
     */
    private static final Class<?>[] CLASSES = new Class[] {
            LocalUser.class,
            LocalUsersLeagueAccounts.class,
            Player.class,
            Champion.class,
            Team.class
    };
    public static void main(String[] args) throws Exception {
        writeConfigFile(new File("app/src/main/res/raw/ormlite_config.txt"), CLASSES);
    }
}
