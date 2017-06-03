package com.epicodus.smallsteps.services;

import android.util.Log;

import com.epicodus.smallsteps.Constants;
import com.epicodus.smallsteps.models.Group;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MeetupGroupService {

    public static final String MEETUP_SIGN_VALUE = "true";
    public static final String MEETUP_PHOTO_HOST_VALUE = "public";

    public static void getGroups(String searchText, String searchZip, Callback callback) {
        OkHttpClient client = new OkHttpClient.Builder().build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.MEETUP_BASE_URL).newBuilder();
        urlBuilder.addQueryParameter(Constants.MEETUP_SIGN_PARAMETER, MEETUP_SIGN_VALUE);
        urlBuilder.addQueryParameter(Constants.MEETUP_PHOTO_HOST_PARAMETER, MEETUP_PHOTO_HOST_VALUE);
        urlBuilder.addQueryParameter(Constants.MEETUP_ZIP_PARAMETER, searchZip);
        urlBuilder.addQueryParameter(Constants.MEETUP_TEXT_PARAMETER, searchText);
        urlBuilder.addQueryParameter(Constants.MEETUP_API_QUERY_PARAMETER, Constants.MEETUP_API_KEY);
        String url = urlBuilder.build().toString();

        Log.v("Meetup Group Service", url);

        url = Constants.MEETUP_BASE_URL +
                Constants.MEETUP_SIGN_PARAMETER + "=" + MEETUP_SIGN_VALUE + "&" +
                Constants.MEETUP_PHOTO_HOST_PARAMETER + "=" + MEETUP_PHOTO_HOST_VALUE + "&" +
                Constants.MEETUP_ZIP_PARAMETER + "=" + searchZip + "&" +
                Constants.MEETUP_TEXT_PARAMETER + "=" + searchText + "&" +
                Constants.MEETUP_API_QUERY_PARAMETER + "=" + Constants.MEETUP_API_KEY;

        Log.v("Meetup Group Service", url);

        Request request = new Request.Builder()
                .url(url)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);
    }

    public ArrayList<Group> processResults(Response response) {
        ArrayList<Group> groupList = new ArrayList<>();

        try {
            if(response.isSuccessful()) {
                String jsonData = response.body().string();
                JSONArray meetupJSON = new JSONArray(jsonData);

                for(int i = 0; i < meetupJSON.length(); i++) {
                    JSONObject groupJSON = meetupJSON.getJSONObject(i);

                    String name = groupJSON.getString("name");
                    String link = groupJSON.getString("link");
                    String category = groupJSON.getJSONObject("category").getString("name");
                    String city = groupJSON.getString("city");
                    String state = groupJSON.getString("state");
                    String country = groupJSON.getString("country");
                    String description = groupJSON.getString("description");
                    int memberCount = groupJSON.getInt("members");

                    Group group = new Group(name, link, category, city, state, country, description, memberCount);
                    groupList.add(group);
                }
            }
        } catch(IOException e) {
            e.printStackTrace();
        } catch(JSONException e) {
            e.printStackTrace();
        }

        return groupList;
    }
}
