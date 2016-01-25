package com.example.taefinalproject1.adapters;

import android.content.Context;
import android.util.Log;

import com.example.taefinalproject1.apis.IRest;
import com.example.taefinalproject1.constants.Constants;
import com.example.taefinalproject1.constants.RestConstants;
import com.example.taefinalproject1.logic.EpochConversion;
import com.example.taefinalproject1.models.championtoidmappings.Championtoidmapping;
import com.example.taefinalproject1.models.nametoidmap.NameToIdMap;
import com.example.taefinalproject1.models.summonermatchesbyseason.MatchlistBySummoner;
import com.example.taefinalproject1.utils.MyPreferences;
import com.example.taefinalproject1.utils.RetrofitErrorHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.mime.TypedInput;

/**
 * Created by TAE_user2 on 20/01/2016.
 */
public class DataRestAdapter{
    private IRest irest;
    private Context context;

    public DataRestAdapter(Context context){
        this.context=context;
        makeRestAdapter(RestConstants.BASE_URL);
    }

    public DataRestAdapter(Context context, Boolean global){
        this.context = context;
        if(global){
            makeRestAdapter(RestConstants.GLOBAL_BASE_URL);
        }
        else {
            makeRestAdapter(RestConstants.BASE_URL);
        }
    }
    private void makeRestAdapter(String base_url){
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint(base_url)
                .build();
        irest = restAdapter.create(IRest.class);
    }

    public void getIdBySummonerName(String region, String summonerName, String api_key){
        irest.getIdBySummonerName(region, summonerName, api_key,new Callback<NameToIdMap>() {
            @Override
            public void success(NameToIdMap nameToIdMap, Response response) {
                TypedInput input = response.getBody(); // get JSON as byte array
                String json = myToString(input); // parse byte array to String
//                JSONArray jsonarr = myToJSONArray(json); // parse String to JSONArray
                JSONObject j = myToJSONObject(json);
                JSONObject innerjsonobj = myInnerJSONObj(j);
//                JSONObject jsonobj = myGetFirst(json); // get first object in JSONArray
                String name = myGetName(innerjsonobj);
                int id = myGetID(innerjsonobj);
                Log.i(Constants.TAG, "success: name is " + name + " id is " + id);
                MyPreferences mypreferences = new MyPreferences();
                Log.i(Constants.TAG, "success: "+mypreferences.retrieveIntPreference("Riven", context));
                mypreferences.savePreference(name, id, context);
                Log.i(Constants.TAG, "success: Reached success");
            }

            @Override
            public void failure(RetrofitError error) {
                Log.i(Constants.TAG, "failure: "+ error);
                RetrofitErrorHandler reh = new RetrofitErrorHandler(context, error);
            }
            private String myGetName(JSONObject jsonobj){
                try {
                    return jsonobj.getString("name");
                } catch (JSONException e) {
                    Log.i(Constants.TAG, "myGetName: Exception");
                    e.printStackTrace();
                }
                Log.i(Constants.TAG, "myGetName: Should not reach here");
                return null;
            }
            private int myGetID(JSONObject jsonobj){
                try {
                    return jsonobj.getInt("id");
                } catch (JSONException e) {
                    Log.i(Constants.TAG, "myGetID: Exception");
                    e.printStackTrace();
                }
                Log.i(Constants.TAG, "myGetID: Should not reach here");
                return -9999;
            }
            private String myToString(TypedInput typedInput){
                BufferedReader reader = null;
                StringBuilder out = null;
                try {
                    reader = new BufferedReader(new InputStreamReader(typedInput.in()));
                    out = new StringBuilder();
                    String newLine = System.getProperty("line.separator");
                    String line;
                    while ((line = reader.readLine()) != null) {
                        out.append(line);
                        out.append(newLine);
                    }
                    Log.i(Constants.TAG, "success: "+ out.toString());
                } catch (IOException e) {
                    Log.i(Constants.TAG, "myToString: Exception");
                    e.printStackTrace();
                }
                return out.toString();
            }
            private JSONObject myToJSONObject(String json){
                JSONObject j = null;
                try {
                    j = new JSONObject(json);
                } catch (JSONException e) {
                    Log.i(Constants.TAG, "myToJSONObject: Exception");
                    e.printStackTrace();
                }
                return j;
            }
            private JSONArray myToJSONArray(String json){
                JSONArray jsonarr = null;
                try {
                    jsonarr = new JSONArray(json);
                } catch (JSONException e) {
                    Log.i(Constants.TAG, "myToJSONArray: Exception");
                    e.printStackTrace();
                }
                return jsonarr;
            }
            private JSONObject myGetFirst(JSONArray jsonarr){
                try {
                    return jsonarr.getJSONObject(0);
                } catch (JSONException e) {
                    Log.i(Constants.TAG, "getFirst: Exception");
                    e.printStackTrace();
                }
                return null;
            }
            private JSONObject myInnerJSONObj(JSONObject j){
                JSONObject p = null;
                try {
                    Object o = j.get(j.keys().next());
                    p = (JSONObject) o;
                    Log.i(Constants.TAG, "successP: "+p.get("name"));
                    Log.i(Constants.TAG, "successP: " +p.get("id"));
                } catch (JSONException e) {
                    Log.i(Constants.TAG, "myInnerJSONObj: Exception");
                    e.printStackTrace();
                }
                return p;
            }
        });
    }

