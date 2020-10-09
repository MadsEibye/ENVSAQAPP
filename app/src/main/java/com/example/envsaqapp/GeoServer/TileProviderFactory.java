package com.example.envsaqapp.GeoServer;

import android.util.Log;

import com.example.envsaqapp.GeoServer.GeoServerTileProvider;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Locale;

public class TileProviderFactory {
    public static GeoServerTileProvider getGeoServerTileProvider() {
//http://10.28.0.241:8088/gwc/service/tms/1.0.0/cite:lpdv2k12_kbh_no2@EPSG%3A900913@png/{z}/{x}/{-y}.png
        String baseURL = "10.28.0.241:8088";
        String version = "1.3.0";
        String request = "GetMap";
        String format = "image/png";
        String srs = "EPSG:900913";
        String service = "WMS";
        String width = "256";
        String height = "256";
        String styles = "";
        String layers = "wtx:road_hazards";

        final String URL_STRING = baseURL +
                "&LAYERS=" + layers +
                "&VERSION=" + version +
                "&SERVICE=" + service +
                "&REQUEST=" + request +
                "&TRANSPARENT=TRUE&STYLES=" + styles +
                "&FORMAT=" + format +
                "&SRS=" + srs +
                "&BBOX=%f,%f,%f,%f" +
                "&WIDTH=" + width +
                "&HEIGHT=" + height;


        GeoServerTileProvider tileProvider =
                new GeoServerTileProvider(256,256) {

                    @Override
                    public synchronized URL getTileUrl(int x, int y, int zoom) {
                        try {

                            double[] bbox = getBoundingBox(x, y, zoom);

                            String s = String.format(Locale.US, URL_STRING, bbox[MINX],
                                    bbox[MINY], bbox[MAXX], bbox[MAXY]);

                            Log.d("GeoServerTileURL", s);

                            URL url = null;

                            try {
                                url = new URL(s);
                            }
                            catch (MalformedURLException e) {
                                throw new AssertionError(e);
                            }

                            return url;
                        }
                        catch (RuntimeException e) {
                            Log.d("GeoServerTileException",
                                    "getTile x=" + x + ", y=" + y +
                                            ", zoomLevel=" + zoom +
                                            " raised an exception", e);
                            throw e;
                        }

                    }
                };
        return tileProvider;
    }
}
