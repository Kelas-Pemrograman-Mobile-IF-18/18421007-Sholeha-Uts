package com.sholeha.aplikasipenjualanhelm.server;

public class BaseURL {

    public static String baseUrl = "http://192.168.43.251:5050/";

    public static String login = baseUrl + "user/login";
    public static String register = baseUrl + "user/registrasi";

    // punya helm
    public static String dataHelm = baseUrl + "helm/datahelm";
    public static String editDataHelm = baseUrl + "helm/ubah/";
    public static String hapusData = baseUrl + "helm/hapus/";
    public static String inputHelm = baseUrl + "helm/input";

    //punya order
    public static String getDataTransaksi = baseUrl + "order/dataTransaksi/";
    public static String order = baseUrl + "order/insert";
    public static String lihatDataTransaksi = baseUrl + "order/lihatTransaksi";
    public static String editTransaksi = baseUrl + "order/ubah/";



}