    public void getChampionToIdMappings(String region, String api_key){
        irest.getChampionIds(region, api_key, new Callback<Championtoidmapping>() {
            @Override
            public void success(Championtoidmapping championtoidmapping, Response response) {
                MyPreferences mypreferences = new MyPreferences();
                Log.i(Constants.TAG, "success: " + championtoidmapping.getData().getAatrox().getName());
                Log.i(Constants.TAG, "success: " + championtoidmapping.getData().getAatrox().getId());
                mypreferences.savePreference(championtoidmapping.getData().getAatrox
                        ().getName(), championtoidmapping.getData().getAatrox
                        ().getId(), context);
                mypreferences.savePreference(championtoidmapping.getData().getAhri
                        ().getName(), championtoidmapping.getData().getAhri
                        ().getId(), context);
                mypreferences.savePreference(championtoidmapping.getData().getAkali
                        ().getName(), championtoidmapping.getData().getAkali
                        ().getId(), context);
                mypreferences.savePreference(championtoidmapping.getData().getAlistar
                        ().getName(), championtoidmapping.getData().getAlistar
                        ().getId(), context);
                mypreferences.savePreference(championtoidmapping.getData().getAmumu
                        ().getName(), championtoidmapping.getData().getAmumu
                        ().getId(), context);
                mypreferences.savePreference(championtoidmapping.getData().getAnivia
                        ().getName(), championtoidmapping.getData().getAnivia
                        ().getId(), context);
                mypreferences.savePreference(championtoidmapping.getData().getAnnie
                        ().getName(), championtoidmapping.getData().getAnnie
                        ().getId(), context);
                mypreferences.savePreference(championtoidmapping.getData().getAshe
                        ().getName(), championtoidmapping.getData().getAshe
                        ().getId(), context);
                mypreferences.savePreference(championtoidmapping.getData().getAzir
                        ().getName(), championtoidmapping.getData().getAzir
                        ().getId(), context);
                mypreferences.savePreference(championtoidmapping.getData().getBard
                        ().getName(), championtoidmapping.getData().getBard
                        ().getId(), context);
                mypreferences.savePreference(championtoidmapping.getData().getBlitzcrank
                        ().getName(), championtoidmapping.getData().getBlitzcrank
                        ().getId(), context);
                mypreferences.savePreference(championtoidmapping.getData().getBrand
                        ().getName(), championtoidmapping.getData().getBrand
                        ().getId(), context);
                mypreferences.savePreference(championtoidmapping.getData().getBraum
                        ().getName(), championtoidmapping.getData().getBraum
                        ().getId(), context);
                mypreferences.savePreference(championtoidmapping.getData().getCaitlyn
                        ().getName(), championtoidmapping.getData().getCaitlyn
                        ().getId(), context);
                mypreferences.savePreference(championtoidmapping.getData().getCassiopeia
                        ().getName(), championtoidmapping.getData().getCassiopeia
                        ().getId(), context);
                mypreferences.savePreference(championtoidmapping.getData().getChogath
                        ().getName(), championtoidmapping.getData().getChogath
                        ().getId(), context);
                mypreferences.savePreference(championtoidmapping.getData().getCorki
                        ().getName(), championtoidmapping.getData().getCorki
                        ().getId(), context);
                mypreferences.savePreference(championtoidmapping.getData().getDarius
                        ().getName(), championtoidmapping.getData().getDarius
                        ().getId(), context);
                mypreferences.savePreference(championtoidmapping.getData().getDiana
                        ().getName(), championtoidmapping.getData().getDiana
                        ().getId(), context);
                mypreferences.savePreference(championtoidmapping.getData().getDraven
                        ().getName(), championtoidmapping.getData().getDraven
                        ().getId(), context);
                mypreferences.savePreference(championtoidmapping.getData().getDrMundo
                        ().getName(), championtoidmapping.getData().getDrMundo
                        ().getId(), context);
                mypreferences.savePreference(championtoidmapping.getData().getEkko
                        ().getName(), championtoidmapping.getData().getEkko
                        ().getId(), context);
                mypreferences.savePreference(championtoidmapping.getData().getElise
                        ().getName(), championtoidmapping.getData().getElise
                        ().getId(), context);
                mypreferences.savePreference(championtoidmapping.getData().getEvelynn
                        ().getName(), championtoidmapping.getData().getEvelynn
                        ().getId(), context);
                mypreferences.savePreference(championtoidmapping.getData().getEzreal
                        ().getName(), championtoidmapping.getData().getEzreal
                        ().getId(), context);
                mypreferences.savePreference(championtoidmapping.getData().getFiddleSticks
                        ().getName(), championtoidmapping.getData().getFiddleSticks
                        ().getId(), context);
                mypreferences.savePreference(championtoidmapping.getData().getFiora
                        ().getName(), championtoidmapping.getData().getFiora
                        ().getId(), context);
                mypreferences.savePreference(championtoidmapping.getData().getFizz
                        ().getName(), championtoidmapping.getData().getFizz
                        ().getId(), context);
                mypreferences.savePreference(championtoidmapping.getData().getGalio
                        ().getName(), championtoidmapping.getData().getGalio
                        ().getId(), context);
                mypreferences.savePreference(championtoidmapping.getData().getGangplank
                        ().getName(), championtoidmapping.getData().getGangplank
                        ().getId(), context);
                mypreferences.savePreference(championtoidmapping.getData().getGaren
                        ().getName(), championtoidmapping.getData().getGaren
                        ().getId(), context);
                mypreferences.savePreference(championtoidmapping.getData().getGnar
                        ().getName(), championtoidmapping.getData().getGnar
                        ().getId(), context);
                mypreferences.savePreference(championtoidmapping.getData().getGragas
                        ().getName(), championtoidmapping.getData().getGragas
                        ().getId(), context);
                mypreferences.savePreference(championtoidmapping.getData().getGraves
                        ().getName(), championtoidmapping.getData().getGraves
                        ().getId(), context);
                mypreferences.savePreference(championtoidmapping.getData().getHecarim
                        ().getName(), championtoidmapping.getData().getHecarim
                        ().getId(), context);
                mypreferences.savePreference(championtoidmapping.getData().getHeimerdinger
                        ().getName(), championtoidmapping.getData().getHeimerdinger
                        ().getId(), context);
                mypreferences.savePreference(championtoidmapping.getData().getIllaoi
                        ().getName(), championtoidmapping.getData().getIllaoi
                        ().getId(), context);
                mypreferences.savePreference(championtoidmapping.getData().getIrelia
                        ().getName(), championtoidmapping.getData().getIrelia
                        ().getId(), context);
                mypreferences.savePreference(championtoidmapping.getData().getJanna
                        ().getName(), championtoidmapping.getData().getJanna
                        ().getId(), context);
                mypreferences.savePreference(championtoidmapping.getData().getJarvanIV
                        ().getName(), championtoidmapping.getData().getJarvanIV
                        ().getId(), context);
                mypreferences.savePreference(championtoidmapping.getData().getJax
                        ().getName(), championtoidmapping.getData().getJax
                        ().getId(), context);
                mypreferences.savePreference(championtoidmapping.getData().getJayce
                        ().getName(), championtoidmapping.getData().getJayce
                        ().getId(), context);
                mypreferences.savePreference(championtoidmapping.getData().getJinx
                        ().getName(), championtoidmapping.getData().getJinx
                        ().getId(), context);
                mypreferences.savePreference(championtoidmapping.getData().getKalista
                        ().getName(), championtoidmapping.getData().getKalista
                        ().getId(), context);
                mypreferences.savePreference(championtoidmapping.getData().getKarma
                        ().getName(), championtoidmapping.getData().getKarma
                        ().getId(), context);
                mypreferences.savePreference(championtoidmapping.getData().getKarthus
                        ().getName(), championtoidmapping.getData().getKarthus
                        ().getId(), context);
                mypreferences.savePreference(championtoidmapping.getData().getKassadin
                        ().getName(), championtoidmapping.getData().getKassadin
                        ().getId(), context);
                mypreferences.savePreference(championtoidmapping.getData().getKatarina
                        ().getName(), championtoidmapping.getData().getKatarina
                        ().getId(), context);
                mypreferences.savePreference(championtoidmapping.getData().getKayle
                        ().getName(), championtoidmapping.getData().getKayle
                        ().getId(), context);
                mypreferences.savePreference(championtoidmapping.getData().getKennen
                        ().getName(), championtoidmapping.getData().getKennen
                        ().getId(), context);
                mypreferences.savePreference(championtoidmapping.getData().getKhazix
                        ().getName(), championtoidmapping.getData().getKhazix
                        ().getId(), context);
                mypreferences.savePreference(championtoidmapping.getData().getKindred
                        ().getName(), championtoidmapping.getData().getKindred
                        ().getId(), context);
                mypreferences.savePreference(championtoidmapping.getData().getKogMaw
                        ().getName(), championtoidmapping.getData().getKogMaw
                        ().getId(), context);
                mypreferences.savePreference(championtoidmapping.getData().getLeblanc
                        ().getName(), championtoidmapping.getData().getLeblanc
                        ().getId(), context);
                mypreferences.savePreference(championtoidmapping.getData().getLeeSin
                        ().getName(), championtoidmapping.getData().getLeeSin
                        ().getId(), context);
                mypreferences.savePreference(championtoidmapping.getData().getLeona
                        ().getName(), championtoidmapping.getData().getLeona
                        ().getId(), context);
                mypreferences.savePreference(championtoidmapping.getData().getLissandra
                        ().getName(), championtoidmapping.getData().getLissandra
                        ().getId(), context);
                mypreferences.savePreference(championtoidmapping.getData().getLucian
                        ().getName(), championtoidmapping.getData().getLucian
                        ().getId(), context);
                mypreferences.savePreference(championtoidmapping.getData().getLulu
                        ().getName(), championtoidmapping.getData().getLulu
                        ().getId(), context);
                mypreferences.savePreference(championtoidmapping.getData().getLux
                        ().getName(), championtoidmapping.getData().getLux
                        ().getId(), context);
                mypreferences.savePreference(championtoidmapping.getData().getMalphite
                        ().getName(), championtoidmapping.getData().getMalphite
                        ().getId(), context);
                mypreferences.savePreference(championtoidmapping.getData().getMalzahar
                        ().getName(), championtoidmapping.getData().getMalzahar
                        ().getId(), context);
                mypreferences.savePreference(championtoidmapping.getData().getMaokai
                        ().getName(), championtoidmapping.getData().getMaokai
                        ().getId(), context);
                mypreferences.savePreference(championtoidmapping.getData().getMasterYi
                        ().getName(), championtoidmapping.getData().getMasterYi
                        ().getId(), context);
                mypreferences.savePreference(championtoidmapping.getData().getMissFortune
                        ().getName(), championtoidmapping.getData().getMissFortune
                        ().getId(), context);
                mypreferences.savePreference(championtoidmapping.getData().getMonkeyKing
                        ().getName(), championtoidmapping.getData().getMonkeyKing
                        ().getId(), context);
                mypreferences.savePreference(championtoidmapping.getData().getMordekaiser
                        ().getName(), championtoidmapping.getData().getMordekaiser
                        ().getId(), context);
                mypreferences.savePreference(championtoidmapping.getData().getMorgana
                        ().getName(), championtoidmapping.getData().getMorgana
                        ().getId(), context);
                mypreferences.savePreference(championtoidmapping.getData().getNami
                        ().getName(), championtoidmapping.getData().getNami
                        ().getId(), context);
                mypreferences.savePreference(championtoidmapping.getData().getNasus
                        ().getName(), championtoidmapping.getData().getNasus
                        ().getId(), context);
                mypreferences.savePreference(championtoidmapping.getData().getNautilus
                        ().getName(), championtoidmapping.getData().getNautilus
                        ().getId(), context);
                mypreferences.savePreference(championtoidmapping.getData().getNidalee
                        ().getName(), championtoidmapping.getData().getNidalee
                        ().getId(), context);
                mypreferences.savePreference(championtoidmapping.getData().getNocturne
                        ().getName(), championtoidmapping.getData().getNocturne
                        ().getId(), context);
                mypreferences.savePreference(championtoidmapping.getData().getNunu
                        ().getName(), championtoidmapping.getData().getNunu
                        ().getId(), context);
                mypreferences.savePreference(championtoidmapping.getData().getOlaf
                        ().getName(), championtoidmapping.getData().getOlaf
                        ().getId(), context);
                mypreferences.savePreference(championtoidmapping.getData().getOrianna
                        ().getName(), championtoidmapping.getData().getOrianna
                        ().getId(), context);
                mypreferences.savePreference(championtoidmapping.getData().getPantheon
                        ().getName(), championtoidmapping.getData().getPantheon
                        ().getId(), context);
                mypreferences.savePreference(championtoidmapping.getData().getPoppy
                        ().getName(), championtoidmapping.getData().getPoppy
                        ().getId(), context);
                mypreferences.savePreference(championtoidmapping.getData().getQuinn
                        ().getName(), championtoidmapping.getData().getQuinn
                        ().getId(), context);
                mypreferences.savePreference(championtoidmapping.getData().getRammus
                        ().getName(), championtoidmapping.getData().getRammus
                        ().getId(), context);
                mypreferences.savePreference(championtoidmapping.getData().getRekSai
                        ().getName(), championtoidmapping.getData().getRekSai
                        ().getId(), context);
                mypreferences.savePreference(championtoidmapping.getData().getRenekton
                        ().getName(), championtoidmapping.getData().getRenekton
                        ().getId(), context);
                mypreferences.savePreference(championtoidmapping.getData().getRengar
                        ().getName(), championtoidmapping.getData().getRengar
                        ().getId(), context);
                mypreferences.savePreference(championtoidmapping.getData().getRiven
                        ().getName(), championtoidmapping.getData().getRiven
                        ().getId(), context);
                mypreferences.savePreference(championtoidmapping.getData().getRumble
                        ().getName(), championtoidmapping.getData().getRumble
                        ().getId(), context);
                mypreferences.savePreference(championtoidmapping.getData().getRyze
                        ().getName(), championtoidmapping.getData().getRyze
                        ().getId(), context);
                mypreferences.savePreference(championtoidmapping.getData().getSejuani
                        ().getName(), championtoidmapping.getData().getSejuani
                        ().getId(), context);
                mypreferences.savePreference(championtoidmapping.getData().getShaco
                        ().getName(), championtoidmapping.getData().getShaco
                        ().getId(), context);
                mypreferences.savePreference(championtoidmapping.getData().getShen
                        ().getName(), championtoidmapping.getData().getShen
                        ().getId(), context);
                mypreferences.savePreference(championtoidmapping.getData().getShyvana
                        ().getName(), championtoidmapping.getData().getShyvana
                        ().getId(), context);
                mypreferences.savePreference(championtoidmapping.getData().getSinged
                        ().getName(), championtoidmapping.getData().getSinged
                        ().getId(), context);
                mypreferences.savePreference(championtoidmapping.getData().getSion
                        ().getName(), championtoidmapping.getData().getSion
                        ().getId(), context);
                mypreferences.savePreference(championtoidmapping.getData().getSivir
                        ().getName(), championtoidmapping.getData().getSivir
                        ().getId(), context);
                mypreferences.savePreference(championtoidmapping.getData().getSkarner
                        ().getName(), championtoidmapping.getData().getSkarner
                        ().getId(), context);
                mypreferences.savePreference(championtoidmapping.getData().getSona
                        ().getName(), championtoidmapping.getData().getSona
                        ().getId(), context);
                mypreferences.savePreference(championtoidmapping.getData().getSoraka
                        ().getName(), championtoidmapping.getData().getSoraka
                        ().getId(), context);
                mypreferences.savePreference(championtoidmapping.getData().getSwain
                        ().getName(), championtoidmapping.getData().getSwain
                        ().getId(), context);
                mypreferences.savePreference(championtoidmapping.getData().getSyndra
                        ().getName(), championtoidmapping.getData().getSyndra
                        ().getId(), context);
                mypreferences.savePreference(championtoidmapping.getData().getTahmKench
                        ().getName(), championtoidmapping.getData().getTahmKench
                        ().getId(), context);
                mypreferences.savePreference(championtoidmapping.getData().getTalon
                        ().getName(), championtoidmapping.getData().getTalon
                        ().getId(), context);
                mypreferences.savePreference(championtoidmapping.getData().getTaric
                        ().getName(), championtoidmapping.getData().getTaric
                        ().getId(), context);
                mypreferences.savePreference(championtoidmapping.getData().getTeemo
                        ().getName(), championtoidmapping.getData().getTeemo
                        ().getId(), context);
                mypreferences.savePreference(championtoidmapping.getData().getThresh
                        ().getName(), championtoidmapping.getData().getThresh
                        ().getId(), context);
                mypreferences.savePreference(championtoidmapping.getData().getTristana
                        ().getName(), championtoidmapping.getData().getTristana
                        ().getId(), context);
                mypreferences.savePreference(championtoidmapping.getData().getTrundle
                        ().getName(), championtoidmapping.getData().getTrundle
                        ().getId(), context);
                mypreferences.savePreference(championtoidmapping.getData().getTryndamere
                        ().getName(), championtoidmapping.getData().getTryndamere
                        ().getId(), context);
                mypreferences.savePreference(championtoidmapping.getData().getTwistedFate
                        ().getName(), championtoidmapping.getData().getTwistedFate
                        ().getId(), context);
                mypreferences.savePreference(championtoidmapping.getData().getTwitch
                        ().getName(), championtoidmapping.getData().getTwitch
                        ().getId(), context);
                mypreferences.savePreference(championtoidmapping.getData().getUdyr
                        ().getName(), championtoidmapping.getData().getUdyr
                        ().getId(), context);
                mypreferences.savePreference(championtoidmapping.getData().getUrgot
                        ().getName(), championtoidmapping.getData().getUrgot
                        ().getId(), context);
                mypreferences.savePreference(championtoidmapping.getData().getVarus
                        ().getName(), championtoidmapping.getData().getVarus
                        ().getId(), context);
                mypreferences.savePreference(championtoidmapping.getData().getVayne
                        ().getName(), championtoidmapping.getData().getVayne
                        ().getId(), context);
                mypreferences.savePreference(championtoidmapping.getData().getVeigar
                        ().getName(), championtoidmapping.getData().getVeigar
                        ().getId(), context);
                mypreferences.savePreference(championtoidmapping.getData().getVelkoz
                        ().getName(), championtoidmapping.getData().getVelkoz
                        ().getId(), context);
                mypreferences.savePreference(championtoidmapping.getData().getVi
                        ().getName(), championtoidmapping.getData().getVi
                        ().getId(), context);
                mypreferences.savePreference(championtoidmapping.getData().getViktor
                        ().getName(), championtoidmapping.getData().getViktor
                        ().getId(), context);
                mypreferences.savePreference(championtoidmapping.getData().getVladimir
                        ().getName(), championtoidmapping.getData().getVladimir
                        ().getId(), context);
                mypreferences.savePreference(championtoidmapping.getData().getVolibear
                        ().getName(), championtoidmapping.getData().getVolibear
                        ().getId(), context);
                mypreferences.savePreference(championtoidmapping.getData().getWarwick
                        ().getName(), championtoidmapping.getData().getWarwick
                        ().getId(), context);
                mypreferences.savePreference(championtoidmapping.getData().getXerath
                        ().getName(), championtoidmapping.getData().getXerath
                        ().getId(), context);
                mypreferences.savePreference(championtoidmapping.getData().getXinZhao
                        ().getName(), championtoidmapping.getData().getXinZhao
                        ().getId(), context);
                mypreferences.savePreference(championtoidmapping.getData().getYasuo
                        ().getName(), championtoidmapping.getData().getYasuo
                        ().getId(), context);
                mypreferences.savePreference(championtoidmapping.getData().getYorick
                        ().getName(), championtoidmapping.getData().getYorick
                        ().getId(), context);
                mypreferences.savePreference(championtoidmapping.getData().getZac
                        ().getName(), championtoidmapping.getData().getZac
                        ().getId(), context);
                mypreferences.savePreference(championtoidmapping.getData().getZed
                        ().getName(), championtoidmapping.getData().getZed
                        ().getId(), context);
                mypreferences.savePreference(championtoidmapping.getData().getZiggs
                        ().getName(), championtoidmapping.getData().getZiggs
                        ().getId(), context);
                mypreferences.savePreference(championtoidmapping.getData().getZilean
                        ().getName(), championtoidmapping.getData().getZilean
                        ().getId(), context);
                mypreferences.savePreference(championtoidmapping.getData().getZyra()
                        .getName(), championtoidmapping.getData().getZyra()
                        .getId(), context);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.i(Constants.TAG, "failure: " + error);
                RetrofitErrorHandler reh = new RetrofitErrorHandler(context, error);
            }
        });
    }

}
