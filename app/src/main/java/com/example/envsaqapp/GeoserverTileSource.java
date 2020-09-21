package com.example.envsaqapp;

import android.util.Log;

import org.osmdroid.tileprovider.tilesource.OnlineTileSourceBase;
import org.osmdroid.util.MapTileIndex;

import java.util.Locale;

import static org.osmdroid.util.MapTileIndex.getX;
import static org.osmdroid.util.MapTileIndex.getY;
import static org.osmdroid.util.MapTileIndex.getZoom;

public class GeoserverTileSource extends OnlineTileSourceBase {
                                        
    public static String[] TILE_URL = {"http://10.28.0.241:8088/geoserver/gwc/demo/cite:lpdv2k12_kbh_no2?gridSet=EPSG:4326&format=image/png"};

    /*public GeoserverTileSource(String aName, int aZoomMinLevel, int aZoomMaxLevel, int aTileSizePixels, String aImageFilenameEnding, String[] aBaseUrl) {
        super(aName, aZoomMinLevel, aZoomMaxLevel, aTileSizePixels, aImageFilenameEnding, aBaseUrl);
    }*/

    /*@Override
    public String getTileURLString(long pTile) {
        return TILE_URL[0] + "/" + getZoom(pTile) + "/" + getX(pTile) + "/" + getY(pTile);
    }*/
    // Web Mercator n/w corner of the map.
    private static final double[] TILE_ORIGIN = {-20037508.34789244, 20037508.34789244};
    //array indexes for that data
    private static final int ORIG_X = 0;
    private static final int ORIG_Y = 1; // "
    // Size of square world map in meters, using WebMerc projection.
    private static final double MAP_SIZE = 20037508.34789244 * 2;
    // array indexes for array to hold bounding boxes.
    protected static final int MINX = 0;
    protected static final int MAXX = 1;
    protected static final int MINY = 2;
    protected static final int MAXY = 3;
    private String layer="";
    /**
     * Constructor
     *
     * @param aName                a human-friendly name for this tile source
     * @param aBaseUrl             the base url(s) of the tile server used when constructing the url to download the tiles http://sedac.ciesin.columbia.edu/geoserver/wms
     */
    public GeoserverTileSource(String aName, String[] aBaseUrl, String layername) {
        super(aName, 0, 22, 256, "png", aBaseUrl);
    }
    final String WMS_FORMAT_STRING =
            "http://%s" +
                    "?service=WMS" +
                    "&version=1.1.1" +
                    "&request=GetMap" +
                    "&layers=%s" +
                    "&bbox=%f,%f,%f,%f" +
                    "&width=256" +
                    "&height=256" +
                    "&srs=EPSG:4326" +
                    "&format=image/png" +
                    "&transparent=true";
    // Return a web Mercator bounding box given tile x/y indexes and a zoom
    // level.
    protected double[] getBoundingBox(int x, int y, int zoom) {
        double tileSize = MAP_SIZE / Math.pow(2, zoom);
        double minx = TILE_ORIGIN[ORIG_X] + x * tileSize;
        double maxx = TILE_ORIGIN[ORIG_X] + (x+1) * tileSize;
        double miny = TILE_ORIGIN[ORIG_Y] - (y+1) * tileSize;
        double maxy = TILE_ORIGIN[ORIG_Y] - y * tileSize;
        double[] bbox = new double[4];
        bbox[MINX] = minx;
        bbox[MINY] = miny;
        bbox[MAXX] = maxx;
        bbox[MAXY] = maxy;
        return bbox;
    }
    @Override
    public String getTileURLString(long aTile) {
        double[] bbox = getBoundingBox(getX(aTile), getY(aTile), getZoom(aTile));
        String s = String.format(Locale.US, WMS_FORMAT_STRING, getBaseUrl(), layer, bbox[MINX],
                bbox[MINY], bbox[MAXX], bbox[MAXY]);
        Log.d("WMSDEMO", s);
        return s;
    }
}
