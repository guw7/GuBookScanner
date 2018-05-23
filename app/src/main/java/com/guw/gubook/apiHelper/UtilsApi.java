package com.guw.gubook.apiHelper;

public class UtilsApi {

    public static final String BASE_URL_API = "http://bukutamu-via.cloud.revoluz.io/G-API/API/";

    public static BaseApiService getAPIService() {
        return RetrofitClient.getClient(BASE_URL_API).create(BaseApiService.class);
    }
}
