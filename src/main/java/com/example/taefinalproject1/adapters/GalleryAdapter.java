package com.example.taefinalproject1.adapters;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.taefinalproject1.R;
import com.example.taefinalproject1.constants.Constants;
import com.squareup.picasso.Picasso;

import java.util.LinkedList;

/**
 * Created by TAE_user2 on 15/01/2016.
 */
public class GalleryAdapter extends BaseAdapter {
    private Context mContext;
    private Integer[] mThumbIds;
    private LinkedList<Integer> tmp = new LinkedList<>();
    public GalleryAdapter(Context c) {
        mContext = c;
        this.mThumbIds = getmThumbIds();
    }

    public int getCount() {
        return mThumbIds.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
//            imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
//            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
//            imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (ImageView) convertView;
        }
        Log.i(Constants.TAG, "getView: "+mThumbIds[position]);
        // TODO: 20/01/2016 java.lang.IllegalArgumentException: Resource ID must not be zero. appears at Irelia. 
        Picasso.with(mContext)
                .load(mThumbIds[position])
                .placeholder(R.drawable.place_holder)
                .error(R.drawable.error)
//                .resize(100, 100).centerCrop()
                .into(imageView);

//        imageView.setImageResource(mThumbIds[position]);
        return imageView;
    }

    private Integer[] getmThumbIds(){
        // TODO: 18/01/2016 Implement thumbnailIds for Champion Selection Screen
        LinkedList<String> lof = new LinkedList<>();
        lof.add("aatrox");
        lof.add("ahri");
        lof.add("akali");
        lof.add("alistar");
        lof.add("amumu");
        lof.add("anivia");
        lof.add("annie");
        lof.add("ashe");
        ///////////////////
        lof.add("blitzcrank");
        lof.add("brand");
        lof.add("braum");
        ///////////////////
        lof.add("caitlyn");
        lof.add("cassiopeia");
        lof.add("chogath");
        lof.add("corki");
        ///////////////////
        lof.add("darius");
        lof.add("diana");
        lof.add("draven");
        lof.add("drmundo");
        ///////////////////
        lof.add("ekko");
        lof.add("elise");
        lof.add("evelynn");
        lof.add("ezreal");
        ///////////////////
        lof.add("fiddlesticks");
        lof.add("fiora");
        lof.add("fizz");
        ///////////////////
        lof.add("galio");
        lof.add("gangplank");
        lof.add("garen");
        lof.add("gnar");
        lof.add("gragas");
        lof.add("graves");
        ////////////////////
        lof.add("hecarim");
        lof.add("heimerdinger");
        ////////////////////
        lof.add("illaoi");
        lof.add("irelia");
        ////////////////////
        lof.add("janna");
        lof.add("jarvaniv");
        lof.add("jax");
        lof.add("jayce");
        lof.add("jinx");
        ////////////////////
        lof.add("kalista");
        lof.add("karma");
        lof.add("karthus");
        lof.add("kassadin");
        lof.add("katarina");
        lof.add("kayle");
        lof.add("khazix");
        lof.add("kindred");
        lof.add("kogmaw");
        ////////////////////
        lof.add("leblanc");
        lof.add("leesin");
        lof.add("leona");
        lof.add("lissandra");
        lof.add("lucian");
        lof.add("lulu");
        lof.add("lux");
        ////////////////////
        lof.add("malphite");
        lof.add("malzahar");
        lof.add("maokai");
        lof.add("masteryi");
        lof.add("missfortune");
        lof.add("monkeyking");
        lof.add("mordekaiser");
        ////////////////////
        lof.add("nami");
        lof.add("nasus");
        lof.add("nautilus");
        lof.add("nidalee");
        lof.add("nocturne");
        lof.add("nunu");
        ////////////////////
        lof.add("olaf");
        lof.add("orianna");
        ////////////////////
        lof.add("pantheon");
        lof.add("poppy");
        ////////////////////
        lof.add("quinn");
        ////////////////////
        lof.add("rammus");
        lof.add("reksai");
        lof.add("renekton");
        lof.add("rengar");
        lof.add("riven");
        lof.add("rumble");
        lof.add("ryze");
        ////////////////////
        lof.add("sejuani");
        lof.add("shaco");
        lof.add("shen");
        lof.add("shyvana");
        lof.add("singed");
        lof.add("sion");
        lof.add("sivir");
        lof.add("skarner");
        lof.add("sona");
        lof.add("soraka");
        lof.add("swain");
        lof.add("syndra");
        ////////////////////
        lof.add("tahmkench");
        lof.add("talon");
        lof.add("taric");
        lof.add("teemo");
        lof.add("thresh");
        lof.add("tristana");
        lof.add("trundle");
        lof.add("tryndamere");
        lof.add("twistedfate");
        lof.add("twitch");
        ////////////////////
        lof.add("udyr");
        lof.add("urgot");
        ////////////////////
        lof.add("varus");
        lof.add("vayne");
        lof.add("veigar");
        lof.add("velkoz");
        lof.add("vi");
        lof.add("viktor");
        lof.add("vladimir");
        lof.add("volibear");
        ////////////////////
        lof.add("warwick");
        ////////////////////
        lof.add("xerath");
        lof.add("xinzhao");
        ////////////////////
        lof.add("yasuo");
        lof.add("yorick");
        ////////////////////
        lof.add("zac");
        lof.add("ziggs");
        lof.add("zilean");
        lof.add("zyra");
        Integer[] ret = new Integer[lof.size()];

        for (int i = 0; i < ret.length; i++) {
            ret[i] = a(lof.get(i));
            Log.i(Constants.TAG, "getmThumbIds: "+lof.get(i) + "'s id is: "+ret[i]);
        }

        return ret;
    }
    private Integer a(String filename){
        return mContext.getResources().getIdentifier(filename, "drawable", mContext.getPackageName());
    }
    private void b(String filename){
       tmp.add(a(filename));
    }

    // TODO: 19/01/2016 Use retrofit to get a list of filenames
    private void c(LinkedList<String> listOfFilename){
        for(String s : listOfFilename){
            b(s);
        }
    }
}
