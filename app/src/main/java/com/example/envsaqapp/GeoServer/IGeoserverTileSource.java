package com.example.envsaqapp.GeoServer;

import org.osmdroid.tileprovider.tilesource.ITileSource;
import org.osmdroid.util.MapTileIndex;

public interface IGeoserverTileSource extends ITileSource {
    String getTileURLString(long aTile);
}
