package com.sharesongs.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sharesongs.entity.Song;

import java.lang.reflect.Type;
import java.util.List;


public class JsonUtils {
	public static String SerializerToJson(List<Song> Data) {
        Gson gson = new Gson();
        String jsonString = gson.toJson(Data);
        return jsonString;
    }
	public static List<Song> getInfosFromJson(String json)
      {
		Gson gson = new Gson();
		Type listType = new TypeToken<List<Song>>() { }.getType();
		List<Song> Infos = gson.fromJson(json, listType);
		return Infos;
	}
}
