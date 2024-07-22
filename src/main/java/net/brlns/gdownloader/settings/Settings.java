/*
 * Copyright (C) 2024 hstr0100
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package net.brlns.gdownloader.settings;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;
import java.util.TreeMap;
import lombok.Data;
import net.brlns.gdownloader.settings.enums.*;

/**
 * @author Gabriel / hstr0100 / vertx010
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Settings{

    @JsonProperty("QualitySettings")
    private Map<WebFilterEnum, QualitySettings> qualitySettings = new TreeMap<>();

    public Settings(){
        for(WebFilterEnum filter : WebFilterEnum.values()){
            QualitySettings quality;

            if(filter == WebFilterEnum.TWITCH){
                quality = QualitySettings.builder()
                    .selector(QualitySelectorEnum.WORST)
                    .minHeight(ResolutionEnum.RES_480)
                    .maxHeight(ResolutionEnum.RES_720)
                    .build();
            }else{
                quality = QualitySettings.builder().build();
            }

            qualitySettings.put(filter, quality);
        }
    }

    @JsonIgnore
    public QualitySettings getDefaultQualitySettings(){
        QualitySettings settings = qualitySettings.get(WebFilterEnum.DEFAULT);
        if(settings == null){
            settings = QualitySettings.builder().build();

            qualitySettings.put(WebFilterEnum.DEFAULT, settings);
        }

        return settings;
    }

    @JsonProperty("AutomaticUpdates")
    private boolean automaticUpdates = true;

    @JsonProperty("Language")
    private LanguageEnum language = LanguageEnum.ENGLISH;

    @JsonProperty("ReadCookies")
    private boolean readCookies = true;

    @JsonProperty("BrowserForCookies")
    private BrowserEnum browser = BrowserEnum.UNSET;

    @JsonProperty("DownloadsPath")
    private String downloadsPath = "";

    //TODO
    @JsonProperty("UIScale")
    private double uiScale = 1.0;

    @JsonProperty("Theme")
    private ThemeEnum theme = ThemeEnum.DARK;

    @JsonProperty("CaptureAnyLinks")
    private boolean captureAnyLinks = false;

    //TODO
    @JsonProperty("ExtraYtDlpArguments")
    private String extraYtDlpArguments = "";

    @JsonProperty("DownloadAudioOnly")
    private boolean downloadAudioOnly = false;

    @JsonProperty("KeepWindowAlwaysOnTop")
    private boolean keepWindowAlwaysOnTop = false;

    @JsonProperty("MaximumSimultaneousDownloads")
    private int maxSimultaneousDownloads = 3;

    @JsonProperty("PlaylistDownloadOption")
    private PlayListOptionEnum playlistDownloadOption = PlayListOptionEnum.ALWAYS_ASK;

    @JsonProperty("DebugMode")
    private boolean debugMode = true;

    @JsonProperty("AutoStart")
    private boolean autoStart = false;

    @JsonProperty("ExitOnClose")
    private boolean exitOnClose = false;

    //TODO add more
    @JsonProperty("Play Sounds")
    private boolean playSounds = false;

}
