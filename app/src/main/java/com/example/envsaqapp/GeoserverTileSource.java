package com.example.envsaqapp;

import org.osmdroid.tileprovider.tilesource.OnlineTileSourceBase;
import org.osmdroid.util.MapTileIndex;

import static org.osmdroid.util.MapTileIndex.getX;
import static org.osmdroid.util.MapTileIndex.getY;
import static org.osmdroid.util.MapTileIndex.getZoom;

public class GeoserverTileSource extends OnlineTileSourceBase {

    public static String[] TILE_URL = {"http://10.28.0.241:8088/geoserver/gwc/demo/cite:lpdv2k12_kbh_no2?gridSet=EPSG:4326&format=image/png"};

    public GeoserverTileSource(String aName, int aZoomMinLevel, int aZoomMaxLevel, int aTileSizePixels, String aImageFilenameEnding, String[] aBaseUrl) {
        super(aName, aZoomMinLevel, aZoomMaxLevel, aTileSizePixels, aImageFilenameEnding, aBaseUrl);
    }

    @Override
    public String getTileURLString(long pTile) {
        return TILE_URL[0] + "/" + getZoom(pTile) + "/" + getX(pTile) + "/" + getY(pTile);
    }
}
